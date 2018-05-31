package dp.decodeWays;

import java.util.Arrays;

/**
 * 使用从后到前，动态规划的思想实现
 * @author zyh
 *
 */
public class DecodeWays2 {
	public static void main(String[] args) {
		DecodeWays2 dWays2 = new DecodeWays2();
		System.out.println(dWays2.numDecodings("4757562545844617494555774581341211511296816786586787755257741178599337186486723247528324612117156948"));
	}
	public int numDecodings(String s) {
		int length = s.length();
		// 特殊情况1：字符串长度为0
		if(length == 0){
			return 0;
		} 
		char[] chars = s.toCharArray();
		
		// 特殊情况2：第一个字符为0
		if(chars[0] == '0') {
			return 0;
		}
		
		// 特殊情况3：字符串长度为1
		if(length == 1){
			if(s.equals("0")){
				return 0;
			} else {
				return 1;
			}
		}
		
		// 特殊情况4：字符串长度为2,并且首字符不可能为0
		if(length == 2) {
			return match(chars[0], chars[1]);
		}
		
		// 一般情况，字符串长度大于2
		
		// 存储[i, n]编码数
		int[] c = new int[length + 1];
		// 初始化c[length]
		c[length] = 0;
		if(chars[length - 1] == '0') {
			c[length -1] = 0;
		} else {
			c[length -1] = 1;
		}
		
		System.out.println("chars[" + (length - 1) + "] : " + chars[length - 1] + " -- " + c[length - 1]);
		// 用于判断连续的1，2的个数
		boolean[] assist = new boolean[length + 1];
		assist[length] = false;
		assist[length - 1] = false;
		
		for(int i = length -2; i >= 0; i--) {
			// 当前字符为0
			if(chars[i] == '0'){
				assist[i] = false;
				if(chars[i + 1] == '0'){
					return 0;
				} else {
					c[i] = c[i + 1];
					System.out.println("chars[" + i + "] : " + chars[i] + " -- " + c[i]);
					assist[i] = false;
				}
			} else {
				// 非零
				int result = match(chars[i], chars[i + 1]);
				if (result == 0) {
					return 0;
				} else if (result == 1) {
					// 特例 10 为1
					if(i == length - 2 && c[i + 1] == 0) {
						c[i] = 1;
						System.out.println("chars[" + i + "] : " + chars[i] + " -- " + c[i]);
						assist[i] = false;
					} else {
						c[i] = c[i + 1];	
						System.out.println("chars[" + i + "] : " + chars[i] + " -- " + c[i]);
						assist[i] = false;
					}
				} else {
					// 2
					int j = 0;
					for(j = i + 1; j <= length; j++){
						if(assist[j] == true) {
							continue;
						} else {
							break;
						}
					}
					
					if(j - i == 1) {
						if(assist[i + 1] == false) {
							if(i < length - 2) {
								if(chars[i + 2] == '0') {
									c[i] = c[i + 1];
									System.out.println("chars[" + i + "] : " + chars[i] + " -- " + c[i]);
									assist[i] = true;
								} else {
									c[i] = 2 * c[i + 1];
									System.out.println("chars[" + i + "] : " + chars[i] + " -- " + c[i]);
									assist[i] = true;
								}
							} else {
								c[i] = 2 * c[i + 1];
								System.out.println("chars[" + i + "] : " + chars[i] + " -- " + c[i]);
								assist[i] = true;
							}
						} else {
							if(i <  length - 2){
								if(chars[i + 2] == '0') {
									c[i] = c[i + 1];
									System.out.println("chars[" + i + "] : " + chars[i] + " -- " + c[i]);
									assist[i] = true;
								} else {
									c[i] = c[i + 1] + 1;
									System.out.println("chars[" + i + "] : " + chars[i] + " -- " + c[i]);
									assist[i] = true;
								}
							} else {
								c[i] = c[i + 1] + 1;
								System.out.println("chars[" + i + "] : " + chars[i] + " -- " + c[i]);
								assist[i] = true;
							}
						}
					} else {
						if(j == length - 1) {
							int offset = j - i + 1;
							if (c[j + 1] == 0 || c[j + 1] == 1) {
								c[i] = fib(offset) + c[j + 1];
							} else {
								c[i] = fib(offset) * c[j + 1];
							}
							System.out.println("chars[" + i + "] : " + chars[i] + " -- " + c[i]);
							assist[i] = true;
						} else{
							int offset = j - i;
							if (c[j + 1] == 0 || c[j + 1] == 1) {
								c[i] = fib(offset) + c[j + 1];
							} else {
								c[i] = fib(offset) * c[j + 1];
							}
							System.out.println("chars[" + i + "] : " + chars[i] + " -- " + c[i]);
							assist[i] = true;
						}
					}
				} 
			}
		}
		
		System.out.println("c: " + Arrays.toString(c));
		System.out.println("a: " + Arrays.toString(assist));
		return c[0];
	}
	
	/**
	 * 两个字符构成编码的个数
	 * @param one
	 * @param two
	 * @return 0：出错
	 */
	private int match(char one, char two) {
		if(two == '0') {
			if(one == '1' || one == '2') {
				// 10 20
				return 1;
			} else {
				// 30，出错
				return 0;
			}
		}else if(one == '1'){
			return 2;
		} else if(one == '2' && 
				(two == '1' || two == '2' || two == '3' || two == '4' || two == '5' || two == '6')){
			return 2;
		} else {
			// 前一个字符和第二个字符不能构成一个编码，这两个字符只能构成一种编码
			return 1;
		}
	}
	// 斐波那契
	private int fib(int n) {
		if(n == 1) {
			return 1;
		} else if(n == 2) {
			return 2;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}
}
