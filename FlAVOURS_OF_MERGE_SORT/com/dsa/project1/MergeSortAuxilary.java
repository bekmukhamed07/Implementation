package com.dsa.project1;

/*
 * Program for (2)Class implementing mergesort with auxiliary array
 */
public class MergeSortAuxilary {

	public void mergeSort(int[] inputArr, int[] auxArray, int firstIndex,
			int lastIndex) {
		if (firstIndex < lastIndex) {
			int midIndex = (firstIndex + lastIndex) / 2;
			mergeSort(inputArr, auxArray, firstIndex, midIndex);
			mergeSort(inputArr, auxArray, midIndex + 1, lastIndex);
			sortAndMerge(inputArr, auxArray, firstIndex, midIndex, lastIndex);
		}
	}

	public void sortAndMerge(int[] inputArr, int[] auxArray, int firstIndex,
			int midIndex, int lastIndex) {
		for (int i = firstIndex; i <= lastIndex; i++) {
			auxArray[i] = inputArr[i];
		}

		int rightStartIndex = midIndex + 1;
		int startIndex = firstIndex;
		int currentIndex = firstIndex;

		while ((startIndex <= midIndex) && (rightStartIndex <= lastIndex)) {

			if (auxArray[startIndex] <= auxArray[rightStartIndex]) {
				inputArr[currentIndex++] = auxArray[startIndex++];

			} else if (auxArray[startIndex] > auxArray[rightStartIndex]) {
				inputArr[currentIndex++] = auxArray[rightStartIndex++];

			}
		}
		// To copy the other elements from the array
		while (startIndex <= midIndex) {
			inputArr[currentIndex++] = auxArray[startIndex++];

		}

	}

}
