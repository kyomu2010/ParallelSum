package com.msmart.sum.parallel;

public class ParallelSum {

	private ParallelWorker[] sums;

	private int numOfThreads;

	public ParallelSum(int numOfThreads) {
		this.numOfThreads = numOfThreads;
		this.sums = new ParallelWorker[numOfThreads];
	}

	public int sum(int[] nums) {

		// partition array into subarrays to match number of threads
		int steps = (int) Math.ceil(nums.length * 1.0 / numOfThreads);

		for (int i = 0; i < numOfThreads; i++) {
			sums[i] = new ParallelWorker(nums, 1 * steps, (i + 1) * steps);
			sums[i].start();
		}

		try {
			for (ParallelWorker worker : sums) {
				// wait for each worker to finish job
				worker.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int total = 0;

		for (ParallelWorker worker : sums) {
			total += worker.getPartialSum();
		}

		return total;
	}
}
