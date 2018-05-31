package dp.uniquePath;

import java.math.BigInteger;

/**
 * 将路径数量抽象为排列数
 * @author zhou
 *
 */
public class UniquePaths {
	public static void main(String[] args) {
		UniquePaths uniquePaths = new UniquePaths();
		System.out.println(uniquePaths.solution(1, 10));
		System.out.println(uniquePaths.solution1(10, 1));
	}

	/**
	 * 直接算阶乘
	 * @param m
	 * @param n
	 * @return
	 */
	public int solution(int m, int n) {
        BigInteger b1 = factorial(m + n -2);
        BigInteger b2 = factorial(m - 1);
        BigInteger b3 = factorial(n - 1);
        
        BigInteger b4 = b1.divide(b2.multiply(b3));
        return b4.intValue();
    }
	/**
	 * 将分子分母阶乘因子适当消元后计算阶乘
	 * @param m
	 * @param n
	 * @return
	 */
	public int solution1(int m, int n) {
		int max = 0;
		int min = 0;
		if(m > n) {
			max = m;
			min = n;
		} else {
			max = n;
			min = m;
		}
		
		BigInteger b1 = factorial(max, m + n -2);
		BigInteger b2 = factorial(min - 1);
		BigInteger b3 = b1.divide(b2);
		
		return b3.intValue();
	}
	/**
	 * 计算阶乘
	 * 注意溢出
	 * @param n
	 * @return
	 */
	public BigInteger factorial(int m) {
		BigInteger n = new BigInteger(m + "");
		
		BigInteger val = BigInteger.ONE;
		for (BigInteger index = BigInteger.ONE;
		index.compareTo(n) < 1; index = index.add(BigInteger.ONE)) {
			val = val.multiply(index);
		}
		return val;
	}
	
	/**
	 * 计算连乘[from, to] 
	 * @param from
	 * @param to
	 * @return
	 */
	public BigInteger factorial(int from, int to){
		BigInteger end = new BigInteger(to + ""); 
		BigInteger val = BigInteger.ONE;
		for(BigInteger index = new BigInteger(from + "");
				index.compareTo(end) < 1; index = index.add(BigInteger.ONE)){
			val = val.multiply(index);
		}
		return val;
	}
}
