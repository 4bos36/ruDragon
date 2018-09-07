package studio.rashka.Lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import studio.rashka.Models.food.Duck;
import studio.rashka.RuDragonGame;

public class TimeDay {

    private Calendar calendar;
    private SimpleDateFormat time;
    private ArrayList<TextureRegion> stars, cloud;
    private ArrayList<Integer> randomsX, randomsY;
    private boolean isDay;
    private int offStars = 0;
    private Random random;

    private ParticleEffect sun;
    private ArrayList<Duck> ducks;

    public TimeDay() {
        calendar = Calendar.getInstance();
        time = new SimpleDateFormat("HH");

        ducks = new ArrayList<Duck>();
        ducks.add(0, new Duck(-20));
        ducks.add(1, new Duck(-10));
        ducks.add(2, new Duck(0));

        cloud = new ArrayList<TextureRegion>();
        random = new Random();
        randomsX = new ArrayList<Integer>();
        randomsY = new ArrayList<Integer>();
        if (Integer.parseInt(time.format(calendar.getTime())) > 8 && Integer.parseInt(time.format(calendar.getTime())) < 20) {
            sun = new ParticleEffect();
            sun.load(Gdx.files.internal("particles/Sun.p"), Gdx.files.internal("particles"));
            sun.start();
            for (int i = 0; i < 20; i++) {
                if (i < 5)
                    cloud.add(i, new TextureRegion(RuDragonGame.getTextures().textureRegions.get("cloudBig")));
                if (i >= 5 && i < 10)
                    cloud.add(i, new TextureRegion(RuDragonGame.getTextures().textureRegions.get("cloudMini")));
                if (i >= 10)
                    cloud.add(i, new TextureRegion(RuDragonGame.getTextures().textureRegions.get("cloudMini2")));
                randomsX.add(i, new Random().nextInt(RuDragonGame.WIDTH));
                randomsY.add(i, new Random().nextInt(640));
            }
            isDay = true;
        } else {
            stars = new ArrayList<TextureRegion>();
            for (int i = 0; i < 60; i++) {
                if (i < 10)
                    stars.add(i, new TextureRegion(RuDragonGame.getTextures().textureRegions.get("star_blue")));
                if (i >= 10 && i < 20)
                    stars.add(i, new TextureRegion(RuDragonGame.getTextures().textureRegions.get("star_red")));
                if (i >= 20 && i < 40)
                    stars.add(i, new TextureRegion(RuDragonGame.getTextures().textureRegions.get("star_white")));
                if (i >= 40)
                    stars.add(i, new TextureRegion(RuDragonGame.getTextures().textureRegions.get("star_gray")));
                if (i < 5)
                    cloud.add(i, new TextureRegion(RuDragonGame.getTextures().textureRegions.get("cloudBig")));
                if (i >= 5 && i < 10)
                    cloud.add(i, new TextureRegion(RuDragonGame.getTextures().textureRegions.get("cloudMini")));
                if (i >= 10 && i < 20)
                    cloud.add(i, new TextureRegion(RuDragonGame.getTextures().textureRegions.get("cloudMini2")));
                randomsX.add(i, new Random().nextInt(RuDragonGame.WIDTH));
                randomsY.add(i, new Random().nextInt(640));
            }
            isDay = false;
        }
    }

    public void update(float deltaTime) {
        if (isDay) sun.update(deltaTime);
        else offStars = random.nextInt(30);
        ducks.get(0).updateDucks(deltaTime);
        ducks.get(1).updateDucks(deltaTime);
        ducks.get(2).updateDucks(deltaTime);
    }

    public void render(SpriteBatch batch) {
        if (isDay) {
            batch.draw(RuDragonGame.getTextures().textureRegions.get("day"), 0, 0, RuDragonGame.WIDTH, RuDragonGame.HEIGHT);
            sun.setPosition(160, RuDragonGame.HEIGHT - 112);
            sun.draw(batch);
            ducks.get(2).renderDucks(batch);
            for (int i = 0; i < 10; i++) batch.draw(cloud.get(i), randomsX.get(i), RuDragonGame.HEIGHT - randomsY.get(i) - 128);
            ducks.get(0).renderDucks(batch);
            for (int i = 10; i < 20; i++) batch.draw(cloud.get(i), randomsX.get(i), RuDragonGame.HEIGHT - randomsY.get(i) - 128);
            ducks.get(1).renderDucks(batch);
        } else {
            batch.draw(RuDragonGame.getTextures().textureRegions.get("night"), 0, 0, RuDragonGame.WIDTH, RuDragonGame.HEIGHT);
            for (int i = 0; i < 60; i++) {
                if (i < 10)
                    if (offStars != 5)
                        batch.draw(stars.get(i), randomsX.get(i), RuDragonGame.HEIGHT - randomsY.get(i), 5, 5);
                if (i >= 10 && i < 20)
                    if (offStars != 10)
                        batch.draw(stars.get(i), randomsX.get(i), RuDragonGame.HEIGHT - randomsY.get(i), 5, 5);
                if (i >= 20 && i < 40)
                    if (offStars != 15)
                        batch.draw(stars.get(i), randomsX.get(i), RuDragonGame.HEIGHT - randomsY.get(i), 4, 4);
                if (i >= 40)
                    if (offStars != 20)
                        batch.draw(stars.get(i), randomsX.get(i), RuDragonGame.HEIGHT - randomsY.get(i), 3, 3);
            }
            batch.draw(RuDragonGame.getTextures().textureRegions.get("moon"), 64, RuDragonGame.HEIGHT - 160);
            ducks.get(2).renderDucks(batch);
            for (int i = 0; i < 10; i++) batch.draw(cloud.get(i), randomsX.get(i), RuDragonGame.HEIGHT - randomsY.get(i) - 128);
            ducks.get(0).renderDucks(batch);
            for (int i = 10; i < 20; i++) batch.draw(cloud.get(i), randomsX.get(i), RuDragonGame.HEIGHT - randomsY.get(i) - 128);
            ducks.get(1).renderDucks(batch);
        }
    }

    public boolean isDay() {
        return isDay;
    }

    public void dispose() {
        try {
            calendar.clear();
            cloud.clear();
            randomsX.clear();
            randomsY.clear();
            ducks.clear();
            if (isDay) sun.dispose();
            else stars.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}