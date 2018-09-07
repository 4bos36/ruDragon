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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.StringBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;
import studio.rashka.Lib.btn.Buttons;
import studio.rashka.RuDragonGame;

public class AchievementsScreen extends State {

    private Stage stage;
    private Calendar calendar;
    private SimpleDateFormat time;

    private Map<String, LabelStyle> textStyles;
    private Map<String, Label> text;
    private Map<String, Buttons> buttons;

    private ArrayList<TextureRegion> hams;
    private ArrayList<Label> progressUp, meatUp, progressDown, meatDown;

    public AchievementsScreen(final Game game) {
        super(game);
        RuDragonGame.getLanguage().textAchievements();
        calendar = Calendar.getInstance();
        time = new SimpleDateFormat("HH");

        stage = new Stage();

        text = new HashMap<String, Label>();
        textStyles = new HashMap<String, LabelStyle>();
        textStyles.put("BigBlack", new LabelStyle(RuDragonGame.getFontTTF().getFont48(), Color.WHITE));
        textStyles.put("BigWhite", new LabelStyle(RuDragonGame.getFontTTF().getFont38(), Color.WHITE));

        progressUp = new ArrayList<Label>();
        progressDown = new ArrayList<Label>();
        meatUp = new ArrayList<Label>();
        meatDown = new ArrayList<Label>();

        hams = new ArrayList<TextureRegion>();
        for (int i = 0; i < 7; i++) hams.add(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("ham")));

        text.put("textFull", new Label("", textStyles.get("BigBlack")));
        text.put("text", new Label(new StringBuilder(RuDragonGame.getLanguage().text.get("nullText")), textStyles.get("BigBlack")));
        text.get("text").setPosition(640 * RuDragonGame.getRatioMonitorW(), 192 * RuDragonGame.getRatioMonitorH());

        text.put("textInfo", new Label(new StringBuilder(RuDragonGame.getLanguage().text.get("textInfo")), textStyles.get("BigWhite")));
        text.get("textInfo").setPosition(8 * RuDragonGame.getRatioMonitorW(), 4 * RuDragonGame.getRatioMonitorH());

        buttons();
        progressPoints();

        stage.addActor(text.get("text"));
        stage.addActor(text.get("textFull"));
        stage.addActor(text.get("textInfo"));
        for (int i = 0; i < 7; i++) {
            stage.addActor(progressUp.get(i));
            stage.addActor(meatUp.get(i));
        }
        for (int i = 0; i < 6; i++) {
            stage.addActor(progressDown.get(i));
            stage.addActor(meatDown.get(i));
        }
        Gdx.input.setInputProcessor(stage);

