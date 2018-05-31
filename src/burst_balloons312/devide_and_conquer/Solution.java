package burst_balloons312.devide_and_conquer;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 使用 带备忘录的 自顶向下 的动态规划算法 解决 312. Burst Balloons 问题
 * 时间复杂度是指数级别 超时
 * @author zyh
 *
 */
public class Solution {
	public static void main(String[] args) {
		Solution solution = new Solution();
		
		int[] nums = new int[]{8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2};
		//int[] nums = new int[]{3, 1, 5, 8};
		
		Long startTime = new Date().getTime();
		
		System.out.println(solution.maxCoins(nums));
		
		Long endTime = new Date().getTime();
		System.out.println("ms: " + (endTime - startTime));
	}
	public int maxCoins(int[] nums) {
		Map<String, Integer> cache = new HashMap<String, Integer>();
		int result = maxCoinsRecursion(nums, cache);
		System.out.println("子问题个数： " + cache.size());
		// System.out.flush();
		return result;
	}
	/**
	 * 递归求解
	 * @param nums
	 * @return
	 */
	public int maxCoinsRecursion(final int[] nums, Map<String, Integer> cache) {
		int max = 0;
		if(nums.length == 1) {
			return nums[0];
		} else {
			for(int i = 0; i < nums.length; i++) {
				// nums[k - 1]
				int previous = 1;
				if((i - 1) >= 0) {
					previous = nums[i - 1];
				} 
				
				// nums[k + 1]
				int next = 1;
				if((i + 1) < nums.length) {
					next = nums[i + 1];
				}
				
				// nums - nums[k]
				int[] nums1 = new int[nums.length - 1];
				System.arraycopy(nums, 0, nums1, 0, i);
				System.arraycopy(nums, i + 1, nums1, i, nums.length - i - 1);
				
				// 计算子问题
				int tem = 0;
				Object result = cache.get(Arrays.toString(nums1));
				if(result == null) {
					tem = maxCoinsRecursion(nums1,cache);
					cache.put(Arrays.toString(nums1), tem);
				} else {
					tem = (Integer)result;
				}
				
				// max
				if((tem + previous * nums[i] * next) > max) {
					max = tem + previous * nums[i] * next;
				}
			}
			
			return max;
		}
	}
	
	private int[] testArrayCopy(int[] nums, int i) {
		int[] nums1 = new int[nums.length - 1];
		System.arraycopy(nums, 0, nums1, 0, i);
		System.arraycopy(nums, i + 1, nums1, i, nums.length - i - 1);
		return nums1;
	}
}
