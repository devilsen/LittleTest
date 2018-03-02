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
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Arrays;
import java.util.List;

import io.github.rockerhieu.emojicon.listener.OnEmojiconClickListener;
import io.github.rockerhieu.emojicon.listener.OnEmojiconDeleteListener;

/**
 * Created by rockerhieu on 8/31/16.
 */
public class EmojiconsView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private Context mContext;
    ViewPager mViewPager;
    private ViewGroup mTabsContainer;
    private View[] mTabs;
    private View mLastTab;
    private ImageButton deleteBtn;
    private EmojiconGridViewPagerAdapter emojiconGridViewPagerAdapter;
    private OnEmojiconDeleteListener emojiconDeleteListener;

    public EmojiconsView(Context context) {
        this(context, null);
    }

    public EmojiconsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.emojicons_view, this);
        mViewPager = (ViewPager) findViewById(R.id.emojis_pager);
        mTabsContainer = (ViewGroup) findViewById(R.id.emojis_tab);
        deleteBtn = (ImageButton) findViewById(R.id.emojis_backspace);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mViewPager.removeOnPageChangeListener(this);
    }

    public void setPages(@NonNull List<EmojiconPage> pages) {
        if (mTabs == null || mTabs.length != pages.size()) {
            mTabs = new View[pages.size()];
        } else {
            Arrays.fill(mTabs, null);
        }

        for (int i = 0; i < mTabsContainer.getChildCount() - 2; i++) {
            mTabsContainer.removeViewAt(0);
        }

        int index = 0;
        for (EmojiconPage page : pages) {
            addTabIcon(page, index++);
            addTabDivider();
        }
        onPageSelected(0);
        emojiconGridViewPagerAdapter = new EmojiconGridViewPagerAdapter(mContext, pages);
        mViewPager.setAdapter(emojiconGridViewPagerAdapter);

        deleteBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emojiconDeleteListener != null)
                    emojiconDeleteListener.onEmojiconDelete();
            }
        });
    }

    private void addTabDivider() {
        View divider = new View(mContext);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mViewPager.setBackgroundColor(mContext.getResources().getColor(R.color.horizontal_vertical));
        } else {
            mViewPager.setBackgroundColor(mContext.getColor(R.color.horizontal_vertical));
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
        mTabsContainer.addView(divider, mTabsContainer.getChildCount() - 2, params);
    }

    private void addTabIcon(EmojiconPage page, int index) {
        ImageButton icon = new ImageButton(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            icon.setBackground(null);
        }
        icon.setScaleType(ImageView.ScaleType.CENTER);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            icon.setImageDrawable(mContext.getResources().getDrawable(page.getIcon()));
        } else {
            icon.setImageDrawable(mContext.getDrawable(page.getIcon()));
        }
        mTabsContainer.addView(icon, mTabsContainer.getChildCount() - 2, params);
        mTabs[index] = icon;
        final int indexToMove = index;
        icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(indexToMove, true);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (mTabs == null || position >= mTabs.length) {
            return;
        }
        if (mLastTab != null) {
            mLastTab.setSelected(false);
        }
        mLastTab = mTabs[position];
        mLastTab.setSelected(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void setOnEmojiconClickedListener(OnEmojiconClickListener listener) {
        emojiconGridViewPagerAdapter.setOnEmojiconClickedListener(listener);
    }

    public void setOnEmojiconDeleteListener(OnEmojiconDeleteListener listener) {
        emojiconDeleteListener = listener;
    }


}
