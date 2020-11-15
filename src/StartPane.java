import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class StartPane extends StackPane {
    public StartPane() {
        Image background = Catch.imageFactory("Images\\startBackground.jpg");
        assert background != null;
        BackgroundImage backgroundimage = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0D, 1.0D, true, true, false, false));
        Background rootBackground = new Background(backgroundimage);
        this.setBackground(rootBackground);
        HBox titleText = new HBox();
        titleText.setAlignment(Pos.TOP_CENTER);
        Image title = Catch.imageFactory("Images\\instructionsTitle.png");
        assert title != null;
        ImageView titleImage = new ImageView(title);
        titleText.getChildren().add(titleImage);
        HBox filler = new HBox(new Label(""));
        VBox titleBox = new VBox(20);
        titleBox.getChildren().addAll(filler, titleText);
        titleBox.setAlignment(Pos.TOP_CENTER);
        this.getChildren().addAll(titleBox);
        HBox instructions = new HBox();
        Image instructionText = Catch.imageFactory("Images\\instructions.png");
        ImageView instructionView = new ImageView(instructionText);
        instructions.getChildren().add(instructionView);
        instructions.setAlignment(Pos.CENTER);
        this.getChildren().add(instructions);
        HBox buttons = new HBox();
        Button start = new Button();
        Button exit = new Button();
        start.setOnAction(actionEvent -> {
           Catch.primaryScene.setRoot(Catch.root);
            Catch.score = 0;
            Catch.misses = 0;
            Catch.gameTimer = -1;
            Catch.multiplier = 1;
            Catch.boostBar.innerBar.changeHeight(-1 * Catch.boostBar.innerBar.currentHeight);
            Catch.timer.start();
            Catch.boostBar.timer.start();
            Catch.basket.timer.start();
        });
        exit.setOnAction(actionEvent -> {
            Platform.exit();
        });
        buttons.getChildren().addAll(start, exit);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        this.getChildren().add(buttons);
    }
}
