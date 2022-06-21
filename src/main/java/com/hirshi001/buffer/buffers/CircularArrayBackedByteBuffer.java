package com.hirshi001.buffer.buffers;

import com.hirshi001.buffer.bufferfactory.BufferFactory;

import java.util.NoSuchElementException;

public class CircularArrayBackedByteBuffer extends AbstractByteBuffer{

    private byte[] bytes;
    private int readerIndex;
    private int writerIndex;
    private int arrayReaderIndex;
    private int arrayWriterIndex;
    private int readerMark;
    private int writerMark;
    private final Object lock = new Object();

    public CircularArrayBackedByteBuffer(int size, BufferFactory factory) {
        this(new byte[size], factory);
    }

    public CircularArrayBackedByteBuffer(BufferFactory factory) {
        this(16, factory);
    }

    public CircularArrayBackedByteBuffer(byte[] bytes, BufferFactory bufferFactory) {
        super(bufferFactory);
        this.bytes = bytes;
    }


    @Override
    public void clear() {
        readerIndex = 0;
        writerIndex = 0;
        readerMark = 0;
        writerMark = 0;
        arrayReaderIndex = 0;
        arrayWriterIndex = 0;
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
        this.arrayReaderIndex = readerIndex%bytes.length;
        return this;
    }

    @Override
    public ByteBuffer writerIndex(int writerIndex) {
        this.writerIndex = writerIndex;
        this.arrayWriterIndex = writerIndex%bytes.length;
        return this;
    }

    public int arrayWriterIndex(){
        return arrayWriterIndex;
    }

    public int arrayReaderIndex(){
        return arrayReaderIndex;
    }

    @Override
    public ByteBuffer markReaderIndex() {
        readerMark = readerIndex;
        return this;
    }

    @Override
    public ByteBuffer resetReaderIndex() {
        return readerIndex(readerMark);
    }

    @Override
    public ByteBuffer markWriterIndex() {
        writerMark = writerIndex;
        return this;
    }

    @Override
    public ByteBuffer resetWriterIndex() {
        return writerIndex(writerMark);
    }

    @Override
    public ByteBuffer discardReadBytes() {
        return this;
    }

    @Override
    public ByteBuffer ensureWritable(int writable) {
        int needed = writable-writableBytes();
        if(needed > 0){
            int newSize = bytes.length + needed;
            if(newSize < bytes.length*2) newSize = bytes.length*2;

            byte[] newBytes = new byte[newSize];
            if(arrayReaderIndex < arrayWriterIndex) {
                System.arraycopy(bytes, 0, newBytes, 0, bytes.length);
                //arrayReaderIndex remains the same
                //arrayWriterIndex remains the same
            }else {
                System.arraycopy(bytes, arrayWriterIndex, newBytes, newSize-bytes.length+arrayWriterIndex, bytes.length-arrayWriterIndex);

                System.arraycopy(bytes, 0, newBytes, 0, arrayReaderIndex);

                arrayReaderIndex = newSize-bytes.length+arrayReaderIndex;
                //something incorrect here, idk yet

                /*
                System.arraycopy(bytes, arrayReaderIndex, newBytes, 0, bytes.length - arrayReaderIndex);
                System.arraycopy(bytes, 0, newBytes, bytes.length - arrayReaderIndex, arrayReaderIndex);
                arrayReaderIndex = 0;
                arrayWriterIndex = readableBytes();

                 */
            }
            bytes = newBytes;
        }
        return this;
    }


    @Override
    public int readableBytes() {
        return writerIndex - readerIndex;
    }

    @Override
    public int writableBytes() {
        if(readerIndex == writerIndex) return bytes.length;
        else if(arrayWriterIndex > arrayReaderIndex){
            return bytes.length - arrayWriterIndex + arrayReaderIndex;
        }else{
            return arrayReaderIndex - arrayWriterIndex;
        }
    }

    @Override
    public int size() {
        return bytes.length;
    }

    @Override
    public ByteBuffer writeByte(int b) {
        ensureWritable(1);

        bytes[arrayWriterIndex] = (byte) b;
        arrayWriterIndex+=1;
        if(arrayWriterIndex == bytes.length) arrayWriterIndex = 0;
        writerIndex+=1;

        return this;
    }

    @Override
    public byte readByte() {
        if(writerIndex == readerIndex) throw new NoSuchElementException();
        byte b = bytes[arrayReaderIndex];
        arrayReaderIndex+=1;
        if(arrayReaderIndex == bytes.length)arrayReaderIndex = 0;
        readerIndex+=1;
        return b;
    }

    @Override
    public ByteBuffer putByte(int b, int index) {
        bytes[getIndexInArray(index)] = (byte) b;
        return this;
    }

    @Override
    public byte getByte(int index) {
        return bytes[getIndexInArray(index)];
    }

    private int getIndexInArray(int index){
        return (index-readerIndex+arrayReaderIndex)%bytes.length;
    }

    @Override
    public boolean hasArray() {
        return false;
    }

    @Override
    public byte[] array() {
        return bytes;
    }

}
