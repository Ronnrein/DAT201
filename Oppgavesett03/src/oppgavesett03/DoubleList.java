package oppgavesett03;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Ronnrein
 * @param <E> Type to be stored
 */
public class DoubleList<E extends Comparable<E>> implements Iterable<DoubleNode<E>>, Serializable{
    
    private DoubleNode<E> head;
    private DoubleNode<E> tail;
    private int size;
    
    public DoubleList(){
        head = new DoubleNode<>(null);
        tail = new DoubleNode<>(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }
    
    public void addFirst(E value){
        addAfter(head, value);
    }
    
    public void addLast(E value){
        addBefore(tail, value);
    }
    
    public void addAfter(DoubleNode<E> node, E value){
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
    
    public void addBefore(DoubleNode<E> node, E value){
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
    
    public void add(E value){
        DoubleNode<E> closest = lowestValue();
        if(size == 0 || closest.getValue().compareTo(value) > 0){
            addFirst(value);
            return;
        }
        for(DoubleNode<E> current : this){
            if(current.getValue().compareTo(value) < 0 &&
               current.getValue().compareTo(closest.getValue()) > 0){
                closest = current;
            }
        }
        addAfter(closest, value);
    }
    
    public void clear(){
        for(DoubleNode<E> node : this){
            remove(node);
        }
    }
    
    public boolean remove(E value){
        boolean removed = false;
        for(DoubleNode<E> node : this){
            if(value.equals(node.getValue())){
                remove(node);
                removed = true;
            }
        }
        return removed;
    }
    
    public boolean remove(DoubleNode<E> n){
        if(contains(n)){
            n.getPrev().setNext(n.getNext());
            n.getNext().setPrev(n.getPrev());
            n = null;
            size--;
            return true;
        }
        return false;
    }
    
    public DoubleNode<E> find(E value){
        for(DoubleNode<E> node : this){
            if(value.equals(node.getValue())){
                return node;
            }
        }
        return null;
    }
    
    public boolean contains(DoubleNode<E> n){
        for(DoubleNode<E> node : this){
            if(n == node){
                return true;
            }
        }
        return false;
    }
    
    public DoubleNode<E> lowestValue(){
        DoubleNode<E> lowest = head.getNext();
        for(DoubleNode<E> node : this){
            if(node.getValue().compareTo(lowest.getValue()) < 0){
                lowest = node;
            }
        }
        return lowest;
    }
    
    public int size(){
        return size;
    }
    
    @Override
    public String toString(){
        String result = size+" items: {";
        for(DoubleNode<E> current : this){
            result += current.getValue()+", ";
        }
        if(result.endsWith(", ")){
            result = result.substring(0, result.length()-2);
        }
        result += "}";
        return result;
    }

    @Override
    public Iterator<DoubleNode<E>> iterator() {
        Iterator<DoubleNode<E>> i = new Iterator<DoubleNode<E>>(){
            private DoubleNode<E> current = head.getNext();
            
            @Override
            public boolean hasNext(){
                return current != null && current != tail;
            }
            
            @Override
            public DoubleNode<E> next(){
                DoubleNode<E> next = current;
                current = current.getNext();
                return next;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet");
            }
            
            
        };
        return i;
    }
    
}

/**
 *
 * @author Ronnrein
 * @param <E> Type to be stored
 */
class DoubleNode<E extends Comparable<E>> implements Serializable {
    
    private E value;
    private DoubleNode<E> prev;
    private DoubleNode<E> next;
    
    public DoubleNode(E value){
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public DoubleNode<E> getPrev() {
        return prev;
    }

    public void setPrev(DoubleNode<E> prev) {
        this.prev = prev;
    }

    public DoubleNode<E> getNext() {
        return next;
    }

    public void setNext(DoubleNode<E> next) {
        this.next = next;
    }
    
}

