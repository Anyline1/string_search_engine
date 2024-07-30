package ru.anyline.string_search_engine.model;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class Document {
    private int id;
    private String content;
    private Map<String, Integer> termFrequency;

    public Document() {
        this.termFrequency = new HashMap<>();
    }

    public Document(int id, String content) {
        this.id = id;
        this.content = content;
        this.termFrequency = new HashMap<>();
        calculateTermFrequency();
    }

    private void calculateTermFrequency() {
        String[] terms = content.toLowerCase().split("\\W+");
        for (String term : terms) {
            termFrequency.put(term, termFrequency.getOrDefault(term, 0) + 1);
        }
    }
}
