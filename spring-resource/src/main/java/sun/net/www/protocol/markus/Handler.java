package sun.net.www.protocol.markus;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/7
 * @Description:
 */
public class Handler extends URLStreamHandler {
  @Override
  protected URLConnection openConnection(URL u) throws IOException {
    return new MarkusURLConnection(u);
  }
}
