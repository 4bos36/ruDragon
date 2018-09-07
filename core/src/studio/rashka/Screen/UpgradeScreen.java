package studio.rashka.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.ArrayList;
import java.util.Random;

import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;
import studio.rashka.Lib.TimeDay;
import studio.rashka.Lib.btn.Buttons;
import studio.rashka.Models.RuDragon;
import studio.rashka.Models.food.Goat;
import studio.rashka.Models.food.Kine;
import studio.rashka.Models.food.Pig;
import studio.rashka.Models.obstacle.birds.Eagle;
import studio.rashka.Models.obstacle.birds.Hawk;
import studio.rashka.Models.obstacle.birds.Osprey;
import studio.rashka.RuDragonGame;

public class UpgradeScreen extends State {

    private Stage stage;
    private TimeDay timeDay;

    private Buttons home, back, getXP, getHAM;

    private Label scoreBank, timeBonusXP, timeBonusHAM;

    private ArrayList<TextureRegion> clouds, mountains;
    private ArrayList<Integer> randomX, randomY;
    private ParticleEffect bonfire, bonfire2;

    private Eagle eagle;
    private Hawk hawk;
    private Osprey osprey;
    private Goat goat;
    private Pig pig;
    private Kine kine;

    private static int bank;

    public UpgradeScreen(final Game game) {
        super(game);
        stage = new Stage();
        timeDay = new TimeDay();
        if (RuDragonGame.getPreference().loadBuyBirds() >= 1) eagle = new Eagle();
        if (RuDragonGame.getPreference().loadBuyBirds() >= 2) hawk = new Hawk();
        if (RuDragonGame.getPreference().loadBuyBirds() == 3) osprey = new Osprey();

        if (RuDragonGame.getPreference().loadBuyLiveStock() >= 1) goat = new Goat();
        if (RuDragonGame.getPreference().loadBuyLiveStock() >= 2) pig = new Pig();
        if (RuDragonGame.getPreference().loadBuyLiveStock() == 3) kine = new Kine();

        bonfire = new ParticleEffect();
        bonfire2 = new ParticleEffect();
        bonfire.load(Gdx.files.internal("particles/Bonfire.p"), Gdx.files.internal("particles"));
        bonfire2.load(Gdx.files.internal("particles/Bonfire.p"), Gdx.files.internal("particles"));
        bonfire.start();
        bonfire2.start();

        bank = RuDragonGame.getPreference().loadBank();

        scoreBank = new Label(new StringBuilder().append(bank), new LabelStyle(RuDragonGame.getFontTTF().getFont48(), Color.WHITE));
        scoreBank.setPosition(448 * RuDragonGame.getRatioMonitorW() - scoreBank.getPrefWidth() / 2, 78 * RuDragonGame.getRatioMonitorH());

        randomX = new ArrayList<Integer>();
        randomY = new ArrayList<Integer>();

        clouds = new ArrayList<TextureRegion>();
        mountains = new ArrayList<TextureRegion>();

        for (int i = 0; i < 5; i++) clouds.add(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("cloud")));
        for (int i = 0; i < 7; i++) mountains.add(new TextureRegion(RuDragonGame.getTextures().textureRegions.get("mountains")));

        for (int i = 0; i < 5; i++) randomX.add(new Random().nextInt(RuDragonGame.WIDTH));
        for (int i = 0; i < 5; i++) randomY.add(new Random().nextInt(RuDragonGame.HEIGHT / 4));

        back = new Buttons("Back", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 128, 128, 64, 128);
        back.addListener(new ButtonsInputListener(back.getName()));

        home = new Buttons("Home", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 160, 160, RuDragonGame.WIDTH / 2 + 70, 135);
        home.addListener(new ButtonsInputListener(home.getName()));

        getXP = new Buttons("getXP", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("I_best"), 96, 96, RuDragonGame.WIDTH - 256, RuDragonGame.HEIGHT - 128);
        getXP.addListener(new ButtonsInputListener(getXP.getName()));

