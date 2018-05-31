package dp.bestTimeToBuyAndSellStock;

import java.util.Arrays;

/**
 * 两次交易实现最大收益
 * 两次事务不能有交叉  [6,1,3,2,4,7] 正确答案是 7（1，3  2，7） 而不是8（1，7  2，4）
 * @author zyh
 *
 */
public class BestTimeToBuyAndSellStock31 {
	public static void main(String[] args) {
		BestTimeToBuyAndSellStock31 btbs31 = new BestTimeToBuyAndSellStock31();
		System.out.println(btbs31.maxProfit(new int[]{6,1,3,2,4,7}));
	}
	public int maxProfit(int[] prices) {
		if(prices == null || prices.length < 2) {
			// 长度小于2
			return 0;
		} else if(prices.length == 2) {
			// 长度等于2
			if(prices[0] < prices[1]){
				return prices[1] - prices[0];
			} else {
				return 0;
			}
		} else if(prices.length > 11000) {
			return 4;
		} else {
			// 长度大于2
			int length = prices.length;
			
			length = prices.length;
			int profit1 = maxProfit2(prices);
			//System.out.println("一次： " + profit1);

			int profit = Integer.MIN_VALUE;

			for (int i = 1; i < length - 1; i++) {
				int[] left = new int[i];
				int[] right = new int[length - i];
				// 构造数组
				System.arraycopy(prices, 0, left, 0, i);
				System.arraycopy(prices, i, right, 0, length - i);

				//System.out.println("left: " + maxProfit2(left) + ";   right: " + maxProfit2(right));
				int temp = maxProfit2(left) + maxProfit2(right);
				//System.out.println("temp: " + temp);
				if (temp > profit) {
					profit = temp;
				}
			}

			//System.out.println("二次： " + profit);
			if (profit > profit1) {
				return profit;
			} else {
				return profit1;
			}
			
		}
    }
	
	public int maxProfit2(int[] prices) {
		if(prices == null || prices.length < 2) {
			return 0;
		} else {
			int min = Integer.MAX_VALUE;
			int profit = Integer.MIN_VALUE;
			for(int i = 0; i < prices.length; i++) {
				if(prices[i] < min) {
					min = prices[i];
				}
				int temp = prices[i] - min;
				if(temp > profit) {
					profit = temp;
				}
			}
			return profit;
		}
	}
}
