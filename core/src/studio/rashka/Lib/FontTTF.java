package studio.rashka.Lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.HashMap;
import java.util.Map;

import studio.rashka.RuDragonGame;

public class FontTTF {

    private static final String FONT_NAME = "fonts/font.ttf"; // расположение шрифта
    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;
    private Map<String, BitmapFont> typeFont;

    private StringBuilder fontCHARS;
    private float ratioMonitor;

    public FontTTF() {
        typeFont = new HashMap<String, BitmapFont>();
        ratioMonitor = (float) Gdx.graphics.getWidth() / (float) RuDragonGame.WIDTH; // коэффициент масштаба графики

        generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
        typeFont.put("fontIMG", new BitmapFont(Gdx.files.internal("fonts/font.fnt"), Gdx.files.internal("fonts/font.png"), false));

        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontCHARS = new StringBuilder("");

        for (int i = 32; i < 127; i++) fontCHARS.append((char)i);
        for (int i = 1024; i < 1169; i++) fontCHARS.append((char)i); // русские+украинский символы
        parameter.characters = fontCHARS.toString(); // заполняем массив символами рус и остальные

        parameter.size = (int)(48 * ratioMonitor);
        typeFont.put("font48", generator.generateFont(parameter));

        parameter.size = (int)(38 * ratioMonitor);
        typeFont.put("font38", generator.generateFont(parameter));

        parameter.size = (int)(32 * ratioMonitor);
        typeFont.put("font32", generator.generateFont(parameter));

        parameter.size = (int)(25 * ratioMonitor);
        typeFont.put("font25", generator.generateFont(parameter));
    }

    public BitmapFont getFont48() {
        return typeFont.get("font48");
    }

    public BitmapFont getFont38() {
        return typeFont.get("font38");
    }

    public BitmapFont getFont32() {
        return typeFont.get("font32");
    }

    public BitmapFont getFont25() {
        return typeFont.get("font25");
    }

    public BitmapFont getFontIMG() {
        return typeFont.get("fontIMG");
    }

    public void dispose() {
        try {
            typeFont.clear();
            generator.dispose();
            parameter = null;
            fontCHARS.setLength(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}