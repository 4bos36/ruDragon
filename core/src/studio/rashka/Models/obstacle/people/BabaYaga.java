package studio.rashka.Models.obstacle.people;

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
 * Баба Яга
 */

public class BabaYaga implements Collides {

    private static final int MOVEMENT = -40;
    private static final int WIDTH = 192;
    private static final float START_POSITION = RuDragonGame.WIDTH + 10;
    private int posY;

    private Vector2 position, positionAnim;

    private AnimationModels animationModels, animationModelsFaster;
    private Rectangle bodyBaba, broomUp, broomDown;
    private Random random;
    private boolean isGo = false;
    private boolean isSound = true, isShowAnim = false;
    private float speed = 1;
    private ParticleEffect magic;

    public BabaYaga() {
        animationModels = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("babaYaga")), 9, 0.8f);
        animationModelsFaster = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("babaYaga")), 9, 0.5f);

        random = new Random();
        flyY();
        position = new Vector2(START_POSITION, posY);
        positionAnim = new Vector2(START_POSITION, posY);

        magic = new ParticleEffect();
        magic.load(Gdx.files.internal("particles/BabaYaga.p"), Gdx.files.internal("particles"));
        magic.start();

        bodyBaba = new Rectangle(position.x + 58, position.y + 40, 55, 110);
        broomUp = new Rectangle(position.x + 25, position.y + 100, 8, 8);
        broomDown = new Rectangle(position.x + 160, position.y + 55, 20, 20);
    }

    private int flyY() {
        posY = random.nextInt(RuDragonGame.HEIGHT / 3) + 160;
        return posY;
    }

    private void collidesFly() {
        if (speed < 12) {
            if (animationModels.getFrameCount() == 0) {
                broomUp.setPosition(position.x + 25, position.y + 100);
                broomDown.setPosition(position.x + 160, position.y + 55);
            } else if (animationModels.getFrameCount() == 1) {
                broomUp.setPosition(position.x + 25, position.y + 112);
                broomDown.setPosition(position.x + 153, position.y + 52);
            } else if (animationModels.getFrameCount() == 2) {
                broomUp.setHeight(30);
                broomUp.setPosition(position.x + 36, position.y + 110);
                broomDown.setPosition(position.x + 127, position.y + 38);
            } else if (animationModels.getFrameCount() == 3) {
                broomUp.setPosition(position.x + 45, position.y + 128);
                broomDown.setPosition(position.x + 80, position.y + 20);
            } else if (animationModels.getFrameCount() == 4) {
                broomDown.setHeight(40);
                broomUp.setPosition(position.x + 73, position.y + 152);
                broomDown.setPosition(position.x + 20, position.y + 30);
            } else if (animationModels.getFrameCount() == 5) {
                broomUp.setPosition(position.x + 70, position.y + 152);
                broomDown.setPosition(position.x + 12, position.y + 34);
            } else if (animationModels.getFrameCount() == 6) {
                broomUp.setPosition(position.x + 70, position.y + 152);
                broomDown.setPosition(position.x + 16, position.y + 34);
            } else if (animationModels.getFrameCount() == 7) {
                broomDown.setHeight(20);
                broomUp.setPosition(position.x + 54, position.y + 132);
                broomDown.setPosition(position.x + 73, position.y + 13);
            } else if (animationModels.getFrameCount() == 8) {
                broomUp.setHeight(8);
                broomUp.setPosition(position.x + 28, position.y + 120);
                broomDown.setPosition(position.x + 139, position.y + 38);
            }
        } else if (speed >= 12) {
            if (animationModelsFaster.getFrameCount() == 0) {
                broomUp.setPosition(position.x + 25, position.y + 100);
                broomDown.setPosition(position.x + 160, position.y + 55);
            } else if (animationModelsFaster.getFrameCount() == 1) {
                broomUp.setPosition(position.x + 25, position.y + 112);
                broomDown.setPosition(position.x + 153, position.y + 52);
            } else if (animationModelsFaster.getFrameCount() == 2) {
                broomUp.setHeight(30);
                broomUp.setPosition(position.x + 36, position.y + 110);
                broomDown.setPosition(position.x + 127, position.y + 38);
            } else if (animationModelsFaster.getFrameCount() == 3) {
                broomUp.setPosition(position.x + 45, position.y + 128);
                broomDown.setPosition(position.x + 80, position.y + 20);
            } else if (animationModelsFaster.getFrameCount() == 4) {
                broomDown.setHeight(40);
                broomUp.setPosition(position.x + 73, position.y + 152);
                broomDown.setPosition(position.x + 20, position.y + 30);
            } else if (animationModelsFaster.getFrameCount() == 5) {
                broomUp.setPosition(position.x + 70, position.y + 152);
                broomDown.setPosition(position.x + 12, position.y + 34);
            } else if (animationModelsFaster.getFrameCount() == 6) {
                broomUp.setPosition(position.x + 70, position.y + 152);
                broomDown.setPosition(position.x + 16, position.y + 34);
            } else if (animationModelsFaster.getFrameCount() == 7) {
                broomDown.setHeight(20);
                broomUp.setPosition(position.x + 54, position.y + 132);
                broomDown.setPosition(position.x + 73, position.y + 13);
            } else if (animationModelsFaster.getFrameCount() == 8) {
                broomUp.setHeight(8);
                broomUp.setPosition(position.x + 28, position.y + 120);
                broomDown.setPosition(position.x + 139, position.y + 38);
            }
        }
    }

    public void update(float deltaTime) {
        if (isGo) {
            if (position.x < RuDragonGame.WIDTH && isSound) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("babaYaga").play();
                isSound = false;
            }
            position.add(MOVEMENT * deltaTime * speed, 0);
            bodyBaba.setPosition(position.x + 58, position.y + 40);
            if (speed < 12) animationModels.update(deltaTime);
            else if (speed >= 12) animationModelsFaster.update(deltaTime);
            collidesFly();

            if (position.x + WIDTH < 0 || RuDragonGame.getPreference().isRoarDragon() || RuDragonGame.getPreference().isContinue()) {
                if (RuDragonGame.getPreference().isRoarDragon()) {
                    positionAnim.x = position.x + 32;
                    positionAnim.y = position.y + 96;
                    isShowAnim = true;
                }
                isGo = false;
                isSound = true;
                position.x = START_POSITION;
                position.y = flyY();
            }
        }
        if (isShowAnim) {
            positionAnim.add(MOVEMENT * deltaTime * speed, 0);
            magic.update(deltaTime);
            if (magic.isComplete()) {
                magic.reset();
                positionAnim.x = START_POSITION;
                position.y = flyY();
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
            magic.setPosition(positionAnim.x, positionAnim.y);
            magic.draw(batch);
        }
    }

    public void updateHelp(float deltaTime) {
        animationModels.update(deltaTime);
    }

    public void renderHelp(SpriteBatch batch) {
        batch.draw(animationModels.getFrame(), RuDragonGame.WIDTH / 2 - 256, 448);
    }

    @Override
    public boolean collides(Rectangle tail, Rectangle pawsR11, Rectangle pawsR12, Rectangle paws, Rectangle head, Rectangle wings) {
        return (tail.overlaps(bodyBaba) || tail.overlaps(broomUp) || tail.overlaps(broomDown) ||
                pawsR11.overlaps(bodyBaba) || pawsR11.overlaps(broomUp) || pawsR11.overlaps(broomDown) ||
                pawsR12.overlaps(bodyBaba) || pawsR12.overlaps(broomUp) || pawsR12.overlaps(broomDown) ||
                paws.overlaps(bodyBaba) || paws.overlaps(broomUp) || paws.overlaps(broomDown) ||
                head.overlaps(bodyBaba) || head.overlaps(broomUp) || head.overlaps(broomDown) ||
                wings.overlaps(bodyBaba) || wings.overlaps(broomUp) || wings.overlaps(broomDown));
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
            magic.dispose();
            position = null;
            positionAnim = null;
            animationModels = null;
            animationModelsFaster = null;
            bodyBaba = null;
            broomUp = null;
            broomDown = null;
            random = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}