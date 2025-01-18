package washine.washineCore;

public class LoadWeightCalculator {
  // reference: https://shippingstorm.com/en/list-of-weight/
  public static final float averageTShirtWeight = 200;
  public static final float averageJeansWeight = 700;
  public static final float averagePantsWeight = 80;
  public static final float averageShirtWeight = 150;
  public static final float averageShortsWeight = 200;

  public LoadWeightCalculator() {}
  ;

  /**
   * @param numberOfTShirts
   * @param numberOfJeans
   * @param numberOfPants
   * @param numberOfShirts
   * @param numberOfShorts
   * @return the weight of the load in kilograms
   */
  public float calculateLoadWeight(
      float numberOfTShirts,
      float numberOfJeans,
      float numberOfPants,
      float numberOfShirts,
      float numberOfShorts) {
    float totalLoad = 0;

    totalLoad += averageTShirtWeight * numberOfTShirts;
    totalLoad += averageJeansWeight * numberOfJeans;
    totalLoad += averagePantsWeight * numberOfPants;
    totalLoad += averageShirtWeight * numberOfShirts;
    totalLoad += averageShortsWeight * numberOfShorts;

    return totalLoad / 1000;
  }
}
