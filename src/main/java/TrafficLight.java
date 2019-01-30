import javafx.application.Platform;
import java.util.Timer;
import java.util.TimerTask;

public class TrafficLight extends Observable
{
    private TrafficLightType type;
    private TrafficLightState actState, newState;
    private boolean inProgress = false;
    private Timer timerChangeState;


    /**
     * TrafficLight(): Konstruktor. Define the type from the trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    26.11.2018
     * @arg     TrafficLightType type: Type from the trafficLight (CAR, PEDESTRIAN, BUS, BYCICLE; ...)
     */
    public TrafficLight(TrafficLightType type)
    {
        this.type = type;
        actState = newState = TrafficLightState.RED;
        notifyObservers();
    }


    /**
     * getType(): Returns the type for the trafficLight. (CAR or PEDESTRIAN)
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     * @return  TrafficLightType: Type from the trafficLight
     */
    public TrafficLightType getType()
    {
        return this.type;
    }


    /**
     * getState(): Returns the actState (color) from the trafficLight
     *
     * Additional: Called from TrafficLightController after notifyObservers()!
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     * @return  state: State from the trafficLight
     */
    public TrafficLightState getState()
    {
        return actState;
    }


    /**
     * getInProgress(): Returns if the trafficLight is in progress
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    08.12.2018
     * @return  boolean: State if an change of lights in progress (true or false)
     */
    public boolean getInProgress()
    {
        return inProgress;
    }


    /**
     * setRed(): Change the color from the trafficLight to RED.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    public void setRed()
    {
        newState = TrafficLightState.RED;
        startTimerForChangeState(newState);
        notifyObservers();
    }


    /**
     * setGreen(): Change the color from the trafficLight to GREEN.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    public void setGreen()
    {
        newState = TrafficLightState.GREEN;
        startTimerForChangeState(newState);
        notifyObservers();
    }


    /**
     * setYellowFlash(): Flash the yellow color from the trafficLight
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    public void setYellowFlash()
    {
        newState = TrafficLightState.YELLOW_FLASH;
        startTimerForChangeState(newState);
        notifyObservers();
    }


    /**
     * setDark(): Change the color from the trafficLight to DARK.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    public void setDark()
    {
        newState = TrafficLightState.DARK;
        startTimerForChangeState(newState);
        notifyObservers();
    }


    /**
     * setSIMULATION(): Makes a free run light.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    18.11.2018
     */
    public void setSIMULATION()
    {
        newState = TrafficLightState.SIMULATION;
        startTimerForChangeState(newState);
        notifyObservers();
    }


    /**
     * startTimerForChangeState(): Starts the timer for time-based change from the state
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    08.12.2018
     * @arg     TrafficLightState newState: (value: enum TrafficLightState)
     */
    private void startTimerForChangeState(TrafficLightState newState)
    {
        if(inProgress == true)
        {
            timerChangeState.cancel();
            inProgress = false;
        }
        timerChangeState = new Timer();
        timerChangeState.schedule(new TimerTask() {
                                      @Override
                                      public void run() {
                                          Platform.runLater(new Runnable() {
                                              @Override
                                              public void run() {
                                                  inProgress = true;
                                                  changeTimerBasedState(newState);
                                              }
                                          });
                                      }
                                  },
                0 /* ms delay */,
                500 /* ms period */);
        notifyObservers();
    }


    /**
     * changeTimerBasedState(): Automatic called every 1Sec after start timerChangeState
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    08.12.2018
     * @arg     TrafficLightState newState: (value: enum TrafficLightState)
     */
    private void changeTimerBasedState(TrafficLightState newState)
    {
        switch(newState)
        {
            case RED:
            {
                switchToRed();
                break;
            }
            case GREEN:
            {
                switchToGreen();
                break;
            }
            case YELLOW_FLASH:
            {
                switchToYellowFlash();
                break;
            }
            case DARK:
            {
                switchToDark();
                break;
            }
            case SIMULATION:
            {
                switchToSIMULATION();
                break;
            }
        }
        notifyObservers();
    }


    /**
     * switchToRed(): State-Machine from the lights to switch to the RED state.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    08.12.2018
     */
    private void switchToRed()
    {
        switch (actState)
        {
            case GREEN:
            {
                actState = TrafficLightState.YELLOW;
                break;
            }
            case YELLOW:
            {
                actState = TrafficLightState.RED;
                break;
            }
            default:
            {
                actState = TrafficLightState.RED;
                break;
            }
        }

        /* Stop timer */
        if(actState == TrafficLightState.RED)
        {
            inProgress = false;
            timerChangeState.cancel();
        }
        notifyObservers();
    }


    /**
     * switchToGreen(): State-Machine from the lights to switch to the GREEN state.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    08.12.2018
     */
    private void switchToGreen()
    {
        switch (actState)
        {
            case RED:
            {
                actState = TrafficLightState.YELLOW_RED;
                break;
            }
            case YELLOW_RED:
            {
                actState = TrafficLightState.GREEN;
                break;
            }
            default:
            {
                actState = TrafficLightState.RED;
                break;
            }
        }

        /* Stop timer */
        if(actState == TrafficLightState.GREEN)
        {
            inProgress = false;
            timerChangeState.cancel();
        }
        notifyObservers();
    }


    /**
     * switchToYellowFlash(): State-Machine from the lights to flash the YELLOW light.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    08.12.2018
     */
    private void switchToYellowFlash()
    {
        switch (actState)
        {
            case YELLOW:
            {
                actState = TrafficLightState.DARK;
                break;
            }
            case DARK:
            {
                actState = TrafficLightState.YELLOW;
                break;
            }
            default:
            {
                actState = TrafficLightState.YELLOW;
                break;
            }
        }
        notifyObservers();
    }


    /**
     * switchToDark(): State-Machine from the lights to switch to the DARK state.
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    08.12.2018
     */
    private void switchToDark()
    {
        switch (actState)
        {
            case ALLOn:
            {
                actState = TrafficLightState.DARK;
                /* Stop timer */
                inProgress = false;
                timerChangeState.cancel();
                break;
            }
            default:
            {
                actState = TrafficLightState.ALLOn;
                break;
            }
        }
        notifyObservers();
    }


    /**
     * switchToSIMULATION(): State-Machine from the lights for SIMULATION (running lights).
     *
     *
     * @version 1.0
     * @autor   Schweizer Patrick
     * @date    08.12.2018
     */
    private void switchToSIMULATION()
    {
        switch (actState)
        {
            case GREEN:
            {
                actState = TrafficLightState.YELLOW;
                break;
            }
            case YELLOW:
            {
                actState = TrafficLightState.YELLOW_RED;
                break;
            }
            case YELLOW_RED:
            {
                actState = TrafficLightState.RED;
                break;
            }
            case RED:
            {
                actState = TrafficLightState.DARK;
                break;
            }
            case DARK:
            {
                actState = TrafficLightState.ALLOn;
                break;
            }
            case ALLOn:
            {
                actState = TrafficLightState.GREEN;
                break;
            }
        }
    }
}
