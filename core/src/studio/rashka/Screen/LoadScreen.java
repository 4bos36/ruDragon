package studio.rashka.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

import studio.rashka.RuDragonGame;
import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;

public class LoadScreen extends State {

    private Texture load;
    private Map<String, TextureRegion> loading;
    private int alpha = 0, start = 0, progressbar = 0;

    public LoadScreen(final Game game) {
        super(game);

        load = new Texture("loading.tga");
        loading = new HashMap<String, TextureRegion>();
        loading.put("logo", new TextureRegion(load, 0, 0, 512, 320));
        loading.put("loading", new TextureRegion(load, 0, 320, 64, 64));
        loading.put("progressBar", new TextureRegion(load, 255, 64, 1, 1));

        RuDragonGame.getMusicSound().loadMusic();
        RuDragonGame.getMusicSound().loadSound();
        RuDragonGame.getTextures().loadTextures.load("packTexture.png", Texture.class);
        RuDragonGame.getTextures().loadTextures.load("textureAnimals.png", Texture.class);
        RuDragonGame.getTextures().loadTextures.load("textureTree.png", Texture.class);
        RuDragonGame.getTextures().loadTextures.load("fonCave.tga", Texture.class);
        RuDragonGame.getTextures().loadTextures.load("fonCaveNight.jpg", Texture.class);
        RuDragonGame.getTextures().loadTextures.load("buttons.texture", TextureAtlas.class);
    }

    @Override
    public void update(float deltaTime) {
        alpha-=3;
        if (RuDragonGame.getTextures().loadTextures.isLoaded("packTexture.png") && !RuDragonGame.getTextures().isLoad()) progressbar = 1;
        if (RuDragonGame.getTextures().loadTextures.isLoaded("textureAnimals.png") && !RuDragonGame.getTextures().isLoad()) progressbar = 2;
        if (RuDragonGame.getTextures().loadTextures.isLoaded("textureTree.png") && !RuDragonGame.getTextures().isLoad()) progressbar = 3;
        if (RuDragonGame.getTextures().loadTextures.isLoaded("fonCave.tga") && !RuDragonGame.getTextures().isLoad()) progressbar = 4;
        if (RuDragonGame.getTextures().loadTextures.isLoaded("fonCaveNight.jpg") && !RuDragonGame.getTextures().isLoad()) progressbar = 5;
        if (RuDragonGame.getTextures().loadTextures.isLoaded("buttons.texture") && !RuDragonGame.getTextures().isLoad()) progressbar = 6;

        if (RuDragonGame.getTextures().loadTextures.update() && !RuDragonGame.getTextures().isLoad()) {
            RuDragonGame.getTextures().loadTexture();
            RuDragonGame.getTextures().buttons();
            RuDragonGame.getTextures().loadTextureAnimals();
            RuDragonGame.getTextures().loadTextureTree();
        }
        if (RuDragonGame.getTextures().isLoad()) {
            start++;
            if (start == 30) {
                progressbar = 8;
                RuDragonGame.adHandler.showAds(404);
            }
            if (start >= 50) {
                progressbar = 9;
                if (RuDragonGame.getMusicSound().getMusic().update() && RuDragonGame.getMusicSound().getSound().update()) {
                    RuDragonGame.getMusicSound().loadMusicFon();
                    RuDragonGame.getMusicSound().loadGame();
                    RuDragonGame.getMusicSound().loadCave();
                    progressbar = 10;
                    RuDragonGame.getMusicSound().loadMusicRun();
                    RuDragonGame.setLoadMusic(1);
                    RuDragonGame.getTimeBonus().setTimeResetXP();
                    RuDragonGame.getTimeBonus().setTimeResetHAM();
                    game.set(new MenuScreen(game));
                }
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(loading.get("logo"), RuDragonGame.WIDTH / 2 - 160, RuDragonGame.HEIGHT / 2 - 100, 320, 200);
        batch.draw(loading.get("loading"), RuDragonGame.WIDTH / 2 - loading.get("loading").getRegionWidth() / 2, 128, loading.get("loading").getRegionWidth() / 2, loading.get("loading").getRegionHeight() / 2, loading.get("loading").getRegionWidth(), loading.get("loading").getRegionHeight(), 1, 1, alpha);
        batch.draw(loading.get("progressBar"), 0, 0, 192 * progressbar, 4);
        batch.end();
    }

    @Override
    public void dispose() {
        try {
            load.dispose();
            loading.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}