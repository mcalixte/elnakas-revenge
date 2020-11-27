package client;

import java.io.*;
import java.util.Arrays;

public class Request implements Serializable {

    private String type;
    private String url;
    private String version;
    private String[] headers;
    private String body;

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers.clone();
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Request{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", version='" + version + '\'' +
                ", headers=" + Arrays.toString(headers) +
                ", body='" + body + '\'' +
                '}';
    }
}
