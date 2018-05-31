package dp.houseRobber;

import java.util.Arrays;

/**
 * 在1的基础上 
 * 
 * @author zyh
 *
 */
public class HouseRobber2 {
	public static void main(String[] args) {
		int sum = rob(new int[]{2,1,1,2});
	}
	public static int rob(int[] nums) {
		int length = nums.length;
		if (length == 0) {
			// 数组长度为0
			return 0;
		} else if (length == 1) {
			// 数组长度为1
			return nums[0];
		} else if (length == 2) {
			// 数组长度为2
			if (nums[0] > nums[1]) {
				return nums[0];
			} else {
				return nums[1];
			}
		} else{
			int sum = 0;
			for(int i = 0; i < length ; i++){
				// 去掉nums[i]的（顺序重整后）非环的HouseRobber
				int[] temps = new int[length -1];
				for(int j = 0; j < length; j++) {
					if(j < i){
						temps[length - i - 1 + j] = nums[j];
					} else if(j > i) {
						temps[j - i - 1] = nums[j];
					}
				}
				System.out.println(Arrays.toString(temps));
				int tem = houseRobber1(temps);
				System.out.println("max: " + tem);
				if(tem > sum) {
					sum = tem;
				}
			}
			return sum;
		}
	}

	private static int houseRobber1(int[] nums) {
		if (nums[0] > nums[1]) {
			nums[1] = nums[0];
		}
		for (int i = 2; i < nums.length; i++) {
			if (nums[i - 1] > nums[i - 2] + nums[i]) {
				nums[i] = nums[i - 1];
			} else {
				nums[i] = nums[i - 2] + nums[i];
			}
		}
		return nums[nums.length - 1];
	}
}
