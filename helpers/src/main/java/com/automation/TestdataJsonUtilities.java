package com.automation;

import com.jayway.jsonpath.JsonPath;
import com.runner.runner.EnhancedLogging;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestdataJsonUtilities {

    /**
     * -- getDataFromLiteralPathJSONFile --
     *
     * @param sFileLocation
     * @param sJsonExpression
     * @return
     */
    public static String getDataFromLiteralPathJSONFile(String sFileLocation, String sJsonExpression) {

        try {
            String str = readFile(sFileLocation, StandardCharsets.UTF_8);
            Object jsonresult = JsonPath.parse(str).read(sJsonExpression);
            return TrimJSONResults(jsonresult.toString());

        } catch (IOException e) {
            EnhancedLogging.debug("Unable to get Data from Json File: " + sFileLocation + "\n" + e.getMessage());

        }

        return "NO JSON FILE FOUND";
    }


    /**
     * -- getDataFromResourcesRelativePathJSONFile --
     *
     * @param sjsonFileName
     * @param sJsonExpression
     * @return
     */
    public static String getDataFromResourcesRelativePathJSONFile(String sjsonFileName, String sJsonExpression) {

        try {


            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            File file = new File(classLoader.getResource(sjsonFileName).getFile());

            String str = new String(Files.readAllBytes(file.toPath()));

            Object jsonresult = JsonPath.parse(str).read(sJsonExpression);


            return TrimJSONResults(jsonresult.toString());

        } catch (IOException e) {
            EnhancedLogging.debug("Unable to get Data from Json File: " + sjsonFileName + "\n" + e.getMessage());
        }

        return "NO JSON FILE FOUND";
    }


    /**
     * -- getBooleanFromResourcesRelativePathJSONFile --
     *
     * @param sjsonFileName
     * @param sJsonExpression
     * @return
     */
    public static boolean getBooleanFromResourcesRelativePathJSONFile(String sjsonFileName, String sJsonExpression) {

        try {

            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            File file = new File(classLoader.getResource(sjsonFileName).getFile());

            String str = new String(Files.readAllBytes(file.toPath()));

            Object jsonresult = JsonPath.parse(str).read(sJsonExpression);

            boolean result = Boolean.parseBoolean(TrimJSONResults(jsonresult.toString()));
            return result;

        } catch (IOException e) {
            EnhancedLogging.debug("Unable to get Data from Json File: " + sjsonFileName + "\n" + e.getMessage());

        }

        return false;
    }

    /**
     * -- getDateFromResourcesRelativePathJSONFile --
     *
     * @param sjsonFileName
     * @param sJsonExpression
     * @return
     */
    public static String getDateFromResourcesRelativePathJSONFile(String sjsonFileName, String sJsonExpression) {

        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            File file = new File(classLoader.getResource(sjsonFileName).getFile());

            String str = new String(Files.readAllBytes(file.toPath()));

            Object jsonresult = JsonPath.parse(str).read(sJsonExpression);

            return TrimJSONResults(jsonresult.toString().replace("\\", ""));

        } catch (Exception e) {
            EnhancedLogging.debug("Unable to get Data from Json File: " + sjsonFileName + "\n" + e.getMessage());
        }

        return "NO JSON FILE FOUND";
    }

    /**
     * -- readFile --
     *
     * @param path
     * @param encoding
     * @return
     * @throws IOException
     */
    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /**
     * -- readFile --
     *
     * @param inString
     * @return
     */
    private static String TrimJSONResults(String inString) {


        return inString.substring(2, inString.length() - 2);


    }

}