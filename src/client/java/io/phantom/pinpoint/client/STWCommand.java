package io.phantom.pinpoint.client;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.hud.minimap.waypoint.WaypointPurpose;
import io.phantom.pinpoint.client.mixin.AccessorWaypointSet;
import xaero.hud.minimap.waypoint.set.WaypointSet;
import xaero.hud.minimap.world.MinimapWorld;
import net.minecraft.text.Text;
import xaero.hud.minimap.BuiltInHudModules;
import xaero.hud.minimap.module.MinimapSession;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static xaero.hud.minimap.waypoint.WaypointColor.RED;

public class STWCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
                ClientCommandManager.literal("setwaypoint")
                        .then(ClientCommandManager.argument("x", IntegerArgumentType.integer())
                                .suggests((ctx, builder) -> suggestX(builder))
                                .then(ClientCommandManager.argument("y", IntegerArgumentType.integer())
                                        .suggests((ctx, builder) -> suggestY(builder))
                                        .then(ClientCommandManager.argument("z", IntegerArgumentType.integer())
                                                .suggests((ctx, builder) -> suggestZ(builder))
                                                .executes(STWCommand::execute)
                                        )))
        );
    }

    private static CompletableFuture<Suggestions> suggestX(SuggestionsBuilder builder) {
        List<int[]> coords = ChatCoordinateExtractor.getRecentCoords();
        if (!coords.isEmpty()) {
            int[] c = coords.getFirst();
            builder.suggest(
                    String.valueOf(c[0]),
                    Text.translatable("suggestion.coords.format", c[0], c[1], c[2])
            );
        }
        return builder.buildFuture();
    }

    private static CompletableFuture<Suggestions> suggestY(SuggestionsBuilder builder) {
        List<int[]> coords = ChatCoordinateExtractor.getRecentCoords();
        if (!coords.isEmpty()) {
            int[] c = coords.getFirst();
            builder.suggest(
                    String.valueOf(c[1]),
                    Text.translatable("suggestion.coords.format", c[0], c[1], c[2])
            );
        }
        return builder.buildFuture();
    }

    private static CompletableFuture<Suggestions> suggestZ(SuggestionsBuilder builder) {
        List<int[]> coords = ChatCoordinateExtractor.getRecentCoords();
        if (!coords.isEmpty()) {
            int[] c = coords.getFirst();
            builder.suggest(
                    String.valueOf(c[2]),
                    Text.translatable("suggestion.coords.format", c[0], c[1], c[2])
            );
        }
        return builder.buildFuture();
    }

    private static int execute(CommandContext<FabricClientCommandSource> context) {
        int x = IntegerArgumentType.getInteger(context, "x");
        int y = IntegerArgumentType.getInteger(context, "y");
        int z = IntegerArgumentType.getInteger(context, "z");

        try {
            WaypointSet waypointSet = getCurrentWaypointSet();

            if (waypointSet == null) {
                context.getSource().sendFeedback(
                        Text.translatable("command.setwaypoint.error.waypointset")
                );
                return 0;
            }

            String waypointName = Text.translatable("waypoint.default.name", x, y, z).getString();
            String waypointSymbol = Text.translatable("waypoint.default.symbol").getString();

            Waypoint waypoint = new Waypoint(
                    x, y, z,
                    waypointName,
                    waypointSymbol,
                    RED,
                    WaypointPurpose.NORMAL,
                    true
            );

            ((AccessorWaypointSet)(Object)waypointSet).getList().add(waypoint);

            lookAtWaypoint(context, x, y, z);

            context.getSource().sendFeedback(
                    Text.translatable("command.setwaypoint.success", x, y, z)
            );

            return 1;

        } catch (Exception e) {
            context.getSource().sendFeedback(
                    Text.translatable("command.setwaypoint.error.generic", e.getMessage())
            );
            return 0;
        }
    }

    private static void lookAtWaypoint(CommandContext<FabricClientCommandSource> context, int x, int y, int z) {
        try {
            MinecraftClient client = context.getSource().getClient();
            var player = client.player;

            if (player == null) return;

            double playerX = player.getX();
            double playerY = player.getEyeY();
            double playerZ = player.getZ();

            double diffX = x + 0.5 - playerX;
            double diffY = y + 0.5 - playerY;
            double diffZ = z + 0.5 - playerZ;

            double horizontalDistance = Math.sqrt(diffX * diffX + diffZ * diffZ);

            float yaw = (float) Math.toDegrees(Math.atan2(-diffX, diffZ));
            float pitch = (float) Math.toDegrees(Math.atan2(-diffY, horizontalDistance));

            pitch = MathHelper.clamp(pitch, -90.0F, 90.0F);

            player.setYaw(yaw);
            player.setPitch(pitch);

        } catch (Exception e) {
            context.getSource().sendFeedback(
                    Text.translatable("command.setwaypoint.error.camera", e.getMessage())
            );
        }
    }

    public static WaypointSet getCurrentWaypointSet() {
        try {
            MinimapSession minimapSession = BuiltInHudModules.MINIMAP.getCurrentSession();
            if (minimapSession == null) {
                return null;
            }
            MinimapWorld currentWorld = minimapSession.getWorldManager().getCurrentWorld();
            if (currentWorld == null) {
                return null;
            }
            return currentWorld.getCurrentWaypointSet();
        } catch (Exception e) {
            return null;
        }
    }
}