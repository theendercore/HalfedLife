package com.theendercore.halfed_life.data.providers

import com.theendercore.halfed_life.HalfedLife.id
import com.theendercore.halfed_life.blocks.HLBlocks
import com.theendercore.halfed_life.blocks.WoodenPallet
import com.theendercore.halfed_life.blocks.wallblock.WallBlock2x1
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.block.Block
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.*
import net.minecraft.data.client.model.BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates
import net.minecraft.data.client.model.BlockStateModelGenerator.createSouthDefaultHorizontalRotationStates
import net.minecraft.state.property.Properties
import net.minecraft.util.Identifier
import java.util.*

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {

    private val fullCubeList = listOf(
        HLBlocks.OAK_CRATE, HLBlocks.SPRUCE_CRATE, HLBlocks.PILLARIUM, HLBlocks.CUT_PILLARIUM
    )

    private val wallBlock1x2 = listOf(
        HLBlocks.POSTER_RUNES,
        HLBlocks.POSTER_GOLEM,
        HLBlocks.POSTER_FACE,
        HLBlocks.POSTER_DARK_RUNES,
        HLBlocks.POSTER_CROSSBOW
    )

    private val WALL_BLOCK: Identifier = id("block/wall_block_1x1")

    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
        try {
            fullCubeList.forEach { gen.registerSimpleCubeAll(it) }


            gen.parented(HLBlocks.GRAFFITI_YELLOW_2X1.modelSuffixed("_left"), HLBlocks.GRAFFITI_YELLOW_2X1)
            gen.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(HLBlocks.GRAFFITI_YELLOW_2X1)
                    .coordinate(createSouthDefaultHorizontalRotationStates()).coordinate(
                        BlockStateVariantMap.create(WallBlock2x1.RIGHT).register(
                            false, BlockStateVariant.create().put(
                                VariantSettings.MODEL, gen.wallBlock1x1Model(HLBlocks.GRAFFITI_YELLOW_2X1, "_left")
                            )
                        ).register(
                            true, BlockStateVariant.create().put(
                                VariantSettings.MODEL, gen.wallBlock1x1Model(HLBlocks.GRAFFITI_YELLOW_2X1, "_right")
                            )
                        )
                    )
            )

            wallBlock1x2.forEach { gen.wallBlock1x2(it) }


            gen.blockStateCollector.accept(
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

        } catch (e: Exception) {
            LOGGER.error("Error {}", e.toString())
        }
    }

    override fun generateItemModels(gen: ItemModelGenerator) {}


    private fun BlockStateModelGenerator.parented(parent: Identifier, block: Block): Identifier =
        Model(parent.myb, Optional.empty()).upload(
            ModelIds.getItemModelId(block.asItem()),
            Texture(),
            this.modelCollector
        )

    private fun BlockStateModelGenerator.wallBlock1x1Model(block: Block, suffix: String): Identifier =
        this.wallBlock1x1Model(block, suffix, true)

    private fun BlockStateModelGenerator.wallBlock1x1Model(block: Block, suffix: String, x: Boolean): Identifier {
        val ketText = TextureKey.of("tex")
        return Model(WALL_BLOCK.myb, Optional.empty(), ketText).upload(
            block.modelSuffixed(if (x) suffix else ""),
            Texture().put(ketText, block.modelSuffixed(suffix)),
            this.modelCollector
        )
    }

    private fun BlockStateModelGenerator.wallBlock1x2(block: Block) {
        this.parented(block.modelSuffixed("_top"), block)
        this.blockStateCollector.accept(
            VariantsBlockStateSupplier.create(block)
                .coordinate(createSouthDefaultHorizontalRotationStates())
                .coordinate(
                    BlockStateVariantMap.create(Properties.DOUBLE_BLOCK_HALF).register(
                        DoubleBlockHalf.UPPER, BlockStateVariant.create().put(
                            VariantSettings.MODEL, this.wallBlock1x1Model(block, "_top")
                        )
                    ).register(
                        DoubleBlockHalf.LOWER, BlockStateVariant.create().put(
                            VariantSettings.MODEL, this.wallBlock1x1Model(block, "_bottom")
                        )
                    )
                )
        )
    }

    private val <T: Any?> T.myb get() = Optional.ofNullable(this)
    private fun Block.modelSuffixed(str: String): Identifier = ModelIds.getBlockSubModelId(this, str)
    private fun Block.modelId(): Identifier = ModelIds.getBlockModelId(this)


}