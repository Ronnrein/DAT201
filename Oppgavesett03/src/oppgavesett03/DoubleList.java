package oppgavesett03;

import java.io.Serializable;
import java.util.Iterator;

/**
 * A doubly linked list
 * @author Ronny Reinhardtsen
 * @param <E> Type to be stored
 */
public class DoubleList<E extends Comparable<E>> implements Iterable<E>, Serializable{

    private DoubleNode<E> head;
    private DoubleNode<E> tail;
    private int size;

    /**
     * Constructor
     */
    public DoubleList(){
        head = new DoubleNode<>(null);
        tail = new DoubleNode<>(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /**
     * Add an item at the start of the list
     * @param value The value to be added
     */
    public void addFirst(E value){
        addAfter(head, value);
    }

    /**
     * Add an item at the end of the list
     * @param value The value to be added
     */
    public void addLast(E value){
        addBefore(tail, value);
    }

    /**
     * Add an item after the supplied node
     * @param node The node to add after
     * @param value The value to add
     */
    private void addAfter(DoubleNode<E> node, E value){
        if(node == tail){
            throw new IllegalArgumentException("Cannot add node after tail");
        }
        DoubleNode<E> n = new DoubleNode<>(value);
        node.getNext().setPrev(n);
        n.setNext(node.getNext());
        node.setNext(n);
        n.setPrev(node);
        size++;
    }

    /**
     * Add an item before the supplied node
     * @param node The node to add before
     * @param value The value to add
     */
    private void addBefore(DoubleNode<E> node, E value){
        if(node == head){
            throw new IllegalArgumentException("Cannot add node before head");
        }
        DoubleNode<E> n = new DoubleNode<>(value);
        node.getPrev().setNext(n);
        n.setPrev(node.getPrev());
        node.setPrev(n);
        n.setNext(node);
        size++;
    }

    /**
     * Add an item in rising order
     * @param value The value to be added
     */
    public void add(E value){
        DoubleNode<E> closest = lowest();
        if(size == 0 || closest.getValue().compareTo(value) > 0){
            addFirst(value);
            return;
        }
        Iterator<DoubleNode<E>> i = nodeIterator();
        while(i.hasNext()){
            DoubleNode<E> current = i.next();
            if(current.getValue().compareTo(value) < 0 &&
                    current.getValue().compareTo(closest.getValue()) > 0){
                closest = current;
            }
        }
        addAfter(closest, value);
    }

    /**
     * Clear the list of all items
     */
    public void clear(){
        Iterator<DoubleNode<E>> i = nodeIterator();
        while(i.hasNext()){
            removeNode(i.next());
        }
    }

    /**
     * Remove all items with matching value
     * @param value The value to be removed
     * @return Whether one or more items were removed
     */
    public boolean remove(E value){
        boolean removed = false;
        Iterator<DoubleNode<E>> i = nodeIterator();
        while(i.hasNext()){
            DoubleNode<E> current = i.next();
            if(value.equals(current.getValue())){
                removeNode(current);
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Check if the list contains an item with matching value
     * @param value The value to be checked
     * @return Whether the value was found in the list
     */
    public boolean find(E value){
        for(E node : this){
            if(value.equals(node)){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the items with the lowest value in the list
     * @return The lowest valued item
     */
    public E lowestValue(){
        return lowest().getValue();
    }

    /**
     * Returns the item with the highest value in the list
     * @return The highest valued item
     */
    public E highestValue(){
        return highest().getValue();
    }

    /**
     * Get the first item in the list
     * @return The first item in the list
     */
    public E getFirst(){
        return head.getNext().getValue();
    }

    /**
     * Get the last item in the list
     * @return The last item in the list
     */
    public E getLast(){
        return tail.getPrev().getValue();
    }

    /**
     * Get the size of the list
     * @return The size of the list
     */
    public int size(){
        return size;
    }

    /**
     * Remove the supplied node
     * @param n The node to be removed
     * @return Whether node was removed (If it returns false, the node does not exist in the list)
     */
    private boolean removeNode(DoubleNode<E> n){
        if(contains(n)){
            n.getPrev().setNext(n.getNext());
            n.getNext().setPrev(n.getPrev());
            n = null;
            size--;
            return true;
        }
        return false;
    }

    /**
     * Checks if supplied node exists in this list
     * @param n The node to be checked
     * @return Whether the node exists in the list or not
     */
    private boolean contains(DoubleNode<E> n){
        Iterator<DoubleNode<E>> i = nodeIterator();
        while(i.hasNext()){
            if(i.next() == n){
                return true;
            }
        }
        return false;
    }

    /**
     * Get the node with the lowest value in the list
     * @return The node with the lowest item value
     */
    private DoubleNode<E> lowest(){
        DoubleNode<E> lowest = head.getNext();
        Iterator<DoubleNode<E>> i = nodeIterator();
        while(i.hasNext()){
            DoubleNode<E> current = i.next();
            if(current.getValue().compareTo(lowest.getValue()) < 0){
                lowest = current;
            }
        }
        return lowest;
    }

    /**
     * Get the node with the highest value in the list
     * @return The node with the highest item value
     */
    private DoubleNode<E> highest(){
        DoubleNode<E> highest = head.getNext();
        Iterator<DoubleNode<E>> i = nodeIterator();
        while(i.hasNext()){
            DoubleNode<E> current = i.next();
            if(current.getValue().compareTo(highest.getValue()) > 0){
                highest = current;
            }
        }
        return highest;
    }

    /**
     * Returns the content of the list as a formatted string
     * @return
     */
    @Override
    public String toString(){
        String result = size+" items: {";
        for(E current : this){
            result += current+", ";
        }
        if(result.endsWith(", ")){
            result = result.substring(0, result.length()-2);
        }
        result += "}";
        return result;
    }

    /**
     * Private iterator, used to loop through nodes, not items
     * @return
     */
    private Iterator<DoubleNode<E>> nodeIterator() {
        Iterator<DoubleNode<E>> i = new Iterator<DoubleNode<E>>(){
            private DoubleNode<E> current = head.getNext();
            private DoubleNode<E> prev = head;
            
            @Override
            public boolean hasNext(){
                return current != null && current != tail;
            }
            
            @Override
            public DoubleNode<E> next(){
                prev = current;
                current = current.getNext();
                return prev;
            }

            @Override
            public void remove() {
                removeNode(prev);
            }
            
            
        };
        return i;
    }

    /**
     * Public iterator, used to loop through items, not nodes
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        Iterator<E> i = new Iterator<E>(){
            private DoubleNode<E> current = head.getNext();
            private DoubleNode<E> prev = head;

            @Override
            public boolean hasNext(){
                return current != null && current != tail;
            }

            @Override
            public E next(){
                prev = current;
                current = current.getNext();
                return prev.getValue();
            }

            @Override
            public void remove() {
                removeNode(prev);
            }


        };
        return i;
    }
    
}

/**
 * The node class used in the list
 * @author Ronny Reinhardtsen
 * @param <E> Type to be stored
 */
class DoubleNode<E extends Comparable<E>> implements Serializable {
    
    private E value;
    private DoubleNode<E> prev;
    private DoubleNode<E> next;

    /**
     * The constructor
     * @param value The value to be set for this node
     */
    public DoubleNode(E value){
        this.value = value;
    }

    /**
     * Get the value of this node
     * @return The value/item;
     */
    public E getValue() {
        return value;
    }

    /**
     * Set the value of this node
     * @param value The value/item
     */
    public void setValue(E value) {
        this.value = value;
    }

    /**
     * Get the node before this node
     * @return The previous node
     */
    public DoubleNode<E> getPrev() {
        return prev;
    }

    /**
     * Set which node is after this node
     * @param prev The new previous node
     */
    public void setPrev(DoubleNode<E> prev) {
        this.prev = prev;
    }

    /**
     * Get the node after this node
     * @return The next node
     */
    public DoubleNode<E> getNext() {
        return next;
    }

    /**
     * Set which node is before this node
     * @param next The new next node
     */
    public void setNext(DoubleNode<E> next) {
        this.next = next;
    }
    
}

