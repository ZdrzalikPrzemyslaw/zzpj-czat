package tech.pzjswpooks.zzpj.chat.api.cdi.stateful.endpoints;

import javax.ejb.Stateful;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

// TODO: 24.04.2021 https://github.com/javaee/tutorial-examples/tree/master/web/websocket/websocketbot
//  https://javaee.github.io/tutorial/websocket012.html
@Stateful
@ServerEndpoint(value="/socket_example/{id}", encoders = {}, decoders = {})
public class ExampleWebsocketEndpoint {

    /* Queue for all open WebSocket sessions */
    private static Queue<Session> queue = new ConcurrentLinkedQueue<>();

    @OnOpen
    public void open(Session session,
                     EndpointConfig conf) {
        queue.add(session);
    }

    @OnMessage
    public void message(Session session,
                        String msg) {
    }

    @OnError
    public void error(Session session,
                      Throwable error) {
        queue.remove(session);
    }

    @OnClose
    public void close(Session session,
                      CloseReason reason) {
        queue.remove(session);
    }
}
