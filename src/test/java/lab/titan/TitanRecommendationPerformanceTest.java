package lab.titan;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tinkerpop.rexster.client.RexProException;
import com.tinkerpop.rexster.client.RexsterClient;
import com.tinkerpop.rexster.client.RexsterClientFactory;

public class TitanRecommendationPerformanceTest {
  private final String ip = "10.101.30.96";
  private final String graphName = "graph";
  private RexsterClient client;

  @Before
  public void createClient() throws Exception {
    this.client = RexsterClientFactory.open(ip, graphName);
  }

  @Test
  public void canAddVector() throws Exception {
    createSchema();
    createUserVertex();
    // createMusicVertex();
    // createFollowEdge();
    // createPlaysEdge();
  }

  private void createSchema() throws Exception {
    client
        .execute("g.makeType().name('userid').dataType(String.class).indexed(Vertex.class).unique(Direction.BOTH).makePropertyKey()");
    client
        .execute("g.makeType().name('musicid').dataType(String.class).indexed(Vertex.class).unique(Direction.BOTH).makePropertyKey()");
  }

  @SuppressWarnings("unused")
  private void createPlaysEdge() {}

  @SuppressWarnings("unused")
  private void createFollowEdge() {}

  @SuppressWarnings("unused")
  private void createMusicVertex() throws Exception {
    for (int id = 0; id < 5000000; id++) {
      if (id % 1000 == 0) {
        System.out.println("User:" + id);
      }
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("musicid", id);
      client.execute("g.addVertex([type:'music', musicid:musicid])", params);
    }
  }

  private void createUserVertex() throws Exception {
    long time = System.currentTimeMillis();
    StringBuffer buf = new StringBuffer(10000);
    for (int id = 0; id < 5000000; id++) {
      if (id % 1000 == 0) {
        System.out.println("Start:" + id + " " + ((System.currentTimeMillis() - time) / 1000));
        client.execute(buf.toString());
        buf.delete(0, buf.length());
        System.out.println("End  :" + id + " " + ((System.currentTimeMillis() - time) / 1000));
      }
      buf.append("g.addVertex([type:'user', userid:'" + id + "', age:'34']);");
    }
  }

  @After
  public void removeFixture() throws RexProException, IOException {
    client.execute("g.V.each{g.removeVertex(it)};g.V.each{g.removeEdge(it)};g.commit();");
  }
}
