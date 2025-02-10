package uni.washine.application.views.help;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Help")
@Route("help")
@Menu(order = 6, icon = LineAwesomeIconUrl.QUESTION_CIRCLE)
public class HelpView extends Composite<VerticalLayout> {

  public HelpView() {

    HelpViewParagraph paragraph1 =
        new HelpViewParagraph(
            "Registration and log in", "videos/register_login_logout.mp4", "500px");

    paragraph1.setAnchor("", "Home page");
    paragraph1.setText(
        new Paragraph(
            new Text("To get started with Washine go to the "),
            paragraph1.getAnchor(),
            new Text(
                ". First of all you need to register a new account, you can do that by inserting a valid email and a safe password in the specific fields in the bottom part of the page and clicking the subscribe button. Make sure that the email is correct and that your password is at least 8 characters long. After successfully creating an account you can go ahead and insert the same email and password you just entered in the fields in the top part of the page. Make sure you are entering the same credentials you registerred with. By clicking on the login button you should be set to use Washine. Watch the video below for further clarification")));
    Details details1 = new Details(paragraph1.getTitle(), paragraph1);

    HelpViewParagraph paragraph2 =
        new HelpViewParagraph("Community invitations", "videos/invitation_generate.mp4", "500px");

    paragraph2.setText(
        new Paragraph(
            new Text(
                "Washine lets you invite your friends and family to your washing community so they can participate to your group washings. Once you are logged in go to the invitations page, to create an invitation for a person write his/her name in the 'participant name' field (remember to choose a meaningful name) and click the generate button. You can then copy the code or the link as instructed and send it directly to the invitee (through messagings apps, email...). Watch the video below for further clarification")));
    Details details2 = new Details(paragraph2.getTitle(), paragraph2);

    HelpViewParagraph paragraph3 = new HelpViewParagraph("Log out", "videos/logout.mp4", "500px");
    paragraph3.setText(
        new Paragraph(
            "Whenever you are done creating and joining group washings or when you simply want to switch accounts you can disconnect from your current account by clicking the log out button in the bottom part of the menu. By doing that you'll have to log in again to use Washine's functionalities. Watch the video below for further clarification"));
    Details details3 = new Details(paragraph3.getTitle(), paragraph3);

    HelpViewParagraph paragraph4 =
        new HelpViewParagraph("Join a community", "videos/invitation_join.mp4", "500px");
    paragraph4.setText(
        new Paragraph(
            "If you want to join people's group washings you first need to join their washine community. After receinving their invitation code go to the invitations page, paste the code and enter their name in the specific fields in the top part of the page and click the join button. Remember to enter a name you've never used before to save another person's community and to use the right code. After seing the success notification you should be set. Watch the video below for further clarification"));
    Details details4 = new Details(paragraph4.getTitle(), paragraph4);

    HelpViewParagraph paragraph5 =
        new HelpViewParagraph(
            "Create, update or delete a washing", "videos/create_and_update_washing.mp4", "500px");
    paragraph5.setText(
        new Paragraph(
            "To organize your group washing, once your community is set, go to the my machine page. There you'll have to fill the form (that appears by clicking on the create a washing button) with essential informations to schedule the washing: the date and time the washing will take place, the initial load for your laundry, the load ceiling, the time in days that the washing will remain visible to the community members and all the washing characteristics. In addition to that you can also add some optional information to your washing by filling the form that is reveiled when clicking the 'non required otions' button. When you're done, to make your washing joinable by your community, click the create washing button again. In this page you can always check all of your scheduled washings' informations (date and time, number of participants, available load...), update them or, if you changed your mind, delete  the scheduled washing. Watch the video below for further clarification"));
    Details details5 = new Details(paragraph5.getTitle(), paragraph5);

    HelpViewParagraph paragraph6 =
        new HelpViewParagraph("Change credentials", "videos/newemail.mp4", "500px");
    paragraph6.setText(
        new Paragraph(
            "If you want to change your email or password for any reason, you can do that by going to the my credentials view. To change your account's email enter the new one in the specific field in the top part of the page and click the change my email button (make sure to enter an email that has never been used for Washine). To change your password, enter the new one in the appropriate field at the bottom of the page and repeat it a second time in the field alongside, then click on the button that says change my password. Remember to enter a password that is at least 8 characters long. After seeing the success notification your email or password should be updated. Watch the video below for further clarification"));
    Details details6 = new Details(paragraph6.getTitle(), paragraph6);

    HelpViewParagraph paragraph7 =
        new HelpViewParagraph("Handle community", "videos/mycommunity_remove.mp4", "500px");
    paragraph7.setText(
        new Paragraph(
            "You can see all your Washine community members in the my community page. There you can see the name you saved them with and you can also remove a member by checking the chekbox next to the name of the person you want to remove and clicking the remove button.  Watch the video below for further clarification"));
    Details details7 = new Details(paragraph7.getTitle(), paragraph7);

    getContent().add(details1, details2, details3, details4, details5, details6, details7);
  }
}