        if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().startCave();
    }

    private void buttons() {
        buttons = new HashMap<String, Buttons>();
        buttons.put("Back", new Buttons("Back", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Back"), 128, 64, 32, RuDragonGame.HEIGHT - 96));
        buttons.put("lvlUp", new Buttons("lvlUp", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Up_lvl"), 128, 128, 128, RuDragonGame.HEIGHT / 2 + 192));
        buttons.put("start", new Buttons("start", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Start"), 128, 128, 384, RuDragonGame.HEIGHT / 2 + 192));
        buttons.put("fly", new Buttons("fly", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Fly"), 128, 128, 640, RuDragonGame.HEIGHT / 2 + 192));
        buttons.put("run", new Buttons("run", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Run"), 128, 128, 896, RuDragonGame.HEIGHT / 2 + 192));
        buttons.put("dragonUp", new Buttons("dragonUp", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Up_dragon"), 128, 128, 1152, RuDragonGame.HEIGHT / 2 + 192));
        buttons.put("homeLove", new Buttons("homeLove", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Love_home"), 128, 128, 1408, RuDragonGame.HEIGHT / 2 + 192));
        buttons.put("iBest", new Buttons("iBest", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("I_best"), 128, 128, 1664, RuDragonGame.HEIGHT / 2 + 192));
        buttons.put("banker", new Buttons("banker", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Banker"), 128, 128, 256, RuDragonGame.HEIGHT / 2 - 128));
        buttons.put("eatLove", new Buttons("eatLove", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Love_eat"), 128, 128, 512, RuDragonGame.HEIGHT / 2 - 128));
        buttons.put("duckLove", new Buttons("duckLove", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Love_duck"), 128, 128, 768, RuDragonGame.HEIGHT / 2 - 128));
        buttons.put("goatLove", new Buttons("goatLove", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Love_goat"), 128, 128, 1024, RuDragonGame.HEIGHT / 2 - 128));
        buttons.put("pigLove", new Buttons("pigLove", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Love_pig"), 128, 128, 1280, RuDragonGame.HEIGHT / 2 - 128));
        buttons.put("kineLove", new Buttons("kineLove", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Love_kine"), 128, 128, 1536, RuDragonGame.HEIGHT / 2 - 128));

        buttons.get("Back").addListener(new ButtonsInputListener(buttons.get("Back").getName()));
        buttons.get("lvlUp").addListener(new ButtonsInputListener(buttons.get("lvlUp").getName()));
        buttons.get("start").addListener(new ButtonsInputListener(buttons.get("start").getName()));
        buttons.get("fly").addListener(new ButtonsInputListener(buttons.get("fly").getName()));
        buttons.get("run").addListener(new ButtonsInputListener(buttons.get("run").getName()));
        buttons.get("dragonUp").addListener(new ButtonsInputListener(buttons.get("dragonUp").getName()));
        buttons.get("homeLove").addListener(new ButtonsInputListener(buttons.get("homeLove").getName()));
        buttons.get("iBest").addListener(new ButtonsInputListener(buttons.get("iBest").getName()));
        buttons.get("banker").addListener(new ButtonsInputListener(buttons.get("banker").getName()));
        buttons.get("eatLove").addListener(new ButtonsInputListener(buttons.get("eatLove").getName()));
        buttons.get("duckLove").addListener(new ButtonsInputListener(buttons.get("duckLove").getName()));
        buttons.get("goatLove").addListener(new ButtonsInputListener(buttons.get("goatLove").getName()));
        buttons.get("pigLove").addListener(new ButtonsInputListener(buttons.get("pigLove").getName()));
        buttons.get("kineLove").addListener(new ButtonsInputListener(buttons.get("kineLove").getName()));

        stage.addActor(buttons.get("Back"));
        stage.addActor(buttons.get("lvlUp"));
        stage.addActor(buttons.get("start"));
        stage.addActor(buttons.get("fly"));
        stage.addActor(buttons.get("run"));
        stage.addActor(buttons.get("dragonUp"));
        stage.addActor(buttons.get("homeLove"));
        stage.addActor(buttons.get("iBest"));
        stage.addActor(buttons.get("banker"));
        stage.addActor(buttons.get("eatLove"));
        stage.addActor(buttons.get("duckLove"));
        stage.addActor(buttons.get("goatLove"));
        stage.addActor(buttons.get("pigLove"));
        stage.addActor(buttons.get("kineLove"));
    }

    private void labelDescription() {
        text.get("text").setPosition(256 * RuDragonGame.getRatioMonitorW(), 192 * RuDragonGame.getRatioMonitorH());
        text.get("textFull").setPosition(192 * RuDragonGame.getRatioMonitorW(), 256 * RuDragonGame.getRatioMonitorH() - text.get("textFull").getPrefHeight());
        text.get("textFull").setWidth((RuDragonGame.WIDTH - 192) * RuDragonGame.getRatioMonitorW());
        text.get("textFull").setWrap(true);
    }

    private static float round(float number, int scale) { // округление числа
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
    }

    private void progressPoints() {
        int level, start, glutton, upgrade, home, best, bank, duckLove, goatLove, pigLove, kineLove;
        float fly, run;
        level = RuDragonGame.getPreference().loadLevel();
        if (level == 0) {
            progressUp.add(0, new Label("1 lvl", textStyles.get("BigBlack")));
            meatUp.add(0, new Label("+50", textStyles.get("BigBlack")));
        }
        else if (level >= 1 && level < 3) {
            progressUp.add(0, new Label("3 lvl", textStyles.get("BigBlack")));
            meatUp.add(0, new Label("+135", textStyles.get("BigBlack")));
        }
        else if (level >= 3 && level < 5) {
            progressUp.add(0, new Label("5 lvl", textStyles.get("BigBlack")));
            meatUp.add(0, new Label("+250", textStyles.get("BigBlack")));
        }
        else if (level >= 5 && level < 8) {
            progressUp.add(0, new Label("8 lvl", textStyles.get("BigBlack")));
            meatUp.add(0, new Label("+400", textStyles.get("BigBlack")));
        }
        else if (level >= 8 && level < 10) {
            progressUp.add(0, new Label("10 lvl", textStyles.get("BigBlack")));
            meatUp.add(0, new Label("+550", textStyles.get("BigBlack")));
        }
        else if (level == 10) {
            progressUp.add(0, new Label("Max", textStyles.get("BigBlack")));
            meatUp.add(0, new Label("-", textStyles.get("BigBlack")));
        }
        progressUp.get(0).setPosition(192 * RuDragonGame.getRatioMonitorW() - progressUp.get(0).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 132) * RuDragonGame.getRatioMonitorH());
        meatUp.get(0).setPosition(192 * RuDragonGame.getRatioMonitorW() - meatUp.get(0).getPrefWidth(), (RuDragonGame.HEIGHT / 2 + 72) * RuDragonGame.getRatioMonitorH());

        start = RuDragonGame.getPreference().loadStart();
        if (start < 10) {
            progressUp.add(1, new Label(new StringBuilder().append(10 - start), textStyles.get("BigBlack")));
            meatUp.add(1, new Label("+30", textStyles.get("BigBlack")));
        }
        else if (start >= 10 && start < 100) {
            progressUp.add(1, new Label(new StringBuilder().append(100 - start), textStyles.get("BigBlack")));
            meatUp.add(1, new Label("+315", textStyles.get("BigBlack")));
        }
        else if (start >= 100 && start < 1000) {
            progressUp.add(1, new Label(new StringBuilder().append(1000 - start), textStyles.get("BigBlack")));
            meatUp.add(1, new Label("+1210", textStyles.get("BigBlack")));
        }
        else if (start >= 1000 && start < 1800) {
            progressUp.add(1, new Label(new StringBuilder().append(1800 - start), textStyles.get("BigBlack")));
            meatUp.add(1, new Label("+2100", textStyles.get("BigBlack")));
        }
        else if (start >= 1800 && start < 3500) {
            progressUp.add(1, new Label(new StringBuilder().append(3500 - start), textStyles.get("BigBlack")));
            meatUp.add(1, new Label("+5800", textStyles.get("BigBlack")));
        }
        else if (start >= 3500) {
            progressUp.add(1, new Label("Max", textStyles.get("BigBlack")));
            meatUp.add(1, new Label("-", textStyles.get("BigBlack")));
        }
        progressUp.get(1).setPosition(448 * RuDragonGame.getRatioMonitorW() - progressUp.get(1).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 132) * RuDragonGame.getRatioMonitorH());
        meatUp.get(1).setPosition(448 * RuDragonGame.getRatioMonitorW() - meatUp.get(1).getPrefWidth(), (RuDragonGame.HEIGHT / 2 + 72) * RuDragonGame.getRatioMonitorH());

        fly = RuDragonGame.getPreference().loadFly();
        if (fly < 1000) {
            float flying = 1000 - fly;
            progressUp.add(2, new Label(new StringBuilder().append(round(flying, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(2, new Label("+250", textStyles.get("BigBlack")));
        }
        else if (fly >= 1000 && fly < 10000) {
            float flying = 10000 - fly;
            progressUp.add(2, new Label(new StringBuilder().append(round(flying, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(2, new Label("+650", textStyles.get("BigBlack")));
        }
        else if (fly >= 10000 && fly < 30000) {
            float flying = 30000 - fly;
            progressUp.add(2, new Label(new StringBuilder().append(round(flying, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(2, new Label("+1150", textStyles.get("BigBlack")));
        }
        else if (fly >= 30000 && fly < 60000) {
            float flying = 60000 - fly;
            progressUp.add(2, new Label(new StringBuilder().append(round(flying, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(2, new Label("+2050", textStyles.get("BigBlack")));
        }
        else if (fly >= 60000 && fly < 100000) {
            float flying = 100000 - fly;
            progressUp.add(2, new Label(new StringBuilder().append(round(flying, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(2, new Label("+3500", textStyles.get("BigBlack")));
        }
        else if (fly >= 100000 && fly < 200000) {
            float flying = 200000 - fly;
            progressUp.add(2, new Label(new StringBuilder().append(round(flying, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(2, new Label("+5850", textStyles.get("BigBlack")));
        }
        else if (fly >= 200000) {
            progressUp.add(2, new Label("Max", textStyles.get("BigBlack")));
            meatUp.add(2, new Label("-", textStyles.get("BigBlack")));
        }
        progressUp.get(2).setPosition(704 * RuDragonGame.getRatioMonitorW() - progressUp.get(2).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 132) * RuDragonGame.getRatioMonitorH());
        meatUp.get(2).setPosition(704 * RuDragonGame.getRatioMonitorW() - meatUp.get(2).getPrefWidth(), (RuDragonGame.HEIGHT / 2 + 72) * RuDragonGame.getRatioMonitorH());

        run = RuDragonGame.getPreference().loadRun();
        if (run < 500) {
            float runs = 500 - run;
            progressUp.add(3, new Label(new StringBuilder().append(round(runs, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(3, new Label("+250", textStyles.get("BigBlack")));
        }
        else if (run >= 500 && run < 4000) {
            float runs = 4000 - run;
            progressUp.add(3, new Label(new StringBuilder().append(round(runs, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(3, new Label("+650", textStyles.get("BigBlack")));
        }
        else if (run >= 4000 && run < 9000) {
            float runs = 9000 - run;
            progressUp.add(3, new Label(new StringBuilder().append(round(runs, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(3, new Label("+1300", textStyles.get("BigBlack")));
        }
        else if (run >= 9000 && run < 15000) {
            float runs = 15000 - run;
            progressUp.add(3, new Label(new StringBuilder().append(round(runs, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(3, new Label("+2100", textStyles.get("BigBlack")));
        }
        else if (run >= 15000 && run < 32500) {
            float runs = 32500 - run;
            progressUp.add(3, new Label(new StringBuilder().append(round(runs, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(3, new Label("+4750", textStyles.get("BigBlack")));
        }
        else if (run >= 32500 && run < 48000) {
            float runs = 48000 - run;
            progressUp.add(3, new Label(new StringBuilder().append(round(runs, 1)).append(" m"), textStyles.get("BigBlack")));
            meatUp.add(3, new Label("+5850", textStyles.get("BigBlack")));
        }
        else if (run >= 48000) {
            progressUp.add(3, new Label("Max", textStyles.get("BigBlack")));
            meatUp.add(3, new Label("-", textStyles.get("BigBlack")));
        }
        progressUp.get(3).setPosition(960 * RuDragonGame.getRatioMonitorW() - progressUp.get(3).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 132) * RuDragonGame.getRatioMonitorH());
        meatUp.get(3).setPosition(960 * RuDragonGame.getRatioMonitorW() - meatUp.get(3).getPrefWidth(), (RuDragonGame.HEIGHT / 2 + 72) * RuDragonGame.getRatioMonitorH());

        upgrade = RuDragonGame.getPreference().loadUpgrade();
        if (upgrade < 1) {
            progressUp.add(4, new Label("1", textStyles.get("BigBlack")));
            meatUp.add(4, new Label("+40", textStyles.get("BigBlack")));
        }
        else if (upgrade >= 1 && upgrade < 5) {
            progressUp.add(4, new Label(new StringBuilder().append(5 - upgrade), textStyles.get("BigBlack")));
            meatUp.add(4, new Label("+225", textStyles.get("BigBlack")));
        }
        else if (upgrade >= 5 && upgrade < 15) {
            progressUp.add(4, new Label(new StringBuilder().append(15 - upgrade), textStyles.get("BigBlack")));
            meatUp.add(4, new Label("+475", textStyles.get("BigBlack")));
        }
        else if (upgrade >= 15 && upgrade < 30) {
            progressUp.add(4, new Label(new StringBuilder().append(30 - upgrade), textStyles.get("BigBlack")));
            meatUp.add(4, new Label("+600", textStyles.get("BigBlack")));
        }
        else if (upgrade >= 30 && upgrade < 45) {
            progressUp.add(4, new Label(new StringBuilder().append(45 - upgrade), textStyles.get("BigBlack")));
            meatUp.add(4, new Label("+825", textStyles.get("BigBlack")));
        }
        else if (upgrade >= 45 && upgrade < 80) {
            progressUp.add(4, new Label(new StringBuilder().append(80 - upgrade), textStyles.get("BigBlack")));
            meatUp.add(4, new Label("+1200", textStyles.get("BigBlack")));
        }
        else if (upgrade >= 80 && upgrade < 130) {
            progressUp.add(4, new Label(new StringBuilder().append(130 - upgrade), textStyles.get("BigBlack")));
            meatUp.add(4, new Label("+1850", textStyles.get("BigBlack")));
        }
        else if (upgrade >= 130 && upgrade < 180) {
            progressUp.add(4, new Label(new StringBuilder().append(180 - upgrade), textStyles.get("BigBlack")));
            meatUp.add(4, new Label("+2200", textStyles.get("BigBlack")));
        }
        else if (upgrade >= 180 && upgrade < 210) {
            progressUp.add(4, new Label(new StringBuilder().append(210 - upgrade), textStyles.get("BigBlack")));
            meatUp.add(4, new Label("+2750", textStyles.get("BigBlack")));
        }
        else if (upgrade >= 210) {
            progressUp.add(4, new Label("Max", textStyles.get("BigBlack")));
            meatUp.add(4, new Label("-", textStyles.get("BigBlack")));
        }
        progressUp.get(4).setPosition(1216 * RuDragonGame.getRatioMonitorW() - progressUp.get(4).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 132) * RuDragonGame.getRatioMonitorH());
        meatUp.get(4).setPosition(1216 * RuDragonGame.getRatioMonitorW() - meatUp.get(4).getPrefWidth(), (RuDragonGame.HEIGHT / 2 + 72) * RuDragonGame.getRatioMonitorH());

        home = RuDragonGame.getPreference().loadHome();
        if (home < 1) {
            progressUp.add(5, new Label("1", textStyles.get("BigBlack")));
            meatUp.add(5, new Label("+100", textStyles.get("BigBlack")));
        }
        else if (home >= 1 && home < 3) {
            progressUp.add(5, new Label(new StringBuilder().append(3 - home), textStyles.get("BigBlack")));
            meatUp.add(5, new Label("+320", textStyles.get("BigBlack")));
        }
        else if (home >= 3 && home < 5) {
            progressUp.add(5, new Label(new StringBuilder().append(5 - home), textStyles.get("BigBlack")));
            meatUp.add(5, new Label("+530", textStyles.get("BigBlack")));
        }
        else if (home >= 5 && home < 7) {
            progressUp.add(5, new Label(new StringBuilder().append(7 - home), textStyles.get("BigBlack")));
            meatUp.add(5, new Label("+620", textStyles.get("BigBlack")));
        }
        else if (home >= 7 && home < 9) {
            progressUp.add(5, new Label(new StringBuilder().append(9 - home), textStyles.get("BigBlack")));
            meatUp.add(5, new Label("+750", textStyles.get("BigBlack")));
        }
        else if (home == 9) {
            progressUp.add(5, new Label("Max", textStyles.get("BigBlack")));
            meatUp.add(5, new Label("-", textStyles.get("BigBlack")));
        }
        progressUp.get(5).setPosition(1472 * RuDragonGame.getRatioMonitorW() - progressUp.get(5).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 132) * RuDragonGame.getRatioMonitorH());
        meatUp.get(5).setPosition(1472 * RuDragonGame.getRatioMonitorW() - meatUp.get(5).getPrefWidth(), (RuDragonGame.HEIGHT / 2 + 72) * RuDragonGame.getRatioMonitorH());

        best = RuDragonGame.getPreference().loadBest();
        if (best < 1) {
            progressUp.add(6, new Label("1", textStyles.get("BigBlack")));
            meatUp.add(6, new Label("+15", textStyles.get("BigBlack")));
        }
        else if (best >= 1 && best < 5) {
            progressUp.add(6, new Label(new StringBuilder().append(5 - best), textStyles.get("BigBlack")));
            meatUp.add(6, new Label("+430", textStyles.get("BigBlack")));
        }
        else if (best >= 5 && best < 10) {
            progressUp.add(6, new Label(new StringBuilder().append(10 - best), textStyles.get("BigBlack")));
            meatUp.add(6, new Label("+2500", textStyles.get("BigBlack")));
        }
        else if (best >= 10) {
            progressUp.add(6, new Label("Max", textStyles.get("BigBlack")));
            meatUp.add(6, new Label("-", textStyles.get("BigBlack")));
        }
        progressUp.get(6).setPosition(1728 * RuDragonGame.getRatioMonitorW() - progressUp.get(6).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 132) * RuDragonGame.getRatioMonitorH());
        meatUp.get(6).setPosition(1728 * RuDragonGame.getRatioMonitorW() - meatUp.get(6).getPrefWidth(), (RuDragonGame.HEIGHT / 2 + 72) * RuDragonGame.getRatioMonitorH());

        bank = RuDragonGame.getPreference().loadBank();
        if (bank < 500 && RuDragonGame.getPreference().loadBankYes500() == 0) {
            progressDown.add(0, new Label(new StringBuilder().append(500 - bank), textStyles.get("BigBlack")));
            meatDown.add(0, new Label("+250", textStyles.get("BigBlack")));
        }
        else if (bank < 1000 && RuDragonGame.getPreference().loadBankYes1000() == 0) {
            progressDown.add(0, new Label(new StringBuilder().append(1000 - bank), textStyles.get("BigBlack")));
            meatDown.add(0, new Label("+650", textStyles.get("BigBlack")));
        }
        else if (bank < 1500 && RuDragonGame.getPreference().loadBankYes1500() == 0) {
            progressDown.add(0, new Label(new StringBuilder().append(1500 - bank), textStyles.get("BigBlack")));
            meatDown.add(0, new Label("+810", textStyles.get("BigBlack")));
        }
        else if (bank < 3000 && RuDragonGame.getPreference().loadBankYes3000() == 0) {
            progressDown.add(0, new Label(new StringBuilder().append(3000 - bank), textStyles.get("BigBlack")));
            meatDown.add(0, new Label("+1250", textStyles.get("BigBlack")));
        }
        else if (bank < 5500 && RuDragonGame.getPreference().loadBankYes5500() == 0) {
            progressDown.add(0, new Label(new StringBuilder().append(5500 - bank), textStyles.get("BigBlack")));
            meatDown.add(0, new Label("+2085", textStyles.get("BigBlack")));
        }
        else if (bank < 8350 && RuDragonGame.getPreference().loadBankYes8350() == 0) {
            progressDown.add(0, new Label(new StringBuilder().append(8350 - bank), textStyles.get("BigBlack")));
            meatDown.add(0, new Label("+4255", textStyles.get("BigBlack")));
        }
        else {
            progressDown.add(0, new Label("Max", textStyles.get("BigBlack")));
            meatDown.add(0, new Label("-", textStyles.get("BigBlack")));
        }
        progressDown.get(0).setPosition(320 * RuDragonGame.getRatioMonitorW() - progressDown.get(0).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 - 188) * RuDragonGame.getRatioMonitorH());
        meatDown.get(0).setPosition(320 * RuDragonGame.getRatioMonitorW() - meatDown.get(0).getPrefWidth(), (RuDragonGame.HEIGHT / 2 - 248) * RuDragonGame.getRatioMonitorH());

        glutton = RuDragonGame.getPreference().loadGlutton();
        if (glutton < 100) {
            progressDown.add(1, new Label(new StringBuilder().append(100 - glutton), textStyles.get("BigBlack")));
            meatDown.add(1, new Label("+35", textStyles.get("BigBlack")));
        }
        else if (glutton >= 100 && glutton < 1000) {
            progressDown.add(1, new Label(new StringBuilder().append(1000 - glutton), textStyles.get("BigBlack")));
            meatDown.add(1, new Label("+385", textStyles.get("BigBlack")));
        }
        else if (glutton >= 1000 && glutton < 5000) {
            progressDown.add(1, new Label(new StringBuilder().append(5000 - glutton), textStyles.get("BigBlack")));
            meatDown.add(1, new Label("+1615", textStyles.get("BigBlack")));
        }
        else if (glutton >= 5000 && glutton < 9400) {
            progressDown.add(1, new Label(new StringBuilder().append(9400 - glutton), textStyles.get("BigBlack")));
            meatDown.add(1, new Label("+2010", textStyles.get("BigBlack")));
        }
        else if (glutton >= 9400 && glutton < 14000) {
            progressDown.add(1, new Label(new StringBuilder().append(14000 - glutton), textStyles.get("BigBlack")));
            meatDown.add(1, new Label("+2750", textStyles.get("BigBlack")));
        }
        else if (glutton >= 14000) {
            progressDown.add(1, new Label("Max", textStyles.get("BigBlack")));
            meatDown.add(1, new Label("-", textStyles.get("BigBlack")));
        }
        progressDown.get(1).setPosition(576 * RuDragonGame.getRatioMonitorW() - progressDown.get(1).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 - 188) * RuDragonGame.getRatioMonitorH());
        meatDown.get(1).setPosition(576 * RuDragonGame.getRatioMonitorW() - meatDown.get(1).getPrefWidth(), (RuDragonGame.HEIGHT / 2 - 248) * RuDragonGame.getRatioMonitorH());

        duckLove = RuDragonGame.getPreference().loadDuckLove();
        if (duckLove < 50) {
            progressDown.add(2, new Label(new StringBuilder().append(50 - duckLove), textStyles.get("BigBlack")));
            meatDown.add(2, new Label("+30", textStyles.get("BigBlack")));
        }
        else if (duckLove >= 50 && duckLove < 250) {
            progressDown.add(2, new Label(new StringBuilder().append(250 - duckLove), textStyles.get("BigBlack")));
            meatDown.add(2, new Label("+130", textStyles.get("BigBlack")));
        }
        else if (duckLove >= 250 && duckLove < 500) {
            progressDown.add(2, new Label(new StringBuilder().append(500 - duckLove), textStyles.get("BigBlack")));
            meatDown.add(2, new Label("+360", textStyles.get("BigBlack")));
        }
        else if (duckLove >= 500 && duckLove < 800) {
            progressDown.add(2, new Label(new StringBuilder().append(800 - duckLove), textStyles.get("BigBlack")));
            meatDown.add(2, new Label("+420", textStyles.get("BigBlack")));
        }
        else if (duckLove >= 800 && duckLove < 1250) {
            progressDown.add(2, new Label(new StringBuilder().append(1250 - duckLove), textStyles.get("BigBlack")));
            meatDown.add(2, new Label("+505", textStyles.get("BigBlack")));
        }
        else if (duckLove >= 1250 && duckLove < 1805) {
            progressDown.add(2, new Label(new StringBuilder().append(1805 - duckLove), textStyles.get("BigBlack")));
            meatDown.add(2, new Label("+575", textStyles.get("BigBlack")));
        }
        else if (duckLove >= 1805 && duckLove < 2500) {
            progressDown.add(2, new Label(new StringBuilder().append(2500 - duckLove), textStyles.get("BigBlack")));
            meatDown.add(2, new Label("+700", textStyles.get("BigBlack")));
        }
        else if (duckLove >= 2500) {
            progressDown.add(2, new Label("Max", textStyles.get("BigBlack")));
            meatDown.add(2, new Label("-", textStyles.get("BigBlack")));
        }
        progressDown.get(2).setPosition(832 * RuDragonGame.getRatioMonitorW() - progressDown.get(2).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 - 188) * RuDragonGame.getRatioMonitorH());
        meatDown.get(2).setPosition(832 * RuDragonGame.getRatioMonitorW() - meatDown.get(2).getPrefWidth(), (RuDragonGame.HEIGHT / 2 - 248) * RuDragonGame.getRatioMonitorH());

        goatLove = RuDragonGame.getPreference().loadGoatLove();
        if (goatLove < 35) {
            progressDown.add(3, new Label(new StringBuilder().append(35 - goatLove), textStyles.get("BigBlack")));
            meatDown.add(3, new Label("+45", textStyles.get("BigBlack")));
        }
        else if (goatLove >= 35 && goatLove < 175) {
            progressDown.add(3, new Label(new StringBuilder().append(175 - goatLove), textStyles.get("BigBlack")));
            meatDown.add(3, new Label("+145", textStyles.get("BigBlack")));
        }
        else if (goatLove >= 175 && goatLove < 300) {
            progressDown.add(3, new Label(new StringBuilder().append(300 - goatLove), textStyles.get("BigBlack")));
            meatDown.add(3, new Label("+405", textStyles.get("BigBlack")));
        }
        else if (goatLove >= 300 && goatLove < 550) {
            progressDown.add(3, new Label(new StringBuilder().append(550 - goatLove), textStyles.get("BigBlack")));
            meatDown.add(3, new Label("+485", textStyles.get("BigBlack")));
        }
        else if (goatLove >= 550 && goatLove < 780) {
            progressDown.add(3, new Label(new StringBuilder().append(780 - goatLove), textStyles.get("BigBlack")));
            meatDown.add(3, new Label("+625", textStyles.get("BigBlack")));
        }
        else if (goatLove >= 780 && goatLove < 1000) {
            progressDown.add(3, new Label(new StringBuilder().append(1000 - goatLove), textStyles.get("BigBlack")));
            meatDown.add(3, new Label("+757", textStyles.get("BigBlack")));
        }
        else if (goatLove >= 1000 && goatLove < 1500) {
            progressDown.add(3, new Label(new StringBuilder().append(1500 - goatLove), textStyles.get("BigBlack")));
            meatDown.add(3, new Label("+1000", textStyles.get("BigBlack")));
        }
        else if (goatLove >= 1500) {
            progressDown.add(3, new Label("Max", textStyles.get("BigBlack")));
            meatDown.add(3, new Label("-", textStyles.get("BigBlack")));
        }
        progressDown.get(3).setPosition(1088 * RuDragonGame.getRatioMonitorW() - progressDown.get(3).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 - 188) * RuDragonGame.getRatioMonitorH());
        meatDown.get(3).setPosition(1088 * RuDragonGame.getRatioMonitorW() - meatDown.get(3).getPrefWidth(), (RuDragonGame.HEIGHT / 2 - 248) * RuDragonGame.getRatioMonitorH());

        pigLove = RuDragonGame.getPreference().loadPigLove();
        if (pigLove < 20) {
            progressDown.add(4, new Label(new StringBuilder().append(20 - pigLove), textStyles.get("BigBlack")));
            meatDown.add(4, new Label("+60", textStyles.get("BigBlack")));
        }
        else if (pigLove >= 20 && pigLove < 100) {
            progressDown.add(4, new Label(new StringBuilder().append(100 - pigLove), textStyles.get("BigBlack")));
            meatDown.add(4, new Label("+190", textStyles.get("BigBlack")));
        }
        else if (pigLove >= 100 && pigLove < 200) {
            progressDown.add(4, new Label(new StringBuilder().append(200 - pigLove), textStyles.get("BigBlack")));
            meatDown.add(4, new Label("+470", textStyles.get("BigBlack")));
        }
        else if (pigLove >= 200 && pigLove < 340) {
            progressDown.add(4, new Label(new StringBuilder().append(340 - pigLove), textStyles.get("BigBlack")));
            meatDown.add(4, new Label("+640", textStyles.get("BigBlack")));
        }
        else if (pigLove >= 340 && pigLove < 518) {
            progressDown.add(4, new Label(new StringBuilder().append(518 - pigLove), textStyles.get("BigBlack")));
            meatDown.add(4, new Label("+805", textStyles.get("BigBlack")));
        }
        else if (pigLove >= 518 && pigLove < 666) {
            progressDown.add(4, new Label(new StringBuilder().append(666 - pigLove), textStyles.get("BigBlack")));
            meatDown.add(4, new Label("+950", textStyles.get("BigBlack")));
        }
        else if (pigLove >= 666 && pigLove < 858) {
            progressDown.add(4, new Label(new StringBuilder().append(858 - pigLove), textStyles.get("BigBlack")));
            meatDown.add(4, new Label("+1200", textStyles.get("BigBlack")));
        }
        else if (pigLove >= 858) {
            progressDown.add(4, new Label("Max", textStyles.get("BigBlack")));
            meatDown.add(4, new Label("-", textStyles.get("BigBlack")));
        }
        progressDown.get(4).setPosition(1344 * RuDragonGame.getRatioMonitorW() - progressDown.get(4).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 - 188) * RuDragonGame.getRatioMonitorH());
        meatDown.get(4).setPosition(1344 * RuDragonGame.getRatioMonitorW() - meatDown.get(4).getPrefWidth(), (RuDragonGame.HEIGHT / 2 - 248) * RuDragonGame.getRatioMonitorH());

        kineLove = RuDragonGame.getPreference().loadKineLove();
        if (kineLove < 10) {
            progressDown.add(5, new Label(new StringBuilder().append(10 - kineLove), textStyles.get("BigBlack")));
            meatDown.add(5, new Label("+75", textStyles.get("BigBlack")));
        }
        else if (kineLove >= 10 && kineLove < 50) {
            progressDown.add(5, new Label(new StringBuilder().append(50 - kineLove), textStyles.get("BigBlack")));
            meatDown.add(5, new Label("+215", textStyles.get("BigBlack")));
        }
        else if (kineLove >= 50 && kineLove < 100) {
            progressDown.add(5, new Label(new StringBuilder().append(100 - kineLove), textStyles.get("BigBlack")));
            meatDown.add(5, new Label("+505", textStyles.get("BigBlack")));
        }
        else if (kineLove >= 100 && kineLove < 230) {
            progressDown.add(5, new Label(new StringBuilder().append(230 - kineLove), textStyles.get("BigBlack")));
            meatDown.add(5, new Label("+830", textStyles.get("BigBlack")));
        }
        else if (kineLove >= 230 && kineLove < 450) {
            progressDown.add(5, new Label(new StringBuilder().append(450 - kineLove), textStyles.get("BigBlack")));
            meatDown.add(5, new Label("+1350", textStyles.get("BigBlack")));
        }
        else if (kineLove >= 450 && kineLove < 675) {
            progressDown.add(5, new Label(new StringBuilder().append(675 - kineLove), textStyles.get("BigBlack")));
            meatDown.add(5, new Label("+1800", textStyles.get("BigBlack")));
        }
        else if (kineLove >= 675) {
            progressDown.add(5, new Label("Max", textStyles.get("BigBlack")));
            meatDown.add(5, new Label("-", textStyles.get("BigBlack")));
        }
        progressDown.get(5).setPosition(1600 * RuDragonGame.getRatioMonitorW() - progressDown.get(5).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 - 188) * RuDragonGame.getRatioMonitorH());
        meatDown.get(5).setPosition(1600 * RuDragonGame.getRatioMonitorW() - meatDown.get(5).getPrefWidth(), (RuDragonGame.HEIGHT / 2 - 248) * RuDragonGame.getRatioMonitorH());
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        if (RuDragonGame.getRatioAdd() == 0) {
            if (Integer.parseInt(time.format(calendar.getTime())) > 8 && Integer.parseInt(time.format(calendar.getTime())) < 20)
                batch.draw(RuDragonGame.getTextures().textureRegions.get("fonCave"), 0, 0, 1920, 1080);
            else {
                batch.draw(RuDragonGame.getTextures().textureRegions.get("fonCave"), 0, 0, 1920, 1080);
                batch.draw(RuDragonGame.getTextures().textureRegions.get("fonCaveNight"), 1060, 68, 541, 541);
            }
        }
        else {
            if (Integer.parseInt(time.format(calendar.getTime())) > 8 && Integer.parseInt(time.format(calendar.getTime())) < 20)
                batch.draw(RuDragonGame.getTextures().textureRegions.get("fonCave"), -156, 0, 2133, 1200);
            else {
                batch.draw(RuDragonGame.getTextures().textureRegions.get("fonCave"), -156, 0, 2133, 1200);
                batch.draw(RuDragonGame.getTextures().textureRegions.get("fonCaveNight"), 1020, 74, 602, 602);
            }
        }
        for (int i = 0; i < 7; i++) batch.draw(hams.get(i), 192 + 256 * i, RuDragonGame.HEIGHT / 2 + 64);
        for (int i = 0; i < 6; i++) batch.draw(hams.get(i), 320 + 256 * i, RuDragonGame.HEIGHT / 2 - 256);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        try {
            RuDragonGame.getMusicSound().stopCave();
            stage.dispose();
            buttons.clear();

            progressUp.clear();
            progressDown.clear();
            meatUp.clear();
            meatDown.clear();

            text.clear();
            textStyles.clear();
            calendar.clear();
            RuDragonGame.getLanguage().text.clear();
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
            if (name.equals("lvlUp")) {
                buttons.get("lvlUp").setSize(120, 120);
                buttons.get("lvlUp").setPosition(132, RuDragonGame.HEIGHT / 2 + 196);
            } else if (name.equals("start")) {
                buttons.get("start").setSize(120, 120);
                buttons.get("start").setPosition(388, RuDragonGame.HEIGHT / 2 + 196);
            } else if (name.equals("fly")) {
                buttons.get("fly").setSize(120, 120);
                buttons.get("fly").setPosition(644, RuDragonGame.HEIGHT / 2 + 196);
            } else if (name.equals("run")) {
                buttons.get("run").setSize(120, 120);
                buttons.get("run").setPosition(900, RuDragonGame.HEIGHT / 2 + 196);
            } else if (name.equals("dragonUp")) {
                buttons.get("dragonUp").setSize(120, 120);
                buttons.get("dragonUp").setPosition(1156, RuDragonGame.HEIGHT / 2 + 196);
            } else if (name.equals("homeLove")) {
                buttons.get("homeLove").setSize(120, 120);
                buttons.get("homeLove").setPosition(1412, RuDragonGame.HEIGHT / 2 + 196);
            } else if (name.equals("iBest")) {
                buttons.get("iBest").setSize(120, 120);
                buttons.get("iBest").setPosition(1668, RuDragonGame.HEIGHT / 2 + 196);
            } else if (name.equals("banker")) {
                buttons.get("banker").setSize(120, 120);
                buttons.get("banker").setPosition(260, RuDragonGame.HEIGHT / 2 - 124);
            } else if (name.equals("eatLove")) {
                buttons.get("eatLove").setSize(120, 120);
                buttons.get("eatLove").setPosition(516, RuDragonGame.HEIGHT / 2 - 124);
            } else if (name.equals("duckLove")) {
                buttons.get("duckLove").setSize(120, 120);
                buttons.get("duckLove").setPosition(772, RuDragonGame.HEIGHT / 2 - 124);
            } else if (name.equals("goatLove")) {
                buttons.get("goatLove").setSize(120, 120);
                buttons.get("goatLove").setPosition(1028, RuDragonGame.HEIGHT / 2 - 124);
            } else if (name.equals("pigLove")) {
                buttons.get("pigLove").setSize(120, 120);
                buttons.get("pigLove").setPosition(1284, RuDragonGame.HEIGHT / 2 - 124);
            } else if (name.equals("kineLove")) {
                buttons.get("kineLove").setSize(120, 120);
                buttons.get("kineLove").setPosition(1540, RuDragonGame.HEIGHT / 2 - 124);
            }
            return true;
        }

        @Override
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
            if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);

            if (name.equals("Back")) game.set(new MenuScreen(game));
            else if (name.equals("lvlUp")) {
                buttons.get("lvlUp").setSize(128, 128);
                buttons.get("lvlUp").setPosition(128, RuDragonGame.HEIGHT / 2 + 192);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("lvlUp")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("lvlUpFull")));
                labelDescription();
            } else if (name.equals("start")) {
                buttons.get("start").setSize(128, 128);
                buttons.get("start").setPosition(384, RuDragonGame.HEIGHT / 2 + 192);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("start")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("startFull")));
                labelDescription();
            } else if (name.equals("fly")) {
                buttons.get("fly").setSize(128, 128);
                buttons.get("fly").setPosition(640, RuDragonGame.HEIGHT / 2 + 192);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("fly")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("flyFull")));
                labelDescription();
            } else if (name.equals("run")) {
                buttons.get("run").setSize(128, 128);
                buttons.get("run").setPosition(896, RuDragonGame.HEIGHT / 2 + 192);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("run")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("runFull")));
                labelDescription();
            } else if (name.equals("dragonUp")) {
                buttons.get("dragonUp").setSize(128, 128);
                buttons.get("dragonUp").setPosition(1152, RuDragonGame.HEIGHT / 2 + 192);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("dragonUp")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("dragonUpFull")));
                labelDescription();
            } else if (name.equals("homeLove")) {
                buttons.get("homeLove").setSize(128, 128);
                buttons.get("homeLove").setPosition(1408, RuDragonGame.HEIGHT / 2 + 192);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("homeLove")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("homeLoveFull")));
                labelDescription();
            } else if (name.equals("iBest")) {
                buttons.get("iBest").setSize(128, 128);
                buttons.get("iBest").setPosition(1664, RuDragonGame.HEIGHT / 2 + 192);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("iBest")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("iBestFull")));
                labelDescription();
            } else if (name.equals("banker")) {
                buttons.get("banker").setSize(128, 128);
                buttons.get("banker").setPosition(256, RuDragonGame.HEIGHT / 2 - 128);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("banker")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("bankerFull")));
                labelDescription();
            } else if (name.equals("eatLove")) {
                buttons.get("eatLove").setSize(128, 128);
                buttons.get("eatLove").setPosition(512, RuDragonGame.HEIGHT / 2 - 128);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("eatLove")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("eatLoveFull")));
                labelDescription();
            } else if (name.equals("duckLove")) {
                buttons.get("duckLove").setSize(128, 128);
                buttons.get("duckLove").setPosition(768, RuDragonGame.HEIGHT / 2 - 128);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("duckLove")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("duckLoveFull")));
                labelDescription();
            } else if (name.equals("goatLove")) {
                buttons.get("goatLove").setSize(128, 128);
                buttons.get("goatLove").setPosition(1024, RuDragonGame.HEIGHT / 2 - 128);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("goatLove")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("goatLoveFull")));
                labelDescription();
            } else if (name.equals("pigLove")) {
                buttons.get("pigLove").setSize(128, 128);
                buttons.get("pigLove").setPosition(1280, RuDragonGame.HEIGHT / 2 - 128);
                text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("pigLove")));
                text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("pigLoveFull")));
                labelDescription();
            } else if (name.equals("kineLove")) {
                buttons.get("kineLove").setSize(128, 128);
                buttons.get("kineLove").setPosition(1536, RuDragonGame.HEIGHT / 2 - 128);
                text.get("text").setText(RuDragonGame.getLanguage().text.get("kineLove"));
                text.get("textFull").setText(RuDragonGame.getLanguage().text.get("kineLoveFull"));
                labelDescription();
            }
        }
    }
}