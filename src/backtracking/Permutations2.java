package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations2 {
	public static void main(String[] args) {
		int[] nums = new int[]{-1,2,-1,2,1,-1,2,1};
		
		Permutations2 p2 = new Permutations2();
		List<List<Integer>> result = p2.permute(nums);
		System.out.println(result);
	}
	public List<List<Integer>> permute(int[] nums) {
		Set<List<Integer>> result = new HashSet<List<Integer>>();
		
		for(int i = 0; i < nums.length; i++) {
			List<Integer> tem = new ArrayList<Integer>();
			tem.add(nums[i]);
			Set<Integer> indexs = new HashSet<Integer>();
			indexs.add(i);
			backtracking(indexs, tem, nums, result);
		}
		List<List<Integer>> result1 = new ArrayList<List<Integer>>(result);
		return result1;
	}
	
	/**
	 * 递归的回溯方法
	 * @param indexs 当前部分解在数组中对应索引的集合
	 * @param tem 当前部分解中已存在元素
	 * @param nums 原始数组
	 * @param result 解的集合
	 */
	private void backtracking(Set<Integer> indexs, List<Integer> tem, final int[] nums, Set<List<Integer>> result) {
		System.out.println(tem);
		if(tem.size() == nums.length) {
			// 触底 
			result.add(tem);
			return;
		} else {
			// 继续所搜
			for(int i = 0; i < nums.length; i++) {
				if(!indexs.contains(i)) {
					Set<Integer> indexs1 = new HashSet<Integer>(indexs);
					indexs1.add(i);
					List<Integer> tem1 = new ArrayList<Integer>(tem);
					tem1.add(nums[i]);
					backtracking(indexs1, tem1, nums, result);
				}
			}
		}
	}
}
