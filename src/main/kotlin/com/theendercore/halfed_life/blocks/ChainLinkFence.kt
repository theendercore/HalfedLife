package com.theendercore.halfed_life.blocks

import org.teamvoided.voidlib.core.rotate

class ChainLinkFence(settings: Settings) : HLHorizontalFacingBlock(settings) {
    val shape = createCuboidShape(0.0, 0.0, 15.0, 16.0, 16.0, 16.0)
   init {
       NORTH_SHAPE = shape
       EAST_SHAPE = shape.rotate(1)
       SOUTH_SHAPE = shape.rotate(2)
       WEST_SHAPE = shape.rotate(3)
   }

}