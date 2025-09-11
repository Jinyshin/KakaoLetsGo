package org.example.domain;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SubwayStore {

  private final BlockingQueue<Order> orderQueue;

  public SubwayStore() {
    this.orderQueue = new LinkedBlockingQueue<>();
  }

  public void addOrder(Order order) throws InterruptedException {
    this.orderQueue.put(order);
  }

  public Order getNextOrder() throws InterruptedException {
    return this.orderQueue.take();
  }
}
