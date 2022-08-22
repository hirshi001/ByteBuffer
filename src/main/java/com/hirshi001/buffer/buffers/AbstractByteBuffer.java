package com.hirshi001.buffer.buffers;


import com.hirshi001.buffer.bufferfactory.BufferFactory;
import com.hirshi001.buffer.byteorder.ByteOrder;

public abstract class AbstractByteBuffer implements ByteBuffer {

    private BufferFactory factory;
    private ByteOrder order = ByteOrder.LITTLE_ENDIAN;

    public AbstractByteBuffer(BufferFactory factory) {
        this.factory = factory;
    }

    @Override
    public BufferFactory bufferFactory() {
        return factory;
    }

    @Override
    public ByteBuffer setBufferFactory(BufferFactory bufferFactory) {
        this.factory = bufferFactory;
        return this;
    }

    @Override
    public ByteOrder order() {
        return order;
    }

    @Override
    public ByteBuffer order(ByteOrder order) {
        this.order = order;
        return this;
    }

    @Override
    public void release() {
        factory.recycle(this);
    }



    //Subclasses implement
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public abstract ByteBuffer writeByte(int b);

    @Override
    public abstract byte readByte();

    @Override
    public abstract ByteBuffer putByte(int b, int index);

    @Override
    public abstract byte getByte(int index);
    //------------------------------------------------------------------------------------------------------------------



    @Override
    public ByteBuffer readBytes(int length) {
        ByteBuffer src = factory.buffer(length);
        readBytes(src, length);
        return src;
    }

    @Override
    public ByteBuffer writeInt(int i){
        order.writeInt(this, i);
        return this;
    }

    @Override
    public  int readInt(){
        return order.readInt(this);
    }

    @Override
    public ByteBuffer writeLong(long l){
        order.writeLong(this, l);
        return this;
    }

    @Override
    public long readLong(){
        return order.readLong(this);
    }

    @Override
    public ByteBuffer writeShort(int s){
        order.writeShort(this, s);
        return this;
    }

    @Override
    public short readShort(){
        return order.readShort(this);
    }

    @Override
    public ByteBuffer writeFloat(float f){
        order.writeFloat(this, f);
        return this;
    }

    @Override
    public float readFloat(){
        return order.readFloat(this);
    }

    @Override
    public ByteBuffer writeDouble(double d){
        order.writeDouble(this, d);
        return this;
    }

    @Override
    public double readDouble(){
        return order.readDouble(this);
    }

    @Override
    public ByteBuffer writeBoolean(boolean b){
        writeByte((byte)(b ? TRUE : FALSE));
        return this;
    }

    @Override
    public boolean readBoolean(){
        return readByte() == TRUE;
    }

    @Override
    public ByteBuffer writeChar(int c){
        writeShort((short)c);
        return this;
    }

    @Override
    public int readChar(){
        return readShort();
    }

    //--------------------------------------------------------------------------



    @Override
    public ByteBuffer writeBytes(byte[] bytes) {
        return writeBytes(bytes, 0, bytes.length);
    }

    @Override
    public ByteBuffer writeBytes(byte[] bytes, int offset, int length) {
        for(int i=0;i<length;i++) {
            writeByte(bytes[offset+i]);
        }
        return this;
    }

    @Override
    public ByteBuffer writeBytes(ByteBuffer src) {
        while(src.readableBytes()>0){
            writeByte(src.readByte());
        }
        return this;
    }

    @Override
    public ByteBuffer writeBytes(ByteBuffer src, int length) {
        for(int i=0;i<length;i++) {
            writeByte(src.readByte());
        }
        return this;
    }

    @Override
    public ByteBuffer writeBytes(ByteBuffer src, int srcIndex, int length) {
        for(int i=0;i<length;i++) {
            writeByte(src.getByte(srcIndex+i));
        }
        return this;
    }


    //--------------------------------------------------------------------------

    @Override
    public ByteBuffer getBytes(byte[] dst, int index, int length) {
        return getBytes(dst, 0, length, index);
    }

    @Override
    public ByteBuffer getBytes(byte[] dst, int dstIndex, int length, int index) {
        for(int i=0;i<length;i++) {
            dst[dstIndex+i] = getByte(index+i);
        }
        return this;
    }

