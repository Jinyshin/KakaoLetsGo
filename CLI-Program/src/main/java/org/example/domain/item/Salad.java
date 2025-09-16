package org.example.domain.item;

import org.example.data.MenuData;

public class Salad extends MainMenu {

  private final String dressingType;

  public Salad(String name, int price, String vegetable, String meatType, String dressingType) {
    super(name, price, vegetable, meatType);
    this.dressingType = dressingType;
  }

  public static Salad createRandom() {
    String saladName = MenuData.getRandomSalad();
    int saladPrice = MenuData.getMenuPrice(saladName);
    String vegetableType = MenuData.getRandomVegetable();
    String meatType = MenuData.getRandomMeat();
    String dressingType = MenuData.getRandomDressing();

    return new Salad(saladName, saladPrice, vegetableType, meatType, dressingType);
  }
}
