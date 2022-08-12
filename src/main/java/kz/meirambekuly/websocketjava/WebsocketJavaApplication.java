package kz.meirambekuly.websocketjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebsocketJavaApplication {

    /*
    * Создать WebSocket соединение для связи с клиентской частью.
    *  При запросе { "command": "addLog", "content": "someText" }
    *  сервер должен сохранить значение "content" в БД. При запросе { "command": "logs" },
    * сервер должен передать массив, в котором находятся все предыдущие запросы от клиента.
    P.S.: Клиентская часть должна быть внутри Java Spring Boot проекта.
    * */

    public static void main(String[] args) {
        SpringApplication.run(WebsocketJavaApplication.class, args);
    }

}
