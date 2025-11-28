package view;

import controller.CurrencyConverterController;
import datasource.MariaDbConnection;
import entity.Currency;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CurrencyConverterView extends Application {
    private CurrencyConverterController controller;

    private TextField amountField;
    private TextField resultField;
    private ComboBox<Currency> fromCurrencyCombo;
    private ComboBox<Currency> toCurrencyCombo;
    private Button convertButton;

    @Override
    public void init() {
        // Initialize controller before start
        controller = new CurrencyConverterController();

        // Check database connection
        if (!controller.isDatabaseAvailable()) {
            System.err.println("WARNING: Database connection is not available!");
            System.err.println("The application will start, but currency conversion will not work.");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Check if database is available and show warning if not
        if (!controller.isDatabaseAvailable() || controller.getCurrencies().isEmpty()) {
            showDatabaseErrorAlert();
        }

        setupUI(primaryStage);
        setupEventHandlers();
    }

    /**
     * Show an error alert when database is not available at startup
     */
    private void showDatabaseErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Database Connection Issue");
        alert.setHeaderText("Cannot connect to database");
        alert.setContentText("The application cannot connect to the database.\n\n" +
                "Please ensure that:\n" +
                "1. MariaDB server is running\n" +
                "2. The currency_converter database exists\n" +
                "3. The appuser account has been created\n\n" +
                "Currency conversion will not work until the database is available.");
        alert.showAndWait();
    }

    private void setupUI(Stage stage) {
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(20));

        // Title
        Label titleLabel = new Label("Currency Converter");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(0, 0, 20, 0));
        mainLayout.setTop(titleLabel);

        // Main form
        VBox formBox = createForm();
        mainLayout.setCenter(formBox);

        // Instructions area
        VBox instructionsBox = createInstructions();
        mainLayout.setBottom(instructionsBox);

        Scene scene = new Scene(mainLayout, 500, 550);

        // Load CSS file from resources
        var cssResource = getClass().getResource("/style.css");
        if (cssResource != null) {
            String cssPath = cssResource.toExternalForm();
            scene.getStylesheets().add(cssPath);
        } else {
            System.err.println("Warning: Could not load style.css");
        }

        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private VBox createForm() {
        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(20));

        // Amount input
        Label amountLabel = new Label("Amount to Convert:");
        amountField = new TextField();
        amountField.setPromptText("Enter amount");
        amountField.setPrefWidth(300);

        // From currency - label on top of combo box
        Label fromLabel = new Label("From Currency:");
        fromCurrencyCombo = new ComboBox<>();
        fromCurrencyCombo.setItems(FXCollections.observableArrayList(controller.getCurrencies()));
        fromCurrencyCombo.setPromptText("Select source currency");
        fromCurrencyCombo.setPrefWidth(300);

        // To currency - label on top of combo box
        Label toLabel = new Label("To Currency:");
        toCurrencyCombo = new ComboBox<>();
        toCurrencyCombo.setItems(FXCollections.observableArrayList(controller.getCurrencies()));
        toCurrencyCombo.setPromptText("Select target currency");
        toCurrencyCombo.setPrefWidth(300);

        // Result field
        Label resultLabel = new Label("Converted Amount:");
        resultField = new TextField();
        resultField.setEditable(false);
        resultField.setStyle("-fx-background-color: #f0f0f0;");
        resultField.setPrefWidth(300);

        // Convert button
        convertButton = new Button("Convert");
        convertButton.setDefaultButton(true);
        convertButton.setPrefWidth(150);

        // Add all components to form
        formBox.getChildren().addAll(
                amountLabel, amountField,
                fromLabel, fromCurrencyCombo,
                toLabel, toCurrencyCombo,
                resultLabel, resultField,
                convertButton
        );

        return formBox;
    }

    private VBox createInstructions() {
        VBox instructionsBox = new VBox(8);
        instructionsBox.setPadding(new Insets(20));
        instructionsBox.setAlignment(Pos.CENTER_LEFT);
        instructionsBox.getStyleClass().add("instructions");

        Label instructionsTitle = new Label("How to use:");
        instructionsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");

        Label instruction1 = new Label("1. Enter the amount you want to convert");
        Label instruction2 = new Label("2. Select the source currency from the dropdown");
        Label instruction3 = new Label("3. Select the target currency from the dropdown");
        Label instruction4 = new Label("4. Click the 'Convert' button to see the result");

        instructionsBox.getChildren().addAll(
                instructionsTitle, instruction1, instruction2, instruction3, instruction4
        );

        return instructionsBox;
    }

    private void setupEventHandlers() {
        // Input validation - only allow numeric input with decimal point
        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                amountField.setText(oldValue);
            }
        });

        // Convert button event handler
        convertButton.setOnAction(event -> handleConvert());
    }

    private void handleConvert() {
        String result = controller.convert(
                amountField.getText(),
                fromCurrencyCombo.getValue(),
                toCurrencyCombo.getValue()
        );

        if (result.startsWith("ERROR:")) {
            showErrorAlert(result.substring(7)); // Remove "ERROR: " prefix
            resultField.clear();
        } else {
            resultField.setText(result);
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Conversion Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void stop() {
        // Clean up resources when application closes
        System.out.println("Closing Currency Converter application...");
        MariaDbConnection.terminate();
    }
}