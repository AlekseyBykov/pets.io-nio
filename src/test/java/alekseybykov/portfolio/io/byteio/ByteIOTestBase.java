//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.io.byteio;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 2019-10-15
 */
class ByteIOTestBase {

    File file;

    private Path filePath;

    @BeforeEach
    @SneakyThrows
    void createTempFile() {
        filePath = Paths.get("src", "test", "resources", "temp");
        file = filePath.resolve("tmp").toFile();
    }

    @AfterEach
    @SneakyThrows
    void clearTempDirectory() {
        FileUtils.cleanDirectory(filePath.toAbsolutePath().toFile());
    }
}
