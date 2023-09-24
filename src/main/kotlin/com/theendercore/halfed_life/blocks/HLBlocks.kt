package com.theendercore.halfed_life.blocks

import com.theendercore.halfed_life.HalfedLife.id
import com.theendercore.halfed_life.blocks.wallblock.BaseWallBlock
import com.theendercore.halfed_life.blocks.wallblock.WallBlock1x2
import com.theendercore.halfed_life.blocks.wallblock.WallBlock2x1
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
object HLBlocks {
    val BLOCKS = LinkedList<Block>()
    val CUTOUT_LIST = LinkedList<Block>()
    val TRANSPARENT_LIST = LinkedList<Block>()

    val OAK_CRATE: Block = Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS))
    val SPRUCE_CRATE: Block = Block(FabricBlockSettings.copy(Blocks.SPRUCE_PLANKS))
    val PILLARIUM: Block = Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE))
    val CUT_PILLARIUM: Block = Block(FabricBlockSettings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.NETHERITE))

    val ITEM_CRATE: Block = ItemCreate(FabricBlockSettings.copy(Blocks.OAK_PLANKS))
    val WOODEN_PALLET = WoodenPallet(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR))

    val GRAY_BARREL: Block = HLBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))
    val WHITE_BARREL: Block = HLBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))
    val BROWN_BARREL: Block = HLBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))
    val EXPLOSIVE_BARREL: Block = ExplosiveBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))

    val COMPUTER: Block = Computer(FabricBlockSettings.copy(Blocks.WHITE_CONCRETE))

    val CHAIN_LINK_FENCE: Block = ChainLinkFence(FabricBlockSettings.copy(Blocks.IRON_BARS))

    val GRAFFITI_BLACK_1X1: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val GRAFFITI_RED_1X1: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val GRAFFITI_ORANGE_1X1: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val GRAFFITI_YELLOW_2X1: Block = WallBlock2x1(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))

    val POSTER_RUNES: Block = WallBlock1x2(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val POSTER_GOLEM: Block = WallBlock1x2(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val POSTER_FACE: Block = WallBlock1x2(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val POSTER_DARK_RUNES: Block = WallBlock1x2(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val POSTER_CROSSBOW: Block = WallBlock1x2(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))

    fun init() {
        registerWithItem("oak_crate", OAK_CRATE)
        registerWithItem("spruce_crate", SPRUCE_CRATE)
        registerWithItem("pillarium", PILLARIUM)
        registerWithItem("cut_pillarium", CUT_PILLARIUM)

        registerWithItem("item_crate", ITEM_CRATE)
        registerWithItem("wooden_pallet", WOODEN_PALLET)

        registerWithItem("gray_barrel", GRAY_BARREL)
        registerWithItem("white_barrel", WHITE_BARREL)
        registerWithItem("brown_barrel", BROWN_BARREL)
        registerWithItem("explosive_barrel", EXPLOSIVE_BARREL)

        registerWithItem("computer", COMPUTER)

        registerWithItem("chain_link_fence", CHAIN_LINK_FENCE)

        registerWithItem("graffiti_black_1x1", GRAFFITI_BLACK_1X1)
        registerWithItem("graffiti_red_1x1", GRAFFITI_RED_1X1)
        registerWithItem("graffiti_orange_1x1", GRAFFITI_ORANGE_1X1)
        registerWithItem("graffiti_yellow_2x1", GRAFFITI_YELLOW_2X1)

        registerWithItem("poster_runes", POSTER_RUNES)
        registerWithItem("poster_golem", POSTER_GOLEM)
        registerWithItem("poster_face", POSTER_FACE)
        registerWithItem("poster_dark_runes", POSTER_DARK_RUNES)
        registerWithItem("poster_crossbow", POSTER_CROSSBOW)


        CUTOUT_LIST.addAll(
            listOf(
                GRAY_BARREL,
                WHITE_BARREL,
                BROWN_BARREL,
                EXPLOSIVE_BARREL,
                ITEM_CRATE,
                CHAIN_LINK_FENCE,
                COMPUTER,
                WOODEN_PALLET
            )
        )

        TRANSPARENT_LIST.addAll(
            listOf(
                GRAFFITI_BLACK_1X1,
                GRAFFITI_RED_1X1,
                GRAFFITI_ORANGE_1X1,
                GRAFFITI_YELLOW_2X1,
                POSTER_RUNES,
                POSTER_GOLEM,
                POSTER_FACE,
                POSTER_DARK_RUNES,
                POSTER_CROSSBOW
            )
        )
    }

    @Suppress("UnstableApiUsage")
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