package com.theendercore.halfed_life.items

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission
import net.minecraft.item.ArrowItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World


class GunItem(settings: FabricItemSettings, val reloadSpeed: Int = 3) : Item(settings) {
    constructor(reloadSpeed: Int) : this(FabricItemSettings(), reloadSpeed)
    constructor() : this(FabricItemSettings(), 3)

    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (world.isClient) {
            return TypedActionResult.pass(player.getStackInHand(hand))
        }

        if (!world.isClient) {
            val stack = Items.ARROW.defaultStack
            val persistentProjectileEntity = (Items.ARROW as ArrowItem).createArrow(world, stack, player)
            persistentProjectileEntity.setProperties(
                player, player.pitch, player.yaw, 0.0f, 3.0f, 1.0f
            )

            /*val j = EnchantmentHelper.getLevel(Enchantments.POWER, stack)
                if (j > 0) {
                    persistentProjectileEntity.damage = persistentProjectileEntity.damage + j.toDouble() * 0.5 + 0.5
                }*/

            persistentProjectileEntity.pickupType = PickupPermission.DISALLOWED
            player.itemCooldownManager.set(this, reloadSpeed)

            world.spawnEntity(persistentProjectileEntity)

        }

        return TypedActionResult.pass(player.getStackInHand(hand))
    }
}