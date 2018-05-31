package dp.bestTimeToBuyAndSellStock;
/**
 * 只交易一次
 * @author zyh
 *
 */
public class BestTimeToBuyAndSellStock {
	public static void main(String[] args) {
		BestTimeToBuyAndSellStock bttbss = new BestTimeToBuyAndSellStock();
		System.out.println(bttbss.maxProfit(new int[]{7, 6, 4, 3, 1}));
	}
	// 比较蠢的解法是 用后一个元素减去前一个元素构造新的数组 然后求新数组的连续最大和子数组
	public int maxProfit(int[] prices) {
		if(prices == null || prices.length <= 1) {
			return 0;
		} else {
			// 用后一个元素减去前一个元素构造新的数组
			int[] tem = new int[prices.length - 1];
			for(int i = 0; i < prices.length - 1; i++) {
				tem[i] = prices[i + 1] - prices[i];
			}
			return maxSubArray(tem);
		}
	}
	
	/**
	 * 最大连续子序列和
	 * 若都是负数则返回0
	 * @param nums
	 * @return
	 */
	private int maxSubArray(int[] nums) {
		for(int i = 0; i < nums.length; i++) {
			if(i == 0) {
				// 第一个元素
				if(nums[0] < 0) {
					nums[0] = 0;
				}
			} else {
				if(nums[i - 1] > 0) {
					int tem = nums[i - 1] + nums[i];
					if(tem > 0) {
						nums[i] = tem;
					} else {
						nums[i] = 0;
					}
				} else {
					if(nums[i] < 0) {
						nums[i] = 0;
					}
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] > max) {
				max = nums[i];
			}
		}
		return max;
	}
	
	/**
	 * 简单的解法是顺序遍历数组，用当前元素减去 遍历到当前的最小值（min） 作为收益（profit），返回profit的最大值
	 * 遍历的过程不断更新 min 和 profit
	 * @param prices
	 * @return
	 */
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
