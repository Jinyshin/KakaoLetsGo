package org.example.domain.item;

public class FoodItem {

  private final String name;
  private final int price;

  public FoodItem(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }
}
