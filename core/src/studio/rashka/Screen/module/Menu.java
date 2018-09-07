package studio.rashka.Screen.module;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import java.util.HashMap;
import java.util.Map;

import studio.rashka.Lib.btn.Buttons;
import studio.rashka.RuDragonGame;
import studio.rashka.Screen.AchievementsScreen;
import studio.rashka.Screen.EncyclopediaScreen;
import studio.rashka.Screen.HelpScreen;
import studio.rashka.Screen.StoreScreen;
import studio.rashka.Screen.UpgradeScreen;

public class Menu {

    private Settings settingMod;
    private Exit exitMod;
    private Credits creditsMod;

    private Map<String, Label> text;
    private LabelStyle labelStyle;
    private Map<String, Buttons> buttons;

    private Stage stage;
    private float menuX = 0, menuSoc = 0, eating = 0;

    public Menu() {
        stage = new Stage();
        settingMod = new Settings();
        exitMod = new Exit();
        creditsMod = new Credits();

        text = new HashMap<String, Label>();
        labelStyle = new LabelStyle(RuDragonGame.getFontTTF().getFont32(), Color.WHITE);

        buttonsMenu();
        textMenu();

        text.put("version", new Label(RuDragonGame.getLanguage().textMenu.get("version"), labelStyle));
        text.get("version").setPosition((menuX - 256 + 16) * RuDragonGame.getRatioMonitorW(), 8 * RuDragonGame.getRatioMonitorH());

        text.put("RoarNumber", new Label(String.valueOf(RuDragonGame.getPreference().loadRoar()), labelStyle));
        text.get("RoarNumber").setPosition((RuDragonGame.WIDTH + 128) * RuDragonGame.getRatioMonitorW() - text.get("RoarNumber").getPrefWidth() / 2, 42 * RuDragonGame.getRatioMonitorH() - text.get("RoarNumber").getPrefHeight());

        stage.addActor(text.get("version"));
        stage.addActor(text.get("RoarNumber"));
        Gdx.input.setInputProcessor(stage);
    }

