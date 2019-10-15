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

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 2019-10-15
 */
@DisplayName("Tests for some concepts of InputStreamReader and InputStreamWriter")
class ReaderWriterTest extends IOTestBase {

    @Test
    @SneakyThrows
    @DisplayName("Read all the chars from string one by one")
    void test() {
        final String japaneseString = "いくつかのテキスト";
        final String charsetName = "UTF-8";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(japaneseString.getBytes(charsetName))))) {

            assertEquals('い', (char)bufferedReader.read());
            assertEquals('く', (char)bufferedReader.read());
            assertEquals('つ', (char)bufferedReader.read());
            assertEquals('か', (char)bufferedReader.read());
            assertEquals('の', (char)bufferedReader.read());
            assertEquals('テ', (char)bufferedReader.read());
            assertEquals('キ', (char)bufferedReader.read());
            assertEquals('ス', (char)bufferedReader.read());
            assertEquals('ト', (char)bufferedReader.read());
        }
    }
}
