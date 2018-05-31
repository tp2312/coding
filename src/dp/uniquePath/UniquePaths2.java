package dp.uniquePath;

import java.math.BigInteger;
/**
 * 在UniquePaths基础上，使用组合+筛选，结果错误
 * @author zhou
 *
 */
public class UniquePaths2 {
	public static void main(String[] args) {
		int[] int1 = new int[]{0, 0};
		int[] int2 = new int[]{1, 1};
		int[] int3 = new int[]{0, 0};
		
		int[][] ints = new int[][]{int1, int2, int3};
		
		for(int i = 0; i < ints.length; i++) {
			for(int j = 0; j < ints[i].length; j++) {
				System.out.print(ints[i][j] + " ");
				if(j == ints[i].length - 1){
					System.out.println();
				}
			}
		}
		
		
		UniquePaths2 uniquePaths2 = new UniquePaths2();
		System.out.println(uniquePaths2.uniquePathsWithObstacles(ints));
	}
	
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int row = obstacleGrid.length;
		int column = obstacleGrid[0].length;
		
		int total = simplePath(row, column);
		System.out.println("row :" + row + " column: " + column);
		System.out.println("total: " + column);
		// 不能单独考虑每一个障碍点，计算一个障碍点的路径时可能会受到其他障碍点的影响
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(obstacleGrid[i][j] == 1) {
					int path1 = simplePath(i + 1, j + 1);
					int path2 = simplePath(row - i, column - j);
					total = total - path1 * path2;
					System.out.println("i: " + i + " j: " + j + " path1: " + path1 + " path2: " + path2);
				}
			}
		}
        return total;
    }
	/**
	 * 简单路径
	 * @param m
	 * @param n
	 * @return
	 */
	public int simplePath(int m, int n) {
		int max = 0;
		int min = 0;
		if(m > n) {
			max = m;
			min = n;
		} else {
			max = n;
			min = m;
		}
		
		BigInteger b1 = factorial(max, m + n -2);
		BigInteger b2 = factorial(min - 1);
		BigInteger b3 = b1.divide(b2);
		
		return b3.intValue();
	}
	/**
	 * 计算阶乘
	 * 注意溢出
	 * @param n
	 * @return
	 */
	public BigInteger factorial(int m) {
		BigInteger n = new BigInteger(m + "");
		
		BigInteger val = BigInteger.ONE;
		for (BigInteger index = BigInteger.ONE;
		index.compareTo(n) < 1; index = index.add(BigInteger.ONE)) {
			val = val.multiply(index);
		}
		return val;
	}
	
	/**
	 * 计算连乘[from, to] 
	 * @param from
	 * @param to
	 * @return
	 */
	public BigInteger factorial(int from, int to){
		BigInteger end = new BigInteger(to + ""); 
		BigInteger val = BigInteger.ONE;
		for(BigInteger index = new BigInteger(from + "");
				index.compareTo(end) < 1; index = index.add(BigInteger.ONE)){
			val = val.multiply(index);
		}
		return val;
	}
}
