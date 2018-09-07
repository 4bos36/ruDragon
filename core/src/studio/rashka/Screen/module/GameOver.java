package studio.rashka.Screen.module;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.HashMap;
import java.util.Map;

import studio.rashka.Lib.btn.Buttons;
import studio.rashka.RuDragonGame;
import studio.rashka.Screen.MenuScreen;

public class GameOver {

    private Stage stage;
    private Map<String, Buttons> buttons;
    private Map<String, Label> text;

    private boolean saveResult = false, superBonusXP = false, superBonusHam = false,
            activeXP = false, activeXam = false;

    public GameOver() {
        stage = new Stage();
        buttons = new HashMap<String, Buttons>();
        text = new HashMap<String, Label>();

        buttons.put("Menu", new Buttons("Menu", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 192, 64, RuDragonGame.WIDTH / 2 - 288, RuDragonGame.HEIGHT / 2 - 240));
        buttons.put("Repeat", new Buttons("Repeat", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 256, 64, RuDragonGame.WIDTH / 2 + 64, RuDragonGame.HEIGHT / 2 - 240));
        buttons.put("Feedback", new Buttons("Feedback", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 256, 128, RuDragonGame.WIDTH / 2 - 128, RuDragonGame.HEIGHT - 288));
        buttons.put("Continue", new Buttons("Continue", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Angel"), 192, 192, RuDragonGame.WIDTH / 2 - 96, 64));
        buttons.put("BonusXP", new Buttons("BonusXP", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("BonusXP"), 192, 192, RuDragonGame.WIDTH / 2 - 320, 64));
        buttons.put("BonusHam", new Buttons("BonusHam", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("BonusHam"), 192, 192, RuDragonGame.WIDTH / 2 + 128, 64));

        buttons.get("Menu").addListener(new ButtonsInputListener(buttons.get("Menu").getName()));
        buttons.get("Repeat").addListener(new ButtonsInputListener(buttons.get("Repeat").getName()));
        buttons.get("Feedback").addListener(new ButtonsInputListener(buttons.get("Feedback").getName()));
        buttons.get("Continue").addListener(new ButtonsInputListener(buttons.get("Continue").getName()));
        buttons.get("BonusXP").addListener(new ButtonsInputListener(buttons.get("BonusXP").getName()));
        buttons.get("BonusHam").addListener(new ButtonsInputListener(buttons.get("BonusHam").getName()));

        numberBonus();
    }

    private void numberBonus() {
        Label.LabelStyle style = new Label.LabelStyle(RuDragonGame.getFontTTF().getFont38(), Color.WHITE);
        text.put("NumberBonusXP", new Label(String.valueOf(RuDragonGame.getPreference().loadSuperBonusXP()), style));
        text.put("NumberBonusHam", new Label(String.valueOf(RuDragonGame.getPreference().loadSuperBonusHam()), style));
        text.put("NumberBonusAngel", new Label(String.valueOf(RuDragonGame.getPreference().loadAngelBonus()), style));

        text.get("NumberBonusXP").setPosition((RuDragonGame.WIDTH / 2 - 224) * RuDragonGame.getRatioMonitorW() - text.get("NumberBonusXP").getPrefWidth() / 2, 30 * RuDragonGame.getRatioMonitorH());
        text.get("NumberBonusHam").setPosition((RuDragonGame.WIDTH / 2 + 224) * RuDragonGame.getRatioMonitorW() - text.get("NumberBonusHam").getPrefWidth() / 2, 30 * RuDragonGame.getRatioMonitorH());
        text.get("NumberBonusAngel").setPosition(RuDragonGame.WIDTH / 2 * RuDragonGame.getRatioMonitorW() - text.get("NumberBonusAngel").getPrefWidth() / 2, 30 * RuDragonGame.getRatioMonitorH());
    }

    public void onGameOver(boolean onOff) {
        if (onOff) {
            stage.addActor(buttons.get("Menu"));
            stage.addActor(buttons.get("Repeat"));
            stage.addActor(buttons.get("Feedback"));
            stage.addActor(buttons.get("Continue"));
            stage.addActor(buttons.get("BonusXP"));
            stage.addActor(buttons.get("BonusHam"));
            stage.addActor(text.get("NumberBonusXP"));
            stage.addActor(text.get("NumberBonusHam"));
            stage.addActor(text.get("NumberBonusAngel"));
            Gdx.input.setInputProcessor(stage);
        }
        else stage.clear();
    }

    public Stage getStageGameOver() {
        return stage;
    }

    public boolean isSaveResult() {
        return saveResult;
    }

    public boolean isSuperBonusXP() {
        return superBonusXP;
    }

    public boolean isSuperBonusHam() {
        return superBonusHam;
    }

    public void setSuperBonusXP(boolean superBonusXP) {
        this.superBonusXP = superBonusXP;
    }

    public void setSuperBonusHam(boolean superBonusHam) {
        this.superBonusHam = superBonusHam;
    }

    public void setSaveResult(boolean saveResult) {
        this.saveResult = saveResult;
    }

    public boolean isActiveXP() {
        return activeXP;
    }

    public boolean isActiveXam() {
        return activeXam;
    }

    public void dispose () {
        try {
            stage.dispose();
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
            if (name.equals("Menu") || name.equals("Repeat")) {
                saveResult = true;
            } else if (name.equals("Continue")) {
                if (RuDragonGame.getPreference().loadAngelBonus() >= 1) {
                    buttons.get("Continue").setSize(186, 186);
                    buttons.get("Continue").setPosition(RuDragonGame.WIDTH / 2 - 93, 67);
                }
            } else if (name.equals("BonusXP")) {
                if (RuDragonGame.getPreference().loadSuperBonusXP() >= 1 && !activeXP) {
                    buttons.get("BonusXP").setSize(186, 186);
                    buttons.get("BonusXP").setPosition(RuDragonGame.WIDTH / 2 - 317, 67);
                }
            } else if (name.equals("BonusHam")) {
                if (RuDragonGame.getPreference().loadSuperBonusHam() >= 1 && !activeXam) {
                    buttons.get("BonusHam").setSize(186, 186);
                    buttons.get("BonusHam").setPosition(RuDragonGame.WIDTH / 2 + 131, 67);
                }
            }
            return true;
        }

        @Override
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

            if (name.equals("Menu")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                onGameOver(false);
                RuDragonGame.getPreference().setOnWindow(true);
                RuDragonGame.getPreference().setGameRun(false);
                RuDragonGame.getPreference().setOnMenu(true);
                RuDragonGame.getPreference().setGameOver(false);
                RuDragonGame.getMusicSound().stopRun();
                if (RuDragonGame.getPreference().loadMusic() == 1) RuDragonGame.getMusicSound().startFon();
                RuDragonGame.getGame().set(new MenuScreen(RuDragonGame.getGame()));
            } else if (name.equals("Repeat")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                onGameOver(false);
                RuDragonGame.getPreference().setOnWindow(true);
                RuDragonGame.getPreference().setGameRun(true);
                RuDragonGame.getPreference().setOnMenu(false);
                RuDragonGame.getPreference().setGameOver(false);
                RuDragonGame.getMusicSound().stopRun();
                if (RuDragonGame.getPreference().loadMusic() == 1) {
                    RuDragonGame.getMusicSound().startRun();
                    RuDragonGame.getMusicSound().pauseFon();
                }
                RuDragonGame.getGame().set(new MenuScreen(RuDragonGame.getGame()));
            } else if (name.equals("Feedback")) {
                if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                Gdx.net.openURI("https://play.google.com/store/apps/details?id=studio.rashka");
            } else if (name.equals("Continue")) {
                if (RuDragonGame.getPreference().loadAngelBonus() >= 1) {
                    buttons.get("Continue").setSize(192, 192);
                    buttons.get("Continue").setPosition(RuDragonGame.WIDTH / 2 - 96, 64);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    RuDragonGame.getPreference().setIsContinue(true);
                    onGameOver(false);
                    RuDragonGame.getPreference().setOnWindow(true);
                    RuDragonGame.getPreference().setGameRun(true);
                    RuDragonGame.getPreference().setOnMenu(false);
                    RuDragonGame.getPreference().setGameOver(false);
                    RuDragonGame.getPreference().saveAngelBonus(1);
                    text.get("NumberBonusAngel").setText(new StringBuilder().append(RuDragonGame.getPreference().loadAngelBonus()));
                    if (RuDragonGame.getPreference().loadMusic() == 1) RuDragonGame.getMusicSound().startRun();
                }
            } else if (name.equals("BonusXP")) {
                if (RuDragonGame.getPreference().loadSuperBonusXP() >= 1 && !activeXP) {
                    buttons.get("BonusXP").setSize(192, 192);
                    buttons.get("BonusXP").setPosition(RuDragonGame.WIDTH / 2 - 320, 64);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    RuDragonGame.getPreference().saveSuperBonusXP(1);
                    text.get("NumberBonusXP").setText(new StringBuilder().append(RuDragonGame.getPreference().loadSuperBonusXP()));
                    superBonusXP = true;
                    activeXP = true;
                }
            } else if (name.equals("BonusHam")) {
                if (RuDragonGame.getPreference().loadSuperBonusHam() >= 1 && !activeXam) {
                    buttons.get("BonusHam").setSize(192, 192);
                    buttons.get("BonusHam").setPosition(RuDragonGame.WIDTH / 2 + 128, 64);
                    if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
                    if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);
                    RuDragonGame.getPreference().saveSuperBonusHam(1);
                    text.get("NumberBonusHam").setText(new StringBuilder().append(RuDragonGame.getPreference().loadSuperBonusHam()));
                    superBonusHam = true;
                    activeXam = true;
                }
            }
        }
    }
}