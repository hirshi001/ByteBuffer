package com.hirshi001.buffer.byteorder;

public enum ByteOrder {

    LITTLE_ENDIAN(){

        @Override
        public void writeInt(ByteWriter writer, int i) {
            //write int in little endian
            writer.writeByte((byte) (i));
            writer.writeByte((byte) (i >>> 8));
            writer.writeByte((byte) (i >>> 16));
            writer.writeByte((byte) (i >>> 24));
        }

        @Override
        public int readInt(ByteReader reader) {
            //read int in little endian
            return (reader.readByte() & 0xFF) |
                    (reader.readByte() & 0xFF) << 8 |
                    (reader.readByte() & 0xFF) << 16 |
                    (reader.readByte() & 0xFF) << 24;
        }

        @Override
        public void writeLong(ByteWriter writer, long l) {
            //write long in little endian
            writer.writeByte((byte) (l));
            writer.writeByte((byte) (l >>> 8));
            writer.writeByte((byte) (l >>> 16));
            writer.writeByte((byte) (l >>> 24));
            writer.writeByte((byte) (l >>> 32));
            writer.writeByte((byte) (l >>> 40));
            writer.writeByte((byte) (l >>> 48));
            writer.writeByte((byte) (l >>> 56));

        }

        @Override
        public long readLong(ByteReader reader) {
            //read long in little endian
            return (reader.readByte() & 0xFF) |
                    (reader.readByte() & 0xFF) << 8 |
                    (reader.readByte() & 0xFF) << 16 |
                    (long) (reader.readByte() & 0xFF) << 24 |
                    (long) (reader.readByte() & 0xFF) << 32 |
                    (long) (reader.readByte() & 0xFF) << 40 |
                    (long) (reader.readByte() & 0xFF) << 48 |
                    (long) (reader.readByte() & 0xFF) << 56;
        }

        @Override
        public void writeShort(ByteWriter writer, int s) {
            //write short in little endian
            writer.writeByte((byte) (s));
            writer.writeByte((byte) (s >>> 8));
        }

        @Override
        public short readShort(ByteReader reader) {
            //read short in little endian
            return (short) ((reader.readByte() & 0xFF) |
                    (reader.readByte() & 0xFF) << 8);
        }

        @Override
        public void putInt(BytePutter putter, int index, int i) {
            putter.putByte((byte)(i), index);
            putter.putByte((byte)(i >>> 8), index + 1);
            putter.putByte((byte)(i >>> 16), index + 2);
            putter.putByte((byte)(i >>> 24), index + 3);
        }

        @Override
        public int getInt(ByteGetter getter, int index) {
            return (getter.getByte(index) & 0xFF) |
                    (getter.getByte(index + 1) & 0xFF) << 8 |
                    (getter.getByte(index + 2) & 0xFF) << 16 |
                    (getter.getByte(index + 3) & 0xFF) << 24;
        }

        @Override
        public void putLong(BytePutter putter, int index, long l) {
            putter.putByte((byte)(l), index);
            putter.putByte((byte)(l >>> 8), index + 1);
            putter.putByte((byte)(l >>> 16), index + 2);
            putter.putByte((byte)(l >>> 24), index + 3);
            putter.putByte((byte)(l >>> 32), index + 4);
            putter.putByte((byte)(l >>> 40), index + 5);
            putter.putByte((byte)(l >>> 48), index + 6);
            putter.putByte((byte)(l >>> 56), index + 7);
        }

        @Override
        public long getLong(ByteGetter getter, int index) {
            return (getter.getByte(index) & 0xFF) |
                    (getter.getByte(index + 1) & 0xFF) << 8 |
                    (getter.getByte(index + 2) & 0xFF) << 16 |
                    (getter.getByte(index + 3) & 0xFF) << 24 |
                    (getter.getByte(index + 4) & 0xFF) << 32 |
                    (getter.getByte(index + 5) & 0xFF) << 40 |
                    (getter.getByte(index + 6) & 0xFF) << 48 |
                    (getter.getByte(index + 7) & 0xFF) << 56;
        }

        @Override
        public void putShort(BytePutter putter, int index, int s) {
            putter.putByte((byte)(s), index);
            putter.putByte((byte)(s >>> 8), index + 1);
        }

        @Override
        public short getShort(ByteGetter getter, int index) {
            return (short) ((getter.getByte(index) & 0xFF) |
                    (getter.getByte(index + 1) & 0xFF) << 8);
        }
    },

