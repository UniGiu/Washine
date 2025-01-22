package uni.washine.application.views.mymachine;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

import java.time.Instant;

import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreAuthIf;
import washine.washineCore.WashineCoreWashingIf;
import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.user.WashineUserIf;
import washine.washineCore.washing.WashineLaundryWashingIf;
import washine.washineCore.washing.WashineLaundryWashingOptionsLaunderIf;

public class MachineForm extends VerticalLayout {
  final WashineCoreWashingIf wCore;
  private static MachineForm uniqueInstance;
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

  private MachineForm() {
    wCore = AbstractCoreFactory.getInstance("vaadin").createCoreWashing();
    FormLayout formLayout = new FormLayout();
    FormLayout formLayoutNotRequired = new FormLayout();
    Details optionalInputs = new Details();
    optionalInputs.setWidth("100%");
    optionalInputs.setSummaryText("Non required options");
    // Required

    // Time
    dateTimeWashingPicker = new DateTimePicker("Washing Date and Time");
    dateTimeWashingPicker.setHelperText("Format: DD/MM/YYYY and HH:MM");
    dateTimeWashingPicker.setRequiredIndicatorVisible(true);
    dateTimeWashingPicker.setMin(LocalDateTime.now());

    durationField = new NumberField("Wash duration (minutes)");
    durationField.setRequired(true);
    durationField.setMin(1);

    initialLoadField = new NumberField("Initial Load (kilograms)");
    initialLoadField.setHelperText("Specify for how many kilograms you reserve for yourself");
    initialLoadField.setRequired(true);
    initialLoadField.setMin(0);

    maxLoadField = new NumberField("Maximum Load (kilograms)");
    maxLoadField.setRequired(true);
    maxLoadField.setHelperText("Specify the capacity of your washing machine in kilograms");
    maxLoadField.setMin(0);

    visibilityTimeField = new NumberField("Visibility Time (days)");
    visibilityTimeField.setHelperText(
        "Specify for how long the washing will be visible after completion");
    visibilityTimeField.setRequired(true);
    maxLoadField.setMin(0);

    // Machine setup
    temperatureGroup = new RadioButtonGroup<>();
    temperatureGroup.setLabel("Temperature");
    temperatureGroup.setItems("Cold", "30°C", "40°C", "60°C", "90°C");
    temperatureGroup.setRequired(true);

    spinSpeedGroup = new RadioButtonGroup<>();
    spinSpeedGroup.setLabel("Spin Speed");
    spinSpeedGroup.setItems("400", "800", "1000", "1200", "1400");
    spinSpeedGroup.setRequired(true);

    fabricTypeGroup = new RadioButtonGroup<>();
    fabricTypeGroup.setLabel("Fabric Type");
    fabricTypeGroup.setItems("Cotton", "Synthetic", "Wool", "Delicate", "Any");
    fabricTypeGroup.setRequired(true);

    colorGroup = new RadioButtonGroup<>();
    colorGroup.setLabel("Color");
    colorGroup.setItems("White", "Light", "Dark", "Mixed");
    colorGroup.setRequired(true);

    detergentTypeGroup = new RadioButtonGroup<>();
    detergentTypeGroup.setLabel("Detergent Type");
    detergentTypeGroup.setItems("Standard", "Color-safe", "Gentle", "Heavy-duty");
    detergentTypeGroup.setRequired(true);
    // other required

    dryingTypeGroup = new RadioButtonGroup<>();
    dryingTypeGroup.setLabel("Drying Type");
    dryingTypeGroup.setItems("Air Dry", "Tumble Dry", "Hang Dry", "None");
    dryingTypeGroup.setRequired(true);

    underwearCheckbox = new Checkbox("Include Underwear/Lingerie");
    underwearCheckbox.setRequiredIndicatorVisible(true);

    // not required

    ironingCheckbox = new Checkbox("Include Ironing");

    pickupAddressField = new TextField("Pickup Address, where to bring the clothes");

    deliveryAddressField = new TextField("Delivery Address, where to get the washed clothes");

    pickupAvailabilityField = new TextField("Pickup Availability");
    pickupAvailabilityField.setHelperText("Specify your available time slots for pickup");

    deliveryAvailabilityField = new TextField("Delivery Availability");
    deliveryAvailabilityField.setHelperText("Specify your available time slots for delivery");

    refundTypeField = new TextField("Refound information");
    refundTypeField.setHelperText(
        "Specify if you will ask for a refound, the total amount and the sharing logic");

    participantMaxLoadField = new NumberField("Max Load per Participant (kilograms)");

    accessOpenDatePicker = new DateTimePicker("Participants accepted after Date/Time");
    accessOpenDatePicker.setHelperText("Specify when people will be able to access the washing");
    accessCloseDatePicker = new DateTimePicker("Participant accepted untill Date/Time");
    accessCloseDatePicker.setHelperText(
        "Specify till when it will be possible to add clothes to the washing");

    submitButton = new Button();
    submitButton.setWidth("min-content");
    submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    updateSubmitButtonLabel(); // Set the initial label based on washingInfo state

    // requred options
    formLayout.add(
        dateTimeWashingPicker,
        durationField,
        initialLoadField,
        maxLoadField,
        visibilityTimeField,
        temperatureGroup,
        spinSpeedGroup,
        fabricTypeGroup,
        colorGroup,
        detergentTypeGroup,
        dryingTypeGroup,
        underwearCheckbox);
    // not requred options
    formLayoutNotRequired.add(
        refundTypeField,
        ironingCheckbox,
        pickupAddressField,
        deliveryAddressField,
        pickupAvailabilityField,
        deliveryAvailabilityField,
        participantMaxLoadField,
        accessOpenDatePicker,
        accessCloseDatePicker);
    optionalInputs.add(formLayoutNotRequired);
    add(formLayout);
    add(optionalInputs);
    add(submitButton);

    submitButton.addClickListener(event -> handleSubmit());
  }

