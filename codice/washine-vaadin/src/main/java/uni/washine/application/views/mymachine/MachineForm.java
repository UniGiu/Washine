package uni.washine.application.views.mymachine;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;

import uni.washine.application.utils.UiNotifier;
import uni.washine.application.utils.WashineTimeUtils;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

import java.time.Duration;

import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreWashingIf;
import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.user.WashineUserIf;
import washine.washineCore.washing.WashineLaundryWashingIf;
import washine.washineCore.washing.WashineLaundryWashingOptionsLaunderIf;

import com.vaadin.flow.component.ComponentEventListener;


public class MachineForm extends VerticalLayout {
	final WashineCoreWashingIf wCore;
	private WashineLaundryWashingIf washingInfo;
	private Button submitButton;

	// Form controls as class fields
	private DateTimePicker dateTimeWashingPicker;
	private NumberField durationField;
	private NumberField initialLoadField;
	private NumberField maxLoadField;
	private NumberField visibilityTimeField;
	private RadioButtonGroup<String> temperatureGroup;
	private RadioButtonGroup<String> spinSpeedGroup;
	private RadioButtonGroup<String> fabricTypeGroup;
	private RadioButtonGroup<String> colorGroup;
	private RadioButtonGroup<String> detergentTypeGroup;
	private RadioButtonGroup<String> dryingTypeGroup;
	private Checkbox underwearCheckbox;
	private Checkbox ironingCheckbox;
	private TextField pickupAddressField;
	private TextField deliveryAddressField;
	private TextField pickupAvailabilityField;
	private TextField deliveryAvailabilityField;
	private TextField refundTypeField;
	private NumberField participantMaxLoadField;
	private DateTimePicker accessOpenDatePicker;
	private DateTimePicker accessCloseDatePicker;

