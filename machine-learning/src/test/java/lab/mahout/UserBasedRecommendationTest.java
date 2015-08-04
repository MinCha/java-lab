package lab.mahout;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericRecommendedItem;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.junit.Test;

public class UserBasedRecommendationTest {
  @Test
  public void recommendItemBasedOnUser() throws Exception {
    File file = new File(UserBasedRecommendationTest.class.getResource("/purchase_history.txt").getPath());
    DataModel model = new FileDataModel(file);
    UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
    UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, similarity, model);
    Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

    List<RecommendedItem> result = recommender.recommend(1000, 10);

    assertThat(result.size(), is(3));
    assertThat(result, hasItem((RecommendedItem) new GenericRecommendedItem(3L, 1.0F)));
  }

  @Test
  public void recommendItemBasedOnUserWithSorting() throws Exception {
    File file = new File(UserBasedRecommendationTest.class.getResource("/purchase_history_sorting.txt").getPath());
    DataModel model = new FileDataModel(file);
    UserSimilarity similarity = new EuclideanDistanceSimilarity(model, Weighting.WEIGHTED);
    UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, similarity, model);
    Recommender recommender = new GenericBooleanPrefUserBasedRecommender(model, neighborhood, similarity);

    final long targetUserId = 1000;
    final int howMany = 3;
    List<RecommendedItem> result = recommender.recommend(targetUserId, howMany);

    System.out.println(result);
    assertThat(result.size(), is(3));
    assertThat(result.get(0), is((RecommendedItem) new GenericRecommendedItem(3L, 4.0F)));
    assertThat(result.get(1), is((RecommendedItem) new GenericRecommendedItem(6L, 3.0F)));
    assertThat(result.get(2), is((RecommendedItem) new GenericRecommendedItem(4L, 2.0F)));
  }
}
