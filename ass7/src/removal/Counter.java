package removal;

/**
 * @author Tom Sendrovich.
 */
public class Counter {
    private int count;

    /**
     * Constructor.
     *
     * @param count - an integer.
     */
    public Counter(int count) {
        this.count = count;
    }


    /**
     * add number to current count.
     *
     * @param number - an integer
     */
    public void increase(int number) {
        count += number;
    }


    /**
     * subtract number from current count.
     *
     * @param number - an integer.
     */
    public void decrease(int number) {
        count -= number;
    }


    /**
     * @return get current count.
     */
    public int getValue() {
        return count;
    }
}