package view;

import controller.DictionaryController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DictionaryView extends Application {
    private TextField wordField;
    private Label resultLabel;
    private DictionaryController controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new DictionaryController();

        // Create UI components
        Label titleLabel = new Label("Virtual Dictionary");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label instructionLabel = new Label("Enter a word to search:");
        wordField = new TextField();
        wordField.setPromptText("Type word here...");
        wordField.setPrefWidth(200);

        Button searchButton = new Button("Search");
        resultLabel = new Label();
        resultLabel.setWrapText(true);
        resultLabel.setPrefWidth(300);
        resultLabel.setStyle("-fx-border-color: #cccccc; -fx-padding: 10px;");

        // Set button event handler
        searchButton.setOnAction(e -> handleSearch());

        // Enable search on Enter key press
        wordField.setOnAction(e -> handleSearch());

        // Create layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        layout.getChildren().addAll(
                titleLabel,
                instructionLabel,
                wordField,
                searchButton,
                resultLabel
        );

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Virtual Dictionary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSearch() {
        String word = wordField.getText();
        String result = controller.searchWord(word);
        resultLabel.setText(result);
    }

    @Override
    public void init() {
        // Initialization code (if needed)
    }
}