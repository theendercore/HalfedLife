package com.theendercore.halfed_life.data.providers

import com.theendercore.halfed_life.HalfedLife.getId
import com.theendercore.halfed_life.items.HLItems.ITEMS
import com.theendercore.halfed_life.blocks.HLBlocks.BLOCKS
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.util.Identifier
import org.apache.commons.lang3.text.WordUtils

class EnglishTranslationProvider(o: FabricDataOutput) : FabricLanguageProvider(o) {
    override fun generateTranslations(build: TranslationBuilder) {
        ITEMS.forEach { build.add(it.translationKey, genLang(getId(it))) }
        BLOCKS.forEach { build.add(it.translationKey, genLang(getId(it))) }
    }


    private fun genLang(identifier: Identifier): String =
        WordUtils.capitalize(identifier.path.replace("_", " "))
}