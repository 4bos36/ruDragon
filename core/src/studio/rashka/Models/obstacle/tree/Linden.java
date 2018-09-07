package studio.rashka.Models.obstacle.tree;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import studio.rashka.Lib.Collides;
import studio.rashka.RuDragonGame;

/**
 * ЛИПА
 */

public class Linden implements Collides {

    private static final int MOVEMENT = -30;
    private static final int WIDTH = 489;
    private static final int Y = 128;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;

    private Rectangle foliageL, // ствол
            foliageR; // листва
    private Circle shell; // мокушка
    private Vector2 position;

    private boolean isGo = false;
    private float speed = 1;

    public Linden() {
        position = new Vector2(START_POSITION, Y);
        shell = new Circle(position.x + 210, position.y + 180, 160);
        foliageL = new Rectangle(position.x + 10, position.y + 32, 32, 128);
        foliageR = new Rectangle(position.x + 384, position.y + 48, 64, 160);
    }

    public void update(float deltaTime) {
        if (isGo) {
            position.add(MOVEMENT * deltaTime * speed, 0);
            shell.setPosition(position.x + 210, position.y + 180);
            foliageL.setPosition(position.x + 10, position.y + 32);
            foliageR.setPosition(position.x + 384, position.y + 48);

            if (position.x + WIDTH < 0 || RuDragonGame.getPreference().isContinue()) {
                isGo = false;
                position.x = START_POSITION;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isGo) batch.draw(RuDragonGame.getTextures().textureRegions.get("linden"), position.x, position.y);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (tail.overlaps(foliageL) || tail.overlaps(foliageR) ||  Intersector.overlaps(shell, tail) ||
                pawsR11.overlaps(foliageL) || pawsR11.overlaps(foliageR) || Intersector.overlaps(shell, pawsR11) ||
                pawsR12.overlaps(foliageL) || pawsR12.overlaps(foliageR) || Intersector.overlaps(shell, pawsR12) ||
                paws.overlaps(foliageL) || paws.overlaps(foliageR) || Intersector.overlaps(shell, paws) ||
                head.overlaps(foliageL) || head.overlaps(foliageR) || Intersector.overlaps(shell, head) ||
                wings.overlaps(foliageL) || wings.overlaps(foliageR) || Intersector.overlaps(shell, wings));
    }

    public float getX() {
        return position.x;
    }

    public float setAddX(float addX) {
        return position.x = START_POSITION + addX;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setGo(boolean go) {
        isGo = go;
    }

    public boolean isGo() {
        return isGo;
    }

    public void dispose() {
        try {
            shell = null;
            foliageL = null;
            foliageR = null;
            position = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}