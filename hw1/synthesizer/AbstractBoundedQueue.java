public abstract class AbstractBoundedQueue implements BoundedQueue {
    protected int fillCount;

    protected int capacity;

    public int capacity() {
        return capacity - fillCount.length;
    }

    public int fillCount() {
        return fillCount.length;
    }
}
