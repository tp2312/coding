package dp.uniquePath;

public class Test {
	
	public static void main(String[] args) {
		Test test = new Test();
		// 矩阵为 1*1
		int[][] obstacleGrid1 = new int[1][1];
		obstacleGrid1[0][0] = 1;
		
		// 矩阵为 1*3
		int[][] obstacleGrid2 = new int[1][3];
		obstacleGrid2[0][1] = 1;
		
		// 矩阵为 3*1
		int[][] obstacleGrid3 = new int[3][1];
		obstacleGrid3[1][0] = 1;
		
		// 矩阵为 3*3
		int[][] obstacleGrid4 = new int[3][3];
		obstacleGrid4[1][1] = 1;
		
		// 矩阵为 2*4
		int[][] obstacleGrid5 = new int[2][4];
		obstacleGrid5[1][1] = 1;

		// 矩阵为 4*2
		int[][] obstacleGrid6 = new int[4][2];
		obstacleGrid6[1][1] = 1;
		
		test.ergodicMatrix(obstacleGrid6);
	}
	/**
	 * 沿着逆对角线方向，从右下到左上遍历矩阵
	 */
	public void ergodicMatrix(int[][] obstacleGrid) {
		int row = obstacleGrid.length; // 行
		int column = 0; // 列
		if(row == 0) {
			// 空矩阵
			System.out.println("矩阵为空！！");
		} else {
			// 矩阵不空
			column = obstacleGrid[0].length;// 列
			
			System.out.println("下：");
			// 下
			for(int j = column - 1; j >= 0; j--){
				// 最后一行，从右到左
				for(int r = row - 1, c = j; r >= 0 && c < column; r--, c++) {
					// 从[row - 1,j] 开始，遍历逆对角线上的格子
					System.out.println("grid[" + r + "][" + c + "] " + obstacleGrid[r][c]);
				}
			}
			
			// 从左下到左上的遍历，矩阵的行数必须大于等于2
			if(row >= 2) {
				System.out.println("左：");
				// 左
				for(int i = row - 2; i >= 0; i--) {
					// 第一列，从下到上
					for(int r = i, c = 0; r >= 0 && c < column; r--, c++) {
						// 从[i,0] 开始，遍历逆对角线上的格子
						System.out.println("grid[" + r + "][" + c + "] " + obstacleGrid[r][c]);
					}
				}
			}
		}
	}
}
