package studio.rashka.Lib;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.HashMap;
import java.util.Map;

public class Textures {

    public Skin textureButtonSkin;
    public AssetManager loadTextures;
    public Map<String, Texture> allTextures;
    public Map<String, TextureRegion> textureRegions; //массив регионов
    private boolean isLoad = false;

    public Textures() {
        loadTextures = new AssetManager();
        allTextures = new HashMap<String, Texture>();
        textureRegions = new HashMap<String, TextureRegion>();

        allTextures.put("BtnLng", new Texture("btnLng.tga"));
    }

    public void buttons() {
        textureButtonSkin = new Skin(loadTextures.get("buttons.texture", TextureAtlas.class));
    }

    public void onLanRus() {
        textureRegions.put("settings", new TextureRegion(allTextures.get("BtnLng"), 0, 0, 448, 64));
        textureRegions.put("exit", new TextureRegion(allTextures.get("BtnLng"), 448, 0, 320, 64));
        textureRegions.put("yes", new TextureRegion(allTextures.get("BtnLng"), 768, 0, 192, 64));
        textureRegions.put("no", new TextureRegion(allTextures.get("BtnLng"), 320, 128, 192, 64));
        textureRegions.put("credits", new TextureRegion(allTextures.get("BtnLng"), 0, 128, 320, 64));
        textureRegions.put("menu", new TextureRegion(allTextures.get("BtnLng"), 512, 128, 192, 64));
        textureRegions.put("repeat", new TextureRegion(allTextures.get("BtnLng"), 704, 128, 256, 64));
    }

    public void onLanUk() {
        textureRegions.put("settings", new TextureRegion(allTextures.get("BtnLng"), 0, 256, 448, 64));
        textureRegions.put("exit", new TextureRegion(allTextures.get("BtnLng"), 448, 256, 320, 64));
        textureRegions.put("yes", new TextureRegion(allTextures.get("BtnLng"), 768, 256, 192, 64));
        textureRegions.put("no", new TextureRegion(allTextures.get("BtnLng"), 320, 384, 192, 64));
        textureRegions.put("credits", new TextureRegion(allTextures.get("BtnLng"), 0, 384, 320, 64));
        textureRegions.put("menu", new TextureRegion(allTextures.get("BtnLng"), 512, 384, 192, 64));
        textureRegions.put("repeat", new TextureRegion(allTextures.get("BtnLng"), 704, 384, 256, 64));
    }

    public void onLanBe() {
        textureRegions.put("settings", new TextureRegion(allTextures.get("BtnLng"), 0, 320, 448, 64));
        textureRegions.put("exit", new TextureRegion(allTextures.get("BtnLng"), 448, 320, 320, 64));
        textureRegions.put("yes", new TextureRegion(allTextures.get("BtnLng"), 768, 320, 192, 64));
        textureRegions.put("no", new TextureRegion(allTextures.get("BtnLng"), 320, 448, 192, 64));
        textureRegions.put("credits", new TextureRegion(allTextures.get("BtnLng"), 0, 448, 320, 64));
        textureRegions.put("menu", new TextureRegion(allTextures.get("BtnLng"), 512, 448, 192, 64));
        textureRegions.put("repeat", new TextureRegion(allTextures.get("BtnLng"), 704, 448, 256, 64));
    }

    public void onLanEng() {
        textureRegions.put("settings", new TextureRegion(allTextures.get("BtnLng"), 0, 64, 448, 64));
        textureRegions.put("exit", new TextureRegion(allTextures.get("BtnLng"), 448, 64, 320, 64));
        textureRegions.put("yes", new TextureRegion(allTextures.get("BtnLng"), 768, 64, 192, 64));
        textureRegions.put("no", new TextureRegion(allTextures.get("BtnLng"), 320, 192, 192, 64));
        textureRegions.put("credits", new TextureRegion(allTextures.get("BtnLng"), 0, 192, 320, 64));
        textureRegions.put("menu", new TextureRegion(allTextures.get("BtnLng"), 512, 192, 192, 64));
        textureRegions.put("repeat", new TextureRegion(allTextures.get("BtnLng"), 704, 192, 256, 64));
    }

