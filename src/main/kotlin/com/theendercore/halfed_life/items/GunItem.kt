package com.theendercore.halfed_life.items

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.EntityType
import net.minecraft.entity.LightningEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World


class GunItem(settings: FabricItemSettings) : Item(settings) {

    constructor(): this(FabricItemSettings())

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand))
        }

        val frontOfPlayer = user.blockPos.offset(user.horizontalFacing, 10)

        // Spawn the lightning bolt.
        val lightningBolt = LightningEntity(EntityType.LIGHTNING_BOLT, world)
        lightningBolt.setPosition(frontOfPlayer.ofCenter())
        world.spawnEntity(lightningBolt)

        // Nothing has changed to the item stack,
        // so we just return it how it was.
        return TypedActionResult.success(user.getStackInHand(hand))
    }
}