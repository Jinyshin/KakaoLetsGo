package org.example.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuData {
    
    // 메뉴 타입들
    public static final List<String> SANDWICH_TYPES = Arrays.asList(
        "이탈리안 비엠티",
        "터키 베이컨 아보카도"
    );
    
    public static final List<String> SALAD_TYPES = Arrays.asList(
        "치킨 시저 샐러드",
        "터키 샐러드"
    );
    
    public static final List<String> SIDE_TYPES = Arrays.asList(
        "음료",
        "쿠키", 
        "수프"
    );
    
    // 메뉴별 고정 가격 (원)
    public static final Map<String, Integer> MENU_PRICES = new HashMap<String, Integer>() {{
        // 샌드위치 가격
        put("이탈리안 비엠티", 8500);
        put("터키 베이컨 아보카도", 9000);
        
        // 샐러드 가격
        put("치킨 시저 샐러드", 7500);
        put("터키 샐러드", 7000);
        
        // 사이드 메뉴 가격
        put("음료", 2000);
        put("쿠키", 1500);
        put("수프", 3000);
    }};
    
    // 재료들
    public static final List<String> VEGETABLES = Arrays.asList(
        "양상추",
        "토마토",
        "오이"
    );
    
    public static final List<String> MEATS = Arrays.asList(
        "햄",
        "치킨",
        "터키",
        "베이컨"
    );
    
    public static final List<String> BREADS = Arrays.asList(
        "화이트",
        "위트",
        "허니오트"
    );
    
    public static final List<String> SAUCES = Arrays.asList(
        "랜치",
        "스위트어니언",
        "치폴레"
    );
    
    public static final List<String> CHEESES = Arrays.asList(
        "아메리칸",
        "모짜렐라",
        "체다"
    );
    
    public static final List<String> DRESSINGS = Arrays.asList(
        "올리브오일",
        "발사믹"
    );
    
    // 랜덤 선택을 위한 메서드
    public static String getRandomSandwich() {
        return SANDWICH_TYPES.get((int) (Math.random() * SANDWICH_TYPES.size()));
    }
    
    public static String getRandomSalad() {
        return SALAD_TYPES.get((int) (Math.random() * SALAD_TYPES.size()));
    }
    
    public static String getRandomSide() {
        return SIDE_TYPES.get((int) (Math.random() * SIDE_TYPES.size()));
    }
    
    public static String getRandomVegetable() {
        return VEGETABLES.get((int) (Math.random() * VEGETABLES.size()));
    }
    
    public static String getRandomMeat() {
        return MEATS.get((int) (Math.random() * MEATS.size()));
    }
    
    public static String getRandomBread() {
        return BREADS.get((int) (Math.random() * BREADS.size()));
    }
    
    public static String getRandomSauce() {
        return SAUCES.get((int) (Math.random() * SAUCES.size()));
    }
    
    public static String getRandomCheese() {
        return CHEESES.get((int) (Math.random() * CHEESES.size()));
    }
    
    public static String getRandomDressing() {
        return DRESSINGS.get((int) (Math.random() * DRESSINGS.size()));
    }
    
    // 가격 조회 메서드
    public static int getMenuPrice(String menuName) {
        return MENU_PRICES.getOrDefault(menuName, 0);
    }
}