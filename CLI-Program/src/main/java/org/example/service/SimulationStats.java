package org.example.service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SimulationStats {
    
    private static final AtomicInteger totalOrders = new AtomicInteger(0);
    private static final AtomicInteger completedOrders = new AtomicInteger(0);
    private static final AtomicLong totalProcessingTime = new AtomicLong(0);
    private static final long startTime = System.currentTimeMillis();
    
    public static void recordOrderCreated() {
        totalOrders.incrementAndGet();
    }
    
    public static void recordOrderCompleted(long processingTimeMs) {
        completedOrders.incrementAndGet();
        totalProcessingTime.addAndGet(processingTimeMs);
    }
    
    public static void printStats() {
        long runtimeMs = System.currentTimeMillis() - startTime;
        long runtimeSeconds = runtimeMs / 1000;
        
        int total = totalOrders.get();
        int completed = completedOrders.get();
        double avgProcessingTime = completed > 0 ? 
            (double) totalProcessingTime.get() / completed / 1000.0 : 0.0;
        
        System.out.println("\n====== 시뮬레이션 결과 ======");
        System.out.println("총 주문 수: " + total + "개");
        System.out.println("완료된 주문: " + completed + "개");
        System.out.printf("평균 제조 시간: %.1f초%n", avgProcessingTime);
        System.out.println("실행 시간: " + runtimeSeconds + "초");
    }
}