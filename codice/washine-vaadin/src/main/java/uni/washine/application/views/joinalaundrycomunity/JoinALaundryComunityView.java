package uni.washine.application.views.joinalaundrycomunity;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import uni.washine.application.utils.AuthenticationComponent;
import washine.washineCore.AbstractCoreFactory;
import washine.washineCore.WashineCoreCommunityIf;
import washine.washineCore.user.WashineUserIf;
import uni.washine.application.utils.UiNotifier;

import java.util.List;
import java.util.Map;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@PageTitle("Invitations")
@Route("invitations")
@Menu(order = 5, icon = LineAwesomeIconUrl.SIGN_IN_ALT_SOLID)
public class JoinALaundryComunityView extends Composite<VerticalLayout>
    implements BeforeEnterObserver{
	
	private static Logger logger = LogManager.getLogger();
	
  private WashineUserIf userData;
  // the code in the invitation link
  private String invitationCodeFromURL = "";
    private final WashineCoreCommunityIf community;
  public JoinALaundryComunityView() {
	  community =  AbstractCoreFactory.getInstance("vaadin").createCoreWashineCommunity();
	logger.debug("3: "+invitationCodeFromURL);
    HorizontalLayout layoutRow = new HorizontalLayout();
    H2 h2 = new H2();
    Paragraph textLarge = new Paragraph();

    VerticalLayout layoutColumn2 = new VerticalLayout();
    FormLayout layoutCodeIn = getCodeInsertionLayout();
    Hr hr = new Hr();
    VerticalLayout layoutCodeGen = getCodeGenerationLayout();

    getContent().setWidth("100%");
    getContent().getStyle().set("flex-grow", "1");
    layoutRow.addClassName(Gap.MEDIUM);
    layoutRow.setWidth("100%");
    layoutRow.setHeight("min-content");
    h2.setText("Invitations");
    h2.setWidth("max-content");
    textLarge.setText(
        "Here you can join to a launder washing community or invite people to your own laundry");
    textLarge.setWidth("100%");
    textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
    layoutColumn2.setWidth("100%");
    layoutColumn2.getStyle().set("flex-grow", "1");

    getContent().add(layoutRow);
    layoutRow.add(h2);
    getContent().add(textLarge);
    getContent().add(layoutColumn2);

    layoutColumn2.add(layoutCodeIn, hr, layoutCodeGen);
  }

  private FormLayout getCodeInsertionLayout() {
    FormLayout formLayout2Col = new FormLayout();
    H3 h3Join = new H3();
    Paragraph textJoin = new Paragraph();
    TextField textFieldCode = new TextField();
    TextField textFieldCommunity = new TextField();
    Button buttonJoin = new Button();
    h3Join.setText("Join a community");
    textJoin.setText(
        "Enter the code you received by a machine owner to join their laundry community. Choose a meaningful community name so that you will be able to understand who is offering the wash");
    formLayout2Col.setWidth("100%");
    textFieldCode.setLabel("Join code");
    textFieldCode.setWidth("min-content");
    textFieldCommunity.setLabel("Community name");
    textFieldCommunity.setWidth("min-content");
    buttonJoin.setText("Join");
    buttonJoin.setWidth("min-content");
    buttonJoin.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    formLayout2Col.add(textFieldCode);
    formLayout2Col.add(textFieldCommunity);
    formLayout2Col.add(buttonJoin);
    logger.debug("4: "+invitationCodeFromURL);
    //code from URL
    if (invitationCodeFromURL != null) {
      textFieldCode.setValue(invitationCodeFromURL);
    }
    buttonJoin.addClickListener(
        event -> {
          String code = textFieldCode.getValue().replaceAll("-", "");
          String communityName = textFieldCommunity.getValue();
          String uid = userData.getId();
          //TODO:validare nel core
       
          //controllo input non nullo
          if (communityName.isBlank()) {
            UiNotifier.showErrorNotification("You should provide a community name");
            return;
          }
          if (code.isBlank()) {
              UiNotifier.showErrorNotification("You should provide an invitation code");
              return;
            }           
          //Checks for code
         
        
          String communityId = community.getInvitationCodeCommunityId(code);
         
          if (communityId == null) {
            UiNotifier.showErrorNotification("Wrong or expired code.");
            return;
          }
          
          if (community.userInCommunity(uid, communityId)) {
            UiNotifier.showErrorNotification("You already joined this community.");
          } else {
            if (community.nameInJoinedCommunities(communityName, uid)) {
              UiNotifier.showErrorNotification(
                  "You already used this name for another community you joined.");
            } else {
              // User can finally join the community!
              if (community.joinCommunity(uid, code, communityName)) {
                UiNotifier.showSuccessNotification("Congratulations! Join successful!");
              }
            }
          }
        });

    return formLayout2Col;
  }

  private VerticalLayout getCodeGenerationLayout() {
    VerticalLayout container = new VerticalLayout();
    FormLayout formLayout2Col = new FormLayout();
    H3 h3Generate = new H3();

    TextField textFieldParticipant = new TextField();
    Button buttonGenerate = new Button();
    
    Paragraph invitationLinkText = new Paragraph();
    Paragraph invitationCodeText = new Paragraph();
    h3Generate.setText(
        "Press the generate button and share the code with the invitee. Choose a meaningful namename of the person you want to add to your washing community,");
    formLayout2Col.setWidth("100%");
    textFieldParticipant.setLabel("Participant name");
    textFieldParticipant.setWidth("min-content");

    Anchor link = new Anchor();
    buttonGenerate.setText("Generate");
    buttonGenerate.setWidth("min-content");
    buttonGenerate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
  
    formLayout2Col.add(textFieldParticipant);
    formLayout2Col.add(buttonGenerate);
    container.add(formLayout2Col,invitationCodeText,invitationLinkText,link );
  
    buttonGenerate.addClickListener(
        event -> {
          String newUserName = textFieldParticipant.getValue();
          String uid = userData.getId();
          String invitationCode;
          if (newUserName.isBlank()) {
            UiNotifier.showErrorNotification("You should provide a name");
            return;
          }
       
          if (community.nameInCommunity(newUserName, uid)) {
            UiNotifier.showErrorNotification("This name is already taken.");
            invitationLinkText.setText("");
            invitationCodeText.setText("");
            link.setVisible(false);
            return;
          }
          //TODO:move into the core
          if (community.nameInInvitations(newUserName, uid)) {
            invitationCode = community.updateCode(newUserName);
          } else {
            invitationCode = community.getNewInvitationCode(uid, newUserName);
          }

          if (invitationCode != null) {

            
            String invitationLink =
                VaadinRequest.getCurrent().getHeader("host") + "/invitations?icode="  + invitationCode;

            invitationCodeText.setText(
                "To join your community "  + newUserName  + " should insert this code into the above form: "
                    + makeCodeReadable(invitationCode));
            link.setText(invitationLink);
            link.setHref(invitationLink);
            link.setVisible(true);
            invitationLinkText.setText(
                "or follow this link: "); 
          } else {
            UiNotifier.showErrorNotification("The code could not be created.");
          }
        });

    return container;
  }
  private String makeCodeReadable(String code){
    String s1 = code.substring(0, 4);
            String s2 = code.substring(4, 8);
            String s3 = code.substring(8, 12);
            String readableCode = s1 + "-" + s2 + "-" + s3;

            readableCode.replaceAll("(.{4})", "$1-");
            return readableCode;
  }

  
  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
    if (userData == null) {
      event.rerouteTo("anonymous-user");
    } else {
    	 Location location = event.getLocation();
    	    QueryParameters queryParameters = location.getQueryParameters();
    	    // gets the invitation code from invitation link
    	    Map<String, List<String>> parametersMap = queryParameters.getParameters();  
    	    logger.debug("1: "+invitationCodeFromURL);
    	    if( parametersMap.get("icode") != null && !parametersMap.get("icode").isEmpty()){      
    	      invitationCodeFromURL = parametersMap.get("icode").get(0);
    	    }    
    	    logger.debug("2: "+invitationCodeFromURL);
    }
  }
}

@PageTitle("Invitations")
@Route("anonymous-user")
class AnonymousUser extends Composite<VerticalLayout> {
  public AnonymousUser() {
    HorizontalLayout layoutRow = new HorizontalLayout();
    H2 h2 = new H2();
    Paragraph textLarge = new Paragraph();
    getContent().setWidth("100%");
    getContent().getStyle().set("flex-grow", "1");
    layoutRow.addClassName(Gap.MEDIUM);
    layoutRow.setWidth("100%");
    layoutRow.setHeight("min-content");
    h2.setText("Join a Loundry Community");
    h2.setWidth("max-content");
    textLarge.setText("To manage invitations you must login");
    textLarge.setWidth("100%");
    textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
    getContent().add(layoutRow);
    layoutRow.add(h2);
    getContent().add(textLarge);

    AuthenticationComponent authComp = new AuthenticationComponent();
    getContent().add(authComp);
  }
}
