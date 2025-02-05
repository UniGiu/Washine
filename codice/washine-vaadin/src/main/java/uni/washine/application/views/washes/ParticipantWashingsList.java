package uni.washine.application.views.washes;

import java.util.List;

import com.vaadin.flow.server.VaadinSession;

import uni.washine.application.utils.UiNotifier;
import uni.washine.application.utils.WashingsList;
import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.user.WashineUserIf;
import washine.washineCore.washing.WashineLaundryWashingIf;


public class ParticipantWashingsList extends WashingsList{
	   private WashineUserIf userData;
	  
	   ParticipantWashingsList(){
		   super();
		  
		userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
	}
	@Override
	protected void fetchData() {
		String userId=userData.getId();

		try{
			//List<String> participatedWashings=wCore.getParticipatedWashingIds(userId);			
			List<WashineLaundryWashingIf> washings = wCore.getCommunitiesWashings(userId);
			
			for (WashineLaundryWashingIf washing : washings) {				
				//boolean particpates=participatedWashings.contains(washing.getId());
				//addItem(washing,particpates);
				addItem(washing);
			}
		}catch(WashineCoreException e){
			UiNotifier.showErrorNotification(e.getMessage());
		}
   }
   @Override
   protected void addItem(WashineLaundryWashingIf washing) {
   // this.addItem(washing,false);
	 
	   ParticipantWashingListItem item = new ParticipantWashingListItem(washing);
	   add(item);
	}
	/*protected void addItem(WashineLaundryWashingIf washing, boolean participates) {
		ParticipantWashingListItem item = new ParticipantWashingListItem(washing,participates);
		 
		add(item);
		}
		*/
}
