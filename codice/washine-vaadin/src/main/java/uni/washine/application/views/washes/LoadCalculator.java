package uni.washine.application.views.washes;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.popover.Popover;
import com.vaadin.flow.component.popover.PopoverPosition;
import com.vaadin.flow.component.popover.PopoverVariant;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

public class LoadCalculator extends VerticalLayout {
  public static final double averageTShirtWeight = 0.2;
  public static final double averageJeansWeight = 0.6;
  public static final double averagePantsWeight = 0.6;
  public static final double averageShirtWeight = 0.15;
  public static final double averageShortsWeight = 0.2;
  public static final double averageTowelWeight = 0.4;
  public static final double averageBedsheetWeight = 0.8;

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
              double tshirtWeight = tshirtsField.getValue() * averageTShirtWeight;
              double jeansWeight = jeansField.getValue() * averageJeansWeight;
              double towelWeight = towelsField.getValue() * averageTowelWeight;
              double bedsheetWeight = bedsheetField.getValue() * averageBedsheetWeight;
              double pantsWeight = pantsField.getValue() * averagePantsWeight;
              double shirtsWeight = shirtsField.getValue() * averageShirtWeight;
              double shortsWeight = shortsField.getValue() * averageShortsWeight;

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
    popover.add(
        tshirtsField,
        jeansField,
        towelsField,
        bedsheetField,
        shirtsField,
        shortsField,
        pantsField,
        calculateButton,
        totalWeightField);
    add(button, popover);
  }
}
