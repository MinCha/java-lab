package lab.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.indices.CreateIndex;

import org.junit.Before;

public abstract class AbstractElasticSearchTest {
  static final String INDEX = "music";
  final String ip = "10.99.197.42:9200";
  ClientConfig config = new ClientConfig.Builder("http://" + ip).multiThreaded(true).build();
  JestClient client;

  @Before
  public void before() throws Exception {
    client = client();
    client.execute(new CreateIndex.Builder(INDEX).build());
  }

  JestClient client() {
    JestClientFactory factory = new JestClientFactory();
    factory.setClientConfig(config);
    return factory.getObject();
  }

}
