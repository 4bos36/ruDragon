package studio.rashka.Models.obstacle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import studio.rashka.Lib.Collides;
import studio.rashka.RuDragonGame;

/**
 * Ларец
 */

public class Casket implements Collides {

    private static final int MOVEMENT = -30,
            WIDTH = 128,
            Y = 128;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;

    private Rectangle box;
    private Vector2 position, positionAnim;
    private float speed = 1;

    private Random random;
    private float eaten;
    private int choice;
    private boolean isEaten = false, isGo = false, isShowAnim = false;

    private ParticleEffect ham, xp;

    public Casket() {
        position = new Vector2(START_POSITION, Y);
        positionAnim = new Vector2(START_POSITION, Y);
        random = new Random();

        choice = random.nextInt(2);
        eaten = random.nextInt(151) + 50;

        box = new Rectangle(position.x + 16, position.y, 96, 80);

        ham = new ParticleEffect();
        xp = new ParticleEffect();
        ham.load(Gdx.files.internal("particles/Ham.p"), Gdx.files.internal("particles"));
        xp.load(Gdx.files.internal("particles/XP.p"), Gdx.files.internal("particles"));
        ham.start();
        xp.start();
    }

    public void update(float deltaTime) {
        if (isGo) {
            position.add(MOVEMENT * deltaTime * speed, 0);
            box.setPosition(position.x + 16, position.y);

            if (position.x + WIDTH < 0 || isEaten || RuDragonGame.getPreference().isContinue()) {
                if (isEaten) {
                    positionAnim.x = position.x + 64;
                    isShowAnim = true;
                }
                isGo = false;
                isEaten = false;
                position.x = START_POSITION;
                eaten = random.nextInt(150) + 51;
            }
        }
        if (isShowAnim) {
            positionAnim.add(MOVEMENT * deltaTime * speed, 0);
            if (choice == 0) {
                xp.update(deltaTime);
                if (xp.isComplete()) {
                    xp.reset();
                    positionAnim.x = START_POSITION;
                    choice = random.nextInt(2);
                    isShowAnim = false;
                }
            }
            else if (choice == 1) {
                ham.update(deltaTime);
                if (ham.isComplete()) {
                    ham.reset();
                    positionAnim.x = START_POSITION;
                    choice = random.nextInt(2);
                    isShowAnim = false;
                }
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (choice == 0) {
            batch.draw(RuDragonGame.getTextures().textureRegions.get("CasketXP"), position.x, position.y);
            if (isShowAnim) {
                xp.draw(batch);
                xp.setPosition(positionAnim.x, positionAnim.y);
            }
        }
        else if (choice == 1) {
            batch.draw(RuDragonGame.getTextures().textureRegions.get("CasketXam"), position.x, position.y);
            if (isShowAnim) {
                ham.draw(batch);
                ham.setPosition(positionAnim.x, positionAnim.y);
            }
        }
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (tail.overlaps(box) || pawsR11.overlaps(box) || pawsR12.overlaps(box) || paws.overlaps(box) || head.overlaps(box) ||
        wings.overlaps(box));
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

    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }

    public float getEATEN() {
        return eaten;
    }

    public int getChoice() {
        return choice;
    }

    public void setGo(boolean go) {
        isGo = go;
    }

    public boolean isGo() {
        return isGo;
    }

    public void dispose() {
        try {
            xp.dispose();
            ham.dispose();
            random = null;
            box = null;
            position = null;
            positionAnim = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}