    BIG_ENDIAN(){
        @Override
        public void writeInt(ByteWriter writer, int i) {
            //write int in big endian
            writer.writeByte((byte) (i >>> 24));
            writer.writeByte((byte) (i >>> 16));
            writer.writeByte((byte) (i >>> 8));
            writer.writeByte((byte) (i));
        }

        @Override
        public int readInt(ByteReader reader) {
            //read int in big endian
            return (reader.readByte() & 0xFF) << 24 |
                    (reader.readByte() & 0xFF) << 16 |
                    (reader.readByte() & 0xFF) << 8 |
                    (reader.readByte() & 0xFF);
        }

        @Override
        public void writeLong(ByteWriter writer, long l) {
            //write long in big endian
            writer.writeByte((byte) (l >>> 56));
            writer.writeByte((byte) (l >>> 48));
            writer.writeByte((byte) (l >>> 40));
            writer.writeByte((byte) (l >>> 32));
            writer.writeByte((byte) (l >>> 24));
            writer.writeByte((byte) (l >>> 16));
            writer.writeByte((byte) (l >>> 8));
            writer.writeByte((byte) (l));
        }

        @Override
        public long readLong(ByteReader reader) {
            //read long in big endian
            return (long) (reader.readByte() & 0xFF) << 56 |
                    (long) (reader.readByte() & 0xFF) << 48 |
                    (long) (reader.readByte() & 0xFF) << 40 |
                    (long) (reader.readByte() & 0xFF) << 32 |
                    (long) (reader.readByte() & 0xFF) << 24 |
                    (reader.readByte() & 0xFF) << 16 |
                    (reader.readByte() & 0xFF) << 8 |
                    (reader.readByte() & 0xFF);
        }

        @Override
        public void writeShort(ByteWriter writer, int s) {
            //write short in big endian
            writer.writeByte((byte) (s >>> 8));
            writer.writeByte((byte) (s));
        }

        @Override
        public short readShort(ByteReader reader) {
            //read short in big endian
            return (short) ((reader.readByte() & 0xFF) << 8 |
                    (reader.readByte() & 0xFF));
        }

        @Override
        public void putInt(BytePutter putter, int index, int i) {
            putter.putByte((byte)(i >>> 24), index);
            putter.putByte((byte)(i >>> 16), index + 1);
            putter.putByte((byte)(i >>> 8), index + 2);
            putter.putByte((byte)(i), index + 3);
        }

        @Override
        public int getInt(ByteGetter getter, int index) {
            return (getter.getByte(index) & 0xFF) << 24 |
                    (getter.getByte(index + 1) & 0xFF) << 16 |
                    (getter.getByte(index + 2) & 0xFF) << 8 |
                    (getter.getByte(index + 3) & 0xFF);
        }

        @Override
        public void putLong(BytePutter putter, int index, long l) {
            putter.putByte((byte)(l >>> 56), index);
            putter.putByte((byte)(l >>> 48), index + 1);
            putter.putByte((byte)(l >>> 40), index + 2);
            putter.putByte((byte)(l >>> 32), index + 3);
            putter.putByte((byte)(l >>> 24), index + 4);
            putter.putByte((byte)(l >>> 16), index + 5);
            putter.putByte((byte)(l >>> 8), index + 6);
            putter.putByte((byte)(l), index + 7);
        }

        @Override
        public long getLong(ByteGetter getter, int index) {
            return (long) (getter.getByte(index) & 0xFF) << 56 |
                    (long) (getter.getByte(index + 1) & 0xFF) << 48 |
                    (long) (getter.getByte(index + 2) & 0xFF) << 40 |
                    (long) (getter.getByte(index + 3) & 0xFF) << 32 |
                    (long) (getter.getByte(index + 4) & 0xFF) << 24 |
                    (getter.getByte(index + 5) & 0xFF) << 16 |
                    (getter.getByte(index + 6) & 0xFF) << 8 |
                    (getter.getByte(index + 7) & 0xFF);
        }

        @Override
        public void putShort(BytePutter putter, int index, int s) {
            putter.putByte((byte)(s >>> 8), index);
            putter.putByte((byte)(s), index + 1);
        }

        @Override
        public short getShort(ByteGetter getter, int index) {
            return (short) ((getter.getByte(index) & 0xFF) << 8 |
                    (getter.getByte(index + 1) & 0xFF));
        }
    };

    public abstract void writeInt(ByteWriter writer, int i);

    public abstract int readInt(ByteReader reader);

    public abstract void writeLong(ByteWriter writer, long l);

    public abstract long readLong(ByteReader reader);

    public abstract void writeShort(ByteWriter writer, int s);

    public abstract short readShort(ByteReader reader);

    public void writeFloat(ByteWriter writer, float f){
        writeInt(writer, Float.floatToIntBits(f));
    }

    public float readFloat(ByteReader reader){
        return Float.intBitsToFloat(readInt(reader));
    }

    public void writeDouble(ByteWriter writer, double d){
        writeLong(writer, Double.doubleToLongBits(d));
    }

    public double readDouble(ByteReader reader){
        return Double.longBitsToDouble(readLong(reader));
    }

    public abstract void putInt(BytePutter putter, int index, int i);

    public abstract int getInt(ByteGetter getter, int index);

    public abstract void putLong(BytePutter putter, int index, long l);

    public abstract long getLong(ByteGetter getter, int index);

    public abstract void putShort(BytePutter putter, int index, int s);

    public abstract short getShort(ByteGetter getter, int index);

    public void putFloat(BytePutter putter, int index, float f){
        putInt(putter, index, Float.floatToIntBits(f));
    }

    public float getFloat(ByteGetter getter, int index){
        return Float.intBitsToFloat(getInt(getter, index));
    }

    public void putDouble(BytePutter putter, int index, double d){
        putLong(putter, index, Double.doubleToLongBits(d));
    }

    public double getDouble(ByteGetter getter, int index){
        return Double.longBitsToDouble(getLong(getter, index));
    }

}
