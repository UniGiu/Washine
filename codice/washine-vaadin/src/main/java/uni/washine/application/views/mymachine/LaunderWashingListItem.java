package uni.washine.application.views.mymachine;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.shared.Registration;

import uni.washine.application.utils.WashingListItem;
import washine.washineCore.washing.WashineLaundryWashingIf;

public class LaunderWashingListItem extends WashingListItem {

	public LaunderWashingListItem(WashineLaundryWashingIf washing) {
		super(washing);
		
		HorizontalLayout layoutUi=new HorizontalLayout();
	
		Button editButton = new Button();
		Button deleteButton = new Button();

		layoutUi.setWidthFull();

		layoutUi.add(editButton, deleteButton);
		
		
		deleteButton.setWidth("min-content");
		deleteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		deleteButton.setText("Delete");
		deleteButton.addClickListener(event -> fireEvent(new DeleteEvent(this)));

		
		editButton.setWidth("min-content");
		editButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		editButton.setText("Edit");
		editButton.addClickListener(event -> fireEvent(new EditEvent(this)));
		
		add (layoutUi);
	}
/**
	 * Class for custom events for the container
	 */
	public static abstract class LaunderWashingListItemEvent extends ComponentEvent<LaunderWashingListItem> {
		protected LaunderWashingListItemEvent(LaunderWashingListItem source) {
			super(source, false);
		}
	}

	public static class DeleteEvent extends LaunderWashingListItemEvent {
		public DeleteEvent(LaunderWashingListItem source) {
			super(source);
		}
	}

	public static class EditEvent extends LaunderWashingListItemEvent {
		public EditEvent(LaunderWashingListItem source) {
			super(source);
		}
	}

	// Add registration methods for the events
	public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
		return addListener(DeleteEvent.class, listener);
	}
	public Registration addEditListener(ComponentEventListener<EditEvent> listener) {
		return addListener(EditEvent.class, listener);
	}
}
