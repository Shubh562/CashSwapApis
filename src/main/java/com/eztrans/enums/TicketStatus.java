package com.eztrans.enums;

public enum TicketStatus {
	EXPIRED(0),
	ACTIVE(1),
	QR_GENERATED(2),
	QR_SCANNED(3),
	CASH_DISPENSED(4);
	
	private int value;

    private TicketStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
