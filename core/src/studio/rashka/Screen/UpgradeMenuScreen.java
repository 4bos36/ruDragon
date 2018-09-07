package studio.rashka.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.StringBuilder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;
import studio.rashka.Lib.btn.Buttons;
import studio.rashka.RuDragonGame;

public class UpgradeMenuScreen extends State {

    private static final int NOACTIVE = 0, DRAGONACTIVE = 1, HOMEACTIVE = 2;

    private static final int
            NO = 0, FLYUP = 1, FLYDOWN = 2, ROAR = 3, BONUSXP = 4, BONUSHAM = 5, TREES = 6, BIRDS = 7, LIVESTOCK = 9, FENDOFF = 10;
    private int buyUpgrade = NO, bonusScore = 0;
    private boolean showAchievements = false;
    private float timeHide = 0;

    private Map<String, LabelStyle> textStyles;
    private ArrayList<Label> textBasic, textInfo, textPrice;
    private Map<String, Buttons> imageButton;

    private Stage stage;
    private Calendar calendar;
    private SimpleDateFormat time;

    private ArrayList<TextureRegion> hams;
    private ParticleEffect bonus;

    private float scroll = 1;
    private boolean isScroll = false;
    private int active = NOACTIVE;
    private int hide = NOACTIVE;
    private int progress = 0;

    private int buyHam = 0;
    private boolean isBuy = true, isActiveBuy = false,
            isBuys[] = new boolean[]{false, false, false, false, false, false, false, false, false, false},
            isActiveBuys[] = new boolean[]{false, false, false, false, false, false, false, false, false, false};
    private int buyX = -500, buyY = -500;

    public UpgradeMenuScreen(final Game game) {
        super(game);
        RuDragonGame.getLanguage().textUpgradeMenu();

        stage = new Stage();
        calendar = Calendar.getInstance();
        time = new SimpleDateFormat("HH");

        bonus = new ParticleEffect();
        bonus.load(Gdx.files.internal("particles/Bonus.p"), Gdx.files.internal("particles"));
        bonus.start();

        textStyles = new HashMap<String, LabelStyle>();
        textStyles.put("Black", new LabelStyle(RuDragonGame.getFontTTF().getFont48(), Color.WHITE));
        textStyles.put("White", new LabelStyle(RuDragonGame.getFontTTF().getFont38(), Color.WHITE));
        textStyles.put("XP", new LabelStyle(RuDragonGame.getFontTTF().getFont25(), Color.GOLD));

        hams = new ArrayList<TextureRegion>();

        textBasic = new ArrayList<Label>();
        textInfo = new ArrayList<Label>();
        textPrice = new ArrayList<Label>();

        for (int i = 0; i <= 4; i++) textBasic.add(i, new Label("", textStyles.get("Black")));
        for (int i = 0; i <= 5; i++) textInfo.add(i, new Label("", textStyles.get("Black")));
        textInfo.add(6, new Label("", new LabelStyle(RuDragonGame.getFontTTF().getFont38(), Color.RED)));

        for (int i = 0; i <= 5; i++) textPrice.add(i, new Label("", textStyles.get("Black")));
        for (int i = 0; i < 6; i++) hams.add(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("ham")));

        buttons();

        textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("nullText"));
        textBasic.get(0).setPosition(1072 * RuDragonGame.getRatioMonitorW(), -96 * RuDragonGame.getRatioMonitorH());

        textLVL();

        textBasic.get(3).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBank()));
        textBasic.get(3).setPosition((RuDragonGame.WIDTH - 32) * RuDragonGame.getRatioMonitorW() - textBasic.get(3).getPrefWidth(), (RuDragonGame.HEIGHT - 64) * RuDragonGame.getRatioMonitorH() - textBasic.get(3).getPrefHeight());

        textBasic.add(5, new Label(new StringBuilder().append(RuDragonGame.getPreference().loadLevel()), textStyles.get("White")));
        textBasic.get(5).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textBasic.get(5).getWidth() / 2, (RuDragonGame.HEIGHT - 122) * RuDragonGame.getRatioMonitorH());

        for (int i = 0; i <= 5; i++) {
            stage.addActor(textBasic.get(i));
            stage.addActor(textInfo.get(i));
            stage.addActor(textPrice.get(i));
        }
        stage.addActor(textInfo.get(6));
        Gdx.input.setInputProcessor(stage);
        if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().startCave();
    }

    private void buttons() {
        imageButton = new HashMap<String, Buttons>();
        imageButton.put("back", new Buttons("Back", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Back"), 128, 64, 32, RuDragonGame.HEIGHT - 96));
        imageButton.put("dragonUp", new Buttons("DragonUp", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Up_dragon"), 256, 256, 512, RuDragonGame.HEIGHT / 2 - 128));
        imageButton.put("homeUp", new Buttons("HomeUp", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Home_up"), 256, 256, RuDragonGame.WIDTH - 768, RuDragonGame.HEIGHT / 2 - 128));
        imageButton.put("buy", new Buttons("Buy", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 128, 128, RuDragonGame.WIDTH + 10, RuDragonGame.HEIGHT + 10));
        imageButton.put("flyUp", new Buttons("FlyUp", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("FlyUp"), 128, 128, 256, -512));
        imageButton.put("flyDown", new Buttons("FlyDown", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("FlyDown"), 128, 128, 576, -512));
        imageButton.put("fendOff", new Buttons("FendOff", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("FendOff"), 128, 128, 576, -512));
        imageButton.put("roar", new Buttons("Roar", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Roar"), 128, 128, 896, -512));
        imageButton.put("bonusXP", new Buttons("BonusXP", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("BonusXP"), 128, 128, 1216, -512));
        imageButton.put("bonusHam", new Buttons("BonusHam", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("BonusHam"), 128, 128, 1536, -512));
        imageButton.put("buyTree", new Buttons("BuyTree", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("BuyTree"), 128, 128, RuDragonGame.WIDTH / 3 - 64, -512));
        imageButton.put("buyBirds", new Buttons("BuyBirds", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("BuyBirds"), 128, 128, RuDragonGame.WIDTH / 2 - 64, -512));
        imageButton.put("buyLiveStock", new Buttons("BuyLiveStock", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("BuyLiveStock"), 128, 128, RuDragonGame.WIDTH - RuDragonGame.WIDTH / 3 - 64, -512));

        imageButton.get("back").addListener(new ButtonsInputListener(imageButton.get("back").getName()));
        imageButton.get("dragonUp").addListener(new ButtonsInputListener(imageButton.get("dragonUp").getName()));
        imageButton.get("homeUp").addListener(new ButtonsInputListener(imageButton.get("homeUp").getName()));
        imageButton.get("buy").addListener(new ButtonsInputListener(imageButton.get("buy").getName()));
        imageButton.get("flyUp").addListener(new ButtonsInputListener(imageButton.get("flyUp").getName()));
        imageButton.get("flyDown").addListener(new ButtonsInputListener(imageButton.get("flyDown").getName()));
        imageButton.get("fendOff").addListener(new ButtonsInputListener(imageButton.get("fendOff").getName()));
        imageButton.get("roar").addListener(new ButtonsInputListener(imageButton.get("roar").getName()));
        imageButton.get("bonusXP").addListener(new ButtonsInputListener(imageButton.get("bonusXP").getName()));
        imageButton.get("bonusHam").addListener(new ButtonsInputListener(imageButton.get("bonusHam").getName()));
        imageButton.get("buyTree").addListener(new ButtonsInputListener(imageButton.get("buyTree").getName()));
        imageButton.get("buyBirds").addListener(new ButtonsInputListener(imageButton.get("buyBirds").getName()));
        imageButton.get("buyLiveStock").addListener(new ButtonsInputListener(imageButton.get("buyLiveStock").getName()));

        stage.addActor(imageButton.get("back"));
        stage.addActor(imageButton.get("dragonUp"));
        stage.addActor(imageButton.get("homeUp"));
        stage.addActor(imageButton.get("buy"));
        stage.addActor(imageButton.get("flyUp"));
        stage.addActor(imageButton.get("flyDown"));
        stage.addActor(imageButton.get("fendOff"));
        stage.addActor(imageButton.get("roar"));
        stage.addActor(imageButton.get("bonusXP"));
        stage.addActor(imageButton.get("bonusHam"));
        stage.addActor(imageButton.get("buyTree"));
        stage.addActor(imageButton.get("buyBirds"));
        stage.addActor(imageButton.get("buyLiveStock"));
    }

    private void infoANDprice() {
        if (active == DRAGONACTIVE) {
            if ((RuDragonGame.getPreference().loadLevel() == 1 && RuDragonGame.getPreference().loadFlyUp() < 5) ||
                    (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadFlyUp() < 10) ||
                    (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadFlyUp() < 15) ||
                    (RuDragonGame.getPreference().loadLevel() == 4 && RuDragonGame.getPreference().loadFlyUp() < 20) ||
                    (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadFlyUp() < 25) ||
                    (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadFlyUp() < 30) ||
                    (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadFlyUp() < 35) ||
                    (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadFlyUp() < 40) ||
                    (RuDragonGame.getPreference().loadLevel() == 9 && RuDragonGame.getPreference().loadFlyUp() < 45) ||
                    (RuDragonGame.getPreference().loadLevel() == 10 && RuDragonGame.getPreference().loadFlyUp() < 50)
                    ) {
                isActiveBuys[0] = true;
                isBuys[0] = true;
                textInfo.get(0).setText(new StringBuilder().append(RuDragonGame.getPreference().loadFlyUp()).append("% (+1%)"));
                textPrice.get(0).setText("150");
            }
            else if (RuDragonGame.getPreference().loadFlyUp() == 50) {
                isActiveBuys[0] = false;
                isBuys[0] = false;
                textInfo.get(0).setText(new StringBuilder().append(RuDragonGame.getPreference().loadFlyUp()).append("% (MAX)"));
                textPrice.get(0).setText("*_*");
            }
            else if (RuDragonGame.getPreference().loadLevel() == 0 ||
                    (RuDragonGame.getPreference().loadLevel() == 1 && RuDragonGame.getPreference().loadFlyUp() >= 5) ||
                    (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadFlyUp() >= 10) ||
                    (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadFlyUp() >= 15) ||
                    (RuDragonGame.getPreference().loadLevel() == 4 && RuDragonGame.getPreference().loadFlyUp() >= 20) ||
                    (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadFlyUp() >= 25) ||
                    (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadFlyUp() >= 30) ||
                    (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadFlyUp() >= 35) ||
                    (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadFlyUp() >= 40) ||
                    (RuDragonGame.getPreference().loadLevel() == 9 && RuDragonGame.getPreference().loadFlyUp() >= 45)
                    ) {
                isActiveBuys[0] = false;
                isBuys[0] = false;
                textInfo.get(0).setText(new StringBuilder().append(RuDragonGame.getPreference().loadFlyUp()).append("% (-)"));
                textPrice.get(0).setText("150");
            }
            if ((RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadFlyDown() < 5) ||
                    (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadFlyDown() < 10) ||
                    (RuDragonGame.getPreference().loadLevel() == 4 && RuDragonGame.getPreference().loadFlyDown() < 15) ||
                    (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadFlyDown() < 20) ||
                    (RuDragonGame.getPreference().loadLevel() >= 6 && RuDragonGame.getPreference().loadFlyDown() < 25)
                    ) {
                isActiveBuys[1] = true;
                isBuys[1] = true;
                textInfo.get(1).setText(new StringBuilder().append(RuDragonGame.getPreference().loadFlyDown()).append("% (+1%)"));
                textPrice.get(1).setText("100");
            }
            else if (RuDragonGame.getPreference().loadFlyDown() == 25) {
                isActiveBuys[1] = false;
                isBuys[1] = false;
                textInfo.get(1).setText(new StringBuilder().append(RuDragonGame.getPreference().loadFlyDown()).append("% (MAX)"));
                textPrice.get(1).setText("*_*");
            }
            else if (RuDragonGame.getPreference().loadLevel() == 0 || RuDragonGame.getPreference().loadLevel() == 1 ||
                    (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadFlyDown() >= 5) ||
                    (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadFlyDown() >= 10) ||
                    (RuDragonGame.getPreference().loadLevel() == 4 && RuDragonGame.getPreference().loadFlyDown() >= 15) ||
                    (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadFlyDown() >= 20)
                    ) {
                isActiveBuys[1] = false;
                isBuys[1] = false;
                textInfo.get(1).setText(new StringBuilder().append(RuDragonGame.getPreference().loadFlyDown()).append("% (-)"));
                textPrice.get(1).setText("100");
            }
            if (RuDragonGame.getPreference().loadLevel() >= 3 && !RuDragonGame.getPreference().loadFendOff()) {
                isActiveBuys[9] = true;
                isBuys[9] = true;
                textInfo.get(2).setText("(+1)");
                textPrice.get(2).setText("2500");
            }
            else if (RuDragonGame.getPreference().loadLevel() >= 3 && RuDragonGame.getPreference().loadFendOff()) {
                isActiveBuys[9] = false;
                isBuys[9] = false;
                textInfo.get(2).setText(RuDragonGame.getLanguage().textMenu.get("actively"));
                textPrice.get(2).setText("*_*");
            }
            else if (RuDragonGame.getPreference().loadLevel() < 3) {
                isActiveBuys[9] = false;
                isBuys[9] = false;
                textInfo.get(2).setText("(-)");
                textPrice.get(2).setText("2500");
            }
            if (RuDragonGame.getPreference().loadLevel() >= 3 && RuDragonGame.getPreference().loadRoar() < 10) {
                isActiveBuys[2] = true;
                isBuys[2] = true;
                textInfo.get(3).setText(new StringBuilder().append(RuDragonGame.getPreference().loadRoar()).append(" (+1)"));
                textPrice.get(3).setText("100");
            }
            else if (RuDragonGame.getPreference().loadRoar() == 10) {
                isActiveBuys[2] = false;
                isBuys[2] = false;
                textInfo.get(3).setText(new StringBuilder().append(RuDragonGame.getPreference().loadRoar()).append(" (MAX)"));
                textPrice.get(3).setText("*_*");
            }
            else if (RuDragonGame.getPreference().loadLevel() < 3) {
                isActiveBuys[2] = false;
                isBuys[2] = false;
                textInfo.get(3).setText(new StringBuilder().append(RuDragonGame.getPreference().loadRoar()).append(" (-)"));
                textPrice.get(3).setText("100");
            }
            if ((RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBonusXP() < 10) ||
                    (RuDragonGame.getPreference().loadLevel() >= 3 && RuDragonGame.getPreference().loadLevel() <= 6 && RuDragonGame.getPreference().loadBonusXP() < 25) ||
                    (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadBonusXP() < 30) ||
                    (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadBonusXP() < 35) ||
                    (RuDragonGame.getPreference().loadLevel() >= 9 && RuDragonGame.getPreference().loadBonusXP() < 40)
                    ) {
                isActiveBuys[3] = true;
                isBuys[3] = true;
                textInfo.get(4).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBonusXP()).append("% (+1%)"));
                textPrice.get(4).setText("125");
            }
            else if (RuDragonGame.getPreference().loadBonusXP() == 40) {
                isActiveBuys[3] = false;
                isBuys[3] = false;
                textInfo.get(4).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBonusXP()).append("% (MAX)"));
                textPrice.get(4).setText("*_*");
            }
            else if (RuDragonGame.getPreference().loadLevel() < 2 ||
                    (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBonusXP() >= 10) ||
                    (RuDragonGame.getPreference().loadLevel() >= 3 && RuDragonGame.getPreference().loadLevel() <= 6 && RuDragonGame.getPreference().loadBonusXP() >= 25) ||
                    (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadBonusXP() >= 30) ||
                    (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadBonusXP() >= 35)
                    ) {
                isActiveBuys[3] = false;
                isBuys[3] = false;
                textInfo.get(4).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBonusXP()).append(" (-)"));
                textPrice.get(4).setText("125");
            }
            if ((RuDragonGame.getPreference().loadLevel() >= 2 && RuDragonGame.getPreference().loadLevel() <= 5 && RuDragonGame.getPreference().loadBonusHam() < 15) ||
                    (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadBonusHam() < 20) ||
                    (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadBonusHam() < 25) ||
                    (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadBonusHam() < 30) ||
                    (RuDragonGame.getPreference().loadLevel() == 9 && RuDragonGame.getPreference().loadBonusHam() < 35) ||
                    (RuDragonGame.getPreference().loadLevel() == 10 && RuDragonGame.getPreference().loadBonusHam() < 40)
                    ) {
                isActiveBuys[4] = true;
                isBuys[4] = true;
                textInfo.get(5).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBonusHam()).append("% (+1%)"));
                textPrice.get(5).setText("165");
            }
            else if (RuDragonGame.getPreference().loadBonusHam() == 40) {
                isActiveBuys[4] = false;
                isBuys[4] = false;
                textInfo.get(5).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBonusHam()).append("% (MAX)"));
                textPrice.get(5).setText("*_*");
            }
            else if (RuDragonGame.getPreference().loadLevel() < 2 || (RuDragonGame.getPreference().loadLevel() >= 2 &&
                    RuDragonGame.getPreference().loadLevel() <= 5 && RuDragonGame.getPreference().loadBonusHam() >= 15) ||
                    (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadBonusHam() >= 20) ||
                    (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadBonusHam() >= 25) ||
                    (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadBonusHam() >= 30) ||
                    (RuDragonGame.getPreference().loadLevel() == 9 && RuDragonGame.getPreference().loadBonusHam() >= 35)
                    ) {
                isActiveBuys[4] = false;
                isBuys[4] = false;
                textInfo.get(5).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBonusHam()).append(" (-)"));
                textPrice.get(5).setText("165");
            }
            for (int i = 0; i < 6; i++) {
                textInfo.get(i).setPosition(
                        (320 + 256 * i) * RuDragonGame.getRatioMonitorW() - textInfo.get(i).getPrefWidth() / 2,
                        (RuDragonGame.HEIGHT / 2 - 50) * RuDragonGame.getRatioMonitorH());
                textPrice.get(i).setPosition(
                        (352 + 256 * i) * RuDragonGame.getRatioMonitorW() - textPrice.get(i).getPrefWidth() / 2,
                        (RuDragonGame.HEIGHT / 2 - 120) * RuDragonGame.getRatioMonitorH());
            }
        }
        else if (active == HOMEACTIVE) {
            if ((RuDragonGame.getPreference().loadLevel() == 1 && RuDragonGame.getPreference().loadBuyTree() < 1) ||
                    (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBuyTree() < 2) ||
                    (RuDragonGame.getPreference().loadLevel() >= 3 && RuDragonGame.getPreference().loadBuyTree() < 3)
                    ) {
                isActiveBuys[5] = true;
                isBuys[5] = true;
                textInfo.get(0).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBuyTree()).append(" (+1)"));
                textPrice.get(0).setText("1250");
            }
            else if (RuDragonGame.getPreference().loadBuyTree() == 3) {
                isActiveBuys[5] = false;
                isBuys[5] = false;
                textInfo.get(0).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBuyTree()).append(" (MAX)"));
                textPrice.get(0).setText("-_-");
            }
            else if (RuDragonGame.getPreference().loadLevel() == 0 ||
                    (RuDragonGame.getPreference().loadLevel() == 1 && RuDragonGame.getPreference().loadBuyTree() >= 1) ||
                    (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBuyTree() >= 2)
                    ) {
                isActiveBuys[5] = false;
                isBuys[5] = false;
                textInfo.get(0).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBuyTree()).append(" (-)"));
                textPrice.get(0).setText("1250");
            }
            if ((RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBuyBirds() < 1) ||
                    (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadBuyBirds() < 2) ||
                    (RuDragonGame.getPreference().loadLevel() >= 4 && RuDragonGame.getPreference().loadBuyBirds() < 3)
                    ) {
                isActiveBuys[6] = true;
                isBuys[6] = true;
                textInfo.get(1).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBuyBirds()).append(" (+1)"));
                textPrice.get(1).setText("750");
            }
            else if (RuDragonGame.getPreference().loadBuyBirds() == 3) {
                isActiveBuys[6] = false;
                isBuys[6] = false;
                textInfo.get(1).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBuyBirds()).append(" (MAX)"));
                textPrice.get(1).setText("-_-");
            }
            else if (RuDragonGame.getPreference().loadLevel() < 2 ||
                    (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBuyBirds() >= 1) ||
                    (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadBuyBirds() >= 2)
                    ) {
                isActiveBuys[6] = false;
                isBuys[6] = false;
                textInfo.get(1).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBuyBirds()).append(" (-)"));
                textPrice.get(1).setText("750");
            }
            if ((RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadBuyLiveStock() < 1) ||
                    (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadBuyLiveStock() < 2) ||
                    (RuDragonGame.getPreference().loadLevel() >= 7 && RuDragonGame.getPreference().loadBuyLiveStock() < 3)
                    ) {
                isActiveBuys[8] = true;
                isBuys[8] = true;
                textInfo.get(2).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBuyLiveStock()).append(" (+1)"));
                textPrice.get(2).setText("1500");
            }
            else if (RuDragonGame.getPreference().loadBuyLiveStock() == 3) {
                isActiveBuys[8] = false;
                isBuys[8] = false;
                textInfo.get(2).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBuyLiveStock()).append(" (MAX)"));
                textPrice.get(2).setText("-_-");
            }
            else if (RuDragonGame.getPreference().loadLevel() < 5 ||
                    (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadBuyLiveStock() >= 1) ||
                    (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadBuyLiveStock() >= 2)
                    ) {
                isActiveBuys[8] = false;
                isBuys[8] = false;
                textInfo.get(2).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBuyLiveStock()).append(" (-)"));
                textPrice.get(2).setText("1500");
            }
            for (int i = 0; i < 3; i++) {
                textInfo.get(i).setPosition(
                        (RuDragonGame.WIDTH / 3 + 320 * i) * RuDragonGame.getRatioMonitorW() - textInfo.get(i).getPrefWidth() / 2,
                        (RuDragonGame.HEIGHT / 2 - 50) * RuDragonGame.getRatioMonitorH());
                textPrice.get(i).setPosition(
                        ((RuDragonGame.WIDTH / 3 + 32) + 320 * i) * RuDragonGame.getRatioMonitorW() - textPrice.get(i).getPrefWidth() / 2,
                        (RuDragonGame.HEIGHT / 2 - 120) * RuDragonGame.getRatioMonitorH());
            }
        }
    }

    private void textLVL() {
        textBasic.get(2).setStyle(new LabelStyle(RuDragonGame.getFontTTF().getFont32(), Color.GOLD));
        String xp = new DecimalFormat("#0").format(RuDragonGame.getPreference().loadXP());
        if (RuDragonGame.getPreference().loadLevel() == 0) {
            textBasic.get(2).setText(new StringBuilder().append(xp).append(" / 2500 XP"));
            progress = Integer.parseInt(xp) * 448 / 2500;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 1) {
            textBasic.get(2).setText(new StringBuilder().append(xp).append(" / 5814 XP"));
            progress = (Integer.parseInt(xp) - 2500) * 448 / 3314;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 2) {
            textBasic.get(2).setText(new StringBuilder().append(xp).append(" / 11625 XP"));
            progress = (Integer.parseInt(xp) - 5814) * 448 / 5811;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 3) {
            textBasic.get(2).setText(new StringBuilder().append(xp).append(" / 18600 XP"));
            progress = (Integer.parseInt(xp) - 11625) * 448 / 6975;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 4) {
            textBasic.get(2).setText(new StringBuilder().append(xp).append(" / 29760 XP"));
            progress = (Integer.parseInt(xp) - 18600) * 448 / 11160;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 5) {
            textBasic.get(2).setText(new StringBuilder().append(xp).append(" / 47615 XP"));
            progress = (Integer.parseInt(xp) - 29760) * 448 / 17855;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 6) {
            textBasic.get(2).setText(new StringBuilder().append(xp).append(" / 76185 XP"));
            progress = (Integer.parseInt(xp) - 47615) * 448 / 28570;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 7) {
            textBasic.get(2).setText(new StringBuilder().append(xp).append(" / 121900 XP"));
            progress = (Integer.parseInt(xp) - 76185) * 448 / 45715;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 8) {
            textBasic.get(2).setText(new StringBuilder().append(xp).append(" / 195035 XP"));
            progress = (Integer.parseInt(xp) - 121900) * 448 / 73135;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 9) {
            textBasic.get(2).setText(new StringBuilder().append(xp).append(" / 312056 XP"));
            progress = (Integer.parseInt(xp) - 195035) * 448 / 117021;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 10) textBasic.get(2).setText(new StringBuilder().append("MAX"));
        textBasic.get(2).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textBasic.get(2).getPrefWidth() / 2, (RuDragonGame.HEIGHT - 32) * RuDragonGame.getRatioMonitorH() - textBasic.get(2).getPrefHeight());
    }

    private void labelDescription() {
        textBasic.get(0).setPosition(288 * RuDragonGame.getRatioMonitorW(), 130 * RuDragonGame.getRatioMonitorH());
        textBasic.get(1).setPosition(256 * RuDragonGame.getRatioMonitorW(), 110 * RuDragonGame.getRatioMonitorH() - textBasic.get(1).getPrefHeight());
        textBasic.get(1).setWidth((RuDragonGame.WIDTH - 256) * RuDragonGame.getRatioMonitorW());
        textBasic.get(1).setWrap(true);

        textBasic.get(3).setText(new StringBuilder().append(RuDragonGame.getPreference().loadBank()));
        textBasic.get(3).setPosition((RuDragonGame.WIDTH - 32) * RuDragonGame.getRatioMonitorW() - textBasic.get(3).getPrefWidth(), (RuDragonGame.HEIGHT - 64) * RuDragonGame.getRatioMonitorH() - textBasic.get(3).getPrefHeight());

        if (bonusScore > 0) textBasic.get(4).setText(new StringBuilder().append(bonusScore));
        else if (bonusScore == 0) textBasic.get(4).setText("");
        textBasic.get(4).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textBasic.get(4).getWidth() / 2, (RuDragonGame.HEIGHT - 210) * RuDragonGame.getRatioMonitorH());
        infoANDprice();
    }

    private void scrollDragon(float scroll) {
        if (active == DRAGONACTIVE) {
            imageButton.get("flyUp").setPosition(256, -288 + scroll);
            imageButton.get("flyDown").setPosition(512, -288 + scroll);
            imageButton.get("fendOff").setPosition(768, -288 + scroll);
            imageButton.get("roar").setPosition(1024, -288 + scroll);
            imageButton.get("bonusXP").setPosition(1280, -288 + scroll);
            imageButton.get("bonusHam").setPosition(1536, -288 + scroll);
        }
        else if (active == NOACTIVE && hide == DRAGONACTIVE) {
            imageButton.get("flyUp").setPosition(256, RuDragonGame.HEIGHT / 2 - scroll);
            imageButton.get("flyDown").setPosition(512, RuDragonGame.HEIGHT / 2 - scroll);
            imageButton.get("fendOff").setPosition(768, RuDragonGame.HEIGHT / 2 - scroll);
            imageButton.get("roar").setPosition(1024, RuDragonGame.HEIGHT / 2 - scroll);
            imageButton.get("bonusXP").setPosition(1280, RuDragonGame.HEIGHT / 2 - scroll);
            imageButton.get("bonusHam").setPosition(1536, RuDragonGame.HEIGHT / 2 - scroll);
        }
    }

    private void scrollHome(float scroll) {
        if (active == HOMEACTIVE) {
            imageButton.get("buyTree").setPosition(RuDragonGame.WIDTH / 3 - 64, -288 + scroll);
            imageButton.get("buyBirds").setPosition(RuDragonGame.WIDTH / 2 - 64, -288 + scroll);
            imageButton.get("buyLiveStock").setPosition(RuDragonGame.WIDTH - RuDragonGame.WIDTH / 3 - 64, -288 + scroll);
        }
        else if (active == NOACTIVE && hide == HOMEACTIVE) {
            imageButton.get("buyTree").setPosition(RuDragonGame.WIDTH / 3 - 64, RuDragonGame.HEIGHT / 2 - scroll);
            imageButton.get("buyBirds").setPosition(RuDragonGame.WIDTH / 2 - 64, RuDragonGame.HEIGHT / 2 - scroll);
            imageButton.get("buyLiveStock").setPosition(RuDragonGame.WIDTH - RuDragonGame.WIDTH / 3 - 64, RuDragonGame.HEIGHT / 2 - scroll);
        }
    }

    private void scroll(float scroll) {
        if (scroll > RuDragonGame.HEIGHT / 2 + 288) {
            hide = NOACTIVE;
            isScroll = false;
        }
    }

    @Override
    public void update(float deltaTime) {
        if (isScroll) {
            if (active == DRAGONACTIVE) {
                scroll = scroll + deltaTime * 800;
                imageButton.get("dragonUp").setPosition(512 - scroll, RuDragonGame.HEIGHT / 2 - 128);
                imageButton.get("homeUp").setPosition(RuDragonGame.WIDTH - 768 + scroll, RuDragonGame.HEIGHT / 2 - 128);
                scrollDragon(scroll);
                scroll(scroll);
            } else if (active == HOMEACTIVE) {
                scroll = scroll + deltaTime * 800;
                if (scroll <= 512 + 260) {
                    imageButton.get("dragonUp").setPosition(512 - scroll, RuDragonGame.HEIGHT / 2 - 128);
                    imageButton.get("homeUp").setPosition(RuDragonGame.WIDTH - 768 + scroll, RuDragonGame.HEIGHT / 2 - 128);
                }
                scrollHome(scroll);
                scroll(scroll);
            } else if (active == NOACTIVE) {
                scroll = scroll + deltaTime * 800;
                if (scroll <= 512 + 260) {
                    imageButton.get("dragonUp").setPosition(-260 + scroll, RuDragonGame.HEIGHT / 2 - 128);
                    imageButton.get("homeUp").setPosition(RuDragonGame.WIDTH - 4 - scroll, RuDragonGame.HEIGHT / 2 - 128);
                }
                scrollDragon(scroll);
                scrollHome(scroll);
                scroll(scroll);
            }
        }
        if (showAchievements) {
            bonus.update(deltaTime);
            timeHide += deltaTime;
            if (timeHide > 4) {
                showAchievements = false;
                timeHide = 0;
                bonusScore = 0;
                labelDescription();
            }
        }
        if (bonus.isComplete() && !showAchievements) bonus.reset();
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
        batch.draw(RuDragonGame.getTextures().textureRegions.get("ham"), RuDragonGame.WIDTH - 100, RuDragonGame.HEIGHT - 90);
        if (active == DRAGONACTIVE && !isScroll) {
            for (int i = 0; i < 6; i++) batch.draw(hams.get(i), 240 + 256 * i, RuDragonGame.HEIGHT / 2 - 160);
            if (buyHam <= RuDragonGame.getPreference().loadBank() && isBuy) batch.draw(RuDragonGame.getTextures().textureRegions.get("buy"), buyX + 16, buyY + 8, 96, 96);
            else if (buyHam > RuDragonGame.getPreference().loadBank() && isBuy) batch.draw(RuDragonGame.getTextures().textureRegions.get("buy_not"), buyX + 16, buyY + 8, 96, 96);
        }
        else if (active == HOMEACTIVE && !isScroll) {
            batch.draw(hams.get(0), RuDragonGame.WIDTH / 3 - 96, RuDragonGame.HEIGHT / 2 - 160);
            batch.draw(hams.get(1), RuDragonGame.WIDTH / 2 - 96, RuDragonGame.HEIGHT / 2 - 160);
            batch.draw(hams.get(2), RuDragonGame.WIDTH - RuDragonGame.WIDTH / 3 - 96, RuDragonGame.HEIGHT / 2 - 160);
            if (buyHam <= RuDragonGame.getPreference().loadBank() && isBuy) batch.draw(RuDragonGame.getTextures().textureRegions.get("buy"), buyX + 16, buyY + 8, 96, 96);
            else if (buyHam > RuDragonGame.getPreference().loadBank() && isBuy) batch.draw(RuDragonGame.getTextures().textureRegions.get("buy_not"), buyX + 16, buyY + 8, 96, 96);
        }

        batch.draw(RuDragonGame.getTextures().textureRegions.get("black"), RuDragonGame.WIDTH / 2 - 224, RuDragonGame.HEIGHT - 79, 448, 30);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("red"), RuDragonGame.WIDTH / 2 - 224, RuDragonGame.HEIGHT - 79, progress, 30);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("xp_lv"), RuDragonGame.WIDTH / 2 - 64, RuDragonGame.HEIGHT - 138);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("xp_progress_fon"), RuDragonGame.WIDTH / 2 - 86, RuDragonGame.HEIGHT - 96);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("xp_progress_fon"), RuDragonGame.WIDTH / 2 - 22, RuDragonGame.HEIGHT - 96, 108, 64);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("xp_progress"), RuDragonGame.WIDTH / 2 - 278, RuDragonGame.HEIGHT - 96);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("xp_progress"), RuDragonGame.WIDTH / 2 + 278, RuDragonGame.HEIGHT - 96, -192, 64); //448

        if (showAchievements) {
            bonus.setPosition(RuDragonGame.WIDTH / 2, RuDragonGame.HEIGHT - 160);
            bonus.draw(batch);
            batch.draw(RuDragonGame.getTextures().textureRegions.get("fon_lite"), RuDragonGame.WIDTH / 2 - 160, RuDragonGame.HEIGHT - 228, 320, 76);
            batch.draw(RuDragonGame.getTextures().textureRegions.get("ham"), RuDragonGame.WIDTH / 2 - 156, RuDragonGame.HEIGHT - 214, 48, 48);
            batch.draw(RuDragonGame.getTextures().textureRegions.get("ham"), RuDragonGame.WIDTH / 2 + 108, RuDragonGame.HEIGHT - 214, 48, 48);
        }
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        try {
            RuDragonGame.getMusicSound().stopCave();
            stage.dispose();

            hams.clear();
            bonus.dispose();

            textBasic.clear();
            textInfo.clear();
            imageButton.clear();
            RuDragonGame.getLanguage().text.clear();
            textStyles.clear();
            calendar.clear();
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
            if (name.equals("FlyUp")) {
                if (!isScroll) {
                    imageButton.get("flyUp").setSize(120, 120);
                    imageButton.get("flyUp").setPosition(260, -284 + scroll);
                }
            } else if (name.equals("FlyDown")) {
                if (!isScroll) {
                    imageButton.get("flyDown").setSize(120, 120);
                    imageButton.get("flyDown").setPosition(516, -284 + scroll);
                }
            } else if (name.equals("FendOff")) {
                if (!isScroll) {
                    imageButton.get("fendOff").setSize(120, 120);
                    imageButton.get("fendOff").setPosition(772, -284 + scroll);
                }
            } else if (name.equals("Roar")) {
                if (!isScroll) {
                    imageButton.get("roar").setSize(120, 120);
                    imageButton.get("roar").setPosition(1028, -284 + scroll);
                }
            } else if (name.equals("BonusXP")) {
                if (!isScroll) {
                    imageButton.get("bonusXP").setSize(120, 120);
                    imageButton.get("bonusXP").setPosition(1284, -284 + scroll);
                }
            } else if (name.equals("BonusHam")) {
                if (!isScroll) {
                    imageButton.get("bonusHam").setSize(120, 120);
                    imageButton.get("bonusHam").setPosition(1540, -284 + scroll);
                }
            } else if (name.equals("BuyTree")) {
                if (!isScroll) {
                    imageButton.get("buyTree").setSize(120, 120);
                    imageButton.get("buyTree").setPosition(RuDragonGame.WIDTH / 3 - 60, -284 + scroll);
                }
            } else if (name.equals("BuyBirds")) {
                if (!isScroll) {
                    imageButton.get("buyBirds").setSize(120, 120);
                    imageButton.get("buyBirds").setPosition(RuDragonGame.WIDTH / 2 - 60, -284 + scroll);
                }
            } else if (name.equals("BuyLiveStock")) {
                if (!isScroll) {
                    imageButton.get("buyLiveStock").setSize(120, 120);
                    imageButton.get("buyLiveStock").setPosition(RuDragonGame.WIDTH - RuDragonGame.WIDTH / 3 - 60, -284 + scroll);
                }
            }
            return true;
        }

        @Override
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            if (name.equals("Back")) {
                //region
                if (!isScroll) {
                    for (int i = 0; i < 6; i++) {
                        textInfo.get(i).setText("");
                        textPrice.get(i).setText("");
                    }
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    if (active == NOACTIVE) game.set(new UpgradeScreen(game));
                    else if (active == DRAGONACTIVE) {
                        textInfo.get(6).setText("");
                        buyUpgrade = NO;
                        isScroll = true;
                        active = NOACTIVE;
                        hide = DRAGONACTIVE;
                        scroll = 1;
                        buyX = -500; buyY = -500;
                        imageButton.get("buy").setPosition(RuDragonGame.WIDTH + 10, RuDragonGame.HEIGHT + 10);
                    }
                    else if (active == HOMEACTIVE) {
                        textInfo.get(6).setText("");
                        buyUpgrade = NO;
                        isScroll = true;
                        active = NOACTIVE;
                        hide = HOMEACTIVE;
                        scroll = 1;
                        buyX = -500; buyY = -500;
                        imageButton.get("buy").setPosition(RuDragonGame.WIDTH + 10, RuDragonGame.HEIGHT + 10);
                    }
                }
                //endregion
            } else if (name.equals("DragonUp")) {
                //region
                if (!isScroll) {
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    isScroll = true;
                    active = DRAGONACTIVE;
                    scroll = 1;
                    textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("nullText"));
                    textBasic.get(1).setText("");
                    labelDescription();
                }
                //endregion
            } else if (name.equals("HomeUp")) {
                //region
                if (!isScroll) {
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    isScroll = true;
                    active = HOMEACTIVE;
                    scroll = 1;
                    textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("nullText"));
                    textBasic.get(1).setText("");
                    labelDescription();
                }
                //endregion
            } else if (name.equals("Buy")) {
                //region
                if (buyHam <= RuDragonGame.getPreference().loadBank() && isBuy) {
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    if (buyUpgrade == FLYUP && isActiveBuy) {
                        if ((RuDragonGame.getPreference().loadLevel() == 1 && RuDragonGame.getPreference().loadFlyUp() < 5) ||
                                (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadFlyUp() < 10) ||
                                (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadFlyUp() < 15) ||
                                (RuDragonGame.getPreference().loadLevel() == 4 && RuDragonGame.getPreference().loadFlyUp() < 20) ||
                                (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadFlyUp() < 25) ||
                                (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadFlyUp() < 30) ||
                                (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadFlyUp() < 35) ||
                                (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadFlyUp() < 40) ||
                                (RuDragonGame.getPreference().loadLevel() == 9 && RuDragonGame.getPreference().loadFlyUp() < 45) ||
                                (RuDragonGame.getPreference().loadLevel() == 10 && RuDragonGame.getPreference().loadFlyUp() < 50)
                                ) {
                            RuDragonGame.getPreference().saveBank(0, buyHam);
                            RuDragonGame.getPreference().saveFlyUp(1);
                            RuDragonGame.getPreference().saveUpgrade(1);
                        }
                        if (RuDragonGame.getPreference().loadFlyUp() == 50 || RuDragonGame.getPreference().loadLevel() == 0 ||
                                (RuDragonGame.getPreference().loadLevel() == 1 && RuDragonGame.getPreference().loadFlyUp() >= 5) ||
                                (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadFlyUp() >= 10) ||
                                (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadFlyUp() >= 15) ||
                                (RuDragonGame.getPreference().loadLevel() == 4 && RuDragonGame.getPreference().loadFlyUp() >= 20) ||
                                (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadFlyUp() >= 25) ||
                                (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadFlyUp() >= 30) ||
                                (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadFlyUp() >= 35) ||
                                (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadFlyUp() >= 40) ||
                                (RuDragonGame.getPreference().loadLevel() == 9 && RuDragonGame.getPreference().loadFlyUp() >= 45)
                                ) {
                            imageButton.get("buy").setPosition(256, RuDragonGame.HEIGHT + 100);
                            isBuy = false;
                        }
                    }
                    else if (buyUpgrade == FLYDOWN && isActiveBuy) {
                        if ((RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadFlyDown() < 5) ||
                                (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadFlyDown() < 10) ||
                                (RuDragonGame.getPreference().loadLevel() == 4 && RuDragonGame.getPreference().loadFlyDown() < 15) ||
                                (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadFlyDown() < 20) ||
                                (RuDragonGame.getPreference().loadLevel() >= 6 && RuDragonGame.getPreference().loadFlyDown() < 25)
                                ) {
                            RuDragonGame.getPreference().saveBank(0, buyHam);
                            RuDragonGame.getPreference().saveFlyDown(1);
                            RuDragonGame.getPreference().saveUpgrade(1);
                        }
                        if (RuDragonGame.getPreference().loadFlyDown() == 25 || RuDragonGame.getPreference().loadLevel() == 0 || RuDragonGame.getPreference().loadLevel() == 1 ||
                                (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadFlyDown() >= 5) ||
                                (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadFlyDown() >= 10) ||
                                (RuDragonGame.getPreference().loadLevel() == 4 && RuDragonGame.getPreference().loadFlyDown() >= 15) ||
                                (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadFlyDown() >= 20)
                                ) {
                            imageButton.get("buy").setPosition(256, RuDragonGame.HEIGHT + 100);
                            isBuy = false;
                        }
                    }
                    else if (buyUpgrade == FENDOFF && isActiveBuy) {
                        if (RuDragonGame.getPreference().loadLevel() >= 3) {
                            RuDragonGame.getPreference().saveBank(0, buyHam);
                            RuDragonGame.getPreference().saveFendOff(true);
                            RuDragonGame.getPreference().saveUpgrade(1);
                        }
                        if (RuDragonGame.getPreference().loadFendOff() || RuDragonGame.getPreference().loadLevel() < 3) {
                            imageButton.get("buy").setPosition(256, RuDragonGame.HEIGHT + 100);
                            isBuy = false;
                        }
                    }
                    else if (buyUpgrade == ROAR && isActiveBuy) {
                        if (RuDragonGame.getPreference().loadLevel() >= 3 && RuDragonGame.getPreference().loadRoar() < 10) {
                            RuDragonGame.getPreference().saveBank(0, buyHam);
                            RuDragonGame.getPreference().saveRoar(1, 0);
                            RuDragonGame.getPreference().saveUpgrade(1);
                        }
                        if (RuDragonGame.getPreference().loadRoar() == 10 || RuDragonGame.getPreference().loadLevel() < 3) {
                            imageButton.get("buy").setPosition(256, RuDragonGame.HEIGHT + 100);
                            isBuy = false;
                        }
                    }
                    else if (buyUpgrade == BONUSXP && isActiveBuy) {
                        if ((RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBonusXP() < 10) ||
                                (RuDragonGame.getPreference().loadLevel() >= 3 && RuDragonGame.getPreference().loadLevel() <= 6 && RuDragonGame.getPreference().loadBonusXP() < 25) ||
                                (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadBonusXP() < 30) ||
                                (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadBonusXP() < 35) ||
                                (RuDragonGame.getPreference().loadLevel() >= 9 && RuDragonGame.getPreference().loadBonusXP() < 40)
                                ) {
                            RuDragonGame.getPreference().saveBank(0, buyHam);
                            RuDragonGame.getPreference().saveBonusXP(1);
                            RuDragonGame.getPreference().saveUpgrade(1);
                        }
                        if (RuDragonGame.getPreference().loadBonusXP() == 40 || RuDragonGame.getPreference().loadLevel() < 2 ||
                                (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBonusXP() >= 10) ||
                                (RuDragonGame.getPreference().loadLevel() >= 3 && RuDragonGame.getPreference().loadLevel() <= 6 && RuDragonGame.getPreference().loadBonusXP() >= 25) ||
                                (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadBonusXP() >= 30) ||
                                (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadBonusXP() >= 35)
                                ) {
                            imageButton.get("buy").setPosition(256, RuDragonGame.HEIGHT + 100);
                            isBuy = false;
                        }
                    }
                    else if (buyUpgrade == BONUSHAM && isActiveBuy) {
                        if ((RuDragonGame.getPreference().loadLevel() >= 2 && RuDragonGame.getPreference().loadLevel() <= 5 && RuDragonGame.getPreference().loadBonusHam() < 15) ||
                                (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadBonusHam() < 20) ||
                                (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadBonusHam() < 25) ||
                                (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadBonusHam() < 30) ||
                                (RuDragonGame.getPreference().loadLevel() == 9 && RuDragonGame.getPreference().loadBonusHam() < 35) ||
                                (RuDragonGame.getPreference().loadLevel() == 10 && RuDragonGame.getPreference().loadBonusHam() < 40)
                                ) {
                            RuDragonGame.getPreference().saveBank(0, buyHam);
                            RuDragonGame.getPreference().saveBonusHam(1);
                            RuDragonGame.getPreference().saveUpgrade(1);
                        }
                        if (RuDragonGame.getPreference().loadBonusHam() == 40 || RuDragonGame.getPreference().loadLevel() < 2 ||
                                (RuDragonGame.getPreference().loadLevel() >= 2 && RuDragonGame.getPreference().loadLevel() <= 5 && RuDragonGame.getPreference().loadBonusHam() >= 15) ||
                                (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadBonusHam() >= 20) ||
                                (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadBonusHam() >= 25) ||
                                (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadBonusHam() >= 30) ||
                                (RuDragonGame.getPreference().loadLevel() == 9 && RuDragonGame.getPreference().loadBonusHam() >= 35)
                                ) {
                            imageButton.get("buy").setPosition(25, RuDragonGame.HEIGHT + 100);
                            isBuy = false;
                        }
                    }
                    else if (buyUpgrade == TREES && isActiveBuy) {
                        if ((RuDragonGame.getPreference().loadLevel() == 1 && RuDragonGame.getPreference().loadBuyTree() < 1) ||
                                (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBuyTree() < 2) ||
                                (RuDragonGame.getPreference().loadLevel() >= 3 && RuDragonGame.getPreference().loadBuyTree() < 3)
                                ) {
                            RuDragonGame.getPreference().saveBank(0, buyHam);
                            RuDragonGame.getPreference().saveBuyTree(1);
                            RuDragonGame.getPreference().saveHome(1);
                        }
                        if (RuDragonGame.getPreference().loadBuyTree() == 3 || RuDragonGame.getPreference().loadLevel() == 0 ||
                                (RuDragonGame.getPreference().loadLevel() == 1 && RuDragonGame.getPreference().loadBuyTree() >= 1) ||
                                (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBuyTree() >= 2)
                                ) {
                            imageButton.get("buy").setPosition(256, RuDragonGame.HEIGHT + 100);
                            isBuy = false;
                        }
                    }
                    else if (buyUpgrade == BIRDS && isActiveBuy) {
                        if ((RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBuyBirds() < 1) ||
                                (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadBuyBirds() < 2) ||
                                (RuDragonGame.getPreference().loadLevel() >= 4 && RuDragonGame.getPreference().loadBuyBirds() < 3)
                                ) {
                            RuDragonGame.getPreference().saveBank(0, buyHam);
                            RuDragonGame.getPreference().saveBuyBirds(1);
                            RuDragonGame.getPreference().saveHome(1);
                        }
                        if (RuDragonGame.getPreference().loadBuyBirds() == 3 || RuDragonGame.getPreference().loadLevel() < 2 ||
                                (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBuyBirds() >= 1) ||
                                (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadBuyBirds() >= 2)
                                ) {
                            imageButton.get("buy").setPosition(256, RuDragonGame.HEIGHT + 100);
                            isBuy = false;
                        }
                    }
                    else if (buyUpgrade == LIVESTOCK && isActiveBuy) {
                        if ((RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadBuyLiveStock() < 1) ||
                                (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadBuyLiveStock() < 2) ||
                                (RuDragonGame.getPreference().loadLevel() >= 7 && RuDragonGame.getPreference().loadBuyLiveStock() < 3)
                                ) {
                            RuDragonGame.getPreference().saveBank(0, buyHam);
                            RuDragonGame.getPreference().saveBuyLiveStock(1);
                            RuDragonGame.getPreference().saveHome(1);
                        }
                        if (RuDragonGame.getPreference().loadBuyLiveStock() == 3 || RuDragonGame.getPreference().loadLevel() < 5 ||
                                (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadBuyLiveStock() >= 1) ||
                                (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadBuyLiveStock() >= 2)
                                ) {
                            imageButton.get("buy").setPosition(256, RuDragonGame.HEIGHT + 100);
                            isBuy = false;
                        }
                    }
                    if (active == DRAGONACTIVE) {
                        if (RuDragonGame.getPreference().loadUpgrade() == 1) {
                            showAchievements = true;
                            bonusScore += 40;
                            RuDragonGame.getPreference().saveBank(40, 0);
                        } else if (RuDragonGame.getPreference().loadUpgrade() == 5) {
                            showAchievements = true;
                            bonusScore += 225;
                            RuDragonGame.getPreference().saveBank(225, 0);
                        } else if (RuDragonGame.getPreference().loadUpgrade() == 15) {
                            showAchievements = true;
                            bonusScore += 475;
                            RuDragonGame.getPreference().saveBank(475, 0);
                        }
                        else if (RuDragonGame.getPreference().loadUpgrade() == 30) {
                            showAchievements = true;
                            bonusScore += 600;
                            RuDragonGame.getPreference().saveBank(600, 0);
                        }
                        else if (RuDragonGame.getPreference().loadUpgrade() == 45) {
                            showAchievements = true;
                            bonusScore += 825;
                            RuDragonGame.getPreference().saveBank(825, 0);
                        }
                        else if (RuDragonGame.getPreference().loadUpgrade() == 80) {
                            showAchievements = true;
                            bonusScore += 1200;
                            RuDragonGame.getPreference().saveBank(1200, 0);
                        }
                        else if (RuDragonGame.getPreference().loadUpgrade() == 130) {
                            showAchievements = true;
                            bonusScore += 1850;
                            RuDragonGame.getPreference().saveBank(1850, 0);
                        }
                        else if (RuDragonGame.getPreference().loadUpgrade() == 180) {
                            showAchievements = true;
                            bonusScore += 2200;
                            RuDragonGame.getPreference().saveBank(2200, 0);
                        }
                        else if (RuDragonGame.getPreference().loadUpgrade() == 210) {
                            showAchievements = true;
                            bonusScore += 2750;
                            RuDragonGame.getPreference().saveBank(2750, 0);
                        }
                    }
                    else if (active == HOMEACTIVE) {
                        if (RuDragonGame.getPreference().loadHome() == 1) {
                            showAchievements = true;
                            bonusScore += 100;
                            RuDragonGame.getPreference().saveBank(100, 0);
                        } else if (RuDragonGame.getPreference().loadHome() == 3) {
                            showAchievements = true;
                            bonusScore += 320;
                            RuDragonGame.getPreference().saveBank(320, 0);
                        } else if (RuDragonGame.getPreference().loadHome() == 5) {
                            showAchievements = true;
                            bonusScore += 530;
                            RuDragonGame.getPreference().saveBank(530, 0);
                        } else if (RuDragonGame.getPreference().loadHome() == 7) {
                            showAchievements = true;
                            bonusScore += 620;
                            RuDragonGame.getPreference().saveBank(620, 0);
                        } else if (RuDragonGame.getPreference().loadHome() == 9) {
                            showAchievements = true;
                            bonusScore += 750;
                            RuDragonGame.getPreference().saveBank(750, 0);
                        }
                    }
                    labelDescription();
                }
                //endregion
            } else if (name.equals("FlyUp")) {
                //region
                if (!isScroll) {
                    imageButton.get("flyUp").setSize(128, 128);
                    imageButton.get("flyUp").setPosition(256, -288 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("flyUp"));
                    textBasic.get(1).setText(RuDragonGame.getLanguage().text.get("flyUpFull"));
                    labelDescription();
                    if (RuDragonGame.getPreference().loadLevel() == 0) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(1));
                    else if (RuDragonGame.getPreference().loadLevel() == 1 && RuDragonGame.getPreference().loadFlyUp() >= 5) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(2));
                    else if (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadFlyUp() >= 10) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(3));
                    else if (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadFlyUp() >= 15) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(4));
                    else if (RuDragonGame.getPreference().loadLevel() == 4 && RuDragonGame.getPreference().loadFlyUp() >= 20) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(5));
                    else if (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadFlyUp() >= 25) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(6));
                    else if (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadFlyUp() >= 30) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(7));
                    else if (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadFlyUp() >= 35) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(8));
                    else if (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadFlyUp() >= 40) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(9));
                    else if (RuDragonGame.getPreference().loadLevel() == 9 && RuDragonGame.getPreference().loadFlyUp() >= 45) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(10));
                    else textInfo.get(6).setText("");
                    textInfo.get(6).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textInfo.get(6).getPrefWidth() / 2, (RuDragonGame.HEIGHT - 256) * RuDragonGame.getRatioMonitorH());
                    if (RuDragonGame.getPreference().loadFlyUp() < 50) {
                        isActiveBuy = isActiveBuys[0];
                        isBuy = isBuys[0];
                        buyUpgrade = FLYUP;
                        buyHam = 150;
                        buyX = 256;
                        buyY = RuDragonGame.HEIGHT / 2 - 240;
                        imageButton.get("buy").setPosition(256, RuDragonGame.HEIGHT / 2 - 260);
                    }
                }
                //endregion
            } else if (name.equals("FlyDown")) {
                //region
                if (!isScroll) {
                    imageButton.get("flyDown").setSize(128, 128);
                    imageButton.get("flyDown").setPosition(512, -288 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("flyDown"));
                    textBasic.get(1).setText(RuDragonGame.getLanguage().text.get("flyDownFull"));
                    labelDescription();
                    if (RuDragonGame.getPreference().loadLevel() == 0 || RuDragonGame.getPreference().loadLevel() == 1) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(2));
                    else if (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadFlyDown() >= 5) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(3));
                    else if (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadFlyDown() >= 10) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(4));
                    else if (RuDragonGame.getPreference().loadLevel() == 4 && RuDragonGame.getPreference().loadFlyDown() >= 15) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(5));
                    else if (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadFlyDown() >= 20) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(6));
                    else textInfo.get(6).setText("");
                    textInfo.get(6).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textInfo.get(6).getPrefWidth() / 2, (RuDragonGame.HEIGHT - 256) * RuDragonGame.getRatioMonitorH());
                    if (RuDragonGame.getPreference().loadFlyDown() < 30) {
                        isActiveBuy = isActiveBuys[1];
                        isBuy = isBuys[1];
                        buyUpgrade = FLYDOWN;
                        buyHam = 100;
                        buyX = 512;
                        buyY = RuDragonGame.HEIGHT / 2 - 240;
                        imageButton.get("buy").setPosition(512, RuDragonGame.HEIGHT / 2 - 260);
                    }
                }
                //endregion
            } else if (name.equals("FendOff")) {
                //region
                if (!isScroll) {
                    imageButton.get("fendOff").setSize(128, 128);
                    imageButton.get("fendOff").setPosition(768, -288 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("fendOff"));
                    textBasic.get(1).setText(RuDragonGame.getLanguage().text.get("fendOffFull"));
                    labelDescription();
                    if (RuDragonGame.getPreference().loadLevel() < 3) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(3));
                    else textInfo.get(6).setText("");
                    textInfo.get(6).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textInfo.get(6).getPrefWidth() / 2, (RuDragonGame.HEIGHT - 256) * RuDragonGame.getRatioMonitorH());
                    if (!RuDragonGame.getPreference().loadFendOff()) {
                        isActiveBuy = isActiveBuys[9];
                        isBuy = isBuys[9];
                        buyUpgrade = FENDOFF;
                        buyHam = 2500;
                        buyX = 768;
                        buyY = RuDragonGame.HEIGHT / 2 - 240;
                        imageButton.get("buy").setPosition(768, RuDragonGame.HEIGHT / 2 - 260);
                    }
                }
                //endregion
            } else if (name.equals("Roar")) {
                //region
                if (!isScroll) {
                    imageButton.get("roar").setSize(128, 128);
                    imageButton.get("roar").setPosition(1024, -288 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("roar"));
                    textBasic.get(1).setText(RuDragonGame.getLanguage().text.get("roarFull"));
                    labelDescription();
                    if (RuDragonGame.getPreference().loadLevel() < 3) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(3));
                    else textInfo.get(6).setText("");
                    textInfo.get(6).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textInfo.get(6).getPrefWidth() / 2, (RuDragonGame.HEIGHT - 256) * RuDragonGame.getRatioMonitorH());
                    if (RuDragonGame.getPreference().loadRoar() < 10) {
                        isActiveBuy = isActiveBuys[2];
                        isBuy = isBuys[2];
                        buyUpgrade = ROAR;
                        buyHam = 100;
                        buyX = 1024;
                        buyY = RuDragonGame.HEIGHT / 2 - 240;
                        imageButton.get("buy").setPosition(1030, RuDragonGame.HEIGHT / 2 - 260);
                    }
                }
                //endregion
            } else if (name.equals("BonusXP")) {
                //region
                if (!isScroll) {
                    imageButton.get("bonusXP").setSize(128, 128);
                    imageButton.get("bonusXP").setPosition(1280, -288 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("bonusXP"));
                    textBasic.get(1).setText(RuDragonGame.getLanguage().text.get("bonusXPFull"));
                    labelDescription();
                    if (RuDragonGame.getPreference().loadLevel() < 2) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(2));
                    else if (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBonusXP() >= 10) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(3));
                    else if (RuDragonGame.getPreference().loadLevel() >= 3 && RuDragonGame.getPreference().loadLevel() <= 6 && RuDragonGame.getPreference().loadBonusXP() >= 25) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(7));
                    else if (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadBonusXP() >= 30) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(8));
                    else if (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadBonusXP() >= 35) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(9));
                    else textInfo.get(6).setText("");
                    textInfo.get(6).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textInfo.get(6).getPrefWidth() / 2, (RuDragonGame.HEIGHT - 256) * RuDragonGame.getRatioMonitorH());
                    if (RuDragonGame.getPreference().loadBonusXP() < 40) {
                        isActiveBuy = isActiveBuys[3];
                        isBuy = isBuys[3];
                        buyUpgrade = BONUSXP;
                        buyHam = 125;
                        buyX = 1280;
                        buyY = RuDragonGame.HEIGHT / 2 - 240;
                        imageButton.get("buy").setPosition(1280, RuDragonGame.HEIGHT / 2 - 260);
                    }
                }
                //endregion
            } else if (name.equals("BonusHam")) {
                //region
                if (!isScroll) {
                    imageButton.get("bonusHam").setSize(128, 128);
                    imageButton.get("bonusHam").setPosition(1536, -288 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("bonusHam"));
                    textBasic.get(1).setText(RuDragonGame.getLanguage().text.get("bonusHamFull"));
                    labelDescription();
                    if (RuDragonGame.getPreference().loadLevel() < 2) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(2));
                    else if (RuDragonGame.getPreference().loadLevel() >= 2 && RuDragonGame.getPreference().loadLevel() <= 5 && RuDragonGame.getPreference().loadBonusHam() >= 15) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(6));
                    else if (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadBonusHam() >= 20) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(7));
                    else if (RuDragonGame.getPreference().loadLevel() == 7 && RuDragonGame.getPreference().loadBonusHam() >= 25) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(8));
                    else if (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadBonusHam() >= 30) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(9));
                    else if (RuDragonGame.getPreference().loadLevel() == 9 && RuDragonGame.getPreference().loadBonusHam() >= 35) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(10));
                    else textInfo.get(6).setText("");
                    textInfo.get(6).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textInfo.get(6).getPrefWidth() / 2, (RuDragonGame.HEIGHT - 256) * RuDragonGame.getRatioMonitorH());
                    if (RuDragonGame.getPreference().loadBonusHam() < 40) {
                        isActiveBuy = isActiveBuys[4];
                        isBuy = isBuys[4];
                        buyUpgrade = BONUSHAM;
                        buyHam = 165;
                        buyX = 1536;
                        buyY = RuDragonGame.HEIGHT / 2 - 240;
                        imageButton.get("buy").setPosition(1536, RuDragonGame.HEIGHT / 2 - 260);
                    }
                }
                //endregion
            } else if (name.equals("BuyTree")) {
                //region
                if (!isScroll) {
                    imageButton.get("buyTree").setSize(128, 128);
                    imageButton.get("buyTree").setPosition(RuDragonGame.WIDTH / 3 - 64, -288 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1)
                        RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("buyTree"));
                    textBasic.get(1).setText(RuDragonGame.getLanguage().text.get("buyTreeFull"));
                    labelDescription();
                    if (RuDragonGame.getPreference().loadLevel() == 0) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(1));
                    else if (RuDragonGame.getPreference().loadLevel() == 1 && RuDragonGame.getPreference().loadBuyTree() >= 1) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(2));
                    else if (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBuyTree() >= 2) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(3));
                    else textInfo.get(6).setText("");
                    textInfo.get(6).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textInfo.get(6).getPrefWidth() / 2, (RuDragonGame.HEIGHT - 256) * RuDragonGame.getRatioMonitorH());
                    if (RuDragonGame.getPreference().loadBuyTree() < 3) {
                        isActiveBuy = isActiveBuys[5];
                        isBuy = isBuys[5];
                        buyUpgrade = TREES;
                        buyHam = 1250;
                        buyX = RuDragonGame.WIDTH / 3 - 64;
                        buyY = RuDragonGame.HEIGHT / 2 - 240;
                        imageButton.get("buy").setPosition(RuDragonGame.WIDTH / 3 - 64, RuDragonGame.HEIGHT / 2 - 260);
                    }
                }
                //endregion
            } else if (name.equals("BuyBirds")) {
                //region
                if (!isScroll) {
                    imageButton.get("buyBirds").setSize(128, 128);
                    imageButton.get("buyBirds").setPosition(RuDragonGame.WIDTH / 2 - 64, -288 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("buyBirds"));
                    textBasic.get(1).setText(RuDragonGame.getLanguage().text.get("buyBirdsFull"));
                    labelDescription();
                    if (RuDragonGame.getPreference().loadLevel() < 2) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(2));
                    else if (RuDragonGame.getPreference().loadLevel() == 2 && RuDragonGame.getPreference().loadBuyBirds() >= 1) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(3));
                    else if (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadBuyBirds() >= 2) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(4));
                    else textInfo.get(6).setText("");
                    textInfo.get(6).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textInfo.get(6).getPrefWidth() / 2, (RuDragonGame.HEIGHT - 256) * RuDragonGame.getRatioMonitorH());
                    if (RuDragonGame.getPreference().loadBuyBirds() < 3) {
                        isActiveBuy = isActiveBuys[6];
                        isBuy = isBuys[6];
                        buyUpgrade = BIRDS;
                        buyHam = 750;
                        buyX = RuDragonGame.WIDTH / 2 - 64;
                        buyY = RuDragonGame.HEIGHT / 2 - 240;
                        imageButton.get("buy").setPosition(RuDragonGame.WIDTH / 2 - 64, RuDragonGame.HEIGHT / 2 - 260);
                    }
                }
                //endregion
            } else if (name.equals("BuyLiveStock")) {
                //region
                if (!isScroll) {
                    imageButton.get("buyLiveStock").setSize(128, 128);
                    imageButton.get("buyLiveStock").setPosition(RuDragonGame.WIDTH - RuDragonGame.WIDTH / 3 - 64, -288 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1)
                        RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    textBasic.get(0).setText(RuDragonGame.getLanguage().text.get("buyLiveStock"));
                    textBasic.get(1).setText(RuDragonGame.getLanguage().text.get("buyLiveStockFull"));
                    labelDescription();
                    if (RuDragonGame.getPreference().loadLevel() < 5) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(5));
                    else if (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadBuyLiveStock() >= 1) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(6));
                    else if (RuDragonGame.getPreference().loadLevel() == 6 && RuDragonGame.getPreference().loadBuyLiveStock() >= 2) textInfo.get(6).setText(new StringBuilder().append(RuDragonGame.getLanguage().text.get("upLVL")).append(7));
                    else textInfo.get(6).setText("");
                    textInfo.get(6).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textInfo.get(6).getPrefWidth() / 2, (RuDragonGame.HEIGHT - 256) * RuDragonGame.getRatioMonitorH());
                    if (RuDragonGame.getPreference().loadBuyLiveStock() < 3) {
                        isActiveBuy = isActiveBuys[8];
                        isBuy = isBuys[8];
                        buyUpgrade = LIVESTOCK;
                        buyHam = 1500;
                        buyX = RuDragonGame.WIDTH - RuDragonGame.WIDTH / 3 - 64;
                        buyY = RuDragonGame.HEIGHT / 2 - 240;
                        imageButton.get("buy").setPosition(RuDragonGame.WIDTH - RuDragonGame.WIDTH / 3 - 64, RuDragonGame.HEIGHT / 2 - 260);
                    }
                }
                //endregion
            }
        }
    }
}