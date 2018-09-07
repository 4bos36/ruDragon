package studio.rashka.Models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import studio.rashka.Lib.AnimationModels;
import studio.rashka.RuDragonGame;

/**
 * Змей Горыныч
 */

public class RuDragon {

    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private int flyUp, flyDown, homeMove = 0;
    private float zoom = 0.7f;
    private boolean isFendOffDragon = true;

    private Vector2 position, velocity;
    private Rectangle tail, // хвост
            pawsR1, // лапы передние
            pawsR2, // лапы передние
            paws, // лапы задние
            head, // голова
            wings; // крылья

    private AnimationModels animationModelsFly, animationModelsRun;

    public RuDragon(float x, float y) {
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);

        flyUp = MOVEMENT * RuDragonGame.getPreference().loadFlyUp() / 100;
        flyDown = MOVEMENT * RuDragonGame.getPreference().loadFlyDown() / 100;

        tail = new Rectangle(position.x, position.y, 64, 24);
        pawsR1 = new Rectangle(position.x, position.y, 16, 16);
        pawsR2 = new Rectangle(position.x, position.y, 16, 16);
        paws = new Rectangle(position.x, position.y, 48, 16);
        head = new Rectangle(position.x, position.y, 64, 48);
        wings = new Rectangle(position.x, position.y, 100, 60);

