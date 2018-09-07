package studio.rashka.Lib;

import com.badlogic.gdx.utils.TimeUtils;

public class TimeBonus {

    private long startTimeXP;
    private int timeXP = 20;
    private boolean isActiveXP = false;
    private boolean isUpTimeXP = false;

    private long startTimeHAM;
    private int timeHAM = 30;
    private boolean isActiveHAM = false;
    private boolean isUpTimeHAM = false;

    public TimeBonus() {
        startTimeXP = TimeUtils.nanoTime();
        startTimeHAM = TimeUtils.nanoTime();
    }

    public void updateTime() {
        if (timeXP > 0) {
            if (TimeUtils.nanoTime() - startTimeXP > 1000000000) { /* 1,000,000,000ns == one second */
                timeXP--;
                startTimeXP = TimeUtils.nanoTime();
                isUpTimeXP = true;
            }
        } else if (timeXP <= 0) {
            timeXP = 0;
            isActiveXP = true;
        }

        if (timeHAM > 0) {
            if (TimeUtils.nanoTime() - startTimeHAM > 1000000000) { /* 1,000,000,000ns == one second */
                timeHAM--;
                startTimeHAM = TimeUtils.nanoTime();
                isUpTimeHAM = true;
            }
        } else if (timeHAM <= 0) {
            timeHAM = 0;
            isActiveHAM = true;
        }
    }

    public void setTimeResetXP() {
        timeXP = 20;
        isActiveXP = false;
    }

    public void setTimeResetHAM() {
        timeHAM = 30;
        isActiveHAM = false;
    }

    public void setUpTimeXP() {
        isUpTimeXP = false;
    }

    public void setUpTimeHAM() {
        isUpTimeHAM = false;
    }

    public boolean isUpTimeXP() {
        return isUpTimeXP;
    }

    public boolean isUpTimeHAM() {
        return isUpTimeHAM;
    }

    public int getTimeXP() {
        return timeXP;
    }

    public boolean isActiveXP() {
        return isActiveXP;
    }

    public int getTimeHAM() {
        return timeHAM;
    }

    public boolean isActiveHAM() {
        return isActiveHAM;
    }
}