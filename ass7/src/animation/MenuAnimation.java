package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Sendrovich.
 * @param <T> - a generics Type.
 */
public class MenuAnimation<T> implements Menu<T> {
    private String menuName;
    private AnimationRunner runner;
    private List<String> menuKeys;
    private List<String> menuMessages;
    private List<T> menuReturnVal;
    private KeyboardSensor keyboardSensor;
    private T status;
    private List<Boolean> isSubMenu;
    private List<Menu<T>> subMenus;

    /**
     * Constructor.
     * @param menuName - the menu's name.
     * @param keyboardSensor - a KeyboardSensor object.
     * @param runner - an AnimationRunner object.
     */
    public MenuAnimation(String menuName, KeyboardSensor keyboardSensor, AnimationRunner runner) {
        this.menuName = menuName;
        this.keyboardSensor = keyboardSensor;
        this.runner = runner;
        this.menuKeys = new ArrayList<>();
        this.menuMessages = new ArrayList<>();
        this.menuReturnVal = new ArrayList<>();
        this.isSubMenu = new ArrayList<>();
        this.subMenus = new ArrayList<>();
        resetStatus();
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        menuKeys.add(key);
        menuMessages.add(message);
        menuReturnVal.add(returnVal);
        isSubMenu.add(false);

    }

    @Override
    public T getStatus() {
        return status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        menuKeys.add(key);
        menuMessages.add(message);
        menuReturnVal.add(null);
        isSubMenu.add(true);
        subMenus.add(subMenu);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(250, 100, "Arkanoid", 62);
        d.drawLine(0, 110, 800, 110);
        d.drawLine(0, 112, 800, 112);
        d.drawLine(0, 114, 800, 114);
        int j = 40;
        for (int i = 0; i < menuKeys.size(); i++) {
            //todo subMenu implementation
            d.drawText(55, (d.getHeight() / 5) + j, "(" + menuKeys.get(i) + ") " + menuMessages.get(i), 35);
            j += 70;
        }
        for (int i = 0; i < menuKeys.size(); i++) {
            if (keyboardSensor.isPressed((String) menuKeys.get(i))) {
                if (!(isSubMenu.get(i)).booleanValue()) {
                    status = menuReturnVal.get(i);
                    break;
                }
                Menu<T> subMenu = subMenus.get(i);
                runner.run(subMenu);
                status = subMenu.getStatus();
                break;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return status != null;
    }

    /**
     * reset the status field.
     */
    private void resetStatus() {
        this.status = null;
    }
}
