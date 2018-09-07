package studio.rashka.Lib;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

    // Protected похож private, но позволяет наследоваться в дочерних классах.
    protected Vector2 position;
    protected int width;
    protected int height;
    private boolean isScrolledLeft;
    protected float scrollSpeed;

    public Scrollable(float x, float y, int width, int height) {
        position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void update(float deltaTime) {
        position.add(scrollSpeed * deltaTime, 0);

        // Если объект Scrollable более не виден:
        if (position.x + width < 0) isScrolledLeft = true;
    }

    // Reset: Нужно переопределять в дочернем классе, если необходимо описать другое поведение
    public void reset(float newX) {
        position.x = newX;
        isScrolledLeft = false;
    }

    // Методы доступа к переменым класса
    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}