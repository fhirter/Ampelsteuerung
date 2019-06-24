package traffic_lights;

import javafx.application.Platform;
import util.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @autor Schweizer Patrick, Hirter Fabian
 */

public class TrafficLight extends Observable implements TrafficLightInterface {
    private TrafficLightState currentState, nextState, endState;
    private Timer stateChangeTimer;
    private Map<TrafficLightState, TrafficLightState> nextStateMap = new HashMap<>();

    final private int delay = 500;

    public TrafficLight() {
        currentState = nextState = endState = TrafficLightState.RED;

        stateChangeTimer = new Timer();

        // normal operation
        nextStateMap.put(TrafficLightState.GREEN, TrafficLightState.YELLOW);
        nextStateMap.put(TrafficLightState.YELLOW, TrafficLightState.RED);
        nextStateMap.put(TrafficLightState.RED, TrafficLightState.YELLOW_RED);
        nextStateMap.put(TrafficLightState.YELLOW_RED, TrafficLightState.GREEN);

        // yellow flashing
        nextStateMap.put(TrafficLightState.YELLOW_FLASH, TrafficLightState.DARK);
        nextStateMap.put(TrafficLightState.DARK, TrafficLightState.YELLOW_FLASH);
    }

    @Override
    public TrafficLightState getState() {
        return currentState;
    }

    @Override
    public void setState(TrafficLightState state) {
        if(endState != state) {
            endState = state;
            scheduleStateChangeTimer();
        }
    }

    /**
     * scheduleStateChangeTimer(): Starts the timer for time-based change of the state
     *
     * @since 08.12.2018
     */
    private void scheduleStateChangeTimer() {
        if(endState != currentState) {

            nextState = nextStateMap.get(currentState);

            stateChangeTimer.schedule(new TimerTask() {
                                          @Override
                                          public void run() {
                                              Platform.runLater(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      currentState = nextState;
                                                      notifyObservers();

                                                      if (nextState != endState) {                // final state not yet reached so schedule again
                                                          scheduleStateChangeTimer();
                                                      }
                                                  }
                                              });
                                          }
                                      },
                    delay
            );
        }
    }
}
