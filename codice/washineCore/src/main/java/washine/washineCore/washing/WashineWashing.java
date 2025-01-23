package washine.washineCore.washing;

import java.util.ArrayList;
import java.util.List;

public class WashineWashing implements WashineLaundryWashingIf {

  private String id;
  private List<String> enabledParticipants;
  private boolean active;
  private WashineWashingOptions washingOptions;
  private List<String> participantIds;

  public WashineWashing(
      String id, List<String> enabledParticipants, WashineWashingOptions washingOptions) {
    this.id = id;
    this.enabledParticipants = enabledParticipants;
    this.washingOptions = washingOptions;
    this.active = true; // default attivo
    this.participantIds = new ArrayList<>();
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
  public WashineLaundryWashingOptionsIf getWashingOptions() {
    return washingOptions;
  }

  @Override
  public WashineLaundryWashingOptionsLaunderIf getWashingOptionsLaunder() {
    return washingOptions;
  }

  public void addParticipant(String partiticipantId) {
    this.participantIds.add(partiticipantId);
  }

  public List<String> getParticipantIds() {
    return participantIds;
  }
}
