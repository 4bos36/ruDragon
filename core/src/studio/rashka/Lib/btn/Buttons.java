package studio.rashka.Lib.btn;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import studio.rashka.RuDragonGame;

public class Buttons extends Button {

    private String name;

    public Buttons(String name, Drawable imageUp, int sizeW, int sizeH, float posX, float posY) {
        super(imageUp);
        super.setSize(sizeW * RuDragonGame.getRatioMonitorW(), sizeH * RuDragonGame.getRatioMonitorH());
        super.setPosition(posX * RuDragonGame.getRatioMonitorW(), posY * RuDragonGame.getRatioMonitorH());
        this.name = name;
    }

    @Override
    public void setSize (float width, float height) {
        super.setSize(width * RuDragonGame.getRatioMonitorW(), height * RuDragonGame.getRatioMonitorH());
    }

    @Override
    public void setPosition (float x, float y) {
        super.setPosition(x * RuDragonGame.getRatioMonitorW(), y * RuDragonGame.getRatioMonitorH());
    }

    public void imgUpdate(Drawable imageUp) {
        super.getStyle().up = imageUp;
    }

    public String getName() {
        return name;
    }
}