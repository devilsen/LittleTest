package com.wuba.view.DataSource;

/**
 * desc :
 * date : 2019-06-06
 *
 * @author : dongSen
 */
public class ImageDataSource {

    private static final String DANTEN = "https://img1.gamersky.com/upimg/pic/2019/01/27/201901271649018467.jpg";
    private static final String HEAT_ICON = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559801571384&di=f4788c24d7eb7399f96fd0db98ee690b&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F07%2F85%2F23%2F59310eb81817b.png";


    private ImageDataSource() {
    }

    public static ImageDataSource getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        static final ImageDataSource instance = new ImageDataSource();
    }

    public String getImage() {
        return DANTEN;
    }

    public String getIcon() {
        return HEAT_ICON;
    }

}
