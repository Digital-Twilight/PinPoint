package io.phantom.xaerosworldmapaddon.client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatCoordinateExtractor {

    private static final Pattern COORDS_PATTERN = Pattern.compile(
            "(-?\\d+)[,\\s]+(-?\\d+)[,\\s]+(-?\\d+)"
    );

    private static final int MAX_STORED = 10;
    private static final List<int[]> recentCoords = new ArrayList<>();

    public static void processMessage(String message) {
        Matcher matcher = COORDS_PATTERN.matcher(message);
        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int z = Integer.parseInt(matcher.group(3));

            int[] coords = {x, y, z};

            for (int[] existing : recentCoords) {
                if (existing[0] == x && existing[1] == y && existing[2] == z) return;
            }

            recentCoords.addFirst(coords);
            if (recentCoords.size() > MAX_STORED) {
                recentCoords.removeLast();
            }
        }
    }

    public static List<int[]> getRecentCoords() {
        return recentCoords;
    }
}