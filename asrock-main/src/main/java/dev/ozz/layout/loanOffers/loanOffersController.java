package dev.ozz.layout.loanOffers;

import java.io.IOException;
import java.time.LocalDate;

import org.joda.money.Money;

import dev.ozz.App;
import dev.ozz.core.model.Debtor;
import dev.ozz.core.model.Loan_Info;

import dev.ozz.server.Loan_InfoDAO;
import dev.ozz.service.ui.Content;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class loanOffersController {
    @FXML
    private TextField searField;
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
    @FXML
    private Button doneButton;
    @FXML
    private TextField amountField;
    @FXML
    private TextField interestField;
    @FXML
    private TextField montstopayField;
    @FXML
    private TextField penaltyField;

    private App app;
    private static Debtor debtor;

    public void load(App app) {
        this.app = app;

        _load_fields();
        _load_bindings();
        _load_listeners();
    }

    private void _load_fields() {
        searField.requestFocus();
        _table();
        _doneButton();

    }

    private void _load_bindings() {
    }

    private void _load_listeners() {
        TableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            debtor = nv;

            _details();
        });
        _TextFieldslisteners();
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

        app.getDeptorMasterList().forEach(debtor -> {
            TableView.getItems().addAll(debtor);
        });

        app.getLoan_InfoMasterList().forEach(loaninfo -> {

            TableView.getItems().remove(loaninfo.getDebtorID());

        });

    }

    private void _doneButton() {

        doneButton.setOnAction(ev -> {
            Money amountmoney = parseMoney(amountField.getText());
            Money interestmoney = parseMoney(interestField.getText());
            Money penaltyMoney = parseMoney(penaltyField.getText());
            if (amountmoney != null && interestmoney != null && penaltyMoney != null
                    && montstopayField.getText() != null) {
                long amount = amountmoney.getAmountMinorLong();
                long interest = interestmoney.getAmountMinorLong();
                long penalty = penaltyMoney.getAmountMinorLong();
                // CurrencyUnit as=CurrencyUnit.of("PHP");
                // Money money1 = Money.ofMinor(as, amount1);
                // Money money2 = Money.ofMinor(as, amount2);
                // Money money4 = Money.ofMinor(as, amount4);

                int months = Integer.valueOf(montstopayField.getText());
                int asd = months * 30;
                LocalDate currentDate = LocalDate.now();
                LocalDate dateInDays = currentDate.plusDays(asd);

                Loan_Info newLoan_Info = new Loan_Info(debtor, currentDate, amount, months, interest,
                        penalty);

                Loan_InfoDAO.insert(newLoan_Info);
                app.getLoan_InfoMasterList().add(newLoan_Info);

            } else {
                System.out.println("error");
            }

        });

    }

    private void _TextFieldslisteners() {
        interestField.textProperty().addListener((observable, oldValue,
                newValue) -> {

            if (!newValue.matches("\\d{0,2}([\\.]\\d{0,2})?")) {
                interestField.setText(oldValue);
            }

        });
        penaltyField.textProperty().addListener((observable, oldValue,
                newValue) -> {

            if (!newValue.matches("\\d{0,2}([\\.]\\d{0,2})?")) {
                penaltyField.setText(oldValue);
            }

        });
        montstopayField.textProperty().addListener((observable, oldValue,
                newValue) -> {

            if (!newValue.matches("\\d{0,2}")) {
                montstopayField.setText(oldValue);
            }

        });
        amountField.textProperty().addListener((observable, oldValue,
                newValue) -> {

            if (!newValue.matches("\\d{0,10}([\\.]\\d{0,2})?")) {
                amountField.setText(oldValue);
            }

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
