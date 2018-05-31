package backtracking;

public class WordSearch {
	public static void main(String[] args) {
		char[][] board = new char[][]{{'A','B','C','E'},
									  {'S','F','C','S'},
									  {'A','D','E','E'}
									 };
		String word = "ABCCED";
		String word1 = "ABCB";
		
		WordSearch ws = new WordSearch();
		System.out.println(ws.exist(board, word));
		System.out.println(ws.exist(board, word1));
	}
	
	public boolean exist(char[][] board, String word) {
		// 特例：board为空，或者word为空
		
		boolean result = false;
		
		char temChar = word.charAt(0);
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == temChar) {
					int[][] route = new int[2][word.length()];
//					route[0][0] = i;
//					route[1][0] = j;
					// 第一步探索，
					boolean result1 = backtracking(route, i, j, 1, board, word);
					result = result | result1;
				}
			}
		}
		return result;
	}
	
	/**
	 * 递归的回溯方法
	 * @param route 当前部分解已有路径
	 * @param current_x 当前x坐标
	 * @param current_y 当前y坐标
	 * @param steps 当前探索的步数
	 * @param board 原始的 2D board 
	 * @param word 原始的word
	 */
	private boolean backtracking(int[][] route, int current_x, int current_y, int steps, 
			final char[][] board, final String word) {
		// 跳出条件
		
		// 找到路径 true
		// 找不到后继 false
		
		// 递归
		
		// 找后继（不允许重复）
		// 使用可用的后继节点集合，递归
		return false;
	}
}
