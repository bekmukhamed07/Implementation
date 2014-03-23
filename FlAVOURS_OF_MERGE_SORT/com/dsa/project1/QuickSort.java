package com.dsa.project1;

/*
 * Program implementing the traditional Quick Sort
 * 
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] inArr = { 5, 4, 7, 9, 3, 2 };
		new QuickSort().quickSort(inArr, 0, (inArr.length) - 1);
		for (int j = 0; j < inArr.length; j++) {
			System.out.print(" " + inArr[j]);
		}
	}

	public void quickSort(int[] inArr, int low, int high) {

		int nPivotResult = getPivotandSort(inArr, low, high);
		if (low < nPivotResult - 1) {
			quickSort(inArr, low, nPivotResult - 1);
		}
		if (nPivotResult < high) {
			quickSort(inArr, nPivotResult, high);
		}
	}

	public int getPivotandSort(int[] inArr, int low, int high) {
		int nPivot = inArr[(low + high) / 2];
		while (low <= high) {
			while (inArr[low] < nPivot)
				low++;
			while (inArr[high] > nPivot)
				high--;
			if (low <= high) {
				swapArrayElement(inArr, low, high);
				low++;
				high--;
			}
		}
		return low;
	}

	public void swapArrayElement(int[] inArr, int source, int dest) {
		int temp;
		temp = inArr[source];
		inArr[source] = inArr[dest];
		inArr[dest] = temp;
	}

}
