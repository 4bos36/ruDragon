package studio.rashka.Screen.module;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.StringBuilder;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import studio.rashka.Lib.ScrollHandler;
import studio.rashka.Models.RuDragon;
import studio.rashka.Models.food.Duck;
import studio.rashka.Models.obstacle.Casket;
import studio.rashka.Models.obstacle.birds.Buzzard;
import studio.rashka.Models.obstacle.birds.Eagle;
import studio.rashka.Models.obstacle.birds.Falcon;
import studio.rashka.Models.obstacle.birds.Hawk;
import studio.rashka.Models.obstacle.birds.Korshun;
import studio.rashka.Models.obstacle.birds.Orlan;
import studio.rashka.Models.obstacle.birds.Osprey;
import studio.rashka.Models.obstacle.people.BabaYaga;
import studio.rashka.RuDragonGame;
import studio.rashka.Screen.module.scroll.Earth;
import studio.rashka.Screen.module.scroll.Field;
import studio.rashka.Screen.module.scroll.Mountains;
import studio.rashka.Screen.module.scroll.Scene;

public class Games {

    private Stage stageResult, stageAchievements;
    private Random random;

    private boolean showAchievements = false;
    private float timeHide = 0;
    private int bonusScore = 0;

    private ScrollHandler scroll;
    private Field frontField, backField;
    private Earth frontEarth, backEarth;
    private Mountains frontMountains, backMountains;

    private Scene frontScene, bachScene;
    private Scenes scenes;
    private int randomScene, randomScene2;

    public ArrayList<Label> textBasic;
    private Map<String, LabelStyle> textStyles;

    private RuDragon ruDragon;

    private ArrayList<Duck> ducks; // еда
    private BabaYaga babaYaga; // люди

    private Buzzard buzzard; // птицы
    private Eagle eagle;
    private Falcon falcon;
    private Hawk hawk;
    private Korshun korshun;
    private Orlan orlan;
    private Osprey osprey;

    private float score = 0,
            eating = 0, eatingBonus = 0,
            casketXP = 0,
            xp = 0, xpBonus = 0, hamBonus = 0;

    private boolean isHaker = false, superBonusXP = false, superBonusHam = false;

    private float scoreAntiHacker = 1350;
    private float eatingAntiHacker = 710;

    private float run, runs = 0, fly, flying = 0;
    private ParticleEffect bonus;

    public Games() {
        stageResult = new Stage();
        stageAchievements = new Stage();
        random = new Random();
        ruDragon = new RuDragon(64, 128);

        bonus = new ParticleEffect();
        bonus.load(Gdx.files.internal("particles/Bonus.p"), Gdx.files.internal("particles"));
        bonus.start();

        run = RuDragonGame.getPreference().loadRun();
        fly = RuDragonGame.getPreference().loadFly();

        ducks = new ArrayList<Duck>();
        for (int i = 0; i <= 9; i++) ducks.add(i, new Duck());

        babaYaga = new BabaYaga();

        buzzard = new Buzzard();
        eagle = new Eagle();
        falcon = new Falcon();
        hawk = new Hawk();
        korshun = new Korshun();
        orlan = new Orlan();
        osprey = new Osprey();

        scenes = new Scenes();

        textStyles = new HashMap<String, LabelStyle>();
        textStyles.put("Big", new LabelStyle(RuDragonGame.getFontTTF().getFont48(), Color.WHITE));
        textStyles.put("Small", new LabelStyle(RuDragonGame.getFontTTF().getFont32(), Color.WHITE));

        textBasic = new ArrayList<Label>();
        for (int i = 0; i <= 5; i++) textBasic.add(i, new Label("", textStyles.get("Big")));
        for (int i = 0; i <= 4; i++) stageResult.addActor(textBasic.get(i));

        stageAchievements.addActor(textBasic.get(5));
    }

    public void scrollGame() {
        scroll = new ScrollHandler(0);

        frontField = scroll.getFrontField();
        backField = scroll.getBackField();

        frontEarth = scroll.getFrontEarth();
        backEarth = scroll.getBackEarth();

        frontMountains = scroll.getFrontMountains();
        backMountains = scroll.getBackMountains();

        frontScene = scroll.getFrontScene();
        bachScene = scroll.getBackScene();
        randomScene = scroll.getRandomScene();
        randomScene2 = scroll.getRandomScene2();

        //scenes.loadScene(45);
        scenes.loadScene(randomScene);
        //scenes.loadScene2(20);
        scenes.loadScene2(randomScene2);
        randomFly();
    }

    private void newScene() {
        if (scroll.isAddScene()) {
            scroll.setAddScene(false);
            if (RuDragonGame.getPreference().isRoarDragon()) RuDragonGame.getPreference().setRoarDragon(false);
            randomScene = scroll.getRandomScene();
            randomScene2 = scroll.getRandomScene2();
            scenes.loadScene(randomScene);
            scenes.loadScene2(randomScene2);
            randomFly();
        }
        if (scroll.isAddSceneFly()) {
            scroll.setAddSceneFly(false);
            if (RuDragonGame.getPreference().isRoarDragon()) RuDragonGame.getPreference().setRoarDragon(false);
            randomFly();
        }
    }

