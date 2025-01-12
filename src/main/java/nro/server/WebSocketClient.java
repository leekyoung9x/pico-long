package nro.server;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletionStage;

public class WebSocketClient {

    private WebSocket webSocket;

    public WebSocketClient(URI uri) {
        HttpClient client = HttpClient.newHttpClient();
        this.webSocket = client.newWebSocketBuilder()
                .buildAsync(uri, new WebSocket.Listener() {
                    @Override
                    public void onOpen(WebSocket webSocket) {
                        System.out.println("Connected to server");
                        WebSocket.Listener.super.onOpen(webSocket);
                    }

                    @Override
                    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                        System.out.println("Received message: " + data);
                        return WebSocket.Listener.super.onText(webSocket, data, last);
                    }

                    @Override
                    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
                        System.out.println("Session closed: " + reason);
                        return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
                    }

                    @Override
                    public void onError(WebSocket webSocket, Throwable error) {
                        error.printStackTrace();
                    }
                }).join();
    }

    public void sendMessage(String message) {
        this.webSocket.sendText(message, true);
    }

    public static void sendClient(String id, String message) {
        try {
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
            URI uri = new URI("ws", null, "localhost", 6000, "/send", "data=" + id + "|" + encodedMessage, null);
            WebSocketClient client = new WebSocketClient(uri);
            client.sendMessage(message);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public static void sendClient(String id, String message, String type) {
        try {
            String encodedMessage = URLEncoder.encode(message + "?" + type, StandardCharsets.UTF_8.toString());
            URI uri = new URI("ws", null, "localhost", 6000, "/send", "data=" + id + "|" + encodedMessage, null);
            WebSocketClient client = new WebSocketClient(uri);
            client.sendMessage(message);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}