package com.theendercore.halfed_life.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty


open class WoodenPallet(settings: Settings) : HLHorizontalFacingBlock(settings) {
    private val shape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0)

    init {
        NORTH_SHAPE = shape
        SOUTH_SHAPE = shape
        WEST_SHAPE = shape
        EAST_SHAPE = shape
        defaultState = stateManager.defaultState.with(FLIPPED, false)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(FLIPPED)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        val state = super.getPlacementState(ctx)
        println(ctx.player?.entityName)
        return state.with(FLIPPED, (ctx.player?.isSneaking == true))
    }


    companion object {
        val FLIPPED = BooleanProperty.of("flipped")
    }


}