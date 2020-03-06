package animation;

/**
 * @author Tom Sendrovich.
 * @param <T> - a generics Type.
 */
public interface Task<T> {
    /**
     *
     * @return the object who needs to be running.
     */
    T run();
}
