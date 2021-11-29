import java.util.*;

class WzimList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = new Object[0];
    private static final Object[] DEFAULT_ELEMENTDATA = new Object[DEFAULT_CAPACITY];
    private int size;
    Object[] elementData;

    public WzimList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public WzimList() {
        this.elementData = DEFAULT_ELEMENTDATA;
    }


    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(elementData, size, a.getClass());
    }

    private void grow(int minCapacity) {
        elementData = Arrays.copyOf(elementData, minCapacity);
    }

    private void grow() {
        grow(size + 1);
    }

    private void add(E e, int siz) {
        if (siz == elementData.length) grow();
        elementData[siz] = e;
        size = siz + 1;
    }

    @Override
    public boolean add(E e) {
        add(e, size);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int ind = indexOf(o);
        if (ind == -1) return false;
        fastRemove(ind);
        return true;
    }

    private void shrink(int maxCapacity) {
        elementData = Arrays.copyOf(elementData, maxCapacity);
    }

    private void shrink() {
        shrink(size - 1);
    }

    private void fastRemove(int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(elementData, i + 1, elementData, i, newSize - i);
        shrink();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if (a.length == 0) return false;
        grow(a.length);
        System.arraycopy(a, 0, elementData, size, a.length);
        size += a.length;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Object[] a = c.toArray();
        if (a.length == 0) return false;
        int temp = a.length + index -1;
        if (temp > size) {
            grow(temp-size);
            size = temp;
        }
        System.arraycopy(a, 0, elementData, index, a.length);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void clear() {
        elementData = DEFAULT_ELEMENTDATA;
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        E oldElement = elementData(index);
        fastRemove(index);
        return oldElement;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    private int indexOfRange(Object o, int start, int end) {
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return lastIndexOfRange(o, 0, size);
    }

    private int lastIndexOfRange(Object o, int start, int end) {
        Object[] es = elementData;
        if (o == null) {
            for (int i = end - 1; i >= start; i--) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = end - 1; i >= start; i--) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor = 0;
        int lastReturned = -1;

        Itr() {}

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            if (cursor >= size) throw new NoSuchElementException();
            return elementData(lastReturned = cursor++);
        }

        public void remove() {
            if (lastReturned < 0) throw new IllegalStateException();
            WzimList.this.remove(lastReturned);
            cursor--;
            lastReturned = -1;
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListItr(index);
    }

    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public E previous() {
            if (previousIndex() < 0) throw new NoSuchElementException();
            return elementData(lastReturned = cursor = previousIndex());
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(E e) {
            if (lastReturned < 0) throw new IllegalStateException();
            WzimList.this.set(lastReturned, e);
        }

        @Override
        public void add(E e) {
            WzimList.this.add(cursor, e);
            cursor++;
            lastReturned = -1;
        }
    }
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        return null;
    }
}