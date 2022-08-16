# ByteBuffer
A Java library for reading and writing binary data.  
Use Jitpack to download and install or use as a dependency.

## Features
* Read and write primitive data types
* Read and write strings
* Read and write arrays of primitive data types
* ArrayBackedBuffers and CircularArrayBuffers
* Easy to use API
* Easy to add your own implementation (e.g. for performance or to use native code)
* No need to call `flip()` when switching between reading and writing

## How to use
First create a BufferFactory.
```java
BufferFactory factory = new DefaultBufferFactory();
```
Then create a Buffer.
```java
ByteBuffer buffer1 = factory.buffer();
ByteBuffer buffer2 = factory.buffer(100); //initial capacity of 100

ByteBuffer buffer3 = factory.circularBuffer();
ByteBuffer buffer4 = factory.circularBuffer(100); //initial capacity of 100

ByteBuffer buffer5 = factory.wrap(new byte[5]{1,2,3,4,5});
```

Then use the buffer.
```java
for(int i=0;i<10;i++){
    buffer.writeInt(i);
}
for(int i=0;i<10;i++){
    System.out.println(buffer.readInt());
}
```

See Tests for more examples.
