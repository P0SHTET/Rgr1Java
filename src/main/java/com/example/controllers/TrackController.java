package com.example.controllers;

import com.example.model.Track;
import com.example.service.TrackService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Slf4j
public class TrackController implements Initializable {

    private final TrackService trackService;

    @FXML
    private TextField searchString;

    @FXML
    private ListView<Track> listView;

    public TrackController() {
        trackService = new TrackService();

    }

    private void filter(String value) {
        var items = trackService.list()
                                .stream()
                                .toList();
        listView.setItems(
                FXCollections.observableList(
                        value.isBlank()
                                ? items
                                : items.stream()
                                       .filter(x -> x.getName().contains(value))
                                       .collect(Collectors.toList())
                )
        );
    }

    @FXML
    protected void onHelloButtonClick() {
        listView.refresh();
        log.info(String.valueOf(listView.getItems().size()));
    }

    @FXML
    protected void onSave() {
        trackService.save();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var items = FXCollections.observableArrayList(trackService.list());
        listView.setItems(items);

        listView.setCellFactory(parmas -> new TrackCell());

        searchString.textProperty()
                    .addListener((observable, oldValue, newValue) -> filter(newValue));
    }

}