    private void randomFly() {
        if (!babaYaga.isGo()) {
            if (random.nextInt(3) == 0) {
                babaYaga.setGo(true);
                babaYaga.setAddX(random.nextInt(RuDragonGame.WIDTH));
            }
        }
        if (!ducks.get(0).isGo()) {
            ducks.get(0).setGo(true);
            ducks.get(0).setAddX(random.nextInt(RuDragonGame.WIDTH));
        }
        if (!ducks.get(1).isGo()) {
            ducks.get(1).setGo(true);
            ducks.get(1).setAddX(random.nextInt(RuDragonGame.WIDTH));
        }
        if (!ducks.get(2).isGo()) {
            ducks.get(2).setGo(true);
            ducks.get(2).setAddX(random.nextInt(RuDragonGame.WIDTH));
        }
        if (!ducks.get(3).isGo()) {
            ducks.get(3).setGo(true);
            ducks.get(3).setAddX(random.nextInt(RuDragonGame.WIDTH));
        }
        if (!ducks.get(4).isGo()) {
            if (random.nextInt(2) == 0) {
                ducks.get(4).setGo(true);
                ducks.get(4).setAddX(random.nextInt(RuDragonGame.WIDTH));
            }
        }
        if (!ducks.get(5).isGo()) {
            if (random.nextInt(2) == 0) {
                ducks.get(5).setGo(true);
                ducks.get(5).setAddX(random.nextInt(RuDragonGame.WIDTH));
            }
        }
        if (!ducks.get(6).isGo()) {
            if (random.nextInt(2) == 0) {
                ducks.get(6).setGo(true);
                ducks.get(6).setAddX(random.nextInt(RuDragonGame.WIDTH));
            }
        }
        if (!ducks.get(7).isGo()) {
            if (random.nextInt(2) == 0) {
                ducks.get(7).setGo(true);
                ducks.get(7).setAddX(random.nextInt(RuDragonGame.WIDTH));
            }
        }
        if (!ducks.get(8).isGo()) {
            if (random.nextInt(3) == 0) {
                ducks.get(8).setGo(true);
                ducks.get(8).setAddX(random.nextInt(RuDragonGame.WIDTH));
            }
        }
        if (!ducks.get(9).isGo()) {
            if (random.nextInt(3) == 0) {
                ducks.get(9).setGo(true);
                ducks.get(9).setAddX(random.nextInt(RuDragonGame.WIDTH));
            }
        }
        if (!buzzard.isGo()) {
            if (random.nextInt(2) == 0) {
                buzzard.setGo(true);
                buzzard.setAddX(1024);
            }
        }
        if (!eagle.isGo()) {
            if (random.nextInt(2) == 0) {
                eagle.setGo(true);
                eagle.setAddX(1474);
            }
        }
        if (!falcon.isGo()) {
            if (random.nextInt(2) == 0) {
                falcon.setGo(true);
                falcon.setAddX(1924);
            }
        }
        if (!hawk.isGo()) {
            if (random.nextInt(2) == 0) {
                hawk.setGo(true);
                hawk.setAddX(2374);
            }
        }
        if (!korshun.isGo()) {
            if (random.nextInt(2) == 0) {
                korshun.setGo(true);
                korshun.setAddX(2824);
            }
        }
        if (!orlan.isGo()) {
            if (random.nextInt(2) == 0) {
                orlan.setGo(true);
                orlan.setAddX(3274);
            }
        }
        if (!osprey.isGo()) {
            if (random.nextInt(2) == 0) {
                osprey.setGo(true);
                osprey.setAddX(3724);
            }
        }
    }

