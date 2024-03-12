package dev.ozz;

import java.io.IOException;
import java.sql.SQLException;

import dev.ozz.core.model.Debtor;
import dev.ozz.core.model.Loan_Info;
import dev.ozz.core.model.Owner;
import dev.ozz.server.DebtorDAO;
import dev.ozz.server.Loan_InfoDAO;
import dev.ozz.server.OwnerDAO;
import dev.ozz.service.ui.Content;
import javafx.application.Application;
import javafx.collections.ObservableList;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    private Stage applicationStage;
    private StackPane applicationRoot;
    private StackPane applicationBorderPaneRoot;
    private ObservableList<Owner> LoginMasterList;
    private ObservableList<Debtor>deptorMasterList;
    private ObservableList<Loan_Info>loan_InfoMasterList;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.applicationStage = primaryStage;
        _load_resources();
        _initialize();
    }

    private void _initialize() throws IOException {
      Content.load_ROOT(this);
      Content.load_Loan(this);
    }

    private void _load_resources() throws SQLException {
        LoginMasterList = OwnerDAO.getList(this);
        deptorMasterList=DebtorDAO.getMasterLsit(this);
        loan_InfoMasterList=Loan_InfoDAO.getMasterList(this);
    }

    public Stage getApplicationStage() {
        return applicationStage;
    }

    public StackPane getApplicationRoot() {
        return applicationRoot;
    }
    public StackPane getapplicationBorderPaneRoot(){
        return applicationBorderPaneRoot;
    }
    public ObservableList<Owner> getLoginMasterList() {
        return LoginMasterList;
    }
    public ObservableList<Debtor> getDeptorMasterList() {
        return deptorMasterList;
    }
    public ObservableList<Loan_Info>getLoan_InfoMasterList(){
        return loan_InfoMasterList;
    }
   
    public void setApplicationRoot(StackPane applicationRoot) {
        this.applicationRoot = applicationRoot;
    }
    public void setapplicationBorderPaneRoot(StackPane applicationBorderPaneRoot) {
        this.applicationBorderPaneRoot = applicationBorderPaneRoot;
    }
    public static void main(String[] args) {
        launch(args);
    }

}
