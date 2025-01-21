package washine.washineCore.washing;
import java.util.List;

public class WashineWashing implements WashineLaundryWashingIf {
	
	private String id;
	private List<String> enabledParticipants;
	private boolean active;
    private int currentLoad;
    private LaundryWashingOptions washingOptions;

    public WashineWashing(String id, List<String> enabledParticipants, int currentLoad, LaundryWashingOptions washingOptions) {
        this.id = id;
        this.enabledParticipants = enabledParticipants;
        this.currentLoad = currentLoad;
        this.washingOptions = washingOptions;
        this.active = false;  // default non attivo
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
    public int getCurrentLoad() {
        return currentLoad;
    }

    @Override
    public LaundryWashingOptions getWashingOptions() {
        return washingOptions;
    }

}