	public MachineForm() {
		wCore = AbstractCoreFactory.getInstance("vaadin").createCoreWashing();
		FormLayout formLayout = new FormLayout();
		FormLayout formLayoutNotRequired = new FormLayout();
		Details optionalInputs = new Details();
		optionalInputs.setWidth("100%");
		optionalInputs.setSummaryText("Non required options");
		Button cancelButton;
		// Required

		// Time
		dateTimeWashingPicker = new DateTimePicker("Washing date and time");
		dateTimeWashingPicker.setStep(Duration.ofMinutes(15));
		dateTimeWashingPicker.setHelperText("Format: DD/MM/YYYY and HH:MM");
		dateTimeWashingPicker.setRequiredIndicatorVisible(true);
		dateTimeWashingPicker.setMin(LocalDateTime.now());

		durationField = new NumberField("Wash duration (minutes)");
		durationField.setRequired(true);
		durationField.setMin(1);

		initialLoadField = new NumberField("Initial load (kilograms)");
		initialLoadField.setHelperText("Specify for how many kilograms you reserve for yourself");
		initialLoadField.setRequired(true);
		initialLoadField.setMin(0);

		maxLoadField = new NumberField("Maximum load (kilograms)");
		maxLoadField.setRequired(true);
		maxLoadField.setHelperText("Specify the capacity of your washing machine in kilograms");
		maxLoadField.setMin(0);

		visibilityTimeField = new NumberField("Visibility time (days)");
		visibilityTimeField.setHelperText("Specify for how long the washing will be visible to users after completion");
		visibilityTimeField.setRequired(true);
		maxLoadField.setMin(0);

		// Machine setup
		temperatureGroup = new RadioButtonGroup<>();
		temperatureGroup.setLabel("Temperature");
		temperatureGroup.setItems("Cold", "30°C", "40°C", "60°C", "90°C");
		temperatureGroup.setRequired(true);

		spinSpeedGroup = new RadioButtonGroup<>();
		spinSpeedGroup.setLabel("Spin speed");
		spinSpeedGroup.setItems("400", "800", "1000", "1200", "1400");
		spinSpeedGroup.setRequired(true);

		fabricTypeGroup = new RadioButtonGroup<>();
		fabricTypeGroup.setLabel("Fabric type");
		fabricTypeGroup.setItems("Cotton", "Synthetic", "Wool", "Delicate", "Any");
		fabricTypeGroup.setRequired(true);

		colorGroup = new RadioButtonGroup<>();
		colorGroup.setLabel("Color");
		colorGroup.setItems("White", "Light", "Dark", "Mixed");
		colorGroup.setRequired(true);

		detergentTypeGroup = new RadioButtonGroup<>();
		detergentTypeGroup.setLabel("Detergent type");
		detergentTypeGroup.setItems("Standard", "Color-safe", "Gentle", "Heavy-duty");
		detergentTypeGroup.setRequired(true);
		// other required

		dryingTypeGroup = new RadioButtonGroup<>();
		dryingTypeGroup.setLabel("Drying type");
		dryingTypeGroup.setItems("Air Dry", "Tumble Dry", "Hang Dry", "None");
		dryingTypeGroup.setRequired(true);

		underwearCheckbox = new Checkbox("Include Underwear/Lingerie");
		
		// not required

		ironingCheckbox = new Checkbox("Include ironing");

		pickupAddressField = new TextField("Pickup address, where to bring the clothes");

		deliveryAddressField = new TextField("Delivery address, where to get the washed clothes");

		pickupAvailabilityField = new TextField("Pickup availability");
		pickupAvailabilityField.setHelperText("Specify your available time slots for pickup");

		deliveryAvailabilityField = new TextField("Delivery availability");
		deliveryAvailabilityField.setHelperText("Specify your available time slots for delivery");

		refundTypeField = new TextField("Reimbursement information");
		refundTypeField.setHelperText(
				"Specify if you will ask for a refound of your expenses, the total amount and the sharing logic");

		participantMaxLoadField = new NumberField("Max load per participant (kilograms)");
		maxLoadField.setMin(0);
		accessOpenDatePicker = new DateTimePicker("Participants accepted after Date/Time");
		accessOpenDatePicker.setStep(Duration.ofMinutes(15));
		accessOpenDatePicker.setHelperText("Specify when people will be able to access the washing");
		accessCloseDatePicker = new DateTimePicker("Participant accepted untill Date/Time");
		accessCloseDatePicker.setStep(Duration.ofMinutes(15));
		accessCloseDatePicker.setHelperText("Specify till when it will be possible to add clothes to the washing");

		submitButton = new Button();
		submitButton.setWidth("min-content");
		submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		submitButton.addClickListener(event -> handleSubmit());
		updateSubmitButtonLabel();
		cancelButton = new Button();
		cancelButton.setWidth("min-content");
		cancelButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		cancelButton.setText("Cancel");
		cancelButton.addClickListener(event -> fireEvent(new CancelEvent(this)));

		// requred options
		formLayout.add(dateTimeWashingPicker, durationField, initialLoadField, maxLoadField, visibilityTimeField,
				temperatureGroup, spinSpeedGroup, fabricTypeGroup, colorGroup, detergentTypeGroup, dryingTypeGroup,
				underwearCheckbox);
		// not requred options
		formLayoutNotRequired.add(refundTypeField, ironingCheckbox, pickupAddressField, deliveryAddressField,
				pickupAvailabilityField, deliveryAvailabilityField, participantMaxLoadField, accessOpenDatePicker,
				accessCloseDatePicker);
		optionalInputs.add(formLayoutNotRequired);
		add(formLayout);
		add(optionalInputs);
		add(submitButton);
		add(cancelButton);

	}

