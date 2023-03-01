package fr.frostbreker.onetwenty.blocks.custom;

import fr.frostbreker.onetwenty.blocks.entity.custom.ChiseledBookshelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class ChiseledBookshelfBlock extends BaseEntityBlock {
    private static final int MAX_BOOKS_IN_STORAGE = 6;
    public static final int BOOKS_PER_ROW = 3;
    public static final BooleanProperty CHISELED_BOOKSHELF_SLOT_0_OCCUPIED = BooleanProperty.create("slot_0_occupied");
    public static final BooleanProperty CHISELED_BOOKSHELF_SLOT_1_OCCUPIED = BooleanProperty.create("slot_1_occupied");
    public static final BooleanProperty CHISELED_BOOKSHELF_SLOT_2_OCCUPIED = BooleanProperty.create("slot_2_occupied");
    public static final BooleanProperty CHISELED_BOOKSHELF_SLOT_3_OCCUPIED = BooleanProperty.create("slot_3_occupied");
    public static final BooleanProperty CHISELED_BOOKSHELF_SLOT_4_OCCUPIED = BooleanProperty.create("slot_4_occupied");
    public static final BooleanProperty CHISELED_BOOKSHELF_SLOT_5_OCCUPIED = BooleanProperty.create("slot_5_occupied");
    public static final List<BooleanProperty> SLOT_OCCUPIED_PROPERTIES = List.of(
            CHISELED_BOOKSHELF_SLOT_0_OCCUPIED,
            CHISELED_BOOKSHELF_SLOT_1_OCCUPIED,
            CHISELED_BOOKSHELF_SLOT_2_OCCUPIED,
            CHISELED_BOOKSHELF_SLOT_3_OCCUPIED,
            CHISELED_BOOKSHELF_SLOT_4_OCCUPIED,
            CHISELED_BOOKSHELF_SLOT_5_OCCUPIED
    );

    public ChiseledBookshelfBlock(BlockBehaviour.Properties properties) {
        super(properties);
        BlockState blockstate = this.stateDefinition.any().setValue(HorizontalDirectionalBlock.FACING, Direction.NORTH);

        for(BooleanProperty booleanproperty : SLOT_OCCUPIED_PROPERTIES) {
            blockstate = blockstate.setValue(booleanproperty, Boolean.valueOf(false));
        }

        this.registerDefaultState(blockstate);
    }

    public RenderShape getRenderShape(BlockState p_251274_) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level world, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        BlockEntity $$8 = world.getBlockEntity(pos);
        if ($$8 instanceof ChiseledBookshelfBlockEntity chiseledbookshelfblockentity) {
            Optional<Vec2> optional = getRelativeHitCoordinatesForBlockFace(blockHitResult, blockState.getValue(HorizontalDirectionalBlock.FACING));
            if (optional.isEmpty()) {
                return InteractionResult.PASS;
            } else {
                int i = getHitSlot(optional.get());
                if (blockState.getValue(SLOT_OCCUPIED_PROPERTIES.get(i))) {
                    removeBook(world, pos, player, chiseledbookshelfblockentity, i);
                    return InteractionResult.sidedSuccess(world.isClientSide);
                } else {
                    ItemStack itemstack = player.getItemInHand(interactionHand);
                    if (itemstack.is(ItemTags.LECTERN_BOOKS) || itemstack.is(Items.BOOK) || itemstack.is(Items.ENCHANTED_BOOK)) {
                        addBook(world, pos, player, chiseledbookshelfblockentity, itemstack, i);
                        return InteractionResult.sidedSuccess(world.isClientSide);
                    } else {
                        return InteractionResult.CONSUME;
                    }
                }
            }
        } else {
            return InteractionResult.PASS;
        }
    }

    private static Optional<Vec2> getRelativeHitCoordinatesForBlockFace(BlockHitResult p_261714_, Direction p_262116_) {
        Direction direction = p_261714_.getDirection();
        if (p_262116_ != direction) {
            return Optional.empty();
        } else {
            BlockPos blockpos = p_261714_.getBlockPos().relative(direction);
            Vec3 vec3 = p_261714_.getLocation().subtract((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
            double d0 = vec3.x();
            double d1 = vec3.y();
            double d2 = vec3.z();
            Optional optional;
            switch (direction) {
                case NORTH:
                    optional = Optional.of(new Vec2((float)(1.0D - d0), (float)d1));
                    break;
                case SOUTH:
                    optional = Optional.of(new Vec2((float)d0, (float)d1));
                    break;
                case WEST:
                    optional = Optional.of(new Vec2((float)d2, (float)d1));
                    break;
                case EAST:
                    optional = Optional.of(new Vec2((float)(1.0D - d2), (float)d1));
                    break;
                case DOWN:
                case UP:
                    optional = Optional.empty();
                    break;
                default:
                    throw new IncompatibleClassChangeError();
            }

            return optional;
        }
    }

    private static int getHitSlot(Vec2 p_261991_) {
        int i = p_261991_.y >= 0.5F ? 0 : 1;
        int j = getSection(p_261991_.x);
        return j + i * 3;
    }

    private static int getSection(float p_261599_) {
        float f = 0.0625F;
        float f1 = 0.375F;
        if (p_261599_ < 0.375F) {
            return 0;
        } else {
            float f2 = 0.6875F;
            return p_261599_ < 0.6875F ? 1 : 2;
        }
    }

    private static void addBook(Level level, BlockPos pos, Player player, ChiseledBookshelfBlockEntity chiseledBookshelfBlockEntity, ItemStack itemStack, int i) {
        if (!level.isClientSide) {
            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            SoundEvent soundevent = SoundEvents.BUNDLE_INSERT;
            chiseledBookshelfBlockEntity.setItem(i, itemStack.split(1));
            level.playSound((Player)null, pos, soundevent, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (player.isCreative()) {
                itemStack.grow(1);
            }

            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        }
    }

    private static void removeBook(Level p_262654_, BlockPos p_262601_, Player p_262636_, ChiseledBookshelfBlockEntity chiseledBookshelfBlockEntity, int p_262673_) {
        if (!p_262654_.isClientSide) {
            ItemStack itemstack = chiseledBookshelfBlockEntity.removeItem(p_262673_, 1);
            SoundEvent soundevent = SoundEvents.BUNDLE_REMOVE_ONE;
            p_262654_.playSound((Player)null, p_262601_, soundevent, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!p_262636_.getInventory().add(itemstack)) {
                p_262636_.drop(itemstack, false);
            }

            p_262654_.gameEvent(p_262636_, GameEvent.BLOCK_CHANGE, p_262601_);
        }
    }

    public @Nullable BlockEntity newBlockEntity(BlockPos p_250440_, BlockState p_248729_) {
        return new ChiseledBookshelfBlockEntity(p_250440_, p_248729_);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_250973_) {
        p_250973_.add(HorizontalDirectionalBlock.FACING);
        SLOT_OCCUPIED_PROPERTIES.forEach((p_261456_) -> {
            p_250973_.add(p_261456_);
        });
    }

    public void onRemove(BlockState p_250071_, Level p_251485_, BlockPos p_251954_, BlockState p_251852_, boolean p_252250_) {
        if (!p_250071_.is(p_251852_.getBlock())) {
            BlockEntity blockentity = p_251485_.getBlockEntity(p_251954_);
            if (blockentity instanceof ChiseledBookshelfBlockEntity) {
                ChiseledBookshelfBlockEntity chiseledbookshelfblockentity = (ChiseledBookshelfBlockEntity)blockentity;
                if (!chiseledbookshelfblockentity.isEmpty()) {
                    for(int i = 0; i < 6; ++i) {
                        ItemStack itemstack = chiseledbookshelfblockentity.getItem(i);
                        if (!itemstack.isEmpty()) {
                            Containers.dropItemStack(p_251485_, (double)p_251954_.getX(), (double)p_251954_.getY(), (double)p_251954_.getZ(), itemstack);
                        }
                    }

                    chiseledbookshelfblockentity.clearContent();
                    p_251485_.updateNeighbourForOutputSignal(p_251954_, this);
                }
            }

            super.onRemove(p_250071_, p_251485_, p_251954_, p_251852_, p_252250_);
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext p_251318_) {
        return this.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, p_251318_.getHorizontalDirection().getOpposite());
    }

    public boolean hasAnalogOutputSignal(BlockState p_249302_) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState p_249192_, Level p_252207_, BlockPos p_248999_) {
        if (p_252207_.isClientSide()) {
            return 0;
        } else {
            BlockEntity blockentity = p_252207_.getBlockEntity(p_248999_);
            if (blockentity instanceof ChiseledBookshelfBlockEntity) {
                ChiseledBookshelfBlockEntity chiseledbookshelfblockentity = (ChiseledBookshelfBlockEntity)blockentity;
                return chiseledbookshelfblockentity.getLastInteractedSlot() + 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @org.jetbrains.annotations.Nullable Direction direction) {
        return true;
    }
}
