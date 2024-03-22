package com.memory.list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo extends Thread {
    static CopyOnWriteArrayList<String> l = new CopyOnWriteArrayList<>();

    public void run() {
        l.add("D");
    }

    public static void main(String[] args) throws InterruptedException {
        l.add("A");
        l.add("B");
        l.add("C");

        // Create a child thread that will modify list l
        CopyOnWriteArrayListDemo obj = new CopyOnWriteArrayListDemo();
        obj.start();
        Thread.sleep(1000);

        // Now we iterate through the arraylist and get exception.
        Iterator it = l.iterator();
        while (it.hasNext()) {
            String s = (String)it.next();
            System.out.println(s);
            Thread.sleep(1000);
        }
        System.out.println(l);
    }
}
