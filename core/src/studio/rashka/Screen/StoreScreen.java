package studio.rashka.Screen;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;
import studio.rashka.Lib.btn.Buttons;
import studio.rashka.RuDragonGame;

public class StoreScreen extends State {

    private Stage stage;
    private Calendar calendar;
    private SimpleDateFormat time;
    private Map<String, Buttons> buttons;
    private Map<String, LabelStyle> labelStyles;
    private List<Label> text;

    public StoreScreen(final Game game) {
        super(game);
        stage = new Stage();
        calendar = Calendar.getInstance();
        time = new SimpleDateFormat("HH");
        RuDragonGame.adHandler.showAds(111);

        labelText();
        buttons();
        if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().startCave();

        Gdx.input.setInputProcessor(stage);
    }

    private void buttons() {
        buttons = new HashMap<String, Buttons>();
        buttons.put("Back", new Buttons("Back", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Back"), 128, 64, 32, RuDragonGame.HEIGHT - 96));
        buttons.put("UpXP", new Buttons("UpXP", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("UpXP"), 256, 256, 128, RuDragonGame.HEIGHT / 2 - 64));
        buttons.put("UpHam", new Buttons("UpHam", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Love_eat"), 256, 256, 480, RuDragonGame.HEIGHT / 2 - 64));
        buttons.put("Angel", new Buttons("Angel", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Angel"), 256, 256, RuDragonGame.WIDTH / 2 - 128, RuDragonGame.HEIGHT / 2 - 64));
        buttons.put("BonusXP", new Buttons("BonusXP", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("BonusXP"), 256, 256, 1184, RuDragonGame.HEIGHT / 2 - 64));
        buttons.put("BonusHam", new Buttons("BonusHam", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("BonusHam"), 256, 256, 1536, RuDragonGame.HEIGHT / 2 - 64));

        buttons.get("Back").addListener(new ButtonsInputListener(buttons.get("Back").getName()));
        buttons.get("UpXP").addListener(new ButtonsInputListener(buttons.get("UpXP").getName()));
        buttons.get("UpHam").addListener(new ButtonsInputListener(buttons.get("UpHam").getName()));
        buttons.get("Angel").addListener(new ButtonsInputListener(buttons.get("Angel").getName()));
        buttons.get("BonusXP").addListener(new ButtonsInputListener(buttons.get("BonusXP").getName()));
        buttons.get("BonusHam").addListener(new ButtonsInputListener(buttons.get("BonusHam").getName()));

        stage.addActor(buttons.get("Back"));
        stage.addActor(buttons.get("UpXP"));
        stage.addActor(buttons.get("UpHam"));
        stage.addActor(buttons.get("BonusXP"));
        stage.addActor(buttons.get("BonusHam"));
        stage.addActor(buttons.get("Angel"));
    }

    private void labelText() {
        labelStyles = new HashMap<String, LabelStyle>();
        text = new ArrayList<Label>();

        labelStyles.put("Black48", new LabelStyle(RuDragonGame.getFontTTF().getFont48(), Color.WHITE));
        labelStyles.put("White38", new LabelStyle(RuDragonGame.getFontTTF().getFont38(), Color.WHITE));

        text.add(0, new Label("+500 XP", labelStyles.get("Black48")));
        text.add(1, new Label("+250", labelStyles.get("Black48")));
        text.add(2, new Label("+20% XP", labelStyles.get("Black48")));
        text.add(3, new Label("+20%", labelStyles.get("Black48")));
        text.add(4, new Label(RuDragonGame.getLanguage().textMenu.get("storeBonus"), labelStyles.get("White38")));
        text.add(5, new Label(RuDragonGame.getLanguage().textMenu.get("storeAds"), labelStyles.get("White38")));
        text.add(6, new Label(RuDragonGame.getLanguage().textMenu.get("storeAngel"), labelStyles.get("White38")));

        text.get(0).setPosition(160 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 - 128) * RuDragonGame.getRatioMonitorH());
        text.get(1).setPosition(524 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 - 128) * RuDragonGame.getRatioMonitorH());
        text.get(2).setPosition(1216 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 - 128) * RuDragonGame.getRatioMonitorH());
        text.get(3).setPosition(1580 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 - 128) * RuDragonGame.getRatioMonitorH());
        text.get(4).setPosition(32 * RuDragonGame.getRatioMonitorW(), 128 * RuDragonGame.getRatioMonitorH());
        text.get(5).setPosition(32 * RuDragonGame.getRatioMonitorW(), 30 * RuDragonGame.getRatioMonitorH());
        text.get(6).setPosition(32 * RuDragonGame.getRatioMonitorW(), 80 * RuDragonGame.getRatioMonitorH());

        stage.addActor(text.get(0));
        stage.addActor(text.get(1));
        stage.addActor(text.get(2));
        stage.addActor(text.get(3));
        stage.addActor(text.get(4));
        stage.addActor(text.get(5));
        stage.addActor(text.get(6));
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
        batch.draw(RuDragonGame.getTextures().textureRegions.get("ham"), 618, RuDragonGame.HEIGHT / 2 - 134);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("Heart"), RuDragonGame.WIDTH / 2 - 64, RuDragonGame.HEIGHT / 2 - 166, 128, 128);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("ham"), 1700, RuDragonGame.HEIGHT / 2 - 134);
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
            text.clear();
            labelStyles.clear();
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
            if (name.equals("UpXP")) {
                buttons.get("UpXP").setSize(250, 250);
                buttons.get("UpXP").setPosition(131, RuDragonGame.HEIGHT / 2 - 61);
            } else if (name.equals("UpHam")) {
                buttons.get("UpHam").setSize(250, 250);
                buttons.get("UpHam").setPosition(483, RuDragonGame.HEIGHT / 2 - 61);
            } else if (name.equals("Angel")) {
                buttons.get("Angel").setSize(250, 250);
                buttons.get("Angel").setPosition(RuDragonGame.WIDTH / 2 - 125, RuDragonGame.HEIGHT / 2 - 61);
            } else if (name.equals("BonusXP")) {
                buttons.get("BonusXP").setSize(250, 250);
                buttons.get("BonusXP").setPosition(1187, RuDragonGame.HEIGHT / 2 - 61);
            } else if (name.equals("BonusHam")) {
                buttons.get("BonusHam").setSize(250, 250);
                buttons.get("BonusHam").setPosition(1539, RuDragonGame.HEIGHT / 2 - 61);
            }
            return true;
        }

