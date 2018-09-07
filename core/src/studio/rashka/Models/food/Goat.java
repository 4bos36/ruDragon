package studio.rashka.Models.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import studio.rashka.Lib.AnimationModels;
import studio.rashka.Lib.Collides;
import studio.rashka.RuDragonGame;

/**
 * КОЗА
 */

public class Goat implements Collides {

    private static final int MOVEMENT = -30;
    private static final int WIDTH = 128;
    private static final int Y = 128;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;
    private static final float EATEN = 2.0f;

    private Rectangle bodyGoat, hornsGoat;
    private Vector2 position, positionAnim;

    private boolean isGo = false,
            isEaten = false,
            isSound = true,
            isShowAnim = false;
    private float speed = 1;

    private AnimationModels animationModels;
    private ParticleEffect meat;

    public Goat() {
        animationModels = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("goat")), 13, 0.8f);
        position = new Vector2(START_POSITION, Y);
        positionAnim = new Vector2(START_POSITION, Y);

        meat = new ParticleEffect();
        meat.load(Gdx.files.internal("particles/Meat.p"), Gdx.files.internal("particles"));
        meat.start();

        bodyGoat = new Rectangle(position.x + 20, position.y + 15, 60, 45);
        hornsGoat = new Rectangle(position.x + 50, position.y + 62, 32, 27);
    }

    public void update(float deltaTime) {
        if (isGo) {
            if (position.x > RuDragonGame.WIDTH - 10 && position.x < RuDragonGame.WIDTH && isSound) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("goat").play();
                isSound = false;
            }
            animationModels.update(deltaTime);
            position.add(MOVEMENT * deltaTime * speed, 0);

            bodyGoat.setPosition(position.x + 20, position.y + 15);
            hornsGoat.setPosition(position.x + 50, position.y + 62);

            if (position.x + WIDTH < 0 || isEaten || RuDragonGame.getPreference().isContinue()) {
                if (isEaten) {
                    RuDragonGame.getPreference().saveGoatLove(1);
                    positionAnim.x = position.x + 64;
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
        if (isGo) batch.draw(animationModels.getFrame(), position.x, position.y, animationModels.getFrame().getRegionWidth() / 1.3f, animationModels.getFrame().getRegionHeight() / 1.3f);
        if (isShowAnim) {
            meat.setPosition(positionAnim.x, positionAnim.y);
            meat.draw(batch);
        }
    }

    public void homeLoad(float deltaTime) { // для экрана загрузки
        animationModels.update(deltaTime);
    }

    public void renderHelp(SpriteBatch batch) {
        batch.draw(animationModels.getFrame(), 228, 128, animationModels.getFrame().getRegionWidth() / 1.3f, animationModels.getFrame().getRegionHeight() / 1.3f);
    }

    public void renderUpgrade(SpriteBatch batch) {
        batch.draw(animationModels.getFrame(), 228, 128, animationModels.getFrame().getRegionWidth() / 1.5f, animationModels.getFrame().getRegionHeight() / 1.5f);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (tail.overlaps(bodyGoat) || tail.overlaps(hornsGoat) ||
                pawsR11.overlaps(bodyGoat) || pawsR11.overlaps(hornsGoat) ||
                pawsR12.overlaps(bodyGoat) || pawsR12.overlaps(hornsGoat) ||
                paws.overlaps(bodyGoat) || paws.overlaps(hornsGoat) ||
                head.overlaps(bodyGoat) || head.overlaps(hornsGoat) ||
                wings.overlaps(bodyGoat) || wings.overlaps(hornsGoat));
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
            bodyGoat = null;
            hornsGoat = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}