package fr.frostbreker.onetwenty.init;

import fr.frostbreker.onetwenty.utils.Reference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    /*public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item", () -> new Item(
            new Item.Properties()
                    .tab(CreativeModeTab.TAB_SEARCH)
    )); */

    public static final RegistryObject<Item> BAMBOO_SIGN = ITEMS.register("bamboo_sign",
            () -> new SignItem(new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS).stacksTo(16),
                    ModBlocks.BAMBOO_SIGN.get(), ModBlocks.BAMBOO_WALL_SIGN.get()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
