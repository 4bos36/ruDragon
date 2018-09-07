package studio.rashka.Screen.module.scroll;

import studio.rashka.Lib.Scrollable;

public class Field extends Scrollable {
    public Field(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void scrollSpeed(float speed) {
        scrollSpeed = speed;
    }
}
