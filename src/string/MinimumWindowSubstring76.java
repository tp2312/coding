package string;


public class MinimumWindowSubstring76 {
	
	public String minWindow(String s, String t) {
		if(t.length() > s.length()) {
			return "";
		}
		// t 中字符出现的次数
		char[] tArray = new char[128];
		
		for(int i = 0; i < t.length(); i++) {
			tArray[t.charAt(i)]++;
		}
		
		// s 子串中字符出现的次数
		char[] temArray = new char[128];
		
		// 最小窗口的位置
		int start = 0, end = 0;
		int windowSize = Integer.MAX_VALUE;
		
		// 双指针
		int pStart = 0, pEnd = 0;
		
		for(; pEnd < s.length(); pEnd++) {
			// 右指针向右移动找到包括t的s子串
			temArray[s.charAt(pEnd)]++;
			if(isInclude(temArray, tArray)) {
				// 左指针向右移动使子串最小
				while(pStart < pEnd) {
					temArray[s.charAt(pStart)]--;
					if(isInclude(temArray, tArray)) {
						pStart++;
						continue;
					} else {
						temArray[s.charAt(pStart)]++;
						break;
					}
				}
				
				//System.out.println("inner: " + s.substring(pStart, pEnd + 1));
				
				// 如果有更小的窗口，更新窗口位置
				if((pEnd - pStart + 1) < windowSize) {
					start = pStart;
					end = pEnd;
					windowSize = pEnd - pStart + 1;
					//System.out.println(windowSize);
				}
			} 
		}
		if(windowSize == Integer.MAX_VALUE){
			return "";
		} else {
			return s.substring(start, end + 1);
		}
	}

	// src 是否包含 dest
	private boolean isInclude(char[] src, char[] dest) {
		for(int i = 0; i < 128; i++) {
			if(src[i] < dest[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		MinimumWindowSubstring76 minimumWindowSubstring = new MinimumWindowSubstring76();
		String S = "ADOBECODEBANC";
		String T = "ABC";
		
		System.out.println(minimumWindowSubstring.minWindow(S, T));
	}
}
