package ec.diners.com.domain.models;

import ec.diners.com.domain.specification.Filter;
import ec.diners.com.domain.specification.FilterConcat;
import ec.diners.com.domain.specification.FilterOperator;
import ec.diners.com.domain.specification.OrderBy;
import ec.diners.com.domain.specification.OrderType;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class FilterPaginationQueryModel {

    private final List<String> filterRequests;
    private final List<String> orderRequests;
    private int page;
    private int pageSize;
    protected List<Filter> customFilters;
    protected List<OrderBy> customOrders;

    public FilterPaginationQueryModel() {
        this.customFilters = new ArrayList<>();
        this.customOrders = new ArrayList<>();
        this.filterRequests = new ArrayList<>();
        this.orderRequests = new ArrayList<>();
    }

    public FilterPaginationQueryModel(List<String> filterRequests, List<String> orderRequests, int page, int pageSize) {
        this.filterRequests = filterRequests == null ? new ArrayList<>() : filterRequests;
        this.orderRequests = orderRequests == null ? new ArrayList<>() : orderRequests;
        this.customFilters = new ArrayList<>();
        this.customOrders = new ArrayList<>();
        this.page = page;
        this.pageSize = pageSize;
    }

    public void addCustomFilters(List<Filter> customFilters) {
        this.customFilters.addAll(customFilters);
    }

    public void addCustomOrders(List<OrderBy> customOrders) {
        this.customOrders.addAll(customOrders);
    }

    public List<String> getFilterRequests() {
        return this.filterRequests;
    }

    public List<String> getOrderRequests() {
        return this.orderRequests;
    }

    public int getPage() {
        return this.page;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    private Integer createIntegerValue(String valueStr) {
        valueStr = valueStr.substring(8, valueStr.length() - 1);
        try {
            return Integer.valueOf(valueStr);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    private Double createDoubleValue(String valueStr) {
        valueStr = valueStr.substring(7, valueStr.length() - 1);
        try {
            return Double.valueOf(valueStr);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    private Boolean createBooleanValue(String valueStr) {
        valueStr = valueStr.substring(8, valueStr.length() - 1);
        return Boolean.parseBoolean(valueStr);
    }

    private Date createDateValue(String valueStr) {
        valueStr = valueStr.substring(5, valueStr.length() - 1);
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(valueStr);
        } catch (ParseException ignored) {
            return null;
        }
    }

    private Date createDateTimeValue(String valueStr) {
        valueStr = valueStr.substring(9, valueStr.length() - 1);
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valueStr);
        } catch (ParseException ignored) {
            return null;
        }
    }

    private Date createTimeValue(String valueStr) {
        valueStr = valueStr.substring(5, valueStr.length() - 1);
        try {
            return new SimpleDateFormat("HH:mm:ss").parse(valueStr);
        } catch (ParseException ignored) {
            return null;
        }
    }

    private Date createFinalDateValue(String valueStr) {
        //DateTime
        if (valueStr.startsWith(FilterValueType.DATE_TIME)) {
            return createDateTimeValue(valueStr);
        }
        //Date
        else if (valueStr.startsWith(FilterValueType.DATE)) {
            return createDateValue(valueStr);
        }
        //Time
        else if (valueStr.startsWith(FilterValueType.TIME)) {
            return createTimeValue(valueStr);
        }

        return null;
    }

    private Object createFinalNumberValue(String valueStr) {
        //Number Integer
        if (valueStr.startsWith(FilterValueType.INTEGER)) {
            return createIntegerValue(valueStr);
        }
        //Number Double
        else if (valueStr.startsWith(FilterValueType.DOUBLE)) {
            return createDoubleValue(valueStr);
        }

        return null;
    }

    private UUID createUuidValue(String valueStr) {
        valueStr = valueStr.substring(5, valueStr.length() - 1);
        try {
            return UUID.fromString(valueStr);
        } catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    private Object createValueObjectByString(String valueStr) {
        //Number
        if (valueStr.startsWith(FilterValueType.INTEGER) || valueStr.startsWith(FilterValueType.DOUBLE)) {
            return createFinalNumberValue(valueStr);
        }
        //Boolean
        else if (valueStr.startsWith(FilterValueType.BOOLEAN)) {
            return createBooleanValue(valueStr);
        }
        //UUID
        else if (valueStr.startsWith(FilterValueType.UUID)) {
            return createUuidValue(valueStr);
        }
        //Date
        else if (valueStr.startsWith(FilterValueType.DATE) || valueStr.startsWith(FilterValueType.DATE_TIME) || valueStr.startsWith(FilterValueType.TIME)) {
            return createFinalDateValue(valueStr);
        }

        return valueStr;
    }

    private boolean existCustomFilter(String fieldName) {
        return customFilters != null && customFilters.stream().anyMatch(f -> f.getFieldName().equals(fieldName));
    }

    public List<Filter> constructFilters() {
        var result = new ArrayList<Filter>();

        if (customFilters != null && !customFilters.isEmpty()) {
            result.addAll(customFilters);
        }

        for (var filterRequest : filterRequests) {
            var filter = createFilter(filterRequest);
            if (filter != null) {
                result.add(filter);
            }
        }

        return result;
    }

    private Filter createFilter(String filterRequest){
        var filterParameterSplit = filterRequest.split(Pattern.quote("__"));
        var fieldName = filterParameterSplit[0];
        var concat = "";
        var symbolInclude = "";

        if (filterParameterSplit.length < 3 || filterParameterSplit.length > 5 || existCustomFilter(fieldName)) {
            return null;
        }

        var operator = filterParameterSplit[1];
        var valueStr = filterParameterSplit[2];
        if (filterParameterSplit.length >= 4) {
            concat = filterParameterSplit[3];
        }
        if (filterParameterSplit.length == 5) {
            symbolInclude = filterParameterSplit[4];
        }

        Object value = createValueObjectByString(valueStr);
        if (value != null) {
            return new Filter(fieldName, FilterOperator.valueOf(operator.toUpperCase()), value, FilterConcat.valueOf(concat.toUpperCase()), symbolInclude);
        }

        return null;
    }

    private boolean existCustomOrder(String fieldName) {
        return customOrders != null && customOrders.stream().anyMatch(f -> f.getFieldName().equals(fieldName));
    }

    public List<OrderBy> constructOrders() {
        var result = new ArrayList<OrderBy>();

        if (customOrders != null && !customOrders.isEmpty()) {
            result.addAll(customOrders);
        }

        for (var orderRequest : orderRequests) {
            var orderParameterSplit = orderRequest.split(Pattern.quote("__"));
            var fieldName = orderParameterSplit[0];

            if (orderParameterSplit.length != 2 || existCustomOrder(fieldName)) {
                continue;
            }

            var orderType = orderParameterSplit[1];
            var orderBy = new OrderBy(fieldName, OrderType.valueOf(orderType));
            result.add(orderBy);
        }

        return result;
    }
}

