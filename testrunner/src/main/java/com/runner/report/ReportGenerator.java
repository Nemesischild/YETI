package com.runner.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.runner.annotations.Setup;
import com.runner.annotations.Setup.ReportType;
import com.runner.runner.EnhancedAssertion;
import com.runner.runner.EnhancedLogging;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


/**
 * INTERNAL USE ONLY.
 * This class is the thread spawned on death of the test harness and is used to generate reports to the user.
 *
 * @author ryandixon1993@gmail.com
 */
public class ReportGenerator extends Thread {

    private static Setup setup;

    private static String path = "src/main/resources/reports";
    private static File outputFile;
    private static boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
            getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;

    public static final HashMap<String, TestSuite> SUITES = new HashMap<String, TestSuite>();

    public ReportGenerator(Setup setup) {
        ReportGenerator.setup = setup;
    }

    @Override
    public synchronized void start() {
        // Detect Settings for Report Type
        System.out.println("Finished testing. getting reportable data from annotations...");
        if (SUITES == null) {
            System.out.println("Something went wrong, could not generate report.");
            return;
        } else {

        }

        System.out.println();
        System.out.println("Preparing report...");
        try {
            prepareReport();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.start();
    }


    private static void prepareFilePath(String reportName) {
        EnhancedLogging.debug("Preparing new report");
        if (new File(path + reportName).exists()) {
            System.out.println("Found old test, deleting.");
            new File(path).delete();
        }
        System.out.println("Outputting report to " + new File(path).getAbsolutePath() + File.separator + reportName);
        outputFile = new File(path + reportName);

        try {
            outputFile.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void prepareReport() throws IOException, InterruptedException {
        LocalDate localDate = LocalDate.now();
        String sFileName = "";
        for (ReportType report : setup.reportType()) {
            switch (report) {
                case EXCEL:
                    if (isDebug) {
                        sFileName = getSetup().application().replace(" ", "-")  + " report.xlsx";
                    } else {
                        String todaysDate = LocalDate.now().toString();

                        sFileName =  getSetup().application().replace(" ", "-") + "-report-" + todaysDate.replace("-", "") +".xlsx";
                    }
                    prepareFilePath(sFileName);
                    ExcelReport.generateExcelReport(outputFile);
                    break;
                case EXTENT_REPORT:

                    if (isDebug) {

                        sFileName =  getSetup().application().replace(" ", "") + "-report.html";
                    } else {
                        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mmZ");
                        String todaysDate = LocalDate.now().toString();

                        sFileName =  getSetup().application().replace(" ", "") + "-report-" + todaysDate.replace("-", "") + ".html";
                    }

                    prepareFilePath(sFileName);
                    ExtentReport.generateReport(path + sFileName);
           //         shareReport(sFileName);
                    break;
                case JUNIT_XML:
                default:
                    prepareFilePath("report.xml");
                    JUnitReport.generateJUnitReport(outputFile);
                    break;
            }
        }
    }

    //TODO:  Work out how to Share report to somewhere useful
    //private static boolean shareReport(String sFile) throws InterruptedException {
//
//     //APIKEY - AIzaSyBoL7aH57VgmoGfbQSgNq0YUGENKJl3w-s
//    //https://www.googleapis.com/drive/v3/files?uploadType=media&key=AIzaSyBoL7aH57VgmoGfbQSgNq0YUGENKJl3w-s
//
//
//    //File file;
//    Path pFilePath = Paths.get(sFile);
//
//    byte[] fileContent = Files.readAllBytes(pFilePath);
//
//
//
//    EnhancedLogging.testlog("Adding new Pet Record");
//    CloseableHttpClient client = HttpClients.createDefault();
//    HttpEntity entity = new ByteArrayEntity(fileContent);
//    Long byteLength = entity.getContentLength();
//
//    HttpPost httpPost = new HttpPost("https://www.googleapis.com/drive/v3/files?supportsAllDrives=true&supportsTeamDrives=true&useContentAsIndexableText=true&quotaUser=MG&key=AIzaSyBoL7aH57VgmoGfbQSgNq0YUGENKJl3w-s");
//    httpPost.setEntity(entity);
//    httpPost.addHeader("Content-Type", "text/html");
//    httpPost.addHeader("Content-Length", byteLength.toString());
////folder id //1ASJ0MbRQC2x0hXCs-fXwJkGKN7AacD7Q
//    try {
//        CloseableHttpResponse response = client.execute(httpPost);
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//
//
//    return true;
//}




    public static Setup getSetup() {
        return ReportGenerator.setup;
    }

}
