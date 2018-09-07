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
import com.badlogic.gdx.utils.StringBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;
import studio.rashka.Lib.btn.Buttons;
import studio.rashka.RuDragonGame;

public class EncyclopediaScreen extends State {

    private static final int
            NOACTIVE = 0,
            FOODACTIVE = 1,
            DANGERSACTIVE = 2;

    private Map<String, LabelStyle> textStyles;
    private Map<String, Label> text;
    private Map<String, Buttons> buttons;

    private Stage stage;
    private Calendar calendar;
    private SimpleDateFormat time;

    private float scroll = 1;
    private boolean isScroll = false;
    private boolean isFood = false, isXP = false;
    private int active = NOACTIVE;
    private int hide = NOACTIVE;

    public EncyclopediaScreen(final Game game) {
        super(game);
        RuDragonGame.getLanguage().textEncyclopedia();

        stage = new Stage();
        calendar = Calendar.getInstance();
        time = new SimpleDateFormat("HH");

        text = new HashMap<String, Label>();
        textStyles = new HashMap<String, LabelStyle>();
        textStyles.put("BigBlack", new LabelStyle(RuDragonGame.getFontTTF().getFont48(), Color.WHITE));
        textStyles.put("SmallWhite", new LabelStyle(RuDragonGame.getFontTTF().getFont32(), Color.WHITE));

        text.put("text", new Label(new StringBuilder(RuDragonGame.getLanguage().text.get("nullText")), textStyles.get("BigBlack")));
        text.put("textFull", new Label("", textStyles.get("BigBlack")));
        text.put("textScoreFood", new Label("", textStyles.get("BigBlack")));
        text.get("text").setPosition(1072 * RuDragonGame.getRatioMonitorW(), -96 * RuDragonGame.getRatioMonitorH());

        text.put("textBirds", new Label(new StringBuilder(RuDragonGame.getLanguage().text.get("birds")), textStyles.get("BigBlack")));
        text.get("textBirds").setPosition(440 * RuDragonGame.getRatioMonitorW(), -48 * RuDragonGame.getRatioMonitorH());

        text.put("textTrees", new Label(new StringBuilder(RuDragonGame.getLanguage().text.get("trees")), textStyles.get("BigBlack")));
        text.get("textTrees").setPosition(600 * RuDragonGame.getRatioMonitorW(), -270 * RuDragonGame.getRatioMonitorH());

        text.put("textCharacterds", new Label(new StringBuilder(RuDragonGame.getLanguage().text.get("character")), textStyles.get("BigBlack")));
        text.get("textCharacterds").setPosition(1520 * RuDragonGame.getRatioMonitorW(), -232 * RuDragonGame.getRatioMonitorH());

        text.put("textFoods", new Label(new StringBuilder(RuDragonGame.getLanguage().text.get("foods")), textStyles.get("BigBlack")));
        text.get("textFoods").setPosition(440 * RuDragonGame.getRatioMonitorW(), -212 * RuDragonGame.getRatioMonitorH());

        buttons();

        stage.addActor(text.get("text"));
        stage.addActor(text.get("textFull"));
        stage.addActor(text.get("textBirds"));
        stage.addActor(text.get("textTrees"));
        stage.addActor(text.get("textCharacterds"));
        stage.addActor(text.get("textFoods"));
        stage.addActor(text.get("textScoreFood"));
        Gdx.input.setInputProcessor(stage);
        if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().startCave();
    }

