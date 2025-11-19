package model;

import java.util.HashMap;

public class Dictionary {
    private HashMap<String, String> wordMap;

    public Dictionary() {
        wordMap = new HashMap<>();
    }

    public void addWord(String word, String meaning) {
        if (word != null && !word.trim().isEmpty()) {
            wordMap.put(word.toLowerCase(), meaning);
        }
    }

    public String searchWord(String word) throws WordNotFoundException {
        if (word == null || word.trim().isEmpty()) {
            throw new IllegalArgumentException("Word cannot be empty");
        }

        String meaning = wordMap.get(word.toLowerCase());
        if (meaning == null) {
            throw new WordNotFoundException("Word '" + word + "' not found in dictionary");
        }

        return meaning;
    }
}