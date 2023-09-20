package com.theendercore.halfed_life.items

import com.theendercore.halfed_life.HalfedLife.id
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import java.util.LinkedList

object HLItems {
    val ITEMS = LinkedList<Item>()

    val GUN: Item = GunItem()

    fun init() {
        register("gun", GUN)

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(
            ItemGroupEvents.ModifyEntries { it.prepend(GUN) }
        )
    }


    private fun register(id: String, item: Item): Item {
        ITEMS.add(item)
        return Registry.register(Registries.ITEM, id(id), item)
    }
}