        getHAM = new Buttons("getHAM", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Love_eat"), 96, 96, RuDragonGame.WIDTH - 128, RuDragonGame.HEIGHT - 128);
        getHAM.addListener(new ButtonsInputListener(getHAM.getName()));

        if (RuDragonGame.getTimeBonus().getTimeXP() == 0) timeBonusXP = new Label("+15", new LabelStyle(RuDragonGame.getFontTTF().getFont25(), Color.RED));
        else timeBonusXP = new Label("" + RuDragonGame.getTimeBonus().getTimeXP(), new LabelStyle(RuDragonGame.getFontTTF().getFont25(), Color.RED));
        timeBonusXP.setPosition((RuDragonGame.WIDTH - 208) * RuDragonGame.getRatioMonitorW() - timeBonusXP.getPrefWidth() / 2, (RuDragonGame.HEIGHT - 160) * RuDragonGame.getRatioMonitorH());

        if (RuDragonGame.getTimeBonus().getTimeHAM() == 0) timeBonusHAM = new Label("+25", new LabelStyle(RuDragonGame.getFontTTF().getFont25(), Color.RED));
        else timeBonusHAM = new Label("" + RuDragonGame.getTimeBonus().getTimeHAM(), new LabelStyle(RuDragonGame.getFontTTF().getFont25(), Color.RED));
        timeBonusHAM.setPosition((RuDragonGame.WIDTH - 80) * RuDragonGame.getRatioMonitorW() - timeBonusHAM.getPrefWidth() / 2, (RuDragonGame.HEIGHT - 160) * RuDragonGame.getRatioMonitorH());

        stage.addActor(back);
        stage.addActor(home);
        stage.addActor(getXP);
        stage.addActor(getHAM);
        stage.addActor(scoreBank);
        stage.addActor(timeBonusXP);
        stage.addActor(timeBonusHAM);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float deltaTime) {
        bonfire.update(deltaTime);
        bonfire2.update(deltaTime);
        timeDay.update(deltaTime);
        RuDragonGame.getRiver().update(deltaTime);
        if (RuDragonGame.getPreference().loadBuyBirds() >= 1) eagle.homeUpdate(deltaTime);
        if (RuDragonGame.getPreference().loadBuyBirds() >= 2) hawk.homeUpdate(deltaTime);
        if (RuDragonGame.getPreference().loadBuyBirds() == 3) osprey.homeUpdate(deltaTime);
        if (RuDragonGame.getPreference().loadBuyLiveStock() >= 1) goat.homeLoad(deltaTime);
        if (RuDragonGame.getPreference().loadBuyLiveStock() >= 2) pig.homeLoad(deltaTime);
        if (RuDragonGame.getPreference().loadBuyLiveStock() == 3) kine.homeLoad(deltaTime);

        if (RuDragonGame.getTimeBonus().isUpTimeXP()) {
            if (RuDragonGame.getTimeBonus().getTimeXP() == 0) timeBonusXP.setText("+15");
            else timeBonusXP.setText("" + RuDragonGame.getTimeBonus().getTimeXP());
            timeBonusXP.setPosition((RuDragonGame.WIDTH - 208) * RuDragonGame.getRatioMonitorW() - timeBonusXP.getPrefWidth() / 2, (RuDragonGame.HEIGHT - 160) * RuDragonGame.getRatioMonitorH());

            RuDragonGame.getTimeBonus().setUpTimeXP();
        }
        if (RuDragonGame.getTimeBonus().isUpTimeHAM()) {
            if (RuDragonGame.getTimeBonus().getTimeHAM() == 0) timeBonusHAM.setText("+25");
            else timeBonusHAM.setText("" + RuDragonGame.getTimeBonus().getTimeHAM());
            timeBonusHAM.setPosition((RuDragonGame.WIDTH - 80) * RuDragonGame.getRatioMonitorW() - timeBonusHAM.getPrefWidth() / 2, (RuDragonGame.HEIGHT - 160) * RuDragonGame.getRatioMonitorH());

            RuDragonGame.getTimeBonus().setUpTimeHAM();
        }
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

        batch.draw(RuDragonGame.getTextures().textureRegions.get("mountain-home"), RuDragonGame.WIDTH / 2 - 640 / 2, 128, 640 * 1.5f, 384 * 1.5f);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("pointer-menu"), 64, 128);
        bonfire.setPosition(700, 186);
        bonfire.draw(batch);
        bonfire2.setPosition(1097, 182);
        bonfire2.draw(batch);

