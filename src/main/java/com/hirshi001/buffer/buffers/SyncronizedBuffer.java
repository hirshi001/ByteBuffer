package com.hirshi001.buffer.buffers;

import com.hirshi001.buffer.bufferfactory.BufferFactory;
import com.hirshi001.buffer.byteorder.ByteOrder;

public class SyncronizedBuffer implements ByteBuffer{

    private final ByteBuffer inner;

    public SyncronizedBuffer(ByteBuffer inner){
        this.inner = inner;
    }

    @Override
    public BufferFactory bufferFactory() {
        synchronized (inner) {
            return inner.bufferFactory();
        }
    }

    @Override
    public ByteBuffer setBufferFactory(BufferFactory bufferFactory) {
        synchronized (inner) {
            return inner.setBufferFactory(bufferFactory);
        }
    }

    @Override
    public void clear() {
        synchronized (inner) {
            inner.clear();
        }
    }

    @Override
    public void release() {
        synchronized (inner) {
            inner.release();
        }
    }

    @Override
    public ByteOrder order() {
        return inner.order();
    }

    @Override
    public ByteBuffer order(ByteOrder order) {
        return inner.order(order);
    }

    @Override
    public int readerIndex() {
        synchronized (inner) {
            return inner.readerIndex();
        }
    }

    @Override
    public int writerIndex() {
        synchronized (inner) {
            return inner.writerIndex();
        }
    }

    @Override
    public ByteBuffer readerIndex(int readerIndex) {
        synchronized (inner) {
            return inner.readerIndex(readerIndex);
        }
    }

    @Override
    public ByteBuffer writerIndex(int writerIndex) {
        synchronized (inner) {
            return inner.writerIndex(writerIndex);
        }
    }

    @Override
    public ByteBuffer markReaderIndex() {
        synchronized (inner) {
            return inner.markReaderIndex();
        }
    }

    @Override
    public ByteBuffer resetReaderIndex() {
        synchronized (inner) {
            return inner.resetReaderIndex();
        }
    }

    @Override
    public ByteBuffer markWriterIndex() {
        synchronized (inner) {
            return inner.markWriterIndex();
        }
    }

    @Override
    public ByteBuffer resetWriterIndex() {
        synchronized (inner) {
            return inner.resetWriterIndex();
        }
    }

    @Override
    public ByteBuffer discardReadBytes() {
        synchronized (inner) {
            return inner.discardReadBytes();
        }
    }

    @Override
    public ByteBuffer ensureWritable(int writable) {
        synchronized (inner) {
            return inner.ensureWritable(writable);
        }
    }

    @Override
    public int readableBytes() {
        synchronized (inner) {
            return inner.readableBytes();
        }
    }

    @Override
    public int writableBytes() {
        synchronized (inner) {
            return inner.writableBytes();
        }
    }

    @Override
    public int size() {
        synchronized (inner) {
            return inner.size();
        }
    }

    @Override
    public ByteBuffer writeBytes(byte[] bytes) {
        synchronized (inner) {
            return inner.writeBytes(bytes);
        }
    }

    @Override
    public ByteBuffer writeBytes(byte[] bytes, int offset, int length) {
        synchronized (inner) {
            return inner.writeBytes(bytes, offset, length);
        }
    }

    @Override
    public ByteBuffer writeBytes(ByteBuffer src) {
        synchronized (inner) {
            return inner.writeBytes(src);
        }
    }

    @Override
    public ByteBuffer writeBytes(ByteBuffer src, int length) {
        synchronized (inner) {
            return inner.writeBytes(src, length);
        }
    }

    @Override
    public ByteBuffer writeBytes(ByteBuffer src, int srcIndex, int length) {
        synchronized (inner) {
            return inner.writeBytes(src, srcIndex, length);
        }
    }

    @Override
    public ByteBuffer readBytes(int length) {
        synchronized (inner) {
            return inner.readBytes(length);
        }
    }

    @Override
    public int readBytes(byte[] dst) {
        synchronized (inner) {
            return inner.readBytes(dst);
        }
    }

    @Override
    public ByteBuffer readBytes(byte[] dst, int offset, int length) {
        synchronized (inner) {
            return inner.readBytes(dst, offset, length);
        }
    }

    @Override
    public int readBytes(ByteBuffer dst) {
        synchronized (inner) {
            return inner.readBytes(dst);
        }
    }

    @Override
    public ByteBuffer readBytes(ByteBuffer dst, int length) {
        synchronized (inner) {
            return inner.readBytes(dst, length);
        }
    }

    @Override
    public ByteBuffer readBytes(ByteBuffer dst, int dstIndex, int length) {
        synchronized (inner) {
            return inner.readBytes(dst, dstIndex, length);
        }
    }

    @Override
    public ByteBuffer writeByte(int b) {
        synchronized (inner) {
            return inner.writeByte(b);
        }
    }

    @Override
    public ByteBuffer writeInt(int i) {
        synchronized (inner) {
            return inner.writeInt(i);
        }
    }

    @Override
    public ByteBuffer writeLong(long l) {
        synchronized (inner) {
            return inner.writeLong(l);
        }
    }

    @Override
    public ByteBuffer writeShort(int s) {
        synchronized (inner) {
            return inner.writeShort(s);
        }
    }

