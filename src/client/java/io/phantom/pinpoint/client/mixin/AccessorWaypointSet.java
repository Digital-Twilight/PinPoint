package io.phantom.pinpoint.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import xaero.common.minimap.waypoints.Waypoint;
import xaero.hud.minimap.waypoint.set.WaypointSet;

import java.util.List;

@Mixin(value = WaypointSet.class, remap = false)
public interface AccessorWaypointSet {
    @Accessor("list")
    List<Waypoint> getList();
}