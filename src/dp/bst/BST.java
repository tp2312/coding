package dp.bst;

import java.util.Arrays;
/**
 * 递归+存表
 * 
 * 二叉排序树
 *  根节点的值 大于等于 左子树 上任意的值
 * 	有子树节点 上任意节点的值 大于等于 根节点的值 
 * @author zyh
 *
 */
public class BST {
	
	public static void main(String[] args) {
		BST bst = new BST();
		System.out.println(bst.numTrees(4));
		System.out.println(Arrays.toString(bst.assit));
	}
	// 计算出的中间结果存表
	int[] assit;
	
	public int numTrees(int n) {
		if(n == 0) {
			return 0;
		} else if(n ==1) {
			return 1;
		} else if(n == 2) {
			return 2;
		} else if(n ==3) {
			return 5;
		} else{
			assit = new int[n + 1];
			// 初始化
			assit[0] = 1;
			assit[1] = 1;
			assit[2] = 2;
			assit[3] = 5;
			
			return recursivlyUBST(n);
		}
	}
	
	private int recursivlyUBST(int n){
		// 先查看表中是否已经缓存
		int goal = assit[n];
		if(0 == goal) {
			// 没有缓存就递归地计算
			for(int i = 1; i <= n; i++) {
				goal += recursivlyUBST(i - 1) * recursivlyUBST(n - i);
			}
			// 计算完成后将结果缓存到表中
			assit[n] = goal;
		} 
		return goal;
	}
}
