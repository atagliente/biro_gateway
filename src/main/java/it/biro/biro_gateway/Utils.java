package it.biro.biro_gateway;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

public class Utils {

    static public void call( String url, String body) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        Unirest.post(url)
                .header("Content-Type", "application/json")
                .body(body)
                .asString();
    }

    @Data
    @AllArgsConstructor
    static public class LogData {
        private Date date;
        private String origin;
        private String message;
    }

    @Data
    @AllArgsConstructor
    static public class LogRequest {
        private String uri;
        private String id;
        private String raw;
    }

}
