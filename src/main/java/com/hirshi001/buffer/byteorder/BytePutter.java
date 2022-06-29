package com.hirshi001.buffer.byteorder;

@FunctionalInterface
public interface BytePutter<T> {

    public T putByte(int b, int index);

}