        animationModelsFly = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("dragonFly")), 12, 0.6f);
        animationModelsRun = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("dragonRun")), 11, 0.4f);
    }

    private void collidesFly() {
        if (animationModelsFly.getFrameCount() == 0) {
            wings.setHeight(100);
            pawsR1.setPosition(position.x + 207, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 95);
            paws.setPosition(position.x + 107, position.y + 65);
            tail.setPosition(position.x + 11, position.y + 117);
            head.setPosition(position.x + 232, position.y + 112);
            wings.setPosition(position.x + 92, position.y + 177);
        } else if (animationModelsFly.getFrameCount() == 1) {
            pawsR1.setPosition(position.x + 207, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 95);
            paws.setPosition(position.x + 107, position.y + 65);
            tail.setPosition(position.x + 11, position.y + 119);
            head.setPosition(position.x + 232, position.y + 120);
            wings.setPosition(position.x + 92, position.y + 140);
        } else if (animationModelsFly.getFrameCount() == 2) {
            wings.setHeight(40);
            pawsR1.setPosition(position.x + 207, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 95);
            paws.setPosition(position.x + 107, position.y + 65);
            tail.setPosition(position.x + 11, position.y + 122);
            head.setPosition(position.x + 232, position.y + 120);
            wings.setPosition(position.x + 100, position.y + 165);
        } else if (animationModelsFly.getFrameCount() == 3) {
            pawsR1.setPosition(position.x + 207, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 95);
            paws.setPosition(position.x + 107, position.y + 65);
            tail.setPosition(position.x + 11, position.y + 122);
            head.setPosition(position.x + 232, position.y + 125);
            wings.setPosition(position.x + 100, position.y + 125);
        } else if (animationModelsFly.getFrameCount() == 4) {
            pawsR1.setPosition(position.x + 207, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 95);
            paws.setPosition(position.x + 107, position.y + 65);
            tail.setPosition(position.x + 11, position.y + 125);
            head.setPosition(position.x + 232, position.y + 127);
            wings.setPosition(position.x + 100, position.y + 105);
        } else if (animationModelsFly.getFrameCount() == 5) {
            pawsR1.setPosition(position.x + 207, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 95);
            paws.setPosition(position.x + 107, position.y + 41);
            tail.setPosition(position.x + 11, position.y + 129);
            head.setPosition(position.x + 232, position.y + 129);
            wings.setPosition(position.x + 100, position.y + 65);
        } else if (animationModelsFly.getFrameCount() == 6) {
            pawsR1.setPosition(position.x + 207, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 95);
            paws.setPosition(position.x + 118, position.y + 30);
            tail.setPosition(position.x + 8, position.y + 134);
            head.setPosition(position.x + 232, position.y + 132);
            wings.setPosition(position.x + 98, position.y + 50);
        } else if (animationModelsFly.getFrameCount() == 7) {
            pawsR1.setPosition(position.x + 211, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 98);
            paws.setPosition(position.x + 114, position.y + 30);
            tail.setPosition(position.x + 8, position.y + 138);
            head.setPosition(position.x + 232, position.y + 130);
            wings.setPosition(position.x + 98, position.y + 50);
        } else if (animationModelsFly.getFrameCount() == 8) {
            pawsR1.setPosition(position.x + 211, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 98);
            paws.setPosition(position.x + 114, position.y + 67);
            tail.setPosition(position.x + 8, position.y + 138);
            head.setPosition(position.x + 232, position.y + 127);
            wings.setPosition(position.x + 98, position.y + 130);
        } else if (animationModelsFly.getFrameCount() == 9) {
            pawsR1.setPosition(position.x + 211, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 98);
            paws.setPosition(position.x + 111, position.y + 67);
            tail.setPosition(position.x + 8, position.y + 138);
            head.setPosition(position.x + 232, position.y + 121);
            wings.setPosition(position.x + 98, position.y + 130);
        } else if (animationModelsFly.getFrameCount() == 10) {
            pawsR1.setPosition(position.x + 211, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 98);
            paws.setPosition(position.x + 111, position.y + 67);
            tail.setPosition(position.x + 8, position.y + 133);
            head.setPosition(position.x + 232, position.y + 121);
            wings.setPosition(position.x + 108, position.y + 158);
        } else if (animationModelsFly.getFrameCount() == 11) {
            pawsR1.setPosition(position.x + 211, position.y + 82);
            pawsR2.setPosition(position.x + 238, position.y + 98);
            paws.setPosition(position.x + 111, position.y + 67);
            tail.setPosition(position.x + 8, position.y + 133);
            head.setPosition(position.x + 232, position.y + 117);
            wings.setPosition(position.x + 100, position.y + 197);
        }
    }

    private void collidesRun() {
        if (animationModelsRun.getFrameCount() == 0) {
            tail.setPosition(position.x + 14, position.y + 70);
            head.setPosition(position.x + 225, position.y + 58);
            wings.setHeight(60);
            wings.setPosition(position.x + 96, position.y + 45);
        }
        else if (animationModelsRun.getFrameCount() == 1) {
            tail.setPosition(position.x + 14, position.y + 67);
            head.setPosition(position.x + 225, position.y + 62);
            wings.setPosition(position.x + 96, position.y + 45);
        }
        else if (animationModelsRun.getFrameCount() == 2) {
            tail.setPosition(position.x + 14, position.y + 65);
            head.setPosition(position.x + 225, position.y + 62);
            wings.setPosition(position.x + 96, position.y + 45);
        }
        else if (animationModelsRun.getFrameCount() == 3) {
            tail.setPosition(position.x + 14, position.y + 62);
            head.setPosition(position.x + 225, position.y + 58);
            wings.setPosition(position.x + 96, position.y + 45);
        }
        else if (animationModelsRun.getFrameCount() == 4) {
            tail.setPosition(position.x + 14, position.y + 65);
            head.setPosition(position.x + 225, position.y + 45);
            wings.setPosition(position.x + 96, position.y + 45);
        }
        else if (animationModelsRun.getFrameCount() == 5 || animationModelsRun.getFrameCount() == 6) {
            tail.setPosition(position.x + 14, position.y + 70);
            head.setPosition(position.x + 225, position.y + 35);
            wings.setPosition(position.x + 76, position.y + 45);
        }
        else if (animationModelsRun.getFrameCount() == 7 || animationModelsRun.getFrameCount() == 8) {
            tail.setPosition(position.x + 14, position.y + 78);
            head.setPosition(position.x + 225, position.y + 35);
            wings.setPosition(position.x + 76, position.y + 45);
        }
        else if (animationModelsRun.getFrameCount() == 9) {
            tail.setPosition(position.x + 14, position.y + 77);
            head.setPosition(position.x + 225, position.y + 50);
            wings.setPosition(position.x + 76, position.y + 45);
        }
        else if (animationModelsRun.getFrameCount() == 10) {
            tail.setPosition(position.x + 14, position.y + 74);
            head.setPosition(position.x + 225, position.y + 55);
            wings.setPosition(position.x + 76, position.y + 45);
        }
    }

    public void update(float deltaTime, float score) {
        if (score > 150 && animationModelsFly.getCycleTime() == 0.6f) {
            animationModelsFly.setCycleTime(0.5f);
            animationModelsRun.setCycleTime(0.3f);
        }
        if (score > 200 && animationModelsFly.getCycleTime() == 0.5f) {
            animationModelsFly.setCycleTime(0.4f);
            animationModelsRun.setCycleTime(0.2f);
        }

        if (zoom == 0.7f)
            if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("dragon").play();
        if (position.y > 128) {
            if (!RuDragonGame.getPreference().isFendOffDragon()) {
                animationModelsFly.update(deltaTime);
                isFendOffDragon = true;
            } else {
                if (isFendOffDragon) animationModelsFly.setFrame(2);
                isFendOffDragon = false;
                animationModelsFly.updateFendOff(deltaTime);
            }
            collidesFly();
        } else if (position.y <= 128) {
            animationModelsRun.update(deltaTime);
            pawsR1.setPosition(position.x + 96, position.y + 12);
            pawsR2.setPosition(position.x + 96, position.y + 12);
            paws.setPosition(position.x + 180, position.y + 12);
            collidesRun();
        }
        if (position.y > 127) velocity.add(0, (GRAVITY - flyDown));
        velocity.scl(deltaTime);

        if (zoom < 1.8f) {
            zoom += 0.4f * deltaTime;
            if (zoom > 1.8f) zoom = 1.8f;
        }
        else if (zoom == 1.8f) {
            if (position.x < 128) position.add(MOVEMENT * deltaTime, velocity.y);
            else {
                position.add(0, velocity.y); //1.8f
                if (homeMove < 450) homeMove += 80 * deltaTime;
            }
        }

        if (position.y < 128) position.y = 128;
        if (position.y > RuDragonGame.HEIGHT - textureRuDragon().getRegionHeight()) position.y = RuDragonGame.HEIGHT - textureRuDragon().getRegionHeight();
        velocity.scl(1 / deltaTime);
    }

    public void render(SpriteBatch batch) {
        if (homeMove < 450) batch.draw(RuDragonGame.getTextures().textureRegions.get("mountain-home"), 32 - homeMove, 128, 384, 256);
        batch.draw(textureRuDragon(), position.x, position.y, textureRuDragon().getRegionWidth() * zoom, textureRuDragon().getRegionHeight() * zoom);
    }

    public TextureRegion textureRuDragon() {
        if (position.y > 128) return animationModelsFly.getFrame();
        else return animationModelsRun.getFrame();
    }

    public void onFlyUp() {
        velocity.y = 200 + flyUp;
    }

    public void onFendOff() {
        velocity.y = GRAVITY / 2;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Rectangle getTail() {
        return tail;
    }

    public Rectangle getPawsR1() {
        return pawsR1;
    }

    public Rectangle getPawsR2() {
        return pawsR2;
    }

    public Rectangle getPaws() {
        return paws;
    }

    public Rectangle getHead() {
        return head;
    }

    public Rectangle getWings() {
        return wings;
    }

    public void dispose() {
        try {
            position = null;
            velocity = null;
            tail = null;
            pawsR1 = null;
            pawsR2 = null;
            paws = null;
            head = null;
            wings = null;
            animationModelsFly = null;
            animationModelsRun = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}