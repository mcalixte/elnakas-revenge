package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Request {
    private static int GLOBAL_REQUEST_ID = 999;

    private int currentRequestID;
    private String type;
    private String url;
    private String HTTPVersion;
    private HashMap<String, String> headers;
    private String body;

    public Request(String requestStr) {

        List<String> rawRequestLines = Arrays.asList(requestStr.split("\r\n"));

        String requestLine = rawRequestLines.get(0);
        List<String> headerLines = new ArrayList<>();

        if(requestLine.split(" ")[0].equalsIgnoreCase("post"))
            headerLines = rawRequestLines.subList(1, rawRequestLines.indexOf(""));
        else
            headerLines = rawRequestLines.subList(1, rawRequestLines.size() - 1);

        String requestBody = rawRequestLines.get(rawRequestLines.lastIndexOf("") + 1);

        String[] requestLineElements = requestLine.split(" ");
        this.type = requestLineElements[0];
        this.url = "server_files" + requestLineElements[1];
        this.HTTPVersion = requestLineElements[2];
        this.currentRequestID = ++GLOBAL_REQUEST_ID;

        headers = new HashMap<>();
        for (String headerLine: headerLines) {
            String[] headerLineElements = headerLine.split(": ");
            headers.put(headerLineElements[0], headerLineElements[1]);
        }

        this.body = requestBody;
    }

    public String getType() {
        return type;
    }

    public int getRequestID() {
        return this.currentRequestID;
    }

    public String getUrl() {
        return url;
    }

    public String getHTTPVersion() {
        return HTTPVersion;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder();
        stringRepresentation.append(this.type +" "+ this.url +" "+ this.HTTPVersion +"\n");
        for (Object headerKey: this.headers.keySet()) {
            stringRepresentation.append(headerKey + ": " + this.headers.get(headerKey) +"\n");
        }
        stringRepresentation.append("\n\n" + this.body + "\n");

        return stringRepresentation.toString();
    }
}