        if (RuDragonGame.getPreference().loadBuyTree() >= 1) batch.draw(RuDragonGame.getTextures().textureRegions.get("oak"), RuDragonGame.WIDTH / 2 + 326, 128, 652 / 1.5f, 448 / 1.5f);
        if (RuDragonGame.getPreference().loadBuyTree() >= 2) batch.draw(RuDragonGame.getTextures().textureRegions.get("aspen"), 256, 128, 206 / 1.5f, 400 / 1.5f);
        if (RuDragonGame.getPreference().loadBuyTree() == 3) batch.draw(RuDragonGame.getTextures().textureRegions.get("birch"), 832, 128, 143 / 1.5f, 352 / 1.5f);
        if (RuDragonGame.getPreference().loadBuyLiveStock() >= 1) goat.renderUpgrade(batch);
        if (RuDragonGame.getPreference().loadBuyLiveStock() >= 2) pig.renderUpgrade(batch);
        if (RuDragonGame.getPreference().loadBuyLiveStock() == 3) kine.renderUpgrade(batch);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("pointer-home"), RuDragonGame.WIDTH / 2 + 208, 128);
        if (bank < 25) batch.draw(RuDragonGame.getTextures().textureRegions.get("fence"), 384, 128);
        else if (bank >= 25 && bank < 100) batch.draw(RuDragonGame.getTextures().textureRegions.get("fence2"), 384, 128);
        else if (bank >= 100 && bank < 300) batch.draw(RuDragonGame.getTextures().textureRegions.get("fence3"), 384, 128);
        else if (bank >= 300 && bank < 600) batch.draw(RuDragonGame.getTextures().textureRegions.get("fence3"), 384, 128);
        else if (bank >= 600 && bank < 1100) batch.draw(RuDragonGame.getTextures().textureRegions.get("fence4"), 384, 128);
        else if (bank >= 1100 && bank < 1600) batch.draw(RuDragonGame.getTextures().textureRegions.get("fence5"), 320, 128);
        else if (bank >= 1600) batch.draw(RuDragonGame.getTextures().textureRegions.get("fence6"), 320, 128);

        if (RuDragonGame.getPreference().loadBuyBirds() >= 1) eagle.render(batch);
        if (RuDragonGame.getPreference().loadBuyBirds() >= 2) hawk.render(batch);
        if (RuDragonGame.getPreference().loadBuyBirds() == 3) osprey.render(batch);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("earth"), 0, 0);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("ham"), 448 + 48, 78, 48, 48);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        try {
            timeDay.dispose();
            stage.dispose();
            back.clear();
            home.clear();
            getXP.clear();
            getHAM.clear();
            scoreBank.clear();
            timeBonusXP.clear();
            timeBonusHAM.clear();

            bonfire.dispose();
            bonfire2.dispose();

            clouds.clear();
            mountains.clear();
            randomX.clear();
            randomY.clear();

            if (RuDragonGame.getPreference().loadBuyBirds() >= 1) eagle.dispose();
            if (RuDragonGame.getPreference().loadBuyBirds() >= 2) hawk.dispose();
            if (RuDragonGame.getPreference().loadBuyBirds() == 3) osprey.dispose();
            if (RuDragonGame.getPreference().loadBuyLiveStock() >= 1) goat.dispose();
            if (RuDragonGame.getPreference().loadBuyLiveStock() >= 2) pig.dispose();
            if (RuDragonGame.getPreference().loadBuyLiveStock() == 3) kine.dispose();
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

            if (name.equals("Back")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                RuDragonGame.getGame().set(new MenuScreen(game));
            } else if (name.equals("Home")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                RuDragonGame.getGame().set(new UpgradeMenuScreen(game));
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

                    scoreBank.setText(new StringBuilder().append(RuDragonGame.getPreference().loadBank()));
                    scoreBank.setPosition(448 * RuDragonGame.getRatioMonitorW() - scoreBank.getPrefWidth() / 2, 78 * RuDragonGame.getRatioMonitorH());
                }
            }
        }
    }
}