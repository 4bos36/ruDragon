package studio.rashka.Lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Preference {

    private Preferences setting;

    private boolean gameRun = false,
            gameOver = false,
            onMenu = true,
            modalWindow = false,
            onWindow = false,
            roarDragon = false,
            fendOffDragon = false,
            isContinue = false;

    public Preference() {
        setting = Gdx.app.getPreferences("Pref_app"); // настройки приложения
        //setting.clear(); // если изменили тип данных, то разкомментироветь, запускаем и комментируем обратно, т.е. обнуляем всё

        Boolean start_pref = setting.getBoolean("Start_Pref");
        if (!start_pref) { // если запускается в первые, то запускаем параметры по умолчанию
            runNewSettings();
        }
    }

    public void runNewSettings() {
        setting.putInteger("Sound", 1);
        setting.putInteger("Music", 1);
        setting.putInteger("Vibro", 0);
        setting.putInteger("Level", 0);
        setting.putInteger("Level1", 0);
        setting.putInteger("Level3", 0);
        setting.putInteger("Level5", 0);
        setting.putInteger("Level8", 0);
        setting.putInteger("Level10", 0);
        setting.putFloat("BestScore", 0);
        setting.putInteger("Start", 0);
        setting.putFloat("XP", 0);
        setting.putFloat("Fly", 0);
        setting.putInteger("Fly1000", 0);
        setting.putInteger("Fly10000", 0);
        setting.putInteger("Fly30000", 0);
        setting.putInteger("Fly60000", 0);
        setting.putInteger("Fly100000", 0);
        setting.putInteger("Fly200000", 0);
        setting.putFloat("Run", 0);
        setting.putInteger("Run500", 0);
        setting.putInteger("Run4000", 0);
        setting.putInteger("Run9000", 0);
        setting.putInteger("Run15000", 0);
        setting.putInteger("Run32500", 0);
        setting.putInteger("Run48000", 0);
        setting.putInteger("Upgrade", 0);
        setting.putInteger("Home", 0);
        setting.putInteger("Best", 0);
        setting.putInteger("Best1", 0);
        setting.putInteger("Best5", 0);
        setting.putInteger("Best10", 0);
        setting.putInteger("Bank", 0);
        setting.putInteger("Bank500", 0);
        setting.putInteger("Bank1000", 0);
        setting.putInteger("Bank1500", 0);
        setting.putInteger("Bank1500", 0);
        setting.putInteger("Bank3000", 0);
        setting.putInteger("Bank5500", 0);
        setting.putInteger("Bank8350", 0);
        setting.putInteger("Glutton", 0);
        setting.putInteger("DuckLove", 0);
        setting.putInteger("GoatLove", 0);
        setting.putInteger("PigLove", 0);
        setting.putInteger("KineLove", 0);
        setting.putInteger("FlyUp", 0);
        setting.putInteger("FlyDown", 0);
        setting.putBoolean("FendOff", false);
        setting.putInteger("Roar", 0);
        setting.putInteger("BonusXP", 0);
        setting.putInteger("BonusHam", 0);
        setting.putInteger("BuyTree", 0);
        setting.putInteger("BuyBirds", 0);
        setting.putInteger("BuyLiveStock", 0);
        setting.putInteger("SuperBonusXP", 1);
        setting.putInteger("SuperBonusHam", 1);
        setting.putInteger("AngelBonus", 1);
        setting.putBoolean("Start_Pref", true); // сообщаем, что приложение уже было запущено
        setting.flush();
    }

    public int width() {
        return setting.getInteger("Width");
    }

    public int height() {
        return setting.getInteger("Height");
    }

    public void saveSound(int on_off) { // сохраняем настройки звуков
        setting.putInteger("Sound", on_off);
        setting.flush();
    }

    public int loadSound() { // загружаем настройки звуков
        return setting.getInteger("Sound");
    }

    public void saveMusic(int on_off) { // сохраняем настройки музыки
        setting.putInteger("Music", on_off);
        setting.flush();
    }

    public int loadMusic() { // загружаем настройки музыки
        return setting.getInteger("Music");
    }

    public int loadVibro() { // загружаем настройки музыки
        return setting.getInteger("Vibro");
    }

    public void saveVibro(int vibro) { // устанавливаем вибрацию телефона
        setting.putInteger("Vibro", vibro);
        setting.flush();
    }

    public void saveLanguage(String lng) { // сохраняем язык интерфейса
        setting.putString("Lang", lng);
        setting.flush();
    }

    public String loadLanguage() { // загружаем язык интерфейса
        return setting.getString("Lang");
    }

    /*** Game ***/

    public float loadBestScore() {
        return setting.getFloat("BestScore");
    }

    public int loadLevel() {
        return setting.getInteger("Level");
    }

    public void saveLevelYes(int i, int yes) {
        if (i == 1) {
            setting.putInteger("Level1", yes);
            setting.flush();
        }
        else if (i == 3) {
            setting.putInteger("Level3", yes);
            setting.flush();
        }
        else if (i == 5) {
            setting.putInteger("Level5", yes);
            setting.flush();
        }
        else if (i == 8) {
            setting.putInteger("Level8", yes);
            setting.flush();
        }
        else if (i == 10) {
            setting.putInteger("Level10", yes);
            setting.flush();
        }
    }

    public int loadLevelYes1() {
        return setting.getInteger("Level1");
    }

    public int loadLevelYes3() {
        return setting.getInteger("Level3");
    }

    public int loadLevelYes5() {
        return setting.getInteger("Level5");
    }

    public int loadLevelYes8() {
        return setting.getInteger("Level8");
    }

    public int loadLevelYes10() {
        return setting.getInteger("Level10");
    }

    public void saveXP(int xp) {
        setting.putFloat("XP", setting.getFloat("XP") + xp);
    }

    public float loadXP() {
        return setting.getFloat("XP");
    }

    public int loadStart() {
        return setting.getInteger("Start");
    }

    public void saveFly(float i) {
        if (loadFly() < 204000) {
            float fly = loadFly() + i;
            setting.putFloat("Fly", fly);
            setting.flush();
        }
    }

    public float loadFly() {
        return setting.getFloat("Fly");
    }

    public void saveFlyYes(int i, int yes) {
        if (i == 1000) {
            setting.putInteger("Fly1000", yes);
            setting.flush();
        }
        else if (i == 10000) {
            setting.putInteger("Fly10000", yes);
            setting.flush();
        }
        else if (i == 30000) {
            setting.putInteger("Fly30000", yes);
            setting.flush();
        }
        else if (i == 60000) {
            setting.putInteger("Fly60000", yes);
            setting.flush();
        }
        else if (i == 100000) {
            setting.putInteger("Fly100000", yes);
            setting.flush();
        }
        else if (i == 200000) {
            setting.putInteger("Fly200000", yes);
            setting.flush();
        }
    }

    public int loadFlyYes1000() {
        return setting.getInteger("Fly1000");
    }

    public int loadFlyYes10000() {
        return setting.getInteger("Fly10000");
    }

    public int loadFlyYes30000() {
        return setting.getInteger("Fly30000");
    }

    public int loadFlyYes60000() {
        return setting.getInteger("Fly60000");
    }

    public int loadFlyYes100000() {
        return setting.getInteger("Fly100000");
    }

    public int loadFlyYes200000() {
        return setting.getInteger("Fly200000");
    }

    public void saveRunYes(int i, int yes) {
        if (i == 500) {
            setting.putInteger("Run500", yes);
            setting.flush();
        }
        else if (i == 4000) {
            setting.putInteger("Run4000", yes);
            setting.flush();
        }
        else if (i == 9000) {
            setting.putInteger("Run9000", yes);
            setting.flush();
        }
        else if (i == 15000) {
            setting.putInteger("Run15000", yes);
            setting.flush();
        }
        else if (i == 32500) {
            setting.putInteger("Run32500", yes);
            setting.flush();
        }
        else if (i == 48000) {
            setting.putInteger("Run48000", yes);
            setting.flush();
        }
    }

    public int loadRunYes500() {
        return setting.getInteger("Run500");
    }

    public int loadRunYes4000() {
        return setting.getInteger("Run4000");
    }

    public int loadRunYes9000() {
        return setting.getInteger("Run9000");
    }

    public int loadRunYes15000() {
        return setting.getInteger("Run15000");
    }

    public int loadRunYes32500() {
        return setting.getInteger("Run32500");
    }

    public int loadRunYes48000() {
        return setting.getInteger("Run48000");
    }

    public void saveRun(float i) {
        if (loadRun() < 51000) {
            float run = loadRun() + i;
            setting.putFloat("Run", run);
            setting.flush();
        }
    }

    public float loadRun() {
        return setting.getFloat("Run");
    }

    public void saveUpgrade(int i) {
        if (loadUpgrade() < 211) {
            int upgrate = loadUpgrade() + i;
            setting.putInteger("Upgrade", upgrate);
            setting.flush();
        }
    }

    public int loadUpgrade() {
        return setting.getInteger("Upgrade");
    }

    public void saveHome(int i) {
        int home = loadHome() + i;
        setting.putInteger("Home", home);
        setting.flush();
    }

    public int loadHome() {
        return setting.getInteger("Home");
    }

    public void saveBest(float score) {
        if (score > loadBestScore()) {
            setting.putInteger("Best", loadBest() + 1);
            setting.putFloat("BestScore", score);
            setting.flush();
        }
    }

    public int loadBest() {
        return setting.getInteger("Best");
    }

    public void saveBestYes(int i, int yes) {
        if (i == 1) {
            setting.putInteger("Best1", yes);
            setting.flush();
        }
        else if (i == 5) {
            setting.putInteger("Best5", yes);
            setting.flush();
        }
        else if (i == 10) {
            setting.putInteger("Best10", yes);
            setting.flush();
        }
    }

    public int loadBestYes1() {
        return setting.getInteger("Best1");
    }

    public int loadBestYes5() {
        return setting.getInteger("Best5");
    }

    public int loadBestYes10() {
        return setting.getInteger("Best10");
    }

    public void saveBank(int plus, int minus) {
        int bank = loadBank() + plus - minus;
        setting.putInteger("Bank", bank);
        setting.flush();
    }

    public int loadBank() {
        return setting.getInteger("Bank");
    }

    public void saveBankYes(int i, int yes) {
        if (i == 500) {
            setting.putInteger("Bank500", yes);
            setting.flush();
        }
        else if (i == 1000) {
            setting.putInteger("Bank1000", yes);
            setting.flush();
        }
        else if (i == 1500) {
            setting.putInteger("Bank1500", yes);
            setting.flush();
        }
        else if (i == 3000) {
            setting.putInteger("Bank3000", yes);
            setting.flush();
        }
        else if (i == 5500) {
            setting.putInteger("Bank5500", yes);
            setting.flush();
        }
        else if (i == 8350) {
            setting.putInteger("Bank8350", yes);
            setting.flush();
        }
    }

    public int loadBankYes500() {
        return setting.getInteger("Bank500");
    }

    public int loadBankYes1000() {
        return setting.getInteger("Bank1000");
    }

    public int loadBankYes1500() {
        return setting.getInteger("Bank1500");
    }

    public int loadBankYes3000() {
        return setting.getInteger("Bank3000");
    }

    public int loadBankYes5500() {
        return setting.getInteger("Bank5500");
    }

    public int loadBankYes8350() {
        return setting.getInteger("Bank8350");
    }

    public int loadGlutton() {
        return setting.getInteger("Glutton");
    }

    public void saveDuckLove(int i) {
        if (loadDuckLove() < 2501) setting.putInteger("DuckLove", loadDuckLove() + i);
        if (loadGlutton() < 14010) setting.putInteger("Glutton", loadGlutton() + i);
        if (loadDuckLove() < 2501 || loadGlutton() < 14010) setting.flush();
    }

    public int loadDuckLove() {
        return setting.getInteger("DuckLove");
    }

    public void saveGoatLove(int i) {
        if (loadGoatLove() < 1501) setting.putInteger("GoatLove", loadGoatLove() + i);
        if (loadGlutton() < 14010) setting.putInteger("Glutton", loadGlutton() + i);
        if (loadGoatLove() < 1501 || loadGlutton() < 14010) setting.flush();
    }

    public int loadGoatLove() {
        return setting.getInteger("GoatLove");
    }

    public void savePigLove(int i) {
        if (loadPigLove() < 856) setting.putInteger("PigLove", loadPigLove() + i);
        if (loadGlutton() < 14010) setting.putInteger("Glutton", loadGlutton() + i);
        if (loadPigLove() < 856 || loadGlutton() < 14010) setting.flush();
    }

    public int loadPigLove() {
        return setting.getInteger("PigLove");
    }

    public void saveKineLove(int i) {
        if (loadKineLove() < 676) setting.putInteger("KineLove", loadKineLove() + i);
        if (loadGlutton() < 14010) setting.putInteger("Glutton", loadGlutton() + i);
        if (loadKineLove() < 676 || loadGlutton() < 14010) setting.flush();
    }

    public int loadKineLove() {
        return setting.getInteger("KineLove");
    }

    /*** ПОКУПКИ ***/

    public void saveFlyUp(int i) {
        int flyUp = loadFlyUp() + i;
        setting.putInteger("FlyUp", flyUp);
        setting.flush();
    }

    public int loadFlyUp() {
        return setting.getInteger("FlyUp");
    }

    public void saveFlyDown(int i) {
        int flyDown = loadFlyDown() + i;
        setting.putInteger("FlyDown", flyDown);
        setting.flush();
    }

    public int loadFlyDown() {
        return setting.getInteger("FlyDown");
    }

    public void saveFendOff(boolean buy) {
        setting.putBoolean("FendOff", buy);
        setting.flush();
    }

    public boolean loadFendOff() {
        return setting.getBoolean("FendOff");
    }

    public void saveRoar(int plus, int minus) {
        int roar = loadRoar() + plus - minus;
        setting.putInteger("Roar", roar);
        setting.flush();
    }

    public int loadRoar() {
        return setting.getInteger("Roar");
    }

    public void saveBonusXP(int i) {
        int bonus = loadBonusXP() + i;
        setting.putInteger("BonusXP", bonus);
        setting.flush();
    }

    public int loadBonusXP() {
        return setting.getInteger("BonusXP");
    }

    public void saveBonusHam(int i) {
        int bonus = loadBonusHam() + i;
        setting.putInteger("BonusHam", bonus);
        setting.flush();
    }

    public int loadBonusHam() {
        return setting.getInteger("BonusHam");
    }

    public void saveBuyTree(int i) {
        int tree = loadBuyTree() + i;
        setting.putInteger("BuyTree", tree);
        setting.flush();
    }

    public int loadBuyTree() {
        return setting.getInteger("BuyTree");
    }

    public void saveBuyBirds(int i) {
        int birds = loadBuyBirds() + i;
        setting.putInteger("BuyBirds", birds);
        setting.flush();
    }

    public int loadBuyBirds() {
        return setting.getInteger("BuyBirds");
    }

    public void saveBuyLiveStock(int i) {
        int livestock = loadBuyLiveStock() + i;
        setting.putInteger("BuyLiveStock", livestock);
        setting.flush();
    }

    public int loadBuyLiveStock() {
        return setting.getInteger("BuyLiveStock");
    }

    /*** БОНУСЫ ***/

    public void saveSuperBonusXP(int minus) {
        setting.putInteger("SuperBonusXP", setting.getInteger("SuperBonusXP") - minus);
        setting.flush();
    }

    public int loadSuperBonusXP() {
        return setting.getInteger("SuperBonusXP");
    }

    public void saveSuperBonusHam(int minus) {
        setting.putInteger("SuperBonusHam", setting.getInteger("SuperBonusHam") - minus);
        setting.flush();
    }

    public int loadSuperBonusHam() {
        return setting.getInteger("SuperBonusHam");
    }

    public void saveAngelBonus(int minus) {
        setting.putInteger("AngelBonus", setting.getInteger("AngelBonus") - minus);
        setting.flush();
    }

    public int loadAngelBonus() {
        return setting.getInteger("AngelBonus");
    }

    public void saveGameOver(int finish, float score, int bonusScore, int plus, float xp, float flying, float runs) {
        if (loadStart() < 5801) {
            int start = loadStart() + finish;
            setting.putInteger("Start", start);
        }
        setting.putInteger("Bank", loadBank() + plus + bonusScore);
        if (loadXP() < 318000) {
            float i = loadXP() + xp;
            setting.putFloat("XP", i);
            if (i >= 2500 && i < 5814) setting.putInteger("Level", 1);
            else if (i >= 5814 && i < 11625) setting.putInteger("Level", 2);
            else if (i >= 11625 && i < 18600) setting.putInteger("Level", 3);
            else if (i >= 18600 && i < 29760) setting.putInteger("Level", 4);
            else if (i >= 29760 && i < 47615) setting.putInteger("Level", 5);
            else if (i >= 47615 && i < 76185) setting.putInteger("Level", 6);
            else if (i >= 76185 && i < 121900) setting.putInteger("Level", 7);
            else if (i >= 121900 && i < 195035) setting.putInteger("Level", 8);
            else if (i >= 195035 && i < 312056) setting.putInteger("Level", 9);
            else if (i >= 312056) setting.putInteger("Level", 10);
        }
        if (loadFly() < 204000) setting.putFloat("Fly", loadFly() + flying);
        if (loadRun() < 51000) setting.putFloat("Run", loadRun() + runs);
        setting.flush();
    }

    //region Gets
    public void setGameRun(boolean gameRun) {
        this.gameRun = gameRun;
    }

    public boolean isGameRun() {
        return gameRun;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setOnMenu(boolean onMenu) {
        this.onMenu = onMenu;
    }

    public boolean isOnMenu() {
        return onMenu;
    }

    public void setModalWindow(boolean modalWindow) {
        this.modalWindow = modalWindow;
    }

    public boolean isModalWindow() {
        return modalWindow;
    }

    public void setOnWindow(boolean onWindow) {
        this.onWindow = onWindow;
    }

    public boolean isOnWindow() {
        return onWindow;
    }

    public boolean isRoarDragon() {
        return roarDragon;
    }

    public void setRoarDragon(boolean roarDragon) {
        this.roarDragon = roarDragon;
    }

    public boolean isFendOffDragon() {
        return fendOffDragon;
    }

    public void setFendOffDragon(boolean fendOffDragon) {
        this.fendOffDragon = fendOffDragon;
    }

    public boolean isContinue() {
        return isContinue;
    }

    public void setIsContinue(boolean isContinue) {
        this.isContinue = isContinue;
    }
    //endregion
}