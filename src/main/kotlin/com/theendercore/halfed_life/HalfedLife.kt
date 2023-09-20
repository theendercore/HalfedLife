package com.theendercore.halfed_life

import com.theendercore.halfed_life.blocks.HLBlocks
import com.theendercore.halfed_life.blocks.HLBlocks.CUTOUT_LIST
import com.theendercore.halfed_life.items.HLItems
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.Block
import net.minecraft.client.render.RenderLayer
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

@Suppress("unused")
object HalfedLife {
    const val MODID = "halfed_life"

    @JvmField
    val LOGGER = LoggerFactory.getLogger(MODID)


    fun commonInit() {
        LOGGER.info("HalfedLife Common")
        HLItems.init()
        HLBlocks.init()
    }

    fun clientInit() {
        CUTOUT_LIST.forEach { BlockRenderLayerMap.INSTANCE.putBlock(it, RenderLayer.getCutout()) }
    }

    fun id(path: String) = Identifier(MODID, path)
    fun mc(path: String) = Identifier(path)

    fun getId(item: Item): Identifier = Registries.ITEM.getId(item)
    fun getId(block: Block): Identifier = Registries.BLOCK.getId(block)
}