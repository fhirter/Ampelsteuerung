import java.util.LinkedList;
import java.util.List;

abstract class Observable {

    List<Observer> oberservers = new LinkedList<>();


    protected void addObserver(Observer observer){
        oberservers.add(observer);
    }

    protected void notifyObservers(){
        for (Observer i : oberservers) {
         i.update();
        }
    }

}
