//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.io.chario;

import alekseybykov.portfolio.io.IOTestBase;
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
@DisplayName("Tests for some concepts of InputStreamReader and InputStreamWriter")
class ReaderWriterTest extends IOTestBase {

    private final String charsetName = "UTF-8";

    @Test
    @SneakyThrows
    @DisplayName("Read all the chars from string one by one using buffer")
    void testReadAllCharsFromStringUsingBuffer() {
        final String stringJapanese = "いくつかのテキスト";
        try (Reader reader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(stringJapanese.getBytes(charsetName))))) {

            assertEquals('い', (char)reader.read());
            assertEquals('く', (char)reader.read());
            assertEquals('つ', (char)reader.read());
            assertEquals('か', (char)reader.read());
            assertEquals('の', (char)reader.read());
            assertEquals('テ', (char)reader.read());
            assertEquals('キ', (char)reader.read());
            assertEquals('ス', (char)reader.read());
            assertEquals('ト', (char)reader.read());
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Read all the chars from string one by one without buffer")
    void testReadAllCharsFromStringWithoutBuffer() {
        final String stringChinese = "人物";
        try (Reader reader = new InputStreamReader(
                new ByteArrayInputStream(stringChinese.getBytes(charsetName)))) {

            assertEquals('人', (char)reader.read());
            assertEquals('物', (char)reader.read());
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Write all the chars of string to file using buffer")
    void testWriteAllCharsFromStringToFile() {
        final String stringHebrew = "שתי שורות";
        try (Reader reader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(stringHebrew.getBytes(charsetName))));
             Writer writer = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream(destinationFile), charsetName))) {

            assertTrue(destinationFile.exists());

            int c;
            while ((c = reader.read()) != -1) {
                writer.write((char)c);
            }

            assertTrue(destinationFile.length() == 0);
            writer.flush();
            assertTrue(destinationFile.length() > 0);

            Reader inputStreamReader = new InputStreamReader(new FileInputStream(destinationFile));

            assertEquals('ש', inputStreamReader.read());
            assertEquals('ת', inputStreamReader.read());
            assertEquals('י', inputStreamReader.read());
            assertEquals(' ', inputStreamReader.read());
            assertEquals('ש', inputStreamReader.read());
            assertEquals('ו', inputStreamReader.read());
            assertEquals('ר', inputStreamReader.read());
            assertEquals('ו', inputStreamReader.read());
            assertEquals('ת', inputStreamReader.read());

            inputStreamReader.close();
        }
    }
}
