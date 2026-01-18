package moe.sebiann.qol27.utils

import net.minecraft.resources.Identifier

object Resources {
    fun qol27(path: String): Identifier = Identifier.fromNamespaceAndPath("qol27", path)
    fun minecraft(path: String): Identifier = Identifier.withDefaultNamespace(path)
}