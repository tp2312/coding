package dp;

import java.util.Arrays;
/**
 * 最大连续子序列和
 * 要考虑数组元素都是负数，则返回其中最大的元素值
 * @author zyh
 *
 */
public class MaximumSubarray {
	public static void main(String[] args) {
		MaximumSubarray max = new MaximumSubarray();
		System.out.println(max.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
	}
	
	public int maxSubArray(int[] nums) {
		int[] assit = new int[nums.length];
		
		for(int i = 0; i < nums.length; i++) {
			// 第一个元素
			if(i == 0) {
				if(nums[0] > 0) {
					assit[0] = nums[0];
				}
			} else {
				if(assit[i - 1] > 0) {
					int tem = assit[i - 1] + nums[i];
					if(tem > 0) {
						assit[i] = tem;
					} else {
						assit[i] = 0;
					}
				} else {
					if(nums[i] < 0) {
						assit[i] = 0;
					} else {
						assit[i] = nums[i];
					}
				}
			}
		}
		
		// 再次遍历数组，取得最大值
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < assit.length; i++) {
			if(assit[i] > max) {
				max = assit[i];
			}
		}
		
		if(max == 0) {
			// 如果最大值为0，那么说明数组中每个元素都为负
			int min = Integer.MIN_VALUE;
			for(int i = 0; i < nums.length; i++) {
				if(nums[i] > min) {
					min = nums[i];
				}
			}
			//System.out.println(Arrays.toString(assit));
			return min;
		} else {
			//System.out.println(Arrays.toString(assit));
			return max;
		}
	}
}
