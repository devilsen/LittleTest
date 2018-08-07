package com.test.devilsen.test.DP;

/**
 * desc :
 * date : 2018/7/26
 *
 * @author : dongSen
 */
public class AbstractFactory {

    //Button
    class Button {/* ...*/}
    class WinButton extends Button {/* ...*/}
    class MacButton extends Button{/* ...*/}

    //Icon
    class Icon {/* ...*/}
    class WinIcon extends Icon{/* ...*/}
    class MacIcon extends Icon{/* ...*/}


    //抽象工厂
    abstract class OSFactory{
        abstract Button createButton();
        abstract Icon createIcon();
    }

    //工厂接口
    class WinFactory extends OSFactory{

        @Override
        Button createButton() {
            return new WinButton();
        }

        @Override
        Icon createIcon() {
            return new WinIcon();
        }
    }

    class MacFactory extends OSFactory{

        @Override
        Button createButton() {
            return new MacButton();
        }

        @Override
        Icon createIcon() {
            return new MacIcon();
        }
    }
}
