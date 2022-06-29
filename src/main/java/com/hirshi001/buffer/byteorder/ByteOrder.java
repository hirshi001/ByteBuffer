package com.hirshi001.buffer.byteorder;

public enum ByteOrder {

    LITTLE_ENDIAN(){
        @Override
        public void writeByte(ByteWriter writer, byte b) {
            writer.writeByte(b);
        }

        @Override
        public byte readByte(ByteReader reader) {
            return reader.readByte();
        }

        @Override
        public void writeInt(ByteWriter writer, int i) {
            //write int in little endian
            writeByte(writer, (byte)(i));
            writeByte(writer, (byte)(i >> 8));
            writeByte(writer, (byte)(i >> 16));
            writeByte(writer, (byte)(i >> 24));
        }

        @Override
        public int readInt(ByteReader reader) {
            //read int in little endian
            int i = readByte(reader);
            i |= (readByte(reader) << 8);
            i |= (readByte(reader) << 16);
            i |= (readByte(reader) << 24);
            return i;
        }

        @Override
        public void writeLong(ByteWriter writer, long l) {
            //write long in little endian
            writeByte(writer, (byte)(l));
            writeByte(writer, (byte)(l >> 8));
            writeByte(writer, (byte)(l >> 16));
            writeByte(writer, (byte)(l >> 24));
            writeByte(writer, (byte)(l >> 32));
            writeByte(writer, (byte)(l >> 40));
            writeByte(writer, (byte)(l >> 48));
            writeByte(writer, (byte)(l >> 56));
        }

        @Override
        public long readLong(ByteReader reader) {
            //read long in little endian
            long l = readByte(reader);
            l |= (readByte(reader) << 8);
            l |= (readByte(reader) << 16);
            l |= (readByte(reader) << 24);
            l |= ((long) readByte(reader) << 32);
            l |= ((long) readByte(reader) << 40);
            l |= ((long) readByte(reader) << 48);
            l |= ((long) readByte(reader) << 56);
            return l;
        }

        @Override
        public void writeShort(ByteWriter writer, int s) {
            //write short in little endian
            writeByte(writer, (byte)(s));
            writeByte(writer, (byte)(s >> 8));
        }

        @Override
        public short readShort(ByteReader reader) {
            //read short in little endian
            short s = readByte(reader);
            s |= (readByte(reader) << 8);
            return s;
        }

        @Override
        public void putByte(BytePutter putter, int index, byte b) {
            putter.putByte(b, index);
        }

        @Override
        public byte getByte(ByteGetter getter, int index) {
            return getter.getByte(index);
        }

        @Override
        public void putInt(BytePutter putter, int index, int i) {
            putter.putByte((byte)(i), index);
            putter.putByte((byte)(i >> 8), index + 1);
            putter.putByte((byte)(i >> 16), index + 2);
            putter.putByte((byte)(i >> 24), index + 3);
        }

        @Override
        public int getInt(ByteGetter getter, int index) {
            int i = getter.getByte(index);
            i |= (getter.getByte(index + 1) << 8);
            i |= (getter.getByte(index + 2) << 16);
            i |= (getter.getByte(index + 3) << 24);
            return i;
        }

        @Override
        public void putLong(BytePutter putter, int index, long l) {
            putter.putByte((byte)(l), index);
            putter.putByte((byte)(l >> 8), index + 1);
            putter.putByte((byte)(l >> 16), index + 2);
            putter.putByte((byte)(l >> 24), index + 3);
            putter.putByte((byte)(l >> 32), index + 4);
            putter.putByte((byte)(l >> 40), index + 5);
            putter.putByte((byte)(l >> 48), index + 6);
            putter.putByte((byte)(l >> 56), index + 7);
        }

        @Override
        public long getLong(ByteGetter getter, int index) {
            long l = getter.getByte(index);
            l |= (getter.getByte(index + 1) << 8);
            l |= (getter.getByte(index + 2) << 16);
            l |= (getter.getByte(index + 3) << 24);
            l |= ((long) getter.getByte(index + 4) << 32);
            l |= ((long) getter.getByte(index + 5) << 40);
            l |= ((long) getter.getByte(index + 6) << 48);
            l |= ((long) getter.getByte(index + 7) << 56);
            return l;
        }

        @Override
        public void putShort(BytePutter putter, int index, int s) {
            putter.putByte((byte)(s), index);
            putter.putByte((byte)(s >> 8), index + 1);
        }

        @Override
        public short getShort(ByteGetter getter, int index) {
            short s = getter.getByte(index);
            s |= (getter.getByte(index + 1) << 8);
            return s;
        }
    },

