package com.test.devilsen.test.applist;

/**
 * author : dongSen
 * date : 2017/8/22
 * desc :
 */
public class AppModel {

    public String id;
    public String name;
    public String icon;
    public boolean isAdd;

    public AppModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppModel appModel = (AppModel) o;

        if (isAdd != appModel.isAdd) return false;
        if (id != null ? !id.equals(appModel.id) : appModel.id != null) return false;
        if (name != null ? !name.equals(appModel.name) : appModel.name != null) return false;
        return icon != null ? icon.equals(appModel.icon) : appModel.icon == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (isAdd ? 1 : 0);
        return result;
    }
}
