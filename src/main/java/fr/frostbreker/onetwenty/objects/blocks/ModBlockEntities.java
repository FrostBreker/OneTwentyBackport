package fr.frostbreker.onetwenty.objects.blocks;

import fr.frostbreker.onetwenty.OneTwentyMod;
import fr.frostbreker.onetwenty.init.ModBlocks;
import fr.frostbreker.onetwenty.objects.blocks.chiseledbookshelf.ChiseledBookShelfBlockEntity2;
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


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