    public void loadTexture() {
        allTextures.put("packTexture", loadTextures.get("packTexture.png", Texture.class));
        textureRegions.put("fonCave", new TextureRegion(loadTextures.get("fonCave.tga", Texture.class), 0, 0, 1820, 1024));
        textureRegions.put("fonCaveNight", new TextureRegion(loadTextures.get("fonCaveNight.jpg", Texture.class)));

        textureRegions.put("earth", new TextureRegion(allTextures.get("packTexture"), 0, 0, 1920, 192));
        textureRegions.put("moon", new TextureRegion(allTextures.get("packTexture"), 1920, 0, 128, 128));
        textureRegions.put("xp_bonus_on", new TextureRegion(allTextures.get("packTexture"), 0, 192, 64, 64));
        textureRegions.put("xp_bonus_off", new TextureRegion(allTextures.get("packTexture"), 64, 192, 64, 64));
        textureRegions.put("ham_bonus_on", new TextureRegion(allTextures.get("packTexture"), 128, 192, 64, 64));
        textureRegions.put("ham_bonus_off", new TextureRegion(allTextures.get("packTexture"), 192, 192, 64, 64));
        textureRegions.put("ham", new TextureRegion(allTextures.get("packTexture"), 256, 192, 64, 64));
        textureRegions.put("black", new TextureRegion(allTextures.get("packTexture"), 0, 256, 64, 64));
        textureRegions.put("white", new TextureRegion(allTextures.get("packTexture"), 64, 256, 64, 64));
        textureRegions.put("gray", new TextureRegion(allTextures.get("packTexture"), 128, 256, 64, 64));
        textureRegions.put("red", new TextureRegion(allTextures.get("packTexture"), 288, 416, 32, 32));
        textureRegions.put("fon_lite", new TextureRegion(allTextures.get("packTexture"), 192, 256, 64, 64));
        textureRegions.put("best", new TextureRegion(allTextures.get("packTexture"), 256, 256, 64, 64));
        textureRegions.put("star_blue", new TextureRegion(allTextures.get("packTexture"), 320, 256, 32, 32));
        textureRegions.put("star_white", new TextureRegion(allTextures.get("packTexture"), 352, 256, 32, 32));
        textureRegions.put("star_red", new TextureRegion(allTextures.get("packTexture"), 320, 288, 32, 32));
        textureRegions.put("star_gray", new TextureRegion(allTextures.get("packTexture"), 352, 288, 32, 32));
        textureRegions.put("cloud", new TextureRegion(allTextures.get("packTexture"), 320, 192, 128, 64));
        textureRegions.put("cloudBig", new TextureRegion(allTextures.get("packTexture"), 0, 768, 128, 64));
        textureRegions.put("cloudMini", new TextureRegion(allTextures.get("packTexture"), 0, 832, 64, 64));
        textureRegions.put("cloudMini2", new TextureRegion(allTextures.get("packTexture"), 64, 832, 64, 64));
        textureRegions.put("gameover", new TextureRegion(allTextures.get("packTexture"), 448, 192, 384, 64));
        textureRegions.put("logo_game", new TextureRegion(allTextures.get("packTexture"), 0, 320, 832, 448));
        textureRegions.put("fence", new TextureRegion(allTextures.get("packTexture"), 832, 192, 192, 128));
        textureRegions.put("fence2", new TextureRegion(allTextures.get("packTexture"), 1024, 192, 192, 128));
        textureRegions.put("fence3", new TextureRegion(allTextures.get("packTexture"), 1216, 192, 192, 128));
        textureRegions.put("fence4", new TextureRegion(allTextures.get("packTexture"), 832, 320, 192, 192));
        textureRegions.put("fence5", new TextureRegion(allTextures.get("packTexture"), 1024, 320, 320, 192));
        textureRegions.put("fence6", new TextureRegion(allTextures.get("packTexture"), 832, 512, 320, 192));
        textureRegions.put("mountain-home", new TextureRegion(allTextures.get("packTexture"), 1408, 192, 640, 384));
        textureRegions.put("pointer-menu", new TextureRegion(allTextures.get("packTexture"), 1152, 512, 128, 128));
        textureRegions.put("pointer-home", new TextureRegion(allTextures.get("packTexture"), 1280, 512, 128, 128));
        textureRegions.put("exit_icon", new TextureRegion(allTextures.get("packTexture"), 128, 768, 192, 128));
        textureRegions.put("haker", new TextureRegion(allTextures.get("packTexture"), 320, 768, 128, 128));
        textureRegions.put("buy", new TextureRegion(allTextures.get("packTexture"), 448, 768, 128, 128));
        textureRegions.put("buy_not", new TextureRegion(allTextures.get("packTexture"), 576, 768, 128, 128));
        textureRegions.put("start", new TextureRegion(allTextures.get("packTexture"), 1472, 576, 64, 192));
        textureRegions.put("pause", new TextureRegion(allTextures.get("packTexture"), 1536, 576, 64, 192));
        textureRegions.put("mountains", new TextureRegion(allTextures.get("packTexture"), 1600, 576, 448, 192));
        textureRegions.put("FeedbackRu", new TextureRegion(allTextures.get("packTexture"), 0, 896, 256, 128));
        textureRegions.put("FeedbackEn", new TextureRegion(allTextures.get("packTexture"), 256, 896, 256, 128));
        textureRegions.put("FeedbackUk", new TextureRegion(allTextures.get("packTexture"), 512, 896, 256, 128));
        textureRegions.put("FeedbackBe", new TextureRegion(allTextures.get("packTexture"), 768, 896, 256, 128));
        textureRegions.put("CasketXP", new TextureRegion(allTextures.get("packTexture"), 1024, 896, 128, 128));
        textureRegions.put("CasketXam", new TextureRegion(allTextures.get("packTexture"), 1152, 896, 128, 128));
        textureRegions.put("Heart", new TextureRegion(allTextures.get("packTexture"), 1920, 128, 64, 64));
        textureRegions.put("noActive", new TextureRegion(allTextures.get("packTexture"), 768, 256, 64, 64));
        textureRegions.put("PirateShip", new TextureRegion(allTextures.get("packTexture"), 832, 352, 384, 192));
        textureRegions.put("IslandDragon", new TextureRegion(allTextures.get("packTexture"), 1216, 640, 254, 128)); // повернуть на 90

        textureRegions.put("xp_progress", new TextureRegion(allTextures.get("packTexture"), 384, 256, 192, 64));
        textureRegions.put("xp_progress_fon", new TextureRegion(allTextures.get("packTexture"), 576, 256, 64, 64));
        textureRegions.put("xp_lv", new TextureRegion(allTextures.get("packTexture"), 640, 256, 128, 64));

        textureRegions.put("day", new TextureRegion(allTextures.get("packTexture"), 1344, 320, 64, 64));
        textureRegions.put("night", new TextureRegion(allTextures.get("packTexture"), 1344, 384, 64, 64));
    }

