package dp.bestTimeToBuyAndSellStock;

import java.util.Arrays;

/**
 * 两次交易实现最大收益
 * 两次事务有交叉，不符合要求
 * @author zyh
 *
 */
public class BestTimeToBuyAndSellStock3 {
	
	public static void main(String[] args) {
		BestTimeToBuyAndSellStock3 btbs3 = new BestTimeToBuyAndSellStock3();
		
		System.out.println(btbs3.maxProfit(new int[]{6,1,3,2,4,7}));
	}
	// 将一次交易获取最大收益的过程做两遍
	public int maxProfit(int[] prices) {
		if(prices == null || prices.length < 2) {
			//System.out.println("长度小于3");
			return 0;
		} else {
			// 初始数组长度 大于等于3
			int profit = 0;
			int[] resultOne = maxProfit2(prices);
			if(resultOne[0] == 0) {
				//System.out.println("一次返回");
				return 0;
			} else {
				profit += resultOne[0];
				
				// 构造新的数组
				int[] prices2 = new int[prices.length - 2];
				
				int j = 0;
				for(int i = 0; i < prices.length; i++){
					if(i == resultOne[1]) {
						continue;
					} else if(i == resultOne[2]) {
						continue;
					} else {
						prices2[j++] = prices[i];
					}
				}
				int[] resultTwo = maxProfit2(prices2);
				
				profit += resultTwo[0];
				//System.out.println("两次返回");
				return profit;
			}
		}
		
	}
	
	/**
	 * 顺序遍历数组，用当前元素减去 遍历到当前的最小值（min） 作为收益（profit），返回profit的最大值
	 * 遍历的过程不断更新 min (buy_i) 和 profit(sell_i)
	 * 返回profit（最大收益）和 buy_i(此次收益买入索引) sell_i（此次收益卖出索引）
	 * @param prices
	 * @return
	 */
	public int[] maxProfit2(int[] prices) {
		if(prices == null || prices.length < 2) {
			return new int[]{0,-1,-1};
		} else {
			System.out.println("数组：" + Arrays.toString(prices));
			
			int min = Integer.MAX_VALUE;
			int min_i = 0;
			int profit = Integer.MIN_VALUE;
			
			int buy_i = 0;
			int sell_i = 0;
			
			for(int i = 0; i < prices.length; i++) {
				if(prices[i] < min) {
					min = prices[i];
					min_i = i;
				}
				int temp = prices[i] - min;
				if(temp > profit) {
					profit = temp;
					sell_i = i;
					buy_i = min_i;
				}
			}
			System.out.println("maxProfit2: " + profit + " buy_i: " + sell_i + " sell_1: " + sell_i);
			return new int[]{profit, buy_i, sell_i};
		}
	}
}
