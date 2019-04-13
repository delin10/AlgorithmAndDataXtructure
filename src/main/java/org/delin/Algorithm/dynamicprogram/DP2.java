package org.delin.Algorithm.dynamicprogram;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.delin.Algorithm.util.Utils;

/**
 * 回文串
 * @author delin
 *
 */
public class DP2 {

	public static String getLongestPalindromeString(String str) {
		char[] chs = str.toCharArray();
		int len = chs.length;
		List<Character> seq = new ArrayList<>(len);
		int limit = len - 1;
		for (int i = 0; i < len - 1; ++i) {
			for (int j = limit; j > i; --j) {
				if (chs[i] == chs[j]) {
					seq.add(chs[i]);
					limit = j - 1;
					break;
				}
			}
		}
		return seq.toString();
	}

	/**
	 * bb=>#b#b#################
	 * 当算法到达第一个#时
	 * 找到第一对#
	 * i->b
	 * j->第二个b
	 * 导致算法提早结束
	 * @param str
	 * @return
	 */
	public static int getLongestPalindromeStringLen(String str) {

		char[] chs = str.toCharArray();
		int len = chs.length;
		System.out.println(len);
		int[][] matrix = new int[len][len];
		List<Integer> seq = new ArrayList<>(len);
		int max = 0;
		for (int k = 0; k < len - 1; ++k) {
			int count = 0;
			int limit = len - 1;
			int i, j = limit;
			for (i = k; i < limit; ++i) {
				for (j = limit; j > i; --j) {
					System.out.print(chs[j] + " ");
					if (chs[i] == chs[j]) {
						seq.add(i);
						seq.add(j);
						count += 2;
						limit = j - 1;
						break;
					}
				}
			}
			System.out.println(seq);
			seq.clear();
			System.out.println(i + "," + j + "," + count);
			if (i - 1 == j || j - 1 == i) {
				count += 1;
			}
			max = Math.max(count, max);
		}
		return max;
	}

	public static int[] manachar(String input) {
		char[] chs = insertChar(input, '$', '#');
		int[] p = new int[chs.length];
		int mx = 0, id = 0, len = chs.length;
		for (int i = 1; i < len; ++i) {
			p[i] = mx > i ? Math.min(mx - i, p[2 * id - i]) : 1;
			while (i + p[i] < len && chs[i + p[i]] == chs[i - p[i]])
				p[i]++;
			if (mx < i) {
				mx = i + p[i];
				id = i;
			}
		}
		return p;
	}

	public static String getLongestPalindromeString(int[] p, String str) {
		// input已经无分隔符
		int max = 2;
		for (int i = 2; i < p.length; ++i) {
			if (p[i] > p[max]) {
				max = i;
			}
		}

		char[] res = new char[p[max] - 1];
		int cursor = 0;
		// 计算对应的回文串的开始索引
		int i = (int) Math.ceil((max - p[max] - 1) / 2.0D), start = i;
		// 长度偏移的关系 start-p[max] len=p[max]-1 start+len
		for (; i < start + p[max] - 1; ++i) {
			char ch = str.charAt(i);
			res[cursor++] = ch;
		}
		return new String(res);
	}

	public static char[] insertChar(String input, char head, char insertChar) {
		int size = input.length() * 2 + 3;
		char[] chs = new char[size];
		int i = 0;
		chs[i++] = head;
		chs[i++] = insertChar;
		for (int j = 0; j < input.length(); ++j) {
			chs[i++] = input.charAt(j);
			chs[i++] = insertChar;
		}
		chs[i] = '\0';
		return chs;
	}

	public static int manacharLen(String input) {
		char[] chs = insertChar(input, '$', '#');
		int[] p = new int[chs.length];
		int mx = 0, id = 0, len = chs.length;
		int max = 0;
		for (int i = 2; i < len - 1; ++i) {
			// 关于id对称的回文不需要额外计算的p[i]的回文长度
			p[i] = mx > i ? Math.min(mx - i, p[2 * id - i]) : 1;
			while (chs[i + p[i]] == chs[i - p[i]])
				p[i]++;
			if (mx < i) {
				mx = i + p[i];
				id = i;
			}
			if (max < p[i]) {
				max = p[i];
			}
		}
		return max;
	}

	// 暴力穷举
	public static void palindromeExhaustion(String str) {
		int max = 0;
		if (str.length() == 1) {
			max = 1;
		} else {
			char[] chs = str.toCharArray();

			int len = chs.length;
			for (int i = 0; i < len - 1; ++i) {
				max = Math.max(Math.max(calDot(chs, i), calEvenLater(chs, i)), max);

			}
		}
		System.out.println(max);
	}

	public static int calDot(char[] seq, int i) {
		int len = seq.length, offset = 1;

		while (0 <= i - offset && i + offset < len && seq[i - offset] == seq[i + offset])
			offset++;

		return 2 * (offset - 1) + 1;
	}

