package io.github.rockerhieu.emojicon;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * author : dongSen
 * date : 2017/8/4
 * desc : 解析美事中出现的特有字符串，（为了以最快的效率，拓展性不高）
 * <p>
 * 出现的两个标签
 * <mis type='account' id='账号ID'>name</mis>
 * <mis type='keyword'>社会保险</mis>
 */

public class MisTextHandler {

    private static final int TYPE_ACCOUNT = 1;
    private static final int TYPE_KEYWORD = 2;

    private static final int TYPE_ACCOUNT_COLOR = Color.parseColor("#4A87EE");
    private static final int TYPE_KEYWORD_COLOR = Color.parseColor("#E65858");


    private static SpannableStringBuilder spannable;

    private static MisTextClickListener misTextClickListener;

    public static String makeData() {
        int length = 100;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(String.valueOf(i));

            String test = "\n" +
                    "测试回复\n" +
                    "<mis type=\"account\">\n" +
                    "</mis>";

            if (i % 100 == 0) {
                stringBuilder.append("<mis id=\"").append(i).append("\"")
                        .append(" type=\"account\" ")
                        .append(">")
                        .append("name").append(i)
                        .append("</mis>");
            }

//            if (i % 333 == 0) {
//                stringBuilder.append("<mis type=\"keyword\">").append("test").append("</mis>");
//            }
        }

        return stringBuilder.toString();
    }

    private static final String START_TAG = "<mis ";
    private static final String END_TAG = "</mis>";
    private static final String TYPE_ACCOUNT_TAG = "account";
    private static final String TYPE_KEYWORD_TAG = "keyword";

    private static int frontIndex;//递归前置标记

    private static void findTag(String source) {
        int starTag = source.indexOf(START_TAG);
        if (starTag == -1)
            return;

        int endTag = source.indexOf(END_TAG, starTag);
        if (endTag == -1)
            return;

        endTag += 6;//END_TAG 的长度

        String text = source.substring(starTag, endTag);

        if (text.contains(TYPE_ACCOUNT_TAG)) {
            findAccount(text, starTag, endTag);
        } else if (text.contains(TYPE_KEYWORD_TAG)) {
            findKeyword(text, starTag, endTag);
        }

        findTag(source.substring(endTag));

    }

    private static void findAccount(String text, int starTag, int endTag) {
        int idIndex = text.indexOf("id=\"") + 4;
        int idEndIndex = text.indexOf("\"", idIndex);
        String id = text.substring(idIndex, idEndIndex);

        int closeIndex = text.indexOf(">") + 1;
        String content = text.substring(closeIndex, text.indexOf(END_TAG, closeIndex));

        setClickSpan(id, content, starTag, endTag, TYPE_ACCOUNT);

    }

    private static void findKeyword(String text, int starTag, int endTag) {
        int idIndex = text.indexOf(">") + 1;
        int idEndIndex = text.indexOf(END_TAG, idIndex);

        String content = text.substring(idIndex, idEndIndex);

        setClickSpan(content, content, starTag, endTag, TYPE_KEYWORD);

    }

    private static void setClickSpan(final String key, final String content, int star, int end, final int type) {
        star += frontIndex;
        end += frontIndex;

        spannable.replace(star, end, content);

        frontIndex = star + content.length();

        spannable.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if (misTextClickListener != null)
                    misTextClickListener.onTextClick(key, content, type);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                if (type == TYPE_ACCOUNT) {
                    ds.setColor(TYPE_ACCOUNT_COLOR);
                } else {
                    ds.setColor(TYPE_KEYWORD_COLOR);
                }
                ds.setUnderlineText(false);
            }

        }, star, star + content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 为textView添加点击span
     *
     * @return 如果可以点击为true，否则为false
     */
    static boolean addClickSpan(SpannableStringBuilder spannableStringBuilder) {
        frontIndex = 0;

        spannable = spannableStringBuilder;

        findTag(spannable.toString());

        return frontIndex != 0;
    }

    static void setTextClickListener(MisTextClickListener clickListener) {
        misTextClickListener = clickListener;
    }
}
