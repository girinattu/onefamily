package uk.onefamily.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

import static io.restassured.RestAssured.given;

public class RestSetUp {

    public StringWriter requestWriter;
    public PrintStream requestCapture;
    public StringWriter responseWriter;
    public PrintStream responseCapture;


    protected Response sendRequest(RequestSpecBuilder requestSpec, String url,
                                   Method httpMethod) {
        requestWriter = new StringWriter();
        requestCapture = new PrintStream(getWriterOutputStream(requestWriter), true);
        responseWriter = new StringWriter();
        responseCapture = new PrintStream(getWriterOutputStream(responseWriter), true);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        RequestLoggingFilter requestLoggingFilter = getRequestLoggingFilter();
        ResponseLoggingFilter responsesLoggingFilter = getResponseLoggingFilter();
        Response response = null;
        switch (httpMethod) {
            case GET:
                response = given().filter(requestLoggingFilter).urlEncodingEnabled(false).spec(requestSpec.build().filter(responsesLoggingFilter)).log().all().get(url);
                break;
            case POST:
                response = given().filter(requestLoggingFilter).urlEncodingEnabled(false).spec(requestSpec.build().filter(responsesLoggingFilter)).log().all().post(url);
                break;
            case DELETE:
                response = given().filter(requestLoggingFilter).urlEncodingEnabled(false).spec(requestSpec.build().filter(responsesLoggingFilter)).log().all().delete(url);
                break;
        }
        return response;
    }

    private ResponseLoggingFilter getResponseLoggingFilter() {
        return new ResponseLoggingFilter(responseCapture);
    }

    private RequestLoggingFilter getRequestLoggingFilter() {
        return new RequestLoggingFilter(requestCapture);
    }

    private WriterOutputStream getWriterOutputStream(StringWriter requestWriter) {
        return new WriterOutputStream(requestWriter, Charset.defaultCharset(), 1024, false);
    }


}

