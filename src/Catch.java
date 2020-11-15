import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Catch extends Application {
    static final protected double width = 1680.0;
    static final protected double height = 1080.0;
    protected static int gameTimer = 0;
    protected static int score = 0;
    protected static int misses = 0;
    protected static Pane game = new Pane();
    protected static Basket basket = new Basket();
    protected static BoostBar boostBar = new BoostBar();
    protected static StackPane root = new StackPane();
    protected static Scene primaryScene = new Scene(root, width, height);
    protected static int multiplier = 1;
    protected static ScoreBoard scoreBoard = new ScoreBoard();

    public static Image imageFactory(String path) {
        try {
            FileInputStream input = new FileInputStream(path);
            return new Image(input);
        } catch (FileNotFoundException e) {
            Error imageLocationNotFound = new Error(
                    Alert.AlertType.ERROR,
                    "File Not Found",
                    "The Path to your image was not found",
                    "Double check the path you have entered in the method. The method has returned a null image");
            imageLocationNotFound.showAndWait();
        }            return null;
    }

    public void setBackground(StackPane root) {
        Image background = imageFactory("Images\\background.jpg");
        assert background != null;
        BackgroundImage backgroundimage = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0D, 1.0D, true, true, false, false));
        Background rootBackground = new Background(backgroundimage);
        root.setBackground(rootBackground);
        HBox titleText = new HBox();
        titleText.setAlignment(Pos.TOP_CENTER);
    }
    public static void fallingObjectFactory(Pane game) {
        Random random = new Random();
        int randomInt = random.nextInt(5);
        switch(randomInt) {
            case 0:
                FallingObject object0 = new FallingObject(FallingObjectType.CHICKEN);
                game.getChildren().add(object0);
                break;
            case 2:
                FallingObject object2 = new FallingObject(FallingObjectType.HORSE);
                game.getChildren().add(object2);
                break;
            case 3:
                FallingObject object3 = new FallingObject(FallingObjectType.CAT);
                game.getChildren().add(object3);
                break;
            case 4:
                FallingObject object4 = new FallingObject(FallingObjectType.DOG);
                game.getChildren().add(object4);
                break;
            default:
                FallingObject object5 = new FallingObject(FallingObjectType.COW);
                game.getChildren().add(object5);
                break;
        }
    }


    public void createGame(StackPane root) {
        game.getChildren().addAll(basket, boostBar, scoreBoard);
        root.getChildren().add(game);
    }

    private static class startStage extends Stage {
        static final protected double width = 1680.0;
        static final protected double height = 1080.0;

        public void start(Stage primaryStage) {
            primaryStage.setTitle("Catch");
            StackPane root = new StackPane();
            Scene primaryScene = new Scene(root, width, height);
            primaryStage.setScene(primaryScene);
        }
        public static void main(String[] args) {
            Application.launch(args);
        }
    }
    protected static AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            gameTimer += 1;
            if (gameTimer % 360 == 0) {
                multiplier++;
            }
            if (gameTimer % (600 / multiplier) == 0 || gameTimer == 3) {
                fallingObjectFactory(game);
            }
            scoreBoard.updateScore();
            if (misses > 0) {
                this.stop();
                boostBar.timer.stop();
                basket.timer.stop();
                primaryScene.setRoot(new StartPane());
            }
        }
    };
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Catch");
        setBackground(root);
        createGame(root);
        timer.start();
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
