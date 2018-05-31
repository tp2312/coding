package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 3};
		
		Permutations p = new Permutations();
		List<List<Integer>> result = p.permute(nums);
		System.out.println(result);
	}
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for(int i = 0; i < nums.length; i++) {
			List<Integer> tem = new ArrayList<Integer>();
			tem.add(nums[i]);
			backtracking(tem, nums, result);
		}
		return result;
	}
	
	/**
	 * 递归的回溯方法
	 * @param tem 当前部分解中已存在元素
	 * @param nums 原始数组
	 * @param result 解的集合
	 */
	private void backtracking(List<Integer> tem, final int[] nums, List<List<Integer>> result) {
		//System.out.println(tem);
		if(tem.size() == nums.length) {
			// 触底 
			result.add(tem);
			return;
		} else {
			// 继续所搜
			for(Integer element : nums) {
				if(!tem.contains(element)) {
					List<Integer> tem1 = new ArrayList<Integer>(tem);
					tem1.add(element);
					backtracking(tem1, nums, result);
				}
			}
		}
	}
}
