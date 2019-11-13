package alekseybykov.portfolio.io.byteio;

import alekseybykov.portfolio.io.IOTestBase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Tests for some concepts of FileInputStream and FileOutputStream")
class FileIOStreamTest extends IOTestBase {

    @Test
    @SneakyThrows
    @DisplayName("Write data to file and then read it from there")
    void testWriteReadDataToFileWithoutBuffers() {
        try (OutputStream os = new FileOutputStream(destinationFile);
             InputStream is = new FileInputStream(destinationFile)) {

            assertTrue(destinationFile.exists());

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
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(destinationFile));
             InputStream is = new BufferedInputStream(new FileInputStream(destinationFile))) {

            assertTrue(destinationFile.exists());

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
