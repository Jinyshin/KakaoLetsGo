package org.example.domain.item;

import java.util.List;

public class Sandwich extends MainItem {

  private final String breadType;
  private final String cheeseType;
  private final String sauce;

  public Sandwich(
      String name, int price, String vegetable, String meatType,
      String breadType, String cheeseType, String sauce
  ) {
    super(name, price, vegetable, meatType);
    this.breadType = breadType;
    this.cheeseType = cheeseType;
    this.sauce = sauce;
  }
}
