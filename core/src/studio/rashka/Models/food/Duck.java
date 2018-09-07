package studio.rashka.Models.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import studio.rashka.Lib.AnimationModels;
import studio.rashka.Lib.Collides;
import studio.rashka.RuDragonGame;

/**
 * УТКА
 */

public class Duck implements Collides {

    private static final int MOVEMENT = -50;
    private static final int WIDTH = 80;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;
    private int posY, speedFlock, randomSpeed;
    private static final float EATEN = 1.0f;

    private Vector2 position, positionAnim;
    private AnimationModels animationModels, animationModelsFaster;
    private Rectangle bodyDuck, wingsDuck;
    private Random random;
    private boolean isEaten = false,
            isGo = false,
            isSound = true,
            isShowAnim = false;

    private ParticleEffect pen;

    public Duck() {
        animationModels = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("duck")), 17, 0.5f);
        animationModelsFaster = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("duck")), 17, 0.2f);
        random = new Random();
        randomSpeed = random.nextInt(15);
        posY = RuDragonGame.HEIGHT - random.nextInt(RuDragonGame.HEIGHT / 2) - 64;
        position = new Vector2(START_POSITION, posY);
        positionAnim = new Vector2(START_POSITION, posY);

        pen = new ParticleEffect();
        pen.load(Gdx.files.internal("particles/Plumage.p"), Gdx.files.internal("particles"));
        pen.start();

        bodyDuck = new Rectangle(position.x + 4, position.y + 25, 70, 10);
        wingsDuck = new Rectangle(position.x + 22, position.y + 35, 20, 10);
    }

    public Duck(int speedFlock) {
        this.speedFlock = speedFlock;
        animationModels = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("duck")), 17, 0.3f);
        random = new Random();
        posY = RuDragonGame.HEIGHT - random.nextInt(640) - 20;
        position = new Vector2(-256, posY);
    }

    public void update(float deltaTime) {
        if (isGo) {
            if (position.x < RuDragonGame.WIDTH && isSound) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duck").play();
                isSound = false;
            }
            position.add((MOVEMENT - randomSpeed) * deltaTime, 0);
            bodyDuck.setPosition(position.x + 4, position.y + 25);

            if (position.x > 500) {
                animationModels.update(deltaTime);
                if (animationModels.getFrameCount() == 0) wingsDuck.setPosition(position.x + 22, position.y + 45);
                else if (animationModels.getFrameCount() == 1 || animationModels.getFrameCount() == 16) wingsDuck.setPosition(position.x + 22, position.y + 44);
                else if (animationModels.getFrameCount() == 2 || animationModels.getFrameCount() == 15) wingsDuck.setPosition(position.x + 22, position.y + 42);
                else if (animationModels.getFrameCount() == 3 || animationModels.getFrameCount() == 14) wingsDuck.setPosition(position.x + 22, position.y + 38);
                else if (animationModels.getFrameCount() == 4 || animationModels.getFrameCount() == 13) wingsDuck.setPosition(position.x + 22, position.y + 28);
                else if (animationModels.getFrameCount() == 5 || animationModels.getFrameCount() == 12) wingsDuck.setPosition(position.x + 22, position.y + 22);
                else if (animationModels.getFrameCount() == 6 || animationModels.getFrameCount() == 11) wingsDuck.setPosition(position.x + 22, position.y + 16);
                else if (animationModels.getFrameCount() == 7 || animationModels.getFrameCount() == 10) wingsDuck.setPosition(position.x + 22, position.y + 11);
                else if (animationModels.getFrameCount() == 8 || animationModels.getFrameCount() == 9) wingsDuck.setPosition(position.x + 22, position.y + 7);
            } else if (position.x <= 500) {
                animationModelsFaster.update(deltaTime);
                if (animationModelsFaster.getFrameCount() == 0) wingsDuck.setPosition(position.x + 22, position.y + 45);
                else if (animationModelsFaster.getFrameCount() == 1 || animationModelsFaster.getFrameCount() == 16) wingsDuck.setPosition(position.x + 22, position.y + 44);
                else if (animationModelsFaster.getFrameCount() == 2 || animationModelsFaster.getFrameCount() == 15) wingsDuck.setPosition(position.x + 22, position.y + 42);
                else if (animationModelsFaster.getFrameCount() == 3 || animationModelsFaster.getFrameCount() == 14) wingsDuck.setPosition(position.x + 22, position.y + 38);
                else if (animationModelsFaster.getFrameCount() == 4 || animationModelsFaster.getFrameCount() == 13) wingsDuck.setPosition(position.x + 22, position.y + 28);
                else if (animationModelsFaster.getFrameCount() == 5 || animationModelsFaster.getFrameCount() == 12) wingsDuck.setPosition(position.x + 22, position.y + 22);
                else if (animationModelsFaster.getFrameCount() == 6 || animationModelsFaster.getFrameCount() == 11) wingsDuck.setPosition(position.x + 22, position.y + 16);
                else if (animationModelsFaster.getFrameCount() == 7 || animationModelsFaster.getFrameCount() == 10) wingsDuck.setPosition(position.x + 22, position.y + 11);
                else if (animationModelsFaster.getFrameCount() == 8 || animationModelsFaster.getFrameCount() == 9) wingsDuck.setPosition(position.x + 22, position.y + 7);
            }

            if (position.x + WIDTH < 0 || isEaten || RuDragonGame.getPreference().isContinue()) {
                if (isEaten) {
                    RuDragonGame.getPreference().saveDuckLove(1);
                    positionAnim.x = position.x + 35;
                    positionAnim.y = position.y + 22;
                    isShowAnim = true;
                }
                isGo = false;
                isEaten = false;
                isSound = true;
                position.x = START_POSITION;
                position.y = random.nextInt(RuDragonGame.HEIGHT) - 64;
                if (position.y < 150) position.y = 250;
            }
        }
        if (isShowAnim) {
            positionAnim.add(MOVEMENT * deltaTime, 0);
            pen.update(deltaTime);
            if (pen.isComplete()) {
                pen.reset();
                positionAnim.x = START_POSITION;
                positionAnim.y = position.y + 22;
                isShowAnim = false;
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (isGo) {
            if (position.x > 500) batch.draw(animationModels.getFrame(), position.x, position.y);
            else if (position.x <= 500) batch.draw(animationModelsFaster.getFrame(), position.x, position.y);
        }
        if (isShowAnim) {
            pen.setPosition(positionAnim.x, positionAnim.y);
            pen.draw(batch);
        }
    }

    public void updateHelp(float deltaTime) {
        animationModels.update(deltaTime);
    }

    public void renderHelp(SpriteBatch batch, int newPosition) {
        if (newPosition == 1) batch.draw(animationModels.getFrame(), RuDragonGame.WIDTH - 512, RuDragonGame.HEIGHT - 256);
        else if (newPosition == 2) batch.draw(animationModels.getFrame(), 640, RuDragonGame.HEIGHT - 192);
        else if (newPosition == 3) batch.draw(animationModels.getFrame(), 448, RuDragonGame.HEIGHT - 512);
    }

    public void updateDucks(float deltaTime) {
        animationModels.update(deltaTime);
        position.add((-MOVEMENT + speedFlock) * deltaTime, 0);

        if (position.x - 50 > RuDragonGame.WIDTH || RuDragonGame.getPreference().isContinue()) {
            position.x = -32;
            position.y = RuDragonGame.HEIGHT - random.nextInt(640) - 20;
            if (position.y < 250) position.y = 350;
        }
    }

    public void renderDucks(SpriteBatch batch) {
        batch.draw(animationModels.getFrame(), position.x, position.y, 10, 8);
        batch.draw(animationModels.getFrame(), position.x - 14, position.y - 4, 10, 8);
        batch.draw(animationModels.getFrame(), position.x - 14, position.y + 4, 10, 8);
        batch.draw(animationModels.getFrame(), position.x - 28, position.y - 8, 10, 8);
        batch.draw(animationModels.getFrame(), position.x - 28, position.y + 8, 10, 8);
        batch.draw(animationModels.getFrame(), position.x - 42, position.y - 12, 10, 8);
        batch.draw(animationModels.getFrame(), position.x - 42, position.y + 12, 10, 8);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (tail.overlaps(bodyDuck) || pawsR11.overlaps(bodyDuck) || pawsR12.overlaps(bodyDuck) || paws.overlaps(bodyDuck) || head.overlaps(bodyDuck) || wings.overlaps(bodyDuck) ||
                tail.overlaps(wingsDuck) || pawsR11.overlaps(wingsDuck) || pawsR12.overlaps(wingsDuck) || paws.overlaps(wingsDuck) || head.overlaps(wingsDuck) || wings.overlaps(wingsDuck));
    }

    public float getX() {
        return position.x;
    }

    public float setAddX(float addX) {
        return position.x = START_POSITION + addX;
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
            pen.dispose();
            position = null;
            positionAnim = null;
            animationModels = null;
            animationModelsFaster = null;
            bodyDuck = null;
            wingsDuck = null;
            random = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}