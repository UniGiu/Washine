package uni.washine.application.utils;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.details.Details;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import washine.washineCore.washing.WashineLaundryWashingIf;
import washine.washineCore.washing.WashineLaundryWashingOptionsIf;

public class WashingListItem extends HorizontalLayout {
    private final WashineLaundryWashingIf washing;
    
	private static Logger logger = LogManager.getLogger();

    public WashingListItem(WashineLaundryWashingIf washing) {
        this.washing = washing;
        WashineLaundryWashingOptionsIf options = washing.getWashingOptions();
        
        setWidth("100%");
        setPadding(true);
        setSpacing(true);
        
        //responsive
        //TODO: check if it's possible to use media queries without modifying the CSS files
        getStyle().set("gap", "var(--lumo-space-s)");
        getStyle().set("width", "100%"); 
        getStyle().set("overflow", "hidden");
        getStyle().set("flex-wrap", "wrap");
        getStyle().set("max-width", "500px"); 

        // Left section with primary info
        VerticalLayout layoutPrimaryInfo = new VerticalLayout();
        layoutPrimaryInfo.setSpacing(false);
        layoutPrimaryInfo.setPadding(false);
        
        Span spanDateTime = new Span(WashineTimeUtils.unixTimestampToString(options.getDatetime()));
        spanDateTime.getStyle().set("font-weight", "bold");
        
        Double currentLoad = washing.getLoad();
        Double maxLoad = options.getMaxLoad();
        Double availableLoad = maxLoad - currentLoad;
        Span spanLoadInfo = new Span(String.format(
            "Available Load: %.1f/%.1f kg", 
            availableLoad, maxLoad));
       
        spanLoadInfo.getStyle().set("color", "var(--lumo-secondary-text-color)");
        
        // Progress bar
        ProgressBar progressBar = new ProgressBar();
        double progress = options.getInitialLoad() / options.getMaxLoad();
        progressBar.setValue(progress);
        progressBar.setWidth("100%");
        progressBar.setHeight("10px");
        
        layoutPrimaryInfo.add(spanDateTime, spanLoadInfo, progressBar);
        
        VerticalLayout layoutCharacteristics = new VerticalLayout();
        layoutCharacteristics.setSpacing(false);
        layoutCharacteristics.setPadding(false);
        
        Div divWashingSpecs = new Div();
        divWashingSpecs.add(new Span(options.getTemperature()));
        divWashingSpecs.add(new Span(" • "));
        divWashingSpecs.add(new Span(options.getFabricType()));
        divWashingSpecs.add(new Span(" • "));
        divWashingSpecs.add(new Span(options.getColor()));
        
        Span spanParticipants = new Span(new Icon(VaadinIcon.USERS));
        spanParticipants.add(String.format(" %d participants", washing.getParticipantIds().size()));
        spanParticipants.getStyle().set("color", "var(--lumo-secondary-text-color)");
        
        layoutCharacteristics.add(divWashingSpecs, spanParticipants);
              
        addAdditionalDetails(layoutCharacteristics, options);
              
        Span spanStatus = createStateBadge(options);
                
        spanStatus.getStyle()
            .set("margin-left", "auto")
            .set("flex-shrink", "0"); 
              
        add(layoutPrimaryInfo, layoutCharacteristics, spanStatus);             
        expand(layoutCharacteristics);   
        getStyle().set("background-image", "linear-gradient(#0000 , #EEEE)");
		
    }

