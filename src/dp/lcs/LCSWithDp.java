package dp.lcs;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用动态规划自下而上地解决LCS 时间复杂度(m * n)
 * @author zyh
 *
 */
public class LCSWithDp {
	private int[][] c;
	private List<Integer> iIndex = new ArrayList<Integer>();
	private List<Integer> jIndex = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		LCSWithDp lcs = new LCSWithDp();
		lcs.sulosion("ABCBDAB", "BDCABA");
	}
	public void sulosion(String str1, String str2){
		char[] X = str1.toCharArray();
		char[] Y = str2.toCharArray();
		
		// 数组声明两个维度都大1便于初始化
		c = new int[X.length + 1][Y.length + 1];
		
		// 按照从上到下，从左到右的顺序计算 c
		for(int i = 1; i <= X.length; i++){ // 行
			for(int j = 1; j <= Y.length; j++) { // 列
				if(X[i - 1] == Y[j - 1]) {
					c[i][j] = 1 + c[i -1][j - 1];
				} else{
					c[i][j] = max(c[i - 1][j], c[i][j - 1]);
				}
			}
		}
		
		// 打印 C
		System.out.println("c[i][j]:");
		for(int i = 0; i <= X.length; i++){
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for(int j = 0; j <= Y.length; j++) {
				sb.append(" " + c[i][j]);
			}
			sb.append(" ]");
			System.out.println(sb.toString());
		}
		
		// 寻找LCS在原序列中的索引
		int i = X.length;
		int j = Y.length;
		while(c[i][j] != 0) {
			int[] temp = next(i, j);
			if(temp[0] == c[i][j] - 1) {
				iIndex.add(i);
				jIndex.add(j);
			}
			i = temp[1];
			j = temp[2];
		}
		
		// 打印LCS在原序列中的索引
		System.out.println("LCS索引：");
		System.out.println(iIndex);
		System.out.println(jIndex);
	}
	
	private int max(int one, int two) {
		if(one > two) {
			return one;
		} else{
			return two;
		}
	}
	
	/**
	 * 给出i，j
	 * 计算c[i, j] 周围三个点的最大值，和最大值所在坐标
	 * @param i
	 * @param j
	 * @return
	 */
	private int[] next(int i, int j) {
		// i j 任意一个都不可能为0
		int[] temp = new int[3];                    
		
		// 上
		int up = c[i - 1][j];
		// 左
		int left = c[i][j - 1];
		// 左上
		int lu = c[i - 1][j - 1];
		
		// 优先顺序按照 左上，上，左
		if(lu >= up && lu >= left) {
			temp[0] = lu;
			temp[1] = i - 1;
			temp[2] = j -1;
		} else if(up >= lu && up >= left){
			temp[0] = up;
			temp[1] = i - 1;
			temp[2] = j;
		} else {
			temp[0] = left;
			temp[1] = i;
			temp[2] = j -1;
		}
		return temp;
	}
}
