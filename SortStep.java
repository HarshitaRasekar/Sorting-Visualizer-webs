package com.example.model;

public class SortStep {
	private int[] array;
    private int index1;
    private int index2;

    public SortStep(int[] array, int index1, int index2) {
        this.array = array;
        this.index1 = index1;
        this.index2 = index2;
    }

    public int[] getArray() {
        return array;
    }

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }
    public int[] getHighlights() {
        return new int[] { index1, index2 };
    }
}
