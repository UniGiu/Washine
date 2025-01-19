package uni.washine.application.views.joinalaundrycomunity;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import uni.washine.application.utils.AuthenticationComponent;
import washine.washineCore.WashineCoreCommunity;
import washine.washineCore.WashineCoreCommunityIf;
import washine.washineCore.user.WashineUserIf;
import uni.washine.application.utils.UiNotifier;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Invitations")
@Route("invitations")
@Menu(order = 5, icon = LineAwesomeIconUrl.SIGN_IN_ALT_SOLID)
public class JoinALaundryComunityView extends Composite<VerticalLayout> implements BeforeEnterObserver {

	private WashineUserIf userData;

	public JoinALaundryComunityView() {
		HorizontalLayout layoutRow = new HorizontalLayout();
		H2 h2 = new H2();
		Paragraph textLarge = new Paragraph();

		VerticalLayout layoutColumn2 = new VerticalLayout();
		FormLayout layoutCodeIn = getCodeInsertionLayout();
		Hr hr = new Hr();
		FormLayout layoutCodeGen = getCodeGenerationLayout();

		getContent().setWidth("100%");
		getContent().getStyle().set("flex-grow", "1");
		layoutRow.addClassName(Gap.MEDIUM);
		layoutRow.setWidth("100%");
		layoutRow.setHeight("min-content");
		h2.setText("Invitations");
		h2.setWidth("max-content");
		textLarge.setText("Here you can join to a launder washing community or invite people to your own laundry");
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

		buttonJoin.addClickListener(event -> {
			String code = textFieldCode.getValue();
			String communityName = textFieldCommunity.getValue();
			String uid = userData.getId();
			
			WashineCoreCommunityIf community = new WashineCoreCommunity();
            String communityId=community.getInvitationCodeCommunityId(code);
            if(code==null){
                UiNotifier.showErrorNotification("Wrong or expired code.");
            }else{
                if (community.userInCommunity(uid, communityName)) {
                    UiNotifier.showErrorNotification("You already joined this community.");                    
                } else {
                    if(community.nameInJoinedCommunities(uid, communityName)){
                        UiNotifier.showErrorNotification("You already used this name for another community you joined.");                    
                    }else{
                        //User can finally join the community!
                        if(community.joinCommunity(uid, communityName, code)){
                            UiNotifier.showSuccessNotification("Congratulations! Join successful!");
                        }
                    }
                }

			}
		});
		
		
		
		return formLayout2Col;
	}

	private FormLayout getCodeGenerationLayout() {
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
		buttonGenerate.setText("Generate");
		buttonGenerate.setWidth("min-content");
		buttonGenerate.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		formLayout2Col.add(textFieldParticipant);
		formLayout2Col.add(buttonGenerate);
		formLayout2Col.add(invitationLinkText);

		buttonGenerate.addClickListener(event -> {
			String newUserName = textFieldParticipant.getValue();
			String uid = userData.getId();
			
			WashineCoreCommunityIf community = new WashineCoreCommunity();

			if (community.nameInCommunity(uid, newUserName)) {
				UiNotifier.showErrorNotification("This name is already taken.");
				invitationLinkText.setText("");
				invitationCodeText.setText("");
			} else {
				String invitationCode = community.getNewInvitationCode(uid, newUserName);
				if(invitationCode!=null) {
					String invitationLink = "https://www.washine.uni/invitation?icode=" + invitationCode;
					invitationCodeText.setText("");
					invitationLinkText.setText("Share this link: " + invitationLink); // Update the paragraph with the link
				}else {
					UiNotifier.showErrorNotification("The code could not be created.");
				}
				
			}
		});

		return formLayout2Col;
	}

	/**
	 * Shows authentication component to anonymous users
	 */
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		userData = (WashineUserIf) VaadinSession.getCurrent().getAttribute("currentUser");
		if (userData == null) {
			event.rerouteTo("anonymous-user");
			// event.rerouteTo(AnonymousUser.class);
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