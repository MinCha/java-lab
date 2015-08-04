package lab.mahout;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericRecommendedItem;
import org.apache.mahout.cf.taste.impl.similarity.GenericItemSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.GenericItemSimilarity.ItemItemSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.junit.Test;

public class ItemBasedRecommendationTest {
  @Test
  public void recommendItemBasedOnUser() throws Exception {
    File file = new File(ItemBasedRecommendationTest.class.getResource("/purchase_history.txt").getPath());
    DataModel model = new FileDataModel(file);
    List<GenericItemSimilarity.ItemItemSimilarity> correlations =
        new ArrayList<GenericItemSimilarity.ItemItemSimilarity>();
    correlations.add(new ItemItemSimilarity(1, 3, 1.0D));
    correlations.add(new ItemItemSimilarity(2, 3, 1.0D));
    correlations.add(new ItemItemSimilarity(1, 5, 0.8D));
    correlations.add(new ItemItemSimilarity(2, 5, 0.8D));
    ItemSimilarity itemSimilarity = new GenericItemSimilarity(correlations);
    Recommender recommender = new GenericItemBasedRecommender(model, itemSimilarity);

    int targetUserId = 1000;
    int howMany = 5;
    List<RecommendedItem> result = recommender.recommend(targetUserId, howMany);

    assertThat(result.size(), is(2));
    assertThat(result.get(0), is((RecommendedItem) new GenericRecommendedItem(3L, 1.0F)));
    assertThat(result.get(1), is((RecommendedItem) new GenericRecommendedItem(5L, 1.0F)));
  }
}
