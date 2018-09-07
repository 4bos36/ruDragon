package studio.rashka.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;
import studio.rashka.Lib.TimeDay;
import studio.rashka.Lib.btn.Buttons;
import studio.rashka.Models.food.Duck;
import studio.rashka.Models.food.Goat;
import studio.rashka.Models.food.Kine;
import studio.rashka.Models.food.Pig;
import studio.rashka.Models.obstacle.birds.Falcon;
import studio.rashka.Models.obstacle.birds.Korshun;
import studio.rashka.Models.obstacle.birds.Orlan;
import studio.rashka.Models.obstacle.people.BabaYaga;
import studio.rashka.RuDragonGame;

public class HelpScreen extends State {

    private ArrayList<TextureRegion> field, clouds, mountains;
    private ArrayList<Integer> randomX, randomY;
    private Map<String, Buttons> buttons;
    private Map<String, Label> text;

    private Stage stage;

    private Duck duck;
    private Goat goat;
    private Pig pig;
    private Kine kine;
    private BabaYaga babaYaga;
    private Falcon falcon;
    private Orlan orlan;
    private Korshun korshun;

    private TimeDay timeDay;

    public HelpScreen(final Game game) {
        super(game);
        RuDragonGame.getLanguage().textHelp();
        stage = new Stage();
        timeDay = new TimeDay();

        duck = new Duck();
        goat = new Goat();
        pig = new Pig();
        kine = new Kine();
        babaYaga = new BabaYaga();
        falcon = new Falcon();
        orlan = new Orlan();
        korshun = new Korshun();

        buttons = new HashMap<String, Buttons>();
        text = new HashMap<String, Label>();

        randomX = new ArrayList<Integer>();
        randomY = new ArrayList<Integer>();

        field = new ArrayList<TextureRegion>();
        clouds = new ArrayList<TextureRegion>();
        mountains = new ArrayList<TextureRegion>();

        for (int i = 0; i <= 4; i++) field.add(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("mountains")));
        for (int i = 0; i < 5; i++) clouds.add(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("cloud")));
        for (int i = 0; i < 7; i++) mountains.add(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("mountains")));

        for (int i = 0; i < 5; i++) randomX.add(new Random().nextInt(RuDragonGame.WIDTH));
        for (int i = 0; i < 5; i++) randomY.add(new Random().nextInt(RuDragonGame.HEIGHT / 4));

        if (timeDay.isDay()) text.put("Text", new Label(RuDragonGame.getLanguage().text.get("nullText"), new Label.LabelStyle(RuDragonGame.getFontTTF().getFont48(), Color.BLACK)));
        else if (!timeDay.isDay()) text.put("Text", new Label(RuDragonGame.getLanguage().text.get("nullText"), new Label.LabelStyle(RuDragonGame.getFontTTF().getFont48(), Color.WHITE)));
        text.get("Text").setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - text.get("Text").getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 192) * RuDragonGame.getRatioMonitorH() - text.get("Text").getPrefHeight() / 2);
        stage.addActor(text.get("Text"));

        buttons();
    }

    private void buttons() {
        buttons.put("Menu", new Buttons("Menu", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 128, 128, 64, 128));
        buttons.put("Duck", new Buttons("Duck", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 80, 64, RuDragonGame.WIDTH - 512, RuDragonGame.HEIGHT - 256));
        buttons.put("Duck2", new Buttons("Duck2", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 80, 64, 640, RuDragonGame.HEIGHT - 192));
        buttons.put("Duck3", new Buttons("Duck3", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 80, 64, 448, RuDragonGame.HEIGHT - 512));
        buttons.put("Oak", new Buttons("Oak", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 652, 448, RuDragonGame.WIDTH - 800, 128));
        buttons.put("Maple", new Buttons("Maple", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 204, 256, 80, 128));
        buttons.put("Goat", new Buttons("Goat", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 128, 128, 228, 128));
        buttons.put("Pig", new Buttons("Pig", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 154, 128, RuDragonGame.WIDTH - 950, 128));
        buttons.put("Kine", new Buttons("Kine", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 171, 128, RuDragonGame.WIDTH - 356, 128));
        buttons.put("BabaYaga", new Buttons("BabaYaga", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 192, 192, RuDragonGame.WIDTH / 2 - 256, 448));
        buttons.put("Falcon", new Buttons("Falcon", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 113, 128, 256, RuDragonGame.HEIGHT - 320));
        buttons.put("Orlan", new Buttons("Orlan", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 113, 128, RuDragonGame.WIDTH - 128, 640));
        buttons.put("Korshun", new Buttons("Korshun", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 113, 128, RuDragonGame.WIDTH / 2, RuDragonGame.HEIGHT - 96));
        buttons.put("Willow", new Buttons("Willow", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 268, 244, 600, 128));
        buttons.put("Rock", new Buttons("Rock", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 235, 256, 420, 128));

        buttons.get("Menu").addListener(new ButtonsInputListener(buttons.get("Menu").getName()));
        buttons.get("Duck").addListener(new ButtonsInputListener(buttons.get("Duck").getName()));
        buttons.get("Duck2").addListener(new ButtonsInputListener(buttons.get("Duck2").getName()));
        buttons.get("Duck3").addListener(new ButtonsInputListener(buttons.get("Duck3").getName()));
        buttons.get("Oak").addListener(new ButtonsInputListener(buttons.get("Oak").getName()));
        buttons.get("Maple").addListener(new ButtonsInputListener(buttons.get("Maple").getName()));
        buttons.get("Goat").addListener(new ButtonsInputListener(buttons.get("Goat").getName()));
        buttons.get("Pig").addListener(new ButtonsInputListener(buttons.get("Pig").getName()));
        buttons.get("Kine").addListener(new ButtonsInputListener(buttons.get("Kine").getName()));
        buttons.get("BabaYaga").addListener(new ButtonsInputListener(buttons.get("BabaYaga").getName()));
        buttons.get("Falcon").addListener(new ButtonsInputListener(buttons.get("Falcon").getName()));
        buttons.get("Orlan").addListener(new ButtonsInputListener(buttons.get("Orlan").getName()));
        buttons.get("Korshun").addListener(new ButtonsInputListener(buttons.get("Korshun").getName()));
        buttons.get("Willow").addListener(new ButtonsInputListener(buttons.get("Willow").getName()));
        buttons.get("Rock").addListener(new ButtonsInputListener(buttons.get("Rock").getName()));

        stage.addActor(buttons.get("Maple"));
        stage.addActor(buttons.get("Menu"));
        stage.addActor(buttons.get("Duck"));
        stage.addActor(buttons.get("Duck2"));
        stage.addActor(buttons.get("Duck3"));
        stage.addActor(buttons.get("Oak"));
        stage.addActor(buttons.get("Goat"));
        stage.addActor(buttons.get("Pig"));
        stage.addActor(buttons.get("Kine"));
        stage.addActor(buttons.get("BabaYaga"));
        stage.addActor(buttons.get("Falcon"));
        stage.addActor(buttons.get("Orlan"));
        stage.addActor(buttons.get("Korshun"));
        stage.addActor(buttons.get("Willow"));
        stage.addActor(buttons.get("Rock"));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float deltaTime) {
        timeDay.update(deltaTime);
        duck.updateHelp(deltaTime);
        goat.homeLoad(deltaTime);
        pig.homeLoad(deltaTime);
        kine.homeLoad(deltaTime);
        babaYaga.updateHelp(deltaTime);
        falcon.updateHelp(deltaTime);
        orlan.updateHelp(deltaTime);
        korshun.updateHelp(deltaTime);
        RuDragonGame.getRiver().update(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        timeDay.render(batch);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("black"), 0, 100, RuDragonGame.WIDTH, 245);
        for (int i = 0; i < 7; i++) batch.draw(mountains.get(i), 280 * i, 320, 280, 100);
        RuDragonGame.getRiver().render(batch);
        for (int i = 0; i < 5; i++) batch.draw(mountains.get(i), 560 * i, 140, 560, 240);
        for (int i = 0; i < 5; i++) batch.draw(clouds.get(i), randomX.get(i), RuDragonGame.HEIGHT - randomY.get(i) - 45);

        batch.draw(RuDragonGame.getTextures().textureRegions.get("maple"), 80, 128);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("pointer-menu"), 64, 128);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("oak"), RuDragonGame.WIDTH - 800, 128);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("rock_4"), 380, 128);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("willow"), 600, 128);
        duck.renderHelp(batch, 1);
        duck.renderHelp(batch, 2);
        duck.renderHelp(batch, 3);
        goat.renderHelp(batch);
        pig.renderHelp(batch);
        kine.renderHelp(batch);
        babaYaga.renderHelp(batch);
        falcon.renderHelp(batch);
        orlan.renderHelp(batch);
        korshun.renderHelp(batch);

        batch.draw(RuDragonGame.getTextures().textureRegions.get("earth"), 0, 0);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        try {
            RuDragonGame.getLanguage().text.clear();
            timeDay.dispose();
            stage.dispose();

            field.clear();
            clouds.clear();
            mountains.clear();
            randomX.clear();
            randomY.clear();
            buttons.clear();
            text.clear();

            duck.dispose();
            goat.dispose();
            pig.dispose();
            kine.dispose();

            falcon.dispose();
            korshun.dispose();
            orlan.dispose();

            babaYaga.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ButtonsInputListener extends InputListener {
        private String name;

        public ButtonsInputListener(String name) {
            this.name = name;
        }

        @Override
        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            return true;
        }

        @Override
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);

            if (name.equals("Menu")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                RuDragonGame.getGame().set(new MenuScreen(game));
            } else if (name.equals("Duck") || name.equals("Duck2") || name.equals("Duck3")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duck").play();
                text.get("Text").setText(RuDragonGame.getLanguage().text.get("duck"));
                text.get("Text").setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - text.get("Text").getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 192) * RuDragonGame.getRatioMonitorH() - text.get("Text").getPrefHeight() / 2);
            } else if (name.equals("Oak") || name.equals("Maple") || name.equals("Willow")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                text.get("Text").setText(RuDragonGame.getLanguage().text.get("tree"));
                text.get("Text").setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - text.get("Text").getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 192) * RuDragonGame.getRatioMonitorH() - text.get("Text").getPrefHeight() / 2);
            } else if (name.equals("Goat")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("goat").play();
                text.get("Text").setText(RuDragonGame.getLanguage().text.get("goat"));
                text.get("Text").setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - text.get("Text").getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 192) * RuDragonGame.getRatioMonitorH() - text.get("Text").getPrefHeight() / 2);
            } else if (name.equals("Pig")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("pig").play();
                text.get("Text").setText(RuDragonGame.getLanguage().text.get("pig"));
                text.get("Text").setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - text.get("Text").getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 192) * RuDragonGame.getRatioMonitorH() - text.get("Text").getPrefHeight() / 2);
            } else if (name.equals("Kine")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("kine").play();
                text.get("Text").setText(RuDragonGame.getLanguage().text.get("kine"));
                text.get("Text").setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - text.get("Text").getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 192) * RuDragonGame.getRatioMonitorH() - text.get("Text").getPrefHeight() / 2);
            } else if (name.equals("BabaYaga")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("babaYaga").play();
                text.get("Text").setText(RuDragonGame.getLanguage().text.get("babaYaga"));
                text.get("Text").setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - text.get("Text").getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 192) * RuDragonGame.getRatioMonitorH() - text.get("Text").getPrefHeight() / 2);
            } else if (name.equals("Falcon") || name.equals("Orlan") || name.equals("Korshun")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("falcon").play();
                text.get("Text").setText(RuDragonGame.getLanguage().text.get("birds"));
                text.get("Text").setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - text.get("Text").getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 192) * RuDragonGame.getRatioMonitorH() - text.get("Text").getPrefHeight() / 2);
            } else if (name.equals("Rock")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("rock").play();
                text.get("Text").setText(RuDragonGame.getLanguage().text.get("rock"));
                text.get("Text").setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - text.get("Text").getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 192) * RuDragonGame.getRatioMonitorH() - text.get("Text").getPrefHeight() / 2);
            }
        }
    }
}