  public static MachineForm getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new MachineForm();
    }
    return uniqueInstance;
  }

  public void init(WashineLaundryWashingIf washing) {
    washingInfo = washing;
    WashineLaundryWashingOptionsLaunderIf options = washing.getWashingOptionsLaunder();

    // Set values directly using class fields
    dateTimeWashingPicker.setValue(convertToLocalDateTimeViaInstant(options.getDatetime()));
    accessOpenDatePicker.setValue(
        convertToLocalDateTimeViaInstant(options.getWashingAccessOpenDate()));
    accessCloseDatePicker.setValue(
        convertToLocalDateTimeViaInstant(options.getWashingAccessCloseDate()));

    durationField.setValue((double) options.getDurationMinutes());
    initialLoadField.setValue(options.getInitialLoad());
    maxLoadField.setValue(options.getMaxLoad());
    visibilityTimeField.setValue((double) options.getVisibilityTime());
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

  private LocalDateTime convertToLocalDateTimeViaInstant(int timestampSeconds) {
    Instant instant = Instant.ofEpochSecond(timestampSeconds);
    return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  /** resets the form (for a new washing) */
  public void reset() {
    washingInfo = null;

    // Clear all form fields directly
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

  private void updateSubmitButtonLabel() {
    if (washingInfo == null) {
      submitButton.setText("Create Washing");
    } else {
      submitButton.setText("Update Washing");
    }
  }

  private void handleSubmit() {
    if (washingInfo == null) {
      submitNewWashing();
    } else {
      submitWashingUpdate();
    }
  }

  private void submitNewWashing() {
    WashineUserIf userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
    try {
      washingInfo = wCore.createWashing(userData.getId());
      refreshWashingInfo();
      wCore.updateWashingOptions(washingInfo);
    } catch (WashineCoreException e) {
      e.printStackTrace();
    }
  }

  private void submitWashingUpdate() {}

  private void refreshWashingInfo() {
    if (washingInfo == null) return;

    WashineLaundryWashingOptionsLaunderIf options = washingInfo.getWashingOptionsLaunder();

    LocalDateTime washingDateTime = dateTimeWashingPicker.getValue();
    if (washingDateTime != null) {
      options.setDateTime((int) washingDateTime.atZone(ZoneId.systemDefault()).toEpochSecond());
    }

    LocalDateTime openDateTime = accessOpenDatePicker.getValue();
    if (openDateTime != null) {
      options.setWashingAccessOpenDate(
          (int) openDateTime.atZone(ZoneId.systemDefault()).toEpochSecond());
    }

    LocalDateTime closeDateTime = accessCloseDatePicker.getValue();
    if (closeDateTime != null) {
      options.setWashingAccessCloseDate(
          (int) closeDateTime.atZone(ZoneId.systemDefault()).toEpochSecond());
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
}
