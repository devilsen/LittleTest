package io.github.rockerhieu.emojicon;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import io.github.rockerhieu.emojicon.emoji.Emojicon;

/**
 * author : dongSen
 * date : 2016-12-13 16:49
 * desc :
 */

public class SavedState extends View.BaseSavedState {
    @Emojicon.Type
    int type;
    Emojicon[] data;
    boolean useSystemDefaults;
    int scrollX;
    int scrollY;

    SavedState(Parcelable superState) {
        super(superState);
    }

    private SavedState(Parcel in) {
        super(in);
        //noinspection WrongConstant
        this.type = in.readInt();
        this.data = (Emojicon[]) in.readParcelableArray(Emojicon.class.getClassLoader());
        this.useSystemDefaults = in.readInt() != 0;
        this.scrollX = in.readInt();
        this.scrollY = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(this.type);
        out.writeParcelableArray(data, flags);
        out.writeInt(this.useSystemDefaults ? 1 : 0);
        out.writeInt(this.scrollX);
        out.writeInt(this.scrollY);
    }

    //required field that makes Parcelables from a Parcel
    public static final Parcelable.Creator<SavedState> CREATOR =
            new Parcelable.Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    return new SavedState(in);
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
}