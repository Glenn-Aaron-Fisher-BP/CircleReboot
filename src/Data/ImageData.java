package Data;

import Operators.Pixel;
import Points.Point;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageData {
    long total = 0;
    int[] data;
    int w;
    int h;

    public ImageData(BufferedImage image) {
        w = image.getWidth();
        h = image.getHeight();
        data = new int[w * h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                data[i + j * w] = Pixel.rgbIntensity(image.getRGB(i, j));
                total += data[i + j * w];
            }
        }
    }

    public PointData getPointData() {
        Point[] points = new Point[data.length];
        for (int i = 0; i < data.length; i++) {
            points[i] = new Point(i % w, i / w);
        }
        return new PointData(points, data, new int[]{w,h});
    }

    public static class Tester {
        private static File TEST_FILE = new File("C:\\Users\\figon7\\Desktop\\ReportingTools\\CircleReboot\\src\\TestData\\SimpleCircle.png");
        @Test
        public void testPartitions() throws IOException {
            ImageData data = new ImageData(ImageIO.read(TEST_FILE));
            PointData pointData = data.getPointData();
            System.out.println(pointData.getLocalPoints(0,1).size());
        }
    }
}
