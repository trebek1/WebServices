package com.springcourse.spring.basics.springstart;

public class BinarySearchImpl {
	// binary search expects a sorted array 
	// then search the array
	// then return the result 
	
	private SortAlgorithm sortAlgorithm;
	
	
	
	public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
		this.sortAlgorithm = sortAlgorithm;
	}



	public int binarySearch(int[] numbers, int numberToSearchFor) {
		// since this bubble sort algo is moved outside of BinarySearchImpl if there 
		// is a change to bubble sort we dont have to change BinarySearchImpl
		// we can change the BubbleSort class now 
		// How do we change the algorithm though? 
		// BubbleSortAlgorithm bubbleSortAlgorithm = new BubbleSortAlgorithm();
		
		int[] sortedNumbers = sortAlgorithm.sort(numbers);
		System.out.println(sortAlgorithm);
		
		// implement sorting
		// suppose we sort with quickSort 
		// BinarySearchImpl is TIGHTLY COUPLED to the quickSort algorithm
		// means -- if we want to change the logic of the algorithm we need to change BinarySearchImpl
		// How can you implement this so that we can use BubbleSort in some cases but QuickSort in others? 
		// implement search 
		// return index of result 
		return 1;
	}

}
