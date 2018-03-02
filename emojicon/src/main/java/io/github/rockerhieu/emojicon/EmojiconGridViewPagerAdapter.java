package io.github.rockerhieu.emojicon;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.github.rockerhieu.emojicon.listener.OnEmojiconClickListener;
import io.github.rockerhieu.emojicon.util.Utils;

/**
 * author : dongSen
 * date : 2016-12-13 17:00
 * desc :
 */
class EmojiconGridViewPagerAdapter extends PagerAdapter {
    private Context context;
    private final List<EmojiconPage> pages;
    private SavedState[] savedStates;
    private OnEmojiconClickListener onEmojiconClickListener;

    public EmojiconGridViewPagerAdapter(Context context, @NonNull List<EmojiconPage> pages) {
        this.context = context;
        this.pages = pages;
        this.savedStates = new SavedState[pages.size()];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        EmojiconPage emojiconPage = pages.get(position);
        EmojiconGridView emojiGridView = new EmojiconGridView(context);
        emojiGridView.setOnEmojiconClickedListener(onEmojiconClickListener);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            emojiGridView.setId(View.generateViewId());
        } else {
            emojiGridView.setId(Utils.generateViewId());
        }
        container.addView(emojiGridView);
        emojiGridView.setEmojiData(emojiconPage.getType(), emojiconPage.getData(), emojiconPage.isUseSystemDefaults());
        if (savedStates[position] != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>(1);
            sparseArray.put(emojiGridView.getId(), savedStates[position]);
            emojiGridView.restoreHierarchyState(sparseArray);
        }
        return emojiGridView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        EmojiconGridView view = (EmojiconGridView) object;
        savedStates[position] = (SavedState) view.onSaveInstanceState();
        container.removeView((View) object);
    }

    @Override
    public Parcelable saveState() {
        Bundle state = new Bundle();
        state.putParcelableArray("states", savedStates);
        return state;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        if (state != null) {
            Bundle bundle = (Bundle) state;
            Parcelable[] states = bundle.getParcelableArray("states");
            if (states != null) {
                savedStates = new SavedState[states.length];
                for (int i = 0; i < states.length; i++) {
                    savedStates[i] = (SavedState) states[i];
                }
            }
        }
        super.restoreState(state, loader);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setOnEmojiconClickedListener(OnEmojiconClickListener listener) {
        onEmojiconClickListener = listener;
    }
}