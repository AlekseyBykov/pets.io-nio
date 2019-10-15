//
// Feel free to use these solutions in your work.
//
package alekseybykov.portfolio.io.byteio;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author  aleksey.n.bykov@gmail.com
 * @version 2019-10-14
 */
@DisplayName("Tests for some concepts of DataInputStream and DataOutputStream")
class DataIOStreamTest extends ByteIOTestBase {

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

            assertTrue(file.exists());
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

            assertTrue(file.exists());
            assertTrue(file.length() == 0);
        }
    }

    @Test
    @SneakyThrows
    @DisplayName("Write data to file through buffer and then read it from there by similar way")
    void testWriteReadFileUsingBuffer() {
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)));
             DataInputStream in = new DataInputStream
                (new BufferedInputStream(new FileInputStream(file)))) {

            out.writeInt(-1);
            out.writeBoolean(false);
            out.writeShort(20);
            out.writeLong(11L);
            out.writeFloat(1.1f);
            out.writeUTF("some utf string");
            out.flush();

            assertTrue(file.exists());
            assertTrue(file.length() > 0);

            assertEquals(-1, in.readInt());
            assertFalse(in.readBoolean());
            assertEquals(20, in.readShort());
            assertEquals(11L, in.readLong());
            assertEquals(1.1f, in.readFloat());
            assertEquals("some utf string", in.readUTF());

        } // will close all the streams
    }
}
