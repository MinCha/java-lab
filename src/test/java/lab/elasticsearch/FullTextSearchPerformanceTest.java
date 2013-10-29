package lab.elasticsearch;

import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.indices.DeleteIndex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import com.google.common.base.Stopwatch;

public class FullTextSearchPerformanceTest extends AbstractElasticSearchTest {
  static final String TYPE = "fulltext";

  @Test
  public void createMusic() throws Exception {
    createMusic(1000000);
  }

  @Test
  public void removeMusic() throws Exception {
    client.execute(new DeleteIndex.Builder(INDEX).type(TYPE).build());
  }

  @Test
  public void measurePerformance() throws Exception {
    Stopwatch watch = new Stopwatch();
    watch.start();
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.queryString("id:*NOT_EXISTING*"));
    Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(INDEX).addType(TYPE).build();
    System.out.println(client.execute(search).getJsonString());
    watch.stop();
    System.out.println("FullTextSearch:" + watch.elapsedMillis());
  }

  private void createMusic(int count) throws Exception {
    long start = System.currentTimeMillis();
    List<String> word = Arrays.asList("とましま", "いにすなす", "しとまのし", "ててらにらにい", "シノイラス", "イラハノ", "タテイスカ", "チトシハキ", "ツサソヒコ");
    Builder builder = builder();
    for (int i = 0; i < count; i++) {
      if (i % 2000 == 0) {
        client.execute(builder.build());
        builder = builder();
        System.out.println("Music " + i + " " + ((System.currentTimeMillis() - start) / 1000));
      }
      Collections.shuffle(word);
      builder.addAction(new Index.Builder(Music.of(i, word.get(0) + " " + word.get(1) + i)).index(INDEX).type(TYPE)
          .build());
    }
    client.execute(builder.build());
  }

  private Builder builder() {
    return new Bulk.Builder().defaultIndex(INDEX).defaultType(TYPE);
  }
}
