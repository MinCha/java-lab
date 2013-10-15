package lab.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Stopwatch;

public class ElasticFullTextSearchPerformanceTest {
  private static final String INDEX = "test-index";
  private static final String TYPE = "type";
  private final String ip = "10.101.30.96:9200";
  private ClientConfig config = new ClientConfig.Builder("http://" + ip).multiThreaded(true).build();
  private JestClient client;

  @Before
  public void before() throws Exception {
    this.client = client();
    client.execute(new CreateIndex.Builder(INDEX).build());
  }

  @Test
  public void measurePerformance() throws Exception {
    setupMusic(1000000);

    Stopwatch watch = new Stopwatch();
    watch.start();
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.queryString("id:*NOT_EXISTING*"));
    Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX).addType(TYPE).build();
    System.out.println(client.execute(search).getJsonString());
    watch.stop();
    System.out.println("FullTextSearch:" + watch.elapsedMillis());
  }

  private void setupMusic(int count) throws Exception {
    long start = System.currentTimeMillis();
    List<String> word = Arrays.asList("하늘", "바람", "진실", "우정", "변화", "창공", "작곡", "예술", "인간의 삶");
    Builder builder = builder();
    for (int i = 0; i < count; i++) {
      if (i % 2000 == 0) {
        client.execute(builder.build());
        builder = builder();
        System.out.println("Music " + i + " " + ((System.currentTimeMillis() - start) / 1000));
      }
      Collections.shuffle(word);
      builder.addAction(new Index.Builder(Music.of(word.get(0) + " " + word.get(1) + i)).index(INDEX).type(TYPE)
          .build());
    }
    client.execute(builder.build());
  }

  @After
  public void after() throws Exception {
    client.execute(new DeleteIndex.Builder(INDEX).build());
  }

  private Builder builder() {
    return new Bulk.Builder().defaultIndex(INDEX).defaultType(TYPE);
  }

  private JestClient client() {
    JestClientFactory factory = new JestClientFactory();
    factory.setClientConfig(config);
    return factory.getObject();
  }
}
