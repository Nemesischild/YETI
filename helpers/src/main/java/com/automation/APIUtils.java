package com.automation;

import com.google.gson.Gson;
import com.runner.runner.EnhancedLogging;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonParseException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.DeserializationFeature;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class APIUtils {

    /**
     *
     * -- retrieveResourceFromResponse --
     *
     * Method accepts a Http response object
     *
     * @param response - Response object
     * @param clazz - response matching class
     * @param <T>
     * @return - Object Class object defined in input params populkated with the details from the response otherwise returns NULL
     * @throws IOException
     */
    public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz)
            throws IOException {

        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        try{
            ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //String  cycle = jsonFromResponse.replace("[\"","{\"");
           // cycle = jsonFromResponse.replace("\"]","\"}");

            return mapper.readValue(jsonFromResponse, clazz);
        }catch(Exception e) {
            EnhancedLogging.debug("Failed to populate " + clazz.getName() + " from supplied API response" + e.getMessage());
            return null;
        }
    }

    /**
     *
     *  -- retrieveResourceCollectionFromResponse --
     *
     * @param response - API respnse from a http Call
     * @param type -  Class object that the respinse is meant to populate
     * @param <D> - Generic type to accept Class (MUST be followed with '[]' - See Example below)
     * @return - simple List of classes containing the relevant data from the response
     * @throws IOException
     * @throws JsonParseException
     *
     * EXAMPLE CALL :  APIUtils.retrieveResourceCollectionFromResponse(response, myClass[].class);
     *
     */
    public static <D> D[] retrieveResourceCollectionFromResponse(HttpResponse response, Class<D[]> type)  throws IOException, JsonParseException {
        String jsonFromResponse = EntityUtils.toString(response.getEntity());
        try {
            return new Gson().fromJson(jsonFromResponse, type);
        } catch (Exception e) {
            EnhancedLogging.debug("Failed to populate " + type.getName() + " from supplied API response" + e.getMessage());
            return null;
        }
    }

 }
