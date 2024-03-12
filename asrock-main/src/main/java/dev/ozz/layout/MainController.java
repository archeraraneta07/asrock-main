package dev.ozz.layout;

import java.io.IOException;

import dev.ozz.App;

import dev.ozz.service.ui.Content;
import dev.ozz.service.ui.Modal;
import javafx.animation.TranslateTransition;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainController {
    // ---------------------pop_up----
    @FXML
    private VBox leftsideVBox;
    @FXML
    private AnchorPane leftisdeAnchorPane;
    @FXML
    private AnchorPane leftsidemove;
    @FXML
    private VBox rightsideVBox;
    @FXML
    private AnchorPane rightisdeAnchorPane;
    @FXML
    private AnchorPane rightsidemove;
    @FXML
    private StackPane middleStackpane;
    @FXML
    private Button debtorsButton;
    @FXML
    private Button loanoffersButton;
    @FXML
    private Button loandetailsButton;

    // ------------------load-------
    @FXML
    private ImageView imageView;

    private Image image;
    private App app;

    public void load(App app) {
        this.app = app;
        _load_fields();
        _load_bindings();
        _load_listeners();
    }

    private void _load_fields() {
        app.setapplicationBorderPaneRoot(middleStackpane);
        // --deptors--

        // -others-
        _popthings();
        _leftsideButton();
        try {

            Content.load_debtor(app);
            Modal.load_Login(app);

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private void _load_bindings() {
    }

    private void _load_listeners() {
        // --deprots--

    }

    // -----------------------------------------------------load-------------------------------------------------
    private void _popthings() {

        rightisdeAnchorPane.setVisible(false);
        leftisdeAnchorPane.setVisible(false);
        String FAVICON = App.class.getResource("assets/img/FAVICON.png").toExternalForm();
        image = new Image(FAVICON);
        imageView.setImage(image);
    }

    // ---------------------------------Button Left Side -------------------
    private void _leftsideButton() {

        loanoffersButton.setOnAction(ev -> {

            try {
                Content.load_loadoffers(app);
            } catch (IOException e) {

                e.printStackTrace();
            }
        });
        debtorsButton.setOnAction(ev -> {

            try {
                Content.load_debtor(app);
            } catch (IOException e) {

                e.printStackTrace();
            }
        });
        loandetailsButton.setOnAction(ev -> {

            try {
                Content.load_loandetails(app);
            } catch (IOException e) {

                e.printStackTrace();
            }
        });
    }

    // -------------------------------------------------------MovingTings--------------------------------------------------------

    @FXML
    private void handle_left_popup() {

        leftsideVBox.setVisible(true);
        leftisdeAnchorPane.setVisible(true);
        TranslateTransition x = new TranslateTransition();
        x.setNode(leftsidemove);
        x.setDuration(Duration.millis(500));
        x.setFromX(-210);
        x.setByX(210);

        x.play();

    }

    @FXML
    private void handle_left_reverse() {
        TranslateTransition y = new TranslateTransition();
        y.setByX(-210);
        TranslateTransition x = new TranslateTransition();
        x.setNode(leftsidemove);
        x.setDuration(Duration.millis(500));

        x.setByX(-210);

        x.play();

        x.setOnFinished(event -> {
            leftsideVBox.setVisible(false);
            leftisdeAnchorPane.setVisible(false);
        });

    }

    @FXML
    private void handle_Right_popup() {
        rightsideVBox.setVisible(true);
        rightisdeAnchorPane.setVisible(true);
        TranslateTransition x = new TranslateTransition();
        x.setNode(rightsidemove);
        x.setDuration(Duration.millis(500));
        x.setFromX(210);
        x.setByX(-210);

        x.play();
    }

    @FXML
    private void handle_Right_reverse() {
        TranslateTransition y = new TranslateTransition();
        y.setByX(210);
        TranslateTransition x = new TranslateTransition();
        x.setNode(rightsidemove);
        x.setDuration(Duration.millis(500));

        x.setByX(210);

        x.play();

        x.setOnFinished(event -> {
            rightsideVBox.setVisible(false);
            rightisdeAnchorPane.setVisible(false);
        });
    }

    public String toString(int Integer) {
        return String.valueOf(Integer);
    }

    public int toInteger(String str) {
        return Integer.valueOf(str);
    }
}
