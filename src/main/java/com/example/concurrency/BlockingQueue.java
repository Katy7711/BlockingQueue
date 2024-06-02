package com.example.concurrency;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BlockingQueue {
  private List<Object> queue;
  private int limit;
  public BlockingQueue(int limit){
    this.limit = limit;
    this.queue = new ArrayList<>();
  }


  public synchronized void enqueue(Object element) throws InterruptedException  {
    while (this.queue.size() == this.limit) {
      wait();
    }
    if (this.queue.size() == 0) {
      notifyAll();
    }
    this.queue.add(element);
  }


  public synchronized Object dequeue() throws InterruptedException{
    while (this.queue.size() == 0){
      wait();
    }
    if (this.queue.size() == this.limit){
      notifyAll();
    }
    return this.queue.remove(0);
  }

  public int size() {
    return this.queue.size();
  }
}

