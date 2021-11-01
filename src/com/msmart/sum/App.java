package com.msmart.sum;

import java.util.Random;

import com.msmart.sum.parallel.ParallelSum;
import com.msmart.sum.sequential.SequentialSum;

public class App {

	public static void main(String[] args) {

		Random random = new Random();
		SequentialSum sequentialSum = new SequentialSum();

		int numOfProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println(numOfProcessors);

		int[] nums = new int[100000000];

		for (int i = 0; i < nums.length; i++) {
			nums[i] = random.nextInt(100);
		}

		long start = System.currentTimeMillis();

		System.out.println(sequentialSum.sum(nums));

		System.out.println("Sequential sum takes: " + (System.currentTimeMillis() - start) + "ms");

		start = System.currentTimeMillis();

		ParallelSum parallelSum = new ParallelSum(numOfProcessors);

		System.out.println("Sum is: " + parallelSum.sum(nums));
		System.out.println("Parallel sum takes: " + (System.currentTimeMillis() - start) + "ms");
	}

}
