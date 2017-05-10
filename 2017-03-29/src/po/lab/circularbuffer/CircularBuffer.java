package po.lab.circularbuffer;

import java.util.function.Function;


/**
 * Circular buffer.
 * @param <T> type of objects stored in buffer.
 */
public class CircularBuffer<T> {
    private T[] buffer = null;
    private CircularIndexSpace indexSpace = null;

    /**
     * Create new circular buffer with allocated space for given number of elements.
     * @param allocationSize initial capacity of circular buffer
     */
    CircularBuffer(int allocationSize) {
        realloc(allocationSize);
        indexSpace = new CircularIndexSpace(allocationSize);
    }

    /**
     * Get n-th element in buffer. If <c>n >= 0</c> return (n+1)-th element in container. If <c>n < 0</c> return (-n)-th
     * last element in container.
     * @param index index of element
     * @return n-th (or (-n)-th last) element of buffer
     */
    public T get(int index) {
        return buffer[indexSpace.offsetIndex(index)];
    }

    /**
     * Set n-th element in buffer. If <c>n >= 0</c> set (n+1)-th element in container. If <c>n < 0</c> set (-n)-th
     * last element in container.
     * @param index index of element
     * @param value new value for this element
     */
    public void set(int index, T value) {
        buffer[indexSpace.offsetIndex(index)] = value;
    }

    /**
     * Prepend circular buffer. Reallocate if no free space left.
     * @param element value for new element
     */
    public void pushFront(T element) {
        if (indexSpace.freeSpace() == 0)
            realloc(capacity() * 2);
        indexSpace.decFront();
        set(0, element);
    }

    /**
     * Remove first element of buffer.
     */
    public void popFront() {
        if (indexSpace.length() == 0) {
            throw new IllegalStateException("Cannot pop empty array");
        }
        indexSpace.incFront();
    }

    /**
     * Append circular buffer. Reallocate if no free space left.
     * @param element value for new element
     */
    public void pushBack(T element) {
        if (indexSpace.freeSpace() == 0)
            realloc(capacity() * 2);
        indexSpace.incBack();
        set(-1, element);
    }

    /**
     * Remove last element of buffer.
     */
    public void popBack() {
        if (indexSpace.length() == 0) {
            throw new IllegalStateException("Cannot pop empty array");
        }
        indexSpace.decBack();
    }

    /**
     * Maximal number of elements that can be stored in container without reallocation.
     * @return capacity of container
     */
    public int capacity() {
        return buffer.length;
    }

    /**
     * Resize container. Size can be smaller than current capacity, but it must be at minimum equal to length.
     * @param newSize new size for container
     */
    private void realloc(int newSize) {
        assert newSize >= indexSpace.length() || buffer == null;
        T[] new_buffer = (T[])new Object[newSize];

        if (buffer != null) {
            if (indexSpace.first() + indexSpace.length()> capacity()) {
                int suffix_length = indexSpace.capacity() - indexSpace.first();
                System.arraycopy(buffer, indexSpace.first(), new_buffer, 0, suffix_length);
                System.arraycopy(buffer, 0, new_buffer, suffix_length, indexSpace.length() - suffix_length);
            } else {
                System.arraycopy(buffer, indexSpace.first(), new_buffer, 0, indexSpace.length());
            }
        }

        buffer = new_buffer;
        indexSpace = new CircularIndexSpace(newSize);
    }

    /**
     * Like <c>fold_left</c> in OCaml. Supposing that f := reductionLambda, a_0 := initialAccumulator
     * and e_0, e_1, ..., e_n are elemtns of circular buffer this function computes and returns:
     * f( ... f(f(a_0, e_0), e_1), ... e_n)
     * @param reductionLambda f
     * @param initialAccumulator a_0
     * @param <AccT> type of accumulator
     * @return f( ... f(f(a_0, e_0), e_1), ... e_n)
     */
    <AccT> AccT reduceLeft(Function<AccT, Function<T, AccT>> reductionLambda, AccT initialAccumulator) {
        AccT accumulator = initialAccumulator;
        for (int p = 0; p < indexSpace.length(); ++p) {
            accumulator = reductionLambda.apply(accumulator).apply(get(p));
        }
        return accumulator;
    }

    /**
     * Like reduceLeft on reversed but in reversed order. Supposing that f := reductionLambda, a_0 := initialAccumulator
     * and e_0, e_1, ..., e_n are elemtns of circular buffer this function computes and returns:
     * f( ... f(f(a_0, e_n), e_{n-1}), ... e_0)
     * @param reductionLambda f
     * @param initialAccumulator a_0
     * @param <AccT> type of accumulator
     * @return f( ... f(f(a_0, e_n), e_{n-1}), ... e_0)
     */
    <AccT> AccT reduceRight(Function<AccT, Function<T, AccT>> reductionLambda, AccT initialAccumulator) {
        AccT accumulator = initialAccumulator;
        for (int p = indexSpace.length() - 1; p >= 0; --p) {
            accumulator = reductionLambda.apply(accumulator).apply(get(p));
        }
        return accumulator;
    }
}
