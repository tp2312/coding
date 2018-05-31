package dp.bestTimeToBuyAndSellStock;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * k 次事务
 * 从前往后遍历序列，找到所有上升序列的增量，去前K大之和
 * @author zyh
 *
 */
public class BestTimeToBuyAndSellStock4 {
	public static void main(String[] args) {
		BestTimeToBuyAndSellStock4 btbs4 = new BestTimeToBuyAndSellStock4();
		System.out.println(btbs4.maxProfit(2, new int[]{1,2,4,2,5,7,2,4,9,0}));
		
	}
	public int maxProfit(int k, int[] prices) {
		if(k == 0 || prices == null || prices.length == 0) {
			return 0;
		} else if(prices.length == 2) {
			return prices[0] < prices[1] ? (prices[1] - prices[0]) : 0;
		} else {
			List<Integer> assit = new LinkedList<Integer>();
			Collections.sort(assit);
			
			int temp = 0;
			// 从前往后获取所用递增序列的增量
			for(int i = 1; i < prices.length; i++) {
				int increment = prices[i] - prices[i -1];
				if(increment > 0) {
					temp += increment;
					if(i == prices.length - 1) {
						assit.add(temp);
					}
				} else {
					if(temp != 0) {
						assit.add(temp);
						temp = 0;
					}
				}
			}
			
			int result = 0;
			if(k >= assit.size()) {
				for(int i = 0; i < assit.size(); i++) {
					result += assit.get(i);
				}
			} else {
				Collections.sort(assit);
				System.out.println(assit);
				for(int i = assit.size() - 1; i > assit.size() - 1 - k; i--) {
					result += assit.get(i);
				}
			}
			return result;
		}
	}
}
