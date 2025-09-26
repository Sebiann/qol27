package moe.sebiann.qol27.config

import moe.sebiann.qol27.utils.Resources
import dev.isxander.yacl3.api.OptionDescription
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler
import dev.isxander.yacl3.config.v2.api.SerialEntry
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder
import dev.isxander.yacl3.dsl.*
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component

class Config {
    @SerialEntry
    var silkTouchDetectionEnderchest: Boolean = true
    @SerialEntry
    var silkTouchDetectionGlass: Boolean = true
    @SerialEntry
    var silkTouchSneakOverrides: Boolean = true

    @SerialEntry
    var woodStrippingDetection: Boolean = true
    @SerialEntry
    var woodStrippingSneakOverrides: Boolean = true

    @SerialEntry
    var carpetsCantPlaceOnCarpets: Boolean = true
    @SerialEntry
    var carpetsSneakOverrides: Boolean = true

    object SilkTouch {
        val enderchest: Boolean
            get() = handler.instance().silkTouchDetectionEnderchest
        val glass: Boolean
            get() = handler.instance().silkTouchDetectionGlass
        val sneakOverrides: Boolean
            get() = handler.instance().silkTouchSneakOverrides
    }

    object WoodStripping {
        val enabled: Boolean
            get() = handler.instance().woodStrippingDetection
        val sneakOverrides: Boolean
            get() = handler.instance().woodStrippingSneakOverrides
    }

    object Carpets {
        val enabled: Boolean
            get() = handler.instance().carpetsCantPlaceOnCarpets
        val sneakOverrides: Boolean
            get() = handler.instance().carpetsSneakOverrides
    }

    companion object {
        val handler: ConfigClassHandler<Config> by lazy {
            ConfigClassHandler.createBuilder(Config::class.java).id(Resources.qol27("config")).serializer { config ->
                GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().configDir.resolve("qol27.json")).build()
            }.build()
        }

        fun init() {
            handler.load()
        }

        fun getScreen(parentScreen: Screen?): Screen = YetAnotherConfigLib("qol27") {
            title(Component.translatable("config.qol27.name"))
            save {
                handler.save()
            }

            categories.register("qol27") {
                name(Component.translatable("config.qol27.name"))

                groups.register("silk_touch") {
                    name(Component.translatable("config.qol27.silk_touch.name"))
                    description(OptionDescription.of(Component.translatable("config.qol27.silk_touch.description")))

                    options.register<Boolean>("silk_touch_detection_enderchest") {
                        name(Component.translatable("config.qol27.silk_touch.enderchest.name"))
                        description(OptionDescription.of(Component.translatable("config.qol27.silk_touch.enderchest.description")))
                        binding(handler.instance()::silkTouchDetectionEnderchest, true)
                        controller(tickBox())
                    }
                    options.register<Boolean>("silk_touch_detection_glass") {
                        name(Component.translatable("config.qol27.silk_touch.glass.name"))
                        description(OptionDescription.of(Component.translatable("config.qol27.silk_touch.glass.description")))
                        binding(handler.instance()::silkTouchDetectionGlass, true)
                        controller(tickBox())
                    }
                    options.register<Boolean>("silk_touch_sneak_overrides") {
                        name(Component.translatable("config.qol27.silk_touch.sneak_overrides.name"))
                        description(OptionDescription.of(Component.translatable("config.qol27.silk_touch.sneak_overrides.description")))
                        binding(handler.instance()::silkTouchSneakOverrides, true)
                        controller(tickBox())
                    }
                }

                groups.register("wood_stripping") {
                    name(Component.translatable("config.qol27.wood_stripping.name"))
                    description(OptionDescription.of(Component.translatable("config.qol27.wood_stripping.description")))

                    options.register<Boolean>("wood_stripping_detection_enabled") {
                        name(Component.translatable("config.qol27.wood_stripping.enabled.name"))
                        description(OptionDescription.of(Component.translatable("config.qol27.wood_stripping.enabled.description")))
                        binding(handler.instance()::woodStrippingDetection, true)
                        controller(tickBox())
                    }
                    options.register<Boolean>("wood_stripping_sneak_overrides") {
                        name(Component.translatable("config.qol27.wood_stripping.sneak_overrides.name"))
                        description(OptionDescription.of(Component.translatable("config.qol27.wood_stripping.sneak_overrides.description")))
                        binding(handler.instance()::woodStrippingSneakOverrides, true)
                        controller(tickBox())
                    }
                }

                groups.register("carpets") {
                    name(Component.translatable("config.qol27.carpets.name"))
                    description(OptionDescription.of(Component.translatable("config.qol27.carpets.description")))

                    options.register<Boolean>("carpets_enabled") {
                        name(Component.translatable("config.qol27.carpets.enabled.name"))
                        description(OptionDescription.of(Component.translatable("config.qol27.carpets.enabled.description")))
                        binding(handler.instance()::carpetsCantPlaceOnCarpets, true)
                        controller(tickBox())
                    }
                    options.register<Boolean>("carpets_sneak_overrides") {
                        name(Component.translatable("config.qol27.carpets.sneak_overrides.name"))
                        description(OptionDescription.of(Component.translatable("config.qol27.carpets.sneak_overrides.description")))
                        binding(handler.instance()::carpetsSneakOverrides, true)
                        controller(tickBox())
                    }
                }
            }
        }.generateScreen(parentScreen)
    }
}