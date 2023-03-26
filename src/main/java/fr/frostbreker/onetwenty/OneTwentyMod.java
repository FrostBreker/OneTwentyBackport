package fr.frostbreker.onetwenty;

import fr.frostbreker.onetwenty.entity.ModEntityTypes;
import fr.frostbreker.onetwenty.init.ModBlocks;
import fr.frostbreker.onetwenty.init.ModItems;
import fr.frostbreker.onetwenty.objects.blocks.ModBlockEntities;
import fr.frostbreker.onetwenty.objects.blocks.ModWoodTypes;
import fr.frostbreker.onetwenty.screen.ModMenuTypes;
import fr.frostbreker.onetwenty.utils.Reference;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
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

    public OneTwentyMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModEntityTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodTypes.CHERRY);
        });
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
            event.accept(ModBlocks.BAMBOO_SLAB);
            event.accept(ModBlocks.BAMBOO_STAIRS);
            event.accept(ModBlocks.BAMBOO_FENCE);
            event.accept(ModBlocks.BAMBOO_FENCE_GATE);
            event.accept(ModBlocks.BAMBOO_DOOR);
            event.accept(ModBlocks.BAMBOO_TRAPDOOR);
            event.accept(ModBlocks.BAMBOO_MOSAIC);
            event.accept(ModBlocks.BAMBOO_MOSAIC_SLAB);
            event.accept(ModBlocks.BAMBOO_MOSAIC_STAIRS);


            event.accept(ModBlocks.CHISELED_BOOKSHELF);

            //Cherry
            event.accept(ModBlocks.CHERRY_LOG);
            event.accept(ModBlocks.STRIPPED_CHERRY_LOG);
            event.accept(ModBlocks.CHERRY_WOOD);
            event.accept(ModBlocks.STRIPPED_CHERRY_WOOD);
            event.accept(ModBlocks.CHERRY_PLANKS);
            event.accept(ModBlocks.CHERRY_SLAB);
            event.accept(ModBlocks.CHERRY_STAIRS);
            event.accept(ModBlocks.CHERRY_FENCE);
            event.accept(ModBlocks.CHERRY_FENCE_GATE);
            event.accept(ModBlocks.CHERRY_DOOR);
            event.accept(ModBlocks.CHERRY_TRAPDOOR);
            event.accept(ModBlocks.CHERRY_LEAVES);
            event.accept(ModBlocks.CHERRY_BUTTON);
            event.accept(ModBlocks.CHERRY_PRESSURE_PLATE);
            event.accept(ModItems.CHERRY_SIGN);
            event.accept(ModItems.BAMBOO_SIGN);
        }

        if(event.getTab() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(ModBlocks.CHISELED_BOOKSHELF);
            event.accept(ModBlocks.BAMBOO_PRESSURE_PLATE);
            event.accept(ModBlocks.BAMBOO_BUTTON);
        }

        //add raft boat
        if(event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModItems.BAMBOO_CHEST_RAFT);
            event.accept(ModItems.BAMBOO_RAFT);
        }

        if(event.getTab() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.CHERRY_LEAVES);
            event.accept(ModBlocks.CHERRY_SAPLING);
        }
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            WoodType.register(ModWoodTypes.CHERRY);
            BlockEntityRenderers.register(ModBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
            //BlockEntityRenderers.register(ModBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get(), HangingSignRenderer::new);
        }
    }
}
