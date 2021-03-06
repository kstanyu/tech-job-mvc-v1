package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping("results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<HashMap<String, String>> searchResults = new ArrayList<>();
        //ArrayList<String> searchResults = new ArrayList<>();
        if(!searchType.equals("all")){
            searchResults = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        else {
            if (!searchTerm.isEmpty()){
                searchResults = JobData.findByValue(searchTerm);
            } else {
                searchResults = JobData.findAll();
            }
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchresults", searchResults);
        model.addAttribute("numberofresults", "Result(s) " + searchResults.size());

        return "search";
    }
}
