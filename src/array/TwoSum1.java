package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * 给定一个int数组，和目标值 找出数组中两个元素的和为目标值，结果要去重
 * 
 * @author: zyh
 * @date: 2018年5月31日 下午12:21:54
 *
 */
public class TwoSum1 {
	/**
	 * 
	 * 解法1：排序+双指针 O(nlogn)
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> twoSum1(int[] nums, int target) {
		int length = nums.length;
		// 排序
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < length; i++) {
			list.add(nums[i]);
		}
		Collections.sort(list);
		
		// 双指针
		int start = 0;
		int end = length - 1;
		
		// 结果
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		
		// 双指针移动
		while(start < end) {
			if(list.get(start) + list.get(end) == target) {// 等于目标值
				ArrayList<Integer> temList = new ArrayList<Integer>();
				temList.add(list.get(start));
				temList.add(list.get(end));
				lists.add(temList);
				// 移动头尾指针，去重
				start++;
				while(list.get(start) == list.get(start - 1)) {
					start++;
				}
				
				end--;
				while(list.get(end) == list.get(end + 1)) {
					end--;
				}
			} else if(list.get(start) + list.get(end) < target) {// 比目标值小，移动头指针
				start++;
			} else {// 比目标值大，移动尾指针
				end--;
			}
		}
		return lists;
	}
	
	/**
	 * 
	 * 解法2：哈希表
	 * 时间复杂度：O(n)
	 * 空间复杂度：O(n)
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> twoSum2(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		// 结果
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		
		for(int i = 0; i < nums.length; i++) {
			// 包含 target - nums[i]
			if(map.containsKey(target - nums[i])) {
				ArrayList<Integer> temList = new ArrayList<Integer>();
				temList.add(nums[i]);
				temList.add(target - nums[i]);
				lists.add(temList);
			} 
			
			// 放入 hash table
			Integer value = map.get(nums[i]);
			if(value == null) {
				map.put(nums[i], 1);
			} else {
				map.put(nums[i], value + 1);
			}
		}
		return lists;
	}
	
	public static void main(String[] args) {
		int[] ints = new int[]{-3, -2, -2, -1, 0, 0, 0, 2, 3};
		TwoSum1 twoSum = new TwoSum1();
		System.out.println(twoSum.twoSum1(ints, 0));
		System.out.println(twoSum.twoSum2(ints, 0));
	}
}
