package com.java.http.call;

import static java.nio.charset.StandardCharsets.UTF_16;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

    private final String USER_AGENT = "Mozilla/5.0";
    private static final String FILE_PATH = "/Users/gkushwaha/Downloads/18.txt";
    private static final String URL = "http://validate.nimbuzz.com/validate/service/state?user=";
    private static final AtomicInteger counter = new AtomicInteger();
    private static final AtomicInteger veriCounter = new AtomicInteger();
    private static final AtomicInteger unVeriCounter = new AtomicInteger();
    private static final AtomicInteger notFoundCounter = new AtomicInteger();
    private static final boolean shouldWait = true;
    private static final boolean shouldWriteToFile = true;
    private static final int WAIT = 100;
    private static final long startTime = new Date().getTime();

    public static void main(final String[] args) throws Exception {
        new HttpURLConnectionExample().sendGet();
        System.out.println("Time taken :" + (new Date().getTime() - startTime) / 1000 + " Seconds");
        System.out.println("Total user :" + counter);
        System.out.println("Total verified user :" + veriCounter);
        System.out.println("Total un-verified user :" + unVeriCounter);
        System.out.println("Total not-found user :" + notFoundCounter);
    }

    // HTTP GET request
    private void sendGet() throws Exception {
        try (final Stream<String> lines = Files.lines(Paths.get(FILE_PATH), UTF_16);
                final BufferedWriter newBufferedWriter = getWriter();) {
            lines.parallel().forEachOrdered(line -> process(line, newBufferedWriter));
        }
    }

    private String process(final String user, final BufferedWriter newBufferedWriter) {
        final String trimUser = user.trim();
        final int incrementAndGet = counter.incrementAndGet();
        if (incrementAndGet % 200 == 0) {
            try {
                System.out.println("waiting for " + WAIT + " milisec" + " current count : " + counter
                        + " current verified user : " + veriCounter + " current un-verified user :" + unVeriCounter
                        + " current not-found user : " + notFoundCounter + " in : "
                        + (new Date().getTime() - startTime) / 1000 + " seconds");
                if (shouldWait) {
                    Thread.sleep(WAIT);
                }
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
        final String state = getResponse(getConnection(URL + trimUser));
        final String stateStr = "\n" + trimUser + "\t" + state;
        try {
            if (shouldWriteToFile) {
                newBufferedWriter.append(stateStr);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        System.out.println(stateStr);
        return state;
    }

    private BufferedWriter getWriter() throws IOException {
        final BufferedWriter newBufferedWriter =
                Files.newBufferedWriter(Paths.get(FILE_PATH + "_result"), Charset.defaultCharset());
        return newBufferedWriter;
    }

    private int getResponseCode(final HttpURLConnection con) {
        try {
            return con.getResponseCode();
        } catch (final Exception e) {
            e.printStackTrace();
            return 500;
        }
    }

    private String getResponse(final HttpURLConnection con) {
        final int code = getResponseCode(con);
        if (code != 200) {
            notFoundCounter.incrementAndGet();
            // System.out.println("Code : " + code);
            return "N/A";
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));) {
            String inputLine;
            final StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println(response.toString());
            final boolean b = response.indexOf("unverified") > 0;
            if (b) {
                unVeriCounter.incrementAndGet();
                return "unverified";
            } else {
                veriCounter.incrementAndGet();
                return "verified";
            }
        } catch (final Exception e) {
            System.out.println("Failed to fetch response from connection ");
            e.printStackTrace();
        }
        return "N/A";
    }

    private HttpURLConnection getConnection(final String url) {
        HttpURLConnection con = null;
        try {
            final URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/xml");
            con.setRequestProperty("Content-Type", "application/xml");
            con.setRequestProperty("User-Agent", USER_AGENT);
            System.out.println("GET : " + url + " Code : " + getResponseCode(con));
        } catch (final Exception e) {
            System.out.println("Failed to connect");
            e.printStackTrace();
        }
        return con;
    }

    // HTTP POST request
    private void sendPost() throws Exception {

        final String url = "https://selfsolve.apple.com/wcResults.do";
        final URL obj = new URL(url);
        final HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        // add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        final String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        final DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        final int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        final StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // print result
        System.out.println(response.toString());

    }

}
