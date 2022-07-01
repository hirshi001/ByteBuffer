package tests.bytebuffer;

import com.hirshi001.buffer.bufferfactory.BufferFactory;
import com.hirshi001.buffer.buffers.ByteBuffer;
import com.hirshi001.buffer.buffers.CircularArrayBackedByteBuffer;
import com.hirshi001.buffer.byteorder.ByteOrder;

public class TestCircularBufferFactory implements BufferFactory {

    ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;

    @Override
    public ByteBuffer buffer() {
        return new CircularArrayBackedByteBuffer(16, this);
    }

    @Override
    public ByteBuffer buffer(int size) {
        return new CircularArrayBackedByteBuffer(size, this);
    }

    @Override
    public ByteBuffer buffer(byte[] bytes) {
        return null;
    }

    @Override
    public ByteBuffer buffer(byte[] bytes, int offset, int length) {
        return null;
    }

    @Override
    public ByteBuffer tryDirectBuffer(int size) {
        return null;
    }

    @Override
    public ByteBuffer circularBuffer() {
        return null;
    }

    @Override
    public ByteBuffer circularBuffer(int size) {
        return null;
    }

    @Override
    public ByteBuffer circularBuffer(byte[] bytes) {
        return null;
    }

    @Override
    public ByteBuffer wrap(byte[] bytes) {
        return null;
    }

    @Override
    public ByteBuffer wrap(byte[] bytes, int offset, int length) {
        return null;
    }

    @Override
    public void recycle(ByteBuffer buffer) {

    }

    @Override
    public ByteOrder defaultOrder() {
        return byteOrder;
    }

    @Override
    public BufferFactory defaultOrder(ByteOrder order) {
        this.byteOrder = order;
        return this;
    }
}
