package dp;

/**
 * 使用动态规划求解 路径最小和  grid[m][n] （值非负）
 * 
 * grid[i][j] 到 grid[m][n] 的路径最小和为
 * grid[i][j]的值加上  grid[i + 1][j]，grid[i][j + 1]两个点路径最小和的较小者
 * 
 * 从grid[m][n] 右下角开始，每次计算 逆对角线方向一（斜）行元素的路径数,从右下方向左上移动
 * 
 * @author zyh
 *
 */
public class MinimumPathSum {
	private int[][] grid;
	private int row;
	private int column;
	
	public static void main(String[] args) {
		MinimumPathSum minimumPathSum = new MinimumPathSum();
		
		// 矩阵为 3*3
		int[][] grid1 = new int[3][3];
		grid1[2][0] = 1;
		grid1[1][1] = 2;
		grid1[0][2] = 3;
		
		System.out.println(minimumPathSum.minPathSum(grid1));
	}
	public int minPathSum(int[][] grid) {
		row = grid.length;
		if (row == 0) {
			// 空矩阵
			return 0;
		} else if (row == 1 && grid[0].length == 1) {
			// 矩阵大小为 1*1
			return grid[0][0];
		} else {
			// 一般情况
			column = grid[0].length;
			this.grid = grid;
			return computeMinPathSum();
		}
	}
	
	/**
	 * 使用动态规划计算路径最小和
	 * @return
	 */
	private int computeMinPathSum() {
		// 沿着逆对角线方向，从右下到左上遍历矩阵
		
		// 沿着下边缘
		for(int j = column - 1; j >= 0; j--){
			// 最后一行，从右到左
			for(int r = row - 1, c = j; r >= 0 && c < column; r--, c++) {
				// 当前坐标[r][c]
				
				if(r == row -1 && c == column - 1){
					// 当前格子是目的地格子，啥都不做
				} else {
					// 下面的格子坐标 [r + 1][c]
					int down = Integer.MAX_VALUE;
					if(r + 1 >= 0 && r + 1 < row) {
						down = grid[r + 1][c];
					}
					// 右边的格子坐标 [r][c + 1]
					int right = Integer.MAX_VALUE;
					if(c + 1 >= 0 && c + 1 < column) {
						right = grid[r][c + 1];
					}
					
					// 将当前格子的路径最小和写到矩阵中
					if(down < right) {
						grid[r][c] = down + grid[r][c];
					} else {
						grid[r][c] = right + grid[r][c];
					}
				}
			}
		}
		
		// 沿着左边缘
		if (row >= 2) {
			for (int i = row - 2; i >= 0; i--) {
				for (int r = i, c = 0; r >= 0 && c < column; r--, c++) {
					// 当前坐标[r][c]
					// 下面的格子坐标 [r + 1][c]
					int down = Integer.MAX_VALUE;
					if(r + 1 >= 0 && r + 1 < row) {
						down = grid[r + 1][c];
					}
					// 右边的格子坐标 [r][c + 1]
					int right = Integer.MAX_VALUE;
					if(c + 1 >= 0 && c + 1 < column) {
						right = grid[r][c + 1];
					}
					
					// 将当前格子的路径最小和写到矩阵中
					if(down < right) {
						grid[r][c] = down + grid[r][c];
					} else {
						grid[r][c] = right + grid[r][c];
					}
				}
			}
		}
		return grid[0][0];
	}
}