    public void loadTextureTree() {
        allTextures.put("textureTree", loadTextures.get("textureTree.png", Texture.class));

        textureRegions.put("ash", new TextureRegion(allTextures.get("textureTree"), 204, 448, 463, 512)); // ясень - 7
        textureRegions.put("aspen", new TextureRegion(allTextures.get("textureTree"), 1360, 0, 206, 400)); // осина - 2
        textureRegions.put("birch", new TextureRegion(allTextures.get("textureTree"), 1876, 0, 143, 352)); // берёза - 5
        textureRegions.put("fir", new TextureRegion(allTextures.get("textureTree"), 1695, 0, 181, 352)); // пихта - 4
        textureRegions.put("linden", new TextureRegion(allTextures.get("textureTree"), 652, 0, 489, 352)); // липа * 2
        textureRegions.put("maple", new TextureRegion(allTextures.get("textureTree"), 0, 448, 204, 256)); // клён - 6
        textureRegions.put("oak", new TextureRegion(allTextures.get("textureTree"), 0, 0, 652, 448)); // дуб - 1
        textureRegions.put("pine", new TextureRegion(allTextures.get("textureTree"), 1141, 0, 219, 448)); // сосна * 3
        textureRegions.put("poplar", new TextureRegion(allTextures.get("textureTree"), 667, 448, 155, 496)); // тополь - 8
        textureRegions.put("spruce", new TextureRegion(allTextures.get("textureTree"), 1566, 0, 129, 352)); // ель - 3
        textureRegions.put("willow", new TextureRegion(allTextures.get("textureTree"), 822, 448, 268, 244)); // ива * 1

        textureRegions.put("rock_1", new TextureRegion(allTextures.get("textureTree"), 832, 704, 256, 128));
        textureRegions.put("rock_2", new TextureRegion(allTextures.get("textureTree"), 832, 832, 256, 192));
        textureRegions.put("rock_3", new TextureRegion(allTextures.get("textureTree"), 1088, 832, 256, 192));
        textureRegions.put("rock_4", new TextureRegion(allTextures.get("textureTree"), 1090, 448, 254, 256));
        textureRegions.put("rock_5", new TextureRegion(allTextures.get("textureTree"), 1344, 448, 256, 256));
        textureRegions.put("rock_6", new TextureRegion(allTextures.get("textureTree"), 1600, 384, 256, 256));
        textureRegions.put("rock_7", new TextureRegion(allTextures.get("textureTree"), 1856, 384, 192, 256)); // повернуть на -90

        isLoad = true;
    }

