package org.example.thread;

import java.util.ArrayList;
import java.util.List;
import org.example.data.MenuData;
import org.example.domain.Order;
import org.example.domain.SubwayStore;
import org.example.domain.item.Menu;
import org.example.domain.item.Salad;
import org.example.domain.item.Sandwich;
import org.example.domain.item.SideMenu;
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

  public void createOrder(List<Menu> items) {
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
      List<Menu> item = new ArrayList<>();
      try {
        switch (threadId) {
          case 1: {
            // 샌드위치 단품
            item.add(Sandwich.createRandom());
            break;
          }
          case 2: {
            // 샌드위치 + 사이드
            item.add(Sandwich.createRandom());

            String sideName = MenuData.getRandomSide();
            int sidePrice = MenuData.getMenuPrice(sideName);
            item.add(new SideMenu(sideName, sidePrice, sideName));
            break;
          }
          case 3: {
            // 샐러드 단품
            item.add(Salad.createRandom());
            break;
          }
          default: {
            // 샐러드 + 사이드
            item.add(Salad.createRandom());

            String sideName = MenuData.getRandomSide();
            int sidePrice = MenuData.getMenuPrice(sideName);
            item.add(new SideMenu(sideName, sidePrice, sideName));
            break;
          }
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
