package listener;

/**
 * @author Tom Sendrovich.
 */
public interface HitNotifier {

    /**
     * addHitListener - Add hl as a listener to hit events.
     * @param hl - a HitListener object.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener - Remove hl as a listener to hit events.
     * @param hl - a HitListener object.
     */
    void removeHitListener(HitListener hl);
}