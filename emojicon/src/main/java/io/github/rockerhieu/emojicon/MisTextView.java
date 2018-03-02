/*
 * Copyright 2014 Hieu Rocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.rockerhieu.emojicon;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;

/**
 * author : dongSen
 * date : 2017/8/7 下午7:41
 * desc : 处理聊天中的特定字符view
 */
public class MisTextView extends EmojiconTextView {

    public MisTextView(Context context) {
        super(context);
        init(null);
    }

    public MisTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MisTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setText(getText());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        boolean clickAble = false;
        if (!TextUtils.isEmpty(text)) {
            SpannableStringBuilder builder = new SpannableStringBuilder(text);
            clickAble = MisTextHandler.addClickSpan(builder);
            text = builder;
        }

        super.setText(text, type);

        if (clickAble) {
            setMovementMethod(LinkMovementMethod.getInstance());
        }

    }

    /**
     * 特殊字符点击事件
     *
     * @param clickListener 字符点击监听
     */
    public void setTextClickListener(MisTextClickListener clickListener) {
        MisTextHandler.setTextClickListener(clickListener);
    }

}
