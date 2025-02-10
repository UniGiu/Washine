package uni.washine.application.views.washes;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ParticipantWashngItemUi extends HorizontalLayout {
	private Button editButton;
	private Button joinButton;	
	private Button retireButton;
	private String washingId;
	
	ParticipantWashngItemUi(String washingId){
		this.washingId=washingId;
		editButton = new Button();
		joinButton = new Button();	
		retireButton = new Button();
		
		
		joinButton.setWidth("min-content");
		joinButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		joinButton.setText("Join");
		joinButton.addClickListener(event -> {			
			fireCustomEvent("washingjoin");
		});

		retireButton.setWidth("min-content");
		retireButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		retireButton.setText("Retire");
		retireButton.addClickListener(event -> {					
			askConfirmationBeforeUpdate();
		});

		
		editButton.setWidth("min-content");
		editButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		editButton.setText("Edit");
		editButton.addClickListener(event -> {			
			fireCustomEvent("washingeditparticipation");
		});
		
		add(joinButton, editButton, retireButton);
	 }
	/**
	 * Opens a confirmation dialog in order to proceed to delete the
	 * washing
	 */
	private void askConfirmationBeforeUpdate() {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Retire from washing?");
		dialog.setText("Are you sure you want to retire from this washing?");

		dialog.setCancelable(true);		

		dialog.setConfirmText("Retire");
		dialog.setConfirmButtonTheme("error primary");
		dialog.addConfirmListener(event -> retireFromWashing());
		dialog.open();		
	}
	/**
	 * Dispatch the user will tor retire
	 */
	private void retireFromWashing(){		
		fireCustomEvent("retirefromwashing");
	}
	public void setEmpty(boolean value) {		
			joinButton.setVisible(!value);
			editButton.setVisible(value);
			retireButton.setVisible(value);	
	}
	
	/**
	 * Dispatch a javascript custom event bearing the washing id
	 * @param eventType  name of the event type
	 */
	
	private void fireCustomEvent(String eventType) {
		getElement().executeJs("this.dispatchEvent(new CustomEvent($0, { detail: { washingId: '" + washingId + "' }, bubbles: true, composed: true }));", eventType);
	}
}
