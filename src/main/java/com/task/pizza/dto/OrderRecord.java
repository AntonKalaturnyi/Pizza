package com.task.pizza.dto;

public class OrderRecord {

    public OrderRecord() {
    }

    public OrderRecord(long itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    private long itemId;

    private int amount;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderRecord{" +
                "itemId=" + itemId +
                ", amount=" + amount +
                '}';
    }
}
