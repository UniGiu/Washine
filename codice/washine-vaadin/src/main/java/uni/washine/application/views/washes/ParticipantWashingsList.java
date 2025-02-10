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
	/**
	 *  Fetches the available washings data
	 */
	@Override	
	protected void fetchData() {
		String userId=userData.getId();

		try{
					
			List<WashineLaundryWashingIf> washings = wCore.getCommunitiesWashings(userId);
			
			for (WashineLaundryWashingIf washing : washings) {		
				addItem(washing);
			}
		}catch(WashineCoreException e){
			UiNotifier.showErrorNotification(e.getMessage());
		}
   }
   /**
	* adds an item to the list
    */
   @Override
   protected void addItem(WashineLaundryWashingIf washing) {	 
	   ParticipantWashingListItem item = new ParticipantWashingListItem(washing);
	   add(item);
	}	
}
