package studio.rashka.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.ArrayList;
import java.util.Random;

import studio.rashka.Lib.Game;
import studio.rashka.Lib.State;
import studio.rashka.Lib.TimeDay;
import studio.rashka.Models.obstacle.IslandDragon;
import studio.rashka.Models.obstacle.PirateShip;
import studio.rashka.RuDragonGame;
import studio.rashka.Screen.module.GameOver;
import studio.rashka.Screen.module.Games;
import studio.rashka.Screen.module.Menu;

public class MenuScreen extends State {

    private Games games;
    private Menu menu;
    private GameOver gameOver;
    private TimeDay timeDay;
    private PirateShip pirateShip;
    private IslandDragon islandDragon;

    private Stage stageRecord;
    private Label textRecord;

    private ArrayList<TextureRegion> field, clouds, mountains;
    private ArrayList<Integer> randomX, randomY;

    private float scoreCommon = 0;
    private float speed = 1;
    private float score = 0;
    private float scoreUpdateText = 0;
    private float scoreAntiHacker = 1350;

    private ParticleEffect finish;

    public MenuScreen(final Game game) {
        super(game);

        stageRecord = new Stage();
        textRecord = new Label(new StringBuilder().append(RuDragonGame.getLanguage().textMenu.get("record")).append(round(RuDragonGame.getPreference().loadBestScore(), 1)), new LabelStyle(RuDragonGame.getFontTTF().getFont32(), Color.RED));
        textRecord.setPosition(10 * RuDragonGame.getRatioMonitorW(), 46 * RuDragonGame.getRatioMonitorH());
        stageRecord.addActor(textRecord);

        menu = new Menu();
        games = new Games();
        gameOver = new GameOver();
        timeDay = new TimeDay();
        pirateShip = new PirateShip();
        islandDragon = new IslandDragon();

        finish = new ParticleEffect();
        finish.load(Gdx.files.internal("particles/Finish.p"), Gdx.files.internal("particles"));
        finish.start();

        games.scrollGame();

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
    }

