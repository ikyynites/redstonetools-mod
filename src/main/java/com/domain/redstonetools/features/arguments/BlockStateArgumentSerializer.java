package com.domain.redstonetools.features.arguments;

import net.minecraft.command.argument.BlockStateArgument;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.util.registry.Registry;

public class BlockStateArgumentSerializer extends BrigadierSerializer<BlockStateArgument> {
    private static final BlockStateArgumentSerializer INSTANCE = new BlockStateArgumentSerializer();

    private BlockStateArgumentSerializer() {
        super(BlockStateArgument.class, BlockStateArgumentType.blockState());
    }

    public static BlockStateArgumentSerializer blockState() {
        return INSTANCE;
    }

    @Override
    public String serialize(BlockStateArgument value) {
        var state = value.getBlockState();
        var block = state.getBlock();

        var builder = new StringBuilder()
                .append(Registry.BLOCK.getId(block));

        if (state.getProperties().size() == 0) {
            return builder.toString();
        }

        builder.append('[');
        var first = true;
        for (var prop : state.getProperties()) {
            if (first) {
                first = false;
            } else {
                builder.append(',');
            }

            builder.append(prop.getName())
                    .append('=')
                    .append(state.get(prop));
        }
        builder.append(']');

        return builder.toString();
    }
}