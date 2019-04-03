/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Utilities.HtmlAndSQL.addApostrophe;
import static database.databaseConnect.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author dylan
 */
public class InsertCode {
    

    public String updatedData(String[] update) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection myCon = Connect();
        String updateDB = "INSERT INTO Code (codeName, codeDescription, codeEntry) VALUES ( '"
                + addApostrophe(update[0])
                + "', '"
                + addApostrophe(update[1])
                + "', '"
                + addApostrophe(update[2])
                + "', '"
                + addApostrophe(update[3])
                + "')";
        PreparedStatement myStatement = myCon.prepareStatement(updateDB);
        myStatement.executeUpdate();
        return updateDB;
    }

}
