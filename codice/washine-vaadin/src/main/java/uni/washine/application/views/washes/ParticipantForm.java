package uni.washine.application.views.washes;


import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreWashingIf;
import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.user.WashineUserIf;
import washine.washineCore.washing.WashineLaundryWashingIf;
import uni.washine.application.utils.UiNotifier;


public class ParticipantForm  extends VerticalLayout{
	final WashineCoreWashingIf wCore;	
	private WashineLaundryWashingIf washingInfo;
	private Button submitButton;
	private NumberField participantLoadField;
	private String userId;
	private double participantLoad;
	private boolean edit;
	private String washingId;
	
	public ParticipantForm(String userId) {
		wCore = AbstractCoreFactory.getInstance("vaadin").createCoreWashing();
		if(userId==null) {
			WashineUserIf userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");			
			this.userId=userData.getId();
		}else {
			this.userId=userId;
		}
		FormLayout formLayout = new FormLayout();
		
		Button cancelButton;
		
		participantLoadField = new NumberField("Your colthing weight (kilograms)");
		participantLoadField.setRequired(true);
		participantLoadField.setMin(0.1);

		submitButton = new Button();
		submitButton.setWidth("min-content");
		submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		submitButton.addClickListener(event -> handleSubmit());
		
		cancelButton = new Button();
		cancelButton.setWidth("min-content");
		cancelButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		cancelButton.setText("Cancel");
		cancelButton.addClickListener(event -> fireCustomEvent("participantjoincancelled"));

		formLayout.add(participantLoadField);		
		add(formLayout);		
		add(submitButton);
		add(cancelButton);
		
	}

	/**
	 * Initializes the form an existing weight
	 * 
	 * @param washing the washing to be edited
	 */
	public void init(String washingId, boolean edit) {
		this.edit=edit;
		this.washingId=washingId;
		try{
			washingInfo = wCore.getWashing(washingId);
			//TODO add the washing list item with washing information
		}catch(WashineCoreException e){
			UiNotifier.showErrorNotification(e.getMessage());	
		}
		updateSubmitButtonLabel(this.edit);
	}


	/**
	 * Clears the form fields
	 * 
	 */
	public void reset() {
		washingInfo = null;
		participantLoad=0;
		participantLoadField.setValue(0.0);
		
		resetValidations();
	}

	/**
	 * Updates the submit button label depending on the edit or update context
	 */
	private void updateSubmitButtonLabel(boolean edit) {
		
		if (edit) {
			submitButton.setText("Update washing load");			
		} else {
			submitButton.setText("Join washing");
		}
	}
	private void resetValidations(){
		// reset validity states
		participantLoadField.setInvalid(false);
	}
	/**
	 * Provides validation state of the form and updates the fields to their
	 * validity state
	 * 
	 * @return true if all fields are valid, otherwise false
	 */
	private boolean validateForm() {
		boolean valid = true;
		resetValidations();
		if (participantLoadField.getValue() == null) {
			participantLoadField.setInvalid(true);
			participantLoadField.setErrorMessage("A load weight is required");
			valid = false;
		}
		if (!valid) {
			UiNotifier.showErrorNotification("Please check the form for errors");
		}
		return valid;
	}

	/**
	 * Handles the submit button click
	 */
	private void handleSubmit() {
		if (!validateForm()) {
			return;
		}
		participantLoad=participantLoadField.getValue();
		if (edit) {
			submitWashingUpdate();
		} else {
			joinWashing();
		}
	}

	/**
	 * Submits a new washing and if all is OK it starts closing the form
	 */
	private void joinWashing() {
		
		boolean result = false;
		try {				
			result = wCore.participateToWashing(washingId, userId, participantLoad)!=null;
		} catch (WashineCoreException e) {
			UiNotifier.showErrorNotification(e.getMessage());
		}
		if (result) {			
			UiNotifier.showSuccessNotification("New washing ready");
			fireCustomEvent("participantjoincreated");
		}
	}

	/**
	 * Submits a washing update and if all is OK it starts closing the form
	 * 
	 */
	private void submitWashingUpdate() {
		
		boolean result = false;
		//TODO to be implemented
		/*try {	
			
			result=wCore.updateWashingLoad(washingId, userId, participantLoad);
		} catch (WashineCoreException e) {
			UiNotifier.showErrorNotification(e.getMessage());
		}*/
		if (result) {			
			UiNotifier.showSuccessNotification("Your washing has been updated");
			fireCustomEvent("participantjoinupdated");
		}
	}

		
	/**
	 * Dispatch a javascript custom event bearing the washing id
	 * @param eventType  name of the event type
	 */
	
	private void fireCustomEvent(String eventType) {
		getElement().executeJs("this.dispatchEvent(new CustomEvent($0, { detail: { washingId:$1 }, bubbles: true, composed: true }));", eventType, washingInfo.getId());
	}
}
