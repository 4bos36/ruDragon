package studio.rashka.Screen.module;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

public class Credits {

    private Stage stage_credits;
    private Map<String, Buttons> imageButton;

    public Label developerTitle, developerName;
    private LabelStyle labelStyle;

    private boolean windowOn = false;

    public Credits() {
        stage_credits = new Stage();
        labelStyle = new LabelStyle(RuDragonGame.getFontTTF().getFont48(), Color.WHITE);
        imageButton = new HashMap<String, Buttons>();

        imageButton.put("Site", new Buttons("Site", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Site"), 96, 96, RuDragonGame.WIDTH / 2 - 44, RuDragonGame.HEIGHT / 2 - 128));
        imageButton.put("Google", new Buttons("Google", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Google"), 96, 96, RuDragonGame.WIDTH / 2 + 144, RuDragonGame.HEIGHT / 2 - 128));
        imageButton.put("Close", new Buttons("Close", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Close"), 64, 64, RuDragonGame.WIDTH / 2 + 256, RuDragonGame.HEIGHT / 2 + 128));

        imageButton.get("Site").addListener(new ButtonsInputListener(imageButton.get("Site").getName()));
        imageButton.get("Google").addListener(new ButtonsInputListener(imageButton.get("Google").getName()));
        imageButton.get("Close").addListener(new ButtonsInputListener(imageButton.get("Close").getName()));

        developerTitle = new Label(RuDragonGame.getLanguage().textMenu.get("developerTitle"), labelStyle);
        developerName = new Label(RuDragonGame.getLanguage().textMenu.get("developerName"), labelStyle);
    }

    public void onCredits(boolean onOff) {
        this.windowOn = onOff;
        if (onOff) {
            developerTitle.setText(RuDragonGame.getLanguage().textMenu.get("developerTitle"));
            developerName.setText(RuDragonGame.getLanguage().textMenu.get("developerName"));
            developerTitle.setPosition(
                    (RuDragonGame.WIDTH / 2 + 92) * RuDragonGame.getRatioMonitorW() - developerTitle.getPrefWidth() / 2,
                    (RuDragonGame.HEIGHT / 2 + 55) * RuDragonGame.getRatioMonitorH());
            developerName.setPosition(
                    (RuDragonGame.WIDTH / 2 + 92) * RuDragonGame.getRatioMonitorW() - developerName.getPrefWidth() / 2,
                    (RuDragonGame.HEIGHT / 2 + 5) * RuDragonGame.getRatioMonitorH());
            stage_credits.addActor(imageButton.get("Close"));
            stage_credits.addActor(imageButton.get("Site"));
            stage_credits.addActor(imageButton.get("Google"));
            stage_credits.addActor(developerTitle);
            stage_credits.addActor(developerName);
            Gdx.input.setInputProcessor(stage_credits);
        }
        else stage_credits.clear();
    }

    public Stage getStageCredits() {
        return stage_credits;
    }

    public boolean isWindowOn() {
        return windowOn;
    }

    public void dispose() {
        try {
            stage_credits.dispose();
            imageButton.clear();
            developerName.clear();
            developerTitle.clear();
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
            if (name.equals("Site")) {
                imageButton.get("Site").setSize(90, 90);
                imageButton.get("Site").setPosition(RuDragonGame.WIDTH / 2 - 41, RuDragonGame.HEIGHT / 2 - 125);
            } else if (name.equals("Google")) {
                imageButton.get("Google").setSize(90, 90);
                imageButton.get("Google").setPosition(RuDragonGame.WIDTH / 2 + 147, RuDragonGame.HEIGHT / 2 - 125);
            }
            return true;
        }

        @Override
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
            if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);

            if (name.equals("Site")) {
                imageButton.get("Site").setSize(96, 96);
                imageButton.get("Site").setPosition(RuDragonGame.WIDTH / 2 - 44, RuDragonGame.HEIGHT / 2 - 128);
                Gdx.net.openURI("http://rashka.studio/");
            } else if (name.equals("Google")) {
                imageButton.get("Google").setSize(96, 96);
                imageButton.get("Google").setPosition(RuDragonGame.WIDTH / 2 + 144, RuDragonGame.HEIGHT / 2 - 128);
                Gdx.net.openURI("https://play.google.com/store/apps/details?id=studio.rashka");
            } else if (name.equals("Close")) {
                onCredits(false);
                RuDragonGame.getPreference().setOnWindow(true);
                RuDragonGame.getPreference().setModalWindow(false);
            }
        }
    }
}