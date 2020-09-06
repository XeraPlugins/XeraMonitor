package Xera.Monitor;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XeraMonitor extends JavaPlugin {
    public void onEnable() {
        saveDefaultConfig();

        File dir = new File("plugins/XeraMonitor");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File indexhtml = new File(dir, "index.html");

        if (!indexhtml.exists()) {
            try {
                indexhtml.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                getServer().getPluginManager().disablePlugin(this);
                return;
            }

            if (indexhtml.exists()) {
                try {
                    String fileContent = "<html>\n" +
                            "  <head>\n" +
                            "    <title>XeraMonitor</title>\n" +
                            "    <meta http-equiv=\"refresh\" content=\"5\" >\n" +
                            "  </head>\n" +
                            "  <body>\n" +
                            "    <form action=\"/submit\">\n" +
                            "      <div  align=\"center\"><label for=\"username\">\n" +
                            "    <br><br>\n" +
                            "    <img src=\"https://cdn.discordapp.com/attachments/703638060792938578/710023538840698880/server-icon.png\"  width=\"64\" height=\"64\">\n" +
                            "    <b><h1>XeraMonitor</h1></b></label>\n" +
                            "    <h1>TPS:</h1> <h1 style=\"color:#00ff00;\">%server_tps%</h1>\n" +
                            "    <br>\n" +
                            "    <h1>PlayerCount:<h1> <h1 style=\"color:#00ff00;\">%server_playercount%</h1>\n" +
                            "    <br>\n" +
                            "    <h1>PlayerList:</h1>\n" +
                            "    <h4 style=\"color:#00ff00;\">%server_playerlist%</h4>\n" +
                            "      </div>  </body>\n" +
                            "          <style>\n" +
                            "body {\n" +
                            "    color: black;\n" +
                            "    background-image:url('https://cdn.discordapp.com/attachments/699025897373827073/714543738118209566/2020-05-25_11.21.34.png');\n" +
                            "    background-repeat: no-repeat;\n" +
                            "    background-size: 100% 100%;\n" +
                            "    font-family: Verdana,Geneva,sans-serif; \n" +
                            "}\n" +
                            "h1 {\n" +
                            "  color: white;\n" +
                            "}\n" +
                            "h2 {\n" +
                            "  color: white;\n" +
                            "}\n" +
                            "h3 {\n" +
                            "  color: white;\n" +
                            "}\n" +
                            "h4 {\n" +
                            "  color: white;\n" +
                            "}\n" +
                            "h5 {\n" +
                            "  color: white;\n" +
                            "}\n" +
                            "h6 {\n" +
                            "  color: white;\n" +
                            "}\n" +
                            "html {\n" +
                            "    height: 100%\n" +
                            "}\n" +
                            ".class { \n" +
                            "  font-family: Verdana,Geneva,sans-serif; \n" +
                            "}\n" +
                            "            body, html\n" +
                            "            {\n" +
                            "                margin: 0; padding: 0; height: 100%; overflow: hidden;\n" +
                            "            }\n" +
                            "\n" +
                            "            #content\n" +
                            "            {\n" +
                            "                position:absolute; left: 0; right: 0; bottom: 0; top: 0px; \n" +
                            "            }\n" +
                            "</style>\n" +
                            "</html>";

                    BufferedWriter writer = new BufferedWriter(new FileWriter("plugins/XeraMonitor/index.html"));

                    writer.write(fileContent);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    getServer().getPluginManager().disablePlugin(this);
                }
            }
        }

        try {
            new WebServer(this).main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
