package com.example.controllers;

import com.example.model.Track;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TrackCell extends ListCell<Track> {

    @FXML
    private Label nameField;

    @FXML
    private Label genreField;

    @FXML
    private Label authorField;

    @FXML
    private Label albumField;

    @FXML
    private VBox viewBox;

    public TrackCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/track_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Track item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            nameField.setText(null);
            genreField.setText(null);
            albumField.setText(null);
            authorField.setText(null);
            setGraphic(null);
        } else {
            nameField.setText(item.getName());
            genreField.setText(item.getGenre()
                                   .getTitle());
            albumField.setText(item.getAlbum()
                                   .getName());
            authorField.setText(item.getAlbum()
                                    .getAuthor()
                                    .getName());
            setGraphic(viewBox);
        }
    }

}
