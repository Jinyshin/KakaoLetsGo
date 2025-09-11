package org.example.domain.item;

import java.util.List;

public class SideItem extends FoodItem {

  private final String sideType;

  public SideItem(String name, int price, String sideType) {
    super(name, price);
    this.sideType = sideType;
  }

}
