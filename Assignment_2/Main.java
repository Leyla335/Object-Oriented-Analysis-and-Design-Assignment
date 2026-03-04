package Assignment_2;

public class Main {
    public static void main(String[] args) {

        RingBuffer<String> buffer = new RingBuffer<>(3);

        RingBufferReader<String> reader1 = buffer.newReader();
        RingBufferReader<String> reader2 = buffer.newReader();

        buffer.write("A");
        buffer.write("B");
        buffer.write("C");

        System.out.println(reader1.read()); // prints A
        System.out.println(reader2.read()); // prints A
   

        buffer.write("D"); // overwrites A to D

        System.out.println(reader1.read()); // prints B

        buffer.write("F"); //overwrites B to F

        System.out.println(reader1.read()); // prints C
        System.out.println(reader1.read()); // prints D

        System.out.println(reader2.read()); // prints C
    }
}
