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
}
