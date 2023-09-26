package com.theendercore.halfed_life.data.providers

import com.theendercore.halfed_life.HalfedLife.id
import com.theendercore.halfed_life.blocks.HLBlocks
import com.theendercore.halfed_life.blocks.WoodenPallet
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.data.client.model.BlockStateModelGenerator.*
import net.minecraft.util.Identifier
import java.util.*

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {

    private val fullCubes = listOf(
        HLBlocks.OAK_CRATE,
        HLBlocks.SPRUCE_CRATE,
        HLBlocks.PILLARIUM,
        HLBlocks.CUT_PILLARIUM
    )

    private val slabs = mapOf(
        Pair(HLBlocks.PILLARIUM, HLBlocks.PILLARIUM_SLAB),
        Pair(HLBlocks.CUT_PILLARIUM, HLBlocks.CUT_PILLARIUM_SLAB)
    )

    private val list1x1 = listOf(
        HLBlocks.GRAFFITI_BLACK_TEXT,
        HLBlocks.GRAFFITI_ORANGE_SYMBOL,
        HLBlocks.GRAFFITI_RED_TEXT,
        HLBlocks.CHAIN_LINK_FENCE,
    )

    private val list2x2 = listOf(
        HLBlocks.POSTER_RUNES,
        HLBlocks.POSTER_GOLEM,
        HLBlocks.POSTER_FACE,
        HLBlocks.POSTER_DARK_RUNES,
        HLBlocks.POSTER_CROSSBOW,
        HLBlocks.GRAFFITI_YELLOW,
        HLBlocks.GRAFFITI_RED_X,
        HLBlocks.GRAFFITI_GREEN_RED,
        HLBlocks.GRAFFITI_HEAD
    )

    private val list3x3 = listOf(
        HLBlocks.GRAFFITI_BLUE_RED
    )

    private val hzBlock = listOf(
        HLBlocks.COMPUTER,
        HLBlocks.ITEM_CRATE,
    )
//    private val barrels = listOf(
//        HLBlocks.BROWN_BARREL,
//        HLBlocks.WHITE_BARREL,
//        HLBlocks.GRAY_BARREL,
//        HLBlocks.EXPLOSIVE_BARREL
//    )


    private val wallBlock1x1: Identifier = id("block/template/wallblock_1x1")
    private val wallBlock2x2: Identifier = id("block/template/wallblock_2x2")
    private val wallBlock3x3: Identifier = id("block/template/wallblock_3x3")
//    private val barrel: Identifier = id("block/template/barrel")
    private val IMG: TextureKey = TextureKey.of("img")
    val BASE = TextureKey.of("base")

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {

        fullCubes.forEach { gen.registerSimpleCubeAll(it) }

        list1x1.forEach { gen.wallBlock(it, wallBlock1x1) }
        list2x2.forEach { gen.wallBlock(it, wallBlock2x2) }
        list3x3.forEach { gen.wallBlock(it, wallBlock3x3) }

        hzBlock.forEach { gen.horizontalFacingND(it) }
//        barrels.forEach {
//            gen.parentedModel(it, barrel)
//        }

        slabs.forEach { gen.registerCustomSlab(it.key, it.value) }

        gen.woodenPallet()

        gen.blockStateCollector.accept(
            createFenceBlockState(HLBlocks.BARRIER, id("barrier_post").block, id("barrier_side").block)
        )

        gen.registerDoor(HLBlocks.METAL_DOOR)
        gen.registerDoor(HLBlocks.WOODEN_DOOR)
    }

    override fun generateItemModels(gen: ItemModelGenerator) {}


    private fun BlockStateModelGenerator.parented(parent: Identifier, block: Block): Identifier =
        Model(parent.myb, Optional.empty()).upload(
            ModelIds.getItemModelId(block.asItem()), Texture(), this.modelCollector
        )

    private fun BlockStateModelGenerator.wallBlock(block: Block, parent: Identifier) {
        this.wallBlocModel(block, parent)
        this.horizontalFacing(block)
    }

    private fun BlockStateModelGenerator.wallBlocModel(block: Block, parent: Identifier): Identifier =
        Model(parent.myb, Optional.empty(), IMG)
            .upload(block.modelId(), Texture().put(IMG, block.modelId()), this.modelCollector)

    private fun BlockStateModelGenerator.parentedModel(block: Block, parent: Identifier): Identifier =
        Model(parent.myb, Optional.empty(), BASE)
            .upload(block.modelId(), Texture().put(BASE, block.modelId()), this.modelCollector)

    private fun BlockStateModelGenerator.horizontalFacing(block: Block) {
        this.blockStateCollector.accept(
            VariantsBlockStateSupplier
                .create(block, BlockStateVariant.create().put(VariantSettings.MODEL, block.modelId()))
                .coordinate(createSouthDefaultHorizontalRotationStates())
        )
    }
    private fun BlockStateModelGenerator.horizontalFacingND(block: Block) {
        this.blockStateCollector.accept(
            VariantsBlockStateSupplier
                .create(block, BlockStateVariant.create().put(VariantSettings.MODEL, block.modelId()))
                .coordinate(createNorthDefaultHorizontalRotationStates())
        )
    }

    private fun BlockStateModelGenerator.woodenPallet() {
        this.blockStateCollector.accept(
            VariantsBlockStateSupplier.create(
                HLBlocks.WOODEN_PALLET,
                BlockStateVariant.create().put(VariantSettings.MODEL, HLBlocks.WOODEN_PALLET.modelId())
            ).coordinate(createNorthDefaultHorizontalRotationStates())
                .coordinate(
                    BlockStateVariantMap.create(WoodenPallet.FLIPPED).register(
                        false, BlockStateVariant.create().put(
                            VariantSettings.MODEL, HLBlocks.WOODEN_PALLET.modelId()
                        )
                    ).register(
                        true, BlockStateVariant.create().put(
                            VariantSettings.MODEL, HLBlocks.WOODEN_PALLET.modelSuffixed("_flipped")
                        )
                    )
                )
        )
    }

    private fun BlockStateModelGenerator.registerSlab(block: Block, slab: Block) {
        val fullCube = TexturedModel.CUBE_ALL[block]
        val bottom = Models.SLAB.upload(slab, fullCube.texture, modelCollector)
        val top = Models.SLAB_TOP.upload(slab, fullCube.texture, modelCollector)
        this.blockStateCollector.accept(createSlabBlockState(slab, bottom, top, block.modelId()))
    }


    private fun BlockStateModelGenerator.registerCustomSlab(block: Block, slab: Block) {
        val tex = Texture()
            .put(TextureKey.TOP, block.modelId())
            .put(TextureKey.BOTTOM, block.modelId())
            .put(TextureKey.SIDE, block.modelSuffixed("_side"))
        val bottom = Models.SLAB.upload(slab, tex, modelCollector)
        val top = Models.SLAB_TOP.upload(slab, tex, modelCollector)
        val mod = Models.CUBE_BOTTOM_TOP.upload(slab.modelSuffixed("_double"), tex, modelCollector)
        this.blockStateCollector.accept(createSlabBlockState(slab, bottom, top, mod))
    }

    private val <T : Any?> T.myb get() = Optional.ofNullable(this)
    private fun Block.modelSuffixed(str: String): Identifier = ModelIds.getBlockSubModelId(this, str)
    private fun Block.modelId(): Identifier = ModelIds.getBlockModelId(this)
    private val Identifier.block get() = this.withPrefix("block/")


}