package lab.titan;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tinkerpop.rexster.client.RexProException;
import com.tinkerpop.rexster.client.RexsterClient;
import com.tinkerpop.rexster.client.RexsterClientFactory;

public class TitanOperationTest {
  private final String ip = "10.101.30.96";
  private final String graphName = "graph";
  private RexsterClient client;

  @Before
  public void createClient() throws Exception {
    this.client = RexsterClientFactory.open(ip, graphName);
  }

  @Test
  public void canQueryByVectorProperty() throws RexProException, IOException {
    client.execute("g.addVertex([name:'min', age:'34'])");

    List<Map<String, String>> result = client.execute("g.V('name', 'min').map");
    assertThat(result.get(0).get("name"), is("min"));
    assertThat(result.get(0).get("age"), is("34"));
  }

  @Test
  public void canRemoveVertex() throws RexProException, IOException {
    client.execute("g.addVertex([name:'min', age:'34'])");
    client.execute("g.V('name', 'min').remove()");

    assertThat(client.execute("g.V('name', 'min').map").isEmpty(), is(true));
  }

  @After
  public void removeFixture() throws RexProException, IOException {
    client.execute("g.V('name', 'min').remove()");
  }
}
