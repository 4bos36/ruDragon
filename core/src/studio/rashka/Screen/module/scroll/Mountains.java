package studio.rashka.Screen.module.scroll;

import studio.rashka.Lib.Scrollable;

public class Mountains extends Scrollable {
    public Mountains(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void scrollSpeed(float speed) {
        scrollSpeed = speed;
    }
}
