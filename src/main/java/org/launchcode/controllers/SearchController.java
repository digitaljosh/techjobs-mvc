package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }


    // TODO #1 - Create handler to process search request and display results


    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        ArrayList<HashMap<String, String>> jobs = JobData.findAll();
        ArrayList<HashMap<String, String>> searchList = new ArrayList<>();


        if (searchType.equals("all")) {
            for (int i = 0; i < jobs.size(); i++) {
                HashMap<String, String> job = jobs.get(i);
                for (Map.Entry<String, String> record : jobs.get(i).entrySet()) {
                    String value = record.getValue();

                    if (value.contains(searchTerm)) {
                        searchList.add(job);
                    }
                }
            }
        } else {
            for (int i = 0; i < jobs.size(); i++) {
                HashMap<String, String> job = jobs.get(i);
                for (Map.Entry<String, String> record : jobs.get(i).entrySet()) {
                    String key = record.getKey();
                    String value = record.getValue();
                    if (key.contains(searchType)) {
                        if (value.contains(searchTerm)) {
                            searchList.add(job);
                        }
                    }
                }
            }

        }
        model.addAttribute("jobList", searchList);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
}

    // look up search results via JobData class, pass that into search.html view
    // also pass ListController.columnChoices to the view similar to above


