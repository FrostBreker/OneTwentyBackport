package fr.frostbreker.onetwenty.entity;

import fr.frostbreker.onetwenty.entity.custom.camel.Camel;
import fr.frostbreker.onetwenty.utils.Reference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Reference.MODID);

    public static final RegistryObject<EntityType<Camel>> CAMEL = ENTITY_TYPES.register("camel",
            () -> EntityType.Builder.of(Camel::new, MobCategory.CREATURE)
                    .sized(1.7F, 2.375F)
                    .clientTrackingRange(10)
                    .build("camel"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