	/**
	 * Initializes the form with an existing washing in order to edit it
	 * 
	 * @param washing the washing to be edited
	 */
	public void init(String washingId) {
		try{
			washingInfo = wCore.getWashing(washingId);
		}catch(WashineCoreException e){
			UiNotifier.showErrorNotification(e.getMessage());
		}
		
		WashineLaundryWashingOptionsLaunderIf options = washingInfo.getWashingOptionsLaunder();

		// int fields set to 0 are the default unset value
		if (options.getDatetime() > 0)
			dateTimeWashingPicker.setValue(WashineTimeUtils.unixTimestampToLocalDate(options.getDatetime()));
		if (options.getWashingAccessOpenDate() > 0)
			accessOpenDatePicker.setValue(WashineTimeUtils.unixTimestampToLocalDate(options.getWashingAccessOpenDate()));
		if (options.getWashingAccessCloseDate() > 0)
			accessCloseDatePicker.setValue(WashineTimeUtils.unixTimestampToLocalDate(options.getWashingAccessCloseDate()));

		if (options.getDurationMinutes() > 0)
			durationField.setValue((double) options.getDurationMinutes());
		if (options.getInitialLoad() > 0)
			initialLoadField.setValue(options.getInitialLoad());
		if (options.getMaxLoad() > 0)
			maxLoadField.setValue(options.getMaxLoad());
		// this one is required and can have defaut 0
		// if (options.getVisibilityTime() > 0)
		visibilityTimeField.setValue((double) options.getVisibilityTime());
		if (options.getMaxLoadParticipant() > 0)
			participantMaxLoadField.setValue(options.getMaxLoadParticipant());

		temperatureGroup.setValue(options.getTemperature());
		spinSpeedGroup.setValue(options.getSpinSpeed());
		fabricTypeGroup.setValue(options.getFabricType());
		colorGroup.setValue(options.getColor());
		detergentTypeGroup.setValue(options.getDetergentType());
		dryingTypeGroup.setValue(options.getDryingType());

		underwearCheckbox.setValue(options.isUnderwear());
		ironingCheckbox.setValue(options.isIroning());

		pickupAddressField.setValue(options.getPickupAddress());
		deliveryAddressField.setValue(options.getDeliveryAddress());
		pickupAvailabilityField.setValue(options.getPickupAvailability());
		deliveryAvailabilityField.setValue(options.getDeliveryAvailability());
		refundTypeField.setValue(options.getRefundType());

		updateSubmitButtonLabel();
	}


	/**
	 * Clears the form fields
	 * 
	 */
	public void reset() {
		washingInfo = null;

		dateTimeWashingPicker.clear();
		accessOpenDatePicker.clear();
		accessCloseDatePicker.clear();

		durationField.clear();
		initialLoadField.clear();
		maxLoadField.clear();
		visibilityTimeField.clear();
		participantMaxLoadField.clear();

		temperatureGroup.clear();
		spinSpeedGroup.clear();
		fabricTypeGroup.clear();
		colorGroup.clear();
		detergentTypeGroup.clear();
		dryingTypeGroup.clear();

		underwearCheckbox.setValue(false);
		ironingCheckbox.setValue(false);

		pickupAddressField.clear();
		deliveryAddressField.clear();
		pickupAvailabilityField.clear();
		deliveryAvailabilityField.clear();
		refundTypeField.clear();

		updateSubmitButtonLabel();
	}

	/**
	 * Updates the submit button label depending on the edit or update context
	 */
	private void updateSubmitButtonLabel() {
		if (washingInfo == null) {
			submitButton.setText("Create Washing");
		} else {
			submitButton.setText("Update Washing");
		}
	}

