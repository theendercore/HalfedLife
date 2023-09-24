package com.theendercore.halfed_life.blocks.wallblock

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.enums.DoubleBlockHalf
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.state.StateManager
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import org.jetbrains.annotations.Nullable

class WallBlock1x2(settings: Settings) : BaseWallBlock(settings) {
    init {
        defaultState = stateManager.defaultState.with(HALF, DoubleBlockHalf.UPPER)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        super.appendProperties(builder)
        builder.add(HALF)
    }

    @Nullable
    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val blockPos = ctx.blockPos
        val world = ctx.world
        return if (blockPos.y > world.bottomY + 1 && world.getBlockState(blockPos.down()).canReplace(ctx))
            defaultState.with(FACING, ctx.playerFacing.opposite).with(HALF, DoubleBlockHalf.UPPER)
        else null
    }

    @Deprecated("Deprecated in Java")
    override fun getStateForNeighborUpdate(
        state: BlockState, direction: Direction, neighborState: BlockState,
        world: WorldAccess, pos: BlockPos, neighborPos: BlockPos,
    ): BlockState? {
        return if (!neighborState.isOf(this) && (direction == Direction.UP && state.get(HALF) != DoubleBlockHalf.UPPER))
            Blocks.AIR.defaultState
        else state
    }

    override fun onPlaced(
        world: World, pos: BlockPos, state: BlockState, placer: LivingEntity?, itemStack: ItemStack,
    ) {
        world.setBlockState(pos.down(), state.with(HALF, DoubleBlockHalf.LOWER), 3)
    }

    @Deprecated("Deprecated in Java")
    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        return if (state.get(HALF) == DoubleBlockHalf.LOWER) world.getBlockState(pos.up()).isOf(this)
        else true
    }

    override fun onBreak(world: World, pos: BlockPos?, state: BlockState?, player: PlayerEntity) {
        if (!world.isClient && player.isCreative && pos != null && state != null)
            onBreakInCreative(world, pos, state, player)
        super.onBreak(world, pos, state, player)
    }


    companion object {
        val HALF: EnumProperty<DoubleBlockHalf> = Properties.DOUBLE_BLOCK_HALF
        fun onBreakInCreative(world: World, posIn: BlockPos, stateIn: BlockState, player: PlayerEntity?) {
            if (stateIn.get(HALF) == DoubleBlockHalf.LOWER) {
                val pos = posIn.up()
                val state = world.getBlockState(pos)
                if (state.isOf(stateIn.block) && state.get(HALF) == DoubleBlockHalf.UPPER) {
                    val state2 =
                        if (state.fluidState.isOf(Fluids.WATER)) Blocks.WATER.defaultState else Blocks.AIR.defaultState
                    world.setBlockState(pos, state2, 35)
                    world.syncWorldEvent(player, 2001, pos, getRawIdFromState(state))
                }
            }
        }

    }
}