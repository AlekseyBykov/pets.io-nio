//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.io.byteio;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 2019-10-15
 */
@DisplayName("Tests for some concepts of FileInputStream and FileOutputStream")
class FileIOStreamTest extends ByteIOTestBase {

    @Test
    @SneakyThrows
    @DisplayName("Write data to file and then read it from there")
    void testWriteReadDataToFileWithoutBuffers() {
        try (OutputStream os = new FileOutputStream(file);
             InputStream is = new FileInputStream(file)) {

            assertTrue(file.exists());

            byte[] byteArray = {0, 1, 2, 3};
            for (byte abyte : byteArray) {
                os.write(abyte);
            }

            os.flush();
            assertTrue(is.available() > 0);

            assertEquals(0, is.read());
            assertEquals(1, is.read());
            assertEquals(2, is.read());
            assertEquals(3, is.read());

            // stream is empty
            assertEquals(-1, is.read());
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Write data to file through buffer and then read it from there")
    void testWriteReadDataToFileUsingBuffers() {
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
             InputStream is = new BufferedInputStream(new FileInputStream(file))) {

            assertTrue(file.exists());

            byte[] byteArray = {3, 2, 1, 0};
            for (int i = 0; i < byteArray.length; i++) {
                os.write(byteArray[i]);
            }

            os.flush();

            assertTrue(is.available() > 0);

            assertEquals(3, is.read());
            assertEquals(2, is.read());
            assertEquals(1, is.read());
            assertEquals(0, is.read());

            // stream is empty
            assertEquals(-1, is.read());
            assertEquals(-1, is.read());
        }
    }
}
