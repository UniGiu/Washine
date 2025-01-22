package uni.washine.application.views.mymachine;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;

import  java.time.Instant;

import washine.washineCore.washing.WashineLaundryWashingIf;
import washine.washineCore.washing.WashineLaundryWashingOptionsLaunderIf;
public class MachineForm extends VerticalLayout{
    private static MachineForm uniqueInstance;
    private WashineLaundryWashingIf washingInfo;
    private Button submitButton;
    
   private MachineForm(){
      
        FormLayout formLayout = new FormLayout();
        
       //Required
       //Time
        DateTimePicker dateTimeWashingPicker = new DateTimePicker("Washing Date and Time");
        dateTimeWashingPicker.setHelperText("Format: DD/MM/YYYY and HH:MM");
        dateTimeWashingPicker.setRequiredIndicatorVisible(true); 
        dateTimeWashingPicker.setMin(LocalDateTime.now()); 

        NumberField durationField = new NumberField("Wash duration (minutes)");
        durationField.setRequired(true);
        durationField.setMin(1);

        NumberField initialLoadField = new NumberField("Initial Load (kilograms)");
        initialLoadField.setHelperText("Specify for how many kilograms you reserve for yourself");
        initialLoadField.setRequired(true);
        initialLoadField.setMin(0);

        NumberField maxLoadField = new NumberField("Maximum Load (kilograms)");
        maxLoadField.setRequired(true);
        maxLoadField.setHelperText("Specify the capacity of your washing machine in kilograms");
        maxLoadField.setMin(0);

        NumberField visibilityTimeField = new NumberField("Visibility Time (days)");
        visibilityTimeField.setHelperText("Specify for how long the washing will be visible after completion");
        visibilityTimeField.setRequired(true);
        maxLoadField.setMin(0);
       //Machine setup
        RadioButtonGroup<String> temperatureGroup = new RadioButtonGroup<>();
        temperatureGroup.setLabel("Temperature");
        temperatureGroup.setItems("Cold", "30°C", "40°C", "60°C", "90°C");
        temperatureGroup.setRequired(true);

        RadioButtonGroup<String> spinSpeedGroup = new RadioButtonGroup<>();
        spinSpeedGroup.setLabel("Spin Speed");
        spinSpeedGroup.setItems("400", "800", "1000", "1200", "1400");
        spinSpeedGroup.setRequired(true);

        RadioButtonGroup<String> fabricTypeGroup = new RadioButtonGroup<>();
        fabricTypeGroup.setLabel("Fabric Type");
        fabricTypeGroup.setItems("Cotton", "Synthetic", "Wool", "Delicate", "Any");
        fabricTypeGroup.setRequired(true);

        RadioButtonGroup<String> colorGroup = new RadioButtonGroup<>();
        colorGroup.setLabel("Color");
        colorGroup.setItems("White", "Light", "Dark", "Mixed");
        colorGroup.setRequired(true);

        RadioButtonGroup<String> detergentTypeGroup = new RadioButtonGroup<>();
        detergentTypeGroup.setLabel("Detergent Type");
        detergentTypeGroup.setItems("Standard", "Color-safe", "Gentle", "Heavy-duty");
        detergentTypeGroup.setRequired(true);
        //other required
        
        RadioButtonGroup<String> dryingTypeGroup = new RadioButtonGroup<>();
        dryingTypeGroup.setLabel("Drying Type");
        dryingTypeGroup.setItems("Air Dry", "Tumble Dry", "Hang Dry", "None");
        dryingTypeGroup.setRequired(true);
       
        Checkbox underwearCheckbox = new Checkbox("Include Underwear/Lingerie"); 
        underwearCheckbox.setRequiredIndicatorVisible(true);       
       
        //not required       
        Checkbox ironingCheckbox = new Checkbox("Include Ironing");   
        
        TextField pickupAddressField = new TextField("Pickup Address, where to bring the clothes");
       
        TextField deliveryAddressField = new TextField("Delivery Address, where to get the washed clothes");  

        TextField pickupAvailabilityField = new TextField("Pickup Availability");
        pickupAvailabilityField.setHelperText("Specify your available time slots for pickup");

        TextField deliveryAvailabilityField = new TextField("Delivery Availability");
        deliveryAvailabilityField.setHelperText("Specify your available time slots for delivery");

        TextField refundTypeField= new TextField("Refound information");
        refundTypeField.setHelperText("Specify if you will ask for a refound, the total amount and the sharing logic");

        // Create participant max load field
        NumberField participantMaxLoadField = new NumberField("Max Load per Participant (kilograms)");

        // Create access date/time pickers
        DateTimePicker accessOpenDatePicker = new DateTimePicker("Access Open Date/Time");
        refundTypeField.setHelperText("Specify when people will be able to access the washing");
        DateTimePicker accessCloseDatePicker = new DateTimePicker("Access Close Date/Time");
        refundTypeField.setHelperText("Specify till when it will be possible to add clothes to the washing");

        // Initialize the submit button
        submitButton = new Button();
        updateSubmitButtonLabel(); // Set the initial label based on washingInfo state

        // Add components to form
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
            underwearCheckbox,
            //not requred options
            refundTypeField,
            ironingCheckbox,
            pickupAddressField,
            deliveryAddressField,
            pickupAvailabilityField,
            deliveryAvailabilityField,
            participantMaxLoadField,
            accessOpenDatePicker,
            accessCloseDatePicker,
            submitButton
        );
        
      
        formLayout.setResponsiveSteps(
            new FormLayout.ResponsiveStep("0", 1),
            new FormLayout.ResponsiveStep("500px", 2)
        );
        
