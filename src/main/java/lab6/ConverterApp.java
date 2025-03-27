package lab6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main application class for the Unit Converter
 */
public class ConverterApp extends Application {
    private ComboBox<String> conversionType;
    private VBox conversionContainer;

    @Override
    public void start(Stage primaryStage) {
        // Create the main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        // Create header
        Label header = new Label("Unit Converter");
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        BorderPane.setAlignment(header, Pos.CENTER);
        root.setTop(header);

        // Create conversion type selector
        conversionType = new ComboBox<>();
        conversionType.getItems().addAll(
                "Temperature (C° ↔ F°)",
                "Length (cm ↔ in)",
                "Weight (kg ↔ lbs)"
        );
        conversionType.setValue("Temperature (C° ↔ F°)");

        // Container for the current conversion panel
        conversionContainer = new VBox(10);
        conversionContainer.setAlignment(Pos.CENTER);
        updateConversionPanel(conversionType.getValue());

        // Handle conversion type changes
        conversionType.setOnAction(e ->
                updateConversionPanel(conversionType.getValue()));

        // Add components to center
        VBox centerBox = new VBox(20, conversionType, conversionContainer);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(20));
        root.setCenter(centerBox);

        // Set up the scene
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Unit Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Updates the conversion panel based on the selected type
     * @param type The conversion type selected
     */
    private void updateConversionPanel(String type) {
        conversionContainer.getChildren().clear();

        if (type.startsWith("Temperature")) {
            conversionContainer.getChildren().add(new ConversionPanel(
                    "Celsius", "Fahrenheit",
                    c -> c * 9/5 + 32,
                    f -> (f - 32) * 5/9
            ));
        } else if (type.startsWith("Length")) {
            conversionContainer.getChildren().add(new ConversionPanel(
                    "Centimeters", "Inches",
                    cm -> cm / 2.54,
                    inches -> inches * 2.54
            ));
        } else if (type.startsWith("Weight")) {
            conversionContainer.getChildren().add(new ConversionPanel(
                    "Kilograms", "Pounds",
                    kg -> kg * 2.20462,
                    lbs -> lbs / 2.20462
            ));
        }
    }
}