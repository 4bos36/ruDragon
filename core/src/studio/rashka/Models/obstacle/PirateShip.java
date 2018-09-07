package studio.rashka.Models.obstacle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import studio.rashka.Lib.AnimationModels;
import studio.rashka.RuDragonGame;

public class PirateShip {

    private static final int MOVEMENT = 10;
    private static final int WIDTH = 192;
    private static final int Y = 256;
    private static final float START_POSITION = WIDTH + 10;
    private static final int swimUp = 1, swimDown = 2;
    private int swim;

    private boolean isSound = true, isSoundFinish = false;

    private Vector2 position;
    private AnimationModels animationModels;

    public PirateShip() {
        position = new Vector2(-START_POSITION - 128, Y);
        swim = swimUp;

        animationModels = new AnimationModels(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("PirateShip")), 2, 0.5f);
    }

    public void update(float deltaTime) {
        animationModels.update(deltaTime);
        if (position.x > -WIDTH && isSound) {
            if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("PirateStart").play();
            isSound = false;
            isSoundFinish = true;
        }
        if (swim == swimUp) position.add(MOVEMENT * deltaTime, deltaTime * 5);
        else if (swim == swimDown) position.add(MOVEMENT * deltaTime, -deltaTime * 5);

        if (position.y < 190) swim = swimUp;
        if (position.y > 280) swim = swimDown;

        if (position.x > RuDragonGame.WIDTH + WIDTH || RuDragonGame.getPreference().isContinue()) {
            position.x = -START_POSITION;
            position.y = Y;
            isSound = true;
        }
        if (position.x > RuDragonGame.WIDTH - WIDTH / 2 && isSoundFinish) {
            if (RuDragonGame.getPreference().loadSound() == 1)
                RuDragonGame.getMusicSound().sounds.get("PirateFinish").play();
            isSoundFinish = false;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(animationModels.getFrame(), position.x, position.y);
    }

    public void dispose() {
        try {
            position = null;
            animationModels = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}