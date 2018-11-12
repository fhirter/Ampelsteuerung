package com.company;

import java.util.LinkedList;
import java.util.List;

public abstract class Obserable  {

    List<Observer> observers = new LinkedList<>();


    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        for (Observer i : observers) {
         i.update();
        }
    }


}