        @Override
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
            if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);

            if (name.equals("Back")) RuDragonGame.getGame().set(new MenuScreen(game));
            else if (name.equals("UpXP")) {
                buttons.get("UpXP").setSize(256, 256);
                buttons.get("UpXP").setPosition(128, RuDragonGame.HEIGHT / 2 - 64);
                RuDragonGame.adHandler.showAds(2);
            } else if (name.equals("UpHam")) {
                buttons.get("UpHam").setSize(256, 256);
                buttons.get("UpHam").setPosition(480, RuDragonGame.HEIGHT / 2 - 64);
                RuDragonGame.adHandler.showAds(3);
            } else if (name.equals("Angel")) {
                buttons.get("Angel").setSize(256, 256);
                buttons.get("Angel").setPosition(RuDragonGame.WIDTH / 2 - 128, RuDragonGame.HEIGHT / 2 - 64);
                RuDragonGame.adHandler.showAds(4);
            } else if (name.equals("BonusXP")) {
                buttons.get("BonusXP").setSize(256, 256);
                buttons.get("BonusXP").setPosition(1184, RuDragonGame.HEIGHT / 2 - 64);
                RuDragonGame.adHandler.showAds(0);
            } else if (name.equals("BonusHam")) {
                buttons.get("BonusHam").setSize(256, 256);
                buttons.get("BonusHam").setPosition(1536, RuDragonGame.HEIGHT / 2 - 64);
                RuDragonGame.adHandler.showAds(1);
            }
        }
    }
}