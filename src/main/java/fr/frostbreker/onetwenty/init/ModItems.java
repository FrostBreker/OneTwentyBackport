package fr.frostbreker.onetwenty.init;

import fr.frostbreker.onetwenty.utils.Reference;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

   public static final RegistryObject<Item> NETHERITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("netherite_upgrade_smithing_template", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> DUNE_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("dune_armor_trim_smithing_template", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> COAST_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("coast_armor_trim_smithing_template", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> EYE_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("eye_armor_trim_smithing_template", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> RIB_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("rib_armor_trim_smithing_template", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("sentry_armor_trim_smithing_template", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("snout_armor_trim_smithing_template", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("spire_armor_trim_smithing_template", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> TIDE_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("tide_armor_trim_smithing_template", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> VEX_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("vex_armor_trim_smithing_template", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> WARD_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("ward_armor_trim_smithing_template", () -> new Item(new Item.Properties()));
   public static final RegistryObject<Item> WILD_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.register("wild_armor_trim_smithing_template", () -> new Item(new Item.Properties()));

   public static void register(IEventBus eventBus) {
      ITEMS.register(eventBus);
   }

}
