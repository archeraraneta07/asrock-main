package dev.ozz.server;

import java.sql.SQLException;
import java.sql.Types;

import javax.sql.rowset.CachedRowSet;

import dev.ozz.App;
import dev.ozz.core.model.Debtor;
import dev.ozz.service.db.DBCommand;
import dev.ozz.service.db.DBParam;
import dev.ozz.service.db.DBService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DebtorDAO {
    private static final String TABLE = "debtor";
    private static final String DATABASE = "lending";

    public static int getLastId() {
        String sql = "SELECT MAX(debtorID) AS last_id FROM " + TABLE;
        CachedRowSet crs = DBService.QUERY(DATABASE, TABLE, sql, new DBParam[] {});

        try {
            if (crs.next())
                return crs.getInt("last_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;

    }

    public static ObservableList<Debtor> getMasterLsit(App app) throws SQLException {
        ObservableList<Debtor> list = FXCollections.observableArrayList();
        CachedRowSet crs = DBCommand.READ(DATABASE, TABLE, new DBParam[] {});

        while (crs.next()) {
            Debtor debtor = _data(crs, app);
            if (debtor != null) {
                list.add(debtor);
            }
        }

        return list;
    }

    public static void insert(Debtor debtor) {
        DBCommand.CREATE(DATABASE, TABLE, _params(debtor).toArray(new DBParam[] {}));
    }

    public static void remove(Debtor debtor) {
        DBCommand.DELETE(DATABASE, TABLE, new DBParam(Types.INTEGER, "debtorID", debtor.getDebtorID()));
    }

    public static void update(Debtor debtor) {
        DBCommand.UPDATE(DATABASE, TABLE, new DBParam(Types.INTEGER, "debtorID", debtor.getDebtorID()),
                _params(debtor).toArray(new DBParam[] {}));
    }

    private static Debtor _data(CachedRowSet crs, App app) throws SQLException {
        int debtorID = crs.getInt("debtorID");
        String firstName = crs.getString("firstName");
        String lastName = crs.getString("lastName");
        String middleName = crs.getString("middleName");
        String extensionName = crs.getString("extensionName");
        String email = crs.getString("email");
        String phoneNumber = crs.getString("phoneNumber");
        String address = crs.getString("address");
        int gender = crs.getInt("gender");
        String job = crs.getString("jobName");
        String tinid = crs.getString("tinid");
        long monthly_Income = crs.getLong("monthly_Income");
        return new Debtor(debtorID,
                firstName,
                lastName,
                middleName,
                extensionName,
                email,
                phoneNumber,
                address,
                gender,
                job,
                tinid,
                monthly_Income);
    }

    private static ObservableList<DBParam> _params(Debtor debtor) {
        ObservableList<DBParam> parameters = FXCollections.observableArrayList();
        parameters.add(new DBParam(Types.INTEGER, "debtorID", debtor.getDebtorID()));
        parameters.add(new DBParam(Types.VARCHAR, "firstName", debtor.getFirstname()));
        parameters.add(new DBParam(Types.VARCHAR, "lastName", debtor.getLastname()));
        parameters.add(new DBParam(Types.VARCHAR, "middleName", debtor.getMiddleName()));
        parameters.add(new DBParam(Types.VARCHAR, "extensionName", debtor.getExtensionName()));
        parameters.add(new DBParam(Types.VARCHAR, "email", debtor.getEmail()));
        parameters.add(new DBParam(Types.VARCHAR, "phoneNumber", debtor.getPhoneNumber()));
        parameters.add(new DBParam(Types.VARCHAR, "address", debtor.getAddress()));
        parameters.add(new DBParam(Types.INTEGER, "gender", debtor.getGender()));
        parameters.add(new DBParam(Types.VARCHAR, "jobName", debtor.getJobName()));
        parameters.add(new DBParam(Types.VARCHAR, "tinid", debtor.getTinid()));
        parameters.add(new DBParam(Types.BIGINT, "monthly_Income", debtor.getmonthly_income()));
        return parameters;
    }
}
