package studio.rashka.Screen.module;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;

import java.util.HashMap;
import java.util.Map;

import studio.rashka.Lib.btn.Buttons;
import studio.rashka.RuDragonGame;

public class Exit {

    private Stage stage_exit;
    private Map<String, Buttons> imageButton;

    private boolean windowOn = false;

    public Exit() {
        stage_exit = new Stage();
        imageButton = new HashMap<String, Buttons>();

        imageButton.put("Yes", new Buttons("Yes", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 192, 64, RuDragonGame.WIDTH / 2 - 180, RuDragonGame.HEIGHT / 2 - 180));
        imageButton.put("No", new Buttons("No", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("NULL"), 192, 64, RuDragonGame.WIDTH / 2 + 180, RuDragonGame.HEIGHT / 2 - 180));

        imageButton.get("Yes").addListener(new ButtonsInputListener(imageButton.get("Yes").getName()));
        imageButton.get("No").addListener(new ButtonsInputListener(imageButton.get("No").getName()));
    }

    public void onExit(boolean onOff) {
        this.windowOn = onOff;
        if (onOff) {
            stage_exit.addActor(imageButton.get("Yes"));
            stage_exit.addActor(imageButton.get("No"));
            Gdx.input.setInputProcessor(stage_exit);
        }
        else stage_exit.clear();
    }

    public Stage getStageExit() {
        return stage_exit;
    }

    public boolean isWindowOn() {
        return windowOn;
    }

    public void dispose() {
        try {
            stage_exit.dispose();
            imageButton.clear();
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
            if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
            if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);

            if (name.equals("Yes")) System.exit(0);
            else if (name.equals("No")) {
                onExit(false);
                RuDragonGame.getPreference().setOnWindow(true);
                RuDragonGame.getPreference().setModalWindow(false);
            }
        }
    }
}