package studio.rashka.Models.obstacle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import studio.rashka.RuDragonGame;

public class IslandDragon {

    private final static int UP = 1, DOWN = 2;
    private int move;
    private boolean activeXP = false, activeXam = false;

    private ParticleEffect fire, stones, xam, xp;
    private Vector2 position;

    public IslandDragon() {
        position = new Vector2(0, 0);

        fire = new ParticleEffect();
        fire.load(Gdx.files.internal("particles/Bonfire.p"), Gdx.files.internal("particles"));
        fire.start();

        stones = new ParticleEffect();
        stones.load(Gdx.files.internal("particles/Stones.p"), Gdx.files.internal("particles"));
        stones.start();

        xam = new ParticleEffect();
        xam.load(Gdx.files.internal("particles/BonfireXAM.p"), Gdx.files.internal("particles"));
        xam.start();

        xp = new ParticleEffect();
        xp.load(Gdx.files.internal("particles/BonfireXP.p"), Gdx.files.internal("particles"));
        xp.start();

        move = UP;
    }

    public void update(float deltaTime) {
        if (move == UP) position.y += deltaTime * 5;
        else if (move == DOWN) position.y -= deltaTime * 5;

        if (position.y > 50) move = DOWN;
        else if (position.y < -50) move = UP;

        if (RuDragonGame.getPreference().loadAngelBonus() > 0) fire.update(deltaTime);
        if (RuDragonGame.getPreference().loadSuperBonusXP() > 0 && !activeXP) xp.update(deltaTime);
        if (RuDragonGame.getPreference().loadSuperBonusHam() > 0 && !activeXam) xam.update(deltaTime);
        stones.update(deltaTime / 2);
    }

    public void render(SpriteBatch batch) {
        stones.setPosition(1368, 660 + position.y);
        stones.draw(batch);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("IslandDragon"), 1240, 712 + position.y, 127, 64, 254, 128, 1, 1, 90);
        if (RuDragonGame.getPreference().loadAngelBonus() > 0) {
            fire.setPosition(1362, 892 + position.y);
            fire.draw(batch);
        }
        if (RuDragonGame.getPreference().loadSuperBonusXP() > 0 && !activeXP) {
            xp.setPosition(1405, 860 + position.y);
            xp.draw(batch);
        }
        if (RuDragonGame.getPreference().loadSuperBonusHam() > 0 && !activeXam) {
            xam.setPosition(1325, 860 + position.y);
            xam.draw(batch);
        }
    }

    public void setActiveXP(boolean activeXP) {
        this.activeXP = activeXP;
    }

    public void setActiveXam(boolean activeXam) {
        this.activeXam = activeXam;
    }

    public void dispose() {
        try {
            fire.dispose();
            stones.dispose();
            xam.dispose();
            xp.dispose();
            position = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}