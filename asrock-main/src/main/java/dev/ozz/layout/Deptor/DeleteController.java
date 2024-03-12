package dev.ozz.layout.Deptor;

import dev.ozz.App;
import dev.ozz.core.model.Debtor;
import dev.ozz.core.model.Loan_Info;
import dev.ozz.server.DebtorDAO;
import dev.ozz.server.Loan_InfoDAO;
import dev.ozz.service.ui.Modal;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class DeleteController {
    @FXML
    private Label text;

    @FXML
    private void handleExit() {
        Modal.close(app);
    }

    @FXML
    private void handledelete() {
        app.getLoan_InfoMasterList().forEach(loaninfo -> {
            if(debtor.getDebtorID()==loaninfo.getDebtorID().getDebtorID()){
                app.getLoan_InfoMasterList().remove(loaninfo);
                Loan_InfoDAO.remove(loaninfo);
            }
          

        });
        app.getDeptorMasterList().remove(debtor);
        DebtorDAO.remove(debtor);
       

        Modal.close(app);
    }

    private App app;
    private Debtor debtor;

    public void load(App app, Debtor debtor) {
        this.app = app;
        this.debtor = debtor;
        _load_fields();
        _load_bindings();
        _load_listeners();
    }

    private void _load_fields() {
        text.setText(debtor.getfullname());

    }

    private void _load_bindings() {
    }

    private void _load_listeners() {
    }

}
