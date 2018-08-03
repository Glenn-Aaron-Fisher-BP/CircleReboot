package Operators;

import java.nio.ByteBuffer;

public abstract class Pixel {
    private static final int MAX_UNSCALED = 0xFF * 0xFF * 0xFF * 3;
    private static final int DEFAULT_SCALE = 0xFF;

    public static int rgbIntensity(int rgb) {
        return rgbIntensity(rgb, DEFAULT_SCALE);
    }

    private static int rgbIntensity(int rgb, int scale) {
        int alpha = rgb >> 24;
        int intensity = 0;
        for (int shift = 0; shift < 24; shift += 8) {
            int color = (rgb >> shift) % (0x100);
            intensity += color * color;
        }
        return intensity * alpha * scale / (MAX_UNSCALED + 1);
    }
}
