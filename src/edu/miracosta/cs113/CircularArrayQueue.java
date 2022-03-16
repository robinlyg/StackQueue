package edu.miracosta.cs113;

import java.lang.reflect.Array;
import java.util.*;


public class CircularArrayQueue<E> extends AbstractQueue<E> implements Queue<E>
{

    private int front;
    private int rear;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;

    private E[] theData;

    public CircularArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public CircularArrayQueue(int initCapacity){
        capacity = initCapacity;
        theData = (E[]) new Object [capacity];
        front = 0;
        rear = capacity -1;
        size =0;
    }

    /**
     * Returns an iterator over the elements contained in this collection.
     *
     * @return an iterator over the elements contained in this collection
     */
    @Override
    public Iterator<E> iterator() {

        return new Iter();
    }

    @Override
    public int size() {
        return size;
    }

    /**
     Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions.
     When using a capacity-restricted queue, this method is generally preferable to add, which can fail to insert an element only
     by throwing an exception.
     Params:
     e – the element to add
     Returns:
     true if the element was added to this queue, else false
     Throws:
     ClassCastException – if the class of the specified element prevents it from being added to this queue
     NullPointerException – if the specified element is null and this queue does not permit null elements
     IllegalArgumentException – if some property of this element prevents it from being added to this queue
     */

    public boolean add(E e) {
       /*if(size == capacity)
            reallocate();
        //insert at the rear, is located at size
        theData.add(rear,e);
        //shift rear for the next add
        rear++;
        //increase sizes
        size++;
        return true;*/
       return offer(e);
    }



    /**Method offer inserts an element at the rear of the queue and returns true if successful and
     false if unsuccessful*/

    public boolean offer(E e) {
     if(size == capacity){
        reallocate();
     }
     size++;
     rear = (rear +1) % capacity;
     theData[rear] = e;

     return true;
    }

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll() poll()} only in that it throws an exception if
     * this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */

  /*  public E remove() {
        if(isEmpty()) throw new NoSuchElementException();
        E returnValue = theData.get(front);
        theData.remove(front);

        front = 0;
        rear = size-1;

        return returnValue;


    }*/

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */

    public E poll() {
     if(size == 0){
         return null;
     }
     E result = theData[front];
     front = (front +1) % capacity;
     size--;
     return result;
    }

    /**
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */

    public E element() {
        if(size==0) throw new NoSuchElementException();

        return theData[front];
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */

    public E peek() {

        if(size == 0) {
            return null;
        }
        else {
            return theData[front];
        }
    }

    @SuppressWarnings("unchecked")
    private void reallocate(){
        int newCapacity = 2 * capacity;
       E[] newData = (E[]) new Object[newCapacity];
        int j = front;
        for(int i = 0; i <size; i++){
            newData[i] = theData[j];
            j =(j +1) % capacity;
        }

        front =0;
        rear = size-1;
        capacity = newCapacity;
        theData = newData;
    }

    //todo create an inner class
    private class Iter implements Iterator<E> {

        //data fileds
        private int index;
        private int count =0;

        public Iter(){
            index = front;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return count < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            E returnValue = theData[index];
            index = (index +1) % capacity;
            count++;
            return returnValue;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }



}
