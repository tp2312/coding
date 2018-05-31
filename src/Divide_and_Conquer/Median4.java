package Divide_and_Conquer;
/**
 * Find the median of the two sorted arrays.
 * 根据两个数组的中位数递归地二分查找
 * @author zyh
 *
 */
public class Median4 {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		return 0;
	}
	
	/**
	 * 根据两个数组的中位数递归地二分查找
	 * @param nums1 第一个数组
	 * @param start1 开始索引 （前后都是闭区间）
	 * @param end1 结束索引
	 * 
	 * @param nums2 第二个数组
	 * @param start2 开始索引 （前后都是闭区间）
	 * @param end2 结束索引
	 * @return
	 */
	private double findMedian(final int[] nums1, int start1, int end1, 
			final int[] nums2, int start2, int end2) {
		int min1 = nums1[start1];
		int min2 = nums2[start2];
		
		int max1 = nums1[end1];
		int max2 = nums2[end2];
		
		if(min2 > max1) {
			// nums1 和 nums2 不相交
			// nums2 元素大于 nums1
			return 0;
		} else if(min1 > max2) {
			// nums1 和 nums2 不相交
			// nums1 元素大于 nums2
			return 0;
		} else {
			// nums1 和 nums2 相交
			return 0;
		}
	}
}
