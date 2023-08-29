package com.theendercore.halfed_life

import com.theendercore.halfed_life.items.HLItems
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

@Suppress("unused")
object HalfedLife {
    const val MODID = "halfed_life"

    @JvmField
    val LOGGER = LoggerFactory.getLogger(HalfedLife::class.java)


    fun commonInit() {
        LOGGER.info("HalfedLife Common")
        HLItems.init()
    }

    fun clientInit() {
        LOGGER.info("HalfedLife Client")
    }

    fun id(path: String) = Identifier(MODID, path)
    fun mc(path: String) = Identifier(path)
}