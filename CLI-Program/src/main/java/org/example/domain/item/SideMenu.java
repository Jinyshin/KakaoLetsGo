package org.example.domain.item;

public class SideMenu extends Menu {

  private final String sideType;

  public SideMenu(String name, int price, String sideType) {
    super(name, price);
    this.sideType = sideType;
  }

}