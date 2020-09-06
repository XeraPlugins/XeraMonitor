package Xera.Monitor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class WebServer {
    XeraMonitor plugin;

    public WebServer(XeraMonitor plugin) {
        this.plugin = plugin;
    }

    public void main() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(plugin.getConfig().getInt("port")),0);

        server.createContext("/", new WebsiteHandler());
        server.createContext("/tps", new TpsHandler());
        server.createContext("/playercount", new PlayerCountHandler());
        server.createContext("/playerlist", new PlayerListHandler());

        server.setExecutor(null);
        server.start();
    }

    public static class WebsiteHandler implements HttpHandler {
        public void handle(HttpExchange t) {
            StringBuilder contentBuilder = new StringBuilder();

            try {
                BufferedReader in = new BufferedReader(new FileReader("plugins/XeraMonitor/index.html"));
                String str;

                while ((str = in.readLine()) != null) {
                    contentBuilder.append(str);
                }

                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String response = contentBuilder.toString()
                    .replaceAll("%server_tps%", String.valueOf(Bukkit.getServer().getTPS()[0]))
                    .replaceAll("%server_playercount%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size()))
                    .replaceAll("%server_playerlist%", String.valueOf(PlayerGeter.getPlayers()));

            try {
                t.sendResponseHeaders(200, response.length());
            } catch (IOException e) {
                e.printStackTrace();
            }

            OutputStream os = t.getResponseBody();

            try {
                os.write(response.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class TpsHandler implements HttpHandler {
        public void handle(HttpExchange t) {
            String response = String.valueOf(Bukkit.getServer().getTPS()[0]);

            try {
                t.sendResponseHeaders(200, response.length());
            } catch (IOException e) {
                e.printStackTrace();
            }

            OutputStream os = t.getResponseBody();

            try {
                os.write(response.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class PlayerCountHandler implements HttpHandler {
        public void handle(HttpExchange t) {
            String response = String.valueOf(Bukkit.getServer().getOnlinePlayers().size());

            try {
                t.sendResponseHeaders(200, response.length());
            } catch (IOException e) {
                e.printStackTrace();
            }

            OutputStream os = t.getResponseBody();

            try {
                os.write(response.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class PlayerListHandler implements HttpHandler {
        public void handle(HttpExchange t) {
            String response = PlayerGeter.getPlayers();

            try {
                t.sendResponseHeaders(200, response.length());
            } catch (IOException e) {
                e.printStackTrace();
            }

            OutputStream os = t.getResponseBody();

            try {
                os.write(response.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