    @Override
    public ByteBuffer getBytes(ByteBuffer dst, int index, int length) {
        for(int i=0;i<length;i++) {
            dst.writeByte(getByte(index+i));
        }
        return this;
    }

    @Override
    public ByteBuffer getBytes(ByteBuffer dst, int dstIndex, int length, int index) {
        for(int i=0;i<length;i++) {
            dst.putByte(getByte(index+i), dstIndex+i);
        }
        return this;
    }

    //--------------------------------------------------------------------------

    @Override
    public int readBytes(byte[] dst) {
        int length = Math.min(dst.length, readableBytes());
        readBytes(dst, 0, length);
        return length;
    }

    @Override
    public ByteBuffer readBytes(byte[] dst, int offset, int length) {
        for(int i=0;i<length;i++) {
            dst[offset+i] = readByte();
        }
        return this;
    }

    @Override
    public int readBytes(ByteBuffer dst) {
        int length = Math.min(dst.writableBytes(), readableBytes());
        readBytes(dst, 0, length);
        return length;
    }

    @Override
    public ByteBuffer readBytes(ByteBuffer dst, int length) {
        for(int i=0;i<length;i++) {
            dst.writeByte(readByte());
        }
        return this;
    }

    @Override
    public ByteBuffer readBytes(ByteBuffer dst, int dstIndex, int length) {
        for(int i=0;i<length;i++) {
            dst.putByte(readByte(), dstIndex+i);
        }
        return this;
    }


    //--------------------------------------------------------------------------

    @Override
    public ByteBuffer putBytes(byte[] bytes, int index) {
        return putBytes(bytes, 0, bytes.length, index);
    }

    @Override
    public ByteBuffer putBytes(byte[] bytes, int srcIndex, int length, int index) {
        for(int i=0;i<length;i++) {
            putByte(bytes[srcIndex+i], index+i);
        }
        return this;
    }

    @Override
    public ByteBuffer putBytes(ByteBuffer src, int index) {
        for (int i = 0; i < src.readableBytes(); i++) {
            putByte(src.getByte(i), index + i);
        }
        return this;
    }

    @Override
    public ByteBuffer putBytes(ByteBuffer src, int srcIndex, int length, int index) {
        for(int i=0;i<length;i++) {
            putByte(src.getByte(srcIndex+i), index+i);
        }
        return this;
    }

    //--------------------------------------------------------------------------


    @Override
    public ByteBuffer putInt(int i, int index) {
        order.putInt(this, index, i);
        return this;
    }

    @Override
    public ByteBuffer putLong(long l, int index) {
        order.putLong(this, index, l);
        return this;
    }

    @Override
    public ByteBuffer putShort(int s, int index) {
        order.putShort(this, index, s);
        return this;
    }

    @Override
    public ByteBuffer putDouble(double d, int index) {
        order.putDouble(this, index, d);
        return this;
    }

    @Override
    public ByteBuffer putFloat(float f, int index) {
        order.putFloat(this, index, f);
        return this;
    }

    @Override
    public ByteBuffer putBoolean(boolean b, int index) {
        putByte((byte)(b ? TRUE : FALSE), index);
        return this;
    }

    @Override
    public ByteBuffer putChar(char c, int index) {
        return putShort((short) c, index);
    }

    @Override
    public int getInt(int index) {
        return order.getInt(this, index);
    }

    @Override
    public long getLong(int index) {
        return order.getLong(this, index);
    }

    @Override
    public short getShort(int index) {
        return order.getShort(this, index);
    }

    @Override
    public double getDouble(int index) {
        return order.getDouble(this, index);
    }

    @Override
    public float getFloat(int index) {
        return order.getFloat(this, index);
    }

    @Override
    public boolean getBoolean(int index) {
        return getByte(index) == TRUE;
    }

    @Override
    public int getChar(int index) {
        return getShort(index);
    }


    //--------------------------------------------------------------------------


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ByteBuffer)) {
            return false;
        }

        ByteBuffer other = (ByteBuffer) obj;
        if (other.readableBytes() != readableBytes()) {
            return false;
        }

        for (int i = 0; i < readableBytes(); i++) {
            if (getByte(i + readerIndex()) != other.getByte(i + other.readerIndex())) {
                return false;
            }
        }
        return true;
    }

}
