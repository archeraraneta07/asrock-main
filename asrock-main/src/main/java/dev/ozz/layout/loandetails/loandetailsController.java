package dev.ozz.layout.loandetails;

import org.joda.money.Money;

import dev.ozz.App;
import dev.ozz.core.model.Debtor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class loandetailsController {
    @FXML
    private TextField  searchField;
    @FXML
    private TableView<Debtor> TableView;
    @FXML
    private TableColumn<Debtor, String> fullnameColumn;
    @FXML
    private TableColumn<Debtor, String> emailColumn;
    @FXML
    private TableColumn<Debtor, String> phonenumberColumn;
    @FXML
    private TableColumn<Debtor, String> addressColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phonenumberLabel;
    @FXML
    private Label addressLabel;

    private App app;
    private Debtor debtor;

    public void load(App app) {
        this.app = app;

        _load_fields();
        _load_bindings();
        _load_listeners();
    }

    private void _load_fields() {
        searchField.requestFocus();
        _table();

    }

    private void _load_bindings() {
    }

    private void _load_listeners() {
        TableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            debtor = nv;

            _details();
        });
    }

    private void _details() {
        String fullname;
        String email;
        String phonenumber;
        String address;
        if (debtor == null) {
            fullname = "FullName";
            email = "Email";
            phonenumber = "PhoneNumber";
            address = "Address";
        } else {
            fullname = String.format("%s, %s", debtor.getFirstname(), debtor.getLastname());
            email = debtor.getEmail();
            phonenumber = String.valueOf(debtor.getPhoneNumber());
            address = debtor.getAddress();
        }
        nameLabel.setText(fullname);
        emailLabel.setText(email);
        phonenumberLabel.setText(phonenumber);
        addressLabel.setText(address);
    }

    private void _table() {
        fullnameColumn.setCellValueFactory(celldata -> celldata.getValue().fullnameProperty());
        emailColumn.setCellValueFactory(celldata -> celldata.getValue().emailProperty());
        phonenumberColumn.setCellValueFactory(celldata -> celldata.getValue().phoneNumberProperty());
        addressColumn.setCellValueFactory(celldata -> celldata.getValue().addressProperty());

        app.getLoan_InfoMasterList().forEach(loaninfo -> {
            TableView.getItems().addAll(loaninfo.getDebtorID());
        });
    }

    private Money parseMoney(String input) {
        Money money = null;
        try {
            money = Money.parse("PHP " + input); // Assuming input is in USD currency
        } catch (IllegalArgumentException ex) {
            // Handle invalid input here if needed
        }
        return money;
    }
}
