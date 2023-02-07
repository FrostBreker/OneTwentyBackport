package fr.frostbreker.onetwenty;

import fr.frostbreker.onetwenty.init.ModBlocks;
import fr.frostbreker.onetwenty.init.ModItems;
import fr.frostbreker.onetwenty.utils.Reference;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
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

    public OneTwentyMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);


        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
            event.accept(ModItems.WILD_ARMOR_TRIM_SMITHING_TEMPLATE);
            event.accept(ModItems.COAST_ARMOR_TRIM_SMITHING_TEMPLATE);
            event.accept(ModItems.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE);
            event.accept(ModItems.EYE_ARMOR_TRIM_SMITHING_TEMPLATE);
            event.accept(ModItems.RIB_ARMOR_TRIM_SMITHING_TEMPLATE);
            event.accept(ModItems.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE);
            event.accept(ModItems.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE);
            event.accept(ModItems.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE);
            event.accept(ModItems.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE);
            event.accept(ModItems.VEX_ARMOR_TRIM_SMITHING_TEMPLATE);
            event.accept(ModItems.WARD_ARMOR_TRIM_SMITHING_TEMPLATE);

        }

        if(event.getTab() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.BAMBOO_BLOCK);
            event.accept(ModBlocks.BAMBOO_PLANKS);
            event.accept(ModBlocks.STRIPPED_BAMBOO_BLOCK);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }

    }
}
