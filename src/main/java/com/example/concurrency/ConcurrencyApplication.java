package com.example.concurrency;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConcurrencyApplication {

  public static void main(String[] args) {
//    SpringApplication.run(ConcurrencyApplication.class, args);
    final BlockingQueue queue = new BlockingQueue(10);
    Thread producer = new Thread("PRODUCER")
    {
      public void run() {
        Object event = "FOUR";
        try
        {
          queue.enqueue(event);
          System.out.printf("[%s] published event : %s, current queue size: %s %n", Thread .currentThread()
              .getName(), event, queue.size());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    producer.start();
    Thread consumer = new Thread("CONSUMER") {
      public void run() {
        try
        {
          Object event = queue.dequeue();
          System.out.printf("[%s] consumed event : %s, current queue size: %s %n", Thread .currentThread()
              .getName(), event, queue.size());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    consumer.start();
  }
}


