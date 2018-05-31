package Divide_and_Conquer;

import java.util.ArrayList;
import java.util.List;

public class AddParentheses241 {
	public static void main(String[] args) {
		AddParentheses241 addParentheses = new AddParentheses241();
		String input = "2*3-4*5";
		System.out.println(addParentheses.diffWaysToCompute(input));
		
	}
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> result = new ArrayList<Integer>();
		if(input.length() >0) {
			if(numsOfoperators(input) == 0) {
				result.add(new Integer(input));
			}else if(numsOfoperators(input) == 1) {
				for (int i = 0; i < input.length(); i++) {
					if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*'){
						String x = input.substring(0, i);
						String y = input.substring(i + 1, input.length());
						char operator = input.charAt(i);
						result.add(helper(x, y, operator));
					}
				}
			} else {
				for(int i = 1; i < input.length() - 1; i++) {
					if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
						String prefixString = input.substring(0, i);
						String suffixString = input.substring(i + 1, input.length());
						char operator = input.charAt(i);
						
						List<Integer> prefixList = diffWaysToCompute(prefixString);
						List<Integer> suffixList = diffWaysToCompute(suffixString);
						
						for(int x : prefixList) {
							for(int y : suffixList) {
								result.add(helper(x, y, operator));
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	private int helper(String x, String y, char operator) {
		int x1 = new Integer(x);
		int y1 = new Integer(y);
		return helper(x1, y1, operator);
	}
	private int helper(int x, int y, char operator) {
		if(operator == '+') {
			return x + y;
		} else if(operator == '-') {
			return x - y;
		} else if(operator == '*') {
			return x * y;
		} else {
			throw new IllegalArgumentException();
		}
	}
	private int numsOfoperators(String input) {
		int count = 0;
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
				count++;
			}
		}
		return count;
	}
}
