package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 回溯法解决 Combination Sum
 * 
 * @author zyh
 *
 */
public class CombinationSum2 {
	public static void main(String[] args) {
		int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
		int target = 8;
		
		CombinationSum2 cs = new CombinationSum2();
		List<List<Integer>> result = cs.combinationSum(candidates, target);
		
		System.out.println(result);
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Set<List<Integer>> result = new HashSet<List<Integer>>();

		for (int i = 0; i < candidates.length; i++) {
			ArrayList<Integer> tem = new ArrayList<Integer>();
			tem.add(candidates[i]);
			backtracking(i, tem, candidates[i], candidates, target, result);
		}
		
		List<List<Integer>> result1 = new ArrayList<List<Integer>>(result);
		return result1;
	}

	/**
	 * 递归的回溯方法
	 * 
	 * @param index
	 *            当前要处理元素位置
	 * @param tem
	 *            当前部分解
	 * @param temSum
	 *            当前部分解只和
	 * @param candidates
	 *            C
	 * @param target
	 *            T
	 * @param result
	 *            solution
	 */
	private void backtracking(int index, List<Integer> tem, int temSum, final int[] candidates, final int target,
			Set<List<Integer>> result) {
		//System.out.println(tem);
		if (temSum > target) {
			// 当前部分解的和已经大于目标，则剪枝
			return;
		} else if (temSum == target) {
			// 当前部分解的和等于目标，则当前部分解为一个解，该子树上不会再有解，
			// 将当前解加入解集，然后剪枝
			Collections.sort(tem);
			result.add(tem);
			return;
		} else {
			// 当前部分解的和仍小于目标err
			// 递归调用
			for (int i = index + 1, j = 1; i < candidates.length; i++, j++) {
				int index1 = index + j;
				List<Integer> tem1 = new ArrayList<Integer>(tem);
				tem1.add(candidates[i]);
				int temSum1 = temSum + candidates[i];
				// System.out.println(tem1);
				backtracking(index1, tem1, temSum1, candidates, target, result);
			}

		}
	}
}
