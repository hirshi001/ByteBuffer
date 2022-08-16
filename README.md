# ByteBuffer
A Java library for reading and writing binary data.
## Features
* Read and write primitive data types
* Read and write strings
* Read and write arrays of primitive data types
* ArrayBackedBuffers and CircularArrayBuffers
* Easy to use API
* Easy to add your own implementation (e.g. for performance or to use native code)

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
```
