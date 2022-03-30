package renderer;

import org.junit.jupiter.api.Test;
import primitives.*;

/**
 * Testing ImageWriterTest Class
 */
public class ImageWriterTest {

    /**
     * First test ImageWriterTest methods
     */
    @Test
    void testImg() {
        Color myColor = new Color(13, 101, 88);
        ImageWriter imageWriter = new ImageWriter("first image", 800, 500);
        for (int i = 0; i < 800; ++i) {
            for (int j = 0; j < 500; ++j) {
                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(i, j, Color.BLACK);
                else
                    imageWriter.writePixel(i, j, myColor);
            }
        }
        imageWriter.writeToImage();
    }

}
