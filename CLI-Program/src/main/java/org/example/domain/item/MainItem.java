package org.example.domain.item;

public class MainItem extends FoodItem {

  private final String vegetable;
  private final String meatType;

  public MainItem(String name, int price, String vegetable, String meatType) {
    super(name, price);
    this.vegetable = vegetable;
    this.meatType = meatType;
  }

}
