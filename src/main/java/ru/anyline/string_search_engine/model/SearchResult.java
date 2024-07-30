package ru.anyline.string_search_engine.model;

import lombok.Data;

@Data
public class SearchResult {

    private int documentId;
    private double score;

    public SearchResult(int documentId, double score) {
        this.documentId = documentId;
        this.score = score;
    }


}

