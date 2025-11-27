package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import model.Note;
import model.Notebook;

public class NoteController {

    @FXML
    private TextField titleField;
    @FXML
    private TextArea contentArea;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private ListView<String> notesListView;

    private Notebook notebook;
    private int selectedIndex = -1;

    public NoteController() {
        notebook = new Notebook();
    }

    @FXML
    private void initialize() {
        // Set up ListView selection listener
        notesListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> onNoteSelected()
        );

        // Initially disable update and delete buttons
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    @FXML
    private void addNote() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();

        if (title.isEmpty() || content.isEmpty()) {
            showAlert("Error", "Please enter both title and content.");
            return;
        }

        Note note = new Note(title, content);
        notebook.addNote(note);
        updateNotesDisplay();
        clearFields();
    }

    @FXML
    private void updateNote() {
        if (selectedIndex >= 0) {
            String title = titleField.getText().trim();
            String content = contentArea.getText().trim();

            if (title.isEmpty() || content.isEmpty()) {
                showAlert("Error", "Please enter both title and content.");
                return;
            }

            Note updatedNote = new Note(title, content);
            notebook.updateNote(selectedIndex, updatedNote);
            updateNotesDisplay();
            clearFields();
            resetSelection();
        }
    }

    @FXML
    private void deleteNote() {
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Note");
            alert.setHeaderText("Are you sure you want to delete this note?");
            alert.setContentText("This action cannot be undone.");

            if (alert.showAndWait().get() == ButtonType.OK) {
                Note noteToRemove = notebook.getNotes().get(selectedIndex);
                notebook.removeNote(noteToRemove);
                updateNotesDisplay();
                clearFields();
                resetSelection();
            }
        }
    }

    private void onNoteSelected() {
        selectedIndex = notesListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Note selectedNote = notebook.getNotes().get(selectedIndex);
            titleField.setText(selectedNote.getTitle());
            contentArea.setText(selectedNote.getContent());

            // Enable update and delete buttons
            updateButton.setDisable(false);
            deleteButton.setDisable(false);
            addButton.setDisable(true);
        }
    }

    private void updateNotesDisplay() {
        notesListView.getItems().clear();
        for (Note note : notebook.getNotes()) {
            notesListView.getItems().add(note.toString());
        }
    }

    private void clearFields() {
        titleField.clear();
        contentArea.clear();
    }

    private void resetSelection() {
        selectedIndex = -1;
        notesListView.getSelectionModel().clearSelection();
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
        addButton.setDisable(false);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}