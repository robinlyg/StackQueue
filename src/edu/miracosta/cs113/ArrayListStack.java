package edu.miracosta.cs113;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayListStack<E> implements StackInterface<E>{

    List<E> data;
    Node topOfStackRef = null;


    public ArrayListStack()
    {
        data = new ArrayList<>();
    }

    @Override
    public boolean empty() {
       return data.size() == 0;
    }

    @Override
    public E peek() {
        if(empty())
        {throw new EmptyStackException();}

        return data.get(data.size()-1);
    }

    @Override
    public E pop() {
        if(empty()) {throw new EmptyStackException();}

        return data.remove(data.size()-1);
    }

    /**
     * Pushes an item onto the top of the stack and returns the item pushed.
     *
     * @param obj object to push onto top of stack
     * @return item that was pushed
     */
    @Override
    public E push(E obj) {
        data.add(obj);
        return obj;
    }


}
