package com.harvey;

public class DpStudy {

	public static void main(String[] args) {
		int[] nums = {-1};
		int length = nums.length;
		int[] memory = new int[length];
		int max = nums[0];
		for (int i = 0; i < length; i++) {
			memory[i] = f(nums, i, memory);
			if(memory[i]>max) {
				max= memory[i];
			}
		}
		System.out.println(max);
	}

	public static int f(int[] nums, int position, int[] memory) {
		if (position == 0) {
			return nums[0];
		} else {
			if (memory[position-1] == 0) {
				return nums[position] + f(nums, position - 1, memory);
			} else {
				if(memory[position-1]>0) {
					return nums[position] + memory[position-1];
				}else {
					return nums[position];
				}
				
			}
		}
	}
}
