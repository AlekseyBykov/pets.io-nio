package alekseybykov.portfolio.io.byteio;

import alekseybykov.portfolio.io.IOTestBase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.RandomAccessFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Aleksey Bykov
 * @since 15.10.2019
 */
@DisplayName("Tests for some concepts of RandomAccessFile")
class RandomAccessFileTest extends IOTestBase {

    @Test
    @SneakyThrows
    @DisplayName("Shift position and write/read bytes to file")
    void test() {
        final String mode = "rw";
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(destinationFile, mode) ) {
            randomAccessFile.write(new byte[] {0, 1, 2, 3, 4, 5});
            randomAccessFile.seek(3);

            randomAccessFile.write(new byte[] {6, 7, 8, 9 , 10, 11, 12, 13});
            randomAccessFile.seek(0);

            byte[] array = new byte[10];
            randomAccessFile.read(array, 0, 8);

            assertEquals(0, array[0]);
            assertEquals(1, array[1]);
            assertEquals(2, array[2]);
            assertEquals(6, array[3]);
            assertEquals(7, array[4]);
            assertEquals(8, array[5]);
            assertEquals(9, array[6]);
            assertEquals(10, array[7]);

            assertEquals(0, array[8]);
            assertEquals(0, array[9]);

            assertThrows(IndexOutOfBoundsException.class,
                    () -> randomAccessFile.read(array, 1, 10));
        }
    }
}
