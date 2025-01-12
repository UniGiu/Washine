package uni.washine.application.views.washes;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import uni.washine.application.data.SamplePerson;
import uni.washine.application.services.SamplePersonService;

@PageTitle("Washes")
@Route("washes")
@Menu(order = 2, icon = LineAwesomeIconUrl.TSHIRT_SOLID)
@Uses(Icon.class)
public class WashesView extends Composite<VerticalLayout> {

    public WashesView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H2 h2 = new H2();
        Paragraph textLarge = new Paragraph();
        H3 h3 = new H3();
        Grid basicGrid = new Grid(SamplePerson.class);
        H3 h32 = new H3();
        Grid minimalistGrid = new Grid(SamplePerson.class);
        H3 h33 = new H3();
        Grid minimalistGrid2 = new Grid(SamplePerson.class);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h2.setText("Available Washes");
        h2.setWidth("max-content");
        textLarge.setText(
                "Here you can see which washings you are participating to, which ones are available for you and wich ones you participated recently");
        textLarge.setWidth("100%");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        h3.setText("Washings I Participate to");
        h3.setWidth("max-content");
        basicGrid.setWidth("100%");
        basicGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(basicGrid);
        h32.setText("Available Washings");
        h32.setWidth("max-content");
        minimalistGrid.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS);
        minimalistGrid.setWidth("100%");
        minimalistGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(minimalistGrid);
        h33.setText("Recent washings");
        h33.setWidth("max-content");
        minimalistGrid2.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS);
        minimalistGrid2.setWidth("100%");
        minimalistGrid2.getStyle().set("flex-grow", "0");
        setGridSampleData(minimalistGrid2);
        getContent().add(layoutRow);
        layoutRow.add(h2);
        getContent().add(textLarge);
        getContent().add(h3);
        getContent().add(basicGrid);
        getContent().add(h32);
        getContent().add(minimalistGrid);
        getContent().add(h33);
        getContent().add(minimalistGrid2);
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}