    public void speedScore(float speed, float scoreCommon) {
        scenes.speedScene(speed);
        babaYaga.setSpeed(speed);
        buzzard.setSpeed(speed);
        eagle.setSpeed(speed);
        falcon.setSpeed(speed);
        hawk.setSpeed(speed);
        korshun.setSpeed(speed);
        orlan.setSpeed(speed);
        osprey.setSpeed(speed);
        //region Run
        if (ruDragon.getY() >= 128 && ruDragon.getY() < 200) {
            if (run < 500) {
                runs += scoreCommon;
                if ((500 - (run + runs)) <= 0 && RuDragonGame.getPreference().loadRunYes500() == 0) {
                    RuDragonGame.getPreference().saveRun(runs);
                    runs = 0;
                    eating += 250;
                    eatingAntiHacker += 250;
                    bonusRun();
                    showTextBonus();
                }
            }
            else if (run >= 500 && run < 4000 && RuDragonGame.getPreference().loadRunYes4000() == 0) {
                runs += scoreCommon;
                if ((4000 - (run + runs)) <= 0) {
                    RuDragonGame.getPreference().saveRun(runs);
                    runs = 0;
                    eating += 650;
                    eatingAntiHacker += 650;
                    bonusRun();
                    showTextBonus();
                }
            }
            else if (run >= 4000 && run < 9000) {
                runs += scoreCommon;
                if ((9000 - (run + runs)) <= 0 && RuDragonGame.getPreference().loadRunYes9000() == 0) {
                    RuDragonGame.getPreference().saveRun(runs);
                    runs = 0;
                    eating += 1300;
                    eatingAntiHacker += 1300;
                    bonusRun();
                    showTextBonus();
                }
            }
            else if (run >= 9000 && run < 15000) {
                runs += scoreCommon;
                if ((15000 - (run + runs)) <= 0 && RuDragonGame.getPreference().loadRunYes15000() == 0) {
                    RuDragonGame.getPreference().saveRun(runs);
                    runs = 0;
                    eating += 2100;
                    eatingAntiHacker += 2100;
                    bonusRun();
                    showTextBonus();
                }
            }
            else if (run >= 15000 && run < 32500) {
                runs += scoreCommon;
                if ((32500 - (run + runs)) <= 0 && RuDragonGame.getPreference().loadRunYes32500() == 0) {
                    RuDragonGame.getPreference().saveRun(runs);
                    runs = 0;
                    eating += 4750;
                    eatingAntiHacker += 4750;
                    bonusRun();
                    showTextBonus();
                }
            }
            else if (run >= 32500 && run < 48000) {
                runs += scoreCommon;
                if ((48000 - (run + runs)) <= 0 && RuDragonGame.getPreference().loadRunYes48000() == 0) {
                    RuDragonGame.getPreference().saveRun(runs);
                    runs = 0;
                    eating += 5850;
                    eatingAntiHacker += 5850;
                    bonusRun();
                    showTextBonus();
                }
            }
            else if (run >= 48000) {}
        }
        //endregion
        //region Fly
        else if (ruDragon.getY() > 200) {
            if (fly < 1000) {
                flying += scoreCommon;
                if ((1000 - (fly + flying)) <= 0 && RuDragonGame.getPreference().loadFlyYes1000() == 0) {
                    RuDragonGame.getPreference().saveFly(flying);
                    flying = 0;
                    eating += 250;
                    eatingAntiHacker += 250;
                    bonusFly();
                    showTextBonus();
                }
            }
            else if (fly >= 1000 && fly < 10000) {
                flying += scoreCommon;
                if ((10000 - (fly + flying)) <= 0 && RuDragonGame.getPreference().loadFlyYes10000() == 0) {
                    RuDragonGame.getPreference().saveFly(flying);
                    flying = 0;
                    eating += 650;
                    eatingAntiHacker += 650;
                    bonusFly();
                    showTextBonus();
                }
            }
            else if (fly >= 10000 && fly < 30000) {
                flying += scoreCommon;
                if ((30000 - (fly + flying)) <= 0 && RuDragonGame.getPreference().loadFlyYes30000() == 0) {
                    RuDragonGame.getPreference().saveFly(flying);
                    flying = 0;
                    eating += 1150;
                    eatingAntiHacker += 1150;
                    bonusFly();
                    showTextBonus();
                }
            }
            else if (fly >= 30000 && fly < 60000) {
                flying += scoreCommon;
                if ((60000 - (fly + flying)) <= 0 && RuDragonGame.getPreference().loadFlyYes60000() == 0) {
                    RuDragonGame.getPreference().saveFly(flying);
                    flying = 0;
                    eating += 2050;
                    eatingAntiHacker += 2050;
                    bonusFly();
                    showTextBonus();
                }
            }
            else if (fly >= 60000 && fly < 100000) {
                flying += scoreCommon;
                if ((100000 - (fly + flying)) <= 0 && RuDragonGame.getPreference().loadFlyYes100000() == 0) {
                    RuDragonGame.getPreference().saveFly(flying);
                    flying = 0;
                    eating += 3500;
                    eatingAntiHacker += 3500;
                    bonusFly();
                    showTextBonus();
                }
            }
            else if (fly >= 100000 && fly < 200000) {
                flying += scoreCommon;
                if ((200000 - (fly + flying)) <= 0 && RuDragonGame.getPreference().loadFlyYes200000() == 0) {
                    RuDragonGame.getPreference().saveFly(flying);
                    flying = 0;
                    eating += 5850;
                    eatingAntiHacker += 5850;
                    bonusFly();
                    showTextBonus();
                }
            }
            else if (fly >= 200000) {}
        }
        //endregion
    }

