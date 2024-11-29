package xyz.nikitacartes.glowingeyeblossom.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;
import xyz.nikitacartes.glowingeyeblossom.config.MainConfigV1;

@Mixin(Blocks.class)
public class BlocksMixin {

    @Unique
    private static MainConfigV1 config;

    @ModifyExpressionValue(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/AbstractBlock$Settings;create()Lnet/minecraft/block/AbstractBlock$Settings;",
                    ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=open_eyeblossom")))
    private static AbstractBlock.Settings modifyEyeblossom(AbstractBlock.Settings properties) {
        if (config == null) {
            config = MainConfigV1.load();
        }
        return properties.luminance(blockState -> config.openEyeblossomBrightness);
    }

    @ModifyExpressionValue(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/AbstractBlock$Settings;create()Lnet/minecraft/block/AbstractBlock$Settings;",
                    ordinal = 0),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=closed_eyeblossom")))
    private static AbstractBlock.Settings modifyClosedEyeblossom(AbstractBlock.Settings properties) {
        if (config == null) {
            config = MainConfigV1.load();
        }
        return properties.luminance(blockState -> config.closedEyeblossomBrightness);
    }

    @ModifyExpressionValue(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Blocks;createFlowerPotSettings()Lnet/minecraft/block/AbstractBlock$Settings;",
                    ordinal = 36))
    private static AbstractBlock.Settings modifyPottedEyeblossom(AbstractBlock.Settings properties) {
        if (config == null) {
            config = MainConfigV1.load();
        }
        return properties.luminance(blockState -> config.openEyeblossomFlowerpotBrightness);
    }

    @ModifyExpressionValue(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Blocks;createFlowerPotSettings()Lnet/minecraft/block/AbstractBlock$Settings;",
                    ordinal = 37))
    private static AbstractBlock.Settings modifyPottedClosedEyeblossom(AbstractBlock.Settings properties) {
        if (config == null) {
            config = MainConfigV1.load();
        }
        return properties.luminance(blockState -> config.closedEyeblossomFlowerpotBrightness);
    }
}