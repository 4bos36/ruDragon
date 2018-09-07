package studio.rashka.Lib;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class MusicSound {

    private Map<String, Music> musics;
    private Sound click;
    private boolean caveRun = false;

    private AssetManager music, sound;

    public MusicSound() {
        musics = new HashMap<String, Music>();
        music = new AssetManager();
        sound = new AssetManager();
    }

    public void loadMusic() {
        music.load("sounds/fon_play.ogg", Music.class);
        music.load("sounds/run_play.ogg", Music.class);
        music.load("sounds/cave.ogg", Music.class);
        music.finishLoading();
    }

    private void deletedMusic() {
        music.unload("sounds/fon_play.ogg");
        music.unload("sounds/run_play.ogg");
        music.unload("sounds/cave.ogg");
        music.finishLoading();
    }

    public void loadSound() {
        sound.load("sounds/gameover.ogg", Sound.class);
        sound.load("sounds/click.ogg", Sound.class);
        sound.load("sounds/casket.ogg", Sound.class);
        sound.load("sounds/pirateStart.ogg", Sound.class);
        sound.load("sounds/pirateFinish.ogg", Sound.class);

        sound.load("sounds/dragon.ogg", Sound.class);
        sound.load("sounds/babaYaga.ogg", Sound.class);
        sound.load("sounds/babaYagaAttack.ogg", Sound.class);

        sound.load("sounds/tree.ogg", Sound.class);
        sound.load("sounds/duck.ogg", Sound.class);
        sound.load("sounds/goat.ogg", Sound.class);
        sound.load("sounds/pig.ogg", Sound.class);
        sound.load("sounds/kine.ogg", Sound.class);
        sound.load("sounds/duckEaten.ogg", Sound.class);
        sound.load("sounds/goatEaten.ogg", Sound.class);
        sound.load("sounds/pigEaten.ogg", Sound.class);
        sound.load("sounds/kineEaten.ogg", Sound.class);

        sound.load("sounds/buzzard.ogg", Sound.class);
        sound.load("sounds/buzzardAttack.ogg", Sound.class);
        sound.load("sounds/eagle.ogg", Sound.class);
        sound.load("sounds/eagleAttack.ogg", Sound.class);
        sound.load("sounds/falcon.ogg", Sound.class);
        sound.load("sounds/falconAttack.ogg", Sound.class);
        sound.load("sounds/hawk.ogg", Sound.class);
        sound.load("sounds/hawkAttack.ogg", Sound.class);
        sound.load("sounds/korshun.ogg", Sound.class);
        sound.load("sounds/korshunAttack.ogg", Sound.class);
        sound.load("sounds/orlan.ogg", Sound.class);
        sound.load("sounds/orlanAttack.ogg", Sound.class);
        sound.load("sounds/osprey.ogg", Sound.class);
        sound.load("sounds/ospreyAttack.ogg", Sound.class);

        sound.load("sounds/rock.ogg", Sound.class);
    }

    public void deletedSound() {
        sound.unload("sounds/gameover.ogg");
        sound.unload("sounds/click.ogg");
        sound.unload("sounds/casket.ogg");
        sound.unload("sounds/pirateStart.ogg");
        sound.unload("sounds/pirateFinish.ogg");

        sound.unload("sounds/dragon.ogg");
        sound.unload("sounds/babaYaga.ogg");
        sound.unload("sounds/babaYagaAttack.ogg");

        sound.unload("sounds/tree.ogg");
        sound.unload("sounds/duck.ogg");
        sound.unload("sounds/goat.ogg");
        sound.unload("sounds/pig.ogg");
        sound.unload("sounds/kine.ogg");
        sound.unload("sounds/duckEaten.ogg");
        sound.unload("sounds/goatEaten.ogg");
        sound.unload("sounds/pigEaten.ogg");
        sound.unload("sounds/kineEaten.ogg");

        sound.unload("sounds/buzzard.ogg");
        sound.unload("sounds/buzzardAttack.ogg");
        sound.unload("sounds/eagle.ogg");
        sound.unload("sounds/eagleAttack.ogg");
        sound.unload("sounds/falcon.ogg");
        sound.unload("sounds/falconAttack.ogg");
        sound.unload("sounds/hawk.ogg");
        sound.unload("sounds/hawkAttack.ogg");
        sound.unload("sounds/korshun.ogg");
        sound.unload("sounds/korshunAttack.ogg");
        sound.unload("sounds/orlan.ogg");
        sound.unload("sounds/orlanAttack.ogg");
        sound.unload("sounds/osprey.ogg");
        sound.unload("sounds/ospreyAttack.ogg");
    }

    public void loadMusicFon() {
        musics.put("music", music.get("sounds/fon_play.ogg", Music.class));
        musics.get("music").setLooping(true);
        musics.get("music").setVolume(1.0f);
    }

    public void loadMusicRun() {
        musics.put("run", music.get("sounds/run_play.ogg", Music.class));
        musics.get("run").setLooping(true);
        musics.get("run").setVolume(0.4f);
    }

    public void loadCave() {
        musics.put("cave", music.get("sounds/cave.ogg", Music.class));
        musics.get("cave").setLooping(true);
        musics.get("cave").setVolume(1.0f);
    }

    public Map<String, Sound> sounds;
    public void loadGame() {
        sounds = new HashMap<String, Sound>();

        click = sound.get("sounds/click.ogg", Sound.class);

        sounds.put("gameover", sound.get("sounds/gameover.ogg", Sound.class));
        sounds.put("PirateStart", sound.get("sounds/pirateStart.ogg", Sound.class));
        sounds.put("PirateFinish", sound.get("sounds/pirateFinish.ogg", Sound.class));

        sounds.put("dragon", sound.get("sounds/dragon.ogg", Sound.class));
        sounds.put("babaYaga", sound.get("sounds/babaYaga.ogg", Sound.class));
        sounds.put("babaYagaAttack", sound.get("sounds/babaYagaAttack.ogg", Sound.class));

        sounds.put("casket", sound.get("sounds/casket.ogg", Sound.class));
        sounds.put("tree", sound.get("sounds/tree.ogg", Sound.class));
        sounds.put("duck", sound.get("sounds/duck.ogg", Sound.class));
        sounds.put("goat", sound.get("sounds/goat.ogg", Sound.class));
        sounds.put("pig", sound.get("sounds/pig.ogg", Sound.class));
        sounds.put("kine", sound.get("sounds/kine.ogg", Sound.class));
        sounds.put("duckEaten", sound.get("sounds/duckEaten.ogg", Sound.class));
        sounds.put("goatEaten", sound.get("sounds/goatEaten.ogg", Sound.class));
        sounds.put("pigEaten", sound.get("sounds/pigEaten.ogg", Sound.class));
        sounds.put("kineEaten", sound.get("sounds/kineEaten.ogg", Sound.class));

        sounds.put("buzzard", sound.get("sounds/buzzard.ogg", Sound.class));
        sounds.put("buzzardAttack", sound.get("sounds/buzzardAttack.ogg", Sound.class));
        sounds.put("eagle", sound.get("sounds/eagle.ogg", Sound.class));
        sounds.put("eagleAttack", sound.get("sounds/eagleAttack.ogg", Sound.class));
        sounds.put("falcon", sound.get("sounds/falcon.ogg", Sound.class));
        sounds.put("falconAttack", sound.get("sounds/falconAttack.ogg", Sound.class));
        sounds.put("hawk", sound.get("sounds/hawk.ogg", Sound.class));
        sounds.put("hawkAttack", sound.get("sounds/hawkAttack.ogg", Sound.class));
        sounds.put("korshun", sound.get("sounds/korshun.ogg", Sound.class));
        sounds.put("korshunAttack", sound.get("sounds/korshunAttack.ogg", Sound.class));
        sounds.put("orlan", sound.get("sounds/orlan.ogg", Sound.class));
        sounds.put("orlanAttack", sound.get("sounds/orlanAttack.ogg", Sound.class));
        sounds.put("osprey", sound.get("sounds/osprey.ogg", Sound.class));
        sounds.put("ospreyAttack", sound.get("sounds/ospreyAttack.ogg", Sound.class));

        sounds.put("rock", sound.get("sounds/rock.ogg", Sound.class));
    }

    public void startFon(){
        try {
            if (musics.get("music") != null) musics.get("music").play();
        } catch (NullPointerException e) {
            loadMusicFon();
            startRun();
        }
    }

    public void pauseFon(){
        if (musics.get("music").isPlaying()) {
            try {
                musics.get("music").pause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopFon(){
        if (musics.get("music").isPlaying()) {
            try {
                musics.get("music").stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void startRun(){
        try {
            if (!musics.get("run").isPlaying()) musics.get("run").play();
        } catch (NullPointerException e) {
            loadMusicRun();
            startRun();
        }
    }

    public void pauseRun(){
        if (musics.get("run").isPlaying()) {
            try {
                musics.get("run").pause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopRun() {
        if (musics.get("run").isPlaying()) {
            try {
                musics.get("run").stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void startCave(){
        try {
            if (musics.get("cave") != null && !musics.get("cave").isPlaying()) musics.get("cave").play();
        } catch (NullPointerException e) {
            loadCave();
            startCave();
        }
    }

    public void stopCave(){
        if (musics.get("cave") != null && musics.get("cave").isPlaying()) musics.get("cave").stop();
    }

    public void resumeCave(){
        if (caveRun) musics.get("cave").play();
    }

    /*** Gets ***/

    public Sound getClick() {
        return click;
    }

    public AssetManager getMusic() {
        return music;
    }

    public AssetManager getSound() {
        return sound;
    }

    public Map<String, Music> getMusics() {
        return musics;
    }

    public void dispose() {
        try {
            deletedMusic();
            deletedSound();
            music.dispose();
            sound.dispose();
            musics.clear();
            click.dispose();
            sounds.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}