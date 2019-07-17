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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chenupt@gmail.com on 2015/1/31.
 * Description : Draw a head point and foot point.
 */
class SpringView extends View {

    private Paint paint;
    private Path path;

    private Point headPoint;
    private Point footPoint;
    private Point pullPoint;

    public SpringView(Context context) {
        this(context, null);
    }

    public SpringView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpringView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        headPoint = new Point();
        footPoint = new Point();
        pullPoint = new Point();

        path = new Path();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1);
    }

    private void makePath() {
        float headOffsetX = (float) (headPoint.getRadius() * Math.sin(Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));
        float headOffsetY = (float) (headPoint.getRadius() * Math.cos(Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));

        float footOffsetX = (float) (footPoint.getRadius() * Math.sin(Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));
        float footOffsetY = (float) (footPoint.getRadius() * Math.cos(Math.atan((footPoint.getY() - headPoint.getY()) / (footPoint.getX() - headPoint.getX()))));

        float pullOffsetX = (float) (pullPoint.getRadius() * Math.sin(Math.atan((headPoint.getY() - pullPoint.getY()) / (headPoint.getX() - pullPoint.getX()))));
        float pullOffsetY = (float) (pullPoint.getRadius() * Math.cos(Math.atan((headPoint.getY() - pullPoint.getY()) / (headPoint.getX() - pullPoint.getX()))));

        float x1 = headPoint.getX() - headOffsetX;
        float y1 = headPoint.getY() + headOffsetY;

        float x2 = headPoint.getX() + headOffsetX;
        float y2 = headPoint.getY() - headOffsetY;

        float x3 = footPoint.getX() - footOffsetX;
        float y3 = footPoint.getY() + footOffsetY;

        float x4 = footPoint.getX() + footOffsetX;
        float y4 = footPoint.getY() - footOffsetY;

        float x5 = headPoint.getX() + headOffsetX;
        float y5 = headPoint.getY() + headOffsetY;

        float x6 = headPoint.getX() - headOffsetX;
        float y6 = headPoint.getY() - headOffsetY;

        float x7 = pullPoint.getX() - pullOffsetX;
        float y7 = pullPoint.getY() + pullOffsetY;

        float x8 = pullPoint.getX() + pullOffsetX;
        float y8 = pullPoint.getY() - pullOffsetY;

        float anchorX = (footPoint.getX() + headPoint.getX()) / 2;
        float anchorY = (footPoint.getY() + headPoint.getY()) / 2;

        float anchorX2 = (headPoint.getX() + pullPoint.getX()) / 2;
        float anchorY2 = (headPoint.getY() + pullPoint.getY()) / 2;

        path.reset();

        if (Math.abs(pullPoint.getX() - headPoint.getX()) > headPoint.getRadius()) {
            path.moveTo(x1, y1);
            path.quadTo(anchorX, anchorY, x3, y3);
            path.lineTo(x4, y4);
            path.quadTo(anchorX, anchorY, x2, y2);
            path.lineTo(x1, y1);
        }

        if (Math.abs(pullPoint.getX() - headPoint.getX()) < headPoint.getRadius() + headPoint.getRadius()) {
            path.moveTo(x5, y5);
            path.quadTo(anchorX2, anchorY2, x7, y7);
            path.lineTo(x8, y8);
            path.quadTo(anchorX2, anchorY2, x6, y6);
            path.lineTo(x5, y5);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        makePath();

        paint.setColor(headPoint.getColor());
        canvas.drawPath(path, paint);
        canvas.drawCircle(headPoint.getX(), headPoint.getY(), headPoint.getRadius(), paint);

        paint.setColor(footPoint.getColor());
        canvas.drawCircle(footPoint.getX(), footPoint.getY(), footPoint.getRadius(), paint);

        paint.setColor(pullPoint.getColor());
        canvas.drawCircle(pullPoint.getX(), pullPoint.getY(), pullPoint.getRadius(), paint);

        super.onDraw(canvas);
    }

    public Point getHeadPoint() {
        return headPoint;
    }

    public Point getFootPoint() {
        return footPoint;
    }

    public Point getPullPoint() {
        return pullPoint;
    }

    public void setIndicatorColor(int color) {
        paint.setColor(color);
    }

    public int getIndicatorColor() {
        return paint.getColor();
    }
}
