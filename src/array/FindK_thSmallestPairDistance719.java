package array;

import java.util.Arrays;

public class FindK_thSmallestPairDistance719 {
	/**
	 * 
	 * 先枚举出 n*(n-1)/2 个 二元组的距离绝对值 再使用 top K
	 * @param nums
	 * @param k
	 * @return
	 */
	public int smallestDistancePair(int[] nums, int k) {
		// 枚举出 n*(n-1)/2 个 二元组的距离绝对值  O(n^2)
		int length = nums.length * (nums.length - 1) / 2;
		int[] absoluteDistance = new int[length];
		int index = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				absoluteDistance[index] = Math.abs(nums[i] - nums[j]);
				index++;
			}
		}

		System.out.println("legnth: " + length);
		System.out.println("k: " + k);
		// 使用TOP K O(nlgn)
		return findKthLargest(absoluteDistance, length - k + 1);
	}

	public int findKthLargest(int[] nums, int k) {
		return quickSelect(nums, k, 0, nums.length - 1);
	}

	private int quickSelect(int[] arr, int k, int left, int right) {
		if (left == right)
			return arr[right];
		int index = partition(arr, left, right);
		if (index - left + 1 > k)
			return quickSelect(arr, k, left, index - 1);
		else if (index - left + 1 == k)
			return arr[index];
		else
			return quickSelect(arr, k - index + left - 1, index + 1, right);

	}

	private int partition(int arr[], int left, int right) {
		int i = left, j = right + 1, pivot = arr[left];
		while (true) {
			while (i < right && arr[++i] > pivot)
				if (i == right)
					break;
			while (j > left && arr[--j] < pivot)
				if (j == left)
					break;
			if (i >= j)
				break;
			swap(arr, i, j);
		}
		swap(arr, left, j); // swap pivot and a[j]
		return j;
	}

	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	/**
	 * 
	 * TODO(这里用一句话描述这个方法的作用)
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int smallestDistancePair1(int[] nums, int k) {
		// 排序
		Arrays.sort(nums);
		// absolute distance 的上下界
		int n = nums.length, low = 0, hi = nums[n - 1] - nums[0];
		// 根据上下界，二分查找 小于等于某个距离的二元组的对数，若对数而k,则这个距离即为所求值
		while (low < hi) {
			int cnt = 0, j = 0, mid = (low + hi) / 2;
			// 查找距离小于等于mid的二元组的对数 cnt
			for (int i = 0; i < n; ++i) {
				while (j < n && nums[j] - nums[i] <= mid)
					++j;
				cnt += j - i - 1;
			}
			
			if (cnt >= k){
				hi = mid;
			}else{
				low = mid + 1;
			}
		}

		return low;
	}
	
	
	public static void main(String[] args) {
		int[] nums = new int[]{1,3,1};
		FindK_thSmallestPairDistance719 fkspd = new FindK_thSmallestPairDistance719();
		System.out.println(fkspd.smallestDistancePair(nums, 1));
		
		Arrays.sort(nums);
		System.out.println(Arrays.toString(nums));
	}
}
