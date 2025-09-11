package org.example.domain.item;

import java.util.List;

public class Salad extends MainItem {

  private final String dressingType;

  public Salad(String name, int price, String vegetable, String meatType, String dressingType) {
    super(name, price, vegetable, meatType);
    this.dressingType = dressingType;
  }
}
