package com.wuba.acm.test;

/**
 * desc :
 * date : 2019/2/17
 *
 * @author : dongSen
 */
public class SolutionTemp {

    public static void main(String[] args) {
        SolutionTemp solutionTemp = new SolutionTemp();
        int result = solutionTemp.lengthOfLongestSubstring("abcabcbb");

        System.out.println(result);
    }

    public int lengthOfLongestSubstring(String s) {
        int stIdx = 0, maxLen = 0;
        int arr[] = new int[128];
        for (int i = 0; i < s.length(); i++) {
            stIdx = Math.max(arr[s.charAt(i)], stIdx);
            maxLen = Math.max(maxLen, i - stIdx + 1);
            arr[s.charAt(i)] = i + 1;
        }
        return maxLen;
    }

}
