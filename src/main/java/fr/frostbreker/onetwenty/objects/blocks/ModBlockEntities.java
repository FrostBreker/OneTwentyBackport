package fr.frostbreker.onetwenty.objects.blocks;

import fr.frostbreker.onetwenty.OneTwentyMod;
import fr.frostbreker.onetwenty.init.ModBlocks;
import fr.frostbreker.onetwenty.objects.blocks.custom.chiseledbookshelf.ChiseledBookShelfBlockEntity2;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OneTwentyMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ChiseledBookShelfBlockEntity2>> CHISELED_BOOKSHELF =
            BLOCK_ENTITIES.register("chiseled_bookshelf", () ->
                    BlockEntityType.Builder.of(ChiseledBookShelfBlockEntity2::new,
                            ModBlocks.CHISELED_BOOKSHELF.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("sign_block_entity", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlocks.CHERRY_WALL_SIGN.get(),
                            ModBlocks.CHERRY_SIGN.get(),
                            ModBlocks.BAMBOO_WALL_SIGN.get(),
                            ModBlocks.BAMBOO_SIGN.get()
                    ).build(null));

   /* public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> HANGING_SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("hanging_sign_block_entity", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ModBlocks.CHERRY_HANGING_SIGN.get(),
                            ModBlocks.CHERRY_WALL_HANGING_SIGN.get()
                    ).build(null)); */


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
