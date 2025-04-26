package com.skyscanner.hoenscanner.resources;

import com.skyscanner.hoenscanner.HoenScannerApplication;
import com.skyscanner.hoenscanner.model.Search;
import com.skyscanner.hoenscanner.model.SearchResult;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {

    @POST
    public List<SearchResult> searchCity(Search search) {
        String searchCity = search.getCity().toLowerCase();
        return HoenScannerApplication.searchResults.stream()
                .filter(result -> result.getCity().equalsIgnoreCase(searchCity))
                .collect(Collectors.toList());
    }
}