    @Override
    public ByteBuffer writeDouble(double d) {
        synchronized (inner) {
            return inner.writeDouble(d);
        }
    }

    @Override
    public ByteBuffer writeFloat(float f) {
        synchronized (inner) {
            return inner.writeFloat(f);
        }
    }

    @Override
    public ByteBuffer writeBoolean(boolean b) {
        synchronized (inner) {
            return inner.writeBoolean(b);
        }
    }

    @Override
    public ByteBuffer writeChar(int c) {
        synchronized (inner) {
            return inner.writeChar(c);
        }
    }

    @Override
    public byte readByte() {
        synchronized (inner) {
            return inner.readByte();
        }
    }

    @Override
    public int readInt() {
        synchronized (inner) {
            return inner.readInt();
        }
    }

    @Override
    public long readLong() {
        synchronized (inner) {
            return inner.readLong();
        }
    }

    @Override
    public short readShort() {
        synchronized (inner) {
            return inner.readShort();
        }
    }

    @Override
    public double readDouble() {
        synchronized (inner) {
            return inner.readDouble();
        }
    }

    @Override
    public float readFloat() {
        synchronized (inner) {
            return inner.readFloat();
        }
    }

    @Override
    public boolean readBoolean() {
        synchronized (inner) {
            return inner.readBoolean();
        }
    }

    @Override
    public int readChar() {
        synchronized (inner) {
            return inner.readChar();
        }
    }

    @Override
    public ByteBuffer putByte(int b, int index) {
        synchronized (inner) {
            return inner.putByte(b, index);
        }
    }

    @Override
    public ByteBuffer putBytes(byte[] bytes, int index) {
        synchronized (inner) {
            return inner.putBytes(bytes, index);
        }
    }

    @Override
    public ByteBuffer putBytes(byte[] bytes, int srcIndex, int length, int index) {
        synchronized (inner) {
            return inner.putBytes(bytes, srcIndex, length, index);
        }
    }

    @Override
    public ByteBuffer putBytes(ByteBuffer src, int index) {
        synchronized (inner) {
            return inner.putBytes(src, index);
        }
    }

    @Override
    public ByteBuffer putBytes(ByteBuffer src, int srcIndex, int length, int index) {
        synchronized (inner) {
            return inner.putBytes(src, srcIndex, length, index);
        }
    }

    @Override
    public byte getByte(int index) {
        synchronized (inner) {
            return inner.getByte(index);
        }
    }

    @Override
    public ByteBuffer getBytes(byte[] dst, int index, int length) {
        synchronized (inner) {
            return inner.getBytes(dst, index, length);
        }
    }

    @Override
    public ByteBuffer getBytes(byte[] dst, int dstIndex, int length, int index) {
        synchronized (inner) {
            return inner.getBytes(dst, dstIndex, length, index);
        }
    }

    @Override
    public ByteBuffer getBytes(ByteBuffer dst, int index, int length) {
        synchronized (inner) {
            return inner.getBytes(dst, index, length);
        }
    }

    @Override
    public ByteBuffer getBytes(ByteBuffer dst, int dstIndex, int length, int index) {
        synchronized (inner) {
            return inner.getBytes(dst, dstIndex, length, index);
        }
    }

    @Override
    public ByteBuffer putInt(int i, int index) {
        synchronized (inner) {
            return inner.putInt(i, index);
        }
    }

    @Override
    public ByteBuffer putLong(long l, int index) {
        synchronized (inner) {
            return inner.putLong(l, index);
        }
    }

    @Override
    public ByteBuffer putShort(int s, int index) {
        synchronized (inner) {
            return inner.putShort(s, index);
        }
    }

    @Override
    public ByteBuffer putDouble(double d, int index) {
        synchronized (inner) {
            return inner.putDouble(d, index);
        }
    }

    @Override
    public ByteBuffer putFloat(float f, int index) {
        synchronized (inner) {
            return inner.putFloat(f, index);
        }
    }

    @Override
    public ByteBuffer putBoolean(boolean b, int index) {
        synchronized (inner) {
            return inner.putBoolean(b, index);
        }
    }

    @Override
    public ByteBuffer putChar(char c, int index) {
        synchronized (inner) {
            return inner.putChar(c, index);
        }
    }

    @Override
    public int getInt(int index) {
        synchronized (inner) {
            return inner.getInt(index);
        }
    }

    @Override
    public long getLong(int index) {
        synchronized (inner) {
            return inner.getLong(index);
        }
    }

    @Override
    public short getShort(int index) {
        synchronized (inner) {
            return inner.getShort(index);
        }
    }

    @Override
    public double getDouble(int index) {
        synchronized (inner) {
            return inner.getDouble(index);
        }
    }

    @Override
    public float getFloat(int index) {
        synchronized (inner) {
            return inner.getFloat(index);
        }
    }

    @Override
    public boolean getBoolean(int index) {
        synchronized (inner) {
            return inner.getBoolean(index);
        }
    }

    @Override
    public int getChar(int index) {
        synchronized (inner) {
            return inner.getChar(index);
        }
    }

    @Override
    public boolean hasArray() {
        synchronized (inner) {
            return inner.hasArray();
        }
    }

    @Override
    public byte[] array() {
        synchronized (inner) {
            return inner.array();
        }
    }
}
