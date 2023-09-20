package com.theendercore.halfed_life.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

open class ItemCreate(settings: Settings) : Block(settings) {
    private val shape = createCuboidShape(1.0, 0.0, 1.0, 15.0, 8.0, 15.0)
    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext,
        ): VoxelShape = shape


}