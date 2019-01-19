package com.adolphor.mynety.server.config;

import com.adolphor.mynety.common.constants.LanStrategy;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * 加载xml格式config
 *
 * @author Bob.Zhu
 * @Email adolphor@qq.com
 * @since v0.0.1
 */
@Slf4j
public class ConfigLoader {

 private static final String configFile = "server-config.yaml";

  public static void loadConfig() throws Exception {

    try (InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream(configFile)) {

      Map config = new Yaml().load(is);
      System.out.println(config);

      Map server = (Map) config.get("server");
      if (server != null) {
        Object socksPort = server.get("port");
        if (socksPort != null) {
          Config.PROXY_PORT = Integer.valueOf(socksPort.toString());
        }
        Object method = server.get("method");
        if (method != null) {
          Config.PROXY_METHOD = method.toString();
        }
        Object password = server.get("password");
        if (password != null) {
          Config.PROXY_PASSWORD = password.toString();
        }
      }

      Map lannet = (Map) config.get("lannet");
      if (lannet != null) {

        Object lanStrategy = lannet.get("lanStrategy");
        if (lanStrategy != null) {
          Config.LAN_STRATEGY = LanStrategy.getLanStrategyByVal(Integer.valueOf(lanStrategy.toString()));
        }
        Object lanServerPort = lannet.get("lanPort");
        if (lanServerPort != null) {
          Config.LAN_SERVER_PORT = Integer.valueOf(lanServerPort.toString());
        }
        Object lanHostName = lannet.get("lanHostName");
        if (lanHostName != null) {
          Config.LAN_HOST_NAME = lanHostName.toString();
        }
      }
    }
    logger.debug("Proxy server config loaded：Port={}, method={}, password={}", Config.PROXY_PORT, Config.PROXY_METHOD, Config.PROXY_PASSWORD);
    logger.debug("Proxy lannet config loaded：lanStrategy={}, lanHostName={}, lanServerPort={}", Config.LAN_STRATEGY, Config.LAN_HOST_NAME, Config.LAN_SERVER_PORT);
  }

}
