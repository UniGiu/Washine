package uni.washine.application.utils;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import washine.washineCore.washing.WashineLaundryWashingIf;
import washine.washineCore.washing.WashineLaundryWashingOptionsIf;
import com.vaadin.flow.component.details.Details;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class WashingListItem extends HorizontalLayout {
    private final WashineLaundryWashingIf washing;

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
        VerticalLayout primaryInfo = new VerticalLayout();
        primaryInfo.setSpacing(false);
        primaryInfo.setPadding(false);
        
               
        Span dateTime = new Span(unixTimestampToString(options.getDatetime()));
        dateTime.getStyle().set("font-weight", "bold");
        
      
        Span availableLoad = new Span(String.format("Available Load: %.1f/%.1f kg", 
            options.getInitialLoad(), 
            options.getMaxLoad()));
        availableLoad.getStyle().set("color", "var(--lumo-secondary-text-color)");
        
        // Progress bar
        ProgressBar progressBar = new ProgressBar();
        double progress = options.getInitialLoad() / options.getMaxLoad();
        progressBar.setValue(progress);
        progressBar.setWidth("100%");
        progressBar.setHeight("10px");
        
        primaryInfo.add(dateTime, availableLoad, progressBar);
        
      
        VerticalLayout characteristics = new VerticalLayout();
        characteristics.setSpacing(false);
        characteristics.setPadding(false);
        
       
        Div washingSpecs = new Div();
        washingSpecs.add(new Span(options.getTemperature()));
        washingSpecs.add(new Span(" • "));
        washingSpecs.add(new Span(options.getFabricType()));
        washingSpecs.add(new Span(" • "));
        washingSpecs.add(new Span(options.getColor()));
        
       
        Span participants = new Span(new Icon(VaadinIcon.USERS));
        participants.add(String.format(" %d participants", washing.getParticipantIds().size()));
        participants.getStyle().set("color", "var(--lumo-secondary-text-color)");
        
        characteristics.add(washingSpecs, participants);
              
        addAdditionalDetails(characteristics, options);
              
        Span status = createStateBadge(options);
                
        status.getStyle()
            .set("margin-left", "auto")
            .set("flex-shrink", "0"); 
              
        add(primaryInfo, characteristics, status);             
        expand(characteristics);               
    }

    private void addAdditionalDetails(VerticalLayout characteristics, WashineLaundryWashingOptionsIf options) {
  
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
            addDetailRow(detailsContent, "Opening Date", unixTimestampToString(options.getWashingAccessOpenDate()));
        }

      
        if (options.getWashingAccessCloseDate() > 0) {
            addDetailRow(detailsContent, "Opening Date", unixTimestampToString(options.getWashingAccessCloseDate()));
        }
      
        //Only if there is something to shw
        if (detailsContent.getChildren().count() > 0) {
            additionalInfo.add(detailsContent);
            characteristics.add(additionalInfo);
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
        LocalDateTime washingDateTime = unixTimestampToLocalDate(options.getDatetime());
        
        String statusText;
        String theme;
        
        // Check opening date
        if (options.getWashingAccessOpenDate() > 0) {
            LocalDateTime openingDateTime = unixTimestampToLocalDate((options.getWashingAccessOpenDate()));
                           
            if (now.isBefore(openingDateTime)) {
                statusText = "Opens on " + openingDateTime.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
                theme = "contrast";
                return createBadge(statusText, theme);
            }
        }
        
        // Check closing date
        if (options.getWashingAccessCloseDate() > 0) {
            LocalDateTime closingDateTime = unixTimestampToLocalDate(options.getWashingAccessCloseDate());

                
            if (now.isAfter(closingDateTime)) {
                statusText = "Participations closed";
                theme = "error";
                return createBadge(statusText, theme);
            }
            
            statusText = "Closing on " + dateTimeToString(closingDateTime);
        } else {
            statusText = "Closing on " + dateTimeToString(washingDateTime);
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

    	/**
	 * Utility function to transoform unix timestamp in seconds to LocalDateTime
	 * 
	 * @param timestampSeconds
	 * @return the LocalDateTime of the date
	 */
	private LocalDateTime unixTimestampToLocalDate(int timestampSeconds) {
		Instant instant = Instant.ofEpochSecond(timestampSeconds);
		return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
     	/**
	 * Utility function to transoform LocalDateTime to local
	 * String
	 * 
	 * @param ldt
	 * @return the date in local format string
	 */
	private String dateTimeToString(LocalDateTime ldt) {		
		return ldt.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
	}
    	/**
	 * Utility function to transoform unix timestamp in seconds to local
	 * String
	 * 
	 * @param timestampSeconds
	 * @return the date in local format string
	 */
	private String unixTimestampToString(int timestampSeconds) {		
		return dateTimeToString(unixTimestampToLocalDate(timestampSeconds));
	}
}
