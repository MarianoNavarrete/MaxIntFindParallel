package find;

import java.util.concurrent.RecursiveTask;

import app.App;

/**
 * The Class MaxFindParallel.
 * 
 * @author Mariano Navarrete
 */
public class MaxFindParallel extends RecursiveTask<Integer> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8787303405255861090L;

	/** The array of the numbers to find the max value */
	private int[] nums;

	/** The low index. */
	private int lowIndex;

	/** The high index. */
	private int highIndex;

	/**
	 * Instantiates a new max find parallel.
	 *
	 * @param nums      the nums
	 * @param lowIndex  the low index
	 * @param highIndex the high index
	 */
	public MaxFindParallel(int[] nums, int lowIndex, int highIndex) {
		this.nums = nums;
		this.lowIndex = lowIndex;
		this.highIndex = highIndex;
	}

	/**
	 *
	 * @return  the maximum value of the array in parallel form
	 */
	@Override
	protected Integer compute() {
		if (highIndex - lowIndex < App.THREASHHOLD) {
			return sequencialMaxFind();
		} else {
			int middleIndex = (highIndex + lowIndex) / 2;
			MaxFindParallel task1 = new MaxFindParallel(nums, lowIndex, middleIndex);
			MaxFindParallel task2 = new MaxFindParallel(nums, middleIndex + 1, highIndex);

			invokeAll(task1, task2);

			return Math.max(task1.join(), task2.join());
		}
	}

	/**
	 * Sequencial max find.
	 *
	 * @return the integer
	 */
	private Integer sequencialMaxFind() {
		int max = nums[lowIndex];
		for (int i = lowIndex + 1; i < highIndex; i++) {
			if (nums[i] > max) {
				max = nums[i];
			}
		}
		return max;
	}

}
