package moe.sebiann.qol27.utils

import net.minecraft.resources.ResourceLocation

object Resources {
    fun qol27(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath("qol27", path)
    fun minecraft(path: String): ResourceLocation = ResourceLocation.withDefaultNamespace(path)
}