package com.dsa.project1;

/*
 * Program for (3)Class implementing mergesort with by merging alternately
 */
public class MergeSortAlternateMerge {

	public static void mergeSort(int[] inputArray, int[] auxArray,
			int low, int high) {
		if (high <= low) {
			return;
		}
		int mid = low + (high - low) / 2;
		
		mergeSort(auxArray, inputArray, low, mid);
		mergeSort(auxArray, inputArray, mid + 1, high);
		merge(inputArray, auxArray, low, mid, high);
	}

	public static void merge(int[] source, int[] destination, int low,
			int middle, int high) {

		int firstIndex = low;
		int secondIndex = middle + 1;
		for (int destIndex = low; destIndex <= high; destIndex++) {
			if (firstIndex > middle) {
				destination[destIndex] = source[secondIndex++];
			} else if (secondIndex > high) {
				destination[destIndex] = source[firstIndex++];
			} else if (source[secondIndex] < source[firstIndex]) {
				destination[destIndex] = source[secondIndex++];
			} else {
				destination[destIndex] = source[firstIndex++];
			}
		}

	}

}
