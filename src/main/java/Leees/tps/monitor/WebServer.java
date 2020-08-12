package Leees.tps.monitor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.bukkit.Bukkit;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import java.io.*;
import java.net.InetSocketAddress;

public class WebServer {

    public static void main() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(6767),0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
    }
    public static class MyHandler
            implements HttpHandler {
        public void handle(HttpExchange t) {
            StringBuilder contentBuilder = new StringBuilder();
            try {
                BufferedReader in = new BufferedReader(new FileReader("plugins/LeeesTpsMonitor/index.html"));
                String str;
                while ((str = in.readLine()) != null) {
                    contentBuilder.append(str);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String response = contentBuilder.toString().replaceAll("server_tps", String.valueOf(MinecraftServer.getServer().recentTps[0])).replaceAll("server_playercount", String.valueOf(Bukkit.getServer().getOnlinePlayers().size())).replaceAll("server_playerlist", String.valueOf(PlayerGeter.getPlayers()));
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
