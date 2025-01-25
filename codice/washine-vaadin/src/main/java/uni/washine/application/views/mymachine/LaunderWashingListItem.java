package uni.washine.application.views.mymachine;

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

public class LaunderWashingListItem extends WashingListItem {
	private String washingId;

	public LaunderWashingListItem(WashineLaundryWashingIf washing) {
		super(washing);
		washingId = washing.getId();
		
		HorizontalLayout layoutUi=new HorizontalLayout();
	
		Button editButton = new Button();
		Button deleteButton = new Button();

		layoutUi.setWidthFull();

		layoutUi.add(editButton, deleteButton);
		
		
		deleteButton.setWidth("min-content");
		deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		deleteButton.setText("Delete");
		deleteButton.addClickListener(event -> {					
			askConfirmationBeforeUpdate();
		});

		
		editButton.setWidth("min-content");
		editButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		editButton.setText("Edit");
		editButton.addClickListener(event -> {			
			fireCustomEvent("washingedit");
		});
		
		add (layoutUi);
	}

	/**
	 * Opens a confirmation dialog in order to proceed to delete the
	 * washing
	 */
	private void askConfirmationBeforeUpdate() {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Delete this washing?");
		dialog.setText("Are you sure you want to permanently delete this washing?");

		dialog.setCancelable(true);		

		dialog.setConfirmText("Delete");
		dialog.setConfirmButtonTheme("error primary");
		dialog.addConfirmListener(event -> deleteWashing());
		dialog.open();		
	}
	/**
	 * Deletes the list item's washing
	 */
	private void deleteWashing(){		
		WashineCoreWashingIf wCore = AbstractCoreFactory.getInstance("vaadin").createCoreWashing();
		try{
			wCore.deleteWashing(washingId);
		}catch(WashineCoreException e){
			UiNotifier.showErrorNotification(e.getMessage());
			return;
		}
		UiNotifier.showSuccessNotification("Wshing deleted");
		fireCustomEvent("washingdeleted");
	}
	/**
	 * Dispatch a javascript custom event bearing the washing id
	 * @param eventType  name of the event type
	 */
	private void fireCustomEvent(String eventType) {
		getElement().executeJs("this.dispatchEvent(new CustomEvent($0, { detail: { washingId: '" + washingId + "' }, bubbles: true, composed: true }));", eventType);
	}
}
