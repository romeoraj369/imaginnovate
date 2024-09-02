package com.imaginnovate.utils;

public class EntityCounter {

	private static int insertedCount = 0;
	private static int updatedCount = 0;

	public static synchronized void incrementInserted() {
		insertedCount++;
	}

	public static synchronized void incrementUpdated() {
		updatedCount++;
	}

	public static synchronized int getInsertedCount() {
		return insertedCount;
	}

	public static synchronized int getUpdatedCount() {
		return updatedCount;
	}

	public static synchronized void resetCounts() {
		insertedCount = 0;
		updatedCount = 0;
	}
}
