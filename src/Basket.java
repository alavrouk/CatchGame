import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Basket extends Rectangle {
    protected static final double width = 200;
    protected static final double height = 200;
    private static final double regularSpeed = 10;
    private static final double enhancedSpeed = 20;
    protected static final double yPos = Catch.height - 100 - height/2;

    protected static double xPos = Catch.width/2;
    private static boolean rightPressed = false;
    private static boolean leftPressed = false;
    private static boolean shiftPressed = false;
    private static double deltaX = 0;
    private static int itemsCaught = 0;

    private final EventHandler<KeyEvent> keyReleased = event -> {
        switch (event.getCode()) {
            case LEFT:
                leftPressed = false;
                break;
            case RIGHT:
                rightPressed = false;
                break;
            case SHIFT:
                shiftPressed = false;
                break;
        }
    };
    private final EventHandler<KeyEvent> keyPressed = event -> {
        switch (event.getCode()) {
            case LEFT:
                leftPressed = true;
                break;
            case RIGHT:
                rightPressed = true;
                break;
            case SHIFT:
                shiftPressed = true;
                break;
        }
    };
    protected final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if(shiftPressed && Catch.boostBar.isAvailable()) {
                if(rightPressed) {
                    deltaX = enhancedSpeed;
                    xPos += deltaX;
                    Catch.boostBar.use();
                } if(leftPressed) {
                    deltaX = -1 * enhancedSpeed;
                    xPos += deltaX;
                    Catch.boostBar.use();
                }
            } else {
                if(rightPressed) {
                    deltaX = regularSpeed;
                    xPos += deltaX;
                } if(leftPressed) {
                    deltaX = -1 * regularSpeed;
                    xPos += deltaX;
                }
            }
            Catch.boostBar.stopUsing();
            if(xPos < 0) {
                xPos = 0;
            }
            if(xPos > Catch.width - width) {
                xPos = Catch.width - width;
            }
            setX(xPos);
        }
    };

    public Basket() {
        super();
        this.setX(xPos - width/2);
        this.setY(yPos);
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(Color.WHITESMOKE);
        Image basket = Catch.imageFactory("Images\\hopper.png");
        assert basket != null;
        ImagePattern basketPattern = new ImagePattern(basket);
        this.setFill(basketPattern);
        this.setOnKeyReleased(keyReleased);
        this.setOnKeyPressed(keyPressed);
        this.setFocusTraversable(true);
        timer.start();
    }
}
