package traffic_lights;

import javafx.application.Platform;
import util.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * todo: debug
 * @autor Schweizer Patrick, Hirter Fabian
 */

public class TrafficLight extends Observable implements TrafficLightInterface {
    private TrafficLightState currentState, nextState, endState;
    private boolean inProgress = false;
    private Timer stateChangeTimer;

    public TrafficLight() {
        currentState = TrafficLightState.RED;
    }

    @Override
    public TrafficLightState getState() {
        return currentState;
    }

    public void setState(TrafficLightState state) {
        this.endState = state;
        switchTo(state);
    }

    /**
     * startTimerForStateChange(): Starts the timer for time-based change of the state
     *
     * @since  08.12.2018
     */
    private void startTimerForStateChange() {
        if (inProgress == true) {
            stateChangeTimer.cancel();
            inProgress = false;
        }
        stateChangeTimer = new Timer();
        stateChangeTimer.schedule(new TimerTask() {
                                      @Override
                                      public void run() {
                                          Platform.runLater(new Runnable() {
                                              @Override
                                              public void run() {
                                                  inProgress = true;
                                                  switchTo(nextState);
                                                  notifyObservers();
                                              }
                                          });
                                      }
                                  },
                0 /* ms delay */,
                500 /* ms period */);
        notifyObservers();
    }

    public void switchTo(TrafficLightState nextState) {
        Map<TrafficLightState,TrafficLightState> stateMap = new HashMap<>();

        // normal operation
        stateMap.put(TrafficLightState.GREEN, TrafficLightState.YELLOW);
        stateMap.put(TrafficLightState.YELLOW, TrafficLightState.RED);
        stateMap.put(TrafficLightState.RED, TrafficLightState.YELLOW_RED);
        stateMap.put(TrafficLightState.YELLOW_RED, TrafficLightState.GREEN);

        // yellow flashing
        stateMap.put(TrafficLightState.YELLOW_FLASH, TrafficLightState.DARK);
        stateMap.put(TrafficLightState.DARK, TrafficLightState.YELLOW_FLASH);

        if(currentState != nextState) {
            this.nextState = stateMap.get(currentState);
            startTimerForStateChange();
        } else {
            inProgress = false;
            stateChangeTimer.cancel();
        }

    }

    private void setEndState(TrafficLightState endState) {
        this.endState = endState;
    }

    /**
     * switchToSIMULATION(): State-Machine for SIMULATION (running lights).
     *
     * @since 08.12.2018
     */
    private void switchToSIMULATION() {
        Map<TrafficLightState,TrafficLightState> simulationStateMap = new HashMap<>();

        simulationStateMap.put(TrafficLightState.GREEN, TrafficLightState.YELLOW);
        simulationStateMap.put(TrafficLightState.YELLOW, TrafficLightState.YELLOW_RED);
        simulationStateMap.put(TrafficLightState.YELLOW_RED, TrafficLightState.RED);
        simulationStateMap.put(TrafficLightState.RED, TrafficLightState.DARK);
        simulationStateMap.put(TrafficLightState.DARK, TrafficLightState.ALLOn);
        simulationStateMap.put(TrafficLightState.ALLOn, TrafficLightState.GREEN);


        nextState = simulationStateMap.get(currentState);
        currentState = nextState;

    }
}
