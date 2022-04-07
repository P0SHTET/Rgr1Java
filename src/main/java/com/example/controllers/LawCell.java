package com.example.controllers;

import com.example.model.LawOfObligations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LawCell extends ListCell<LawOfObligations> {

    @FXML
    private Label derivativeField;

    @FXML
    private Label lawField;

    @FXML
    private Label riskField;

    @FXML
    private Label dateStartField;

    @FXML
    private Label dateEndField;

    @FXML
    private Label sideField;

    @FXML
    private VBox viewBox;

    public LawCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/law_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(LawOfObligations item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            lawField.setText(null);
            derivativeField.setText(null);
            riskField.setText((null));
            dateStartField.setText(null);
            dateEndField.setText(null);
            sideField.setText(null);
            setGraphic(null);
        } else {
            lawField.setText(item.getLaw());
            derivativeField.setText(item.getDerivative());
            riskField.setText(item.getRisk()
                                    .toString());
            dateStartField.setText(item.getDateOfStart()
                                    .toString());
            dateEndField.setText(item.getDateOfFinish()
                                    .toString());
            sideField.setText(item.getSide().getName());
            setGraphic(viewBox);
        }
    }

}
