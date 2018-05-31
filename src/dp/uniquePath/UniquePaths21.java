package dp.uniquePath;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用动态规划求解 Unique Paths II obstacleGrid[m][n]
 * 
 * 	obstacleGrid[i][j] 到 obstacleGrid[m][n] 的路径数为
 * 	obstacleGrid[i + 1][j]，obstacleGrid[i][j + 1]两个点到obstacleGrid[m][n] 的路径数之和
 * 	
 *  若obstacleGrid[i][j] == 1，则路径数位0
 *  
 *  从obstacleGrid[m][n] 右下角开始，每次计算 逆对角线方向一（斜）行元素的路径数,从右下方向左上移动
 * @author zhou
 *
 */
public class UniquePaths21 {
	// 输入矩阵信息
	private int[][] grid;
	private int row; // 行
	private int column; // 列
	
	// 缓存格子路径数的map
	Map<String, Integer> cacheMap;
	
	public static void main(String[] args) {
		UniquePaths21 uniquePaths21 = new UniquePaths21();
		int[][] obstacleGrid = new int[3][3];
		obstacleGrid[1][1] = 1;
		System.out.println(uniquePaths21.uniquePathsWithObstacles(obstacleGrid));
	}
	
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		row = obstacleGrid.length;
		if(row == 0) {
			// 空的矩阵
			return 0;
		} else if(row == 1 && obstacleGrid[0].length == 1){
			// 矩阵为 1*1
			if(obstacleGrid[0][0] == 1) {
				return 0;
			} else {
				return 1;
			}
		} else {
			// 一般情况
			column = obstacleGrid[0].length;
			grid = obstacleGrid;
			
			return computeUniquePaths2();
		}
	}
	
	/**
	 * 使用动态规划计算有障碍矩阵的路径
	 * @return
	 */
	private int computeUniquePaths2() {
		// 下
		//System.out.println("下");
		for (int j = column - 1; j >= 0; j--) {
			// 最后一行，从右到左
			
			Map<String, Integer> tempMap = new HashMap<String, Integer>();// 遍历一（斜）行使用一个新的map，并更新之前的map
			for (int r = row - 1, c = j; r >= 0 && c < column; r--, c++) {
				// 从[row - 1,j] 开始逆对角线上的格子
				//System.out.println("row " + r + " column " + c + " : "+ grid[r][c]);
				
				// 当前格子 grid[r,c]
				if(grid[r][c] == 1) { // 判断当前格子是否是障碍
					if(r == row -1 && c == column -1) { // 目的地格子是障碍，则不可达
						return 0;
					} else { // 一般的格子是障碍，则将当前格子的路径数置为0
						put(r, c, 0, tempMap);
					}
				} else {
					if(r == row -1 && c == column -1) {// 目的地格子
						// 初始化目的地格子的路径数为1
						put(r, c, 1, tempMap);
					} else { // 一般格子不是障碍
						int tempPaths = 0;
						// 下面的格子坐标 [r + 1, c]
						if(r + 1 >= 0 && r + 1 < row) {
							tempPaths = get(r + 1, c, cacheMap) + tempPaths;
						}
						// 右边的格子坐标 [r, c + 1]
						if(c + 1 >= 0 && c + 1 < column) {
							tempPaths = get(r, c + 1, cacheMap) + tempPaths;
						}
						
						put(r, c, tempPaths, tempMap);
					}
				}
			}
			cacheMap = tempMap;
		}
		
		// 从左下到左上的遍历，矩阵的行数必须大于等于2
		if (row >= 2) {
			// 左
			//System.out.println("左：");
			for (int i = row - 2; i >= 0; i--) {
				// 第一列，从下到上
				Map<String, Integer> tempMap1 = new HashMap<String, Integer>();
				for (int r = i, c = 0; r >= 0 && c < column; r--, c++) {
					// 从[i,0] 开始逆对角线上的格子
					//System.out.println("row " + r + " column " + c + " : " + grid[r][c]);

					// 当前格子[r,c]
					if (grid[r][c] == 1) {
						put(r, c, 0, tempMap1);
					} else {
						int tempPaths = 0;
						// 下面的格子坐标 [r + 1, c]
						if (r + 1 >= 0 && r + 1 < row) {
							tempPaths = get(r + 1, c, cacheMap) + tempPaths;
						}
						// 右边的格子坐标 [r, c + 1]
						if (c + 1 >= 0 && c + 1 < column) {
							tempPaths = get(r, c + 1, cacheMap) + tempPaths;
						}

						put(r, c, tempPaths, tempMap1);
					}
				}
				cacheMap = tempMap1;
			}
		}
		
		return get(0, 0, cacheMap);
	}
	
	/**
	 * 将[i,j]网格的路径数存入map中
	 * @param i
	 * @param j
	 * @param map
	 */
	private void put(int i, int j, int paths, Map<String, Integer> map) {
		map.put(i + "#" + j, paths);
	}
	
	/**
	 * 从map中获取[i,j]格子的路径数
	 * @param i
	 * @param j
	 * @param map
	 * @return
	 */
	private int get(int i, int j, Map<String, Integer> map) {
		//System.out.println("get: [" + i + "][" + j + "] : " + map.get(i + "#" + j));
		return map.get(i + "#" + j);
	}
}
