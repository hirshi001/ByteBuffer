package com.hirshi001.buffer.bufferfactory;


import com.hirshi001.buffer.buffers.ByteBuffer;

public interface BufferFactory {

    public ByteBuffer buffer();
    public ByteBuffer buffer(int size);
    public ByteBuffer buffer(byte[] bytes);
    public ByteBuffer buffer(byte[] bytes, int offset, int length);

    /**
     * Creates a new buffer with the given capacity. Depending on the implementation, it may or may not be a direct buffer.
     * @param size the capacity of the buffer
     * @return the new buffer
     */
    public ByteBuffer tryDirectBuffer(int size);

    public ByteBuffer circularBuffer();
    public ByteBuffer circularBuffer(int size);
    public ByteBuffer circularBuffer(byte[] bytes);

    public ByteBuffer wrap(byte[] bytes);
    public ByteBuffer wrap(byte[] bytes, int offset, int length);

    public ByteBuffer duplicate(ByteBuffer buffer);

    public void recycle(ByteBuffer buffer);

}
