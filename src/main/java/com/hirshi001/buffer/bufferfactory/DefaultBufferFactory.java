package com.hirshi001.buffer.bufferfactory;


import com.hirshi001.buffer.buffers.ArrayBackedByteBuffer;
import com.hirshi001.buffer.buffers.ByteBuffer;
import com.hirshi001.buffer.buffers.CircularArrayBackedByteBuffer;
import com.hirshi001.buffer.byteorder.ByteOrder;

import java.util.Comparator;
import java.util.TreeSet;

public class DefaultBufferFactory implements BufferFactory {

    private ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;


    public DefaultBufferFactory() {
    }

    private ByteBuffer newBuffer(int size){
        return new ArrayBackedByteBuffer(size, this);
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

    private ByteBuffer newBuffer(){
        return newBuffer(0);
    }

    @Override
    public ByteBuffer buffer() {
        return newBuffer().order(byteOrder);
    }

    @Override
    public ByteBuffer buffer(int size) {
        return newBuffer(size).order(byteOrder);
    }

    @Override
    public ByteBuffer buffer(byte[] bytes) {
        return newBuffer(bytes.length)
                .order(byteOrder)
                .writeBytes(bytes);
    }

    @Override
    public ByteBuffer buffer(byte[] bytes, int offset, int length) {
        return newBuffer(length)
                .order(byteOrder)
                .writeBytes(bytes, offset, length);
    }

    @Override
    public ByteBuffer tryDirectBuffer(int size) {
        return newBuffer(size)
                .order(byteOrder);
    }

    @Override
    public ByteBuffer circularBuffer() {
        return circularBuffer(16)
                .order(byteOrder);
    }

    @Override
    public ByteBuffer circularBuffer(int size) {
        return new CircularArrayBackedByteBuffer(size, this)
                .order(byteOrder);
    }

    @Override
    public ByteBuffer circularBuffer(byte[] bytes) {
        return new CircularArrayBackedByteBuffer(bytes, this)
                .order(byteOrder);
    }

    @Override
    public ByteBuffer wrap(byte[] bytes) {
        return new ArrayBackedByteBuffer(bytes, this)
                .order(byteOrder);
    }

    @Override
    public ByteBuffer wrap(byte[] bytes, int offset, int length) {
        return new ArrayBackedByteBuffer(bytes, this)
                .order(byteOrder)
                .writerIndex(offset + length)
                .readerIndex(offset);
    }

}


