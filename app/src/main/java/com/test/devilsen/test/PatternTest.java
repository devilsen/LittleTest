package com.test.devilsen.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author : dongSen
 * date : 2017/8/4
 * desc : 轮询与正则的效率测试
 */
public class PatternTest {


    public static void main(String[] args) {

        String source = makeData();

//        System.out.println("source " + source);

//        patternTest(source);
//        forTest(source);

        long time = System.currentTimeMillis();

        findTag(source);

        System.out.println("time " + (System.currentTimeMillis() - time));
    }

    private static String makeData() {
        int length = 1000;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(String.valueOf(i));

            if (i % 100 == 0) {
                stringBuilder.append("<mis type='account' id='").append(i).append("'>Sam ").append(i).append("</mis>");
            }

            if (i % 333 == 0) {
                stringBuilder.append("<mis type='keyword'>").append("test").append("</mis>");
            }
        }

        return stringBuilder.toString();
    }

    private static void patternTest(String source) {
        long time = System.currentTimeMillis();

        Pattern pattern = Pattern.compile("person");//首先将a标签分离出来
        Matcher matcher = pattern.matcher(source);

        if (matcher.find()) {
            System.out.println("patternTest time " + (System.currentTimeMillis() - time));
        }
    }

    private static void forTest(String source) {
        long time = System.currentTimeMillis();

//        int index = source.indexOf("[person");

        if (source.contains("person")) {
            System.out.println("forTest time " + (System.currentTimeMillis() - time));
        }
    }

    /**
     * <mis type='account' id='账号ID'>liuzhen06</mis>
     * <mis type='keyword'>社会保险</mis>
     */

    private static final String START_TAG = "<mis ";
    private static final String END_TAG = "</mis>";
    private static final String TYPE_ACCOUNT = "account";
    private static final String TYPE_KEYWORD = "keyword";

    private static void findTag(String source) {
        int starTag = source.indexOf(START_TAG);
        if (starTag == -1)
            return;

        int endTag = source.indexOf(END_TAG, starTag);
        if (endTag == -1)
            return;

        String text = source.substring(starTag, endTag + END_TAG.length());

        if (text.contains(TYPE_ACCOUNT)) {
            findAccount(text);
        } else if (text.contains(TYPE_KEYWORD)) {
            findKeyword(text);
        } else {
            findTag(source.substring(endTag));
        }

        findTag(source.substring(endTag));
    }

    private static void findAccount(String text) {
        int idIndex = text.indexOf("id='") + 4;
        int idEndIndex = text.indexOf("'", idIndex);
        String id = text.substring(idIndex, idEndIndex);

        String content = text.substring(idEndIndex + 2, text.indexOf(END_TAG, idEndIndex));

        System.out.println("person " + text + "  id: " + id + "  content :" + content);

    }

    private static void findKeyword(String text) {
        int idIndex = text.indexOf(">") + 1;
        int idEndIndex = text.indexOf(END_TAG, idIndex);

        String content = text.substring(idIndex, idEndIndex);

        System.out.println("keyword " + text + "  content :" + content);
    }


}
