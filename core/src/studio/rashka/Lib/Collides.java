package studio.rashka.Lib;

import com.badlogic.gdx.math.Rectangle;

public interface Collides { // столкновение с драконом
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings);
}
