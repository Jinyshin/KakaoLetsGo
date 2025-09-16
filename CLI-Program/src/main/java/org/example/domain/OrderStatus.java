package org.example.domain;

public enum OrderStatus {
    PENDING("접수"),
    PREPARING("조리 중"),
    COMPLETED("완료"),
    CANCELLED("취소");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}