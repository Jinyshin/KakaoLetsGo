package org.example.domain;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.example.domain.item.FoodItem;

public class Order {

  private static final AtomicInteger nextId = new AtomicInteger(1);
  private final int id;
  private final String customerName;
  private final List<FoodItem> items;
  private String status;

  public Order(String customerName, List<FoodItem> items) {
    this.id = nextId.getAndIncrement();
    this.customerName = customerName;
    this.items = items;
    this.status = "PENDING";
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

}
