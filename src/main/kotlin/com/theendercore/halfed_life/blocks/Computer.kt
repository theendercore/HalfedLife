package com.theendercore.halfed_life.blocks


import net.minecraft.util.shape.VoxelShapes
import org.teamvoided.voidlib.core.rotateVoxelShape


open class Computer(settings: Settings) : HLHorizontalFacingBlock(settings) {

     val shape = VoxelShapes.union(
         createCuboidShape(11.0, 0.0, 1.0, 16.0, 3.0, 15.0),
         createCuboidShape(4.0, 0.0, 1.0, 11.0, 11.0, 15.0),
         createCuboidShape(0.0, 0.0, 3.0, 4.0, 8.0, 13.0)
     )

    init {
        EAST_SHAPE =  shape
        SOUTH_SHAPE = rotateVoxelShape(1, shape )
        WEST_SHAPE = rotateVoxelShape(2, shape )
        NORTH_SHAPE = rotateVoxelShape(3, shape )
    }


}