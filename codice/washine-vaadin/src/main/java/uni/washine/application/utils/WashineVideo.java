package uni.washine.application.utils;

import com.vaadin.flow.component.*;

@Tag("video")
public class WashineVideo extends HtmlContainer
    implements ClickNotifier<com.vaadin.flow.component.html.Image> {

  private static final PropertyDescriptor<String, String> srcDescriptor =
      PropertyDescriptors.attributeWithDefault("src", "");

  public WashineVideo() {
    super();
    getElement().setProperty("controls", true);
  }

  public WashineVideo(String src) {
    setSrc(src);
    getElement().setProperty("controls", true);
  }

  public String getSrc() {
    return get(srcDescriptor);
  }

  public void setSrc(String src) {
    set(srcDescriptor, src);
  }
}
