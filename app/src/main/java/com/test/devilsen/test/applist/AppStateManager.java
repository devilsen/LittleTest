package com.test.devilsen.test.applist;

import java.util.ArrayList;

/**
 * author : dongSen
 * date : 2017/8/22
 * desc :
 */
public class AppStateManager {

    private static class Holder {
        static final AppStateManager instance = new AppStateManager();
    }

    public static AppStateManager getInstance() {
        return Holder.instance;
    }

    private ArrayList<AppModel> list = new ArrayList<>(30);
    private ArrayList<AppModel> appModels;


    public void setData(ArrayList<AppModel> list) {
        this.list.addAll(list);
    }

    public ArrayList<AppModel> getList() {
        return list;
    }

    public ArrayList<AppModel> getAddList() {
        if (appModels == null) {
            appModels = new ArrayList<>();
        } else {
            return appModels;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isAdd) {
                appModels.add(list.get(i));
            }
        }

        appModels.add(new AppModel("100000", "add"));

        return appModels;
    }

    public int addApp(AppModel model) {
        int position = appModels.size() - 1;
        if (position < 0)
            return -1;

        appModels.add(position, model);

        model.isAdd = true;

        return position;
    }

    public int deleteApp(AppModel model) {
        for (int i = 0; i < appModels.size(); i++) {
            if (appModels.get(i).equals(model)) {
                appModels.remove(i);
                model.isAdd = false;
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < appModels.size(); i++) {
            s += appModels.get(i).name;
        }
        return s;
    }
}
