package fr.frostbreker.onetwenty.world;

import fr.frostbreker.onetwenty.utils.Reference;
import fr.frostbreker.onetwenty.world.gen.ModTreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModTreeGeneration.generateTrees(event);
    }
}
