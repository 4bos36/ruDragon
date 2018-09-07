package studio.rashka.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import studio.rashka.RuDragonGame;

public class River {

    private ParticleEffect river;
    private int i = 0;

    public River() {
        river = new ParticleEffect();
        river.load(Gdx.files.internal("particles/River.p"), Gdx.files.internal("particles"));
        river.start();
    }

    public void update(float deltaTime) {
        if (i < 60) i++;
        if (i < 60) river.update(deltaTime);
        else if (i >= 60) river.update(deltaTime / 20);
    }

    public void render(SpriteBatch batch) {
        river.setPosition(RuDragonGame.WIDTH + 32, 253);
        river.draw(batch);
    }

    public void dispose() {
        try {
            river.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}