    BIG_ENDIAN(){
        @Override
        public void writeByte(ByteWriter writer, byte b) {
            writer.writeByte(b);
        }

        @Override
        public byte readByte(ByteReader reader) {
            return reader.readByte();
        }

        @Override
        public void writeInt(ByteWriter writer, int i) {
            //write int in big endian
            writeByte(writer, (byte)(i >> 24));
            writeByte(writer, (byte)(i >> 16));
            writeByte(writer, (byte)(i >> 8));
            writeByte(writer, (byte)i);
        }

        @Override
        public int readInt(ByteReader reader) {
            //read int in big endian
            int i = readByte(reader);
            i |= (readByte(reader) << 8);
            i |= (readByte(reader) << 16);
            i |= (readByte(reader) << 24);
            return i;
        }

        @Override
        public void writeLong(ByteWriter writer, long l) {
            //write long in big endian
            writeByte(writer, (byte)(l >> 56));
            writeByte(writer, (byte)(l >> 48));
            writeByte(writer, (byte)(l >> 40));
            writeByte(writer, (byte)(l >> 32));
            writeByte(writer, (byte)(l >> 24));
            writeByte(writer, (byte)(l >> 16));
            writeByte(writer, (byte)(l >> 8));
            writeByte(writer, (byte)l);
        }

        @Override
        public long readLong(ByteReader reader) {
            //read long in big endian
            long l = readByte(reader);
            l |= ((long) readByte(reader) << 8);
            l |= ((long) readByte(reader) << 16);
            l |= ((long) readByte(reader) << 24);
            l |= ((long) readByte(reader) << 32);
            l |= ((long) readByte(reader) << 40);
            l |= ((long) readByte(reader) << 48);
            return l;
        }

        @Override
        public void writeShort(ByteWriter writer, int s) {
            //write short in big endian
            writeByte(writer, (byte)(s >> 8));
            writeByte(writer, (byte)s);
        }

        @Override
        public short readShort(ByteReader reader) {
            //read short in big endian
            short s = readByte(reader);
            s |= (readByte(reader) << 8);
            return s;
        }

        @Override
        public void putByte(BytePutter putter, int index, byte b) {
            putter.putByte(b, index);
        }

        @Override
        public byte getByte(ByteGetter getter, int index) {
            return getter.getByte(index);
        }

        @Override
        public void putInt(BytePutter putter, int index, int i) {
            putter.putByte((byte)(i >> 24), index);
            putter.putByte((byte)(i >> 16), index + 1);
            putter.putByte((byte)(i >> 8), index + 2);
            putter.putByte((byte)i, index + 3);
        }

        @Override
        public int getInt(ByteGetter getter, int index) {
            int i = getter.getByte(index);
            i |= (getter.getByte(index + 1) << 8);
            i |= (getter.getByte(index + 2) << 16);
            i |= (getter.getByte(index + 3) << 24);
            return i;
        }

        @Override
        public void putLong(BytePutter putter, int index, long l) {
            putter.putByte((byte)(l), index);
            putter.putByte((byte)(l >> 8), index + 1);
            putter.putByte((byte)(l >> 16), index + 2);
            putter.putByte((byte)(l >> 24), index + 3);
            putter.putByte((byte)(l >> 32), index + 4);
            putter.putByte((byte)(l >> 40), index + 5);
            putter.putByte((byte)(l >> 48), index + 6);
            putter.putByte((byte)(l >> 56), index + 7);
        }

        @Override
        public long getLong(ByteGetter getter, int index) {
            long l = getter.getByte(index);
            l |= (getter.getByte(index + 1) << 8);
            l |= (getter.getByte(index + 2) << 16);
            l |= (getter.getByte(index + 3) << 24);
            l |= ((long) getter.getByte(index + 4) << 32);
            l |= ((long) getter.getByte(index + 5) << 40);
            l |= ((long) getter.getByte(index + 6) << 48);
            l |= ((long) getter.getByte(index + 7) << 56);
            return l;
        }

        @Override
        public void putShort(BytePutter putter, int index, int s) {
            putter.putByte((byte)(s >> 8), index);
            putter.putByte((byte)s, index + 1);
        }

        @Override
        public short getShort(ByteGetter getter, int index) {
            short s = getter.getByte(index);
            s |= (getter.getByte(index + 1) << 8);
            return s;
        }
    };

    public abstract void writeByte(ByteWriter writer, byte b);

    public abstract byte readByte(ByteReader reader);

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

    public abstract void putByte(BytePutter putter, int index, byte b);

    public abstract byte getByte(ByteGetter getter, int index);

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
