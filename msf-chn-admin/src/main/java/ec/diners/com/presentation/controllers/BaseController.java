package ec.diners.com.presentation.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.diners.com.domain.models.FilterPaginationQueryModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.owasp.encoder.Encode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseController {

    protected BaseController(){
    }

    protected FilterPaginationQueryModel getFilterPaginationQueryModel(List<String> filters, List<String> orders, int page, int pageSize){
        var safeFilters = new ArrayList<String>();
        if (filters != null){
            for (var filter : filters){
                safeFilters.add(sanitizeString(filter));
            }
        }

        var safeOrders = new ArrayList<String>();
        if (orders != null){
            for (var order : orders){
                safeOrders.add(sanitizeString(order));
            }
        }

        return new FilterPaginationQueryModel(safeFilters, safeOrders, page, pageSize);
    }

    protected String sanitizeString(String input) {
        return Encode.forHtml(input);
    }

    protected UUID sanitizeId(UUID input) {
        if (input == null)
            return null;
        var stringId = Encode.forHtml(input.toString());
        return UUID.fromString(stringId);
    }

    protected List<UUID> sanitizeListId(List<UUID> input) {
        List<UUID> output = new ArrayList<>();
        for (var id : input){
            output.add(sanitizeId(id));
        }

        return output;
    }

    protected String sanitizeEmail(String email) {
        return email.trim().toLowerCase();
    }


    protected String sanitizeResponse(String response) {
        return Encode.forHtml(response);
        
    }


    protected ResponseEntity<Object> createResponse(Object response, HttpStatus status) {
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonResponse;

        try {
            var jsonResponseString = objectMapper.writeValueAsString(response);
            var jsonResponseSanitized = sanitizeResponse(jsonResponseString);
            if (response instanceof String) {
                jsonResponse = objectMapper.readValue(jsonResponseSanitized, JsonNode.class);
            } else {
                jsonResponse = response;
            }
        } catch (JsonProcessingException e) {
            jsonResponse = "{\"error\":\"Error processing JSON response\"}";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(jsonResponse, status);
    }
}
