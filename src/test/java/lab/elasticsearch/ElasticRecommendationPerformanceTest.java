package lab.elasticsearch;

import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.search.facet.TermsFacet;
import io.searchbox.indices.DeleteIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.facet.FacetBuilders;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.base.Stopwatch;

public class ElasticRecommendationPerformanceTest extends AbstractElasticSearchTest {
  static final String TYPE = "recomm";
  final int userCount = 500000;
  final int musicCount = 50000;

  @Test
  public void createUserAndMusic() throws Exception {
    // createMusic(musicCount);
    // createUser(userCount, musicCount);
    createMusicLog(userCount, musicCount);
  }

  @Test
  public void remove() throws Exception {
    client.execute(new DeleteIndex.Builder(INDEX).type("recommandation-musiclog").build());
  }

  @Test
  public void traversal() throws Exception {
    for (int i = 1; i < 100; i++) {
      Stopwatch watch = new Stopwatch();
      watch.start();

      SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
      searchSourceBuilder.size(0);
      searchSourceBuilder.query(QueryBuilders.inQuery("userId", randomId(100)));
      searchSourceBuilder.facet(FacetBuilders.termsFacet("plays").field("musicId"));
      Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX).addType(TYPE).build();

      JestResult result = client.execute(search);

      watch.stop();
      System.out.println("Recommentation:" + watch.elapsedMillis() + " " + result.getJsonString());

      if (i % 1000 == 0) {
        System.out.println(i + " " + result.getJsonString());
      }
    }
  }

  private String[] randomId(int count) {
    List<String> result = new ArrayList<String>(count);
    for (int i = 0; i < count; i++) {
      result.add(String.valueOf(new Random().nextInt(userCount)));
    }
    return result.toArray(new String[0]);
  }

  @Ignore
  @Test
  public void measurePerformance() throws Exception {
    Stopwatch watch = new Stopwatch();
    watch.start();

    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.size(1);
    searchSourceBuilder.query(QueryBuilders.fieldQuery("follows", "2000001"));
    searchSourceBuilder.facet(FacetBuilders.termsFacet("plays").script("doc['plays.musicId'].values"));
    Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX).addType(TYPE).build();
    System.out.println(searchSourceBuilder.toString());

    JestResult result = client.execute(search);
    System.out.println(result.getJsonString());
    for (TermsFacet.Term each : result.getFacets(TermsFacet.class).get(0).terms()) {
      System.out.println(each.getName() + " " + each.getCount());
    }

    watch.stop();
    System.out.println("Recommentation:" + watch.elapsedMillis());
  }

  private Builder builder() {
    return new Bulk.Builder().defaultIndex(INDEX).defaultType(TYPE);
  }


  private void createMusicLog(int userCount, int musicCount) throws Exception {
    long start = System.currentTimeMillis();
    Builder builder = builder();

    for (int i = 0; i < 10000000; i++) {
      if (i % 2000 == 0) {
        client.execute(builder.build());
        builder = builder();
        System.out.println("Music " + i + " " + ((System.currentTimeMillis() - start) / 1000));
      }
      MusicPlayLog log = new MusicPlayLog();
      log.setUserId(new Random().nextInt(userCount));
      log.setMusicId(new Random().nextInt(musicCount));
      builder.addAction(new Index.Builder(log).index(INDEX).type(TYPE).build());
    }

    client.execute(builder.build());
  }
}
