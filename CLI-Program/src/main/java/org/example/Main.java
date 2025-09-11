package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.example.domain.SubwayStore;
import org.example.service.SimulationStats;
import org.example.thread.CustomerThread;
import org.example.thread.EmployeeThread;
import org.example.thread.LoggerThread;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService customerPool = Executors.newFixedThreadPool(4);
    ExecutorService employeePool = Executors.newFixedThreadPool(2);
    ExecutorService logger = Executors.newSingleThreadExecutor();
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    SubwayStore store = new SubwayStore();
    CountDownLatch latch = new CountDownLatch(1);

    logger.submit(new LoggerThread());
    Thread.sleep(100);

    // 모든 스레드에 SubwayStore 객체 전달
    for (int i = 1; i <= 4; i++) {
      customerPool.submit(new CustomerThread(i, store));
    }
    for (int i = 1; i <= 2; i++) {
      employeePool.submit(new EmployeeThread("직원" + i, store));
    }

    // 30초 시뮬레이션
    scheduler.schedule(() -> {
      customerPool.shutdownNow();
      employeePool.shutdownNow();
      // 스레드 종료 로그 처리 시간을 위해 잠깐 대기
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }

      logger.shutdownNow();
      latch.countDown();
    }, 30, TimeUnit.SECONDS);
    latch.await();

    // 통계 출력
    SimulationStats.printStats();

    scheduler.shutdown();
  }
}