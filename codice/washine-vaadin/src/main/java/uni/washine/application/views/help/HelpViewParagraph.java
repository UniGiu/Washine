package uni.washine.application.views.help;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import uni.washine.application.utils.WashineVideo;

public class HelpViewParagraph extends Composite<VerticalLayout> {
  public H2 title;
  Anchor link;
  Paragraph paragraph;
  WashineVideo video;

  public HelpViewParagraph(String paragraphTitle, String videoUrl, String videoWidth) {
    getContent().setWidth("100%");
    getContent().getStyle().set("flex-grow", "1");
    title = new H2(paragraphTitle);
    paragraph = new Paragraph();
    video = new WashineVideo();
    video.setWidth(videoWidth);
    getContent().add(title, paragraph, video);
  }

  public void setAnchor(String ref, String name) {
    link = new Anchor(ref, name);
  }

  public void setText(Paragraph p) {
    paragraph.add(p);
  }

  public Anchor getAnchor() {
    return this.link;
  }
}
