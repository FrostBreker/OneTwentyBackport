package fr.frostbreker.onetwenty.world;

import fr.frostbreker.onetwenty.init.ModBlocks;
import fr.frostbreker.onetwenty.utils.Reference;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Reference.MODID);


    public static final RegistryObject<PlacedFeature> CHERRY_TREE_CHECKED = PLACED_FEATURES.register("cherry_checked",
            () -> new PlacedFeature(ModConfiguredFeatures.CHERRY_TREE.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.CHERRY_SAPLING.get()))));

    public static final RegistryObject<PlacedFeature> CHERRY_TREE_PLACED = PLACED_FEATURES.register("cherry_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.CHERRY_TREE_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2))));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}