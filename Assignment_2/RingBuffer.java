package Assignment_2;

public class RingBuffer<T> {

    private final Object[] items;  // this is the array that stpores the elements
    private final int size;   // capacity of buffer
    private long writePos = 0;  // next writing position 

    public RingBuffer(int size) {
        this.size = size;
        this.items = new Object[size];
    }

    // write one item so only 1 writer allowed
    public synchronized void write(T item) {
        int index = (int) (writePos % size); // find index in array
        items[index] = item;       // stores the itek
        writePos++;      // moves write position to next
    }

    // read item at a specific sequence number
    protected synchronized T read(long seq) {
        if (seq < writePos - size) return null; // if it is missed or overwriten
        if (seq >= writePos) return null;    // if there is still nothing in that position

        int index = (int) (seq % size);    // calculates array index
        return (T) items[index];    // returns the item
    }

    public synchronized long getWritePos() {
        return writePos;   // current glopbal write position
    }

    // for creating a new reader
    public RingBufferReader<T> newReader() {
        return new RingBufferReader<>(this);
    }

    protected int getSize() {
        return size;
    }
}