    private void onSetting(SpriteBatch batch) {
        batch.draw(RuDragonGame.getTextures().textureRegions.get("white"), RuDragonGame.WIDTH / 2 - 720 / 2 + 97, RuDragonGame.HEIGHT / 2 - 320 / 2 - 3, 720 + 6, 320 + 6);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("black"), RuDragonGame.WIDTH / 2 - 720 / 2 + 100, RuDragonGame.HEIGHT / 2 - 320 / 2, 720, 320);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("settings"), RuDragonGame.WIDTH / 2 - 620 / 2 + 60, RuDragonGame.HEIGHT / 2 - 64 / 2 + 170);
    }

    private void onCredits(SpriteBatch batch) {
        batch.draw(RuDragonGame.getTextures().textureRegions.get("white"), RuDragonGame.WIDTH / 2 - 384 / 2 + 97, RuDragonGame.HEIGHT / 2 - 320 / 2 - 3, 384 + 6, 320 + 6);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("black"), RuDragonGame.WIDTH / 2 - 384 / 2 + 100, RuDragonGame.HEIGHT / 2 - 320 / 2, 384, 320);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("credits"), RuDragonGame.WIDTH / 2 - 576 / 2 + 100, RuDragonGame.HEIGHT / 2 - 64 / 2 + 170);
    }

    private void onExit(SpriteBatch batch) {
        batch.draw(RuDragonGame.getTextures().textureRegions.get("white"), RuDragonGame.WIDTH / 2 - 384 / 2 + 97, RuDragonGame.HEIGHT / 2 - 320 / 2 - 3, 384 + 6, 320 + 6);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("black"), RuDragonGame.WIDTH / 2 - 384 / 2 + 100, RuDragonGame.HEIGHT / 2 - 320 / 2, 384, 320);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("exit_icon"), RuDragonGame.WIDTH / 2 - 192 + 100, RuDragonGame.HEIGHT / 2 - 160, 192 * 2, 128 * 2);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("exit"), RuDragonGame.WIDTH / 2 - 320 / 2 + 100, RuDragonGame.HEIGHT / 2 - 64 / 2 + 170);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("yes"), RuDragonGame.WIDTH / 2 - 180, RuDragonGame.HEIGHT / 2 - 180);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("no"), RuDragonGame.WIDTH / 2 + 180, RuDragonGame.HEIGHT / 2 - 180);
    }

    private void onGameOver(SpriteBatch batch) {
        if (RuDragonGame.getPreference().isModalWindow()) {
            gameOver.onGameOver(true);
            RuDragonGame.getPreference().setModalWindow(false);
        }
        batch.draw(RuDragonGame.getTextures().textureRegions.get("white"), RuDragonGame.WIDTH / 2 - 384 / 2 - 3, RuDragonGame.HEIGHT / 2 - 320 / 2 - 3, 390, 326);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("black"), RuDragonGame.WIDTH / 2 - 384 / 2, RuDragonGame.HEIGHT / 2 - 320 / 2, 384, 320);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("gameover"), RuDragonGame.WIDTH / 2 - 384 / 2, RuDragonGame.HEIGHT / 2 + 170);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("menu"), RuDragonGame.WIDTH / 2 - 288, RuDragonGame.HEIGHT / 2 - 240);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("repeat"), RuDragonGame.WIDTH / 2 + 64, RuDragonGame.HEIGHT / 2 - 240);
    }

    private void onSpeedScore(float deltaTime) {
        if (speed < 1) {
            speed += 0.05f;
            scoreCommon = 0.8f * deltaTime;
        }
        else if (speed >= 1 && speed < 4) {
            speed += 0.0075f;
            scoreCommon = 2.5f * deltaTime;
        }
        else if (speed >= 4 && speed < 7){
            speed += 0.0042f;
            scoreCommon = 3.3f * deltaTime;
        }
        else if (speed >= 7 && speed < 9){
            speed += 0.0053f;
            scoreCommon = 4.2f * deltaTime;
        }
        else if (speed >= 9 && speed < 11){
            speed += 0.0066f;
            scoreCommon = 5.15f * deltaTime;
        }
        else if (speed >= 11 && speed < 13){
            speed += 0.0073f;
            scoreCommon = 5.9f * deltaTime;
        }
        else if (speed >= 13 && speed < 15) {
            speed += 0.008f;
            scoreCommon = 6.57f * deltaTime;
        }
        else if (speed >= 15) {
            speed += 0.0024f;
            scoreCommon = 6.88f * deltaTime;
        }
        score += scoreCommon;
        scoreAntiHacker += scoreCommon;
        games.speedScore(speed, scoreCommon);
        games.setScore(score);
        games.setScoreAntiHacker(scoreAntiHacker);
    }

    private static float round(float number, int scale) { // округление числа
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
    }

    @Override
    public void update(float deltaTime) throws NullPointerException {
        RuDragonGame.getRiver().update(deltaTime);
        menu.upMenu(deltaTime, games.getEating());
        games.updateShowBonus(deltaTime);
        islandDragon.update(deltaTime);
        if (RuDragonGame.getPreference().isGameRun()) {
            pirateShip.update(deltaTime);
            games.updateDragon(deltaTime, score);
            if (games.getRuDragon().getX() > 127) {
                games.getScroll().update(deltaTime, speed);
                games.updateGames(deltaTime);
                onSpeedScore(deltaTime);
                if (scoreUpdateText != games.getEating()) scoreUpdateText = games.getEating();
            }
            games.collides();
            timeDay.update(deltaTime);
            if (RuDragonGame.getPreference().isContinue()) RuDragonGame.getPreference().setIsContinue(false);
        }
        if (menu.getSettingMod().isSetLng()) menu.clearTextMenu();
        if (RuDragonGame.getPreference().isGameOver()) finish.update(deltaTime);
        if (gameOver.isSaveResult()) {
            games.saveResult();
            gameOver.setSaveResult(false);
        }
        if (gameOver.isSuperBonusXP()) {
            games.activeSuperBonusXP();
            gameOver.setSuperBonusXP(false);
            islandDragon.setActiveXP(true);
        }
        if (gameOver.isSuperBonusHam()) {
            games.activeSuperBonusXam();
            gameOver.setSuperBonusHam(false);
            islandDragon.setActiveXam(true);
        }
    }

    @Override
    public void render(SpriteBatch batch) throws NullPointerException {
        batch.begin();
        timeDay.render(batch);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("black"), 0, 100, RuDragonGame.WIDTH, 245);
        for (int i = 0; i < 7; i++) {
            batch.draw(mountains.get(i), 280 * i + games.getFrontMountains().getX(), games.getFrontMountains().getY(), games.getFrontMountains().getWidth(), games.getFrontMountains().getHeight());
            batch.draw(mountains.get(i), 280 * i + games.getBackMountains().getX(), games.getBackMountains().getY(), games.getBackMountains().getWidth(), games.getBackMountains().getHeight());
        }
        RuDragonGame.getRiver().render(batch);
        pirateShip.render(batch);
        islandDragon.render(batch);
        for (int i = 0; i <= 4; i++) {
            batch.draw(field.get(i), 560 * i + games.getFrontField().getX(), games.getFrontField().getY(), games.getFrontField().getWidth(), games.getFrontField().getHeight());
            batch.draw(field.get(i), 560 * i + games.getBackField().getX(), games.getBackField().getY(), games.getBackField().getWidth(), games.getBackField().getHeight());
        }
        for (int i = 0; i < 5; i++) batch.draw(clouds.get(i), randomX.get(i), RuDragonGame.HEIGHT - randomY.get(i) - 45);

        games.renderGames(batch);

        batch.draw(RuDragonGame.getTextures().textureRegions.get("earth"), games.getFrontEarth().getX(), games.getFrontEarth().getY(), games.getFrontEarth().getWidth(), games.getFrontEarth().getHeight());
        batch.draw(RuDragonGame.getTextures().textureRegions.get("earth"), games.getBackEarth().getX(), games.getBackEarth().getY(), games.getBackEarth().getWidth(), games.getBackEarth().getHeight());
        batch.draw(RuDragonGame.getTextures().textureRegions.get("ham"), 15, 64);
        RuDragonGame.getFontTTF().getFontIMG().draw(batch, String.valueOf((int) games.getEating()), 90, 120);
        batch.end();

        stageRecord.act();
        stageRecord.draw();

        batch.begin();
        RuDragonGame.getFontTTF().getFontIMG().draw(batch, "m " + round(score, 1), 10, 56);
        if (!RuDragonGame.getPreference().isGameRun()) batch.draw(RuDragonGame.getTextures().textureRegions.get("fon_lite"), 0, 0, RuDragonGame.WIDTH, RuDragonGame.HEIGHT);

        if (RuDragonGame.getPreference().isModalWindow() && menu.getSettingMod().isWindowOn()) onSetting(batch);
        else if (RuDragonGame.getPreference().isModalWindow() && menu.getExitMod().isWindowOn()) onExit(batch);
        else if (RuDragonGame.getPreference().isModalWindow() && menu.getCreditsMod().isWindowOn()) onCredits(batch);
        if (RuDragonGame.getPreference().isGameOver()) onGameOver(batch);
        batch.draw(RuDragonGame.getTextures().textureRegions.get("black"), -256 + menu.getMenuX(), 0, 256, RuDragonGame.HEIGHT);
        if (RuDragonGame.getPreference().isGameOver() && !games.isHaker()) {
            batch.draw(RuDragonGame.getTextures().textureRegions.get("best"),RuDragonGame.WIDTH / 2 - games.textBasic.get(1).getPrefWidth() / 2 - 84, RuDragonGame.HEIGHT / 2 + 40, 48, 48);
            batch.draw(RuDragonGame.getTextures().textureRegions.get("ham"), RuDragonGame.WIDTH / 2 + 16, RuDragonGame.HEIGHT / 2 - 20, 48, 48);
            if (gameOver.isActiveXP()) batch.draw(RuDragonGame.getTextures().textureRegions.get("xp_bonus_on"), RuDragonGame.WIDTH / 2 + 12, RuDragonGame.HEIGHT / 2 - 146, 64, 64);
            else batch.draw(RuDragonGame.getTextures().textureRegions.get("xp_bonus_off"), RuDragonGame.WIDTH / 2 + 12, RuDragonGame.HEIGHT / 2 - 146, 64, 64);
            if (gameOver.isActiveXam()) batch.draw(RuDragonGame.getTextures().textureRegions.get("ham_bonus_on"), RuDragonGame.WIDTH / 2 + 96, RuDragonGame.HEIGHT / 2 - 146, 64, 64);
            else batch.draw(RuDragonGame.getTextures().textureRegions.get("ham_bonus_off"), RuDragonGame.WIDTH / 2 + 96, RuDragonGame.HEIGHT / 2 - 146, 64, 64);
        }
        else if (RuDragonGame.getPreference().isGameOver() && games.isHaker()) batch.draw(RuDragonGame.getTextures().textureRegions.get("haker"), RuDragonGame.WIDTH / 2 - 128, RuDragonGame.HEIGHT / 2 - 128, 256, 256);
        games.renderShowBonus(batch);
        menu.renderMenuButtonStart(batch);
        batch.end();

        if (!RuDragonGame.getPreference().isGameOver()) {
            menu.getStage().act();
            menu.getStage().draw();
        }

        batch.begin();
        if (RuDragonGame.getPreference().isGameRun()) {
            if (RuDragonGame.getPreference().loadRoar() < 1 && !RuDragonGame.getPreference().isGameOver())
                batch.draw(RuDragonGame.getTextures().textureRegions.get("noActive"), RuDragonGame.WIDTH - 192, 40, 128, 128);
            if (!RuDragonGame.getPreference().loadFendOff() && !RuDragonGame.getPreference().isGameOver())
                batch.draw(RuDragonGame.getTextures().textureRegions.get("noActive"), RuDragonGame.WIDTH - 384, 40, 128, 128);
        }
        if (menu.getSettingMod().isWindowOn() || menu.getExitMod().isWindowOn() || menu.getCreditsMod().isWindowOn()) {
            batch.draw(RuDragonGame.getTextures().textureRegions.get("fon_lite"), 0, 0, 256, RuDragonGame.HEIGHT);
            batch.draw(RuDragonGame.getTextures().textureRegions.get("fon_lite"), RuDragonGame.WIDTH / 2 - 160, RuDragonGame.HEIGHT - 96, 96, 96);
            batch.draw(RuDragonGame.getTextures().textureRegions.get("fon_lite"), RuDragonGame.WIDTH / 2, RuDragonGame.HEIGHT - 96, 96, 96);
            batch.draw(RuDragonGame.getTextures().textureRegions.get("fon_lite"), RuDragonGame.WIDTH / 2 + 160, RuDragonGame.HEIGHT - 96, 96, 96);
            batch.draw(RuDragonGame.getTextures().textureRegions.get("fon_lite"), RuDragonGame.WIDTH / 2 + 320, RuDragonGame.HEIGHT - 96, 96, 96);
        } else {
            if (!RuDragonGame.getPreference().isGameRun() && !RuDragonGame.getPreference().isGameOver()) {
                batch.draw(RuDragonGame.getTextures().textureRegions.get("logo_game"), (RuDragonGame.WIDTH - 256) / 2 - 832 / 2 + menu.getMenuX(), RuDragonGame.HEIGHT / 2 - 448 / 2);
            }
        }
        batch.end();

        if (menu.getSettingMod().isWindowOn()) {
            menu.getSettingMod().getStageSetting().act();
            menu.getSettingMod().getStageSetting().draw();
        }
        else if (menu.getCreditsMod().isWindowOn()) {
            menu.getCreditsMod().getStageCredits().act();
            menu.getCreditsMod().getStageCredits().draw();
        }
        else if (menu.getExitMod().isWindowOn()) {
            menu.getExitMod().getStageExit().act();
            menu.getExitMod().getStageExit().draw();
        }
        if (RuDragonGame.getPreference().isGameOver()) {
            gameOver.getStageGameOver().act();
            gameOver.getStageGameOver().draw();
            games.getStageResult().act();
            games.getStageResult().draw();
            batch.begin();
            finish.setPosition(RuDragonGame.WIDTH / 2, RuDragonGame.HEIGHT / 2);
            finish.draw(batch);
            if (RuDragonGame.getPreference().loadLanguage().equals("ru")) batch.draw(RuDragonGame.getTextures().textureRegions.get("FeedbackRu"), RuDragonGame.WIDTH / 2 - 128, RuDragonGame.HEIGHT - 288);
            else if (RuDragonGame.getPreference().loadLanguage().equals("uk")) batch.draw(RuDragonGame.getTextures().textureRegions.get("FeedbackUk"), RuDragonGame.WIDTH / 2 - 128, RuDragonGame.HEIGHT - 288);
            else if (RuDragonGame.getPreference().loadLanguage().equals("be")) batch.draw(RuDragonGame.getTextures().textureRegions.get("FeedbackBe"), RuDragonGame.WIDTH / 2 - 128, RuDragonGame.HEIGHT - 288);
            else batch.draw(RuDragonGame.getTextures().textureRegions.get("FeedbackEn"), RuDragonGame.WIDTH / 2 - 128, RuDragonGame.HEIGHT - 288);
            if (RuDragonGame.getPreference().loadAngelBonus() == 0) batch.draw(RuDragonGame.getTextures().textureRegions.get("noActive"), RuDragonGame.WIDTH / 2 - 96, 64, 192, 192);
            if (RuDragonGame.getPreference().loadSuperBonusXP() == 0 || gameOver.isActiveXP()) batch.draw(RuDragonGame.getTextures().textureRegions.get("noActive"), RuDragonGame.WIDTH / 2 - 320, 64, 192, 192);
            if (RuDragonGame.getPreference().loadSuperBonusHam() == 0 || gameOver.isActiveXam()) batch.draw(RuDragonGame.getTextures().textureRegions.get("noActive"), RuDragonGame.WIDTH / 2 + 128, 64, 192, 192);
            batch.end();
        }

        if (games.getStageAchievements() != null) {
            games.getStageAchievements().act();
            games.getStageAchievements().draw();
        }
    }

    @Override
    public void dispose() {
        try {
            stageRecord.dispose();
            textRecord.clear();

            finish.dispose();
            timeDay.dispose();
            menu.dispose();
            games.dispose();
            islandDragon.dispose();
            pirateShip.dispose();

            field.clear();
            clouds.clear();
            mountains.clear();
            randomX.clear();
            randomY.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}