package lab6;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.function.DoubleUnaryOperator;

/**
 * Custom component that handles a single type of conversion
 */
public class ConversionPanel extends VBox {
    private TextField fromField;
    private TextField toField;

    public ConversionPanel(String fromUnit, String toUnit,
                           DoubleUnaryOperator convertTo,
                           DoubleUnaryOperator convertFrom) {
        setSpacing(10);
        setAlignment(Pos.CENTER);

        // Create input fields
        fromField = new TextField();
        fromField.setPromptText("Enter " + fromUnit);

        toField = new TextField();
        toField.setPromptText("Enter " + toUnit);
        toField.setEditable(false);

        // Create conversion buttons
        Button toButton = new Button("Convert to " + toUnit);
        Button fromButton = new Button("Convert to " + fromUnit);
        Button clearButton = new Button("Clear");

        // Set button actions
        toButton.setOnAction(e -> {
            try {
                double value = Double.parseDouble(fromField.getText());
                double result = convertTo.applyAsDouble(value);
                toField.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                toField.setText("Invalid input");
            }
        });

        fromButton.setOnAction(e -> {
            try {
                double value = Double.parseDouble(toField.getText());
                double result = convertFrom.applyAsDouble(value);
                fromField.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                fromField.setText("Invalid input");
            }
        });

        clearButton.setOnAction(e -> {
            fromField.clear();
            toField.clear();
        });

        // Create layout
        HBox fieldsBox = new HBox(10,
                new VBox(5, new Label(fromUnit + ":"), fromField),
                new VBox(5, new Label(toUnit + ":"), toField)
        );
        fieldsBox.setAlignment(Pos.CENTER);

        HBox buttonsBox = new HBox(10, toButton, fromButton, clearButton);
        buttonsBox.setAlignment(Pos.CENTER);

        getChildren().addAll(fieldsBox, buttonsBox);
    }
}