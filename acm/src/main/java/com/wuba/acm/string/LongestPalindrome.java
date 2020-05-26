package com.wuba.acm.string;

/**
 * desc: 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * date: 2020/05/21
 *
 * @author : dongsen
 */
public class LongestPalindrome {

    // 1. 暴力解法
    public String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) return "";
        int windowSize = s.length() - 1;
        while (windowSize > 0) {
            int time = s.length() - windowSize;
            for (int i = 0; i < time; i++) {
                String check = isPalindrome(i, i + windowSize, s);
                if (check != null) {
                    return check;
                }
            }
            windowSize--;
        }
        return s.substring(0, 1);
    }

    private String isPalindrome(int start, int end, String s) {
        int left = start;
        int right = end;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return null;
            }
            left++;
            right--;
        }
        return s.substring(start, end + 1);
    }


    // 2. 扩展中心法
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return "";
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 奇数情况
            int len1 = center(s, i, i);
            // 偶数情况
            int len2 = center(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (end - start < len) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int center(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // 3. 动态规划 动态转移方程 d[i][j] = (s[i]==s[j]) and (j-i < 3 or d[i+1][j-1])
    public String longestPalindrome(String s) {
        int len = s.length();
        // 特判
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // 1. 状态定义
        // dp[i][j] 表示s[i...j] 是否是回文串

        // 2. 初始化
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chars = s.toCharArray();
        // 3. 状态转移
        // 注意：先填左下角
        // 填表规则：先一列一列的填写，再一行一行的填，保证左下方的单元格先进行计算
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // 头尾字符不相等，不是回文串
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    // 相等的情况下
                    // 考虑头尾去掉以后没有字符剩余，或者剩下一个字符的时候，肯定是回文串
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 状态转移
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要dp[i][j] == true 成立，表示s[i...j] 是否是回文串
                // 此时更新记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        // 4. 返回值
        return s.substring(begin, begin + maxLen);
    }

}
