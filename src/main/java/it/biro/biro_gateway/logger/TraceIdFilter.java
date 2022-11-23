package it.biro.biro_gateway.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import it.biro.biro_gateway.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class TraceIdFilter implements WebFilter {

    @Value("${nameOrDockerIP}")
    private String nameOrDockerIP;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();

        return chain.filter(exchange)
                .contextWrite(context -> {
                    try {
                        ObjectWriter ow = new ObjectMapper().writer();
                        Utils.LogRequest logRequest = new Utils.LogRequest(serverHttpRequest.getURI().toString(),
                                exchange.getAttribute("cachedRequestBodyObject"),null
                        );
                        String inner = ow.writeValueAsString(logRequest);
                        Utils.LogData logData = new Utils.LogData(new Date(System.currentTimeMillis()),
                         "biro_gateway",
                               inner);
                        String json = ow.writeValueAsString(logData);
                        Utils.call("http://" + nameOrDockerIP + ":8081/api/log/", json);
                    } catch (Exception ex) {
                        System.out.println("EXC: " + ex);
                    }
                    return context;
                });
    }

}
