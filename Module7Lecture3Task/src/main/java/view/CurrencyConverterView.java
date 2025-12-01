package view;

import controller.CurrencyConverterController;
import datasource.MariaDbJpaConnection;
import entity.Currency;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * JavaFX View for the Currency Converter application.
 * Provides the graphical user interface for currency conversion and management.
 */
public class CurrencyConverterView extends Application {
    private CurrencyConverterController controller;

    private TextField amountField;
    private TextField resultField;
    private ComboBox<Currency> fromCurrencyCombo;
    private ComboBox<Currency> toCurrencyCombo;
    private Button convertButton;
    private Button addCurrencyButton;

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

        Scene scene = new Scene(mainLayout, 500, 600);

        // Load CSS file from resources
        var cssResource = getClass().getResource("/style.css");
        if (cssResource != null) {
            String cssPath = cssResource.toExternalForm();
            scene.getStylesheets().add(cssPath);
        } else {
            System.err.println("Warning: Could not load style.css");
        }

        stage.setTitle("Currency Converter with JPA");
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

        // Buttons in HBox
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        // Convert button
        convertButton = new Button("Convert");
        convertButton.setDefaultButton(true);
        convertButton.setPrefWidth(140);

        // Add Currency button
        addCurrencyButton = new Button("Add Currency");
        addCurrencyButton.setPrefWidth(140);
        addCurrencyButton.setStyle("-fx-background-color: #2196F3;");

        buttonBox.getChildren().addAll(convertButton, addCurrencyButton);

        // Add all components to form
        formBox.getChildren().addAll(
                amountLabel, amountField,
                fromLabel, fromCurrencyCombo,
                toLabel, toCurrencyCombo,
                resultLabel, resultField,
                buttonBox
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
        Label instruction5 = new Label("5. Click 'Add Currency' to add a new currency to the database");

        instructionsBox.getChildren().addAll(
                instructionsTitle, instruction1, instruction2, instruction3, instruction4, instruction5
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

        // Add Currency button event handler
        addCurrencyButton.setOnAction(event -> handleAddCurrency());
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

    private void handleAddCurrency() {
        // Create a new stage (window) for adding currency
        Stage addCurrencyStage = new Stage();
        addCurrencyStage.initModality(Modality.APPLICATION_MODAL);
        addCurrencyStage.setTitle("Add New Currency");

        // Create form for adding currency
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Add New Currency");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Abbreviation field
        Label abbrLabel = new Label("Abbreviation (e.g., USD):");
        TextField abbrField = new TextField();
        abbrField.setPromptText("3-letter code");
        abbrField.setPrefWidth(250);
        abbrField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 3) {
                abbrField.setText(oldValue);
            }
        });

        // Name field
        Label nameLabel = new Label("Currency Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("e.g., US Dollar");
        nameField.setPrefWidth(250);

        // Conversion rate field
        Label rateLabel = new Label("Conversion Rate to USD:");
        TextField rateField = new TextField();
        rateField.setPromptText("e.g., 1.0 for USD, 0.92 for EUR");
        rateField.setPrefWidth(250);
        // Only allow numeric input
        rateField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                rateField.setText(oldValue);
            }
        });

        // Buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);

        Button saveButton = new Button("Save");
        saveButton.setPrefWidth(100);
        saveButton.setStyle("-fx-background-color: #4CAF50;");

        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(100);
        cancelButton.setStyle("-fx-background-color: #f44336;");

        buttonBox.getChildren().addAll(saveButton, cancelButton);

        // Add all to layout
        layout.getChildren().addAll(
                titleLabel,
                abbrLabel, abbrField,
                nameLabel, nameField,
                rateLabel, rateField,
                buttonBox
        );

        // Button handlers
        saveButton.setOnAction(event -> {
            String abbreviation = abbrField.getText().trim().toUpperCase();
            String name = nameField.getText().trim();
            String rateText = rateField.getText().trim();

            // Validation
            if (abbreviation.isEmpty() || name.isEmpty() || rateText.isEmpty()) {
                showErrorAlert("All fields are required!");
                return;
            }

            if (abbreviation.length() != 3) {
                showErrorAlert("Abbreviation must be exactly 3 characters!");
                return;
            }

            try {
                double rate = Double.parseDouble(rateText);
                if (rate <= 0) {
                    showErrorAlert("Conversion rate must be positive!");
                    return;
                }

                // Create and persist currency
                Currency newCurrency = new Currency(abbreviation, name, rate);
                boolean success = controller.addCurrency(newCurrency);

                if (success) {
                    showInfoAlert("Currency Added", "Currency '" + abbreviation + "' has been added successfully!");

                    // Refresh the combo boxes
                    refreshCurrencyComboBoxes();

                    addCurrencyStage.close();
                } else {
                    showErrorAlert("Failed to add currency. It may already exist or there was a database error.");
                }
            } catch (NumberFormatException e) {
                showErrorAlert("Invalid conversion rate format!");
            }
        });

        cancelButton.setOnAction(event -> addCurrencyStage.close());

        Scene scene = new Scene(layout, 350, 400);

        // Load CSS for the new window
        var cssResource = getClass().getResource("/style.css");
        if (cssResource != null) {
            scene.getStylesheets().add(cssResource.toExternalForm());
        }

        addCurrencyStage.setScene(scene);
        addCurrencyStage.showAndWait(); // Wait for window to close before continuing
    }

    /**
     * Refresh the currency combo boxes with updated currency list.
     */
    private void refreshCurrencyComboBoxes() {
        fromCurrencyCombo.setItems(FXCollections.observableArrayList(controller.getCurrencies()));
        toCurrencyCombo.setItems(FXCollections.observableArrayList(controller.getCurrencies()));
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void stop() {
        // Clean up resources when application closes
        System.out.println("Closing Currency Converter application...");
        MariaDbJpaConnection.close();
    }
}

