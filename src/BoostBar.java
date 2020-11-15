import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoostBar extends Group {
    private boolean available;
    private boolean inUse = false;
    protected final double height = 400;
    private final double width = 100;
    private final double xPos = Catch.width - 100 - width/2;
    private final double yPos = Catch.height/2 - height/2;
    private final double deltaFill = 1;
    private final double borderWidth = 15;
    private class OuterRectangle extends Rectangle {
        public OuterRectangle() {
            super();
            this.setHeight(height);
            this.setWidth(width);
            this.setX(xPos);
            this.setY(yPos);
            this.setFill(Color.TRANSPARENT);
            this.setStroke(Color.WHITESMOKE);
            this.setStrokeWidth(borderWidth);
            this.setArcHeight(height/5);
            this.setArcWidth(width/5);
        }
    }
    protected class InnerRectangle extends Rectangle {
        protected double currentHeight;
        private double currentYPos;
        public InnerRectangle() {
            super();
            this.currentYPos = Catch.height/2 + height/2;
            this.currentHeight = 0;
            this.setHeight(this.currentHeight);
            this.setWidth(width);
            this.setX(xPos);
            this.setY(this.currentYPos);
            this.setFill(Color.GREEN);
        }
        public void changeHeight(double delta) {
            this.currentHeight += delta;
            this.currentYPos -= delta;
            this.setHeight(this.currentHeight);
            this.setY(this.currentYPos);
        }
    }
    OuterRectangle outerBar = new OuterRectangle();
    InnerRectangle innerBar = new InnerRectangle();
    protected final AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
          innerBar.changeHeight(deltaFill);
          if(innerBar.currentHeight > height && !inUse) {
              innerBar.currentHeight = height;
              innerBar.currentYPos = yPos;
              innerBar.setHeight(innerBar.currentHeight);
              innerBar.setY(innerBar.currentYPos);
          }
          available = innerBar.currentHeight > deltaFill;
      }
    };

    public BoostBar(){
        super();
        this.getChildren().addAll(innerBar, outerBar);
        timer.start();
    }

    public boolean isAvailable() {
        return available;
    }

    public void use() {
        inUse = true;
        innerBar.changeHeight(-1 * deltaFill * 15);
    }
    public void stopUsing() {
        inUse = false;
    }
}
