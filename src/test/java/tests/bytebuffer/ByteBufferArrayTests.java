package tests.bytebuffer;

import com.hirshi001.buffer.bufferfactory.BufferFactory;
import com.hirshi001.buffer.bufferfactory.DefaultBufferFactory;
import com.hirshi001.buffer.buffers.ArrayBackedByteBuffer;
import com.hirshi001.buffer.buffers.ByteBuffer;
import com.hirshi001.buffer.buffers.CircularArrayBackedByteBuffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteBufferArrayTests {


    public BufferFactory bufferFactory;

    @BeforeEach
    public void setup(){
        bufferFactory = new DefaultBufferFactory((bufferFactory, size) -> new ArrayBackedByteBuffer(size, bufferFactory));
    }

    @Test
    public void ByteBufferWriteByteReadArrayTest(){
        ByteBuffer buffer = bufferFactory.buffer(10);

        buffer.writeByte(10);
        buffer.writeByte(20);
        buffer.writeByte(30);

        byte[] array = new byte[3];
        buffer.readBytes(array);

        assertEquals(10, array[0]);
        assertEquals(20, array[1]);
        assertEquals(30, array[2]);

        assertEquals(0, buffer.readableBytes());
        assertEquals(7, buffer.writableBytes());
        assertEquals(10, buffer.size());
    }

    @Test
    public void ByteBufferWriteArrayReadByteTest(){
        ByteBuffer buffer = bufferFactory.buffer(10);

        byte[] array = new byte[]{10, 20, 30};
        buffer.writeBytes(array);

        assertEquals(10, buffer.readByte());
        assertEquals(20, buffer.readByte());
        assertEquals(30, buffer.readByte());

        assertEquals(0, buffer.readableBytes());
        assertEquals(7, buffer.writableBytes());
        assertEquals(10, buffer.size());
    }

    @Test
    public void ReadWriteByteArray(){
        ByteBuffer buffer = bufferFactory.buffer(10);

        //Write Bytes
        byte[] array = new byte[]{10, 20, 30};
        buffer.writeBytes(array);

        //Read Bytes
        byte[] array2 = new byte[3];
        buffer.readBytes(array2);

        //Assert
        assertEquals(10, array2[0]);
        assertEquals(20, array2[1]);
        assertEquals(30, array2[2]);

        assertEquals(0, buffer.readableBytes());
        assertEquals(7, buffer.writableBytes());
        assertEquals(10, buffer.size());
    }

    @Test
    public void ByteBufferReadWriteTest1(){
        ByteBuffer buffer1 = bufferFactory.buffer(), buffer2 = bufferFactory.buffer();

        for(int i = 0; i < 10; i++){
            buffer1.writeByte(i);
        }

        assertEquals(10, buffer1.readableBytes());

        buffer2.writeBytes(buffer1);

        assertEquals(10, buffer2.readableBytes());
        assertEquals(0, buffer1.readableBytes());

        for(int i = 0; i < 10; i++){
            assertEquals(i, buffer2.readByte());
        }

        assertEquals(0, buffer2.readableBytes());
    }


    @Test
    public void ByteBufferReadWriteTest2(){
        ByteBuffer buffer1 = bufferFactory.buffer(), buffer2 = bufferFactory.buffer();

        for(int i = 0; i < 10; i++){
            buffer1.writeByte(i);
        }

        assertEquals(10, buffer1.readableBytes());

        buffer2.writeBytes(buffer1, 7);

        assertEquals(7, buffer2.readableBytes());
        assertEquals(3, buffer1.readableBytes());

        for(int i = 0; i < 7; i++){
            assertEquals(i, buffer2.readByte());
        }

        assertEquals(0, buffer2.readableBytes());
    }


    @Test
    public void ByteBufferReadWriteTest3(){
        ByteBuffer buffer1 = bufferFactory.buffer(), buffer2 = bufferFactory.buffer();
        int srcIndex = 2, length = 5, size = 10;

        for(int i = 0; i < size; i++){
            buffer1.writeByte(i);
        }

        assertEquals(size, buffer1.readableBytes());

        buffer2.writeBytes(buffer1, srcIndex, length);

        assertEquals(length, buffer2.readableBytes());
        assertEquals(size, buffer1.readableBytes());

        for(int i = srcIndex; i < srcIndex+length; i++){
            assertEquals(i, buffer2.readByte());
        }

        assertEquals(0, buffer2.readableBytes());
    }



}
