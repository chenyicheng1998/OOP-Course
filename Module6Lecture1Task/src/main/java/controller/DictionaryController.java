package controller;

import model.Dictionary;
import model.WordNotFoundException;

public class DictionaryController {
    private Dictionary dictionary;

    public DictionaryController() {
        dictionary = new Dictionary();
        // Initialize with some hardcoded words
        initializeDictionary();
    }

    private void initializeDictionary() {
        dictionary.addWord("java", "A programming language");
        dictionary.addWord("computer", "An electronic device for processing data");
        dictionary.addWord("program", "A set of instructions for a computer");
        dictionary.addWord("algorithm", "A step-by-step procedure for calculations");
        dictionary.addWord("variable", "A storage location with a specific type");
    }

    public String searchWord(String word) {
        try {
            if (word == null || word.trim().isEmpty()) {
                return "Please enter a word to search";
            }
            return dictionary.searchWord(word.trim());
        } catch (WordNotFoundException e) {
            return e.getMessage();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}