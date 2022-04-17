package com.hirshi001.buffer.bufferfactory;


import com.hirshi001.buffer.buffers.ByteBuffer;

public interface BufferFactory {

    public ByteBuffer buffer();
    public ByteBuffer buffer(int size);
    public ByteBuffer buffer(byte[] bytes);
    public ByteBuffer buffer(byte[] bytes, int offset, int length);

    public ByteBuffer wrap(byte[] bytes);
    public ByteBuffer wrap(byte[] bytes, int offset, int length);

    public ByteBuffer duplicate(ByteBuffer buffer);

    public void recycle(ByteBuffer buffer);

}
