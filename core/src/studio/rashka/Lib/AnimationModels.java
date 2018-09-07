package studio.rashka.Lib;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class AnimationModels {

    private ArrayList<TextureRegion> frames; // массив хранения кадров анимации
    private float maxFrameTime, maxFrameTimeFendOff; // время отображения одного кадра
    private float currentFrameTime; // время отображения текущего кадра
    private int frameCount; // количество кадров анимации
    private int frame; // кадр анимации
    private int frameWidth, frameHeight; // размеры кадра анимации
    private float cycleTime;

    public AnimationModels(TextureRegion region, int frameCount, float cycleTime) {
        this.cycleTime = cycleTime;
        frames = new ArrayList<TextureRegion>();
        frameWidth = region.getRegionWidth() / frameCount;
        frameHeight = region.getRegionHeight();
        for (int i = 0; i < frameCount; i++) frames.add(new TextureRegion(region, i * frameWidth, region.getRegionY(), frameWidth, frameHeight));
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        maxFrameTimeFendOff = cycleTime * 2;
        frame = 0;
    }

    public void update(float deltaTime) {
        currentFrameTime += deltaTime;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) frame = 0;
    }

    public void updateFendOff(float deltaTime) {
        currentFrameTime += deltaTime;
        if (currentFrameTime > maxFrameTimeFendOff) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= 4) frame = 2;
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }

    public int getFrameCount() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public void setCycleTime(float cycleTime) {
        maxFrameTime = cycleTime / frameCount;
        maxFrameTimeFendOff = cycleTime * 2;
        this.cycleTime = cycleTime;
    }

    public float getCycleTime() {
        return cycleTime;
    }
}