	public static int calEvenLater(char[] seq, int i) {
		int len = seq.length, offset = 1, k = i;

		while (0 <= k && k + offset < len && seq[k] == seq[k + offset]) {
			k--;
			offset += 2;
		}
		return offset - 1;
	}

	// 判断一个字符串是否是回文字符串
	public static boolean isPalindrome(String str) {
		char[] chs = str.toCharArray();
		int len = chs.length, i = 0, j = len - 1;
		boolean flag = true;
		while (i < j) {
			if (chs[i] != chs[j]) {
				flag = false;
				break;
			}
			i++;
			j--;
		}

		return flag;
	}

	// 判断一个字符串是否是回文字符串
	public static boolean isPalindrome(char[] chs, int start, int end) {
		int i = start, j = end;
		boolean flag = true;
		while (i < j) {
			if (chs[i] != chs[j]) {
				flag = false;
				break;
			}
			i++;
			j--;
		}

		return flag;
	}

	// 判断整数是否是回文串 不能转化成字符串
	public static boolean isPalindromeInteger(int v) {
		int len = Utils.countNumberDigit(v);
		int i = 1, j = len;
		boolean flag = true;
		while (i < j) {
			int rV = Utils.getDigit(v, len, i);
			int lV = Utils.getDigit(v, len, j);
			if (rV != lV) {
				flag = false;
				break;
			}
			i++;
			j--;
		}

		return flag;
	}

	// 判断一个字符串的数字和字母是否回文
	public static boolean isPalindromeString2(String str) {
		char[] chs = str.toCharArray();

		int i = forwardNext(chs, -1), j = backNext(chs, chs.length);
		boolean flag = true;
		while (i < j) {
			if (chs[i] != chs[j]) {
				flag = false;
				break;
			}
			i = forwardNext(chs, i);
			j = backNext(chs, j);
		}

		return flag;
	}

	public static int forwardNext(char[] chs, int cur) {
		int k = cur + 1;
		for (; k < chs.length && !Character.isLetterOrDigit(chs[k]); ++k)
			;
		return k;
	}

	public static int backNext(char[] chs, int cur) {
		int k = cur - 1;
		for (; k >= 0 && !Character.isLetterOrDigit(chs[k]); --k)
			;
		return k;
	}

	public static void findAllPalindromeGroup(String input) {
		char[] chs = input.toCharArray();
		List<ArrayList<String>> res = new ArrayList<>();
		for (int k = 1; k <= input.length(); ++k) {
			int cur=0,old=cur;
			ArrayList<String> t=new ArrayList<>();
			while (cur<input.length()) {
				if(isPalindrome(chs, cur, cur+k-1)) {
					t.add(new String(chs, cur, cur+k-1));
					cur+=k;
				}else {
					
				}
			}
		}
	}
	
	 //获得一个字符串的所有回文子串集合
    public static ArrayList<ArrayList<String>> partition(String s) {
         ArrayList<ArrayList<String>>arr=new ArrayList<ArrayList<String>>();
         if(s==null||s.length()==0)
           return arr;
         ArrayList<String> list=new ArrayList<String>();
         dfs(0,s,list,arr);
         return arr;
    }
    //回溯法
    private static void dfs(int index,String s,ArrayList<String> preList, ArrayList<ArrayList<String>>arr)
    {
        if(index==s.length()){
          arr.add(new ArrayList<String>(preList));
           return;
        }
        ArrayList<String> list = new ArrayList<String>(preList);
        for(int i=index;i!=s.length();i++){
           if(isHui(s,index,i))
           {
               list.add(s.substring(index,i+1));
               dfs(i+1,s,list,arr); //递归调用
               list.remove(list.size()-1);
           }
        }
    }
    //判断是否为回文串
    private static boolean isHui(String s,int start,int end){
        while(start<=end){
            if(s.charAt(start++)!=s.charAt(end--))
            {
               return false;
            }
        }
        return true;  
    }

	// ***********************************Main*************************************/
	public static void manacharMain(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String input = in.nextLine();
			int max = manacharLen(input);
			System.out.println(getLongestPalindromeString(manachar(input), input));
			System.out.println(max - 1);
		}

	}

	public static void palindromeExhaustionMain(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String input = in.nextLine();
			palindromeExhaustion(input);
			int max = manacharLen(input);
			System.out.println(max - 1);
		}

	}

	public static void isPalindromenMain(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String input = in.nextLine();
			System.out.println(isPalindrome(input));
		}

	}

	public static void isPalindromenIntegerMain(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int input = in.nextInt();
			System.out.println(isPalindromeInteger(input));
		}

	}

	public static void isPalindromenString2Main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String input = in.nextLine();
			System.out.println(isPalindromeString2(input));
		}

	}
	
	public static void partition() {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String input = in.nextLine();
			System.out.println(partition(input));
		}

	}

	public static void main(String[] args) {
		partition();
	}

}
