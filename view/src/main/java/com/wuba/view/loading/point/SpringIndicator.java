/*
 * Copyright 2015 chenupt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wuba.view.loading.point;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.wuba.view.R;

/**
 * Created by chenupt@gmail.com on 2015/1/31.
 * Description : Tab layout container
 */
public class SpringIndicator extends LinearLayout {

    private static final int INDICATOR_ANIM_DURATION = 3000;
    private static final int DEFAULT_NUMBER = 4;

    private float acceleration = 0.5f;
    private float headMoveOffset = 0.6f;
    private float footMoveOffset = 1 - headMoveOffset;
    private float radiusMax;
    private float radiusMin;
    private float radiusOffset;

    private float textSize;
    private int textColorId;
    private int textBgResId;
    private int selectedTextColorId;
    private int indicatorColorId;
    private int indicatorColorsId;

    private SpringView springView;

    public SpringIndicator(Context context) {
        this(context, null);
    }

    public SpringIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        textColorId = R.color.si_default_text_color;
        selectedTextColorId = R.color.si_default_text_color_selected;
        indicatorColorId = R.color.si_default_indicator_bg;
        textSize = getResources().getDimension(R.dimen.si_default_text_size);
        radiusMax = getResources().getDimension(R.dimen.si_default_radius_max);
        radiusMin = getResources().getDimension(R.dimen.si_default_radius_min);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SpringIndicator);
        textColorId = a.getResourceId(R.styleable.SpringIndicator_siTextColor, textColorId);
        selectedTextColorId = a.getResourceId(R.styleable.SpringIndicator_siSelectedTextColor, selectedTextColorId);
        textSize = a.getDimension(R.styleable.SpringIndicator_siTextSize, textSize);
        textBgResId = a.getResourceId(R.styleable.SpringIndicator_siTextBg, 0);
        indicatorColorId = a.getResourceId(R.styleable.SpringIndicator_siIndicatorColor, indicatorColorId);
        indicatorColorsId = a.getResourceId(R.styleable.SpringIndicator_siIndicatorColors, 0);
        radiusMax = a.getDimension(R.styleable.SpringIndicator_siRadiusMax, radiusMax);
        radiusMin = a.getDimension(R.styleable.SpringIndicator_siRadiusMin, radiusMin);
        a.recycle();

        radiusOffset = radiusMax - radiusMin;

        setOrientation(LinearLayout.HORIZONTAL);

        initSpringView();
    }


    private void initSpringView() {
        addPointView();
        animationController();
    }

    private void addPointView() {
        springView = new SpringView(getContext());
        springView.setIndicatorColor(getResources().getColor(indicatorColorId));

        Point headPoint = springView.getHeadPoint();
        Point footPoint = springView.getFootPoint();

        headPoint.setX(100);
        headPoint.setY(80);
        headPoint.setRadius(50);

        footPoint.setX(100);
        footPoint.setY(80);
        footPoint.setRadius(50);


        addView(springView);
    }

    private void animationController() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(100, 300);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();

//                animation(1, (int) animatedValue);
//                springView.invalidate();

                Point footPoint = springView.getFootPoint();
                footPoint.setX(animatedValue);
                springView.invalidate();
            }
        });

        valueAnimator.start();
    }

    private void animation(int position, int positionOffset) {
        // radius
        float radiusOffsetHead = 0.5f;
        if (positionOffset < radiusOffsetHead) {
            springView.getHeadPoint().setRadius(radiusMin);
        } else {
            springView.getHeadPoint().setRadius(((positionOffset - radiusOffsetHead) / (1 - radiusOffsetHead) * radiusOffset + radiusMin));
        }
        float radiusOffsetFoot = 0.5f;
        if (positionOffset < radiusOffsetFoot) {
            springView.getFootPoint().setRadius((1 - positionOffset / radiusOffsetFoot) * radiusOffset + radiusMin);
        } else {
            springView.getFootPoint().setRadius(radiusMin);
        }

        // x
        float headX = 1f;
        if (positionOffset < headMoveOffset) {
            float positionOffsetTemp = positionOffset / headMoveOffset;
            headX = (float) ((Math.atan(positionOffsetTemp * acceleration * 2 - acceleration) + (Math.atan(acceleration))) / (2 * (Math.atan(acceleration))));
        }
        springView.getHeadPoint().setX(getTabX(position) - headX * getPositionDistance(position));
        float footX = 0f;
        if (positionOffset > footMoveOffset) {
            float positionOffsetTemp = (positionOffset - footMoveOffset) / (1 - footMoveOffset);
            footX = (float) ((Math.atan(positionOffsetTemp * acceleration * 2 - acceleration) + (Math.atan(acceleration))) / (2 * (Math.atan(acceleration))));
        }
        springView.getFootPoint().setX(getTabX(position) - footX * getPositionDistance(position));

        // reset radius
        if (positionOffset == 0) {
            springView.getHeadPoint().setRadius(radiusMax);
            springView.getFootPoint().setRadius(radiusMax);
        }
    }

    private float getPositionDistance(int position) {
//        float tarX = tabs.get(position + 1).getX();
//        float oriX = tabs.get(position).getX();
//        return oriX - tarX;
        return 200;
    }

    private float getTabX(int position) {
        return 0;
    }

}
