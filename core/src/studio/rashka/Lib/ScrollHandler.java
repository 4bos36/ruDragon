package studio.rashka.Lib;

import java.util.Random;

import studio.rashka.RuDragonGame;
import studio.rashka.Screen.module.scroll.Earth;
import studio.rashka.Screen.module.scroll.Field;
import studio.rashka.Screen.module.scroll.Mountains;
import studio.rashka.Screen.module.scroll.Scene;

public class ScrollHandler {

    private Random random;

    private Earth frontEarth, backEarth;
    private Field frontField, backField;
    private Mountains frontMountains, backMountains;
    private Scene frontScene, backScene;

    private int randomScene, randomScene2;
    private static final int ALL_SCENES = 45, ALL_SCENES_2 = 20;
    private boolean addScene = false,
                    addSceneFly = false;

    public ScrollHandler(float xPos) {
        random = new Random();
        randomScene = random.nextInt(ALL_SCENES);
        randomScene2 = random.nextInt(ALL_SCENES_2);

        frontEarth = new Earth(xPos, 0, 1920, 192);
        backEarth = new Earth(frontEarth.getTailX(), 0, 1920, 192);

        frontField = new Field(xPos, 140, 560, 240);
        backField = new Field(frontField.getTailX(), 140, 560, 240);

        frontMountains = new Mountains(xPos, 320, 280, 100);
        backMountains = new Mountains(frontMountains.getTailX(), 320, 280, 100);

        frontScene = new Scene(RuDragonGame.WIDTH + 10, 0, RuDragonGame.WIDTH, RuDragonGame.HEIGHT);
        backScene = new Scene(frontScene.getTailX(), 0, RuDragonGame.WIDTH, RuDragonGame.HEIGHT);
    }

    public void update(float deltaTime, float scroll_speed) {
        // Земля
        frontEarth.update(deltaTime);
        backEarth.update(deltaTime);
        if (frontEarth.isScrolledLeft()) frontEarth.reset(backEarth.getTailX() - 1);
        else if (backEarth.isScrolledLeft()) backEarth.reset(frontEarth.getTailX() - 1);
        frontEarth.scrollSpeed(-30 * scroll_speed);
        backEarth.scrollSpeed(-30 * scroll_speed);

        // Поля
        frontField.update(deltaTime);
        backField.update(deltaTime);
        if (frontField.isScrolledLeft()) frontField.reset(backField.getTailX() - 1);
        else if (backField.isScrolledLeft()) backField.reset(frontField.getTailX() - 1);
        frontField.scrollSpeed(-5 * scroll_speed);
        backField.scrollSpeed(-5 * scroll_speed);

        // Горы
        frontMountains.update(deltaTime);
        backMountains.update(deltaTime);
        if (frontMountains.isScrolledLeft()) frontMountains.reset(backMountains.getTailX() - 1);
        else if (backMountains.isScrolledLeft()) backMountains.reset(frontMountains.getTailX() - 1);
        frontMountains.scrollSpeed(-0.2f * scroll_speed);
        backMountains.scrollSpeed(-0.2f * scroll_speed);

        // Смена сцен
        frontScene.update(deltaTime);
        backScene.update(deltaTime);
        if (frontScene.isScrolledLeft()) {
            frontScene.reset(backScene.getTailX() - 1);
            randomScene = random.nextInt(ALL_SCENES) + 1;
            randomScene2 = random.nextInt(ALL_SCENES_2) + 1;
            addScene = true;
        }
        else if (backScene.isScrolledLeft()) {
            backScene.reset(frontScene.getTailX() - 1);
            addSceneFly = true;
        }
        frontScene.scrollSpeed(-30 * scroll_speed);
        backScene.scrollSpeed(-30 * scroll_speed);
    }

    public Earth getFrontEarth() {
        return frontEarth;
    }

    public Earth getBackEarth() {
        return backEarth;
    }

    public Field getFrontField() {
        return frontField;
    }

    public Field getBackField() {
        return backField;
    }

    public Mountains getFrontMountains() {
        return frontMountains;
    }

    public Mountains getBackMountains() {
        return backMountains;
    }

    public Scene getFrontScene() {
        return frontScene;
    }

    public Scene getBackScene() {
        return backScene;
    }

    public int getRandomScene() {
        return randomScene;
    }

    public int getRandomScene2() {
        return randomScene2;
    }

    public boolean isAddScene() {
        return addScene;
    }

    public void setAddScene(boolean addScene) {
        this.addScene = addScene;
    }

    public boolean isAddSceneFly() {
        return addSceneFly;
    }

    public void setAddSceneFly(boolean addSceneFly) {
        this.addSceneFly = addSceneFly;
    }
}