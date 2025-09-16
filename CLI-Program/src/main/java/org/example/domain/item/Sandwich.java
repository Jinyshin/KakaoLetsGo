package org.example.domain.item;

import org.example.data.MenuData;

public class Sandwich extends MainMenu {

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

  public static Sandwich createRandom() {
    String sandwichName = MenuData.getRandomSandwich();
    int sandwichPrice = MenuData.getMenuPrice(sandwichName);
    String vegetableType = MenuData.getRandomVegetable();
    String meatType = MenuData.getRandomMeat();
    String breadType = MenuData.getRandomBread();
    String cheeseType = MenuData.getRandomCheese();
    String sauceType = MenuData.getRandomSauce();

    return new Sandwich(sandwichName, sandwichPrice, vegetableType, meatType, breadType, cheeseType, sauceType);
  }
}
