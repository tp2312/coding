package dp.bestTimeToBuyAndSellStock;

/**
 * as many transactions as you like 
 * @author zyh
 *
 */
public class BestTimeToBuyAndSellStock2 {
	public static void main(String[] args) {
		BestTimeToBuyAndSellStock2 btbs2 = new BestTimeToBuyAndSellStock2();
		System.out.println(btbs2.maxProfit(new int[]{2,1}));
	}
	// 比较蠢的解法是 从后往前遍历数组，寻找递减序列，累加各个序列极值的差值
	public int maxProfit(int[] prices) {
		if(prices == null || prices.length <= 1) {
			return 0;
		} else {
			int max = 0;
			int extremum = 0;
			//从后往前寻找连续下降的序列
			for(int i = prices.length - 1; i >= 0; i--) {
				if(i == 0) {
					if(prices[i] < prices[i + 1]) {
						max += (extremum - prices[0]);
					}
					break;
				} 
				if(prices[i] > prices[i - 1]) {
					// 如果当前元素和前一个元素能构成连续下降的序列
					if(extremum < prices[i]) {
						extremum = prices[i];
					}
					continue;
				} else {
					// 前一个元素大于等于当前元素
					if(i + 1 < prices.length && prices[i + 1] > prices[i]) {
						// 下降序列结束
						max = max + (extremum - prices[i]);
						extremum = prices[i];
					} else {
						// 找到了更大的元素，和前面的元素构成新的下降序列
						extremum = prices[i];
					}
				}
				
			}
			return max;
		}
	}
	
	/**
	 * 比较好的做法是从第二个元素开始从前往后遍历数组
	 * 如果当前元素大于前一个元素，就将它们的差值累加到收益中，因为可以昨天买入，今天卖出
	 * 
	 * 从前往后，在所有的上升序列上交易，总体来看，两次交易严格不能交叉
	 */
	public int maxProfit2(int[] prices) {
		if(prices == null || prices.length < 2) {
			return 0;
		} else {
			int profit = 0;
			for(int i = 1; i < prices.length; i++) {
				if(prices[i] > prices[i - 1]) {
					profit += prices[i] - prices[i - 1];
				}
			}
			return profit;
		}
	}
}
