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
public class MyCommunityView extends Composite<VerticalLayout>  implements BeforeEnterObserver{
	
	
	@Autowired
	private SamplePersonService samplePersonService;

	private WashineUserIf userData;
	final WashineCoreCommunityIf wCore; 
	
	private final Grid<WashineUserIf> multiSelectGrid;

    public MyCommunityView() {
    	
    	
    	wCore = AbstractCoreFactory.getInstance("vaadin").createCoreWashineCommunity(); 

		HorizontalLayout layoutRow = new HorizontalLayout();
        H2 h2 = new H2();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Paragraph textLarge = new Paragraph();
        H3 h3 = new H3();
        
     
        multiSelectGrid = new Grid<>(WashineUserIf.class, false);
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
        buttonPrimary.setText("Remove selected members from the community");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(event -> removeSelectedMembers());

        buttonPrimary2.setText("Create a washing group with selected members");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layoutColumn3.setHeightFull();
        layoutRow2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("50%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        h32.setText("Washing Groups");
        h32.setWidth("max-content");
        textLarge2.setText(
                "Washing groups are sets of people you can enable to partecipate when setting up a washing. To create a group select the partecipants from the community members and then press the \"Create group\" Button");
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
        layoutColumn2.add(buttonPrimary2);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(h32);
        layoutColumn3.add(textLarge2);
        layoutColumn3.add(details);
        layoutColumn3.add(details2);
        layoutColumn3.add(details3);
    
    }
    
    private void configureGrid() {
        multiSelectGrid.addColumn(WashineUserIf::getId).setHeader("Id");
        multiSelectGrid.addColumn(WashineUserIf::getEmail).setHeader("Email");
        //multiSelectGrid.addColumn(WashineUserIf::getName).setHeader("Name");
        
    }
    
    
    private void setGridSampleData(Grid<WashineUserIf> grid) {
    	
        if (userData != null) {
        	String userId = userData.getId(); //ottieni id utente loggato
        	try {
        		List<WashineUserIf> members = wCore.getCommunityMembers(userId);
        		grid.setItems(members);
		
			} catch (WashineCoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    private void removeSelectedMembers() {
        // Ottieni gli utenti selezionati nella griglia
        List<WashineUserIf> selectedUsers = new ArrayList<>(multiSelectGrid.getSelectedItems());

        if (selectedUsers.isEmpty()) {
            return; 
        }

        for (WashineUserIf user : selectedUsers) {
		    wCore.removeUserFromCommunity(user.getId(), userData.getId()); 
		}
         
		setGridSampleData(multiSelectGrid);
    }
    /*private void createWashingGroup() {
        List<WashineUserIf> selectedUsers = new ArrayList<>(multiSelectGrid.getSelectedItems());

        if (selectedUsers.isEmpty()) {
            return;
        }

        try {
            String groupId = wCore.createWashingGroup(userData.getId(), selectedUsers);
            System.out.println("Created washing group with ID: " + groupId);
        } catch (WashineCoreException | SQLException e) {
            e.printStackTrace();
        }*/


/**
 * Redirects anonymous users to home
 */
  @Override
  public void beforeEnter(BeforeEnterEvent event) {
	  
	  userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
	  if(userData==null) {
		  event.forwardTo("/");
	  }else {
		  setGridSampleData(multiSelectGrid);
	  }
  }
}
