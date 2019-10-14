//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.io.byteio;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 2019-10-14
 */
@DisplayName("Tests for some concepts of DataInputStream and DataOutputStream")
class DataIOStreamTest {

    private static Path filePath;
    private static File file;

    @BeforeAll
    @SneakyThrows
    static void createTempFile() {
        filePath = Paths.get("src", "test", "resources", "temp");
        file = filePath.resolve("tmp").toFile();
    }

    @AfterAll
    @SneakyThrows
    static void clearTempDirectory() {
        FileUtils.cleanDirectory(filePath.toAbsolutePath().toFile());
    }

    @Test
    @SneakyThrows
    @DisplayName("Write data to file through buffer")
    void testWriteDataToFileThroughBuffer() {
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))) {

            out.writeInt(1);
            out.writeShort(2);
            out.writeLong(3L);
            out.writeBoolean(true);
            out.writeUTF("some utf string");
            out.flush();

            assertTrue(file.length() > 0);
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Write data to buffer without flushing")
    void testWriteDataToBufferWithoutFlushing() {
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))) {

            out.writeInt(1);
            out.writeLong(3L);

            assertTrue(file.length() == 0);
        }
    }
}
