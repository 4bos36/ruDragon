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
 * ДУБ
 */

public class Oak implements Collides {

    private static final int MOVEMENT = -30;
    private static final int WIDTH = 652;
    private static final int Y = 128;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;

    private Circle foliageUp;
    private Rectangle foliageL, foliageL2, foliageR; // листва
    private Vector2 position;

    private boolean isGo = false;
    private float speed = 1;

    public Oak() {
        position = new Vector2(START_POSITION, Y);
        foliageUp = new Circle(position.x + 330, position.y + 300, 140);
        foliageL = new Rectangle(position.x + 15, position.y + 64, 128, 185);
        foliageL2 = new Rectangle(position.x + 152, position.y + 190, 64, 160);
        foliageR = new Rectangle(position.x + 448, position.y + 135, 160, 220);
    }

    public void update(float deltaTime) {
        if (isGo) {
            position.add(MOVEMENT * deltaTime * speed, 0);
            foliageUp.setPosition(position.x + 330, position.y + 300);
            foliageL.setPosition(position.x + 15, position.y + 64);
            foliageL2.setPosition(position.x + 152, position.y + 190);
            foliageR.setPosition(position.x + 448, position.y + 135);

            if (position.x + WIDTH < 0 || RuDragonGame.getPreference().isContinue()) {
                isGo = false;
                position.x = START_POSITION;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isGo) batch.draw(RuDragonGame.getTextures().textureRegions.get("oak"), position.x, position.y);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (tail.overlaps(foliageL) || Intersector.overlaps(foliageUp, tail) || tail.overlaps(foliageR) || tail.overlaps(foliageL2) ||
                pawsR11.overlaps(foliageL) || Intersector.overlaps(foliageUp, pawsR11) || pawsR11.overlaps(foliageR) || pawsR11.overlaps(foliageL2) ||
                pawsR12.overlaps(foliageL) || Intersector.overlaps(foliageUp, pawsR12) || pawsR12.overlaps(foliageR) || pawsR12.overlaps(foliageL2) ||
                paws.overlaps(foliageL) || Intersector.overlaps(foliageUp, paws) || paws.overlaps(foliageR) || paws.overlaps(foliageL2) ||
                head.overlaps(foliageL) || Intersector.overlaps(foliageUp, head) || head.overlaps(foliageR) || head.overlaps(foliageL2) ||
                wings.overlaps(foliageL) || Intersector.overlaps(foliageUp, wings) || wings.overlaps(foliageR) || wings.overlaps(foliageL2));
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
            foliageUp = null;
            foliageL = null;
            foliageL2 = null;
            foliageR = null;
            position = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}