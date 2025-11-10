package moe.sebiann.qol27.mixin;

import com.mojang.serialization.Codec;
import moe.sebiann.qol27.accessor.GammaAccessor;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public abstract class OptionsMixin implements GammaAccessor {
    @Mutable
    @Shadow
    @Final
    private OptionInstance<Double> gamma;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void modifyGamma(CallbackInfo ci) {
        this.gamma = createGammaOption(true);
    }

    @Override
    public void qol27$updateGammaRange(boolean extended) {
        double currentValue = this.gamma.get();
        this.gamma = createGammaOption(extended);

        if (!extended && currentValue > 1.0) {
            this.gamma.set(1.0);
        } else {
            this.gamma.set(currentValue);
        }
    }

    private static OptionInstance<Double> createGammaOption(boolean extended) {
        int maxValue = extended ? 10000 : 100;

        return new OptionInstance<>(
                "options.gamma",
                OptionInstance.noTooltip(),
                (text, value) -> {
                    double doubleValue = (Double) value;
                    if (doubleValue == 0.0) {
                        return Options.genericValueLabel(text, Component.translatable("options.gamma.min"));
                    } else if (doubleValue == 1.0) {
                        return Options.genericValueLabel(text, Component.translatable("options.gamma.max"));
                    } else {
                        int percentage = (int)(doubleValue * 100.0);
                        return Options.genericValueLabel(text, Component.literal(percentage + "%"));
                    }
                },
                new OptionInstance.IntRange(0, maxValue).xmap(
                        i -> i / 100.0,
                        d -> (int)(d * 100.0)
                ),
                Codec.doubleRange(0.0, extended ? 100.0 : 1.0),
                1.0,
                (value) -> {}
        );
    }
}