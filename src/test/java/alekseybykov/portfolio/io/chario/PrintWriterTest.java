package alekseybykov.portfolio.io.chario;

import alekseybykov.portfolio.io.IOTestBase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Aleksey Bykov
 * @since 15.10.2019
 */
@DisplayName("Tests for some concepts of PrintWriter")
class PrintWriterTest extends IOTestBase {

    @Test
    @SneakyThrows
    @DisplayName("Append data to file line by line")
    void testAppendDataToFile() {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(destinationFile, true));
             BufferedReader bufferedReader = new BufferedReader(new FileReader(fixtureFile))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }

            printWriter.flush();

            assertTrue(destinationFile.exists());
            assertTrue(destinationFile.length() > 0);

            BufferedReader reader = new BufferedReader(new FileReader(destinationFile));

            assertEquals("line 1", reader.readLine());
            assertEquals("line 2", reader.readLine());
            assertEquals("line 3", reader.readLine());

            reader.close();
        }
    }
}