    public void loadTextureAnimals() {
        allTextures.put("textureAnimals", loadTextures.get("textureAnimals.png", Texture.class));

        textureRegions.put("dragonRun", new TextureRegion(allTextures.get("textureAnimals"), 0, 864, 1870, 70));
        textureRegions.put("dragonFly", new TextureRegion(allTextures.get("textureAnimals"), 0, 768, 2040, 170));
        textureRegions.put("babaYaga", new TextureRegion(allTextures.get("textureAnimals"), 0, 672, 1728, 192));

        textureRegions.put("buzzard", new TextureRegion(allTextures.get("textureAnimals"), 0, 0, 1921, 128));
        textureRegions.put("eagle", new TextureRegion(allTextures.get("textureAnimals"), 0, 64, 1921, 128));
        textureRegions.put("falcon", new TextureRegion(allTextures.get("textureAnimals"), 0, 128, 1921, 128));
        textureRegions.put("hawk", new TextureRegion(allTextures.get("textureAnimals"), 0, 192, 1921, 128));
        textureRegions.put("korshun", new TextureRegion(allTextures.get("textureAnimals"), 0, 256, 1921, 128));
        textureRegions.put("orlan", new TextureRegion(allTextures.get("textureAnimals"), 0, 320, 1921, 128));
        textureRegions.put("osprey", new TextureRegion(allTextures.get("textureAnimals"), 0, 384, 1921, 128));

        textureRegions.put("kine", new TextureRegion(allTextures.get("textureAnimals"), 0, 448, 1710, 128));
        textureRegions.put("pig", new TextureRegion(allTextures.get("textureAnimals"), 0, 512, 1694, 128));
        textureRegions.put("goat", new TextureRegion(allTextures.get("textureAnimals"), 0, 576, 1664, 128));
        textureRegions.put("duck", new TextureRegion(allTextures.get("textureAnimals"), 0, 640, 1360, 64));
    }

    public Skin getButtonTextureSkin() {
        return textureButtonSkin;
    }

    public boolean isLoad() {
        return isLoad;
    }

    public void dispose() {
        try {
            allTextures.clear();
            textureRegions.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}