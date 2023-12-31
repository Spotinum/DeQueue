/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.datastructure;

import java.util.Iterator;


public class App {

    public static void main(String[] args) {
       DequeueImpl<Integer> q = new DequeueImpl<>();
       q.pushFirst(4);
       q.pushLast(5);
       q.pushLast(6);
       q.pushLast(7);
       q.pushFirst(3);
       q.pushFirst(2);
       q.pushFirst(1);
       
       Iterator<Integer> iterator = q.descendingIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

         
    }
}
