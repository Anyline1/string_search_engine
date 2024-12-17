package ru.anyline.string_search_engine.service;

import lombok.Getter;
import ru.anyline.string_search_engine.model.Document;
import ru.anyline.string_search_engine.model.SearchResult;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Data
public class SearchEngineService {
    
    private List<Document> documents = new ArrayList<>();
    private Map<String, List<Document>> invertedIndex = new HashMap<>();
    private Map<String, Double> idf = new HashMap<>();
    private int currentId = 1;

    public void addDocument(String content) {
        Document document = new Document(currentId++, content);
        documents.add(document);
        indexDocument(document);
        calculateIDF();
    }

    private void indexDocument(Document document) {
        for (String term : document.getTermFrequency().keySet()) {
            invertedIndex.computeIfAbsent(term, k -> new ArrayList<>()).add(document);
        }
    }

    private void calculateIDF() {
        int totalDocuments = documents.size();
        for (String term : invertedIndex.keySet()) {
            int documentCount = invertedIndex.get(term).size();
            idf.put(term, Math.log((double) totalDocuments / documentCount));
        }
    }

    public List<SearchResult> search(String term) {
        term = term.toLowerCase();
        List<SearchResult> results = new ArrayList<>();
        if (invertedIndex.containsKey(term)) {
            for (Document document : invertedIndex.get(term)) {
                double tf = (double) document.getTermFrequency().get(term) / document.getTermFrequency().size();
                double tfIdf = tf * idf.get(term);
                results.add(new SearchResult(document.getId(), tfIdf));
            }
        }
        results.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));
        return results;
    }

}
