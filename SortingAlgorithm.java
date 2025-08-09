package com.example.sort;

import java.util.ArrayList;
import java.util.List;

import com.example.model.SortStep;

public class SortingAlgorithm {
	public static List<SortStep> getBubbleSortSteps(int[] array) {
        List<SortStep> steps = new ArrayList<>();

        int n = array.length;
        int[] arr = array.clone();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Save current state before swap
                steps.add(new SortStep(arr.clone(), j, j + 1));

                if (arr[j] > arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // Save state after swap
                    steps.add(new SortStep(arr.clone(), j, j + 1));
                }
            }
        }

        return steps;
    }
	public static List<SortStep> getSelectionSortSteps(int[] array) {
	    List<SortStep> steps = new ArrayList<>();
	    int[] arr = array.clone();
	    int n = arr.length;

	    for (int i = 0; i < n - 1; i++) {
	        int minIndex = i;
	        for (int j = i + 1; j < n; j++) {
	            steps.add(new SortStep(arr.clone(), minIndex, j));
	            if (arr[j] < arr[minIndex]) {
	                minIndex = j;
	            }
	        }
	        if (minIndex != i) {
	            int temp = arr[i];
	            arr[i] = arr[minIndex];
	            arr[minIndex] = temp;
	            steps.add(new SortStep(arr.clone(), i, minIndex));
	        }
	    }
	    return steps;
	}

	public static List<SortStep> getInsertionSortSteps(int[] array) {
	    List<SortStep> steps = new ArrayList<>();
	    int[] arr = array.clone();
	    int n = arr.length;

	    for (int i = 1; i < n; i++) {
	        int key = arr[i];
	        int j = i - 1;

	        while (j >= 0 && arr[j] > key) {
	            arr[j + 1] = arr[j];
	            j--;
	            steps.add(new SortStep(arr.clone(), j + 1, j + 2));
	        }
	        arr[j + 1] = key;
	        steps.add(new SortStep(arr.clone(), j + 1, i));
	    }
	    return steps;
	}
}

