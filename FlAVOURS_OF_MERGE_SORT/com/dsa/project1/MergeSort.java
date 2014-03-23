package com.dsa.project1;

/*
 * Program for (1)Class implementing mergesort with dynamic array
 */
public class MergeSort {

	//Main method to test the class independently
	public static void main(String[] args) {

		int[] inArr = { 5, 4, 7, 9, 3, 2 };
		new MergeSort().mergeSort(inArr, 0, inArr.length - 1);

		for (int j = 0; j < inArr.length; j++) {
			System.out.print(" " + inArr[j]);
		}
	}

	public void mergeSort(int[] inputArr, int firstIndex, int lastIndex) {
		if (firstIndex < lastIndex) {
			int midIndex = (firstIndex + lastIndex) / 2;
			mergeSort(inputArr, firstIndex, midIndex);
			mergeSort(inputArr, midIndex + 1, lastIndex);
			sortAndMerge(inputArr, firstIndex, midIndex, lastIndex);
		}
	}

	public void sortAndMerge(int[] inputArr, int firstIndex, int midIndex,
			int lastIndex) {
		int[] auxArray = new int[(lastIndex - firstIndex) + 1];

		int m = 0;
		for (int l = firstIndex; l <= lastIndex; l++) {
			auxArray[m++] = inputArr[l];

		}
		int middle = midIndex - firstIndex;
		int high = lastIndex - firstIndex;
		int currentIndex = firstIndex;

		int rightStartIndex = middle + 1;
		int startIndex = 0;

		while ((startIndex <= middle) && (rightStartIndex <= high)) {

			if (auxArray[startIndex] <= auxArray[rightStartIndex]) {
				inputArr[currentIndex++] = auxArray[startIndex++];

			} else if (auxArray[startIndex] > auxArray[rightStartIndex]) {
				inputArr[currentIndex++] = auxArray[rightStartIndex++];

			}
		}
		while (startIndex <= middle) {
			inputArr[currentIndex++] = auxArray[startIndex++];

		}

	}

}
