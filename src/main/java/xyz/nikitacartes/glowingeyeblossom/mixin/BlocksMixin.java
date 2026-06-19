package xyz.nikitacartes.glowingeyeblossom.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Blocks;
import org.objectweb.asm.Opcodes;
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
                    target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;of()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;",
                    ordinal = 0),
            slice = @Slice(from = @At(value = "FIELD", opcode = Opcodes.GETSTATIC,
                    target = "Lnet/minecraft/references/BlockItemIds;OPEN_EYEBLOSSOM:Lnet/minecraft/references/BlockItemId;")))
    private static BlockBehaviour.Properties modifyEyeblossom(BlockBehaviour.Properties properties) {
        if (config == null) {
            config = MainConfigV1.load();
        }
        return properties.lightLevel(blockState -> config.openEyeblossomBrightness);
    }

    @ModifyExpressionValue(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;of()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;",
                    ordinal = 0),
            slice = @Slice(from = @At(value = "FIELD", opcode = Opcodes.GETSTATIC,
                    target = "Lnet/minecraft/references/BlockItemIds;CLOSED_EYEBLOSSOM:Lnet/minecraft/references/BlockItemId;")))
    private static BlockBehaviour.Properties modifyClosedEyeblossom(BlockBehaviour.Properties properties) {
        if (config == null) {
            config = MainConfigV1.load();
        }
        return properties.lightLevel(blockState -> config.closedEyeblossomBrightness);
    }

    @ModifyExpressionValue(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/Blocks;flowerPotProperties()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;",
                    ordinal = 0),
            slice = @Slice(from = @At(value = "FIELD", opcode = Opcodes.GETSTATIC,
                    target = "Lnet/minecraft/references/BlockIds;POTTED_OPEN_EYEBLOSSOM:Lnet/minecraft/resources/ResourceKey;")))
    private static BlockBehaviour.Properties modifyPottedEyeblossom(BlockBehaviour.Properties properties) {
        if (config == null) {
            config = MainConfigV1.load();
        }
        return properties.lightLevel(blockState -> config.openEyeblossomFlowerpotBrightness);
    }

    @ModifyExpressionValue(method = "<clinit>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/Blocks;flowerPotProperties()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;",
                    ordinal = 0),
            slice = @Slice(from = @At(value = "FIELD", opcode = Opcodes.GETSTATIC,
                    target = "Lnet/minecraft/references/BlockIds;POTTED_CLOSED_EYEBLOSSOM:Lnet/minecraft/resources/ResourceKey;")))
    private static BlockBehaviour.Properties modifyPottedClosedEyeblossom(BlockBehaviour.Properties properties) {
        if (config == null) {
            config = MainConfigV1.load();
        }
        return properties.lightLevel(blockState -> config.closedEyeblossomFlowerpotBrightness);
    }
}