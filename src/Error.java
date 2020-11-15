import javafx.scene.control.Alert;

public class Error extends Alert {
    public Error(AlertType type, String title, String headerText, String content) {
        super(type);
        this.setTitle(title);
        this.setHeaderText(headerText);
        this.setContentText(content);
    }
}
