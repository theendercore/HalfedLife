package com.theendercore.halfed_life.blocks

import com.theendercore.halfed_life.HalfedLife.id
import com.theendercore.halfed_life.blocks.wallblock.BaseWallBlock
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
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
    val CUT_PILLARIUM: Block = Block(FabricBlockSettings.copy(PILLARIUM))

    val PILLARIUM_SLAB: Block = SlabBlock(FabricBlockSettings.copy(PILLARIUM))
    val CUT_PILLARIUM_SLAB: Block = SlabBlock(FabricBlockSettings.copy(CUT_PILLARIUM))

    val ITEM_CRATE: Block = ItemCreate(FabricBlockSettings.copy(Blocks.OAK_PLANKS))
    val WOODEN_PALLET = WoodenPallet(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR))

    val GRAY_BARREL: Block = HLBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))
    val WHITE_BARREL: Block = HLBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))
    val BROWN_BARREL: Block = HLBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))
    val EXPLOSIVE_BARREL: Block = ExplosiveBarrel(FabricBlockSettings.copy(Blocks.CAULDRON))

    val COMPUTER: Block = Computer(FabricBlockSettings.copy(Blocks.WHITE_CONCRETE))

    val CHAIN_LINK_FENCE: Block = ChainLinkFence(FabricBlockSettings.copy(Blocks.IRON_BARS))

    val GRAFFITI_BLACK_TEXT: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val GRAFFITI_RED_TEXT: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val GRAFFITI_ORANGE_SYMBOL: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))

    val GRAFFITI_YELLOW: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val GRAFFITI_RED_X: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))

    val GRAFFITI_HEAD: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val GRAFFITI_GREEN_RED: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))

    val GRAFFITI_BLUE_RED: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))

    val POSTER_RUNES: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val POSTER_GOLEM: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val POSTER_FACE: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val POSTER_DARK_RUNES: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))
    val POSTER_CROSSBOW: Block = BaseWallBlock(FabricBlockSettings.copy(Blocks.WHITE_STAINED_GLASS_PANE))

    val BARRIER: Block = HLBarrier(FabricBlockSettings.copy(Blocks.WHITE_CONCRETE))

    val METAL_DOOR: Block = DoorBlock(FabricBlockSettings.copy(Blocks.IRON_DOOR), BlockSetType.IRON)
    val WOODEN_DOOR: Block = DoorBlock(FabricBlockSettings.copy(Blocks.OAK_DOOR), BlockSetType.OAK)

    fun init() {
        registerWithItem("oak_crate", OAK_CRATE)
        registerWithItem("spruce_crate", SPRUCE_CRATE)
        registerWithItem("pillarium", PILLARIUM)
        registerWithItem("cut_pillarium", CUT_PILLARIUM)

        registerWithItem("pillarium_slab", PILLARIUM_SLAB)
        registerWithItem("cut_pillarium_slab", CUT_PILLARIUM_SLAB)

        registerWithItem("item_crate", ITEM_CRATE)
        registerWithItem("wooden_pallet", WOODEN_PALLET)

        registerWithItem("gray_barrel", GRAY_BARREL)
        registerWithItem("white_barrel", WHITE_BARREL)
        registerWithItem("brown_barrel", BROWN_BARREL)
        registerWithItem("explosive_barrel", EXPLOSIVE_BARREL)

        registerWithItem("computer", COMPUTER)

        registerWithItem("chain_link_fence", CHAIN_LINK_FENCE)

        registerWithItem("graffiti_black_text", GRAFFITI_BLACK_TEXT)
        registerWithItem("graffiti_red_text", GRAFFITI_RED_TEXT)
        registerWithItem("graffiti_orange_symbol", GRAFFITI_ORANGE_SYMBOL)

        registerWithItem("graffiti_yellow", GRAFFITI_YELLOW)
        registerWithItem("graffiti_red_x", GRAFFITI_RED_X)

        registerWithItem("graffiti_head", GRAFFITI_HEAD)
        registerWithItem("graffiti_green_red", GRAFFITI_GREEN_RED)

        registerWithItem("graffiti_blue_red", GRAFFITI_BLUE_RED)

        registerWithItem("poster_runes", POSTER_RUNES)
        registerWithItem("poster_golem", POSTER_GOLEM)
        registerWithItem("poster_face", POSTER_FACE)
        registerWithItem("poster_dark_runes", POSTER_DARK_RUNES)
        registerWithItem("poster_crossbow", POSTER_CROSSBOW)

        registerWithItem("barrier", BARRIER)

        registerWithItem("metal_door", METAL_DOOR)
        registerWithItem("wooden_door", WOODEN_DOOR)


        CUTOUT_LIST.addAll(
            listOf(
                GRAY_BARREL,
                WHITE_BARREL,
                BROWN_BARREL,
                EXPLOSIVE_BARREL,
                ITEM_CRATE,
                CHAIN_LINK_FENCE,
                COMPUTER,
                WOODEN_PALLET,
                POSTER_RUNES,
                POSTER_GOLEM,
                POSTER_FACE,
                POSTER_DARK_RUNES,
                POSTER_CROSSBOW,
            )
        )

        TRANSPARENT_LIST.addAll(
            listOf(
                GRAFFITI_BLACK_TEXT,
                GRAFFITI_RED_TEXT,
                GRAFFITI_ORANGE_SYMBOL,
                GRAFFITI_YELLOW,
                GRAFFITI_RED_X,
                GRAFFITI_HEAD,
                GRAFFITI_GREEN_RED,
                GRAFFITI_BLUE_RED,
                BARRIER,
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