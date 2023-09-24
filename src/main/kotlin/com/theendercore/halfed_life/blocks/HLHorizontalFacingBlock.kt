package com.theendercore.halfed_life.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.block.ShapeContext
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView


open class HLHorizontalFacingBlock(settings: Settings) : HorizontalFacingBlock(settings) {
    var NORTH_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    var SOUTH_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    var WEST_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    var EAST_SHAPE: VoxelShape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)



    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext,
    ): VoxelShape {
        return when (state.get(FACING)) {
            Direction.SOUTH -> SOUTH_SHAPE
            Direction.WEST -> WEST_SHAPE
            Direction.EAST -> EAST_SHAPE
            else -> NORTH_SHAPE
        }
    }


    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? =
        defaultState.with(FACING, ctx.playerFacing.opposite)
}