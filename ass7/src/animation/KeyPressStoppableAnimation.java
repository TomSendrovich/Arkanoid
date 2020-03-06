package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Tom Sendrovich.
 */
public class KeyPressStoppableAnimation implements Animation {
    private String key;
    private KeyboardSensor keyboard;
    private boolean stop;
    private Animation decorated;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     * @param sensor - a KeyboardSensor Object.
     * @param key - a String object.
     * @param animation - an Animation object.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.decorated = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.decorated.doOneFrame(d);
        if (this.keyboard.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
