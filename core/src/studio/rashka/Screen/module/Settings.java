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

public class Settings {

    private Stage stage;
    private Map<String, Buttons> imageButton;
    private ImageButtonStyle music_style, sound_style, vibro_style;

    private boolean windowOn = false, isSetLng = false;

    public Settings() {
        stage = new Stage();
        imageButton = new HashMap<String, Buttons>();

        music_style = new ImageButtonStyle();
        if (RuDragonGame.getPreference().loadMusic() == 1) music_style.up = RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("MusicOn");
        else if (RuDragonGame.getPreference().loadMusic() == 0) music_style.up = RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("MusicOff");
        imageButton.put("Music", new Buttons("Music", music_style.up, 96, 96, RuDragonGame.WIDTH / 2 - 76, RuDragonGame.HEIGHT / 2 + 24));

        sound_style = new ImageButtonStyle();
        if (RuDragonGame.getPreference().loadSound() == 1) sound_style.up = RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("SoundOn");
        else if (RuDragonGame.getPreference().loadSound() == 0) sound_style.up = RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("SoundOff");
        imageButton.put("Sound", new Buttons("Sound", sound_style.up, 96, 96, RuDragonGame.WIDTH / 2 + 50, RuDragonGame.HEIGHT / 2 + 24));

        vibro_style = new ImageButtonStyle();
        if (RuDragonGame.getPreference().loadVibro() == 1) vibro_style.up = RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("VibroOn");
        else if (RuDragonGame.getPreference().loadVibro() == 0) vibro_style.up = RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("VibroOff");
        imageButton.put("Vibro", new Buttons("Vibro", vibro_style.up, 96, 96, RuDragonGame.WIDTH / 2 + 128 + 48, RuDragonGame.HEIGHT / 2 + 24));

        imageButton.put("Close", new Buttons("Close", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Close"), 64, 64, RuDragonGame.WIDTH / 2 + 420, RuDragonGame.HEIGHT / 2 + 128));
        imageButton.put("Rus", new Buttons("Rus", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Rus"), 128, 128, RuDragonGame.WIDTH / 2 - 234, RuDragonGame.HEIGHT / 2 - 128));
        imageButton.put("Uk", new Buttons("Uk", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Uk"), 128, 128, RuDragonGame.WIDTH / 2 - 54, RuDragonGame.HEIGHT / 2 - 128));
        imageButton.put("Be", new Buttons("Be", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Be"), 128, 128, RuDragonGame.WIDTH / 2 + 126, RuDragonGame.HEIGHT / 2 - 128));
        imageButton.put("Eng", new Buttons("Eng", RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("Eng"), 128, 128, RuDragonGame.WIDTH / 2 + 306, RuDragonGame.HEIGHT / 2 - 128));

        imageButton.get("Music").addListener(new ButtonsInputListener(imageButton.get("Music").getName()));
        imageButton.get("Sound").addListener(new ButtonsInputListener(imageButton.get("Sound").getName()));
        imageButton.get("Vibro").addListener(new ButtonsInputListener(imageButton.get("Vibro").getName()));
        imageButton.get("Close").addListener(new ButtonsInputListener(imageButton.get("Close").getName()));
        imageButton.get("Rus").addListener(new ButtonsInputListener(imageButton.get("Rus").getName()));
        imageButton.get("Uk").addListener(new ButtonsInputListener(imageButton.get("Uk").getName()));
        imageButton.get("Be").addListener(new ButtonsInputListener(imageButton.get("Be").getName()));
        imageButton.get("Eng").addListener(new ButtonsInputListener(imageButton.get("Eng").getName()));
    }

    public void onSetting(boolean onOff) {
        this.windowOn = onOff;
        if (onOff) {
            stage.addActor(imageButton.get("Music"));
            stage.addActor(imageButton.get("Sound"));
            stage.addActor(imageButton.get("Vibro"));
            stage.addActor(imageButton.get("Close"));
            stage.addActor(imageButton.get("Rus"));
            stage.addActor(imageButton.get("Uk"));
            stage.addActor(imageButton.get("Be"));
            stage.addActor(imageButton.get("Eng"));
            Gdx.input.setInputProcessor(stage);
        }
        else stage.clear();
    }

    public Stage getStageSetting() {
        return stage;
    }

    public boolean isWindowOn() {
        return windowOn;
    }

    public boolean isSetLng() {
        return isSetLng;
    }

    public void dispose() {
        try {
            stage.dispose();
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
            if (name.equals("Music")) {
                imageButton.get("Music").setSize(90, 90);
                imageButton.get("Music").setPosition(RuDragonGame.WIDTH / 2 - 73, RuDragonGame.HEIGHT / 2 + 27);
            } else if (name.equals("Sound")) {
                imageButton.get("Sound").setSize(90, 90);
                imageButton.get("Sound").setPosition(RuDragonGame.WIDTH / 2 + 53, RuDragonGame.HEIGHT / 2 + 27);
            } else if (name.equals("Vibro")) {
                imageButton.get("Vibro").setSize(90, 90);
                imageButton.get("Vibro").setPosition(RuDragonGame.WIDTH / 2 + 128 + 51, RuDragonGame.HEIGHT / 2 + 27);
            } else if (name.equals("Rus")) {
                imageButton.get("Rus").setSize(120, 120);
                imageButton.get("Rus").setPosition(RuDragonGame.WIDTH / 2 - 230, RuDragonGame.HEIGHT / 2 - 124);
            } else if (name.equals("Uk")) {
                imageButton.get("Uk").setSize(120, 120);
                imageButton.get("Uk").setPosition(RuDragonGame.WIDTH / 2 - 50, RuDragonGame.HEIGHT / 2 - 124);
            } else if (name.equals("Be")) {
                imageButton.get("Be").setSize(120, 120);
                imageButton.get("Be").setPosition(RuDragonGame.WIDTH / 2 + 130, RuDragonGame.HEIGHT / 2 - 124);
            } else if (name.equals("Eng")) {
                imageButton.get("Eng").setSize(120, 120);
                imageButton.get("Eng").setPosition(RuDragonGame.WIDTH / 2 + 310, RuDragonGame.HEIGHT / 2 - 124);
            }
            return true;
        }

        @Override
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            if (RuDragonGame.getPreference().loadSound() == 1) RuDragonGame.getMusicSound().getClick().play();
            if (RuDragonGame.getPreference().loadVibro() == 1) Gdx.input.vibrate(50);

            if (name.equals("Music")) {
                imageButton.get("Music").setSize(96, 96);
                imageButton.get("Music").setPosition(RuDragonGame.WIDTH / 2 - 76, RuDragonGame.HEIGHT / 2 + 24);
                if (RuDragonGame.getPreference().loadMusic() == 0) {
                    imageButton.get("Music").imgUpdate(RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("MusicOn"));
                    RuDragonGame.getPreference().saveMusic(1);
                    RuDragonGame.setLoadMusic(1);
                } else if (RuDragonGame.getPreference().loadMusic() == 1) {
                    imageButton.get("Music").imgUpdate(RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("MusicOff"));
                    RuDragonGame.getPreference().saveMusic(0);
                    RuDragonGame.setLoadMusic(1);
                }
            } else if (name.equals("Sound")) {
                imageButton.get("Sound").setSize(96, 96);
                imageButton.get("Sound").setPosition(RuDragonGame.WIDTH / 2 + 50, RuDragonGame.HEIGHT / 2 + 24);
                if (RuDragonGame.getPreference().loadSound() == 0) {
                    imageButton.get("Sound").imgUpdate(RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("SoundOn"));
                    RuDragonGame.getPreference().saveSound(1);
                } else if (RuDragonGame.getPreference().loadSound() == 1) {
                    imageButton.get("Sound").imgUpdate(RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("SoundOff"));
                    RuDragonGame.getPreference().saveSound(0);
                }
            } else if (name.equals("Vibro")) {
                imageButton.get("Vibro").setSize(96, 96);
                imageButton.get("Vibro").setPosition(RuDragonGame.WIDTH / 2 + 128 + 48, RuDragonGame.HEIGHT / 2 + 24);
                if (RuDragonGame.getPreference().loadVibro() == 0) {
                    imageButton.get("Vibro").imgUpdate(RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("VibroOn"));
                    RuDragonGame.getPreference().saveVibro(1);
                } else if (RuDragonGame.getPreference().loadVibro() == 1) {
                    imageButton.get("Vibro").imgUpdate(RuDragonGame.getTextures().getButtonTextureSkin().getDrawable("VibroOff"));
                    RuDragonGame.getPreference().saveVibro(0);
                }
            } else if (name.equals("Close")) {
                onSetting(false);
                RuDragonGame.getPreference().setOnWindow(true);
                RuDragonGame.getPreference().setModalWindow(false);
            } else if (name.equals("Rus")) {
                imageButton.get("Rus").setSize(128, 128);
                imageButton.get("Rus").setPosition(RuDragonGame.WIDTH / 2 - 234, RuDragonGame.HEIGHT / 2 - 128);
                RuDragonGame.getPreference().saveLanguage("ru");
                isSetLng = true;
                RuDragonGame.setLoadLanguage(1);
            } else if (name.equals("Uk")) {
                imageButton.get("Uk").setSize(128, 128);
                imageButton.get("Uk").setPosition(RuDragonGame.WIDTH / 2 - 54, RuDragonGame.HEIGHT / 2 - 128);
                RuDragonGame.getPreference().saveLanguage("uk");
                isSetLng = true;
                RuDragonGame.setLoadLanguage(1);
            } else if (name.equals("Be")) {
                imageButton.get("Be").setSize(128, 128);
                imageButton.get("Be").setPosition(RuDragonGame.WIDTH / 2 + 126, RuDragonGame.HEIGHT / 2 - 128);
                RuDragonGame.getPreference().saveLanguage("be");
                isSetLng = true;
                RuDragonGame.setLoadLanguage(1);
            } else if (name.equals("Eng")) {
                imageButton.get("Eng").setSize(128, 128);
                imageButton.get("Eng").setPosition(RuDragonGame.WIDTH / 2 + 306, RuDragonGame.HEIGHT / 2 - 128);
                RuDragonGame.getPreference().saveLanguage("en");
                isSetLng = true;
                RuDragonGame.setLoadLanguage(1);
            }
        }
    }
}