package org.example.domain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.example.domain.item.Menu;

public class Order {

  private static final AtomicInteger nextId = new AtomicInteger(1);
  private final int id;
  private final String customerName;
  private final List<Menu> items;
  private OrderStatus status;

  public Order(String customerName, List<Menu> items) {
    this.id = nextId.getAndIncrement();
    this.customerName = customerName;
    this.items = items;
    this.status = OrderStatus.PENDING;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public int getId() {
    return id;
  }

}
