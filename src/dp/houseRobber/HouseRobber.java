package dp.houseRobber;
/**
 * 当前最大和为 max{前一个位置的最大和,前两个位置的最大和加上当前值}
 *
 * @author zyh
 *
 */
public class HouseRobber {
	public int rob(int[] nums) {
		int length = nums.length;
		if(length == 0) {
			// 数组长度为0
			return 0;
		} else if(length == 1) {
			// 数组长度为1
			return nums[0];
		} else if(length == 2) {
			// 数组长度为2
			if(nums[0] > nums[1]) {
				return nums[0];
			} else {
				return nums[1];
			}
		} else {
			// 一般情况
			
			// nums[1]
			if(nums[0] > nums[1]) {
				nums[1] = nums[0];
			} 
			
			// nums[i] (i >= 2)
			for(int i = 2; i < length; i++) {
				if(nums[i - 1] > nums[i - 2] + nums[i]) {
					nums[i] = nums[i - 1];
				} else {
					nums[i] = nums[i - 2] + nums[i];
				}
			}
			return nums[length -1];
		}
	}
}
