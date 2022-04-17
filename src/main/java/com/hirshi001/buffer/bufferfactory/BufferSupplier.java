package com.hirshi001.buffer.bufferfactory;


import com.hirshi001.buffer.buffers.ByteBuffer;

public interface BufferSupplier {

    public ByteBuffer getBuffer(BufferFactory bufferFactory, int size);

}
