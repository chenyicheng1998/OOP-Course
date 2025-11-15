import java.util.ArrayList;
import java.util.Iterator;

public class ThreadSafeCollection<T> {
    private ArrayList<T> collection;

    public ThreadSafeCollection() {
        collection = new ArrayList<>();
    }

    public synchronized void addElement(T element) {
        collection.add(element);
        System.out.println(Thread.currentThread().getName() + " added: " + element);
    }

    public synchronized boolean removeElement(T element) {
        boolean removed = collection.remove(element);
        if (removed) {
            System.out.println(Thread.currentThread().getName() + " removed: " + element);
        }
        return removed;
    }

    public synchronized int getSize() {
        return collection.size();
    }

    public synchronized boolean containsElement(T element) {
        return collection.contains(element);
    }

    public synchronized void displayCollection() {
        System.out.print(Thread.currentThread().getName() + " - Current collection: [");
        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public synchronized void clearCollection() {
        collection.clear();
        System.out.println(Thread.currentThread().getName() + " cleared the collection");
    }
}