import java.lang.reflect.Array;

public class MyArrayList<T> implements MyList<T> {
    private final static int DEFAULT_SIZE = 8;

    private Class<T[]> arrType;
    private Class dataType;
    private T[] myArr;
    private int sz;
    private int cap;

    // Construction happens internally
    private MyArrayList(Class<T[]> arrClass, int length) {
        arrType = arrClass;
        dataType = arrClass.getComponentType();
        myArr = arrClass.cast(Array.newInstance(dataType, length));
        sz = 0;
        cap = length;
    }

    // Constructor called by user
    public MyArrayList(Class<T[]> arrClass) {
        this(arrClass, DEFAULT_SIZE);
    }

    public boolean add(T element) {
        //if the array size is greater than the cap minus 1 (accounting for index 0) then double the array
        if (sz > (cap-1)) {
            doubleArray();
        }
        //once we have enough space then set the element to the last position in the array
        myArr[sz] = element; 
        //increase the size to track the last index and latest array size       
        sz++;
        return true; // (as specified by Collection.add(E))
    }

    public void add(int index, T element) {
        if (index < 0 || sz < index) {
            throw new IndexOutOfBoundsException();
        }
        //check to make sure the array has room to shift right, if not then double
        if (sz > (cap-1)) {
            doubleArray();
        }
        //with enough space then we shift everything right from the index provided
        shiftRight(index);
        //then we assign the index to the element provided
        myArr[index] = element;
        //increase the size to track the last index and array size
        sz++;

    }

    public void clear() {
        //set myArr to a new array with default size
        myArr = createNewArray(DEFAULT_SIZE);
    }

    public boolean contains(T element) {
        //new variable to check if element is in the array
        boolean contains = false;
        //for each element loop through and check against the element provided
        for(T e: myArr){
            if( e == element){
                contains = true;
            }
        }
        return contains;
    }

    public T get(int index) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }
        //If it's not out of bounds then return the element at the provided index
        return myArr[index];
    }

    public int indexOf(T element) {
        // ...
        return -1;
    }

    public boolean isEmpty() {
        // ...
        return true;
    }

    public int lastIndexOf(T element) {
        // ...
        return -1;
    }

    public T remove(int index) {
        T ret = null;
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }
        // ...
        return ret;
    }

    public boolean remove(T element) {
        // ...
        return false;
    }

    public T set(int index, T element) {
        if (index < 0 || sz <= index) {
            throw new IndexOutOfBoundsException();
        }
        T ret = null;
        // ...
        return ret;
    }

    public int size() {
        return sz;
    }

    // Helper functions
    private void doubleArray() {
        cap *= 2;
        T[] myDoubledArr = createNewArray(cap);

        for (int i = 0; i < sz; ++i) {
            myDoubledArr[i] = myArr[i];
        }

        myArr = myDoubledArr;
    }

    private T[] createNewArray(int length) {
        //confused on what this is doing, are we changing the reference to the original array?
        T[] myArr = arrType.cast(Array.newInstance(dataType, length));
        return myArr;
    }

    private void halveArray() {
        cap /= 2;
        T[] myHalvedArr = createNewArray(cap);

        for (int i = 0; i < sz; ++i) {
            myHalvedArr[i] = myArr[i];
        }

        myArr = myHalvedArr;
    }

    private void shiftRight(int index) {
        for (int i = sz - 1; i >= index; --i) {
            myArr[i + 1] = myArr[i];
        }
    }

    private void shiftLeft(int index) {
        for (int i = index; i < sz; ++i) {
            myArr[i - 1] = myArr[i];
        }
    }
}

/**
 * Usage in main():
 * MyArrayList<Integer> myFirstArr = new MyArrayList<Integer>(Integer[].class);
 * myFirstArr.add(5);
 * ...
 *
 * MyArrayList<String> mySecondArr = new MyArrayList<String>(String[].class);
 * mySecondArr.add("Hello World");
 * ...
 */
