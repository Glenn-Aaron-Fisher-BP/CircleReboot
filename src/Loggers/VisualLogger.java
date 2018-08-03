package Loggers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class VisualLogger {
    private static final String DEFAULT_DIRECTORY = "C:\\Users\\figon7\\Desktop\\ReportingTools\\CircleReboot\\src\\Logs\\";
    private boolean enabled = true;
    private String directory;

    public VisualLogger() {
        directory = DEFAULT_DIRECTORY + new Date().getTime() + "\\";
    }

    public void writeFile(int w, int h, int[] data, String name) {
        if (!enabled) return;
        assert data.length == w * h;
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < data.length; i++) {
            image.setRGB(i % w, i / w, data[i]);
        }
        try {
            ImageIO.write(image, "png", new File(directory + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
