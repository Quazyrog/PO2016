package po.lab.circularbuffer;


/**
 * Class used for managing indices of circular buffer (it can compute index of nth circular buffer element).
 */
class CircularIndexSpace {
    private int capacity;
    private int firstIndex = 0;
    private int length = 0;

    /**
     * Initialise {@link CircularIndexSpace} and set it's capacity
     * @param capacity allocation size of CircularBuffer
     */
    CircularIndexSpace(int capacity) {
        assert capacity > 0;
        this.capacity = capacity;
    }

    /**
     * Return capacity.
     * @return capacity used for creation of this object
     */
    int capacity() {
        return capacity;
    }

    /**
     * Return number of elements currently stored in circular buffer
     * @return number of elements in circular buffer
     */
    int length() {
        return length;
    }

    /**
     * Return in-buffer index of n-th element in container. When <c>offset < 0</c>, return index of (-n-1)-th
     * last element.
     * @param offset index in circular buffer
     * @return index in internal array
     */
    int offsetIndex(int offset) {
        if (offset < 0) {
            offset = length + offset;
        }
        if (offset >= length || offset < -length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (firstIndex + offset) % capacity();
    }

    /**
     * Return index of first element in circular buffer (same as calling <c>offsetIndex(0)</c>)
     * @return index of first element in circular buffer.
     */
    int first() {
        return offsetIndex(0);
    }

    /**
     * Return index of last element in circular buffer (same as calling <c>offsetIndex(-1)</c>)
     * @return index of last element in circular buffer.
     */
    int last() {
        return offsetIndex(-1);
    }

    /**
     * Decrement pointer to first element; rotate if needed. Require that <c>length() < capacity()</c>.
     * After this operation <c>first()</c> points to unused index in array, so new element can be prepended.
     */
    void decFront() {
        assert length < capacity();
        --firstIndex;
        ++length;
        if (firstIndex == -1)
            firstIndex = capacity() - 1;
    }

    /**
     * Increment pointer to first element; rotate if needed. Require that <c>length() > 0</c>.
     * After this operation <c>first()</c> points to previously second index in array, so it equals to deletion of first
     * element.
     */
    void incFront() {
        assert length > 0;
        ++firstIndex;
        --length;
        if (firstIndex == capacity())
            firstIndex = 0;
    }

    /**
     * Decrement pointer to last element; rotate if needed. Require that <c>length() > 0</c>.
     * It works like deletion of last element in circular buffer.
     */
    void decBack() {
        assert length > 0;
        --length;
    }

    /**
     * Increment pointer to last element; rotate if needed. Require that <c>length() < capacity()</c>.
     * After this operation <c>last()</c> points to unused index in array, so new element can be appended.
     */
    void incBack() {
        assert length < capacity();
        ++length;
    }

    /**
     * Return number of unused elements in internal array (<c>capacity() - length()</c>)
     * @return number of unused elements in internal array
     */
    int freeSpace() {
        return capacity - length;
    }
}