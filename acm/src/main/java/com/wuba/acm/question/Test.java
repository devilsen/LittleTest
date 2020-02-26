package com.wuba.acm.question;

/**
 * desc :
 * date : 2020-02-17 18:07
 *
 * @author : dongSen
 */
public class Test {

    public static void main(String[] args) {

        String result = removeSpace("ab c def hi");
        System.out.println(result);
    }

    private static String removeSpace(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c != ' ') {
                result.append(c);
            }
        }
        return result.toString();
    }

}
