package util;


/**
 *
 *
 *  @autor  Hirter Fabian
 *  @since  22.10.2019
 */
public interface Observable {
    void addObserver(Observer observer);

    void notifyObservers();
}
