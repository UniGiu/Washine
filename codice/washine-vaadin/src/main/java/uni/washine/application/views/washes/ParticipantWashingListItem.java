package uni.washine.application.views.washes;

import java.util.List;

import com.vaadin.flow.server.VaadinSession;

import uni.washine.application.utils.UiNotifier;
import uni.washine.application.utils.WashingListItem;
import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreCommunityIf;
import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.user.WashineUserIf;
import washine.washineCore.washing.WashineLaundryWashingIf;

public class ParticipantWashingListItem extends WashingListItem {
	
	public ParticipantWashingListItem(WashineLaundryWashingIf washing) {	
		super(washing);
		WashineUserIf userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
		String washingId = washing.getId();		
		String userId=userData.getId();
		
		List<String> participatedWashings=washing.getParticipantIds();		
		
		boolean participates= participatedWashings.contains(userId);		
		ParticipantWeightDisplay weightDisplay=new ParticipantWeightDisplay();
		
		weightDisplay.refresh(washingId,userId);
		weightDisplay.setVisible(participates);
		
		add(weightDisplay);
				

		ParticipantWashngItemUi layoutUi=new ParticipantWashngItemUi(washingId);	
		layoutUi.setEmpty(participates);
		layoutUi.setWidthFull();
		add (layoutUi,weightDisplay);
		
		WashineCoreCommunityIf cCore = AbstractCoreFactory.getInstance("vaadin").createCoreWashineCommunity();
		try{
			h2Title.setText(cCore.getWashingCommunityName(washingId, userId));
		}catch (WashineCoreException e){
			UiNotifier.showErrorNotification(e.getMessage());
		}
		
		//change backgrount to mark participation
		if(participates){
			getStyle().set("background-image", "linear-gradient(#0000 , #EEFD)");
		}
	}
	
}
