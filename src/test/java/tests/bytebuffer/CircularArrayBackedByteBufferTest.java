package tests.bytebuffer;

import com.hirshi001.buffer.bufferfactory.BufferFactory;
import com.hirshi001.buffer.bufferfactory.DefaultBufferFactory;
import com.hirshi001.buffer.buffers.ByteBuffer;
import com.hirshi001.buffer.buffers.CircularArrayBackedByteBuffer;
import com.hirshi001.buffer.byteorder.ByteOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularArrayBackedByteBufferTest {


    public BufferFactory bufferFactory;

    @BeforeEach
    public void setup() {
        //switch out with whatever ByteBuffer you want to test
        bufferFactory = new DefaultBufferFactory();
        bufferFactory.defaultOrder(ByteOrder.BIG_ENDIAN);
    }

    @Test
    public void BasicTest(){
        ByteBuffer buffer = bufferFactory.circularBuffer(10);

        for(int i=0;i<100;i++){
            buffer.writeByte(i);
        }
        assertEquals(100, buffer.readableBytes());
        for(int i=0;i<100;i++){
            assertEquals(buffer.readByte(), i);
        }
    }

    @Test
    public void circularTest(){
        ByteBuffer buffer = bufferFactory.circularBuffer(15);
        buffer.writerIndex(25);
        buffer.readerIndex(20);

        for(int i=0;i<25;i++){
            buffer.writeByte(i);
        }

        for(int i=0;i<5;i++){
            assertEquals(0, buffer.readByte());
        }
        for(int j=0;j<10;j++) {
            for (int i = 0; i < 25; i++) {
                assertEquals(i, buffer.readByte());
                buffer.writeByte(i);
            }
        }
    }

    @Test
    public void putGetTest(){
        ByteBuffer buffer = bufferFactory.circularBuffer(10);
        for(int i=0;i<5;i++) buffer.writeByte(1);
        for(int i=0;i<17;i++){
            buffer.writeByte(2);
            buffer.readByte();
        }
        System.out.println(Arrays.toString(buffer.array()));
        System.out.println(((CircularArrayBackedByteBuffer)buffer).arrayReaderIndex());
        System.out.println(((CircularArrayBackedByteBuffer)buffer).arrayWriterIndex());

        System.out.println(buffer.readerIndex());
        System.out.println(buffer.writerIndex());

        buffer.putByte(10, 12);
        assertEquals(10, buffer.getByte(12));

    }


}
