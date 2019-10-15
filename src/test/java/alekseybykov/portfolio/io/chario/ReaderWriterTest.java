//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.io.chario;

import alekseybykov.portfolio.io.IOTestBase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        final String japaneseString = "いくつかのテキスト";
        try (Reader reader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(japaneseString.getBytes(charsetName))))) {

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
        final String chineseString = "人物";
        try (Reader reader = new InputStreamReader(
                new ByteArrayInputStream(chineseString.getBytes(charsetName)))) {

            assertEquals('人', (char)reader.read());
            assertEquals('物', (char)reader.read());
        }
    }
}
