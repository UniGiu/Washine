package uni.washine.application.views.mycommunity;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import uni.washine.application.data.SamplePerson;
import uni.washine.application.services.SamplePersonService;
import uni.washine.application.views.mymachine.MachineForm;
import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreCommunityIf;
import washine.washineCore.exceptions.WashineCoreException;
import washine.washineCore.user.WashineUser;
import washine.washineCore.user.WashineUserIf;

@PageTitle("My Community")
@Route("my-community")
@Menu(order = 4, icon = LineAwesomeIconUrl.USER_FRIENDS_SOLID)
@Uses(Icon.class)
public class MyCommunityView extends Composite<VerticalLayout> implements BeforeEnterObserver {

  @Autowired private SamplePersonService samplePersonService;

  private WashineUserIf userData;
  final WashineCoreCommunityIf wCore;

  private final Grid<String[]> multiSelectGrid;

  public MyCommunityView() {

    wCore = AbstractCoreFactory.getInstance("vaadin").createCoreWashineCommunity();

    HorizontalLayout layoutRow = new HorizontalLayout();
    H2 h2 = new H2();
    HorizontalLayout layoutRow2 = new HorizontalLayout();
    VerticalLayout layoutColumn2 = new VerticalLayout();
    Paragraph textLarge = new Paragraph();
    H3 h3 = new H3();

    multiSelectGrid = new Grid<>();
    multiSelectGrid.setSelectionMode(Grid.SelectionMode.MULTI);
    multiSelectGrid.setWidthFull();

    configureGrid();

    setGridSampleData(multiSelectGrid);

    Button buttonPrimary = new Button();
    Button buttonPrimary2 = new Button();
    VerticalLayout layoutColumn3 = new VerticalLayout();
    H3 h32 = new H3();
    Paragraph textLarge2 = new Paragraph();
    Details details = new Details();
    Details details2 = new Details();
    Details details3 = new Details();
    getContent().setWidth("100%");
    getContent().getStyle().set("flex-grow", "1");
    layoutRow.addClassName(Gap.MEDIUM);
    layoutRow.setWidth("100%");
    layoutRow.setHeight("min-content");
    h2.setText("Your Laundry Community");
    h2.setWidth("max-content");
    layoutRow2.setWidthFull();
    getContent().setFlexGrow(1.0, layoutRow2);
    layoutRow2.addClassName(Gap.MEDIUM);
    layoutRow2.setWidth("100%");
    layoutRow2.getStyle().set("flex-grow", "1");
    layoutColumn2.setHeightFull();
    layoutRow2.setFlexGrow(1.0, layoutColumn2);
    layoutColumn2.setWidth("100%");
    layoutColumn2.getStyle().set("flex-grow", "1");
    textLarge.setText("Here you can find the list of people who joined your washing community");
    textLarge.setWidth("100%");
    textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
    h3.setText("Community members");
    h3.setWidth("max-content");
    buttonPrimary.setText("Remove selected members");
    buttonPrimary.setWidth("min-content");
    buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    buttonPrimary.addClickListener(event -> removeSelectedMembers());

    layoutColumn3.setHeightFull();
    layoutRow2.setFlexGrow(1.0, layoutColumn3);
    layoutColumn3.setWidth("50%");
    layoutColumn3.getStyle().set("flex-grow", "1");

    textLarge2.setWidth("100%");
    textLarge2.getStyle().set("font-size", "var(--lumo-font-size-xl)");
    details.setWidth("100%");
    details2.setWidth("100%");
    details3.setWidth("100%");
    getContent().add(layoutRow);
    layoutRow.add(h2);
    getContent().add(layoutRow2);
    layoutRow2.add(layoutColumn2);
    layoutColumn2.add(textLarge);
    layoutColumn2.add(h3);
    layoutColumn2.add(multiSelectGrid);
    layoutColumn2.add(buttonPrimary);

    layoutRow2.add(layoutColumn3);
    layoutColumn3.add(h32);
    layoutColumn3.add(textLarge2);
  }

  private void configureGrid() {
 //   multiSelectGrid.addColumn(data -> data[0]).setHeader("ID");
    multiSelectGrid.addColumn(data -> data[1]).setHeader("Name");
  }

  private void setGridSampleData(Grid<String[]> grid) {

    if (userData != null) {
      String userId = userData.getId();
      List<String> Idmembers = wCore.getCommunityMemberId(userId);
      List<String> names = wCore.getCommunityMemberName(userId);

      List<String[]> dataList = new ArrayList<>();
      for (int i = 0; i < Idmembers.size(); i++) {
        dataList.add(new String[] {Idmembers.get(i), names.get(i)});
      }

      grid.setItems(dataList);
    }
  }

  private void removeSelectedMembers() {

    List<String[]> selectedUsers = new ArrayList<>(multiSelectGrid.getSelectedItems());

    for (String[] user : selectedUsers) {
      String memberId = user[0];
      wCore.removeUserFromCommunity(memberId, userData.getId());
    }
    setGridSampleData(multiSelectGrid);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {

    userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
    if (userData == null) {
      event.forwardTo("/");
    } else {
      setGridSampleData(multiSelectGrid);
    }
  }
}
