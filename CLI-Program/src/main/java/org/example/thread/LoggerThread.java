package org.example.thread;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LoggerThread implements Runnable {

  private static final BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
  private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

  private static String formatLogMessage(String message) {
    String timestamp = LocalTime.now().format(timeFormatter);
    return "[" + timestamp + "] " + message;
  }

  public static void log(String message) {
    try {
      String formattedMessage = formatLogMessage(message);
      logQueue.put(formattedMessage);
    } catch (InterruptedException e) {
      // interrupt 상황에서도 로그를 넣기 위해 offer 사용
      String formattedMessage = formatLogMessage(message);
      boolean success = logQueue.offer(formattedMessage);
      if (!success) {
        System.err.println("Failed to add log message: " + formattedMessage);
      }
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public void run() {
    System.out.println("====== Subway Store Simulation ======");
    log("시뮬레이션 시작");

    while (!Thread.currentThread().isInterrupted()) {
      try {
        String logMessage = logQueue.take();
        System.out.println(logMessage);
      } catch (InterruptedException e) {
        log("시뮬레이션 종료");
        while (!logQueue.isEmpty()) {
          try {
            String remainingMessage = logQueue.take();
            System.out.println(remainingMessage);
            System.out.flush();
          } catch (InterruptedException ex) {
            break;
          }
        }
        Thread.currentThread().interrupt();
        break;
      }
    }
  }
}
