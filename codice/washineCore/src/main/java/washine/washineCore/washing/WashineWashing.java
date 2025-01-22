package washine.washineCore.washing;

import java.util.List;

public class WashineWashing implements WashineLaundryWashingIf {

  private String id;
  private List<String> enabledParticipants;
  private boolean active;
  private WashineWashingOptions washingOptions;

  public WashineWashing(
      String id, List<String> enabledParticipants, WashineWashingOptions washingOptions) {
    this.id = id;
    this.enabledParticipants = enabledParticipants;
    this.washingOptions = washingOptions;
    this.active = false; // default non attivo
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public List<String> getEnabledParticipants() {
    return enabledParticipants;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  @Override
  public WashineWashingOptions getWashingOptions() {
    return washingOptions;
  }
}
