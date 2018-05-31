package dp.decodeWays;
/*
 * 使用递归解决 DecodeWays 问题，超时
 */
public class DecodeWays {
	private char[] chars;
	private int length;
	
	public static void main(String[] args) {
		System.out.println(new DecodeWays().numDecodings("8299152987184352183335613793611738246622952882892165423687431814339833963464637792247791375416898193"));
	}
	public int numDecodings(String s) {
		int n = s.trim().length();
		if (n == 0) {
			return 0;
		} else if(n == 1) {
			// 第一个数字是0，则输入数字序列错误，返回0
			if("0".equals(s.trim())){
                return 0;
            }
			return 1;
		} else {
			this.chars = s.toCharArray();
			this.length = n;
			
			try {
				return decodeWaysWithRecursion(0);
			} catch (Exception e) {
				return 0;
			}
		}
    }
	/**
	 * 递归地求解解码问题
	 * @param i ： 当前要解决的字符数组索引
	 * @return
	 * @throws Exception 
	 */
	private int decodeWaysWithRecursion(int i) throws Exception{
		if(i == length) {
			// 递归跳出：最后两个数字构成一个数，对应一个字符的编码
			return 1;
		} else if('0' == chars[i]) {
			// 当前处理的第一个数字是0，则给定的数字序列错误，使用异常处理跳出递归，返回0
			throw new Exception();
		} else if(i == length -1) {
			// 递归跳出：最后一个非零字符对应一个字符的编码
			return 1;
		}else{
			if(isBranch(i)){
				// 可以分为两种情况
				return decodeWaysWithRecursion(i + 1) + decodeWaysWithRecursion(i + 2);
			} else {
				// 一种情况
				
				if(i < length - 1) {
					// 10，20 要跳过连个数字
					if((chars[i] == '1' || chars[i] == '2') && chars[i + 1] == '0') {
						return decodeWaysWithRecursion(i + 2);
					}
				}
				return decodeWaysWithRecursion(i + 1);
			}
		}		
	}
	
	/**
	 * 判断能分为两种情况处理
	 * @param i 当前index
	 * @return
	 */
	private boolean isBranch(int i) {
		if (i == length - 1) {
			// 检查当前索引指向最后一个字符
			return false;
		} else if ("1".equals(chars[i] + "")) {
			// 非最后一个字符，当前字符为1
			
			if(i <= length - 2){
				// 10 
				if("0".equals(chars[i + 1] + "")){
					return false;
				}
			}
			if(i <= length -3) {
				if('1' == chars[i + 1] && '0' == chars[i + 2]) {
					// 110
					return false;
				}
				if('2' == chars[i + 1] && '0' == chars[i + 2]) {
					// 120
					return false;
				}
			}
			//System.out.println("chars[" + i + "] : " + chars[i] + " isBranch: true");
			return true;
		} else if ("2".equals(chars[i] + "")) {
			if(i <= length - 2){
				if("0".equals(chars[i + 1] + "")){
					// 20
					return false;
				}
				
			}
			if(i <= length -3) {
				if('1' == chars[i + 1] && '0' == chars[i + 2]) {
					// 210
					return false;
				}
				if('2' == chars[i + 1] && '0' == chars[i + 2]) {
					// 220
					return false;
				}
			}
			// 非最后一个字符，当前字符是2，并且下一个字符小于等于6(1~6，20已排除)
			if(chars[i + 1] <= '6'){
				return true;
			}
			return false;
		} else {
			return false;
		}
	}
}
