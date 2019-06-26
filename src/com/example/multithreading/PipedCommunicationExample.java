package com.example.multithreading;

import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedCommunicationExample {

    public static void main(String[] args) {

        try {
            PipedReader reader = new PipedReader();
            PipedWriter writer = new PipedWriter();
            writer.connect(reader);

            Thread readerThread = new Thread(new PipedReaderThread("Reader Thread", reader));
            Thread writerThread = new Thread(new PipedWriterThread("Writer Thread", writer));

            readerThread.start();
            writerThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class PipedReaderThread implements Runnable {

    PipedReader reader;
    String name;

    public PipedReaderThread(String name, PipedReader reader) {
        this.reader = reader;
        this.name = name;
    }

    @Override
    public void run() {

        try {
            while (true) {
                char c = (char) reader.read();
                if (c != -1) {
                    System.out.print(c);
                }
            }
        } catch (Exception e) {
            System.out.println("Pipe thread exception");
        }

    }
}

class PipedWriterThread implements Runnable {

    PipedWriter writer;
    String name;

    public PipedWriterThread(String name, PipedWriter writer) {
        this.writer = writer;
        this.name = name;
    }

    @Override
    public void run() {
        try {

            while (true) {
                writer.write("Testing data written...\n");
                writer.flush();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println("Pipe thread exception");
        }
    }
}
