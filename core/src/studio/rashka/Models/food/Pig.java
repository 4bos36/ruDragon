package studio.rashka.Models.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import studio.rashka.Lib.AnimationModels;
import studio.rashka.Lib.Collides;
import studio.rashka.RuDragonGame;

/**
 * СВИНЬЯ
 */

public class Pig implements Collides {

    private static final int MOVEMENT = -30;
    private static final int WIDTH = 154;
    private static final int Y = 128;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;
    private static final float EATEN = 4.0f;

    private Circle bodyPigL, bodyPigR;
    private Vector2 position, positionAnim;

    private boolean isGo = false,
            isEaten = false,
            isSound = true,
            isShowAnim = false;
    private float speed = 1;

    private AnimationModels animationModels;
    private ParticleEffect meat;

    public Pig() {
        animationModels = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("pig")), 11, 1.2f);
        position = new Vector2(START_POSITION, Y);
        positionAnim = new Vector2(START_POSITION, Y);

        meat = new ParticleEffect();
        meat.load(Gdx.files.internal("particles/Meat.p"), Gdx.files.internal("particles"));
        meat.start();

        bodyPigL = new Circle(position.x + 50, position.y + 55, 40);
        bodyPigR = new Circle(position.x + 80, position.y + 52, 40);
    }

    public void update(float deltaTime) {
        if (isGo) {
            if (position.x > RuDragonGame.WIDTH - 10 && position.x < RuDragonGame.WIDTH && isSound) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("pig").play();
                isSound = false;
            }
            animationModels.update(deltaTime);
            position.add(MOVEMENT * deltaTime * speed, 0);

            bodyPigL.setPosition(position.x + 50, position.y + 55);
            bodyPigR.setPosition(position.x + 80, position.y + 52);

            if (position.x + WIDTH < 0 || isEaten || RuDragonGame.getPreference().isContinue()) {
                if (isEaten) {
                    RuDragonGame.getPreference().savePigLove(1);
                    positionAnim.x = position.x + 77;
                    isShowAnim = true;
                }
                isGo = false;
                isEaten = false;
                isSound = true;
                position.x = START_POSITION;
            }
        }
        if (isShowAnim) {
            positionAnim.add(MOVEMENT * deltaTime * speed, 0);
            meat.update(deltaTime);
            if (meat.isComplete()) {
                meat.reset();
                positionAnim.x = START_POSITION;
                isShowAnim = false;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isGo) batch.draw(animationModels.getFrame(), position.x, position.y, animationModels.getFrame().getRegionWidth() / 1.2f, animationModels.getFrame().getRegionHeight() / 1.2f);
        if (isShowAnim) {
            meat.setPosition(positionAnim.x, positionAnim.y);
            meat.draw(batch);
        }
    }

    public void homeLoad(float deltaTime) { // для экрана загрузки
        animationModels.update(deltaTime);
    }

    public void renderHelp(SpriteBatch batch) {
        batch.draw(animationModels.getFrame(), RuDragonGame.WIDTH - 950, 128, animationModels.getFrame().getRegionWidth() / 1.2f, animationModels.getFrame().getRegionHeight() / 1.2f);
    }

    public void renderUpgrade(SpriteBatch batch) {
        batch.draw(animationModels.getFrame(), RuDragonGame.WIDTH / 2 - 256, 128, animationModels.getFrame().getRegionWidth() / 1.4f, animationModels.getFrame().getRegionHeight() / 1.4f);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (Intersector.overlaps(bodyPigL, tail) || Intersector.overlaps(bodyPigL, pawsR11) || Intersector.overlaps(bodyPigL, pawsR12) ||
                Intersector.overlaps(bodyPigL, paws) || Intersector.overlaps(bodyPigL, head) || Intersector.overlaps(bodyPigL, wings) ||
                Intersector.overlaps(bodyPigR, tail) || Intersector.overlaps(bodyPigR, pawsR11) || Intersector.overlaps(bodyPigR, pawsR12) ||
                Intersector.overlaps(bodyPigR, paws) || Intersector.overlaps(bodyPigR, head) || Intersector.overlaps(bodyPigR, wings));
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
        return EATEN;
    }

    public void setGo(boolean go) {
        isGo = go;
    }

    public boolean isGo() {
        return isGo;
    }

    public void dispose() {
        try {
            meat.dispose();
            position = null;
            positionAnim = null;
            animationModels = null;
            bodyPigL = null;
            bodyPigR = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}