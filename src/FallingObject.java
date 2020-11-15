import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class FallingObject extends Rectangle {
    private final double width = 75;
    private final double height = 75;
    private final double gravity = 0.0817;
    private double dY = 0;
    private double yPos;
    private double xPos;
    protected final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            dY += gravity;
            yPos += dY;
            setY(yPos);
            if(xPos > Basket.xPos - 10 && xPos < Basket.xPos + Basket.width + 10 && yPos > Basket.yPos - 5 && yPos < Basket.yPos + 5) {
                Catch.score++;
                setVisible(false);
                if(Catch.boostBar.height - Catch.boostBar.innerBar.currentHeight > 30) {
                    Catch.boostBar.innerBar.changeHeight(30);
                } else {
                    Catch.boostBar.innerBar.changeHeight(Catch.boostBar.height - Catch.boostBar.innerBar.currentHeight);
                }
            } else if(yPos > Catch.height && yPos < Catch.height + 5 && isVisible()){
                Catch.misses++;
                setVisible(false);
            }
        }
    };


    public FallingObject(FallingObjectType type) {
        String location;
        switch(type) {
            case CHICKEN:
                location = "Images\\chicken.png";
                break;
            case COW:
                location = "Images\\cow.png";
                break;
            case HORSE:
                location = "Images\\horse.png";
                break;
            case CAT:
                location = "Images\\cat.png";
                break;
            case DOG:
            default:
                location = "Images\\dog.png";
                break;
        }
        Image animal = Catch.imageFactory(location);
        ImagePattern fallingObjectPattern = new ImagePattern(animal);
        this.setFill(fallingObjectPattern);
        this.setWidth(width);
        this.setHeight(height);
        this.yPos = -1 * height;
        Random randomGenerator = new Random();
        this.xPos = randomGenerator.nextInt((int)(Catch.width - 100)/(int)width) * width;
        this.setY(yPos);
        this.setX(xPos);
        timer.start();
    }
}
