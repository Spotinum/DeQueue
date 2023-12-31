/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructure;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class DequeueImpl<E> implements DeQueue<E> {
    
    private Node<E>[] array;
    private int f;
    private int r;
    private int size;
    private volatile int modCount = 0;
    private static final int DEFAULT_CAPACITY = 8;
    
    public DequeueImpl(){
        this.array = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        this.f = 0;
        this.r = -1;
        this.size = 0;
    }
    
    
    
    public int Length(){ //returns the array length just to show the functionality of shrink and doubleSize (used only in main)
        return array.length;
    }
    
    
    private boolean isFull(){
        return size == array.length; // if size is the same as the array size then its full
    }
    
    private void doubleSize(){
       int newCapacity = array.length * 2; //new size is double the original size
       int oldCapacity = array.length; //keeps the old size for later meazures
        Node<E>[] newArray = (Node<E>[]) new Node[newCapacity]; //new array

        for(int i = f; i < array.length; i++){
            newArray[newArray.length - array.length + i] = array[i]; //everything from the end of the original array goes to the end of the new array
        }
         for(int i = r; i >= 0; i--){ //eveything before the rear goes to the begining of the new array
            newArray[i] = array[i];
        }
        

        array = newArray; //old  array takes all the data from the new array
        f = array.length - oldCapacity + f; //front changes
        //rear stays as it is
    }
    
    
    private void shrink(){
        int newCapacity = array.length / 2;
        int oldCapacity = array.length;
        Node<E>[] newArray = (Node<E>[]) new Node[newCapacity];
        
        if(r < newCapacity){ //checks for potential errors

        if(f != 0){ //checks for potential errors
        for(int i = f; i < array.length; i++){
           newArray[Math.abs(i -( oldCapacity - newCapacity))] = array[i];   
        }
        
        }
         for(int i = r; i >= 0; i--){ //eveything before the rear goes to the begining of the new array
            newArray[i] = array[i];
        }
         
         array = newArray; //old  array takes all the data from the new array
         f = Math.abs(f - ( oldCapacity - newCapacity));
        }
    }
 


    
    
    @Override
    public void pushFirst(E elem) {
         if (isFull()) { //checks if its full if it is then resize
            doubleSize();
        }
        Node<E> newNode = new Node<>(elem); //element to be pushed gets inserted in new node
        
          if (isEmpty()) {//if array is empty then the front index gets the value
            array[f] = newNode;
            r = f + 1; //rear is enabled and goes to where front is
          }
          else { //if its not empty
            f = (f - 1 + array.length) % array.length; //using modulo math front goes one back if not on edge, if on edge then goes to end
            array[f] = newNode; //new data is inserted
        }
        size++; //incriment size
    }

    @Override
    public void pushLast(E elem) {
            if (isFull()) { //checks if its full if it is then resize
               doubleSize();
            }
            Node<E> newNode = new Node<>(elem); //element to be pushed gets inserted in new node

              if (isEmpty()) { //if array is empty then the front index gets the value
                 array[f] = newNode;
                 r = f + 1; //rear is enabled and goes to where front is + 1
                 size++;//incriment size
              } 
              else { //if its not empty
                    array[r] = newNode;//new data is inserted
                    size++;//incriment size
                    if (isFull()) { //checks if its full if it is then resize
                        doubleSize();
                     }
                    r = (r + 1) % array.length; //using modulo math rear goes one forward if not on edge, if on edge then goes to start
                }
         
    }

    @Override
    public E popFirst() {
         if (isEmpty()) { //cant pop if empty
            throw new NoSuchElementException();
        }
          if(size <= array.length/4 && array.length != 4){ //cant shrink lower than 4
            shrink();
            }
          E data = array[f].data; //saves data to be popped
          array[f] = null; //removes the value
          f = (f + 1) % array.length; //using modulo math front goes to its previous position
          size--; //size gets reduces
          
          return data; //returns data
    }

    @Override
    public E popLast() {
        if (isEmpty()) { //cant pop if empty
            throw new NoSuchElementException();
        }
        if(size <= array.length/4 && array.length != 4){//cant shrink lower than 4
            shrink();
        }
        E data = array[(r - 1 + array.length) % array.length].data; //saves data to be popped
        array[(r - 1 + array.length) % array.length] = null; //removes the value
        r = (r-1 + array.length) % array.length; //using modulo math rear goes to its previous position
        size--;
        
        return data;
    }

    @Override
    public E first() {  
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return array[f].data; //returns the first data
    }
    


    @Override
    public E last() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[(r - 1 + array.length) % array.length].data;   //returns the last data, usung modulo math because pointer is always infront of value
    }

    @Override
    public boolean isEmpty() {
        return size == 0; //if size = 0 then its empty
    }

    @Override
    public int size() { //returns the size
        return size;
    }

    @Override
    public void clear() { //takes the values of the constructor
        array = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        this.f = 0;
        this.r = -1;
        this.size = 0;
    }

   @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount; //record the modCount when creating the iterator
        return new Iterator<E>() {

            private int currentIndex = f;
            private int visited = 0;

            @Override
            public boolean hasNext() {
                checkForConcurrentModification(); //check for concurrent modifications
                return visited < size;
            }

            @Override
            public E next() {
                checkForConcurrentModification(); //check for concurrent modifications
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E data = array[currentIndex % array.length].data;
                currentIndex = (currentIndex + 1) % array.length;
                visited++;
                return data;
            }

            private void checkForConcurrentModification() { //function that checks concurent modifications
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Concurrent modification detected");
                }
            }
        };
    }

    @Override
    public Iterator<E> descendingIterator() {
        int expectedModCount = modCount; //record the modCount when creating the iterator
        return new Iterator<E>() {
            private int currentIndex = r - 1;
            private int visited = 0;

            @Override
            public boolean hasNext() {
                checkForConcurrentModification(); //check for concurrent modifications
                return visited < size;
            }

            @Override
            public E next() {
                checkForConcurrentModification(); //check for concurrent modifications
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E data = null;
                if (currentIndex != -1) {
                    data = array[currentIndex % array.length].data;
                } else {
                    data = array[array.length - 1].data;
                }

                currentIndex = (currentIndex - 1 + array.length) % array.length;
                visited++;
                return data;
            }

            private void checkForConcurrentModification() { //function that checks concurent modifications
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Concurrent modification detected");
                }
            }
        };
    }
    
    
    private static class Node<E> { //create a class node for the array
        public E data;
        
         public Node(E data) { //for the push function this is needed to pass a value
            this.data = data;
        }
    }
    
}
