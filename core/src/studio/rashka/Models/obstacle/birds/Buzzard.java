package studio.rashka.Models.obstacle.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import studio.rashka.Lib.AnimationModels;
import studio.rashka.Lib.Collides;
import studio.rashka.RuDragonGame;

/**
 * Канюк
 */

public class Buzzard implements Collides {

    private static final int MOVEMENT = -60;
    private static final int WIDTH = 113;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;
    private int posY, wingsY;

    private Vector2 position, positionAnim;

    private AnimationModels animationModels, animationModelsFaster;
    private Rectangle bodyBuzzard, wings;
    private Random random;
    private boolean isGo = false;
    private boolean isSound = true, isShowAnim = false;
    private float speed = 1;

    private ParticleEffect pen;

    public Buzzard() {
        animationModels = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("buzzard")), 17, 1.0f);
        animationModelsFaster = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("buzzard")), 17, 0.7f);

        random = new Random();
        flyY();
        position = new Vector2(START_POSITION, posY);
        positionAnim = new Vector2(START_POSITION, posY);

        pen = new ParticleEffect();
        pen.load(Gdx.files.internal("particles/Plumage2.p"), Gdx.files.internal("particles"));
        pen.start();

        bodyBuzzard = new Rectangle(position.x + 15, position.y + 38, 80, 15);
        wings = new Rectangle(position.x + 35, position.y + 84, 32, 28);
    }

    private int flyY() {
        posY = RuDragonGame.HEIGHT / 2 + random.nextInt(RuDragonGame.HEIGHT / 2);
        if (posY > RuDragonGame.HEIGHT - 128) posY = RuDragonGame.HEIGHT - 128;
        return posY;
    }

    public void update(float deltaTime) {
        if (isGo) {
            if (position.x < RuDragonGame.WIDTH && isSound) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("buzzard").play();
                isSound = false;
            }
            position.add(MOVEMENT * deltaTime * speed, 0);

            if (speed < 12) {
                animationModels.update(deltaTime);
                if (animationModels.getFrameCount() == 0) wingsY = 0;
                else if (animationModels.getFrameCount() == 1) wingsY = 4;
                else if (animationModels.getFrameCount() == 2) wingsY = 10;
                else if (animationModels.getFrameCount() == 3) wingsY = 25;
                else if (animationModels.getFrameCount() == 4) wingsY = 50;
                else if (animationModels.getFrameCount() == 5) wingsY = 70;
                else if (animationModels.getFrameCount() == 6) wingsY = 75;
                else if (animationModels.getFrameCount() == 7) wingsY = 80;
                else if (animationModels.getFrameCount() == 8) wingsY = 80;
                else if (animationModels.getFrameCount() == 9) wingsY = 75;
                else if (animationModels.getFrameCount() == 10) wingsY = 70;
                else if (animationModels.getFrameCount() == 11) wingsY = 60;
                else if (animationModels.getFrameCount() == 12) wingsY = 50;
                else if (animationModels.getFrameCount() == 13) wingsY = 25;
                else if (animationModels.getFrameCount() == 14) wingsY = 10;
                else if (animationModels.getFrameCount() == 15) wingsY = 4;
                else if (animationModels.getFrameCount() == 16) wingsY = 0;
            } else if (speed >= 12) {
                animationModelsFaster.update(deltaTime);
                if (animationModelsFaster.getFrameCount() == 0) wingsY = 0;
                else if (animationModelsFaster.getFrameCount() == 1) wingsY = 4;
                else if (animationModelsFaster.getFrameCount() == 2) wingsY = 10;
                else if (animationModelsFaster.getFrameCount() == 3) wingsY = 25;
                else if (animationModelsFaster.getFrameCount() == 4) wingsY = 50;
                else if (animationModelsFaster.getFrameCount() == 5) wingsY = 70;
                else if (animationModelsFaster.getFrameCount() == 6) wingsY = 75;
                else if (animationModelsFaster.getFrameCount() == 7) wingsY = 80;
                else if (animationModelsFaster.getFrameCount() == 8) wingsY = 80;
                else if (animationModelsFaster.getFrameCount() == 9) wingsY = 75;
                else if (animationModelsFaster.getFrameCount() == 10) wingsY = 70;
                else if (animationModelsFaster.getFrameCount() == 11) wingsY = 60;
                else if (animationModelsFaster.getFrameCount() == 12) wingsY = 50;
                else if (animationModelsFaster.getFrameCount() == 13) wingsY = 25;
                else if (animationModelsFaster.getFrameCount() == 14) wingsY = 10;
                else if (animationModelsFaster.getFrameCount() == 15) wingsY = 4;
                else if (animationModelsFaster.getFrameCount() == 16) wingsY = 0;
            }

            bodyBuzzard.setPosition(position.x + 15, position.y + 38);
            wings.setPosition(position.x + 35, position.y + 84 - wingsY);

            if (position.x + WIDTH < 0 || RuDragonGame.getPreference().isRoarDragon() || RuDragonGame.getPreference().isContinue()) {
                if (RuDragonGame.getPreference().isRoarDragon()) {
                    positionAnim.x = position.x + 42;
                    positionAnim.y = position.y + 22;
                    isShowAnim = true;
                }
                isGo = false;
                isSound = true;
                position.x = START_POSITION;
                position.y = flyY();
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
            if (speed < 12) batch.draw(animationModels.getFrame(), position.x, position.y);
            else if (speed >= 12) batch.draw(animationModelsFaster.getFrame(), position.x, position.y);
        }
        if (isShowAnim) {
            pen.setPosition(positionAnim.x, positionAnim.y);
            pen.draw(batch);
        }
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (tail.overlaps(bodyBuzzard) || pawsR11.overlaps(bodyBuzzard) || pawsR12.overlaps(bodyBuzzard) ||
                paws.overlaps(bodyBuzzard) || head.overlaps(bodyBuzzard) || wings.overlaps(bodyBuzzard) ||
                tail.overlaps(this.wings) || pawsR11.overlaps(this.wings) || pawsR12.overlaps(this.wings) ||
                paws.overlaps(this.wings) || head.overlaps(this.wings) || wings.overlaps(this.wings));
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
            pen.dispose();
            position = null;
            positionAnim = null;
            animationModels = null;
            animationModelsFaster = null;
            bodyBuzzard = null;
            wings = null;
            random = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}