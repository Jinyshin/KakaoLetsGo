package org.example.thread;

import java.util.ArrayList;
import java.util.List;
import org.example.data.MenuData;
import org.example.domain.Order;
import org.example.domain.SubwayStore;
import org.example.domain.item.FoodItem;
import org.example.domain.item.Salad;
import org.example.domain.item.Sandwich;
import org.example.domain.item.SideItem;
import org.example.service.SimulationStats;

public class CustomerThread implements Runnable {

  private final int threadId;
  private final String threadName;
  private final SubwayStore store;

  public CustomerThread(int id, SubwayStore store) {
    this.threadId = id;
    this.threadName = "손님" + id;
    this.store = store;
  }

  public void createOrder(List<FoodItem> items) {
    try {
      Order order = new Order(threadName, items);
      store.addOrder(order);
      
      LoggerThread.log(threadName + ": 주문 생성 (주문ID: " + order.getId() + ")");
      SimulationStats.recordOrderCreated();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public void run() {
    LoggerThread.log(threadName + " 스레드 시작");
    
    while (!Thread.currentThread().isInterrupted()) {
      List<FoodItem> item = new ArrayList<>();
      try {
        if (threadId == 1) {
          // 샌드위치 단품
          String sandwichName = MenuData.getRandomSandwich();
          int sandwichPrice = MenuData.getMenuPrice(sandwichName);
          String vegetableType = MenuData.getRandomVegetable();
          String meatType = MenuData.getRandomMeat();
          String breadType = MenuData.getRandomBread();
          String cheeseType = MenuData.getRandomCheese();
          String sauceType = MenuData.getRandomSauce();

          item.add(new Sandwich(sandwichName, sandwichPrice, vegetableType, meatType, breadType, cheeseType, sauceType));
        } else if (threadId == 2) {
          // 샌드위치 + 사이드
          String sandwichName = MenuData.getRandomSandwich();
          int sandwichPrice = MenuData.getMenuPrice(sandwichName);
          String vegetableType = MenuData.getRandomVegetable();
          String meatType = MenuData.getRandomMeat();
          String breadType = MenuData.getRandomBread();
          String cheeseType = MenuData.getRandomCheese();
          String sauceType = MenuData.getRandomSauce();
          item.add(new Sandwich(sandwichName, sandwichPrice, vegetableType, meatType, breadType, cheeseType, sauceType));
          
          String sideName = MenuData.getRandomSide();
          int sidePrice = MenuData.getMenuPrice(sideName);
          item.add(new SideItem(sideName, sidePrice, sideName));
        } else if (threadId == 3) {
          // 샐러드 단품
          String saladName = MenuData.getRandomSalad();
          int saladPrice = MenuData.getMenuPrice(saladName);
          String vegetableType = MenuData.getRandomVegetable();
          String meatType = MenuData.getRandomMeat();
          String dressingType = MenuData.getRandomDressing();
          item.add(new Salad(saladName, saladPrice, vegetableType, meatType, dressingType));
        } else {
          // 샐러드 + 사이드
          String saladName = MenuData.getRandomSalad();
          int saladPrice = MenuData.getMenuPrice(saladName);
          String vegetableType = MenuData.getRandomVegetable();
          String meatType = MenuData.getRandomMeat();
          String dressingType = MenuData.getRandomDressing();
          item.add(new Salad(saladName, saladPrice, vegetableType, meatType, dressingType));
          
          String sideName = MenuData.getRandomSide();
          int sidePrice = MenuData.getMenuPrice(sideName);
          item.add(new SideItem(sideName, sidePrice, sideName));
        }
        
        // 주문 생성
        createOrder(item);
        
        // 1-3초 랜덤 대기 (다음 주문까지)
        int randomSeconds = 1 + (int)(Math.random() * 3);
        Thread.sleep(randomSeconds * 1000L);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        break;
      } catch (Exception e) {
        e.printStackTrace();
        // 다른 예외는 계속 실행
      }
    }
    
    LoggerThread.log(threadName + " 스레드 종료");
  }
}
