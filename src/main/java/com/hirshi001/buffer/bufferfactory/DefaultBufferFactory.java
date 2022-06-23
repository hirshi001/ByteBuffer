package com.hirshi001.buffer.bufferfactory;


import com.hirshi001.buffer.buffers.ArrayBackedByteBuffer;
import com.hirshi001.buffer.buffers.ByteBuffer;
import com.hirshi001.buffer.buffers.CircularArrayBackedByteBuffer;

import java.util.Comparator;
import java.util.TreeSet;

public class DefaultBufferFactory implements BufferFactory {


    public DefaultBufferFactory() {
    }

    private ByteBuffer newBuffer(int size){
        return new ArrayBackedByteBuffer(size, this);
    }


    @Override
    public void recycle(ByteBuffer buffer) {

    }

    private ByteBuffer newBuffer(){
        return newBuffer(0);
    }

    @Override
    public ByteBuffer buffer() {
        return newBuffer();
    }

    @Override
    public ByteBuffer buffer(int size) {
        return newBuffer(size);
    }

    @Override
    public ByteBuffer buffer(byte[] bytes) {
        ByteBuffer buffer = newBuffer(bytes.length);
        buffer.writeBytes(bytes);
        return buffer;
    }

    @Override
    public ByteBuffer buffer(byte[] bytes, int offset, int length) {
        ByteBuffer buffer = newBuffer(length);
        buffer.writeBytes(bytes, offset, length);
        return buffer;
    }

    @Override
    public ByteBuffer tryDirectBuffer(int size) {
        return newBuffer(size);
    }

    @Override
    public ByteBuffer circularBuffer() {
        return circularBuffer(16);
    }

    @Override
    public ByteBuffer circularBuffer(int size) {
        return new CircularArrayBackedByteBuffer(size, this);
    }

    @Override
    public ByteBuffer circularBuffer(byte[] bytes) {
        return new CircularArrayBackedByteBuffer(bytes, this);
    }

    @Override
    public ByteBuffer wrap(byte[] bytes) {
        return new ArrayBackedByteBuffer(bytes, this);
    }

    @Override
    public ByteBuffer wrap(byte[] bytes, int offset, int length) {
        ByteBuffer buffer = new ArrayBackedByteBuffer(bytes, this);
        buffer.writerIndex(offset+length);
        buffer.readerIndex(offset);
        return buffer;
    }

    @Override
    public ByteBuffer duplicate(ByteBuffer buffer) {
        ByteBuffer newBuffer = newBuffer(buffer.readableBytes());
        int readerIndex = buffer.readerIndex();
        newBuffer.writeBytes(buffer);
        newBuffer.readerIndex(readerIndex);
        return newBuffer;
    }

}


