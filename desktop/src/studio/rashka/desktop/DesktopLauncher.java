package studio.rashka.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import studio.rashka.AdHandler;
import studio.rashka.RuDragonGame;

public class DesktopLauncher implements AdHandler {

	private static DesktopLauncher application;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		if (application == null) {
			application = new DesktopLauncher();
		}

		config.title = RuDragonGame.TITLE;
		config.width = RuDragonGame.WIDTH / 2;
		config.height = RuDragonGame.HEIGHT / 2;
		new LwjglApplication(new RuDragonGame(application), config);
	}

	@Override
	public void showAds(int show) {
		// TODO Auto-generated method stub
	}
}
