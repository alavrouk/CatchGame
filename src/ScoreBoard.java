import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ScoreBoard extends StackPane {
    private static double width = 250;
    private static double height = 150;

    public ScoreBoard() {
        super();
        Rectangle rect = new Rectangle();
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setX(Catch.width - 100 - width);
        rect.setY(100);
        rect.setStroke(Color.WHITE);
        rect.setStrokeWidth(15);
        rect.setArcHeight(height/2);
        rect.setArcWidth(width/2);
        Label score = new Label();
        Label misses = new Label();
        Font font = Font.font("Comic Sans", FontWeight.EXTRA_BOLD, 35.0D);
        score.setText("SCORE: " + Catch.score);
        misses.setText("MISSES: " + (Catch.misses - Catch.score));
        score.setFont(font);
        misses.setFont(font);
        score.setTextFill(Color.WHITESMOKE);
        misses.setTextFill(Color.WHITESMOKE);
        VBox box = new VBox(score, misses);
        box.setAlignment(Pos.CENTER);
        this.getChildren().addAll(rect, box);
        this.setAlignment(Pos.TOP_RIGHT);
    }

    public void updateScore() {
        this.getChildren().remove(1);
        Label score = new Label();
        Label misses = new Label();
        Font font = Font.font("Comic Sans", FontWeight.EXTRA_BOLD, 35.0D);
        score.setText("SCORE: " + Catch.score);
        misses.setText("MISSES: " + (Catch.misses));
        score.setFont(font);
        misses.setFont(font);
        score.setTextFill(Color.WHITESMOKE);
        misses.setTextFill(Color.WHITESMOKE);
        VBox box = new VBox(score, misses);
        box.setAlignment(Pos.CENTER);
        this.getChildren().add(box);
    }
}
