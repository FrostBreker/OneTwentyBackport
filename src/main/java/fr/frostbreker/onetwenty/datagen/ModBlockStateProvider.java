package fr.frostbreker.onetwenty.datagen;

import fr.frostbreker.onetwenty.init.ModBlocks;
import fr.frostbreker.onetwenty.utils.Reference;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Reference.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        logBlock(((RotatedPillarBlock) ModBlocks.CHERRY_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.CHERRY_WOOD.get(), blockTexture(ModBlocks.CHERRY_LOG.get()), blockTexture(ModBlocks.CHERRY_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CHERRY_LOG.get(), new ResourceLocation(Reference.MODID, "block/stripped_ebony_log"),
                new ResourceLocation(Reference.MODID, "block/stripped_cherry_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CHERRY_WOOD.get(), new ResourceLocation(Reference.MODID, "block/stripped_ebony_log"),
                new ResourceLocation(Reference.MODID, "block/stripped_cherry_log"));

        blockWithItem(ModBlocks.CHERRY_PLANKS);
        blockWithItem(ModBlocks.CHERRY_LEAVES);
        saplingBlock(ModBlocks.CHERRY_SAPLING);

        simpleBlockItem(ModBlocks.CHERRY_LOG.get(), models().withExistingParent("onetwenty:cherry_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.CHERRY_WOOD.get(), models().withExistingParent("onetwenty:cherry_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_CHERRY_LOG.get(), models().withExistingParent("onetwenty:stripped_cherry_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_CHERRY_WOOD.get(), models().withExistingParent("onetwenty:stripped_cherry_wood", "minecraft:block/cube_column"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}
