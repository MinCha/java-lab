package lab.titan;

import java.io.IOException;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.rexster.client.RexProException;

public class TitanBlueprintTest {
  private final String ip = "10.101.30.96";
  @SuppressWarnings("unused")
  private final String graphName = "graph";
  @SuppressWarnings("unused")
  private TitanGraph client;

  @Before
  public void createClient() throws Exception {
    Configuration conf = new BaseConfiguration();
    conf.setProperty("storage.backend", "embeddedcassandra");
    conf.setProperty("storage.hostname", ip);
    conf.setProperty("storage.cassadra-config-dir", "cassandra.yaml");
    client = TitanFactory.open(conf);
  }

  @Test
  public void canQueryByVectorProperty() throws RexProException, IOException {}
}
