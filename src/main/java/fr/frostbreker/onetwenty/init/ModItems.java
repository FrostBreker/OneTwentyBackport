package fr.frostbreker.onetwenty.init;

import fr.frostbreker.onetwenty.utils.Reference;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MODID);

   public static final RegistryObject<Item> NETHERITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("netherite_upgrade_smithing_template", () -> new Item(new Item.Properties()));


   public static void register(IEventBus eventBus) {
      ITEMS.register(eventBus);
   }

}