    private void addAdditionalDetails(VerticalLayout layoutCharacteristics, WashineLaundryWashingOptionsIf options) {
  
        Details additionalInfo = new Details();
        additionalInfo.setSummaryText("More details");        
     
        VerticalLayout detailsContent = new VerticalLayout();
        detailsContent.setSpacing(false);
        detailsContent.setPadding(false);
               
        if (options.getSpinSpeed() != null && !options.getSpinSpeed().isEmpty()) {
            addDetailRow(detailsContent, "Spin Speed", options.getSpinSpeed());
        }
                
        if (options.getDetergentType() != null && !options.getDetergentType().isEmpty()) {
            addDetailRow(detailsContent, "Detergent", options.getDetergentType());
        }        
      
        if (options.getDryingType() != null && !options.getDryingType().isEmpty()) {
            addDetailRow(detailsContent, "Drying", options.getDryingType());
        }
               
        if (options.isUnderwear()) {
            addDetailRow(detailsContent, "Includes", "Underwear/Lingerie");
        }        
       
        if (options.isIroning()) {
            addDetailRow(detailsContent, "Includes", "Ironing");
        }        
        
        if (options.getMaxLoadParticipant() > 0) {
            addDetailRow(detailsContent, "Max Load per Participant", 
                String.format("%.1f kg", options.getMaxLoadParticipant()));
        }        
        
        if (options.getPickupAddress() != null && !options.getPickupAddress().isEmpty()) {
            addDetailRow(detailsContent, "Pickup Address", options.getPickupAddress());
        }
                
        if (options.getDeliveryAddress() != null && !options.getDeliveryAddress().isEmpty()) {
            addDetailRow(detailsContent, "Delivery Address", options.getDeliveryAddress());
        }        
       
        if (options.getPickupAvailability() != null && !options.getPickupAvailability().isEmpty()) {
            addDetailRow(detailsContent, "Pickup Availability", options.getPickupAvailability());
        }        
       
        if (options.getDeliveryAvailability() != null && !options.getDeliveryAvailability().isEmpty()) {
            addDetailRow(detailsContent, "Delivery Availability", options.getDeliveryAvailability());
        }
       
        if (options.getRefundType() != null && !options.getRefundType().isEmpty()) {
            addDetailRow(detailsContent, "Reimbursement", options.getRefundType());
        }        
      
      
        if (options.getWashingAccessOpenDate() > 0) {
            addDetailRow(detailsContent, "Opening Date", WashineTimeUtils.unixTimestampToString(options.getWashingAccessOpenDate()));
        }

      
        if (options.getWashingAccessCloseDate() > 0) {
            addDetailRow(detailsContent, "Opening Date", WashineTimeUtils.unixTimestampToString(options.getWashingAccessCloseDate()));
        }
      
        //Only if there is something to shw
        if (detailsContent.getChildren().count() > 0) {
            additionalInfo.add(detailsContent);
            layoutCharacteristics.add(additionalInfo);
        }
    }

    private void addDetailRow(VerticalLayout container, String label, String value) {
        Div row = new Div();
        row.getStyle().set("margin", "4px 0");
        
        Span labelSpan = new Span(label + ": ");
        labelSpan.getStyle()
            .set("color", "var(--lumo-secondary-text-color)")
            .set("font-size", "var(--lumo-font-size-s)");
        
        Span valueSpan = new Span(value);
        valueSpan.getStyle().set("font-size", "var(--lumo-font-size-s)");
        
        row.add(labelSpan, valueSpan);
        container.add(row);
    }

    private Span createStateBadge(WashineLaundryWashingOptionsIf options) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime washingDateTime = WashineTimeUtils.unixTimestampToLocalDate(options.getDatetime());
        
        String statusText;
        String theme;
        
        // Check opening date
        if (options.getWashingAccessOpenDate() > 0) {
            LocalDateTime openingDateTime = WashineTimeUtils.unixTimestampToLocalDate((options.getWashingAccessOpenDate()));
                           
            if (now.isBefore(openingDateTime)) {
                statusText = "Opens on " + openingDateTime.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
                theme = "contrast";
                return createBadge(statusText, theme);
            }
        }
        
        // Check closing date
        if (options.getWashingAccessCloseDate() > 0) {
            LocalDateTime closingDateTime = WashineTimeUtils.unixTimestampToLocalDate(options.getWashingAccessCloseDate());

                
            if (now.isAfter(closingDateTime)) {
                statusText = "Participations closed";
                theme = "error";
                return createBadge(statusText, theme);
            }
            
            statusText = "Closing on " + WashineTimeUtils.dateTimeToString(closingDateTime);
        } else {
            statusText = "Closing on " + WashineTimeUtils.dateTimeToString(washingDateTime);
        }
        
        // Check if washing date has passed
        if (now.isAfter(washingDateTime)) {
            statusText = "Washing expired";
            theme = "error";
        } else {
            theme = "success";
        }
        
        return createBadge(statusText, theme);
    }
    // The coloured state badge 
    private Span createBadge(String text, String theme) {
        Span badge = new Span(text);
        badge.getElement().getThemeList().add(theme);
        badge.getElement().getThemeList().add("badge");
        badge.getStyle()
            .set("font-size", "var(--lumo-font-size-s)")
            .set("white-space", "nowrap")
            .set("max-width", "none");  // Ensure badge can grow as needed
        return badge;
    }

    public WashineLaundryWashingIf getWashing() {
        return washing;
    }

}
