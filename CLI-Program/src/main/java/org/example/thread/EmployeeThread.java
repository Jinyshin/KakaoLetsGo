package org.example.thread;

import org.example.domain.Order;
import org.example.domain.SubwayStore;
import org.example.service.SimulationStats;

public class EmployeeThread implements Runnable {

  private final String threadName;
  private final SubwayStore store;

  public EmployeeThread(String threadName, SubwayStore store) {
    this.threadName = threadName;
    this.store = store;
  }

  @Override
  public void run() {
    LoggerThread.log(threadName + " 스레드 시작");
    
    while (!Thread.currentThread().isInterrupted()) {
      try {
        Order order = store.getNextOrder();

        order.setStatus("IN_PROGRESS");
        LoggerThread.log(threadName + ": 주문 제조 시작 (주문ID: " + order.getId() + ")");
        
        // 1-5초 랜덤 대기 (제조 시간)
        long startTime = System.currentTimeMillis();
        int randomSeconds = 1 + (int)(Math.random() * 5);
        Thread.sleep(randomSeconds * 1000L);
        long endTime = System.currentTimeMillis();
        
        order.setStatus("COMPLETED");

        LoggerThread.log(threadName + ": 주문 제조 완료 (주문ID: " + order.getId() + ")");
        long processingTime = endTime - startTime;
        SimulationStats.recordOrderCompleted(processingTime);
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
