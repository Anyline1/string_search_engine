package ru.anyline.string_search_engine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.anyline.string_search_engine.model.Document;
import ru.anyline.string_search_engine.model.SearchResult;
import ru.anyline.string_search_engine.service.SearchEngineService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class SearchEngineController {

    private final SearchEngineService searchEngineService;

    @PostMapping("/addDocument")
    public void addDocument(@RequestBody Map<String, String> request){
        String content = request.get("content");
        searchEngineService.addDocument(content);
    }

    @GetMapping("/search")
    public List<SearchResult> search(@RequestParam String term){
        return searchEngineService.search(term);
    }

    @GetMapping("/documents")
    public List<Document> getAll(){
        return searchEngineService.getDocuments();
    }
}
