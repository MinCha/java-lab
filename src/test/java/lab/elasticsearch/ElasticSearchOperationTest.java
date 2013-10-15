package lab.elasticsearch;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ElasticSearchOperationTest {
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
  public void addDocument() throws Exception {
    User user = create("minslovey", "MinCha");

    JestResult result = client.execute(new Index.Builder(user).index(INDEX).type(TYPE).build());

    assertThat((Boolean) result.getValue("ok"), is(true));
  }

  @Test
  public void getDocument() throws Exception {
    User user = create("minslovey", "MinCha");
    client.execute(new Index.Builder(user).index(INDEX).type(TYPE).build());

    JestResult result = client.execute(new Get.Builder("minslovey").index(INDEX).type(TYPE).build());

    User resultUser = result.getSourceAsObject(User.class);
    assertThat(user.getName(), is(resultUser.getName()));
  }

  @Test
  public void searchDocument() throws Exception {
    client.execute(new Index.Builder(create("minslovey", "MinCha")).index(INDEX).type(TYPE).build());
    client.execute(new Index.Builder(create("min", "cha")).index(INDEX).type(TYPE).build());
    client.execute(new Index.Builder(create("piona", "P")).index(INDEX).type(TYPE).build());

    final long dueToSearchLatency = 1000;
    Thread.sleep(dueToSearchLatency);

    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.queryString("id:m*"));
    Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX).addType(TYPE).build();
    JestResult result = client.execute(search);

    List<User> users = result.getSourceAsObjectList(User.class);
    assertThat(users.size(), is(2));
    assertThat(users, hasItem(create("minslovey", "MinCha")));
    assertThat(users, hasItem(create("min", "cha")));
  }

  @After
  public void after() throws Exception {
    client.execute(new DeleteIndex.Builder(INDEX).build());
  }

  private User create(String id, String name) {
    User result = new User();
    result.setId(id);
    result.setName(name);
    return result;
  }

  private JestClient client() {
    JestClientFactory factory = new JestClientFactory();
    factory.setClientConfig(config);
    return factory.getObject();
  }
}
