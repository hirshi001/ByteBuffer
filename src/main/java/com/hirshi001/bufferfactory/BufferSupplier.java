package com.hirshi001.bufferfactory;


import com.hirshi001.buffers.ByteBuffer;

public interface BufferSupplier {

    public ByteBuffer getBuffer(BufferFactory bufferFactory, int size);

}