	/**
	 * Provides validation state of the form and updates the fields to their
	 * validity state
	 * 
	 * @return true if all fields are valid, otherwise false
	 */
	private boolean validateForm() {
		boolean valid = true;

		// reset validity states
		dateTimeWashingPicker.setInvalid(false);
		durationField.setInvalid(false);
		initialLoadField.setInvalid(false);
		maxLoadField.setInvalid(false);
		visibilityTimeField.setInvalid(false);
		temperatureGroup.setInvalid(false);
		spinSpeedGroup.setInvalid(false);
		fabricTypeGroup.setInvalid(false);
		colorGroup.setInvalid(false);
		detergentTypeGroup.setInvalid(false);
		dryingTypeGroup.setInvalid(false);

		// validate and set validity states
		if (dateTimeWashingPicker.getValue() == null) {
			dateTimeWashingPicker.setInvalid(true);
			dateTimeWashingPicker.setErrorMessage("Washing date and time is required");
			valid = false;
		}

		if (durationField.getValue() == null) {
			durationField.setInvalid(true);
			durationField.setErrorMessage("Wash duration is required");
			valid = false;
		}

		if (initialLoadField.getValue() == null) {
			initialLoadField.setInvalid(true);
			initialLoadField.setErrorMessage("Initial load is required");
			valid = false;
		}

		if (maxLoadField.getValue() == null) {
			maxLoadField.setInvalid(true);
			maxLoadField.setErrorMessage("Maximum load is required");
			valid = false;
		}

		if (visibilityTimeField.getValue() == null) {
			visibilityTimeField.setInvalid(true);
			visibilityTimeField.setErrorMessage("Visibility time is required");
			valid = false;
		}

		if (temperatureGroup.getValue() == null) {
			temperatureGroup.setInvalid(true);
			temperatureGroup.setErrorMessage("Temperature selection is required");
			valid = false;
		}

		if (spinSpeedGroup.getValue() == null) {
			spinSpeedGroup.setInvalid(true);
			spinSpeedGroup.setErrorMessage("Spin speed selection is required");
			valid = false;
		}

		if (fabricTypeGroup.getValue() == null) {
			fabricTypeGroup.setInvalid(true);
			fabricTypeGroup.setErrorMessage("Fabric type selection is required");
			valid = false;
		}

		if (colorGroup.getValue() == null) {
			colorGroup.setInvalid(true);
			colorGroup.setErrorMessage("Color selection is required");
			valid = false;
		}

		if (detergentTypeGroup.getValue() == null) {
			detergentTypeGroup.setInvalid(true);
			detergentTypeGroup.setErrorMessage("Detergent type selection is required");
			valid = false;
		}

		if (dryingTypeGroup.getValue() == null) {
			dryingTypeGroup.setInvalid(true);
			dryingTypeGroup.setErrorMessage("Drying type selection is required");
			valid = false;
		}

		// Logical validations
		if (initialLoadField.getValue() != null && maxLoadField.getValue() != null) {
			if (initialLoadField.getValue() > maxLoadField.getValue()) {
				initialLoadField.setInvalid(true);
				initialLoadField.setErrorMessage("Initial load cannot be greater than maximum load");
				valid = false;
			}
		}

		if (dateTimeWashingPicker.getValue() != null) {
			if (dateTimeWashingPicker.getValue().isBefore(LocalDateTime.now())) {
				dateTimeWashingPicker.setInvalid(true);
				dateTimeWashingPicker.setErrorMessage("Washing date and time cannot be in the past");
				valid = false;
			}
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
		if (washingInfo == null) {
			submitNewWashing();
		} else {
			submitWashingUpdate();
		}
	}

	/**
	 * Submits a new washing and if all is OK it starts closing the form
	 */
	private void submitNewWashing() {
		WashineUserIf userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
		boolean result = false;
		try {
			WashineLaundryWashingOptionsLaunderIf options = wCore.getBlankWashingOptions();
			refreshWashingOptions(options);
			result = wCore.createWashing(userData.getId(), options);
		} catch (WashineCoreException e) {
			UiNotifier.showErrorNotification(e.getMessage());
		}
		if (result) {
			// FIRE AN EVENT HERE
			UiNotifier.showSuccessNotification("New washing ready");
			fireEvent(new SavedEvent(this));
		}
	}

	/**
	 * Checks if the washing that is going to be updated has already collectd
	 * participants and if so asks for confirmation
	 * 
	 */
	private void submitWashingUpdate() {

		if (washingInfo.getParticipantIds().isEmpty()) {
			sendWashingUpdate();
		} else {
			askConfirmationBeforeUpdate();
		}

	}

	/**
	 * Opens a confirmation dialog in order to proceed to the update of the 'dirty'
	 * washing
	 */
	private void askConfirmationBeforeUpdate() {
		ConfirmDialog dialog = new ConfirmDialog();
		dialog.setHeader("Update already participated washing?");
		dialog.setText("This washing already has participants, are you sure you want to update it?");

		dialog.setCancelable(true);
	
		dialog.setConfirmText("Update");
		dialog.setConfirmButtonTheme("error primary");
		dialog.addConfirmListener(event -> sendWashingUpdate());
		dialog.open();
	}

	/**
	 * Submits a washing update and if all is OK it starts closing the form
	 */
	private void sendWashingUpdate() {
		boolean result = false;
		try {
			WashineLaundryWashingOptionsLaunderIf options = washingInfo.getWashingOptionsLaunder();
			options.reset();
			refreshWashingOptions(options);
			wCore.updateWashingOptions(washingInfo);
		} catch (WashineCoreException e) {
			UiNotifier.showErrorNotification(e.getMessage());
		}
		if (result) {			
			UiNotifier.showSuccessNotification("Your washing has been updated");
			fireEvent(new SavedEvent(this));
		}
	}

	/**
	 * refreshes an washing options objects to the current state of the form
	 * 
	 * @param options the options object to be refreshed
	 */
	private void refreshWashingOptions(WashineLaundryWashingOptionsLaunderIf options) {

		LocalDateTime washingDateTime = dateTimeWashingPicker.getValue();
		if (washingDateTime != null) {
			options.setDateTime((int) washingDateTime.atZone(ZoneId.systemDefault()).toEpochSecond());
		}

		LocalDateTime openDateTime = accessOpenDatePicker.getValue();
		if (openDateTime != null) {
			options.setWashingAccessOpenDate((int) openDateTime.atZone(ZoneId.systemDefault()).toEpochSecond());
		}

		LocalDateTime closeDateTime = accessCloseDatePicker.getValue();
		if (closeDateTime != null) {
			options.setWashingAccessCloseDate((int) closeDateTime.atZone(ZoneId.systemDefault()).toEpochSecond());
		}

		Double duration = durationField.getValue();
		if (duration != null) {
			options.setDurationMinutes(duration.intValue());
		}

		Double initialLoad = initialLoadField.getValue();
		if (initialLoad != null) {
			options.setInitialLoad(initialLoad);
		}

		Double maxLoad = maxLoadField.getValue();
		if (maxLoad != null) {
			options.setMaxLoad(maxLoad);
		}

		Double visibilityTime = visibilityTimeField.getValue();
		if (visibilityTime != null) {
			options.setVisibilityTime(visibilityTime.intValue() * 24 * 60); // Convert days to minutes
		}

		Double participantMaxLoad = participantMaxLoadField.getValue();
		if (participantMaxLoad != null) {
			options.setParticipantMaxLoad(participantMaxLoad);
		}

		String temperature = temperatureGroup.getValue();
		if (temperature != null) {
			options.setTemperature(temperature);
		}

		String spinSpeed = spinSpeedGroup.getValue();
		if (spinSpeed != null) {
			options.setSpinSpeed(spinSpeed);
		}

		String fabricType = fabricTypeGroup.getValue();
		if (fabricType != null) {
			options.setFabricType(fabricType);
		}

		String color = colorGroup.getValue();
		if (color != null) {
			options.setColor(color);
		}

		String detergentType = detergentTypeGroup.getValue();
		if (detergentType != null) {
			options.setDetergentTypes(detergentType);
		}

		String dryingType = dryingTypeGroup.getValue();
		if (dryingType != null) {
			options.setDrying(dryingType);
		}

		// Handle Checkboxes
		options.setUnderwear(underwearCheckbox.getValue());
		options.setIroning(ironingCheckbox.getValue());

		// Handle Text fields
		String pickupAddress = pickupAddressField.getValue();
		if (pickupAddress != null) {
			options.setPickupAddress(pickupAddress);
		}

		String deliveryAddress = deliveryAddressField.getValue();
		if (deliveryAddress != null) {
			options.setDeliveryAddress(deliveryAddress);
		}

		String pickupAvailability = pickupAvailabilityField.getValue();
		if (pickupAvailability != null) {
			options.setPickupAvailability(pickupAvailability);
		}

		String deliveryAvailability = deliveryAvailabilityField.getValue();
		if (deliveryAvailability != null) {
			options.setDeliveryAvailability(deliveryAvailability);
		}

		String refundType = refundTypeField.getValue();
		if (refundType != null) {
			options.setRefundTypes(refundType);
		}

	}

	/**
	 * Class for custom events for the container
	 */
	public static abstract class MachineFormEvent extends ComponentEvent<MachineForm> {
		protected MachineFormEvent(MachineForm source) {
			super(source, false);
		}
	}

	public static class CancelEvent extends MachineFormEvent {
		public CancelEvent(MachineForm source) {
			super(source);
		}
	}

	public static class SavedEvent extends MachineFormEvent {
		public SavedEvent(MachineForm source) {
			super(source);
		}
	}

	// Add registration methods for the events
	public Registration addCancelListener(ComponentEventListener<CancelEvent> listener) {
		return addListener(CancelEvent.class, listener);
	}
	public Registration addSavedListener(ComponentEventListener<SavedEvent> listener) {
		return addListener(SavedEvent.class, listener);
	}


}
