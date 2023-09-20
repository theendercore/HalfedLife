package com.theendercore.halfed_life.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView

open class Computer(settings: Settings) : Block(settings) {
    private val shape = VoxelShapes.union(
        createCuboidShape(0.0, 0.0, 1.0, 12.0, 3.0, 15.0),
        createCuboidShape(5.0, 0.0, 1.0, 12.0, 11.0, 15.0),
        createCuboidShape(12.0, 0.0, 3.0, 16.0, 8.0, 13.0)

    )

    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext,
    ): VoxelShape = shape


}