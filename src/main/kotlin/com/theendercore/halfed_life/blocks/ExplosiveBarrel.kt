package com.theendercore.halfed_life.blocks

import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.GameRules
import net.minecraft.world.World
import net.minecraft.world.World.ExplosionSourceType
import net.minecraft.world.explosion.Explosion

class ExplosiveBarrel(settings: Settings) : HLBarrel(settings) {


    override fun onBreak(world: World, pos: BlockPos, state: BlockState, player: PlayerEntity) {
        if (!world.isClient() && !player.isCreative) explode(world, pos)
        super.onBreak(world, pos, state, player)
    }

    @Deprecated("Deprecated in Java")
    override fun onProjectileHit(world: World, state: BlockState, hit: BlockHitResult, projectile: ProjectileEntity) {
        if (!world.isClient) {
            val entity = projectile.owner
            if (projectile.canModifyAt(world, hit.blockPos)) {
                explode(world, hit.blockPos, if (entity is LivingEntity) entity else null)
                projectile.kill()
            }
        }
    }

    override fun onDestroyedByExplosion(world: World, pos: BlockPos, explosion: Explosion) {
        if (!world.isClient) explode(world, pos)
    }


    private fun explode(world: World, pos: BlockPos) = explode(world, pos, null)
    private fun explode(world: World, pos: BlockPos, entity: LivingEntity?) {
        world.removeBlock(pos, false)
        world.createExplosion(
            entity,
            pos.x.toDouble(),
            pos.y.toDouble(),
            pos.z.toDouble(),
            4.0f,
            ExplosionSourceType.MOB
        )
        if (!world.gameRules.getBoolean(GameRules.DO_MOB_GRIEFING)) {
            for (i in -5..5) {
                for (j in -5..5) {
                    for (k in -5..5) {
                        val newPos = BlockPos(pos.x + i, pos.y + j, pos.z + k)
                        val block = (world.getBlockState(newPos)).block
                        if (block is ExplosiveBarrel) block.explode(world, newPos)
                    }
                }
            }
        }
    }


}