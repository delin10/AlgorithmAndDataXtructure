package org.delin.Algorithm.dynamicprogram;

import java.util.ArrayList;
import java.util.List;

/**
 * 最大区间和的问题--首先 判断每个元素的状态 0+ 或者1 last+ 所以只需要选择last和0之间的最大值即可
 * 
 * @author delin
 *
 */
public class ProblemMaxSumOfLimit {
	// 最大区间问题
	public static void sum(int[] nums) {
		int last = 0, ans = 0;
		for (int i = 0; i < nums.length; ++i) {
			last = Math.max(0, last) + nums[i];
			ans = Math.max(last, ans);
		}
		System.out.println("最大区间和为:" + ans);
	}

	// 最大区间问题
	public static void sumAndPrintSequence(int[] nums) {
		int last = 0, ans = 0;
		List<Integer> maxSeq = new ArrayList<>(), currentSeq = new ArrayList<>();
		for (int i = 0; i < nums.length; ++i) {
			last = Math.max(0, last) + nums[i];
			if (last > ans) {
				maxSeq = currentSeq;
			}
			if (last < 0) {
				currentSeq=new ArrayList<>();
			}
			currentSeq.add(nums[i]);
			ans = Math.max(last, ans);
		}
		
		maxSeq.forEach(x->{
			System.out.print(" "+x);
		});
		System.out.println("最大区间和为:" + ans);
	}

	// 包里破解法
	public static void sumLowest(int[] nums) {
		int max = Integer.MIN_VALUE, subMax = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; ++i) {
			subMax = nums[i];
			for (int j = i + 1; j < nums.length; ++j) {
				int current = nums[j] + subMax;
				if (current < 0) {
					break;
				}
				subMax = current;
			}
			max = Math.max(subMax, max);
		}
		System.out.println("最大区间和为:" + max);
	}

	public static void main(String[] args) {
		int[] nums = { -100, 1, 2, 3, -1, 100, 0, 10, -120, 90, 20, -300, 100, 2, 1 };
		sum(nums);

		sumLowest(nums);
		
		sumAndPrintSequence(nums);
	}
}
