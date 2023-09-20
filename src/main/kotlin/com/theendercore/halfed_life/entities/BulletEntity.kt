package com.theendercore.halfed_life.entities

//import com.theendercore.halfed_life.items.GunItem
//import com.theendercore.halfed_life.items.HLItems
//import net.minecraft.entity.EntityType
//import net.minecraft.entity.LivingEntity
//import net.minecraft.entity.projectile.thrown.ThrownItemEntity
//import net.minecraft.item.Item
//import net.minecraft.item.Items
//import net.minecraft.world.World


//class BulletEntity(entityType: EntityType<out ThrownItemEntity>, world: World) : ThrownItemEntity(entityType, world) {
//    val item: Item = Items.AIR
//    fun BulletEntity(world: World?, owner: LivingEntity?, item: GunItem?) {
//
//        super(YGEntityType.BULLET_ENTITY.get() as EntityType<out ThrownItemEntity?>, owner, world)
//        item = item
//    }
//
//
//    override fun initDataTracker() {
//    }
//
//
//
//    override fun getDefaultItem(): Item = HLItems.GUN
//}