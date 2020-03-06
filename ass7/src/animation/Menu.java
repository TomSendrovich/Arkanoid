package animation;

/**
 * @author Tom Sendorvich.
 * @param <T> - a generics Type.
 */
public interface Menu<T> extends Animation {
    /**
     * adds a selection to the menu.
     * @param key - the key thats need to be pressed.
     * @param message - the message of the selection.
     * @param returnVal - what will happen when we press the key.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     *
     * @return the status.
     */
    T getStatus();

    /**
     * add a sub menu to the main menu.
     * @param key - the key thats need to be pressed.
     * @param message - the message of the selection.
     * @param subMenu - what will happen when we press the key.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