    private void buttonsMenu() {
        buttons = new HashMap<String, Buttons>();
        buttons.put("menu", new Buttons("menu", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 64, 192, menuX, RuDragonGame.HEIGHT - 220));
        buttons.put("upgrade", new Buttons("upgrade", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Upgrade"), 128, 128, menuX - 128 - 64, RuDragonGame.HEIGHT - 163));
        buttons.put("achievements", new Buttons("achievements", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Achievements"), 128, 128, menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 2));
        buttons.put("shop", new Buttons("shop", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Shop"), 128, 128, menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 3));
        buttons.put("encyclopedia", new Buttons("encyclopedia", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Encyclopedia"), 128, 128, menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 4));
        buttons.put("setting", new Buttons("setting", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Setting"), 128, 128, menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 5));
        buttons.put("credits", new Buttons("credits", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("About"), 64, 64, menuX - 256 + 40, 175));
        buttons.put("help", new Buttons("help", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Help"), 64, 64, menuX - 64 - 40, 175));
        buttons.put("exit", new Buttons("exit", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Exit"), 96, 96, menuX - 256 / 2 - 96 / 2, 60));
        buttons.put("Roar", new Buttons("Roar", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Roar"), 128, 128, RuDragonGame.WIDTH - 192, -200));
        buttons.put("FendOff", new Buttons("FendOff", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("FendOff"), 128, 128, RuDragonGame.WIDTH - 384, -200));
        buttons.put("Facebook", new Buttons("Facebook", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Facebook"), 96, 96, RuDragonGame.WIDTH / 2 - 160, RuDragonGame.HEIGHT + 4));
        buttons.put("Vkontakte", new Buttons("Vkontakte", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Vkontakte"), 96, 96, RuDragonGame.WIDTH / 2, RuDragonGame.HEIGHT + 4));
        buttons.put("Twitter", new Buttons("Twitter", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Twitter"), 96, 96, RuDragonGame.WIDTH / 2 + 160, RuDragonGame.HEIGHT + 4));
        buttons.put("GooglePlus", new Buttons("GooglePlus", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("GooglePlus"), 96, 96, RuDragonGame.WIDTH / 2 + 320, RuDragonGame.HEIGHT + 4));
        buttons.put("getXP", new Buttons("getXP", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("I_best"), 96, 96, RuDragonGame.WIDTH - 256, RuDragonGame.HEIGHT - 128));
        buttons.put("getHAM", new Buttons("getHAM", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Love_eat"), 96, 96, RuDragonGame.WIDTH - 128, RuDragonGame.HEIGHT - 128));

        buttons.get("menu").addListener(new ButtonsInputListener(buttons.get("menu").getName()));
        buttons.get("upgrade").addListener(new ButtonsInputListener(buttons.get("upgrade").getName()));
        buttons.get("achievements").addListener(new ButtonsInputListener(buttons.get("achievements").getName()));
        buttons.get("shop").addListener(new ButtonsInputListener(buttons.get("shop").getName()));
        buttons.get("encyclopedia").addListener(new ButtonsInputListener(buttons.get("encyclopedia").getName()));
        buttons.get("setting").addListener(new ButtonsInputListener(buttons.get("setting").getName()));
        buttons.get("credits").addListener(new ButtonsInputListener(buttons.get("credits").getName()));
        buttons.get("help").addListener(new ButtonsInputListener(buttons.get("help").getName()));
        buttons.get("exit").addListener(new ButtonsInputListener(buttons.get("exit").getName()));
        buttons.get("Roar").addListener(new ButtonsInputListener(buttons.get("Roar").getName()));
        buttons.get("FendOff").addListener(new ButtonsInputListener(buttons.get("FendOff").getName()));
        buttons.get("Facebook").addListener(new ButtonsInputListener(buttons.get("Facebook").getName()));
        buttons.get("Vkontakte").addListener(new ButtonsInputListener(buttons.get("Vkontakte").getName()));
        buttons.get("Twitter").addListener(new ButtonsInputListener(buttons.get("Twitter").getName()));
        buttons.get("GooglePlus").addListener(new ButtonsInputListener(buttons.get("GooglePlus").getName()));
        buttons.get("getXP").addListener(new ButtonsInputListener(buttons.get("getXP").getName()));
        buttons.get("getHAM").addListener(new ButtonsInputListener(buttons.get("getHAM").getName()));

        stage.addActor(buttons.get("menu"));
        stage.addActor(buttons.get("upgrade"));
        stage.addActor(buttons.get("achievements"));
        stage.addActor(buttons.get("shop"));
        stage.addActor(buttons.get("encyclopedia"));
        stage.addActor(buttons.get("setting"));
        stage.addActor(buttons.get("credits"));
        stage.addActor(buttons.get("help"));
        stage.addActor(buttons.get("exit"));
        stage.addActor(buttons.get("Roar"));
        stage.addActor(buttons.get("FendOff"));
        stage.addActor(buttons.get("Facebook"));
        stage.addActor(buttons.get("Vkontakte"));
        stage.addActor(buttons.get("Twitter"));
        stage.addActor(buttons.get("GooglePlus"));
        stage.addActor(buttons.get("getXP"));
        stage.addActor(buttons.get("getHAM"));
    }

    private void textMenu() {
        text.put("upgrade", new Label(RuDragonGame.getLanguage().textMenu.get("upgrade"), labelStyle));
        text.put("achievements", new Label(RuDragonGame.getLanguage().textMenu.get("achievements"), labelStyle));
        text.put("store", new Label(RuDragonGame.getLanguage().textMenu.get("store"), labelStyle));
        text.put("encyclopedia", new Label(RuDragonGame.getLanguage().textMenu.get("encyclopedia"), labelStyle));
        text.put("settings", new Label(RuDragonGame.getLanguage().textMenu.get("settings"), labelStyle));
        if (RuDragonGame.getTimeBonus().getTimeXP() == 0) text.put("timeBonusXP", new Label("+15", new LabelStyle(RuDragonGame.getFontTTF().getFont25(), Color.RED)));
        else text.put("timeBonusXP", new Label("" + RuDragonGame.getTimeBonus().getTimeXP(), new LabelStyle(RuDragonGame.getFontTTF().getFont25(), Color.RED)));
        if (RuDragonGame.getTimeBonus().getTimeHAM() == 0) text.put("timeBonusHAM", new Label("+25", new LabelStyle(RuDragonGame.getFontTTF().getFont25(), Color.RED)));
        else text.put("timeBonusHAM", new Label("" + RuDragonGame.getTimeBonus().getTimeHAM(), new LabelStyle(RuDragonGame.getFontTTF().getFont25(), Color.RED)));


        text.get("upgrade").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("upgrade").getPrefWidth() / 2, (RuDragonGame.HEIGHT - 128 + 32) * RuDragonGame.getRatioMonitorH());
        text.get("achievements").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("achievements").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 33) * 2) * RuDragonGame.getRatioMonitorH());
        text.get("store").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("store").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 33) * 3) * RuDragonGame.getRatioMonitorH());
        text.get("encyclopedia").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("encyclopedia").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 34) * 4) * RuDragonGame.getRatioMonitorH());
        text.get("settings").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("settings").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 34) * 5) * RuDragonGame.getRatioMonitorH());
        text.get("timeBonusXP").setPosition((RuDragonGame.WIDTH - 208) * RuDragonGame.getRatioMonitorW() - text.get("timeBonusXP").getPrefWidth() / 2, (RuDragonGame.HEIGHT - 160) * RuDragonGame.getRatioMonitorH());
        text.get("timeBonusHAM").setPosition((RuDragonGame.WIDTH - 80) * RuDragonGame.getRatioMonitorW() - text.get("timeBonusHAM").getPrefWidth() / 2, (RuDragonGame.HEIGHT - 160) * RuDragonGame.getRatioMonitorH());

        stage.addActor(text.get("upgrade"));
        stage.addActor(text.get("achievements"));
        stage.addActor(text.get("store"));
        stage.addActor(text.get("encyclopedia"));
        stage.addActor(text.get("settings"));
        stage.addActor(text.get("timeBonusXP"));
        stage.addActor(text.get("timeBonusHAM"));
    }

    public void clearTextMenu() {
        text.get("upgrade").setText(RuDragonGame.getLanguage().textMenu.get("upgrade"));
        text.get("achievements").setText(RuDragonGame.getLanguage().textMenu.get("achievements"));
        text.get("store").setText(RuDragonGame.getLanguage().textMenu.get("store"));
        text.get("encyclopedia").setText(RuDragonGame.getLanguage().textMenu.get("encyclopedia"));
        text.get("settings").setText(RuDragonGame.getLanguage().textMenu.get("settings"));

        text.get("upgrade").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("upgrade").getPrefWidth() / 2, (RuDragonGame.HEIGHT - 128 - 32) * RuDragonGame.getRatioMonitorH() - text.get("upgrade").getPrefHeight());
        text.get("achievements").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("achievements").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 33) * 2) * RuDragonGame.getRatioMonitorH() - text.get("achievements").getPrefHeight());
        text.get("store").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("store").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 33) * 3) * RuDragonGame.getRatioMonitorH() - text.get("store").getPrefHeight());
        text.get("encyclopedia").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("encyclopedia").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 34) * 4) * RuDragonGame.getRatioMonitorH() - text.get("encyclopedia").getPrefHeight());
        text.get("settings").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("settings").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 34) * 5) * RuDragonGame.getRatioMonitorH() - text.get("settings").getPrefHeight());
    }

    public void upMenu(float deltaTime, float eating) {
        if (RuDragonGame.getPreference().isOnWindow()) {
            stage.addActor(buttons.get("menu"));
            stage.addActor(buttons.get("upgrade"));
            stage.addActor(buttons.get("achievements"));
            stage.addActor(buttons.get("shop"));
            stage.addActor(buttons.get("encyclopedia"));
            stage.addActor(buttons.get("setting"));
            stage.addActor(buttons.get("credits"));
            stage.addActor(buttons.get("help"));
            stage.addActor(buttons.get("exit"));
            stage.addActor(buttons.get("Roar"));
            stage.addActor(buttons.get("FendOff"));
            stage.addActor(buttons.get("Facebook"));
            stage.addActor(buttons.get("Vkontakte"));
            stage.addActor(buttons.get("Twitter"));
            stage.addActor(buttons.get("GooglePlus"));
            stage.addActor(text.get("version"));
            stage.addActor(text.get("upgrade"));
            stage.addActor(text.get("achievements"));
            stage.addActor(text.get("store"));
            stage.addActor(text.get("encyclopedia"));
            stage.addActor(text.get("settings"));
            stage.addActor(text.get("RoarNumber"));
            Gdx.input.setInputProcessor(stage);
            RuDragonGame.getPreference().setOnWindow(false);
        }
        if (RuDragonGame.getPreference().isOnMenu() && menuX < 256) {
            if (this.eating != eating) this.eating = eating;
            if (menuX == 0) menuX = 1;
            menuX = menuX + deltaTime * 400;
            if (menuX > 256) menuX = 256;
            if (menuSoc > 0) menuSoc = menuSoc - deltaTime * 200;
            if (menuSoc < 0) menuSoc = 0;
            buttons.get("menu").setPosition(menuX, RuDragonGame.HEIGHT - 220);
            buttons.get("upgrade").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - 128 - 35);
            buttons.get("achievements").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 2);
            buttons.get("shop").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 3);
            buttons.get("encyclopedia").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 4);
            buttons.get("setting").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 5);
            buttons.get("credits").setPosition(menuX - 256 + 40, 175);
            buttons.get("help").setPosition(menuX - 64 - 40, 175);
            buttons.get("exit").setPosition(menuX - 256 / 2 - 96 / 2, 60);
            text.get("version").setPosition((menuX - 256 + 16) * RuDragonGame.getRatioMonitorW(), 8 * RuDragonGame.getRatioMonitorH());

            text.get("upgrade").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("upgrade").getPrefWidth() / 2, (RuDragonGame.HEIGHT - 128 - 32) * RuDragonGame.getRatioMonitorH() - text.get("upgrade").getPrefHeight());
            text.get("achievements").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("achievements").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 33) * 2) * RuDragonGame.getRatioMonitorH() - text.get("achievements").getPrefHeight());
            text.get("store").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("store").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 33) * 3) * RuDragonGame.getRatioMonitorH() - text.get("store").getPrefHeight());
            text.get("encyclopedia").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("encyclopedia").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 34) * 4) * RuDragonGame.getRatioMonitorH() - text.get("encyclopedia").getPrefHeight());
            text.get("settings").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("settings").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 34) * 5) * RuDragonGame.getRatioMonitorH() - text.get("settings").getPrefHeight());

            buttons.get("Facebook").setPosition(RuDragonGame.WIDTH / 2 - 160, RuDragonGame.HEIGHT - 96 + menuSoc);
            buttons.get("Vkontakte").setPosition(RuDragonGame.WIDTH / 2, RuDragonGame.HEIGHT - 96 + menuSoc);
            buttons.get("Twitter").setPosition(RuDragonGame.WIDTH / 2 + 160, RuDragonGame.HEIGHT - 96 + menuSoc);
            buttons.get("GooglePlus").setPosition(RuDragonGame.WIDTH / 2 + 320, RuDragonGame.HEIGHT - 96 + menuSoc);
        } else if (!RuDragonGame.getPreference().isOnMenu() && menuX > 1) {
            menuX = menuX - deltaTime * 500;
            if (menuX < 0) menuX = 0;
            if (menuSoc < 100) menuSoc = menuSoc + deltaTime * 400;
            if (menuSoc > 100) menuSoc = 100;
            buttons.get("menu").setPosition(menuX * RuDragonGame.getRatioMonitorW(), RuDragonGame.HEIGHT - 220);
            buttons.get("upgrade").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - 128 - 35);
            buttons.get("achievements").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 2);
            buttons.get("shop").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 3);
            buttons.get("encyclopedia").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 4);
            buttons.get("setting").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 5);
            buttons.get("credits").setPosition(menuX - 256 + 40, 175 * RuDragonGame.getRatioMonitorH());
            buttons.get("help").setPosition(menuX - 64 - 40, 175 * RuDragonGame.getRatioMonitorH());
            buttons.get("exit").setPosition(menuX - 256 / 2 - 96 / 2, 60 * RuDragonGame.getRatioMonitorH());
            text.get("version").setPosition((menuX - 256 + 16) * RuDragonGame.getRatioMonitorW(), 8 * RuDragonGame.getRatioMonitorH());

            text.get("upgrade").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("upgrade").getPrefWidth() / 2, (RuDragonGame.HEIGHT - 128 - 32) * RuDragonGame.getRatioMonitorH() - text.get("upgrade").getPrefHeight());
            text.get("achievements").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("achievements").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 32) * 2) * RuDragonGame.getRatioMonitorH() - text.get("achievements").getPrefHeight());
            text.get("store").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("store").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 33) * 3) * RuDragonGame.getRatioMonitorH() - text.get("store").getPrefHeight());
            text.get("encyclopedia").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("encyclopedia").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 34) * 4) * RuDragonGame.getRatioMonitorH() - text.get("encyclopedia").getPrefHeight());
            text.get("settings").setPosition((menuX - 128) * RuDragonGame.getRatioMonitorW() - text.get("settings").getPrefWidth() / 2, (RuDragonGame.HEIGHT - (128 + 34) * 5) * RuDragonGame.getRatioMonitorH() - text.get("settings").getPrefHeight());

            buttons.get("Facebook").setPosition(RuDragonGame.WIDTH / 2 - 160, RuDragonGame.HEIGHT - 96 + menuSoc);
            buttons.get("Vkontakte").setPosition(RuDragonGame.WIDTH / 2, RuDragonGame.HEIGHT - 96 + menuSoc);
            buttons.get("Twitter").setPosition(RuDragonGame.WIDTH / 2 + 160, RuDragonGame.HEIGHT - 96 + menuSoc);
            buttons.get("GooglePlus").setPosition(RuDragonGame.WIDTH / 2 + 320, RuDragonGame.HEIGHT - 96 + menuSoc);
        }
        if (RuDragonGame.getPreference().isGameRun()) {
            buttons.get("Roar").setPosition(RuDragonGame.WIDTH - 192, 40);
            buttons.get("FendOff").setPosition(RuDragonGame.WIDTH - 384, 40);
            text.get("RoarNumber").setPosition(RuDragonGame.WIDTH - 128 - text.get("RoarNumber").getPrefWidth() / 2, 12);
        } else {
            buttons.get("Roar").setPosition(RuDragonGame.WIDTH - 192, -200);
            buttons.get("FendOff").setPosition(RuDragonGame.WIDTH - 384, -200);
        }

        if (RuDragonGame.getTimeBonus().isUpTimeXP()) {
            if (RuDragonGame.getTimeBonus().getTimeXP() == 0) text.get("timeBonusXP").setText("+15");
            else text.get("timeBonusXP").setText("" + RuDragonGame.getTimeBonus().getTimeXP());
            text.get("timeBonusXP").setPosition((RuDragonGame.WIDTH - 208) * RuDragonGame.getRatioMonitorW() - text.get("timeBonusXP").getPrefWidth() / 2, (RuDragonGame.HEIGHT - 160) * RuDragonGame.getRatioMonitorH());

            RuDragonGame.getTimeBonus().setUpTimeXP();
        }
        if (RuDragonGame.getTimeBonus().isUpTimeHAM()) {
            if (RuDragonGame.getTimeBonus().getTimeHAM() == 0) text.get("timeBonusHAM").setText("+25");
            else text.get("timeBonusHAM").setText("" + RuDragonGame.getTimeBonus().getTimeHAM());
            text.get("timeBonusHAM").setPosition((RuDragonGame.WIDTH - 80) * RuDragonGame.getRatioMonitorW() - text.get("timeBonusHAM").getPrefWidth() / 2, (RuDragonGame.HEIGHT - 160) * RuDragonGame.getRatioMonitorH());

            RuDragonGame.getTimeBonus().setUpTimeHAM();
        }
    }

    public void renderMenuButtonStart(SpriteBatch batch) {
        if (!RuDragonGame.getPreference().isModalWindow() && !RuDragonGame.getPreference().isGameOver()) {
            if (!RuDragonGame.getPreference().isGameRun()) batch.draw(RuDragonGame.getTextures().textureRegions.get("start"), menuX, RuDragonGame.HEIGHT - 220);
            else if (RuDragonGame.getPreference().isGameRun()) batch.draw(RuDragonGame.getTextures().textureRegions.get("pause"), menuX, RuDragonGame.HEIGHT - 220);
        }
    }

    public Stage getStage() {
        return stage;
    }

    public float getMenuX() {
        return menuX;
    }

    public Settings getSettingMod() {
        return settingMod;
    }

    public Credits getCreditsMod() {
        return creditsMod;
    }

    public Exit getExitMod() {
        return exitMod;
    }

    public void dispose () {
        try {
            stage.dispose();
            settingMod.dispose();
            exitMod.dispose();
            creditsMod.dispose();
            buttons.clear();
            text.clear();
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
             if (name.equals("upgrade")) {
                 buttons.get("upgrade").setSize(120, 120);
                 buttons.get("upgrade").setPosition(menuX - 188, RuDragonGame.HEIGHT - 159);
             } else if (name.equals("achievements")) {
                 buttons.get("achievements").setSize(120, 120);
                 buttons.get("achievements").setPosition(menuX - 128 - 60, RuDragonGame.HEIGHT - (128 + 35) * 2 + 4);
             } else if (name.equals("shop")) {
                 buttons.get("shop").setSize(120, 120);
                 buttons.get("shop").setPosition(menuX - 128 - 60, RuDragonGame.HEIGHT - (128 + 35) * 3 + 4);
             } else if (name.equals("encyclopedia")) {
                 buttons.get("encyclopedia").setSize(120, 120);
                 buttons.get("encyclopedia").setPosition(menuX - 128 - 60, RuDragonGame.HEIGHT - (128 + 35) * 4 + 4);
             } else if (name.equals("setting")) {
                 buttons.get("setting").setSize(120, 120);
                 buttons.get("setting").setPosition(menuX - 128 - 60, RuDragonGame.HEIGHT - (128 + 35) * 5 + 4);
             } else if (name.equals("credits")) {
                 buttons.get("credits").setSize(56, 56);
                 buttons.get("credits").setPosition(menuX - 256 + 44, 179);
             } else if (name.equals("help")) {
                 buttons.get("help").setSize(56, 56);
                 buttons.get("help").setPosition(menuX - 100, 179);
             } else if (name.equals("exit")) {
                 buttons.get("exit").setSize(88, 88);
                 buttons.get("exit").setPosition(menuX - 256 / 2 - 96 / 2 + 4, 64);
             } else if (name.equals("Roar")) {
                 if (RuDragonGame.getPreference().loadRoar() >= 1 && RuDragonGame.getPreference().isGameRun()) {
                     buttons.get("Roar").setSize(120, 120);
                     buttons.get("Roar").setPosition(RuDragonGame.WIDTH - 188, 44);
                 }
             } else if (name.equals("FendOff")) {
                 if (RuDragonGame.getPreference().loadFendOff() && RuDragonGame.getPreference().isGameRun()) {
                     buttons.get("FendOff").setSize(120, 120);
                     buttons.get("FendOff").setPosition(RuDragonGame.WIDTH - 380, 44);
                     RuDragonGame.getPreference().setFendOffDragon(true);
                 }
             } else if (name.equals("Facebook")) {
                 buttons.get("Facebook").setSize(90, 90);
                 buttons.get("Facebook").setPosition(RuDragonGame.WIDTH / 2 - 157, RuDragonGame.HEIGHT - 90);
             } else if (name.equals("Vkontakte")) {
                 buttons.get("Vkontakte").setSize(90, 90);
                 buttons.get("Vkontakte").setPosition(RuDragonGame.WIDTH / 2 + 3, RuDragonGame.HEIGHT - 90);
             } else if (name.equals("Twitter")) {
                 buttons.get("Twitter").setSize(90, 90);
                 buttons.get("Twitter").setPosition(RuDragonGame.WIDTH / 2 + 163, RuDragonGame.HEIGHT - 90);
             } else if (name.equals("GooglePlus")) {
                 buttons.get("GooglePlus").setSize(90, 90);
                 buttons.get("GooglePlus").setPosition(RuDragonGame.WIDTH / 2 + 323, RuDragonGame.HEIGHT - 90);
             }
            return true;
        }

        @Override
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            if (!name.equals("Roar")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
            }

            if (name.equals("menu")) {
                if (!RuDragonGame.getPreference().isOnMenu()) {
                    RuDragonGame.getPreference().setOnMenu(true);
                    RuDragonGame.getPreference().setGameRun(false);
                    text.get("RoarNumber").setText("");
                    if (RuDragonGame.getPreference().loadMusic() == 1) {
                        RuDragonGame.getMusicSound().pauseRun();
                        RuDragonGame.getMusicSound().startFon();
                    }
                } else if (RuDragonGame.getPreference().isOnMenu()) {
                    RuDragonGame.getPreference().setOnMenu(false);
                    RuDragonGame.getPreference().setGameRun(true);
                    text.get("RoarNumber").setText(String.valueOf(RuDragonGame.getPreference().loadRoar()));
                    if (RuDragonGame.getPreference().loadMusic() == 1) {
                        RuDragonGame.getMusicSound().startRun();
                        RuDragonGame.getMusicSound().pauseFon();
                    }
                }
            } else if (name.equals("upgrade")) {
                buttons.get("upgrade").setSize(128, 128);
                buttons.get("upgrade").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - 163);
                if (eating != 0) RuDragonGame.getPreference().saveBank((int) eating, 0);
                RuDragonGame.getMusicSound().stopRun();
                RuDragonGame.getGame().set(new UpgradeScreen(RuDragonGame.getGame()));
            } else if (name.equals("achievements")) {
                buttons.get("achievements").setSize(128, 128);
                buttons.get("achievements").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 2);
                if (eating != 0) RuDragonGame.getPreference().saveBank((int) eating, 0);
                RuDragonGame.getMusicSound().stopRun();
                RuDragonGame.getGame().set(new AchievementsScreen(RuDragonGame.getGame()));
            } else if (name.equals("shop")) {
                buttons.get("shop").setSize(128, 128);
                buttons.get("shop").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 3);
                if (eating != 0) RuDragonGame.getPreference().saveBank((int) eating, 0);
                RuDragonGame.getMusicSound().stopRun();
                RuDragonGame.getGame().set(new StoreScreen(RuDragonGame.getGame()));
            } else if (name.equals("encyclopedia")) {
                buttons.get("encyclopedia").setSize(128, 128);
                buttons.get("encyclopedia").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 4);
                if (eating != 0) RuDragonGame.getPreference().saveBank((int) eating, 0);
                RuDragonGame.getMusicSound().stopRun();
                RuDragonGame.getGame().set(new EncyclopediaScreen(RuDragonGame.getGame()));
            } else if (name.equals("setting")) {
                buttons.get("setting").setSize(128, 128);
                buttons.get("setting").setPosition(menuX - 128 - 64, RuDragonGame.HEIGHT - (128 + 35) * 5);
                RuDragonGame.getPreference().setModalWindow(true);
                settingMod.onSetting(true);
            } else if (name.equals("credits")) {
                buttons.get("credits").setSize(64, 64);
                buttons.get("credits").setPosition(menuX - 256 + 40, 175);
                RuDragonGame.getPreference().setModalWindow(true);
                creditsMod.onCredits(true);
            } else if (name.equals("help")) {
                buttons.get("help").setSize(64, 64);
                buttons.get("help").setPosition(menuX - 104, 175);
                RuDragonGame.getPreference().saveBank((int) eating, 0);
                RuDragonGame.getMusicSound().stopRun();
                RuDragonGame.getGame().set(new HelpScreen(RuDragonGame.getGame()));
            } else if (name.equals("exit")) {
                buttons.get("exit").setSize(96, 96);
                buttons.get("exit").setPosition(menuX - 256 / 2 - 96 / 2, 60);
                RuDragonGame.getPreference().setModalWindow(true);
                exitMod.onExit(true);
            } else if (name.equals("Roar")) {
                if (RuDragonGame.getPreference().loadRoar() >= 1 && RuDragonGame.getPreference().isGameRun()) {
                    buttons.get("Roar").setSize(128, 128);
                    buttons.get("Roar").setPosition(RuDragonGame.WIDTH - 192, 40);
                    if (RuDragonGame.getPreference().loadSound() == 1) {
                        RuDragonGame.getMusicSound().getClick().play();
                        RuDragonGame.getMusicSound().sounds.get("dragon").play();
                    }
                    RuDragonGame.getPreference().saveRoar(0, 1);
                    RuDragonGame.getPreference().setRoarDragon(true);
                    text.get("RoarNumber").setText(String.valueOf(RuDragonGame.getPreference().loadRoar()));
                }
            } else if (name.equals("FendOff")) {
                if (RuDragonGame.getPreference().loadFendOff() && RuDragonGame.getPreference().isGameRun()) {
                    buttons.get("FendOff").setSize(128, 128);
                    buttons.get("FendOff").setPosition(RuDragonGame.WIDTH - 384, 40);
                    RuDragonGame.getPreference().setFendOffDragon(false);
                }
            } else if (name.equals("Facebook")) {
                buttons.get("Facebook").setSize(96, 96);
                buttons.get("Facebook").setPosition(RuDragonGame.WIDTH / 2 - 160, RuDragonGame.HEIGHT - 96);
                Gdx.net.openURI("https://www.facebook.com/groups/rashka.studio/");
            } else if (name.equals("Vkontakte")) {
                buttons.get("Vkontakte").setSize(96, 96);
                buttons.get("Vkontakte").setPosition(RuDragonGame.WIDTH / 2, RuDragonGame.HEIGHT - 96);
                Gdx.net.openURI("https://vk.com/rashka.studio");
            } else if (name.equals("Twitter")) {
                buttons.get("Twitter").setSize(96, 96);
                buttons.get("Twitter").setPosition(RuDragonGame.WIDTH / 2 + 160, RuDragonGame.HEIGHT - 96);
                Gdx.net.openURI("https://twitter.com/rashka_studio/");
            } else if (name.equals("GooglePlus")) {
                buttons.get("GooglePlus").setSize(96, 96);
                buttons.get("GooglePlus").setPosition(RuDragonGame.WIDTH / 2 + 320, RuDragonGame.HEIGHT - 96);
                Gdx.net.openURI("https://plus.google.com/communities/117036335723806113531/");
            } else if (name.equals("getXP")) {
                if (RuDragonGame.getTimeBonus().isActiveXP()) {
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    RuDragonGame.getPreference().saveXP(15);
                    RuDragonGame.getTimeBonus().setTimeResetXP();
                }
            } else if (name.equals("getHAM")) {
                if (RuDragonGame.getTimeBonus().isActiveHAM()) {
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    RuDragonGame.getPreference().saveBank(25, 0);
                    RuDragonGame.getTimeBonus().setTimeResetHAM();
                }
            }
        }
    }
}