        add(formLayout);       

        // Add a click listener to handle the button click
        submitButton.addClickListener(event -> handleSubmit());
    }
    public static MachineForm getInstance() {
        if (uniqueInstance == null) {
        uniqueInstance = new MachineForm();
        }
        return uniqueInstance;
        }
        
    public void init(WashineLaundryWashingIf washing) {
              washingInfo=washing;            
        WashineLaundryWashingOptionsLaunderIf options = washing.getWashingOptionsLaunder();        
       
        getChildren().forEach(component -> {
            if (component instanceof DateTimePicker) {
                DateTimePicker picker = (DateTimePicker) component;
                switch (picker.getLabel()) {
                    case "Washing Date and Time":
                        picker.setValue(convertToLocalDateTimeViaInstant(options.getDatetime()));
                        break;
                    case "Access Open Date/Time":
                        picker.setValue(convertToLocalDateTimeViaInstant(options.getWashingAccessOpenDate()));
                        break;
                    case "Access Close Date/Time":
                        picker.setValue(convertToLocalDateTimeViaInstant(options.getWashingAccessCloseDate()));
                        break;
                }
            } else if (component instanceof NumberField) {
                NumberField field = (NumberField) component;
                switch (field.getLabel()) {
                    case "Duration (minutes)":
                        field.setValue((double) options.getDurationMinutes());
                        break;
                    case "Initial Load":
                        field.setValue(options.getInitialLoad());
                        break;
                    case "Maximum Load":
                        field.setValue(options.getMaxLoad());
                        break;
                    case "Visibility Time (minutes)":
                        field.setValue((double) options.getVisibilityTime());
                        break;
                    case "Max Load per Participant":
                        field.setValue(options.getMaxLoadParticipant());
                        break;
                }
            } else if (component instanceof RadioButtonGroup<?>) {
                @SuppressWarnings("unchecked")
             //} else if (component instanceof RadioButtonGroup<String>) {
             //   RadioButtonGroup<String> group = (RadioButtonGroup<String>) component;
                RadioButtonGroup<String> group = (RadioButtonGroup<String>) component;
                switch (group.getLabel()) {
                    case "Temperature":
                        group.setValue(options.getTemperature());
                        break;
                    case "Spin Speed":
                        group.setValue(options.getSpinSpeed());
                        break;
                    case "Fabric Type":
                        group.setValue(options.getFabricType());
                        break;
                    case "Color":
                        group.setValue(options.getColor());
                        break;
                    case "Detergent Type":
                        group.setValue(options.getDetergentType());
                        break;
                    case "Refund Type":
                        group.setValue(options.getRefundType());
                        break;
                    case "Drying Type":
                        group.setValue(options.getDryingType());
                        break;
                }
            } else if (component instanceof Checkbox) {
                Checkbox checkbox = (Checkbox) component;
                switch (checkbox.getLabel()) {
                    case "Include Underwear/Lingerie":
                        checkbox.setValue(options.isUnderwear());
                        break;
                    case "Include Ironing":
                        checkbox.setValue(options.isIroning());
                        break;
                }
            } else if (component instanceof TextField) {
                TextField field = (TextField) component;
                switch (field.getLabel()) {
                    case "Pickup Address":
                        field.setValue(options.getPickupAddress());
                        break;
                    case "Delivery Address":
                        field.setValue(options.getDeliveryAddress());
                        break;
                    case "Pickup Availability":
                        field.setValue(options.getPickupAvailability());
                        break;
                    case "Delivery Availability":
                        field.setValue(options.getDeliveryAvailability());
                        break;
                }
            }
        });
        
        updateSubmitButtonLabel(); // Update the button label when initializing
    }
    private LocalDateTime convertToLocalDateTimeViaInstant(int timestampSeconds) {
        Instant instant = Instant.ofEpochSecond( timestampSeconds );
    return instant
      .atZone(ZoneId.systemDefault())
      .toLocalDateTime();
    }

    /**
     * resets the form (for a new )
     */
    public void reset() {
        washingInfo=null;       
        getChildren().forEach(component -> {
            if (component instanceof DateTimePicker) {
                DateTimePicker picker = (DateTimePicker) component;
                picker.clear(); 
            } else if (component instanceof NumberField) {
                NumberField field = (NumberField) component;
                field.clear(); 
            } else if (component instanceof RadioButtonGroup<?>) {
                @SuppressWarnings("unchecked")
                RadioButtonGroup<String> group = (RadioButtonGroup<String>) component;
                group.clear(); 
            } else if (component instanceof Checkbox) {
                Checkbox checkbox = (Checkbox) component;
                checkbox.setValue(false); 
            } else if (component instanceof TextField) {
                TextField field = (TextField) component;
                field.clear(); 
            }
        });
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

    private void submitNewWashing(){

    }
    private void submitWashingUpdate(){

    }
    private void refreshWashingInfo() {
        if (washingInfo == null) return;
        
        WashineLaundryWashingOptionsLaunderIf options = washingInfo.getWashingOptionsLaunder();
        
        getChildren().forEach(component -> {
            if (component instanceof DateTimePicker) {
                DateTimePicker picker = (DateTimePicker) component;
                LocalDateTime value = picker.getValue();
                if (value != null) {
                    long epochSeconds = value.atZone(ZoneId.systemDefault()).toEpochSecond();
                    switch (picker.getLabel()) {
                        case "Washing Date and Time":
                            options.setDatetime((int)epochSeconds);
                            break;
                        case "Access Open Date/Time":
                            options.setWashingAccessOpenDate((int)epochSeconds);
                            break;
                        case "Access Close Date/Time":
                            options.setWashingAccessCloseDate((int)epochSeconds);
                            break;
                    }
                }
            } else if (component instanceof NumberField) {
                NumberField field = (NumberField) component;
                Double value = field.getValue();
                if (value != null) {
                    switch (field.getLabel()) {
                        case "Wash duration (minutes)":
                            options.setDurationMinutes(value.intValue());
                            break;
                        case "Initial Load (kilograms)":
                            options.setInitialLoad(value);
                            break;
                        case "Maximum Load (kilograms)":
                            options.setMaxLoad(value);
                            break;
                        case "Visibility Time (days)":
                            options.setVisibilityTime(value.intValue() * 24 * 60); // Convert days to minutes
                            break;
                        case "Max Load per Participant (kilograms)":
                            options.setParticipantMaxLoad()
                            break;
                    }
                }
            } else if (component instanceof RadioButtonGroup<?>) {
                @SuppressWarnings("unchecked")
                RadioButtonGroup<String> group = (RadioButtonGroup<String>) component;
                String value = group.getValue();
                if (value != null) {
                    switch (group.getLabel()) {
                        case "Temperature":
                            options.setTemperature(value);
                            break;
                        case "Spin Speed":
                            options.setSpinSpeed(value);
                            break;
                        case "Fabric Type":
                            options.setFabricType(value);
                            break;
                        case "Color":
                            options.setColor(value);
                            break;
                        case "Detergent Type":
                            options.setDetergentTypes(value);
                            break;
                        case "Drying Type":
                            options.setDrying(value);
                            break;
                    }
                }
            } else if (component instanceof Checkbox) {
                Checkbox checkbox = (Checkbox) component;
                switch (checkbox.getLabel()) {
                    case "Include Underwear/Lingerie":
                        options.setUnderwear(checkbox.getValue());
                        break;
                    case "Include Ironing":
                        options.setIroning(checkbox.getValue());
                        break;
                }
            } else if (component instanceof TextField) {
                TextField field = (TextField) component;
                String value = field.getValue();
                if (value != null) {
                    switch (field.getLabel()) {
                        case "Pickup Address, where to bring the clothes":
                            options.setPickupAddress(value);
                            break;
                        case "Delivery Address, where to get the washed clothes":
                            options.setDeliveryAddress(value);
                            break;
                        case "Pickup Availability":
                            options.setPickupAvailability(value);
                            break;
                        case "Delivery Availability":
                            options.setDeliveryAvailability(value);
                            break;
                        case "Refound information":
                            options.setRefundType(value);
                            break;
                    }
                }
            }
        });
    }
}

