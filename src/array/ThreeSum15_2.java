package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum15_2 {
	// a + b + c = target
	// The solution set must not contain duplicate triplets
	// O(nlogn) + O(n^2)
	// 使用了set去重
	public List<List<Integer>> threeSum(int[] nums, int target) {
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(nums == null || nums.length < 3) {
			return result;
		} else {
			// 排序 O(nlgn)
			List<Integer> srcList = new ArrayList<Integer>();
			for(int i : nums) {
				srcList.add(i);
			}
			Collections.sort(srcList);
			
			Set<List<Integer>> resultSet = new HashSet<List<Integer>>();// 使用set去重
			// a使用遍历，b和 c使用 twoSum O(n^2)
			for(int indexA = 0; indexA <= nums.length - 3; indexA++) {
				int a = srcList.get(indexA);
				twoSum(a, target - a, srcList, indexA + 1, resultSet);
				
				// 如果 indexA 指向的元素和下一个元素相等，则继续移动indexA以去重
				while((indexA + 1 <= nums.length - 3) && srcList.get(indexA + 1) == srcList.get(indexA)) {
					indexA++;
				}
			}
			result.addAll(resultSet);
			return result;
		}
	}
	
	/**
	 * 
	 * 计算 2Sum O(n)
	 * 
	 * @param target 目标和
	 * @param srcList 排序后的list
	 * @param start 计算 2Sum srcList 窗口为 [start, srcList.size() - 1]
	 * @param result 存入结果
	 */
	private void twoSum(int a, int target, List<Integer> srcList, int start, Set<List<Integer>> resultSet){
		// 双指针
		int pStart = start;
		int pEnd = srcList.size() - 1;
		
		while(pStart < pEnd) {
			if(srcList.get(pStart) + srcList.get(pEnd) == target) {// 和等于target，移动头尾指针
				List<Integer> temList = new ArrayList<Integer>();
				temList.add(a);
				temList.add(srcList.get(pStart));
				temList.add(srcList.get(pEnd));
				resultSet.add(temList);// 三元组加入result
				
				pStart++;
				// 如果 pStart 指向的元素和上一个元素相等，则继续移动pStart以去重
				while(pStart < srcList.size() && (srcList.get(pStart) == srcList.get(pStart - 1))){
					pStart++;
				}
				
				pEnd--;
				// 如果 pEnd 指向的元素和上一个元素相等，则继续移动pEnd以去重
				while(pEnd > 0 && (srcList.get(pEnd) == srcList.get(pEnd + 1))){
					pEnd--;
				}
				
			} else if(srcList.get(pStart) + srcList.get(pEnd) < target) {// 和小于target，移动头指针
				pStart++;
			} else {// 和大于target，移动尾指针
				pEnd--;
			}
		}
	}
	
	public static void main(String[] args) {
		/*int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
		ThreeSum15_2 threeSum = new ThreeSum15_2();
		System.out.println(threeSum.threeSum(nums, 0));*/
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Set<List<Integer>> resultSet = new HashSet<List<Integer>>();// 使用set去重
		
		List<Integer> temList = new ArrayList<Integer>();
		temList.add(1);
		temList.add(2);
		temList.add(1);
		resultSet.add(temList);
		
		List<Integer> temList1 = new ArrayList<Integer>();
		temList1.add(1);
		temList1.add(2);
		temList1.add(1);
		resultSet.add(temList1);
		
		List<Integer> temList2 = new ArrayList<Integer>();
		temList2.add(1);
		temList2.add(1);
		temList2.add(2);
		resultSet.add(temList2);
		
		result.addAll(resultSet);
		System.out.println(result);
	}
}
