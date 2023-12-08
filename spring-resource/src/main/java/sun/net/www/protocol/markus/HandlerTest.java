package sun.net.www.protocol.markus;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/7
 * @Description:
 */
public class HandlerTest {
  public static void main(String[] args) throws IOException {
    // 协议://host:port/path/xxx   /// 说明是本机
    URL url = new URL("markus:///META-INF/default.properties");
    InputStream inputStream = url.openStream();
    System.out.println(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
  }
}
