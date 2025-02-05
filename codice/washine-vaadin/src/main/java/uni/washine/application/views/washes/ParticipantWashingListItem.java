package uni.washine.application.views.washes;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import uni.washine.application.utils.UiNotifier;
import uni.washine.application.utils.WashingListItem;
import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreWashingIf;
import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.washing.WashineLaundryWashingIf;

public class ParticipantWashingListItem extends WashingListItem {
	private String washingId;
	private ParticipantWeightDisplay weightDisplay;
	public ParticipantWashingListItem(WashineLaundryWashingIf washing, boolean participates) {
		super(washing);
		washingId = washing.getId();
		ParticipantWeightDisplay weightDisplay=new ParticipantWeightDisplay();
		weightDisplay.setVisible(false);
		PartecipantWashngItemUi layoutUi=new PartecipantWashngItemUi(washingId);	
		layoutUi.setEmpty(participates);
		layoutUi.setWidthFull();

		add (layoutUi);
	}
	
}
