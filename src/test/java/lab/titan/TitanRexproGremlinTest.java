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

public class TitanRexproGremlinTest {
  private final String ip = "10.101.30.96";
  private final String graphName = "graph";
  private RexsterClient client;

  @Before
  public void createClient() throws Exception {
    this.client = RexsterClientFactory.open(ip, graphName);
    // client
    // .execute("g.makeType().name('name').dataType(String.class).indexed(Vertex.class).unique(Direction.BOTH).makePropertyKey()");
  }

  @Test
  public void canAddVector() throws RexProException, IOException, InterruptedException {
    client.execute("g.addVertex([name:'min', age:'34'])");

    List<Map<String, String>> result = client.execute("g.V('name', 'min').map");
    assertThat(result.get(0).get("name"), is("min"));
    assertThat(result.get(0).get("age"), is("34"));
  }

  @Test
  public void canAddEdge() throws RexProException, IOException {
    client.execute("g.addVertex([type:'user', name:'min', age:'34'])");
    client.execute("g.addVertex([type:'user', name:'cha', age:'31'])");
    client.execute("g.addVertex([type:'user', name:'jung', age:'29'])");

    client.execute("g.V('name', 'min').next().addEdge('likes', g.V('name', 'cha').next())");
    client.execute("g.V('name', 'min').next().addEdge('likes', g.V('name', 'jung').next())");

    List<Map<String, String>> result = client.execute("g.V('name', 'min').out('likes').map");
    assertThat(result.size(), is(2));
    assertThat(result.get(0).get("name"), is("cha"));
    assertThat(result.get(1).get("name"), is("jung"));
  }

  @Test
  public void canAdd2DepthVertex() throws RexProException, IOException {
    client.execute("g.addVertex([type:'user', name:'min', age:'34'])");
    client.execute("g.addVertex([type:'user', name:'cha', age:'31'])");
    client.execute("g.addVertex([type:'user', name:'jung', age:'29'])");
    client.execute("g.addVertex([type:'music', name:'a'])");
    client.execute("g.addVertex([type:'music', name:'b'])");
    client.execute("g.addVertex([type:'music', name:'c'])");
    client.execute("g.addVertex([type:'music', name:'d'])");

    client.execute("g.V('name', 'min').next().addEdge('likes', g.V('name', 'cha').next())");
    client.execute("g.V('name', 'min').next().addEdge('likes', g.V('name', 'jung').next())");

    client.execute("g.V('name', 'cha').next().addEdge('plays', g.V('name', 'a').next())");
    client.execute("g.V('name', 'cha').next().addEdge('plays', g.V('name', 'b').next())");
    client.execute("g.V('name', 'cha').next().addEdge('plays', g.V('name', 'c').next())");
    client.execute("g.V('name', 'cha').next().addEdge('plays', g.V('name', 'c').next())");
    client.execute("g.V('name', 'cha').next().addEdge('plays', g.V('name', 'd').next())");

    client.execute("g.V('name', 'jung').next().addEdge('plays', g.V('name', 'c').next())");
    client.execute("g.V('name', 'jung').next().addEdge('plays', g.V('name', 'd').next())");

    List<String> result =
        client
            .execute("g.V('name', 'min').out('likes').out('plays').property('name').groupCount().cap.orderMap(T.decr)");
    assertThat(result.size(), is(4));
    assertThat(result.get(0), is("c"));
    assertThat(result.get(1), is("d"));
  }

  @Test
  public void canRemoveVertex() throws RexProException, IOException {
    client.execute("g.addVertex([name:'min', age:'34'])");
    client.execute("g.V('name', 'min').remove()");

    assertThat(client.execute("g.V('name', 'min').map").isEmpty(), is(true));
  }

  @After
  public void removeFixture() throws RexProException, IOException {
    client.execute("g.V.each{g.removeVertex(it)};g.V.each{g.removeEdge(it)};g.commit();");
  }
}
