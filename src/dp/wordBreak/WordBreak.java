package dp.wordBreak;

public class WordBreak {
	public static void main(String[] args) {
		String str1 = "tomcatover";
		
		String str2 = "cat";
		int str2Index = str1.indexOf(str2);
		int str2Length = str2.length();
		
		String str3 = "over";
		int str3Index = str1.indexOf(str3);
		int str3Length = str3.length();
		
		System.out.println(str2Index + str2Length == str3Index);
		System.out.println(str3Index + str3Length  == str1.length());
	}
}
