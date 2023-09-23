package com.theendercore.halfed_life.blocks


open class ItemCreate(settings: Settings) : HLHorizontalFacingBlock(settings) {
    private val shape = createCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0)
   init {
       NORTH_SHAPE = shape
       SOUTH_SHAPE = shape
       WEST_SHAPE = shape
       EAST_SHAPE = shape
   }


}