package com.theendercore.halfed_life.data.providers

import com.theendercore.halfed_life.blocks.HLBlocks
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {

    val FULL_SQUARE_LIST = listOf(
        HLBlocks.OAK_CRATE,
        HLBlocks.SPRUCE_CRATE,
        HLBlocks.PILLARIUM,
        HLBlocks.CUT_PILLARIUM
    )

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        try {
            FULL_SQUARE_LIST.forEach { gen.registerSimpleCubeAll(it) }
        } catch (e: Exception) {
            LOGGER.error("Error {}", e.toString())
        }
    }


    override fun generateItemModels(gen: ItemModelGenerator) {}


}