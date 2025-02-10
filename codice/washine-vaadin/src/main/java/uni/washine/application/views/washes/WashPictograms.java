package uni.washine.application.views.washes;
import com.vaadin.flow.component.popover.Popover;
import com.vaadin.flow.component.popover.PopoverPosition;
import com.vaadin.flow.component.popover.PopoverVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;

import java.util.List;
import com.vaadin.flow.data.renderer.LitRenderer;

public class WashPictograms extends VerticalLayout {

    public WashPictograms() {
    	  Button button = new Button();
    	  button.setText("Clothes labels meanings");
    	  button.setWidth("min-content");
    	  button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
          button.setAriaLabel("Clothes labels meanings");

          Popover popover = new Popover();
          popover.setTarget(button);
          popover.setWidth("300px");
          popover.addThemeVariants(PopoverVariant.ARROW,
                  PopoverVariant.LUMO_NO_PADDING);
          popover.setPosition(PopoverPosition.BOTTOM);
          popover.setAriaLabelledBy("notifications-heading");
          
          
        Grid<GalleryItem> grid = new Grid<>(GalleryItem.class);
        grid.setItems(getGalleryItems());
        grid.setColumns( "description");
        grid.addColumn(LitRenderer
        	       .<GalleryItem>of("<img src=${item.imageUrl}>").withProperty("imageUrl", GalleryItem::getImageUrl)
        	).setHeader("Symbol").setWidth("40px");
       // grid.getColumnByKey("image").setHeader("Image").setRenderer(new LitRenderer<>(item -> item.getImageUrl()));
        grid.getColumnByKey("description").setHeader("Description");
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);
      popover.add(grid);

        add(button, popover);
        
    }
    //TODO: in production save the images to use them as static asset
    private List<GalleryItem> getGalleryItems() {
        return List.of(
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Waschen.svg/40px-Waschen.svg.png", "Washing symbol"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Waschen_30.svg/40px-Waschen_30.svg.png", "Wash at or below 30°C (86°F) (US, 1 dot, ●)"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Waschen_40.svg/40px-Waschen_40.svg.png", "Wash at or below 40°C (104°F) (US, 2 dots, ●●)"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/Waschen_50.svg/40px-Waschen_50.svg.png", "Wash at or below 50°C (122°F) (US, 3 dots, ●●●)"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Waschen_60.svg/40px-Waschen_60.svg.png", "Wash at or below 60°C (140°F) (US, 4 dots, ●●●●)"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Handw%C3%A4sche.svg/40px-Handw%C3%A4sche.svg.png", "Hand wash"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Nicht_waschen.svg/40px-Nicht_waschen.svg.png", "Do not wash"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Bleichen.svg/40px-Bleichen.svg.png", "Bleaching symbol (allowed for both chlorine and non-chlorine bleach)"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Bleichen_mit_chlor.svg/40px-Bleichen_mit_chlor.svg.png", "Bleaching with chlorine allowed (obsolete)"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Sauerstoffbleichen.svg/40px-Sauerstoffbleichen.svg.png", "Non-chlorine bleach when needed"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Nicht_bleichen_v2.svg/40px-Nicht_bleichen_v2.svg.png", "Do not bleach"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Nicht_bleichen.svg/40px-Nicht_bleichen.svg.png", "Do not bleach"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Trommeltrocknen.svg/40px-Trommeltrocknen.svg.png", "Tumble drying symbol"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Trommeltrocknen_1.svg/40px-Trommeltrocknen_1.svg.png", "Tumble drying (low temperature)"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Trommeltrocknen_2.svg/40px-Trommeltrocknen_2.svg.png", "Tumble drying (normal)"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Nicht_trommeltrocknen.svg/40px-Nicht_trommeltrocknen.svg.png", "Do not tumble dry"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/6/61/Trocknen.svg/40px-Trocknen.svg.png", "Drying symbol"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Trocknen_%28leine%29.svg/40px-Trocknen_%28leine%29.svg.png", "Line dry"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Trocknen_%28liegend%29.svg/40px-Trocknen_%28liegend%29.svg.png", "Dry flat"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Trocknen_%28tropfnass%29.svg/40px-Trocknen_%28tropfnass%29.svg.png", "Drip dry"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Trocknen_%28schatten%29.svg/40px-Trocknen_%28schatten%29.svg.png", "Dry in the shade"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/Trocknen_%28leine_im_schatten%29.svg/40px-Trocknen_%28leine_im_schatten%29.svg.png", "Line dry in the shade"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Trocknen_%28liegend_im_schatten%29.svg/40px-Trocknen_%28liegend_im_schatten%29.svg.png", "Dry flat in shade"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/1/17/Trocknen_%28tropfnass_im_schatten%29.svg/40px-Trocknen_%28tropfnass_im_schatten%29.svg.png", "Drip dry in shade"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/B%C3%BCgeln.svg/40px-B%C3%BCgeln.svg.png", "Ironing symbol"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/B%C3%BCgeln_1.svg/40px-B%C3%BCgeln_1.svg.png", "Iron at low temperature"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b7/B%C3%BCgeln_2.svg/40px-B%C3%BCgeln_2.svg.png", "Iron at medium temperature"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/B%C3%BCgeln_3.svg/40px-B%C3%BCgeln_3.svg.png", "Iron at high temperature"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Nicht_b%C3%BCgeln.svg/40px-Nicht_b%C3%BCgeln.svg.png", "Do not iron"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Professionelle_reinigung.svg/40px-Professionelle_reinigung.svg.png", "Professional cleaning symbol"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/Professionelle_reinigung_%28F%29.svg/40px-Professionelle_reinigung_%28F%29.svg.png", "Dry clean, hydrocarbon solvent only (HCS)"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Professionelle_reinigung_%28F%29s.svg/40px-Professionelle_reinigung_%28F%29s.svg.png", "Gentle cleaning with hydrocarbon solvents"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Professionelle_reinigung_%28F%29ss.svg/40px-Professionelle_reinigung_%28F%29ss.svg.png", "Very gentle cleaning with hydrocarbon solvents"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Professionelle_reinigung_%28P%29.svg/40px-Professionelle_reinigung_%28P%29.svg.png", "Dry clean, tetrachloroethylene (PCE) only"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Professionelle_reinigung_%28P%29s.svg/40px-Professionelle_reinigung_%28P%29s.svg.png", "Gentle cleaning with PCE"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Professionelle_reinigung_%28P%29ss.svg/40px-Professionelle_reinigung_%28P%29ss.svg.png", "Very gentle cleaning with PCE"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Nicht_chemisch_reinigen.svg/40px-Nicht_chemisch_reinigen.svg.png", "Do not dry clean"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Professionelle_reinigung_%28W%29.svg/40px-Professionelle_reinigung_%28W%29.svg.png", "Professional wet cleaning"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Professionelle_reinigung_%28W%29s.svg/40px-Professionelle_reinigung_%28W%29s.svg.png", "Gentle wet cleaning"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Professionelle_reinigung_%28W%29ss.svg/40px-Professionelle_reinigung_%28W%29ss.svg.png", "Very gentle wet cleaning"),
            new GalleryItem("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c6/Nicht_nassreinigen.svg/40px-Nicht_nassreinigen.svg.png", "Do not wet clean")
        );
    }

    public static class GalleryItem {
        private String imageUrl;
        private String description;

        public GalleryItem(String imageUrl, String description) {
            this.imageUrl = imageUrl;
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getDescription() {
            return description;
        }
    }
}