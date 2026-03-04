package Assignment_2;

public class RingBufferReader<T> {

    private final RingBuffer<T> buffer;  // reference to the buffer
    private long readPos;   // stores readers current read position

    public RingBufferReader(RingBuffer<T> buffer) {
        this.buffer = buffer;
        this.readPos = buffer.getWritePos(); // start reading from current write
    }

    // read next available item
    public T read() {
        synchronized (buffer) {
            long writePos = buffer.getWritePos();

            // if reader is too slow, it skips lost items
            if (readPos < writePos - buffer.getSize()) {
                readPos = writePos - buffer.getSize();
            }

            T item = buffer.read(readPos); // gets item

            if (item != null) readPos++; // moves reader forward if something was read

            return item;
        }
    }
}