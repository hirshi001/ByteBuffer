package tests.bytebuffer;

import com.hirshi001.buffer.bufferfactory.BufferFactory;
import com.hirshi001.buffer.bufferfactory.DefaultBufferFactory;
import com.hirshi001.buffer.buffers.ArrayBackedByteBuffer;
import com.hirshi001.buffer.buffers.ByteBuffer;
import com.hirshi001.buffer.buffers.CircularArrayBackedByteBuffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByteBufferTest {


    public BufferFactory bufferFactory;
    @BeforeEach
    public void setup() {
        //switch out with whatever ByteBuffer you want to test
        bufferFactory = new DefaultBufferFactory((bufferFactory, size) -> new CircularArrayBackedByteBuffer(size, bufferFactory));
    }

    @Test
    public void ByteBufferSimpleTest(){
        ByteBuffer buffer = bufferFactory.buffer(10);
        assertEquals(buffer.readableBytes(), 0);
        assertEquals(buffer.writableBytes(), 10);
        assertEquals(buffer.size(), 10);

        buffer.writeByte(10);
        buffer.writeByte(20);
        buffer.writeByte(30);

        assertEquals(3,  buffer.readableBytes());
        assertEquals(7, buffer.writableBytes());
        assertEquals(10, buffer.size());

        assertEquals(10, buffer.readByte());
        assertEquals(20, buffer.readByte());
        assertEquals(30, buffer.readByte());
    }

    @Test
    public void ByteBufferAutoResizeTest(){
        ByteBuffer buffer = bufferFactory.buffer(10);
        for(int i=0; i<100; i++){
            buffer.writeByte(i);
        }
        assert buffer.readableBytes() == 100;

        for(int i=0; i<100; i++){
            assertEquals(i, buffer.readByte());
        }
    }


    @Test
    public void EnsureWritableTest(){
        ByteBuffer buffer = bufferFactory.buffer(3);
        buffer.writeByte(10);
        buffer.writeInt(20);

        assertEquals(5, buffer.readableBytes());
        assertTrue(buffer.size()>=5, "Size of buffer is " + buffer.size());
        assertEquals(10, buffer.readByte());
        assertEquals(20, buffer.readInt());
    }

    @Test
    public void EnsureWritableTest2() {
        ByteBuffer buffer = bufferFactory.buffer(10);
        buffer.ensureWritable(100);

        assertTrue(buffer.writableBytes() >= 100, "Writable bytes is " + buffer.writableBytes() + ", expected at least 100");
    }


}
