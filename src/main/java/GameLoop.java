
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    private final MovedElements movedElements;

    private long previousTime = 0;

    private float maximumStep = (float)0.016;

    public GameLoop(MovedElements movedElements) {
        this.movedElements = movedElements;
    }

    @Override
    public void handle(long now) {
        if (previousTime == 0) {
            previousTime = now;
            return;
        }

        float secondsElapsed = (now - previousTime) / 1e9f;
        float secondsElapsedCapped = Math.min(secondsElapsed, maximumStep);
        previousTime = now;

       // movedElements.calculatePositions(secondsElapsedCapped);
    }
}