package util;

import java.util.LinkedList;
import java.util.List;

public abstract class Subject implements Observable {

    private List<Observer> observers = new LinkedList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer i : observers) {
            i.update();
        }
    }
}
