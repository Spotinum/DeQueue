/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.datastructure;

import java.util.Iterator;


public class App {

    public static void main(String[] args) {
            DequeueImpl<Integer> q = new DequeueImpl<>();
       for (int i = 0; i < 100; i++) {
                System.out.println("Adding element " + i + " to First with array length: "+ q.Length());
                q.pushFirst(i);
            }
       
            while (!q.isEmpty()) {
                System.out.println("Next element served from queue: " + q.popFirst()+" With array length: "+ q.Length());
            }

         
    }
}
