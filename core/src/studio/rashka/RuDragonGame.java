package studio.rashka;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import studio.rashka.Lib.FontTTF;
import studio.rashka.Lib.Game;
import studio.rashka.Lib.Language;
import studio.rashka.Lib.Monitor;
import studio.rashka.Lib.MusicSound;
import studio.rashka.Lib.Preference;
import studio.rashka.Lib.Textures;
import studio.rashka.Lib.TimeBonus;
import studio.rashka.Models.River;
import studio.rashka.Screen.LoadScreen;

/**
 * Created by 4bos on 19.08.2017.
 */

public class RuDragonGame extends ApplicationAdapter {

	public static final int WIDTH = 1920;
	public static int HEIGHT = 1080;
	public static final String TITLE = "Змей Горыныч";

	public static AdHandler adHandler;
	public RuDragonGame(AdHandler adHandler) {
		this.adHandler = adHandler;
	}

	private SpriteBatch batch;
	private static Game game;
	//private static FPSLogger fps;

	private OrthographicCamera camera;
	private static Preference preference; // lib
	private static MusicSound musicSound;
	private static Language language;
	private static Textures textures;
	private static FontTTF fontTTF;
	private Monitor monitor;

	private static River river;
	private static TimeBonus timeBonus;

	private static float ratioMonitorW, ratioMonitorH, ratioAdd;
	private static int loadLanguage = 1, loadMusic = 0;

	@Override
	public void create () {
		preference = new Preference();
		musicSound = new MusicSound();
		textures = new Textures();
		fontTTF = new FontTTF();
		//language = new Language();
		monitor = new Monitor();

		batch = new SpriteBatch();
		//fps = new FPSLogger();
		game = new Game();

		river = new River();
		timeBonus = new TimeBonus();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);

		ratioMonitorW = monitor.getRatioMonitorW();
		ratioMonitorH = monitor.getRatioMonitorH();
		ratioAdd = HEIGHT - 1080;

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		game.push(new LoadScreen(game));
	}

	@Override
	public void render () {
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

		game.update(Gdx.graphics.getDeltaTime());
		game.render(batch);

		timeBonus.updateTime();

		if (loadLanguage == 1) { // устанавливаем язык интерфейса
			language = new Language();
			loadLanguage = 0;
		}

		if (loadMusic == 1) { // старт/стоп музыки
			if (preference.loadMusic() == 1) musicSound.startFon();
			else if (preference.loadMusic() == 0) musicSound.pauseFon();
			loadMusic = 0;
		}
		//fps.log();
	}

	public static Game getGame() {
		return game;
	}

	public static TimeBonus getTimeBonus() {
		return timeBonus;
	}

	public static void setHEIGHT(int HEIGHT) {
		RuDragonGame.HEIGHT = HEIGHT;
	}

	public static Preference getPreference() {
		return preference;
	}

	public static MusicSound getMusicSound() {
		return musicSound;
	}

	public static Language getLanguage() {
		return language;
	}

	public static Textures getTextures() {
		return textures;
	}

	public static FontTTF getFontTTF() {
		return fontTTF;
	}

	public static River getRiver() {
		return river;
	}

	public static float getRatioMonitorW() {
		return ratioMonitorW;
	}

	public static float getRatioMonitorH() {
		return ratioMonitorH;
	}

	public static float getRatioAdd() {
		return ratioAdd;
	}

	public static void setLoadMusic(int loadmusic) {
		loadMusic = loadmusic;
	}

	public static void setLoadLanguage(int loadlanguage) {
		loadLanguage = loadlanguage;
	}

	@Override
	public void pause () {
		preference.setGameRun(false);
		if (!preference.isGameOver()) preference.setOnMenu(true);
		try {
			if (musicSound.getMusics().get("music").isPlaying()) musicSound.pauseFon();
			if (musicSound.getMusics().get("run").isPlaying()) musicSound.pauseRun();
			if (musicSound.getMusics().get("cave").isPlaying()) musicSound.stopCave();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void resume () {
		if (preference.loadMusic() == 1) musicSound.startFon();
		if (preference.loadSound() == 1) musicSound.resumeCave();
	}

	@Override
	public void dispose () {
		batch.dispose();
		musicSound.dispose();
		language.dispose();
		textures.dispose();
		fontTTF.dispose();

		river.dispose();

		System.exit(0);
	}
}