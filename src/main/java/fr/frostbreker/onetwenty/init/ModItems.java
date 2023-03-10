package fr.frostbreker.onetwenty.init;

import fr.frostbreker.onetwenty.entity.ModEntityTypes;
import fr.frostbreker.onetwenty.utils.Reference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.flag.FeatureFlags;
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
   public static final RegistryObject<Item> BAMBOO_RAFT = ITEMS.register("bamboo_raft", () -> new BoatItem(false, Boat.Type.BAMBOO, (new Item.Properties()).stacksTo(1)));
   public static final RegistryObject<Item> BAMBOO_CHEST_RAFT = ITEMS.register("bamboo_chest_raft", () -> new BoatItem(true, Boat.Type.BAMBOO, (new Item.Properties()).stacksTo(1)));


   //Signs and Hanging Signs

   //Signs
   public static final RegistryObject<Item> CHERRY_SIGN = ITEMS.register("cherry_sign",
           () -> new SignItem(new Item.Properties().stacksTo(16),
                   ModBlocks.CHERRY_SIGN.get(), ModBlocks.CHERRY_WALL_SIGN.get()));

   public static final RegistryObject<Item> BAMBOO_SIGN = ITEMS.register("bamboo_sign",
           () -> new SignItem(new Item.Properties().stacksTo(16),
                   ModBlocks.BAMBOO_SIGN.get(), ModBlocks.BAMBOO_WALL_SIGN.get()));

   //Hanging Signs
  /* public static final RegistryObject<Item> CHERRY_HANGING_SIGN = ITEMS.register("cherry_hanging_sign",
           () -> new HangingSignItem(
                   ModBlocks.CHERRY_HANGING_SIGN.get(),
                   ModBlocks.CHERRY_WALL_HANGING_SIGN.get(),
                   new Item.Properties().stacksTo(16)
           )); */


   public static void register(IEventBus eventBus) {
      ITEMS.register(eventBus);
   }

}
