package uni.washine.application.views.washes;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.popover.Popover;
import com.vaadin.flow.component.popover.PopoverPosition;
import com.vaadin.flow.component.popover.PopoverVariant;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import uni.washine.application.utils.UiNotifier;

public class LoadCalculator extends VerticalLayout {
  public static final double AVERAGE_TSHIRT_WEIGHT = 0.2;
  public static final double AVERAGE_JEANS_WEIGHT = 0.6;
  public static final double AVERAGE_PANTS_WEIGHT = 0.6;
  public static final double AVERAGE_SHIRT_WEIGHT = 0.15;
  public static final double AVERAGE_SHORT_WEIGHT = 0.2;
  public static final double AVERAGE_TOWEL_WEIGHT = 0.4;
  public static final double AVERAGE_BEDSHEET_WEIGHT = 0.8;

  public LoadCalculator() {
    Button button = new Button();
    button.setText("Load calculator");
    button.setWidth("min-content");
    button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    button.setAriaLabel("Clothes labels meanings");

    Popover popover = new Popover();
    popover.setTarget(button);
    popover.setWidth("300px");
    popover.addThemeVariants(PopoverVariant.ARROW, PopoverVariant.LUMO_NO_PADDING);
    popover.setPosition(PopoverPosition.BOTTOM);
    popover.setAriaLabelledBy("notifications-heading");

    setPadding(true);

    NumberField tshirtsField = new NumberField("T-shirts");
    tshirtsField.setValue(0.0);
    NumberField jeansField = new NumberField("Jeans");
    jeansField.setValue(0.0);
    NumberField pantsField = new NumberField("Pants");
    pantsField.setValue(0.0);
    NumberField shirtsField = new NumberField("Shirts");
    shirtsField.setValue(0.0);
    NumberField shortsField = new NumberField("Shorts");
    shortsField.setValue(0.0);
    NumberField towelsField = new NumberField("Towels");
    towelsField.setValue(0.0);
    NumberField bedsheetField = new NumberField("Bedsheets");
    bedsheetField.setValue(0.0);

    TextField totalWeightField = new TextField("Total weight (kg)");
    totalWeightField.setReadOnly(true);

    Button calculateButton =
        new Button(
            "Calculate",
            event -> {
              double tshirtWeight = tshirtsField.getValue() * AVERAGE_TSHIRT_WEIGHT;
              double jeansWeight = jeansField.getValue() * AVERAGE_JEANS_WEIGHT;
              double towelWeight = towelsField.getValue() * AVERAGE_TOWEL_WEIGHT;
              double bedsheetWeight = bedsheetField.getValue() * AVERAGE_BEDSHEET_WEIGHT;
              double pantsWeight = pantsField.getValue() * AVERAGE_PANTS_WEIGHT;
              double shirtsWeight = shirtsField.getValue() * AVERAGE_SHIRT_WEIGHT;
              double shortsWeight = shortsField.getValue() * AVERAGE_SHORT_WEIGHT;

              double totalWeight =
                  tshirtWeight
                      + jeansWeight
                      + towelWeight
                      + bedsheetWeight
                      + pantsWeight
                      + shirtsWeight
                      + shortsWeight;
              totalWeightField.setValue(String.format("%.2f", totalWeight) + " kg");
              tshirtsField.setValue(0.0);
              jeansField.setValue(0.0);
              pantsField.setValue(0.0);
              shirtsField.setValue(0.0);
              shortsField.setValue(0.0);
              towelsField.setValue(0.0);
              bedsheetField.setValue(0.0);
            });
    Button copyButton =
        new Button(
            VaadinIcon.COPY.create(),
            event -> {
              String value = totalWeightField.getValue();
              if (!value.isEmpty()) {
                Page page = getUI().get().getPage();
                page.executeJs(
                    "var input = document.createElement('input');"
                        + "document.body.appendChild(input);"
                        + "input.value = $0;"
                        + "input.select();"
                        + "document.execCommand('copy');"
                        + "document.body.removeChild(input);",
                    value);
                UiNotifier.showSuccessNotification("Weight copied: " + value);
              }
            });
    popover.add(
        tshirtsField,
        jeansField,
        towelsField,
        bedsheetField,
        shirtsField,
        shortsField,
        pantsField,
        calculateButton,
        totalWeightField,
        copyButton);
    add(button, popover);
  }
}
