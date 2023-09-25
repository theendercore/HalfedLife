package com.theendercore.halfed_life.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.FenceBlock
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import org.teamvoided.voidlib.core.datastructures.vector.Vec3d
import org.teamvoided.voidlib.core.datastructures.vector.Vec3f

open class HLBarrier(settings: Settings) : FenceBlock(settings) {

    override fun canConnect(state: BlockState, neighborIsFullSquare: Boolean, dir: Direction?): Boolean {
        return if (state.isOf(HLBlocks.BARRIER)) true else super.canConnect(state, neighborIsFullSquare, dir)
    }

    override fun createShapes(
        radius1: Float,
        radius2: Float,
        height1: Float,
        offset2: Float,
        height2: Float,
    ): Array<VoxelShape>? {

        val post = createCuboidShape(vec3(), vec3())
        val f = 8.0f - radius1
        val g = 8.0f + radius1
        val h = 8.0f - radius2
        val i = 8.0f + radius2
        val voxelShape = createCuboidShape(Vec3f(f, 0.0f, f), Vec3f(g, height1, g))
        val voxelShape2 =
            createCuboidShape(h.toDouble(), offset2.toDouble(), 0.0, i.toDouble(), height2.toDouble(), i.toDouble())
//            createCuboidShape(Vec3f(h, offset2, 0f), Vec3f(i, height2, i))
        val voxelShape3 =
            createCuboidShape(h.toDouble(), offset2.toDouble(), h.toDouble(), i.toDouble(), height2.toDouble(), 16.0)
        val voxelShape4 =
            createCuboidShape(0.0, offset2.toDouble(), h.toDouble(), i.toDouble(), height2.toDouble(), i.toDouble())
        val voxelShape5 =
            createCuboidShape(h.toDouble(), offset2.toDouble(), h.toDouble(), 16.0, height2.toDouble(), i.toDouble())
        val voxelShape6 = VoxelShapes.union(voxelShape2, voxelShape5)
        val voxelShape7 = VoxelShapes.union(voxelShape3, voxelShape4)
        val voxelShapes = arrayOf(
            VoxelShapes.empty(),
            voxelShape3,
            voxelShape4,
            voxelShape7,
            voxelShape2,
            VoxelShapes.union(voxelShape3, voxelShape2),
            VoxelShapes.union(voxelShape4, voxelShape2),
            VoxelShapes.union(voxelShape7, voxelShape2),
            voxelShape5,
            VoxelShapes.union(voxelShape3, voxelShape5),
            VoxelShapes.union(voxelShape4, voxelShape5),
            VoxelShapes.union(voxelShape7, voxelShape5),
            voxelShape6,
            VoxelShapes.union(voxelShape3, voxelShape6),
            VoxelShapes.union(voxelShape4, voxelShape6),
            VoxelShapes.union(voxelShape7, voxelShape6)
        )

        for (j in 0..15) voxelShapes[j] = VoxelShapes.union(voxelShape, voxelShapes[j])

        return voxelShapes
    }

    fun createCuboidShape(pos1: Vec3d, pos2: Vec3d): VoxelShape =
        Block.createCuboidShape(pos1.x, pos1.y, pos1.z, pos2.z, pos2.y, pos2.z)

    fun createCuboidShape(pos1: Vec3f, pos2: Vec3f): VoxelShape = createCuboidShape(pos1.to3d(), pos1.to3d())

    fun vec3() = Vec3f(0f,0f,0f)

}