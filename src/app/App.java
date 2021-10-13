package app;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import find.MaxFindParallel;

/**
 * The Class App.
 * @author Mariano Navarrete
 */
public class App {
	
	/** The threashhold. */
	public static int THREASHHOLD = 0;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		int[] nums = initialiceNums();
		THREASHHOLD = nums.length / Runtime.getRuntime().availableProcessors();
		MaxFindParallel maxFindParallel = new MaxFindParallel(nums, 0, nums.length);
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		long start = System.currentTimeMillis();
		System.out.println("Max " + forkJoinPool.invoke(maxFindParallel));
		System.out.println("time " + (System.currentTimeMillis() - start));
		System.out.println("Max " + sequencialImpl(nums));
		System.out.println("time " + (System.currentTimeMillis() - start));
	}

	/**
	 * Initialice nums.
	 *
	 * @return the int[]
	 */
	private static int[] initialiceNums() {
		Random random = new Random();
		int[] nums = new int[1000000000];
		for (int i = 0; i < 1000000000; i++) {
			nums[i] = random.nextInt(1000000001);
		}
		return nums;
	}

	/**
	 * Sequencial impl.
	 *
	 * @param nums the nums
	 * @return the int
	 */
	private static int sequencialImpl(int[] nums) {
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > max) {
				max = nums[i];
			}
		}
		return max;
	}
}
