package fr.frostbreker.onetwenty;

import fr.frostbreker.onetwenty.entity.ModEntityTypes;
import fr.frostbreker.onetwenty.init.ModBlocks;
import fr.frostbreker.onetwenty.init.ModItems;
import fr.frostbreker.onetwenty.objects.blocks.ModBlockEntities;
import fr.frostbreker.onetwenty.objects.blocks.ModWoodTypes;
import fr.frostbreker.onetwenty.screen.ModMenuTypes;
import fr.frostbreker.onetwenty.utils.Reference;
import com.mojang.logging.LogUtils;
import fr.frostbreker.onetwenty.world.ModConfiguredFeatures;
import fr.frostbreker.onetwenty.world.ModPlacedFeatures;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Reference.MODID)
public class OneTwentyMod {
    public static final String MOD_ID = Reference.MODID;
    private static final Logger LOGGER = LogUtils.getLogger();

    public OneTwentyMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModEntityTypes.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodTypes.CHERRY);
            Sheets.addWoodType(ModWoodTypes.BAMBOO);
        });
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            WoodType.register(ModWoodTypes.CHERRY);
            WoodType.register(ModWoodTypes.BAMBOO);
            BlockEntityRenderers.register(ModBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
            //BlockEntityRenderers.register(ModBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get(), HangingSignRenderer::new);
        }
    }
}
