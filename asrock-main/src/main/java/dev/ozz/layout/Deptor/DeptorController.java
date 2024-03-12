package dev.ozz.layout.Deptor;

import java.io.IOException;

import dev.ozz.App;
import dev.ozz.core.model.Debtor;

import dev.ozz.service.ui.Modal;
import dev.ozz.service.ui.Modall;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DeptorController {

    @FXML
    private TextField debtors_searchField;

    @FXML
    private Button addDebtors;
    @FXML
    private Button editDebtor;
    @FXML
    private Button deleteDebtor;
    @FXML
    private TableView<Debtor> debtors_TableView;
    @FXML
    private TableColumn<Debtor, String> debtors_firstnameColumn;

    @FXML
    private TableColumn<Debtor, String> debtors_emailColumn;
    @FXML
    private TableColumn<Debtor, String> debtors_phonenumberColumn;
    @FXML
    private TableColumn<Debtor, String> debtors_addressColumn;

    private Debtor debtor;
    private FilteredList<Debtor> debtors_FilteredList;
    private SortedList<Debtor> debtors_SortedList;
    private App app;

    public void load(App app) {
        this.app = app;
        _load_fields();
        _load_bindings();
        _load_listeners();
    }

    private void _load_fields() {
        debtors_searchField.requestFocus();
        _deptors_fields();
        _add_deptors();
        _visible_buttons();
    }

    private void _load_bindings() {
    }

    private void _load_listeners() {

        _debtors_TableView();
        _debtors_searchField();

    }

    private void _debtors_searchField() {
        debtors_TableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            debtor = nv;
            _visible_buttons();
            _delete_edit(debtor);
        });
    }

    private void _visible_buttons() {
        editDebtor.setVisible(false);
        deleteDebtor.setVisible(false);
        if (debtors_TableView.getSelectionModel().getSelectedItem() != null) {
            editDebtor.setVisible(true);
            deleteDebtor.setVisible(true);
        }

    }

    private void _debtors_TableView() {
        debtors_searchField.textProperty().addListener((o, ov, nv) -> {
            debtors_FilteredList.setPredicate(debtor -> {
                if (nv == null || nv.isEmpty()) {
                    return true;
                }
                // lower case all possible
                String lowerCaseFilter = nv.toLowerCase();

                if (debtor.getFirstname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }

                return false;

            });

        });
    }

    private void _add_deptors() {
        addDebtors.setOnAction(ev -> {
            debtors_TableView.getSelectionModel().clearSelection();
            try {
                Modal.load_Add(app,debtor);
            } catch (IOException e) {

                e.printStackTrace();
            }
           
        });
    }

    private void _delete_edit(Debtor debtor) {

        editDebtor.setOnAction(ev -> {

            try {
                Modal.load_Add(app, debtor);
            } catch (IOException e) {
                e.printStackTrace();
            }
            debtors_TableView.getSelectionModel().clearSelection();
        });
        deleteDebtor.setOnAction(ev -> {
            try {
                Modall.load_delete(app, debtor);
            } catch (IOException e) {

                e.printStackTrace();
            }
            debtors_TableView.getSelectionModel().clearSelection();
        });
    }

    private void _deptors_fields() {

        debtors_firstnameColumn.setCellValueFactory(celldata -> celldata.getValue().fullnameProperty());
        debtors_emailColumn.setCellValueFactory(celldata -> celldata.getValue().emailProperty());
        debtors_phonenumberColumn.setCellValueFactory(celldata -> celldata.getValue().phoneNumberProperty());
        debtors_addressColumn.setCellValueFactory(celldata -> celldata.getValue().addressProperty());
        debtors_FilteredList = new FilteredList<>(app.getDeptorMasterList());
        debtors_SortedList = new SortedList<>(debtors_FilteredList);
        debtors_TableView.setItems(debtors_SortedList);

       
    }

    private void _TextFields() {
        // debtors_Firstnamefield.setOnAction(event -> {

        // debtors_LastNamefield.requestFocus();
        // });
        // debtors_LastNamefield.setOnAction(event -> {

        // debtors_Emailfield.requestFocus();
        // });

    }

    private void _TextFieldslisteners() {
        // debtors_PhoneNumberfield.textProperty().addListener((observable, oldValue,
        // newValue) -> {
        // if (!newValue.matches("\\d*")) {
        // debtors_PhoneNumberfield.setText(newValue.replaceAll("[^\\d]", ""));
        // }
        

        // });
        // debtors_MonthlyIncomefield.textProperty().addListener((observable, oldValue,
        // newValue) -> {
        // if (!newValue.matches("\\d*")) {
        // debtors_MonthlyIncomefield.setText(newValue.replaceAll("[^\\d]", ""));
        // }
        // if (newValue.length() > 11) {
        // debtors_MonthlyIncomefield.setText(oldValue);
        // }

        // });
        // debtors_Addressfield.textProperty().addListener((ob, ov, nv) -> {
        // String newValueWithoutSpaces = nv.replaceAll("\\s", "");
        // if (!nv.equals(newValueWithoutSpaces)) {
        // debtors_Addressfield.setText(newValueWithoutSpaces);
        // }
        // });
        // debtors_Emailfield.textProperty().addListener((ob, ov, nv) -> {
        // String newValueWithoutSpaces = nv.replaceAll("\\s", "");
        // if (!nv.equals(newValueWithoutSpaces)) {
        // debtors_Emailfield.setText(newValueWithoutSpaces);
        // }
        // });

    }
}
