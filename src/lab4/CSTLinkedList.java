package lab4;

import java.util.List;
import java.util.Objects;

public class CSTLinkedList<R> implements CSTList<R> {
    private Node<R> head;
    private Node<R> tail;
    private int size;

    /**
     * is empty
     */
    public CSTLinkedList() {
        size = 0;
    }

    /**
     * create a list by copying the data from given list
     *
     * @param list - source of data to be copied
     */
    //Initial constructor
    public CSTLinkedList(List<R> list) {
        for (R l : list)
            addLast(l);
    }

    /**
     * return the node before the given index. very similar to {@link CSTList#get(int)}.
     * can be used in other methods like insert and remove to get the node before index.
     *
     * @param index - index of the node to find the node before it
     * @return the node before the index of the given index. if index is zero or size, null.
     * @throws IndexOutOfBoundsException if given index is not valid
     */
    private Node<R> getNodeBefore(int index) {
        isIndexValid(index);
        Node<R> tempNode = head;
        //get the element of index 0 
        if (index == 0)
            return null;

        if (index - 1 == 0) {
            return tempNode;
        }
        for (int i = 0; i < index - 1; i++) {
            tempNode = tempNode.next;
        }
        return tempNode;
    }

    /**
     * if index is not between zero and list size throw an exception.
     * can be used in any method that takes an argument of int index to error check.
     *
     * @param index - index to check
     * @throws IndexOutOfBoundsException if index is not between zero and list size
     */
    private void isIndexValid(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException("Index is not between zero and list size.");
    }

    @Override
    public String toString() {
        return "CSTLinkedList{" +
                "head='" + head + '\'' +
                ", tail='" + tail + '\'' +
                ", size='" + size +
                '}';
    }

    public static void main(String[] args) {
        CSTLinkedList<String> list = new CSTLinkedList<>();
        list.addLast("1");
        list.addLast("2");
        System.out.println(list.remove(1));
    }

    //inner class Node
    private class Node<T> {
        private Node<T> next;
        private T element;

        private Node(T t) {
            this.element = t;
        }

        private Node(T t, Node<T> next) {
            this.element = t;
            this.next = next;
        }

        @Override
        public int hashCode() {
            return Objects.hash(next, element);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Node)) return false;
            Node<T> node = (Node) obj;
            return next == node.next && Objects.equals(next, element);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "next='" + next + '\'' +
                    ", element='" + element + '}';
        }
    }

    /**
     * add the the given element to the beginning of the list.
     * can simply call the methods {@link CSTLinkedList#insert(Object, int)}.
     *
     * @param r - the new element to be added
     */
    @Override
    public void addFirst(R r) {
        Node<R> tempNode = new Node<R>(r);
        if (size == 0)
            head = tail = tempNode;

        if (size > 0) {
            tempNode.next = head;
            head = tempNode;
        }
        size++;
    }

    /**
     * add the the given element to the end of the list
     * can simply call the method {@link CSTList#insert(Object, int)}.
     *
     * @param r - the new element to be added
     */
    @Override
    public void addLast(R r) {
        Node<R> tempNode = new Node<R>(r);
        if (size == 0) {
            head = tempNode;
            tail = tempNode;
        }
        if (size > 0) {
            tail.next = tempNode;
            tail = tempNode;
        }
        size++;
    }

    /**
     * add the the given element to the at provided index. the old index will be pushed forward.
     *
     * @param r     - the new element to be added
     * @param index - index of position to be inserted
     */
    @Override
    public void insert(R r, int index) {
        isIndexValid(index);
        Node<R> tempNode = new Node<R>(r);

        if (size == 0) {
            head = tail = tempNode;
        } else if (index == size) {
            tail.next = tempNode;
            tail = tempNode;
        } else if (index == 0) {
            tempNode.next = head;
            head = tempNode;
        } else {
            Node<R> n = getNodeBefore(index);
            Node<R> insert_element = new Node<R>(r);
            if (n == null) {
                n.next = insert_element;
            } else {
                insert_element.next = n.next;
                n.next = insert_element;
            }
        }
        size++;
    }

    /**
     * remove the first element.
     * can simply call the method {@link CSTList#remove(int)}.
     *
     * @return removed element
     */
    @Override
    public R removeFirst() {
        return remove(0);
    }

    /**
     * remove the last element.
     * can simply call the method {@link CSTList#remove(int)}.
     *
     * @return removed element
     */
    @Override
    public R removeLast() {
        if (size >= 1)
            return remove(size - 1);
        return null;
    }

    /**
     * remove the element at the given index
     *
     * @param index
     * @return element as the given index after removal.
     * @throws IndexOutOfBoundsException if given index is not valid
     */
    @Override
    public R remove(int index) {
        isIndexValid(index);
        Node<R> a = getNodeBefore(index);

        if (size == 0) {
            return null;
        }

        if (index == 0) {
            a = head;
            head = head.next;
            size--;
            return a.element;
        } else {
            Node<R> temp = a.next;
            a.next = temp.next;
            size--;
            return temp.element;
        }

    }

    /**
     * @return the first element in the list.
     * can simply call the method {@link CSTList#get(int)}.
     * @throws IndexOutOfBoundsException if given index is not valid
     */
    @Override
    public R getFirst() {
        return get(0);
    }

    /**
     * @return the last element in the list.
     * can simply call the method {@link CSTList#get(int)}.
     * @throws IndexOutOfBoundsException if given index is not valid
     */
    @Override
    public R getLast() {
        if (size >= 1)
            return get(size - 1);
        return null;
    }

    /**
     * @param index
     * @return the element as given index,
     * @throws IndexOutOfBoundsException if given index is not valid
     */
    @Override
    public R get(int index) {
        isIndexValid(index);
        if (isEmpty()) {
            return null;
        }

        if (index == size - 1) {
            return tail.element;
        }

        if (index == 0) {
            return head.element;
        }

        Node<R> n = head;
        for (int i = 1; i <= index; i++) {
            n = n.next;
            if (i == index) {
                return n.element;
            }
        }
        return null;
    }

    /**
     * @return true of list is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return current size of list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * clear the current list by setting head and tail to null and size to zero.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}