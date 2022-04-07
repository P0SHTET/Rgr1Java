package com.example.controllers;

import com.example.controllers.dto.CreateLawOfObligationsDto;
import com.example.model.LawOfObligations;
import com.example.service.LawService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Slf4j
public class LawController implements Initializable {

    private final LawService lawService;

    @FXML
    private GridPane createLaw;

    @FXML
    private TextField lawDerivative;

    @FXML
    private TextField lawLaw;

    @FXML
    private DatePicker lawStart;

    @FXML
    private DatePicker lawEnd;

    @FXML
    private TextField lawSide;

    @FXML
    private TextField lawRisk;

    @FXML
    private TextField searchString;

    @FXML
    private ListView<LawOfObligations> listView;

    public LawController() {
        lawService = new LawService();

    }

    private void filter(String value) {
        var items = lawService.list()
                                .stream()
                                .toList();
        listView.setItems(
                FXCollections.observableList(
                        value.isBlank()
                                ? items
                                : items.stream()
                                        .filter(x -> x.getDerivative().contains(value))
                                        .sorted((o1, o2) -> o2.getRisk().compareTo(o1.getRisk()))
                                        .collect(Collectors.toList())
                )
        );
    }

    @FXML
    protected void onSubmit(){
        lawService.add(CreateLawOfObligationsDto.builder()
                        .law(lawLaw.getText())
                        .derivative(lawDerivative.getText())
                        .dateOfFinish(lawEnd.getValue())
                        .dateOfStart(lawStart.getValue())
                        .sideName(lawSide.getText())
                        .risk(Double.parseDouble(lawRisk.getText()))
                .build());
        createLaw.setVisible(false);
    }

    @FXML
    protected void onHelloButtonClick() {
        createLaw.setVisible(true);
    }

    @FXML
    protected void onSave() {
        lawService.save();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var items = FXCollections.observableArrayList(lawService.list());
        listView.setItems(items);

        listView.setCellFactory(parmas -> new LawCell());

        searchString.textProperty()
                    .addListener((observable, oldValue, newValue) -> filter(newValue));
    }

}
