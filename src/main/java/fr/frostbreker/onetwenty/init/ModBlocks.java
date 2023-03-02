package fr.frostbreker.onetwenty.init;

import fr.frostbreker.onetwenty.OneTwentyMod;
import fr.frostbreker.onetwenty.objects.blocks.ModFlammableRotatedPillarBlock;
import fr.frostbreker.onetwenty.objects.blocks.ModWoodTypes;
import fr.frostbreker.onetwenty.objects.blocks.custom.signs.ModStandingSignBlock;
import fr.frostbreker.onetwenty.objects.blocks.custom.signs.ModWallSignBlock;
import fr.frostbreker.onetwenty.objects.blocks.custom.chiseledbookshelf.ChiseledBookShelfBlock2;
import fr.frostbreker.onetwenty.world.tree.CherryTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.BAMBOO;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OneTwentyMod.MOD_ID);

    public static final RegistryObject<Block> CHISELED_BOOKSHELF = registerBlock("chiseled_bookshelf", () -> new ChiseledBookShelfBlock2(
            BlockBehaviour.Properties.of(Material.WOOD).strength(1.5F)), CreativeModeTab.TAB_REDSTONE);

    //Bamboo
    public static final RegistryObject<Block> BAMBOO_BLOCK = registerBlock("bamboo_block", () -> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_BAMBOO_BLOCK = registerBlock("stripped_bamboo_block", () -> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.BAMBOO)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAMBOO_PLANKS = registerBlock("bamboo_planks", () -> new Block(
            BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAMBOO_SLAB = registerBlock("bamboo_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).sound(SoundType.BAMBOO)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAMBOO_STAIRS = registerBlock("bamboo_stairs", () -> new StairBlock(
            () -> BAMBOO_PLANKS.get().defaultBlockState(),
            BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).sound(SoundType.BAMBOO)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAMBOO_FENCE = registerBlock("bamboo_fence", () -> new FenceBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.BAMBOO)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAMBOO_FENCE_GATE = registerBlock("bamboo_fence_gate", () -> new FenceGateBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).sound(SoundType.BAMBOO)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BAMBOO_DOOR = registerBlock("bamboo_door", () -> new DoorBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).sound(SoundType.BAMBOO)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }

    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAMBOO_TRAPDOOR = registerBlock("bamboo_trapdoor", () -> new TrapDoorBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).sound(SoundType.BAMBOO)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);


    public static final RegistryObject<Block> BAMBOO_PRESSURE_PLATE = registerBlock("bamboo_pressure_plate", () -> new PressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            BlockBehaviour.Properties.of(Material.WOOD, BAMBOO_PLANKS.get().defaultMaterialColor())
                    .noCollission().strength(0.5F)), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> BAMBOO_BUTTON = registerBlock("bamboo_button", () -> new ButtonBlock(
            true,
            BlockBehaviour.Properties.of(Material.DECORATION)
                    .noCollission().strength(0.5F)) {
        @Override
        protected SoundEvent getSound(boolean p_51102_) {
            return SoundEvents.WOODEN_BUTTON_CLICK_ON;
        }
    }, CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> BAMBOO_MOSAIC = registerBlock("bamboo_mosaic", () -> new Block(
            BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_YELLOW)
                    .strength(2.0F, 3.0F)
                    ), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAMBOO_MOSAIC_STAIRS = registerBlock("bamboo_mosaic_stairs", () -> new StairBlock(
            BAMBOO_MOSAIC.get().defaultBlockState(),
            BlockBehaviour.Properties.copy(BAMBOO_MOSAIC.get())), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAMBOO_MOSAIC_SLAB = registerBlock("bamboo_mosaic_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_YELLOW)
                    .strength(2.0F, 3.0F)
                    ), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> BAMBOO_SAPLING = registerBlockWithoutBlockItem("bamboo_sapling", () -> new BambooSaplingBlock(
            BlockBehaviour.Properties.of(Material.BAMBOO_SAPLING)
                    .randomTicks().instabreak().noCollission()
                    .strength(1.0F).sound(SoundType.BAMBOO_SAPLING)
                    .offsetType(BlockBehaviour.OffsetType.XZ)));

    public static final RegistryObject<Block> POTTED_BAMBOO = registerBlockWithoutBlockItem("potted_bamboo", () -> new FlowerPotBlock(
            BAMBOO,
            BlockBehaviour.Properties.of(Material.DECORATION).
                    instabreak().
                    noOcclusion()
    ));

    //Cherry

    public static final RegistryObject<Block> CHERRY_LOG = registerBlock("cherry_log", () -> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_WOOD = registerBlock("cherry_wood", () -> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STRIPPED_CHERRY_LOG = registerBlock("stripped_cherry_log", () -> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> STRIPPED_CHERRY_WOOD = registerBlock("stripped_cherry_wood", () -> new ModFlammableRotatedPillarBlock(
            BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.WOOD)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_LEAVES = registerBlock("cherry_leaves", () -> new LeavesBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).sound(SoundType.GRASS)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 30;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 60;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_PLANKS = registerBlock("cherry_planks", () -> new Block(
            BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }

    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_SLAB = registerBlock("cherry_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_SLAB).sound(SoundType.WOOD)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_STAIRS = registerBlock("cherry_stairs", () -> new StairBlock(
            () -> CHERRY_PLANKS.get().defaultBlockState(),
            BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS).sound(SoundType.WOOD)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_FENCE = registerBlock("cherry_fence", () -> new FenceBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.WOOD)) {
        @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_FENCE_GATE = registerBlock("cherry_fence_gate", () -> new FenceGateBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).sound(SoundType.WOOD)) {
       @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CHERRY_DOOR = registerBlock("cherry_door", () -> new DoorBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).sound(SoundType.WOOD)) {
       @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }

    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_TRAPDOOR = registerBlock("cherry_trapdoor", () -> new TrapDoorBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).sound(SoundType.WOOD)) {
       @Override
        public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return true;
        }

        @Override
        public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 5;
        }

        @Override
        public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
            return 20;
        }
    }, CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHERRY_PRESSURE_PLATE = registerBlock("cherry_pressure_plate", () -> new PressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            BlockBehaviour.Properties.of(Material.WOOD, CHERRY_PLANKS.get().defaultMaterialColor())
                    .noCollission().strength(0.5F).sound(SoundType.WOOD)
    ), CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> CHERRY_BUTTON = registerBlock("cherry_button", () -> new ButtonBlock(
            true,
            BlockBehaviour.Properties.of(Material.DECORATION)
                    .noCollission().strength(0.5F)
                    .sound(SoundType.WOOD)) {
        @Override
        protected SoundEvent getSound(boolean p_51102_) {
            return SoundEvents.WOODEN_BUTTON_CLICK_ON;
        }
    }, CreativeModeTab.TAB_REDSTONE);

    public static final RegistryObject<Block> CHERRY_SAPLING = registerBlock("cherry_sapling", () -> new SaplingBlock(
            new CherryTreeGrower(),
            BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)
                    .randomTicks().instabreak().noCollission()
                    .strength(1.0F).sound(SoundType.BAMBOO_SAPLING)
                    .offsetType(BlockBehaviour.OffsetType.XZ)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> POTTED_CHERRY = registerBlockWithoutBlockItem("potted_cherry", () -> new FlowerPotBlock(
            CHERRY_SAPLING.get(),
            BlockBehaviour.Properties.of(Material.DECORATION).
                    instabreak().
                    noOcclusion()
    ));


    //Signs and Hanging Signs

    //Signs
    public static final RegistryObject<Block> BAMBOO_WALL_SIGN = registerBlockWithoutBlockItem("bamboo_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.BAMBOO));

    public static final RegistryObject<Block> BAMBOO_SIGN = registerBlockWithoutBlockItem("bamboo_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.BAMBOO));

    public static final RegistryObject<Block> CHERRY_WALL_SIGN = registerBlockWithoutBlockItem("cherry_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.CHERRY));

    public static final RegistryObject<Block> CHERRY_SIGN = registerBlockWithoutBlockItem("cherry_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.CHERRY));




    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
