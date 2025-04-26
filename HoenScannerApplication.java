package com.skyscanner.hoenscanner;

import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skyscanner.hoenscanner.model.SearchResult;

import com.skyscanner.hoenscanner.resources.SearchResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static List<SearchResult> searchResults = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        
        new HoenScannerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HoenScannerConfiguration> bootstrap) {
        // 
    }

    @Override
    public void run(HoenScannerConfiguration configuration, Environment environment) throws Exception {
        
        ObjectMapper mapper = new ObjectMapper();

        InputStream rentalCarsStream = getClass().getClassLoader().getResourceAsStream("rental_cars.json");
        InputStream hotelsStream = getClass().getClassLoader().getResourceAsStream("hotels.json");

        if (rentalCarsStream != null) {
            
            searchResults.addAll(mapper.readValue(rentalCarsStream, new TypeReference<List<SearchResult>>() {}));
        }
        if (hotelsStream != null) {
            
            searchResults.addAll(mapper.readValue(hotelsStream, new TypeReference<List<SearchResult>>() {}));
        }

        environment.jersey().register(new SearchResource());
    }
}
