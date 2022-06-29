package com.hirshi001.buffer.byteorder;


@FunctionalInterface
public interface ByteWriter<T> {

    T writeByte(int b);

}
