package com.theendercore.halfed_life.blocks.wallblock

import com.theendercore.halfed_life.blocks.HLHorizontalFacingBlock
import org.teamvoided.voidlib.core.rotateVoxelShape

open class BaseWallBlock(settings: Settings) : HLHorizontalFacingBlock(settings) {
    val shape = createCuboidShape(0.0, 0.0, 15.0, 16.0, 16.0, 16.0)
   init {
       NORTH_SHAPE = shape
       EAST_SHAPE = rotateVoxelShape(1, shape)
       SOUTH_SHAPE = rotateVoxelShape(2, shape)
       WEST_SHAPE = rotateVoxelShape(3, shape)
   }

}