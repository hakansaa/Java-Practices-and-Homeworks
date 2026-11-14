package semester1.practice11;

import java.util.Arrays;

// Навчальна мінімодель ArrayList під капотом
public class SimpleArrayList {
    private Object[] data = new Object[10];
    private int size = 0;

    public void add(Object element) {
        if (size >= data.length) {
            int oldCap = data.length;
            int newCap = oldCap + (oldCap >> 1); // Розширення в 1.5 раза
            data = Arrays.copyOf(data, newCap); // System.arraycopy під капотом
        }
        data[size++] = element;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return data[index];
    }

    public int size() { return size; }
    public int getCapacity() { return data.length; }
}