package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯法解决 Combination Sum
 * 
 * @author zyh
 *
 */
public class CombinationSum {
	public static void main(String[] args) {
		int[] candidates = new int[]{2, 3, 6, 7};
		
		int target = 7;
		
		CombinationSum cs = new CombinationSum();
		List<List<Integer>> result = cs.combinationSum(candidates, target);
		
		System.out.println(result);
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		for (int i = 0; i < candidates.length; i++) {
			ArrayList<Integer> tem = new ArrayList<Integer>();
			tem.add(candidates[i]);
			backtracking(i, tem, candidates[i], candidates, target, result);
		}
		return result;
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
			List<List<Integer>> result) {
		// System.out.println(tem);
		if (temSum > target) {
			// 当前部分解的和已经大于目标，则剪枝
			return;
		} else if (temSum == target) {
			// 当前部分解的和等于目标，则当前部分解为一个解，该子树上不会再有解，
			// 将当前解加入解集，然后剪枝
			result.add(tem);
			return;
		} else {
			// 当前部分解的和仍小于目标
			// 递归调用
			for (int i = index , j = 0; i < candidates.length; i++, j++) {
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
