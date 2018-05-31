package dp.lcs;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归地求解LCS问题 时间复杂度为指数
 * 
 * 最优子结构：两个序列 X = [x1, x2, ..., xm] Y = [y1, y2, ..., yn], Z = [z1, z2, ..., zk]为X和Y的LCS
 * 	则有：
 * 		1，如果 xm == yn,则 zk == xm == yn 且Zk-1是Xm-1和Yn-1的一个LCS
 * 		2，如果xm ！= yn，那么zk != xm，意味着Z是Xm-1和Y的一个LCS
 * 		3，如果xm ！= yn，那么zk != yn，意味着Z是X和Yn-1的一个LCS
 * 
 * @author zyh
 *
 */
public class LCSRecursively {
	List<Integer> xIndex = new ArrayList<Integer>();
	List<Integer> yIndex = new ArrayList<Integer>();
	List<Character> chars = new ArrayList<Character>(); 
	
	public static void main(String[] args) {
		LCSRecursively lcs = new LCSRecursively();
		lcs.sulosion("ABCBDAB", "BDCABA");
		
	}
	public void sulosion(String str1, String str2){
		char[] X = str1.toCharArray();
		char[] Y = str2.toCharArray();
		System.out.println("LCS length: " + LCS(X, X.length, Y, Y.length, true));
		for(int i = 1; i <= xIndex.size(); i++) {
			System.out.println("c[" + xIndex.get(i - 1) + ", " + yIndex.get(i - 1) + "] : " + chars.get(i - 1));
		}
	}
	/**
	 * 
	 * @param X
	 * @param i 为X当前指针，当前元素为X[i - 1]
	 * @param Y
	 * @param j 同i
	 * @return
	 */
	public int LCS(char[] X, int i, char[]Y, int j, boolean flag){
		if(X.length == 0 || Y.length == 0){
			return 0;
		} 
		if(i == 0 || j == 0){
			return 0;
		} else {
			if(X[i - 1] == Y[j - 1]) {
				if(flag) {
					//System.out.println("i: " + i + "; j: " + j + "; char:　" + Y[j - 1]);
					xIndex.add(i);
					yIndex.add(j);
					chars.add(X[i - 1]);
				}
				return 1 + LCS(X, i - 1, Y, j - 1, flag);
			} else {
				// 这里递归调用了两次
				int one = LCS(X, i - 1, Y, j, false);
				int two = LCS(X, i, Y, j - 1, false);
				if(two > one) {
					return LCS(X, i, Y, j - 1, flag);
				} else{
					return LCS(X, i - 1, Y, j, flag);
				}
			}
		}
	}
}
