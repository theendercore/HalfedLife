package com.theendercore.halfed_life.blocks

import com.theendercore.halfed_life.HalfedLife.id
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import java.util.*

object HLBlocks {
    val BLOCKS = LinkedList<Block>()
    val CUTOUT_LIST = LinkedList<Block>()

    val OAK_CRATE: Block = Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS))
    val SPRUCE_CRATE: Block = Block(FabricBlockSettings.copy(Blocks.SPRUCE_PLANKS))

    val ITEM_CRATE: Block = ItemCreate(FabricBlockSettings.copy(Blocks.OAK_PLANKS))

    val GRAY_BARREL: Block = HLBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))
    val WHITE_BARREL: Block = HLBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))
    val BROWN_BARREL: Block = HLBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))
    val EXPLOSIVE_BARREL: Block = ExplosiveBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))


    val FENCE: Block = HLFence(FabricBlockSettings.copy(Blocks.CAULDRON))
    val COMPUTER: Block = Computer(FabricBlockSettings.copy(Blocks.WHITE_CONCRETE))

    fun init() {
        registerWithItem("oak_crate", OAK_CRATE)
        registerWithItem("spruce_crate", SPRUCE_CRATE)

        registerWithItem("item_crate", ITEM_CRATE)

        registerWithItem("gray_barrel", GRAY_BARREL)
        registerWithItem("white_barrel", WHITE_BARREL)
        registerWithItem("brown_barrel", BROWN_BARREL)
        registerWithItem("explosive_barrel", EXPLOSIVE_BARREL)

        registerWithItem("fence", FENCE)
        registerWithItem("computer", COMPUTER)

        CUTOUT_LIST.addAll(
            listOf(
                GRAY_BARREL,
                WHITE_BARREL,
                BROWN_BARREL,
                EXPLOSIVE_BARREL,
                ITEM_CRATE,
                FENCE,
                COMPUTER
            )
        )
    }

    private fun registerWithItem(id: String, block: Block): Block {
        val item = Registry.register(Registries.ITEM, id(id), BlockItem(block, FabricItemSettings()))
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS)
            .register(ItemGroupEvents.ModifyEntries { it.prepend(item) })
        return register(id, block)
    }

    private fun register(id: String, block: Block): Block {
        BLOCKS.add(block)
        return Registry.register(Registries.BLOCK, id(id), block)
    }
}