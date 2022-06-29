package com.hirshi001.buffer.buffers;


import com.hirshi001.buffer.bufferfactory.BufferFactory;

public class ArrayBackedByteBuffer extends AbstractByteBuffer{

    private byte[] data;
    private int readerIndex, writerIndex;
    private int readerMark, writerMark;


    public ArrayBackedByteBuffer(int size, BufferFactory factory) {
        super(factory);
        this.data = new byte[size];
        readerIndex = 0;
        writerIndex = 0;
    }

    public ArrayBackedByteBuffer(BufferFactory factory) {
        this(16, factory);
    }

    public ArrayBackedByteBuffer(byte[] data, BufferFactory factory) {
        super(factory);
        this.data = data;
        readerIndex = 0;
        writerIndex = data.length;
    }

    @Override
    public int readerIndex() {
        return readerIndex;
    }

    @Override
    public int writerIndex() {
        return writerIndex;
    }

    @Override
    public ByteBuffer readerIndex(int readerIndex) {
        this.readerIndex = readerIndex;
        return this;
    }

    @Override
    public ByteBuffer writerIndex(int writerIndex) {
        this.writerIndex = writerIndex;
        return this;
    }

    @Override
    public int readableBytes() {
        return writerIndex - readerIndex;
    }

    @Override
    public int writableBytes() {
        return data.length - writerIndex;
    }

    @Override
    public int size() {
        return data.length;
    }


    //------------------------------------------------------------------------------------------------------------------
    @Override
    public ByteBuffer writeByte(int b) {
        ensureWritable(1);
        data[writerIndex++] = (byte)b;
        return this;
    }

    @Override
    public byte readByte() {
        return data[readerIndex++];
    }
    @Override
    public ByteBuffer putByte(int b, int index) {
        data[index] = (byte)b;
        return this;
    }

    @Override
    public byte getByte(int index) {
        return data[index];
    }
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public ByteBuffer writeBytes(byte[] bytes) {
        return writeBytes(bytes, 0, bytes.length);
    }

    @Override
    public ByteBuffer writeBytes(byte[] bytes, int offset, int length) {
        ensureWritable(length);
        System.arraycopy(bytes, offset, data, writerIndex, length);
        writerIndex += length;
        return this;
    }

    @Override
    public ByteBuffer writeBytes(ByteBuffer src) {
        return writeBytes(src, src.readableBytes());
    }

    @Override
    public ByteBuffer writeBytes(ByteBuffer src, int length) {
        ensureWritable(length);
        if(src.hasArray()){
            System.arraycopy(src.array(), src.readerIndex(), data, writerIndex, length);
            writerIndex += length;
            src.readerIndex(src.readerIndex() + length);
            return this;
        }
        return super.writeBytes(src, length);
    }

    @Override
    public ByteBuffer writeBytes(ByteBuffer src, int srcIndex, int length) {
        ensureWritable(length);
        if(src.hasArray()){
            System.arraycopy(src.array(), srcIndex, data, writerIndex, length);
            writerIndex += length;
            return this;
        }
        return super.writeBytes(src, srcIndex, length);
    }


    @Override
    public int readBytes(byte[] dst) {
        int length = Math.min(readableBytes(), dst.length);
        readBytes(dst, 0, length);
        return length;
    }

    @Override
    public ByteBuffer readBytes(byte[] dst, int offset, int length) {
        System.arraycopy(data, readerIndex, dst, offset, length);
        readerIndex += length;
        return this;
    }

    @Override
    public int readBytes(ByteBuffer dst) {
        int length = Math.min(readableBytes(), dst.writableBytes());
        readBytes(dst, length);
        return length;
    }

    @Override
    public ByteBuffer readBytes(ByteBuffer dst, int length) {
        if(dst.hasArray()){
            System.arraycopy(data, readerIndex, dst.array(), dst.writerIndex(), length);
            readerIndex += length;
            dst.writerIndex(dst.writerIndex() + length);
            return this;
        }
        return super.readBytes(dst, length);
    }

    @Override
    public ByteBuffer readBytes(ByteBuffer dst, int dstIndex, int length) {
        if (dst.hasArray()) {
            System.arraycopy(data, readerIndex, dst.array(), dstIndex, length);
            readerIndex += length;
            return this;
        }
        return super.readBytes(dst, dstIndex, length);
    }



    @Override
    public ByteBuffer putBytes(byte[] bytes, int index) {
        System.arraycopy(bytes, 0, data, index, bytes.length);
        return this;
    }

    @Override
    public ByteBuffer putBytes(byte[] bytes, int srcIndex, int length, int index) {
        System.arraycopy(bytes, srcIndex, data, index, length);
        return this;
    }

    @Override
    public ByteBuffer putBytes(ByteBuffer src, int index) {
        int length = src.readableBytes();
        if (src.hasArray()) {
            System.arraycopy(src.array(), src.readerIndex(), data, index, length);
            src.readerIndex(src.readerIndex() + length);
            return this;
        }
        return super.putBytes(src, index);
    }

    @Override
    public ByteBuffer putBytes(ByteBuffer src, int srcIndex, int length, int index) {
        if (src.hasArray()) {
            System.arraycopy(src.array(), srcIndex, data, index, length);
            return this;
        }
        return super.putBytes(src, srcIndex, length, index);
    }


    @Override
    public ByteBuffer getBytes(byte[] dst, int index, int length) {
        System.arraycopy(data, index, dst, 0, length);
        return this;
    }

    @Override
    public ByteBuffer getBytes(byte[] dst, int dstIndex, int length, int index) {
        System.arraycopy(data, index, dst, dstIndex, length);
        return this;
    }

    @Override
    public ByteBuffer getBytes(ByteBuffer dst, int index, int length) {
        if (dst.hasArray()) {
            System.arraycopy(data, index, dst.array(), dst.writerIndex(), length);
            dst.writerIndex(dst.writerIndex() + length);
            return this;
        }
        return super.getBytes(dst, index, length);
    }

    @Override
    public ByteBuffer getBytes(ByteBuffer dst, int dstIndex, int length, int index) {
        if (dst.hasArray()) {
            System.arraycopy(data, index, dst.array(), dstIndex, length);
            return this;
        }
        return super.getBytes(dst, dstIndex, length, index);
    }

    @Override
    public boolean hasArray() {
        return true;
    }

    @Override
    public byte[] array() {
        return data;
    }

    @Override
    public ByteBuffer markReaderIndex() {
        readerMark = readerIndex;
        return this;
    }

    @Override
    public ByteBuffer resetReaderIndex() {
        readerIndex = readerMark;
        return this;
    }

    @Override
    public ByteBuffer markWriterIndex() {
        writerMark = writerIndex;
        return this;
    }

    @Override
    public ByteBuffer resetWriterIndex() {
        writerIndex = writerMark;
        return this;
    }

    @Override
    public ByteBuffer discardReadBytes() {
        if(readerIndex == readerMark) return this;
        byte[] newData = new byte[readableBytes()];
        System.arraycopy(data, readerIndex, newData, 0, readableBytes());
        data = newData;
        readerIndex = 0;
        writerIndex = readableBytes();
        readerMark = 0;
        writerMark = 0;
        return this;
    }

    @Override
    public ByteBuffer ensureWritable(int writable) {
        if(writable <= writableBytes()) return this;
        int newCapacity = Math.max(data.length << 1, writable);
        byte[] newData = new byte[newCapacity];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
        return this;
    }

    @Override
    public void clear() {
        readerIndex = 0;
        writerIndex = 0;
        readerMark = 0;
        writerMark = 0;
        //Arrays.fill(data, (byte) 0); // May not be necessary
    }

}
