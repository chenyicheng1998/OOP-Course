package model;

import java.util.ArrayList;
import java.util.List;

public class Notebook {
    private List<Note> notes;

    public Notebook() {
        notes = new ArrayList<>();
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes); // Return a copy to protect internal data
    }

    public void removeNote(Note note) {
        notes.remove(note);
    }

    public void updateNote(int index, Note updatedNote) {
        if (index >= 0 && index < notes.size()) {
            notes.set(index, updatedNote);
        }
    }
}