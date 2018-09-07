package studio.rashka.Lib;

import studio.rashka.RuDragonGame;

public class Monitor {

    private float ratioMonitorW = 0, ratioMonitorH = 0;
    private static int mWIDTH = RuDragonGame.WIDTH, mHEIGHT = RuDragonGame.HEIGHT;

    public Monitor() {
        onWidth();
        onHeight();
        if (ratioMonitorW != ratioMonitorH) {
            RuDragonGame.setHEIGHT(1200);
            mHEIGHT = RuDragonGame.HEIGHT;
            ratioMonitorW = 0;
            ratioMonitorH = 0;
            onWidth();
            onHeight();
        }
    }

    private void onWidth() {
        if (RuDragonGame.getPreference().width() == 0) ratioMonitorW = (float) (RuDragonGame.WIDTH / 2) / (float) mWIDTH;
        else ratioMonitorW = (float) RuDragonGame.getPreference().width() / (float) mWIDTH;
    }

    private void onHeight() {
        if (RuDragonGame.getPreference().height() == 0) ratioMonitorH = (float) (RuDragonGame.HEIGHT / 2) / (float) mHEIGHT;
        else ratioMonitorH = (float) RuDragonGame.getPreference().height() / (float) mHEIGHT;
    }

    public float getRatioMonitorW() {
        return ratioMonitorW;
    }

    public float getRatioMonitorH() {
        return ratioMonitorH;
    }
}