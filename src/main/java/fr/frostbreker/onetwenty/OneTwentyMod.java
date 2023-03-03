package fr.frostbreker.onetwenty;

import fr.frostbreker.onetwenty.blocks.entity.ModBlockEntities;
import fr.frostbreker.onetwenty.blocks.entity.ModWoodTypes;
import fr.frostbreker.onetwenty.init.ModBlocks;
import fr.frostbreker.onetwenty.init.ModItems;
import fr.frostbreker.onetwenty.utils.Reference;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class OneTwentyMod {
    private static final Logger LOGGER = LogManager.getLogger();

    public OneTwentyMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModBlockEntities.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BAMBOO_DOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BAMBOO_TRAPDOOR.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_DOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_TRAPDOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CHERRY_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_CHERRY_SAPLING.get(), RenderType.cutout());

        WoodType.register(ModWoodTypes.BAMBOO);
        WoodType.register(ModWoodTypes.CHERRY);
        BlockEntityRenderers.register(ModBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.CHERRY_SAPLING.getId(), ModBlocks.POTTED_CHERRY_SAPLING);

            Sheets.addWoodType(ModWoodTypes.BAMBOO);
            Sheets.addWoodType(ModWoodTypes.CHERRY);
        });
    }
}
