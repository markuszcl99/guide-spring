package sun.net.www.protocol.markus;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: zhangchenglong06
 * @Date: 2023/12/7
 * @Description:
 */
public class MarkusURLConnection extends URLConnection {

  private ClassPathResource resource;

  /**
   * Constructs a URL connection to the specified URL. A connection to
   * the object referenced by the URL is not created.
   *
   * @param url the specified URL.
   */
  protected MarkusURLConnection(URL url) {
    super(url);
    this.resource = new ClassPathResource(url.getPath());
  }

  @Override
  public void connect() throws IOException {

  }

  @Override
  public InputStream getInputStream() throws IOException {
    return resource.getInputStream();
  }
}
