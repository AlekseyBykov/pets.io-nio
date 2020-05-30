package alekseybykov.portfolio.io.byteio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Aleksey Bykov
 * @since 14.10.2019
 */
@DisplayName("Tests for some concepts of ByteArrayInputStream")
class ByteArrayInputStreamTest {

    private final byte[] byteArray = new byte[]{0, 1, 2, 3};

    @Test
    @DisplayName("Read all the bytes from array one by one")
    void testReadAllBytesFromArrayOneByOne() {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

        assertEquals(0, byteArrayInputStream.read());
        assertEquals(1, byteArrayInputStream.read());
        assertEquals(2, byteArrayInputStream.read());
        assertEquals(3, byteArrayInputStream.read());

        // stream is empty
        assertEquals(-1, byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());
        assertEquals(-1, byteArrayInputStream.read());
    }

    @Test
    @DisplayName("Read last two bytes from array")
    void testReadLastTwoBytesFromArray() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray, 2, 2);

        assertEquals(2, byteArrayInputStream.read());
        assertEquals(3, byteArrayInputStream.read());

        // stream is empty
        assertEquals(-1, byteArrayInputStream.read());
    }
}
