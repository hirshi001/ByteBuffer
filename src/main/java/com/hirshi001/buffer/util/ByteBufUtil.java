package com.hirshi001.buffer.util;

import com.hirshi001.buffer.buffers.ByteBuffer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ByteBufUtil {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    public static void writeStringToBuf(Charset charset, String msg, ByteBuffer buf){
        if(msg == null){
            buf.ensureWritable(4);
            buf.writeInt(-1);
            return;
        }
        byte[] bytes = msg.getBytes(charset);
        int size = bytes.length;

        buf.ensureWritable(size + 4);
        buf.writeInt(size);
        buf.writeBytes(bytes);
    }

    public static void writeStringToBuf(String msg, ByteBuffer buf){
        writeStringToBuf(DEFAULT_CHARSET, msg, buf);
    }

    public static String readStringFromBuf(Charset charset, ByteBuffer buf){
        int size = buf.readInt();
        if(size == -1) return null;

        byte[] bytes = new byte[size];
        buf.readBytes(bytes);
        return new String(bytes, charset);
    }

    public static String readStringFromBuf(ByteBuffer buf){
        return readStringFromBuf(DEFAULT_CHARSET, buf);
    }

    public static void writeVarInt(ByteBuffer buffer, int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                buffer.ensureWritable(1);
                buffer.writeByte(value);
                return;
            } else {
                buffer.writeByte((value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    public static int readVarInt(ByteBuffer buffer) {
        int result = 0;
        int shift = 0;
        byte b;
        do {
            b = buffer.readByte();
            result |= (b & 0x7F) << shift;
            shift += 7;
        } while ((b & 0x80) != 0);
        return result;
    }

}
