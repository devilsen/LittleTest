/*
 * Copyright (c) 2016 Hieu Rocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.rockerhieu.emojicon;

import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.rockerhieu.emojicon.emoji.Emojicon;
import io.github.rockerhieu.emojicon.listener.OnEmojiconClickListener;

/**
 * Created by rockerhieu on 8/10/16.
 */
public class EmojiconGridView extends GridView implements AdapterView.OnItemClickListener {
    @Emojicon.Type
    private int mType;
    private Emojicon[] mData;
    private boolean mUseSystemDefaults;
    private OnEmojiconClickListener onEmojiconClickedListener;
    private EmojiconAdapter mEmojiAdapter;
    private List<Emojicon> mEmojiList;

    public EmojiconGridView(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.emojiconGridViewStyle);
        setOnItemClickListener(this);
        setSaveEnabled(true);
        mEmojiList = new ArrayList<>();
        mEmojiAdapter = new EmojiconAdapter(context, mEmojiList);
        setAdapter(mEmojiAdapter);
    }

    public EmojiconGridView(Context context) {
        this(context, null);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.data = mData;
        ss.type = mType;
        ss.useSystemDefaults = mUseSystemDefaults;
        ss.scrollX = getScrollX();
        ss.scrollY = getScrollY();
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        mType = ss.type;
        mData = ss.data;
        mUseSystemDefaults = ss.useSystemDefaults;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            setScrollX(ss.scrollX);
            setScrollY(ss.scrollY);
        }
        setEmojiData(mType, mData, mUseSystemDefaults);
    }

    public void setEmojiData(@Emojicon.Type int type, Emojicon[] data, boolean useSystemDefaults) {
        this.mType = type;
        if (this.mType != Emojicon.TYPE_UNDEFINED) {
            this.mData = Emojicon.getEmojicons(type);
        } else {
            this.mData = data;
        }
        this.mUseSystemDefaults = useSystemDefaults;
        if (this.mData == null) {
            mEmojiList.clear();
        } else {
            mEmojiList.clear();
            Collections.addAll(mEmojiList, this.mData);
        }
        mEmojiAdapter.notifyDataSetChanged();
    }

    public void setOnEmojiconClickedListener(OnEmojiconClickListener onEmojiconClickedListener) {
        this.onEmojiconClickedListener = onEmojiconClickedListener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (onEmojiconClickedListener != null) {
//            onEmojiconClickedListener.onEmojiconClicked((Emojicon) parent.getItemAtPosition(position));
            onEmojiconClickedListener.onEmojiconClicked(mEmojiList.get(position));
        }
    }

}
