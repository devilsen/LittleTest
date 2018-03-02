package io.github.rockerhieu.emojicon;

/**
 * author : dongSen
 * date : 2017/8/7
 * desc : 特殊字符点击事件
 */
public interface MisTextClickListener {

    /**
     * 点击字符
     *
     * @param key     关键处理字符（如account的 id）
     * @param content 显示的字
     * @param type    点击事件类型{@link MisTextHandler TYPE_ACCOUNT and TYPE_KEYWORD}
     */
    void onTextClick(String key, String content, int type);

}
