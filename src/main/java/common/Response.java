package common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

public class Response {
    private final String separator = "\r\n";

    private String type;
    private String HTTPVersion;
    private HashMap<String, String> headers;

    private int statusCode;
    private String body;

    private Request request;

    public Response(Request request, int statusCode, String bodyContent) {
        this.type = request.getType();
        this.HTTPVersion = request.getHTTPVersion();
        this.headers = new HashMap<>();
        for (Map.Entry<String, String> entry: request.getHeaders().entrySet()) {
            this.headers.put(entry.getKey(), entry.getValue()+ separator);
        }

        this.statusCode = statusCode;
        this.body = bodyContent;

        this.request = request;
    }

    public String createResponse() {
        StringBuilder response = new StringBuilder();

        response.append("HTTP/1.0 " + statusCode + " " + HTTPStatusCodes.getStatus(statusCode) + separator);
        for (Map.Entry<String, String> entry: headers.entrySet()) {
            response.append(entry.getKey() + ": " + entry.getValue());
        }

        if (body != null && (statusCode == 200 || statusCode == 201)) {
            response.append("ContentType: text/html" + separator);
            response.append("ContentLength: " + body.length() + separator);
            response.append(separator + separator);
            response.append(body);
        }

        if (HttpfsServer.getVerbose()) {
            writeLog(response.toString());
        }

        return response.toString();
    }

    private void writeLog(String response) {
        try {
            BufferedWriter logger = new BufferedWriter(new FileWriter("logs.txt", true));
            logger.write(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()) + "\n");
            logger.write(this.request.toString());
            logger.write(response + "\n");
            logger.flush();
            logger.close();
        }
        catch (IOException e) {
            System.out.println("problem with logger");
        }
    }

    public String toString() {
        StringBuilder stringRepresentation = new StringBuilder();
        stringRepresentation.append("HTTP/"+ this.HTTPVersion +" "+ this.statusCode +" "+ this.statusCode +"\n");
        for (Object headerKey: this.headers.keySet()) {
            stringRepresentation.append(headerKey + ": " + this.headers.get(headerKey) +"\n");
        }
        stringRepresentation.append(this.body + "\n");

        return stringRepresentation.toString();
    }
}
