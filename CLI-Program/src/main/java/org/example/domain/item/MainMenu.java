package org.example.domain.item;

public class MainMenu extends Menu {

  private final String vegetable;
  private final String meatType;

  public MainMenu(String name, int price, String vegetable, String meatType) {
    super(name, price);
    this.vegetable = vegetable;
    this.meatType = meatType;
  }
}