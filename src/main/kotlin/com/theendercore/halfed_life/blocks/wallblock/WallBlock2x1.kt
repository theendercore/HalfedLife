package com.theendercore.halfed_life.blocks.wallblock

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty

class WallBlock2x1(settings: Settings) : BaseWallBlock(settings) {


    init {
        defaultState = stateManager.defaultState.with(RIGHT, false)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(RIGHT)
    }

    companion object {
        val RIGHT: BooleanProperty = BooleanProperty.of("right")
    }
}