    public void collides() {
        //region Еда
        if (scenes.getCasket().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getCasket().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("casket").play();
                if (scenes.getCasket().getChoice() == 1) {
                    eating = eating + scenes.getCasket().getEATEN();
                    eatingAntiHacker = eatingAntiHacker + scenes.getCasket().getEATEN();
                    showTextBonus();
                }
                else if (scenes.getCasket().getChoice() == 0) casketXP += scenes.getCasket().getEATEN();
                scenes.getCasket().setEaten(true);
            }
        if (scenes.getKine().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getKine().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("kineEaten").play();
                eating = eating + scenes.getKine().getEATEN();
                eatingAntiHacker = eatingAntiHacker + scenes.getKine().getEATEN();
                scenes.getKine().setEaten(true);
                bonusGlutton();
                bonusKine();
                showTextBonus();
            }
        if (scenes.getPig().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getPig().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("pigEaten").play();
                eating = eating + scenes.getPig().getEATEN();
                eatingAntiHacker = eatingAntiHacker + scenes.getPig().getEATEN();
                scenes.getPig().setEaten(true);
                bonusGlutton();
                bonusPig();
                showTextBonus();
            }
        if (scenes.getGoat().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getGoat().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("goatEaten").play();
                eating = eating + scenes.getGoat().getEATEN();
                eatingAntiHacker = eatingAntiHacker + scenes.getGoat().getEATEN();
                scenes.getGoat().setEaten(true);
                bonusGlutton();
                bonusGoat();
                showTextBonus();
            }
        if (ducks.get(0).getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (ducks.get(0).collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duckEaten").play();
                eating = eating + ducks.get(0).getEATEN();
                eatingAntiHacker = eatingAntiHacker + ducks.get(0).getEATEN();
                ducks.get(0).setEaten(true);
                bonusGlutton();
                bonusDuck();
                showTextBonus();
            }
        if (ducks.get(1).getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (ducks.get(1).collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duckEaten").play();
                eating = eating + ducks.get(1).getEATEN();
                eatingAntiHacker = eatingAntiHacker + ducks.get(1).getEATEN();
                ducks.get(1).setEaten(true);
                bonusGlutton();
                bonusDuck();
                showTextBonus();
            }
        if (ducks.get(2).getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (ducks.get(2).collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duckEaten").play();
                eating = eating + ducks.get(2).getEATEN();
                eatingAntiHacker = eatingAntiHacker + ducks.get(2).getEATEN();
                ducks.get(2).setEaten(true);
                bonusGlutton();
                bonusDuck();
                showTextBonus();
            }
        if (ducks.get(3).getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (ducks.get(3).collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duckEaten").play();
                eating = eating + ducks.get(3).getEATEN();
                eatingAntiHacker = eatingAntiHacker + ducks.get(3).getEATEN();
                ducks.get(3).setEaten(true);
                bonusGlutton();
                bonusDuck();
                showTextBonus();
            }
        if (ducks.get(4).getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (ducks.get(4).collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duckEaten").play();
                eating = eating + ducks.get(4).getEATEN();
                eatingAntiHacker = eatingAntiHacker + ducks.get(4).getEATEN();
                ducks.get(4).setEaten(true);
                bonusGlutton();
                bonusDuck();
                showTextBonus();
            }
        if (ducks.get(5).getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (ducks.get(5).collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duckEaten").play();
                eating = eating + ducks.get(5).getEATEN();
                eatingAntiHacker = eatingAntiHacker + ducks.get(5).getEATEN();
                ducks.get(5).setEaten(true);
                bonusGlutton();
                bonusDuck();
                showTextBonus();
            }
        if (ducks.get(6).getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (ducks.get(6).collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duckEaten").play();
                eating = eating + ducks.get(6).getEATEN();
                eatingAntiHacker = eatingAntiHacker + ducks.get(6).getEATEN();
                ducks.get(6).setEaten(true);
                bonusGlutton();
                bonusDuck();
                showTextBonus();
            }
        if (ducks.get(7).getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (ducks.get(7).collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duckEaten").play();
                eating = eating + ducks.get(7).getEATEN();
                eatingAntiHacker = eatingAntiHacker + ducks.get(7).getEATEN();
                ducks.get(7).setEaten(true);
                bonusGlutton();
                bonusDuck();
                showTextBonus();
            }
        if (ducks.get(8).getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (ducks.get(8).collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duckEaten").play();
                eating = eating + ducks.get(8).getEATEN();
                eatingAntiHacker = eatingAntiHacker + ducks.get(8).getEATEN();
                ducks.get(8).setEaten(true);
                bonusGlutton();
                bonusDuck();
                showTextBonus();
            }
        if (ducks.get(9).getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (ducks.get(9).collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("duckEaten").play();
                eating = eating + ducks.get(9).getEATEN();
                eatingAntiHacker = eatingAntiHacker + ducks.get(9).getEATEN();
                ducks.get(9).setEaten(true);
                bonusGlutton();
                bonusDuck();
                showTextBonus();
            }
        //endregion
        //region Птицы и деревья
        if (scenes.getAsh().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getAsh().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                gameOver();
            }
        if (scenes.getAspen().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getAspen().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                gameOver();
            }
        if (scenes.getBirch().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getBirch().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                gameOver();
            }
        if (scenes.getFir().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getFir().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                gameOver();
            }
        if (scenes.getLinden().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getLinden().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                gameOver();
            }
        if (scenes.getMaple().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getMaple().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                gameOver();
            }
        if (scenes.getOak().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getOak().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                gameOver();
            }
        if (scenes.getPine().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getPine().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                gameOver();
            }
        if (scenes.getPoplar().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getPoplar().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                gameOver();
            }
        if (scenes.getSpruce().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getSpruce().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                gameOver();
            }
        if (scenes.getWillow().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getWillow().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("tree").play();
                gameOver();
            }

        if (babaYaga.getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (babaYaga.collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("babaYagaAttack").play();
                gameOver();
            }
        if (buzzard.getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (buzzard.collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("buzzardAttack").play();
                gameOver();
            }
        if (eagle.getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (eagle.collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("eagleAttack").play();
                gameOver();
            }
        if (falcon.getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (falcon.collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("falconAttack").play();
                gameOver();
            }
        if (hawk.getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (hawk.collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("hawkAttack").play();
                gameOver();
            }
        if (korshun.getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (korshun.collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("korshunAttack").play();
                gameOver();
            }
        if (orlan.getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (orlan.collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("orlanAttack").play();
                gameOver();
            }
        if (osprey.getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (osprey.collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("ospreyAttack").play();
                gameOver();
            }
        //endregion
        if (scenes.getRock1().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getRock1().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("rock").play();
                gameOver();
            }
        if (scenes.getRock2().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getRock2().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("rock").play();
                gameOver();
            }
        if (scenes.getRock3().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getRock3().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("rock").play();
                gameOver();
            }
        if (scenes.getRock4().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getRock4().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("rock").play();
                gameOver();
            }
        if (scenes.getRock5().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getRock5().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("rock").play();
                gameOver();
            }
        if (scenes.getRock6().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getRock6().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("rock").play();
                gameOver();
            }
        if (scenes.getRock7().getX() < ruDragon.getX() + ruDragon.textureRuDragon().getRegionWidth() + 100)
            if (scenes.getRock7().collides(ruDragon.getTail(), ruDragon.getPawsR1(), ruDragon.getPawsR2(), ruDragon.getPaws(), ruDragon.getHead(), ruDragon.getWings())) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().sounds.get("rock").play();
                gameOver();
            }
    }

    private void showTextBonus() {
        if (bonusScore > 0) textBasic.get(5).setText(new StringBuilder().append("+").append(bonusScore));
        else if (bonusScore == 0) textBasic.get(5).setText("+");
        textBasic.get(5).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textBasic.get(5).getPrefWidth() / 2, (RuDragonGame.HEIGHT - 90) * RuDragonGame.getRatioMonitorH());
    }

    private void bonusDuck() {
        if (RuDragonGame.getPreference().loadDuckLove() == 49) {
            eating += 30;
            eatingAntiHacker += 30;
            eatingBonus += 30;
            bonusScore += 30;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadDuckLove() == 249) {
            eating += 130;
            eatingAntiHacker += 130;
            eatingBonus += 130;
            bonusScore += 130;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadDuckLove() == 499) {
            eating += 360;
            eatingAntiHacker += 360;
            eatingBonus += 360;
            bonusScore += 360;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadDuckLove() == 799) {
            eating += 420;
            eatingAntiHacker += 420;
            eatingBonus += 420;
            bonusScore += 420;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadDuckLove() == 1249) {
            eating += 505;
            eatingAntiHacker += 505;
            eatingBonus += 505;
            bonusScore += 505;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadDuckLove() == 1804) {
            eating += 575;
            eatingAntiHacker += 575;
            eatingBonus += 575;
            bonusScore += 575;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadDuckLove() == 2499) {
            eating += 700;
            eatingAntiHacker += 700;
            eatingBonus += 700;
            bonusScore += 700;
            showAchievements = true;
        }
    }

    private void bonusGoat() {
        if (RuDragonGame.getPreference().loadGoatLove() == 34) {
            eating += 45;
            eatingAntiHacker += 45;
            eatingBonus += 45;
            bonusScore += 45;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadGoatLove() == 174) {
            eating += 145;
            eatingAntiHacker += 145;
            eatingBonus += 145;
            bonusScore += 145;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadGoatLove() == 299) {
            eating += 405;
            eatingAntiHacker += 405;
            eatingBonus += 405;
            bonusScore += 405;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadGoatLove() == 549) {
            eating += 485;
            eatingAntiHacker += 485;
            eatingBonus += 485;
            bonusScore += 485;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadGoatLove() == 779) {
            eating += 625;
            eatingAntiHacker += 625;
            eatingBonus += 625;
            bonusScore += 625;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadGoatLove() == 999) {
            eating += 757;
            eatingAntiHacker += 757;
            eatingBonus += 757;
            bonusScore += 757;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadGoatLove() == 1499) {
            eating += 1000;
            eatingAntiHacker += 1000;
            eatingBonus += 1000;
            bonusScore += 1000;
            showAchievements = true;
        }
    }

    private void bonusPig() {
        if (RuDragonGame.getPreference().loadPigLove() == 19) {
            eating += 60;
            eatingAntiHacker += 60;
            eatingBonus += 60;
            bonusScore += 60;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadPigLove() == 99) {
            eating += 190;
            eatingAntiHacker += 190;
            eatingBonus += 190;
            bonusScore += 190;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadPigLove() == 199) {
            eating += 470;
            eatingAntiHacker += 470;
            eatingBonus += 470;
            bonusScore += 470;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadPigLove() == 339) {
            eating += 640;
            eatingAntiHacker += 640;
            eatingBonus += 640;
            bonusScore += 640;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadPigLove() == 517) {
            eating += 805;
            eatingAntiHacker += 805;
            eatingBonus += 805;
            bonusScore += 805;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadPigLove() == 665) {
            eating += 950;
            eatingAntiHacker += 950;
            eatingBonus += 950;
            bonusScore += 950;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadPigLove() == 857) {
            eating += 1200;
            eatingAntiHacker += 1200;
            eatingBonus += 1200;
            bonusScore += 1200;
            showAchievements = true;
        }
    }

    private void bonusKine() {
        if (RuDragonGame.getPreference().loadKineLove() == 9) {
            eating += 75;
            eatingAntiHacker += 75;
            eatingBonus += 75;
            bonusScore += 75;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadKineLove() == 49) {
            eating += 215;
            eatingAntiHacker += 215;
            eatingBonus += 215;
            bonusScore += 215;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadKineLove() == 99) {
            eating += 505;
            eatingAntiHacker += 505;
            eatingBonus += 505;
            bonusScore += 505;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadKineLove() == 229) {
            eating += 830;
            eatingAntiHacker += 830;
            eatingBonus += 830;
            bonusScore += 830;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadKineLove() == 449) {
            eating += 1350;
            eatingAntiHacker += 1350;
            eatingBonus += 1350;
            bonusScore += 1350;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadKineLove() == 674) {
            eating += 1800;
            eatingAntiHacker += 1800;
            eatingBonus += 1800;
            bonusScore += 1800;
            showAchievements = true;
        }
    }

    private void bonusGlutton() {
        if (RuDragonGame.getPreference().loadGlutton() == 99) {
            eating += 35;
            eatingAntiHacker += 35;
            eatingBonus += 35;
            bonusScore += 35;
        }
        else if (RuDragonGame.getPreference().loadGlutton() == 999) {
            eating += 385;
            eatingAntiHacker += 385;
            eatingBonus += 385;
            bonusScore += 385;
        }
        else if (RuDragonGame.getPreference().loadGlutton() == 4999) {
            eating += 1615;
            eatingAntiHacker += 1615;
            eatingBonus += 1615;
            bonusScore += 1615;
        }
        else if (RuDragonGame.getPreference().loadGlutton() == 9399) {
            eating += 2010;
            eatingAntiHacker += 2010;
            eatingBonus += 2010;
            bonusScore += 2010;
        }
        else if (RuDragonGame.getPreference().loadGlutton() == 13999) {
            eating += 2750;
            eatingAntiHacker += 2750;
            eatingBonus += 2750;
            bonusScore += 2750;
        }
    }

    private void bonusLvl() {
        if (RuDragonGame.getPreference().loadLevel() == 1 && RuDragonGame.getPreference().loadLevelYes1() == 0) {
            RuDragonGame.getPreference().saveLevelYes(1, 1);
            eatingBonus += 50;
            bonusScore += 50;
            showAchievements = true;
        }
        if (RuDragonGame.getPreference().loadLevel() == 3 && RuDragonGame.getPreference().loadLevelYes3() == 0) {
            RuDragonGame.getPreference().saveLevelYes(3, 1);
            eatingBonus += 135;
            bonusScore += 135;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 5 && RuDragonGame.getPreference().loadLevelYes5() == 0) {
            RuDragonGame.getPreference().saveLevelYes(5, 1);
            eatingBonus += 250;
            bonusScore += 250;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 8 && RuDragonGame.getPreference().loadLevelYes8() == 0) {
            RuDragonGame.getPreference().saveLevelYes(8, 1);
            eatingBonus += 400;
            bonusScore += 400;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadLevel() == 10 && RuDragonGame.getPreference().loadLevelYes10() == 0) {
            RuDragonGame.getPreference().saveLevelYes(10, 1);
            eatingBonus += 550;
            bonusScore += 550;
            showAchievements = true;
        }
    }

    private void bonusFinish() {
        if (RuDragonGame.getPreference().loadStart() == 10) {
            eatingBonus += 30;
            bonusScore += 30;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadStart() == 100) {
            eatingBonus += 315;
            bonusScore += 315;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadStart() == 1000) {
            eatingBonus += 1210;
            bonusScore += 1210;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadStart() == 1800) {
            eatingBonus += 2100;
            bonusScore += 2100;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadStart() == 3500) {
            eatingBonus += 5800;
            bonusScore += 5800;
            showAchievements = true;
        }
    }

    private void bonusFly() {
        if (RuDragonGame.getPreference().loadFly() > 1000 && RuDragonGame.getPreference().loadFlyYes1000() == 0) {
            RuDragonGame.getPreference().saveFlyYes(1000, 1);
            eatingBonus += 250;
            bonusScore += 250;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadFly() == 10000 && RuDragonGame.getPreference().loadFlyYes10000() == 0) {
            RuDragonGame.getPreference().saveFlyYes(10000, 1);
            eatingBonus += 650;
            bonusScore += 650;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadFly() == 30000 && RuDragonGame.getPreference().loadFlyYes30000() == 0) {
            RuDragonGame.getPreference().saveFlyYes(30000, 1);
            eatingBonus += 1150;
            bonusScore += 1150;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadFly() == 60000 && RuDragonGame.getPreference().loadFlyYes60000() == 0) {
            RuDragonGame.getPreference().saveFlyYes(60000, 1);
            eatingBonus += 2050;
            bonusScore += 2050;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadFly() == 100000 && RuDragonGame.getPreference().loadFlyYes100000() == 0) {
            RuDragonGame.getPreference().saveFlyYes(100000, 1);
            eatingBonus += 3500;
            bonusScore += 3500;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadFly() == 200000 && RuDragonGame.getPreference().loadFlyYes200000() == 0) {
            RuDragonGame.getPreference().saveFlyYes(200000, 1);
            eatingBonus += 5850;
            bonusScore += 5850;
            showAchievements = true;
        }
    }

    private void bonusRun() {
        if (RuDragonGame.getPreference().loadRun() > 500 && RuDragonGame.getPreference().loadRunYes500() == 0) {
            RuDragonGame.getPreference().saveRunYes(500, 1);
            eatingBonus += 250;
            bonusScore += 250;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadRun() > 4000 && RuDragonGame.getPreference().loadRunYes4000() == 0) {
            RuDragonGame.getPreference().saveRunYes(4000, 1);
            eatingBonus += 650;
            bonusScore += 650;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadRun() > 9000 && RuDragonGame.getPreference().loadRunYes9000() == 0) {
            RuDragonGame.getPreference().saveRunYes(9000, 1);
            eatingBonus += 1300;
            bonusScore += 1300;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadRun() > 15000 && RuDragonGame.getPreference().loadRunYes15000() == 0) {
            RuDragonGame.getPreference().saveRunYes(15000, 1);
            eatingBonus += 2100;
            bonusScore += 2100;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadRun() > 32500 && RuDragonGame.getPreference().loadRunYes32500() == 0) {
            RuDragonGame.getPreference().saveRunYes(32500, 1);
            eatingBonus += 4750;
            bonusScore += 4750;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadRun() > 48000 && RuDragonGame.getPreference().loadRunYes48000() == 0) {
            RuDragonGame.getPreference().saveRunYes(48000, 1);
            eatingBonus += 5850;
            bonusScore += 5850;
            showAchievements = true;
        }
    }

    private void bonusBest() {
        if (RuDragonGame.getPreference().loadBest() == 1 && RuDragonGame.getPreference().loadBestYes1() == 0) {
            RuDragonGame.getPreference().saveBestYes(1, 1);
            eatingBonus += 15;
            bonusScore += 15;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadBest() == 5 && RuDragonGame.getPreference().loadBestYes5() == 0) {
            RuDragonGame.getPreference().saveBestYes(5, 1);
            eatingBonus += 430;
            bonusScore += 430;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadBest() == 10 && RuDragonGame.getPreference().loadBestYes10() == 0) {
            RuDragonGame.getPreference().saveBestYes(10, 1);
            eatingBonus += 2500;
            bonusScore += 2500;
            showAchievements = true;
        }
    }

    private void bonusBank() {
        if (RuDragonGame.getPreference().loadBank() >= 500 && RuDragonGame.getPreference().loadBankYes500() == 0) {
            RuDragonGame.getPreference().saveBankYes(500, 1);
            eatingBonus += 250;
            bonusScore += 250;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadBank() >= 1000 && RuDragonGame.getPreference().loadBankYes1000() == 0) {
            RuDragonGame.getPreference().saveBankYes(1000, 1);
            eatingBonus += 650;
            bonusScore += 650;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadBank() >= 1500 && RuDragonGame.getPreference().loadBankYes1500() == 0) {
            RuDragonGame.getPreference().saveBankYes(1500, 1);
            eatingBonus += 810;
            bonusScore += 810;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadBank() >= 3000 && RuDragonGame.getPreference().loadBankYes3000() == 0) {
            RuDragonGame.getPreference().saveBankYes(3000, 1);
            eatingBonus += 1250;
            bonusScore += 1250;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadBank() >= 5500 && RuDragonGame.getPreference().loadBankYes5500() == 0) {
            RuDragonGame.getPreference().saveBankYes(5500, 1);
            eatingBonus += 2085;
            bonusScore += 2085;
            showAchievements = true;
        }
        else if (RuDragonGame.getPreference().loadBank() >= 8350 && RuDragonGame.getPreference().loadBankYes8350() == 0) {
            RuDragonGame.getPreference().saveBankYes(8350, 1);
            eatingBonus += 4255;
            bonusScore += 4255;
            showAchievements = true;
        }
    }

    private void gameOver() {
        RuDragonGame.getPreference().setGameRun(false);
        RuDragonGame.getPreference().setGameOver(true);
        RuDragonGame.getPreference().setModalWindow(true);
        if (RuDragonGame.getPreference().loadMusic() == 1) {
            RuDragonGame.getMusicSound().sounds.get("gameover").play();
            RuDragonGame.getMusicSound().stopRun();
        }

        if (round(score, 1) >= round(scoreAntiHacker - 1351, 1) && round(score, 1) <= round(scoreAntiHacker - 1349, 1) && eating == eatingAntiHacker - 710) {
            isHaker = false;
            RuDragonGame.getPreference().saveBest(score);

            xp = (score + (eating - eatingBonus) * 2) / 2 + casketXP;
            if (!superBonusXP) xpBonus = xp + xp * RuDragonGame.getPreference().loadBonusXP() / 100;
            else xpBonus = xp + (xp * RuDragonGame.getPreference().loadBonusXP() + xp * 20) / 100;
            if (!superBonusHam) hamBonus = eating + eating * RuDragonGame.getPreference().loadBonusHam() / 100;
            else hamBonus = eating + (eating * RuDragonGame.getPreference().loadBonusHam() + eating * 20) / 100;

            bonusLvl();
            bonusFinish();
            bonusFly();
            bonusRun();
            bonusBest();
            bonusBank();

            if (showAchievements) showTextBonus();

            textBasic.get(0).setText(new StringBuilder().append(RuDragonGame.getLanguage().textMenu.get("flyResult")).append(round(score, 1)).append(" m"));
            textBasic.get(1).setText(new StringBuilder().append(round(RuDragonGame.getPreference().loadBestScore(), 1)).append(" m"));
            textBasic.get(2).setText(new StringBuilder().append("+").append((int) hamBonus));
            textBasic.get(3).setText(new StringBuilder().append("XP: ").append(round(xpBonus, 1)));
            textBasic.get(4).setText(RuDragonGame.getLanguage().textMenu.get("bonus"));

            textBasic.get(0).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textBasic.get(0).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 120) * RuDragonGame.getRatioMonitorH());
            textBasic.get(1).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textBasic.get(1).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 + 60) * RuDragonGame.getRatioMonitorH());
            textBasic.get(2).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textBasic.get(2).getPrefWidth(), (RuDragonGame.HEIGHT / 2 + 10) * RuDragonGame.getRatioMonitorH());
            textBasic.get(3).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textBasic.get(3).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 - 50) * RuDragonGame.getRatioMonitorH());
            textBasic.get(4).setPosition((RuDragonGame.WIDTH / 2 - 176) * RuDragonGame.getRatioMonitorW(), (RuDragonGame.HEIGHT / 2 - 115) * RuDragonGame.getRatioMonitorH());
        }
        else {
            isHaker = true;
            // если взломали
        }
    }

    public void saveResult() {
        if (!isHaker) RuDragonGame.getPreference().saveGameOver(1, score, (int) eatingBonus, (int) hamBonus, xpBonus, flying, runs);
    }

    public void activeSuperBonusXam() {
        superBonusHam = true;
        hamBonus = eating + (eating * RuDragonGame.getPreference().loadBonusHam() + eating * 20) / 100;
        textBasic.get(2).setText(new StringBuilder().append("+").append((int) hamBonus));
        textBasic.get(2).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textBasic.get(2).getPrefWidth(), (RuDragonGame.HEIGHT / 2 + 10) * RuDragonGame.getRatioMonitorH());
    }

    public void activeSuperBonusXP() {
        superBonusXP = true;
        xpBonus = xp + (xp * RuDragonGame.getPreference().loadBonusXP() + xp * 20) / 100;
        textBasic.get(3).setText(new StringBuilder().append("XP: ").append(round(xpBonus, 1)));
        textBasic.get(3).setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - textBasic.get(3).getPrefWidth() / 2, (RuDragonGame.HEIGHT / 2 - 50) * RuDragonGame.getRatioMonitorH());
    }

    private static float round(float number, int scale) { // округление числа
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
    }

    public void updateDragon(float deltaTime, float score) {
        if (Gdx.input.isTouched() && !RuDragonGame.getPreference().isFendOffDragon()) ruDragon.onFlyUp();
        if (Gdx.input.isTouched() && RuDragonGame.getPreference().isFendOffDragon()) ruDragon.onFendOff();
        ruDragon.update(deltaTime, score);
    }

    public void updateGames(float deltaTime) {
        newScene();
        scenes.updateScene(deltaTime);
        babaYaga.update(deltaTime);
        ducks.get(0).update(deltaTime);
        ducks.get(1).update(deltaTime);
        ducks.get(2).update(deltaTime);
        ducks.get(3).update(deltaTime);
        ducks.get(4).update(deltaTime);
        ducks.get(5).update(deltaTime);
        ducks.get(6).update(deltaTime);
        ducks.get(7).update(deltaTime);
        ducks.get(8).update(deltaTime);
        ducks.get(9).update(deltaTime);
        buzzard.update(deltaTime);
        eagle.update(deltaTime);
        falcon.update(deltaTime);
        hawk.update(deltaTime);
        korshun.update(deltaTime);
        orlan.update(deltaTime);
        osprey.update(deltaTime);
    }

    public void updateShowBonus(float deltaTime) {
        if (showAchievements) {
            bonus.update(deltaTime);
            timeHide += deltaTime;
            if (timeHide > 4) {
                showAchievements = false;
                timeHide = 0;
                bonusScore = 0;
                showTextBonus();
            }
        }
        if (bonus.isComplete() && !showAchievements) bonus.reset();
    }

    public void renderGames(SpriteBatch batch) {
        ducks.get(0).render(batch);
        ducks.get(1).render(batch);
        ducks.get(2).render(batch);
        ducks.get(3).render(batch);
        ducks.get(4).render(batch);
        ducks.get(5).render(batch);
        ducks.get(6).render(batch);
        ducks.get(7).render(batch);
        ducks.get(8).render(batch);
        ducks.get(9).render(batch);
        scenes.renderScene(batch);
        buzzard.render(batch);
        eagle.render(batch);
        falcon.render(batch);
        hawk.render(batch);
        babaYaga.render(batch);
        korshun.render(batch);
        orlan.render(batch);
        osprey.render(batch);
        ruDragon.render(batch);
    }

    public void renderShowBonus(SpriteBatch batch) {
        if (showAchievements) {
            bonus.setPosition(RuDragonGame.WIDTH / 2, RuDragonGame.HEIGHT - 128);
            bonus.draw(batch);
            batch.draw(RuDragonGame.getTextures().textureRegions.get("fon_lite"), RuDragonGame.WIDTH / 2 - 160, RuDragonGame.HEIGHT - 128, 320, 76);
            batch.draw(RuDragonGame.getTextures().textureRegions.get("ham"), RuDragonGame.WIDTH / 2 - 156, RuDragonGame.HEIGHT - 114, 48, 48);
            batch.draw(RuDragonGame.getTextures().textureRegions.get("ham"), RuDragonGame.WIDTH / 2 + 108, RuDragonGame.HEIGHT - 114, 48, 48);
        }
    }

    public RuDragon getRuDragon() {
        return ruDragon;
    }

    public float getEating() {
        return eating;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setScoreAntiHacker(float scoreAntiHacker) {
        this.scoreAntiHacker = scoreAntiHacker;
    }

    public ScrollHandler getScroll() {
        return scroll;
    }

    public Field getFrontField() {
        return frontField;
    }

    public Field getBackField() {
        return backField;
    }

    public Earth getFrontEarth() {
        return frontEarth;
    }

    public Earth getBackEarth() {
        return backEarth;
    }

    public Mountains getFrontMountains() {
        return frontMountains;
    }

    public Mountains getBackMountains() {
        return backMountains;
    }

    public Stage getStageResult() {
        return stageResult;
    }

    public Stage getStageAchievements() {
        if (showAchievements) return stageAchievements;
        else return null;
    }

    public boolean isHaker() {
        return isHaker;
    }

    public Scenes getScenes() {
        return scenes;
    }

    public void dispose() {
        try {
            ruDragon.dispose();
            bonus.dispose();
            stageResult.dispose();
            stageAchievements.dispose();
            ducks.clear();
            textBasic.clear();
            textStyles.clear();
            scenes.getCasket().dispose();
            scenes.getGoat().dispose();
            scenes.getPig().dispose();
            scenes.getKine().dispose();
            babaYaga.dispose();
            buzzard.dispose();
            eagle.dispose();
            falcon.dispose();
            hawk.dispose();
            korshun.dispose();
            orlan.dispose();
            osprey.dispose();

            scenes.getRock1().dispose();
            scenes.getRock2().dispose();
            scenes.getRock3().dispose();
            scenes.getRock4().dispose();
            scenes.getRock5().dispose();
            scenes.getRock6().dispose();
            scenes.getRock7().dispose();

            scenes.getAsh().dispose();
            scenes.getAspen().dispose();
            scenes.getBirch().dispose();
            scenes.getFir().dispose();
            scenes.getLinden().dispose();
            scenes.getMaple().dispose();
            scenes.getOak().dispose();
            scenes.getPine().dispose();
            scenes.getPoplar().dispose();
            scenes.getSpruce().dispose();
            scenes.getWillow().dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}