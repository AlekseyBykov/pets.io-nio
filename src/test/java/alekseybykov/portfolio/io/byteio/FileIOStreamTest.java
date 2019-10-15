//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.io.byteio;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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

            // stream is empty
            assertEquals(0, is.read());
            assertEquals(1, is.read());
            assertEquals(2, is.read());
            assertEquals(3, is.read());

            // stream is empty
            assertEquals(-1, is.read());
        }
    }
}
