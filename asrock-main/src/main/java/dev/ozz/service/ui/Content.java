package dev.ozz.service.ui;

import java.io.IOException;

import dev.ozz.App;
import dev.ozz.layout.MainController;
import dev.ozz.layout.RootController;
import dev.ozz.layout.Deptor.DeptorController;
import dev.ozz.layout.loanOffers.loanOffersController;
import dev.ozz.layout.loandetails.loandetailsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Content {

    // Favicon
    private static final String FAVICON = App.class.getResource("assets/img/FAVICON.png").toExternalForm();
    // Css Stylesheets->
    public static String COLOR = "migraine.css";

    public static String CSS_THEME = App.class.getResource("assets/css/" + COLOR).toExternalForm();

    // Masser.css
    public static FXMLLoader load(String uri) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("layout/" + uri + ".fxml"));
        return loader;
    }

    // APPLICATION STAGE->
    public static void intialize_application(App app) throws IOException {
        Stage applicationStage = app.getApplicationStage();
        applicationStage.setTitle("Lending Management App");

        // applicationStage.setResizable(false);

        // Img-->
        applicationStage.getIcons()
                .add(new Image(FAVICON));

    }

    // CUSTOM LOADERS -->
    public static void load_ROOT(App app) throws IOException {
        intialize_application(app);
        FXMLLoader loader = load("ROOT");

        Parent container = loader.load();
        Scene scene = new Scene(container);
        scene.getStylesheets().addAll(CSS_THEME);
        app.getApplicationStage().setScene(scene);
        app.getApplicationStage().show();

        RootController controller = loader.getController();
        controller.load(app);

    }

    public static void load_Loan(App app) throws IOException {

        FXMLLoader loader = load("MAIN");
        Parent container = loader.load();
        app.getApplicationRoot().getChildren().add(container);

        MainController controller = loader.getController();

        controller.load(app);
    }

    public static void load_loadoffers(App app) throws IOException {

        FXMLLoader loader = load("loanOffers/loanoffers");
        Parent container = loader.load();
        app.getapplicationBorderPaneRoot().getChildren().clear();
        app.getapplicationBorderPaneRoot().getChildren().add(container);

        loanOffersController controller = loader.getController();

        controller.load(app);
    }

    public static void load_loandetails(App app) throws IOException {
        FXMLLoader loader = load("loandetails/loandetails");
        Parent container = loader.load();
        app.getapplicationBorderPaneRoot().getChildren().clear();
        app.getapplicationBorderPaneRoot().getChildren().add(container);

        loandetailsController controller = loader.getController();

        controller.load(app);
    }

    public static void load_debtor(App app) throws IOException {
        FXMLLoader loader = load("Deptor/Deptor");
        Parent container = loader.load();
        app.getapplicationBorderPaneRoot().getChildren().clear();
        app.getapplicationBorderPaneRoot().getChildren().add(container);

        DeptorController controller = loader.getController();

        controller.load(app);
    }

}