    private void buttons() {
        buttons = new HashMap<String, Buttons>();
        buttons.put("back", new Buttons("Back", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Back"), 128, 64, 32, RuDragonGame.HEIGHT - 96));
        buttons.put("food", new Buttons("Food", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Food"), 256, 256, 512, RuDragonGame.HEIGHT / 2 - 128));
        buttons.put("dangers", new Buttons("Dangers", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Dangers"), 256, 256, RuDragonGame.WIDTH - 768, RuDragonGame.HEIGHT / 2 - 128));
        buttons.put("buzzard", new Buttons("Buzzard", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Buzzard"), 192, 128, 192, -144));
        buttons.put("eagle", new Buttons("Eagle", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Eagle"), 192, 128, 400, -144));
        buttons.put("falcon", new Buttons("Falcon", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Falcon"), 192, 128, 608, -144));
        buttons.put("hawk", new Buttons("Hawk", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Hawk"), 192, 128, 816, -144));
        buttons.put("korshun", new Buttons("Korshun", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Korshun"), 192, 128, 192, -288));
        buttons.put("orlan", new Buttons("Orlan", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Orlan"), 192, 128, 400, -288));
        buttons.put("osprey", new Buttons("Osprey", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Osprey"), 192, 128, 608, -288));
        buttons.put("ash", new Buttons("Ash", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Ash"), 192, 192, 64, -544));
        buttons.put("aspen", new Buttons("Aspen", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Aspen"), 192, 192, 272, -544));
        buttons.put("birch", new Buttons("Birch", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Birch"), 192, 192, 480, -544));
        buttons.put("fir", new Buttons("Fir", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Fir"), 192, 192, 688, -544));
        buttons.put("linden", new Buttons("Linden", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Linden"), 192, 192, 896, -544));
        buttons.put("maple", new Buttons("Maple", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Maple"), 192, 192, 1104, -544));
        buttons.put("oak", new Buttons("Oak", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Oak"), 192, 192, 64, -752));
        buttons.put("pine", new Buttons("Pine", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Pine"), 192, 192, 272, -752));
        buttons.put("poplar", new Buttons("Poplar", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Poplar"), 192, 192, 480, -752));
        buttons.put("spruce", new Buttons("Spruce", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Spruce"), 192, 192, 688, -752));
        buttons.put("willow", new Buttons("Willow", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Willow"), 192, 192, 896, -752));
        buttons.put("babaYaga", new Buttons("BabaYaga", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("BabaYaga"), 192, 192, 1536, -544));
        buttons.put("duck", new Buttons("Duck", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Duck"), 192, 128, 128, -352));
        buttons.put("goat", new Buttons("Goat", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Goat"), 192, 128, 336, -352));
        buttons.put("pig", new Buttons("Pig", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Pig"), 192, 128, 544, -352));
        buttons.put("kine", new Buttons("Kine", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Kine"), 192, 128, 752, -352));
        buttons.put("CasketXP", new Buttons("CasketXP", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("CasketXP"), 128, 128, 400, -512));
        buttons.put("CasketXam", new Buttons("CasketXam", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("CasketXam"), 128, 128, 544, -512));

        buttons.get("back").addListener(new ButtonsInputListener(buttons.get("back").getName()));
        buttons.get("food").addListener(new ButtonsInputListener(buttons.get("food").getName()));
        buttons.get("dangers").addListener(new ButtonsInputListener(buttons.get("dangers").getName()));
        buttons.get("buzzard").addListener(new ButtonsInputListener(buttons.get("buzzard").getName()));
        buttons.get("eagle").addListener(new ButtonsInputListener(buttons.get("eagle").getName()));
        buttons.get("falcon").addListener(new ButtonsInputListener(buttons.get("falcon").getName()));
        buttons.get("hawk").addListener(new ButtonsInputListener(buttons.get("hawk").getName()));
        buttons.get("korshun").addListener(new ButtonsInputListener(buttons.get("korshun").getName()));
        buttons.get("orlan").addListener(new ButtonsInputListener(buttons.get("orlan").getName()));
        buttons.get("osprey").addListener(new ButtonsInputListener(buttons.get("osprey").getName()));
        buttons.get("ash").addListener(new ButtonsInputListener(buttons.get("ash").getName()));
        buttons.get("aspen").addListener(new ButtonsInputListener(buttons.get("aspen").getName()));
        buttons.get("birch").addListener(new ButtonsInputListener(buttons.get("birch").getName()));
        buttons.get("fir").addListener(new ButtonsInputListener(buttons.get("fir").getName()));
        buttons.get("linden").addListener(new ButtonsInputListener(buttons.get("linden").getName()));
        buttons.get("maple").addListener(new ButtonsInputListener(buttons.get("maple").getName()));
        buttons.get("oak").addListener(new ButtonsInputListener(buttons.get("oak").getName()));
        buttons.get("pine").addListener(new ButtonsInputListener(buttons.get("pine").getName()));
        buttons.get("poplar").addListener(new ButtonsInputListener(buttons.get("poplar").getName()));
        buttons.get("spruce").addListener(new ButtonsInputListener(buttons.get("spruce").getName()));
        buttons.get("willow").addListener(new ButtonsInputListener(buttons.get("willow").getName()));
        buttons.get("babaYaga").addListener(new ButtonsInputListener(buttons.get("babaYaga").getName()));
        buttons.get("duck").addListener(new ButtonsInputListener(buttons.get("duck").getName()));
        buttons.get("goat").addListener(new ButtonsInputListener(buttons.get("goat").getName()));
        buttons.get("pig").addListener(new ButtonsInputListener(buttons.get("pig").getName()));
        buttons.get("kine").addListener(new ButtonsInputListener(buttons.get("kine").getName()));
        buttons.get("CasketXP").addListener(new ButtonsInputListener(buttons.get("CasketXP").getName()));
        buttons.get("CasketXam").addListener(new ButtonsInputListener(buttons.get("CasketXam").getName()));

        stage.addActor(buttons.get("back"));
        stage.addActor(buttons.get("food"));
        stage.addActor(buttons.get("dangers"));
        stage.addActor(buttons.get("buzzard"));
        stage.addActor(buttons.get("eagle"));
        stage.addActor(buttons.get("falcon"));
        stage.addActor(buttons.get("hawk"));
        stage.addActor(buttons.get("korshun"));
        stage.addActor(buttons.get("orlan"));
        stage.addActor(buttons.get("osprey"));
        stage.addActor(buttons.get("ash"));
        stage.addActor(buttons.get("aspen"));
        stage.addActor(buttons.get("birch"));
        stage.addActor(buttons.get("fir"));
        stage.addActor(buttons.get("linden"));
        stage.addActor(buttons.get("maple"));
        stage.addActor(buttons.get("oak"));
        stage.addActor(buttons.get("pine"));
        stage.addActor(buttons.get("poplar"));
        stage.addActor(buttons.get("spruce"));
        stage.addActor(buttons.get("willow"));
        stage.addActor(buttons.get("babaYaga"));
        stage.addActor(buttons.get("duck"));
        stage.addActor(buttons.get("goat"));
        stage.addActor(buttons.get("pig"));
        stage.addActor(buttons.get("kine"));
        stage.addActor(buttons.get("CasketXP"));
        stage.addActor(buttons.get("CasketXam"));
    }

    private void labelDescription() {
        text.get("text").setPosition(1072 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 + 192) * RuDragonGame.getRatioMonitorH());
        text.get("textFull").setPosition(1072 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 + 96) * RuDragonGame.getRatioMonitorH());
        text.get("textFull").setWidth((RuDragonGame.WIDTH - 1072) * RuDragonGame.getRatioMonitorW());
        text.get("textFull").setWrap(true);
        if (isFood || isXP) text.get("textScoreFood").setPosition((RuDragonGame.WIDTH - 100) * RuDragonGame.getRatioMonitorW() - text.get("textScoreFood").getPrefWidth(), (RuDragonGame.HEIGHT / 2 + 192) * RuDragonGame.getRatioMonitorH());
    }

    private void scrollBirds(float scroll) {
        if (active == DANGERSACTIVE) {
            text.get("textBirds").setPosition(440 * RuDragonGame.getRatioMonitorW(), (-38 + scroll) * RuDragonGame.getRatioMonitorH());
            buttons.get("buzzard").setPosition(192, -182 + scroll);
            buttons.get("eagle").setPosition(400, -182 + scroll);
            buttons.get("falcon").setPosition(608, -182 + scroll);
            buttons.get("hawk").setPosition(816, -182 + scroll);
            buttons.get("korshun").setPosition(192, -326 + scroll);
            buttons.get("orlan").setPosition(400, -326 + scroll);
            buttons.get("osprey").setPosition(608, -326 + scroll);
        }
        else if (active == NOACTIVE && hide == DANGERSACTIVE) {
            text.get("textBirds").setPosition(440 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 + 234 - scroll) * RuDragonGame.getRatioMonitorH());
            buttons.get("buzzard").setPosition(192, RuDragonGame.HEIGHT / 2 + 90 - scroll);
            buttons.get("eagle").setPosition(400, RuDragonGame.HEIGHT / 2 + 90 - scroll);
            buttons.get("falcon").setPosition(608, RuDragonGame.HEIGHT / 2 + 90 - scroll);
            buttons.get("hawk").setPosition(816, RuDragonGame.HEIGHT / 2 + 90 - scroll);
            buttons.get("korshun").setPosition(192, RuDragonGame.HEIGHT / 2 - 54 - scroll);
            buttons.get("orlan").setPosition(400, RuDragonGame.HEIGHT / 2 - 54 - scroll);
            buttons.get("osprey").setPosition(608, RuDragonGame.HEIGHT / 2 - 54 - scroll);
        }
    }

    private void scrollTrees(float scroll) {
        if (active == DANGERSACTIVE) {
            text.get("textTrees").setPosition(600 * RuDragonGame.getRatioMonitorW(), (-382 + scroll) * RuDragonGame.getRatioMonitorH());
            buttons.get("ash").setPosition(64, -582 + scroll);
            buttons.get("aspen").setPosition(272, -582 + scroll);
            buttons.get("birch").setPosition(480, -582 + scroll);
            buttons.get("fir").setPosition(688, -582 + scroll);
            buttons.get("linden").setPosition(896, -582 + scroll);
            buttons.get("maple").setPosition(1104, -582 + scroll);
            buttons.get("oak").setPosition(64, -790 + scroll);
            buttons.get("pine").setPosition(272, -790 + scroll);
            buttons.get("poplar").setPosition(480, -790 + scroll);
            buttons.get("spruce").setPosition(688, -790 + scroll);
            buttons.get("willow").setPosition(896, -790 + scroll);
        }
        else if (active == NOACTIVE && hide == DANGERSACTIVE) {
            text.get("textTrees").setPosition(600 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 - 94 - scroll) * RuDragonGame.getRatioMonitorH());
            buttons.get("ash").setPosition(64, RuDragonGame.HEIGHT / 2 - 294 - scroll);
            buttons.get("aspen").setPosition(272, RuDragonGame.HEIGHT / 2 - 294 - scroll);
            buttons.get("birch").setPosition(480, RuDragonGame.HEIGHT / 2 - 294 - scroll);
            buttons.get("fir").setPosition(688, RuDragonGame.HEIGHT / 2 - 294 - scroll);
            buttons.get("linden").setPosition(896, RuDragonGame.HEIGHT / 2 - 294 - scroll);
            buttons.get("maple").setPosition(1104, RuDragonGame.HEIGHT / 2 - 294 - scroll);
            buttons.get("oak").setPosition(64, RuDragonGame.HEIGHT / 2 - 502 - scroll);
            buttons.get("pine").setPosition(272, RuDragonGame.HEIGHT / 2 - 502 - scroll);
            buttons.get("poplar").setPosition(480, RuDragonGame.HEIGHT / 2 - 502 - scroll);
            buttons.get("spruce").setPosition(688, RuDragonGame.HEIGHT / 2 - 502 - scroll);
            buttons.get("willow").setPosition(896, RuDragonGame.HEIGHT / 2 - 502 - scroll);
        }
    }

    private void scrollCharacters(float scroll) {
        if (active == DANGERSACTIVE) {
            text.get("textCharacterds").setPosition(1520 * RuDragonGame.getRatioMonitorW(), (-344 + scroll) * RuDragonGame.getRatioMonitorH());
            buttons.get("babaYaga").setPosition(1536, -544 + scroll);
        }
        else if (active == NOACTIVE && hide == DANGERSACTIVE) {
            text.get("textCharacterds").setPosition(1520 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 - 56 - scroll) * RuDragonGame.getRatioMonitorH());
            buttons.get("babaYaga").setPosition(1536, RuDragonGame.HEIGHT / 2 - 256 - scroll);
        }
    }

    private void scrollFood(float scroll) {
        if (active == FOODACTIVE) {
            text.get("textFoods").setPosition(392 * RuDragonGame.getRatioMonitorW(), (-212 + scroll) * RuDragonGame.getRatioMonitorH());
            buttons.get("duck").setPosition(128, -352 + scroll);
            buttons.get("goat").setPosition(336, -352 + scroll);
            buttons.get("pig").setPosition(544, -352 + scroll);
            buttons.get("kine").setPosition(752, -352 + scroll);
            buttons.get("CasketXP").setPosition(400, -512 + scroll);
            buttons.get("CasketXam").setPosition(544, -512 + scroll);
        }
        else if (active == NOACTIVE && hide == FOODACTIVE) {
            text.get("textFoods").setPosition(392 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 + 64 - scroll) * RuDragonGame.getRatioMonitorH());
            buttons.get("duck").setPosition(128, RuDragonGame.HEIGHT / 2 - 64 - scroll);
            buttons.get("goat").setPosition(336, RuDragonGame.HEIGHT / 2 - 64 - scroll);
            buttons.get("pig").setPosition(544, RuDragonGame.HEIGHT / 2 - 64 - scroll);
            buttons.get("kine").setPosition(752, RuDragonGame.HEIGHT / 2 - 64 - scroll);
            buttons.get("CasketXP").setPosition(400, RuDragonGame.HEIGHT / 2 - 224 - scroll);
            buttons.get("CasketXam").setPosition(544, RuDragonGame.HEIGHT / 2 - 224 - scroll);
        }
    }

    private void scroll(float scroll) {
        if (active == FOODACTIVE || active == DANGERSACTIVE) {
            text.get("text").setPosition(1072 * RuDragonGame.getRatioMonitorW(), (-96 + scroll) * RuDragonGame.getRatioMonitorH());
            text.get("textFull").setPosition(1072 * RuDragonGame.getRatioMonitorW(), (-192 + scroll) * RuDragonGame.getRatioMonitorH());
        }
        else if (active == NOACTIVE && hide == FOODACTIVE) {
            text.get("text").setPosition(1072 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 + 192 - scroll) * RuDragonGame.getRatioMonitorH());
            text.get("textFull").setPosition(1072 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 + 96 - scroll) * RuDragonGame.getRatioMonitorH());
            if (scroll > RuDragonGame.HEIGHT / 2 + 288) hide = NOACTIVE;
        }
        else if (active == NOACTIVE && hide == DANGERSACTIVE) {
            text.get("text").setPosition(1072 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 + 192 - scroll) * RuDragonGame.getRatioMonitorH());
            text.get("textFull").setPosition(1072 * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 + 96 - scroll) * RuDragonGame.getRatioMonitorH());
            if (scroll > RuDragonGame.HEIGHT / 2 + 288) hide = NOACTIVE;
        }
        if (scroll > RuDragonGame.HEIGHT / 2 + 288) isScroll = false;
    }

    @Override
    public void update(float deltaTime) {
        if (isScroll) {
            if (active == FOODACTIVE) {
                scroll = scroll + deltaTime * 800;
                buttons.get("food").setPosition(512 - scroll, RuDragonGame.HEIGHT / 2 - 128);
                buttons.get("dangers").setPosition(RuDragonGame.WIDTH - 768 + scroll, RuDragonGame.HEIGHT / 2 - 128);
                scrollFood(scroll);
                scroll(scroll);
            }
            else if (active == DANGERSACTIVE) {
                scroll = scroll + deltaTime * 800;
                if (scroll <= 512 + 260) {
                    buttons.get("food").setPosition(512 - scroll, RuDragonGame.HEIGHT / 2 - 128);
                    buttons.get("dangers").setPosition(RuDragonGame.WIDTH - 768 + scroll, RuDragonGame.HEIGHT / 2 - 128);
                }
                scrollCharacters(scroll);
                scrollTrees(scroll);
                scrollBirds(scroll);
                scroll(scroll);
            }
            else if (active == NOACTIVE) {
                scroll = scroll + deltaTime * 800;
                if (scroll <= 512 + 260) {
                    buttons.get("food").setPosition(-260 + scroll, RuDragonGame.HEIGHT / 2 - 128);
                    buttons.get("dangers").setPosition(RuDragonGame.WIDTH - 4 - scroll, RuDragonGame.HEIGHT / 2 - 128);
                }
                scrollCharacters(scroll);
                scrollTrees(scroll);
                scrollBirds(scroll);
                scrollFood(scroll);
                scroll(scroll);
            }
        }
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
        batch.end();

        stage.act();
        stage.draw();

        batch.begin();
        if (isFood) batch.draw(RuDragonGame.getTextures().textureRegions.get("ham"), RuDragonGame.WIDTH - 96, RuDragonGame.HEIGHT / 2 + 184);
        batch.end();
    }

    @Override
    public void dispose() {
        try {
            RuDragonGame.getMusicSound().stopCave();
            stage.dispose();
            buttons.clear();
            textStyles.clear();
            text.clear();
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
            if (!isScroll) {
                if (name.equals("Buzzard")) {
                    buttons.get("buzzard").setSize(180, 120);
                    buttons.get("buzzard").setPosition(198, -178 + scroll);
                } else if (name.equals("Eagle")) {
                    buttons.get("eagle").setSize(180, 120);
                    buttons.get("eagle").setPosition(406, -178 + scroll);
                } else if (name.equals("Falcon")) {
                    buttons.get("falcon").setSize(180, 120);
                    buttons.get("falcon").setPosition(614, -178 + scroll);
                } else if (name.equals("Hawk")) {
                    buttons.get("hawk").setSize(180, 120);
                    buttons.get("hawk").setPosition(822, -178 + scroll);
                } else if (name.equals("Korshun")) {
                    buttons.get("korshun").setSize(180, 120);
                    buttons.get("korshun").setPosition(198, -322 + scroll);
                } else if (name.equals("Orlan")) {
                    buttons.get("orlan").setSize(180, 120);
                    buttons.get("orlan").setPosition(406, -322 + scroll);
                } else if (name.equals("Osprey")) {
                    buttons.get("osprey").setSize(180, 120);
                    buttons.get("osprey").setPosition(614, -322 + scroll);
                } else if (name.equals("Ash")) {
                    buttons.get("ash").setSize(180, 180);
                    buttons.get("ash").setPosition(70, -576 + scroll);
                } else if (name.equals("Aspen")) {
                    buttons.get("aspen").setSize(180, 180);
                    buttons.get("aspen").setPosition(278, -576 + scroll);
                } else if (name.equals("Birch")) {
                    buttons.get("birch").setSize(180, 180);
                    buttons.get("birch").setPosition(486, -576 + scroll);
                } else if (name.equals("Fir")) {
                    buttons.get("fir").setSize(180, 180);
                    buttons.get("fir").setPosition(694, -576 + scroll);
                } else if (name.equals("Linden")) {
                    buttons.get("linden").setSize(180, 180);
                    buttons.get("linden").setPosition(902, -576 + scroll);
                } else if (name.equals("Maple")) {
                    buttons.get("maple").setSize(180, 180);
                    buttons.get("maple").setPosition(1110, -576 + scroll);
                } else if (name.equals("Oak")) {
                    buttons.get("oak").setSize(180, 180);
                    buttons.get("oak").setPosition(70, -784 + scroll);
                } else if (name.equals("Pine")) {
                    buttons.get("pine").setSize(180, 180);
                    buttons.get("pine").setPosition(278, -784 + scroll);
                } else if (name.equals("Poplar")) {
                    buttons.get("poplar").setSize(180, 180);
                    buttons.get("poplar").setPosition(486, -784 + scroll);
                } else if (name.equals("Spruce")) {
                    buttons.get("spruce").setSize(180, 180);
                    buttons.get("spruce").setPosition(694, -784 + scroll);
                } else if (name.equals("Willow")) {
                    buttons.get("willow").setSize(180, 180);
                    buttons.get("willow").setPosition(902, -784 + scroll);
                } else if (name.equals("BabaYaga")) {
                    buttons.get("babaYaga").setSize(180, 180);
                    buttons.get("babaYaga").setPosition(1542, -538 + scroll);
                } else if (name.equals("Duck")) {
                    buttons.get("duck").setSize(180, 120);
                    buttons.get("duck").setPosition(134, -348 + scroll);
                } else if (name.equals("Goat")) {
                    buttons.get("goat").setSize(180, 120);
                    buttons.get("goat").setPosition(342, -348 + scroll);
                } else if (name.equals("Pig")) {
                    buttons.get("pig").setSize(180, 120);
                    buttons.get("pig").setPosition(550, -348 + scroll);
                } else if (name.equals("Kine")) {
                    buttons.get("kine").setSize(180, 120);
                    buttons.get("kine").setPosition(758, -348 + scroll);
                } else if (name.equals("CasketXP")) {
                    buttons.get("CasketXP").setSize(120, 120);
                    buttons.get("CasketXP").setPosition(404, -508 + scroll);
                } else if (name.equals("CasketXam")) {
                    buttons.get("CasketXam").setSize(120, 120);
                    buttons.get("CasketXam").setPosition(548, -508 + scroll);
                }
            }
            return true;
        }

        @Override
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            if (!isScroll) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);

                if (name.equals("Back")) {
                    //region
                    if (active == NOACTIVE) game.set(new MenuScreen(game));
                    else if (active == FOODACTIVE) {
                        isScroll = true;
                        isFood = false;
                        isXP = false;
                        active = NOACTIVE;
                        scroll = 1;
                        if (text.get("textScoreFood") != null) text.get("textScoreFood").setPosition(-208 * RuDragonGame.getRatioMonitorW(), -192 * RuDragonGame.getRatioMonitorH());
                    } else if (active == DANGERSACTIVE) {
                        isScroll = true;
                        active = NOACTIVE;
                        scroll = 1;
                    }
                    //endregion
                } else if (name.equals("Food")) {
                    //region
                    isScroll = true;
                    active = FOODACTIVE;
                    hide = FOODACTIVE;
                    scroll = 1;
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("nullText")));
                    text.get("textFull").setText("");
                    labelDescription();
                    //endregion
                } else if (name.equals("Dangers")) {
                    //region
                    isScroll = true;
                    active = DANGERSACTIVE;
                    hide = DANGERSACTIVE;
                    scroll = 1;
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("nullText")));
                    text.get("textFull").setText("");
                    labelDescription();
                    //endregion
                } else if (name.equals("Buzzard")) {
                    //region
                    buttons.get("buzzard").setSize(192, 128);
                    buttons.get("buzzard").setPosition(192, -182 + scroll);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("buzzard")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("buzzardFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Eagle")) {
                    //region
                    buttons.get("eagle").setSize(192, 128);
                    buttons.get("eagle").setPosition(400, -182 + scroll);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("eagle")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("eagleFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Falcon")) {
                    //region
                    buttons.get("falcon").setSize(192, 128);
                    buttons.get("falcon").setPosition(608, -182 + scroll);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("falcon")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("falconFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Hawk")) {
                    //region
                    buttons.get("hawk").setSize(192, 128);
                    buttons.get("hawk").setPosition(816, -182 + scroll);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("hawk")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("hawkFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Korshun")) {
                    //region
                    buttons.get("korshun").setSize(192, 128);
                    buttons.get("korshun").setPosition(192, -326 + scroll);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("korshun")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("korshunFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Orlan")) {
                    //region
                    buttons.get("orlan").setSize(192, 128);
                    buttons.get("orlan").setPosition(400, -326 + scroll);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("orlan")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("orlanFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Osprey")) {
                    //region
                    buttons.get("osprey").setSize(192, 128);
                    buttons.get("osprey").setPosition(608, -326 + scroll);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("osprey")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("ospreyFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Ash")) {
                    //region
                    buttons.get("ash").setSize(192, 192);
                    buttons.get("ash").setPosition(64, -582 + scroll);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("ash")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("ashFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Aspen")) {
                    //region
                    buttons.get("aspen").setSize(192, 192);
                    buttons.get("aspen").setPosition(272, -582 + scroll);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("aspen")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("aspenFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Birch")) {
                    //region
                    buttons.get("birch").setSize(192, 192);
                    buttons.get("birch").setPosition(480, -582 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("birch")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("birchFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Fir")) {
                    //region
                    buttons.get("fir").setSize(192, 192);
                    buttons.get("fir").setPosition(688, -582 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("fir")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("firFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Linden")) {
                    //region
                    buttons.get("linden").setSize(192, 192);
                    buttons.get("linden").setPosition(896, -582 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("linden")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("lindenFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Maple")) {
                    //region
                    buttons.get("maple").setSize(192, 192);
                    buttons.get("maple").setPosition(1104, -582 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("maple")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("mapleFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Oak")) {
                    //region
                    buttons.get("oak").setSize(192, 192);
                    buttons.get("oak").setPosition(64, -790 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("oak")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("oakFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Pine")) {
                    //region
                    buttons.get("pine").setSize(192, 192);
                    buttons.get("pine").setPosition(272, -790 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("pine")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("pineFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Poplar")) {
                    //region
                    buttons.get("poplar").setSize(192, 192);
                    buttons.get("poplar").setPosition(480, -790 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("poplar")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("poplarFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Spruce")) {
                    //region
                    buttons.get("spruce").setSize(192, 192);
                    buttons.get("spruce").setPosition(688, -790 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("spruce")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("spruceFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Willow")) {
                    //region
                    buttons.get("willow").setSize(192, 192);
                    buttons.get("willow").setPosition(896, -790 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("willow")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("willowFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("BabaYaga")) {
                    //region
                    buttons.get("babaYaga").setSize(192, 192);
                    buttons.get("babaYaga").setPosition(1536, -544 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("babaYaga")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("babaYagaFull")));
                    labelDescription();
                    //endregion
                } else if (name.equals("Duck")) {
                    //region
                    buttons.get("duck").setSize(192, 128);
                    buttons.get("duck").setPosition(128, -352 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("duck")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("duckFull")));
                    text.get("textScoreFood").setText(new StringBuilder("+1"));
                    isFood = true;
                    isXP = false;
                    labelDescription();
                    //endregion
                } else if (name.equals("Goat")) {
                    //region
                    buttons.get("goat").setSize(192, 128);
                    buttons.get("goat").setPosition(336, -352 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("goat")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("goatFull")));
                    text.get("textScoreFood").setText(new StringBuilder("+2"));
                    isFood = true;
                    isXP = false;
                    labelDescription();
                    //endregion
                } else if (name.equals("Pig")) {
                    //region
                    buttons.get("pig").setSize(192, 128);
                    buttons.get("pig").setPosition(544, -352 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("pig")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("pigFull")));
                    text.get("textScoreFood").setText(new StringBuilder("+4"));
                    isFood = true;
                    isXP = false;
                    labelDescription();
                    //endregion
                } else if (name.equals("Kine")) {
                    //region
                    buttons.get("kine").setSize(192, 128);
                    buttons.get("kine").setPosition(752, -352 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("kine")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("kineFull")));
                    text.get("textScoreFood").setText(new StringBuilder("+6"));
                    isFood = true;
                    isXP = false;
                    labelDescription();
                    //endregion
                } else if (name.equals("CasketXP")) {
                    //region
                    buttons.get("CasketXP").setSize(128, 128);
                    buttons.get("CasketXP").setPosition(400, -512 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("CasketXP")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("CasketXPFull")));
                    text.get("textScoreFood").setText(new StringBuilder("+50-200 XP"));
                    isFood = false;
                    isXP = true;
                    labelDescription();
                    //endregion
                } else if (name.equals("CasketXam")) {
                    //region
                    buttons.get("CasketXam").setSize(128, 128);
                    buttons.get("CasketXam").setPosition(544, -512 + scroll);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    text.get("text").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("CasketXam")));
                    text.get("textFull").setText(new StringBuilder(RuDragonGame.getLanguage().text.get("CasketXamFull")));
                    text.get("textScoreFood").setText(new StringBuilder("+50-200"));
                    isFood = true;
                    isXP = false;
                    labelDescription();
                    //endregion
                }
            }
        }
    }
}