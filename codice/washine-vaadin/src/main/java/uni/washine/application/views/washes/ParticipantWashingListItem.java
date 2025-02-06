package uni.washine.application.views.washes;

import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.VaadinSession;

import uni.washine.application.utils.UiNotifier;
import uni.washine.application.utils.WashingListItem;
import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreWashingIf;
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
		weightDisplay.refresh(userId,washingId);
		weightDisplay.setVisible(false);
		
		ParticipantWashngItemUi layoutUi=new ParticipantWashngItemUi(washingId);	
		layoutUi.setEmpty(participates);
		layoutUi.setWidthFull();
		add (layoutUi);
	}
	
}
