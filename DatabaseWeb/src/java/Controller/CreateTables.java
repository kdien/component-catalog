/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static database.databaseConnect.*;

/**
 *
 * @author dylan
 */
public class CreateTables {

    public String createTables() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            Connection myCon = Connect();
            String categoryTable
                    = "Create table app.category \n"
                    + "(\n"
                    + "categoryid integer not null \n"
                    + "generated always as identity\n"
                    + "(start with 1, increment by 1), \n"
                    + "categoryname varchar(50) not null,\n"
                    + "primary key(categoryid))";
            PreparedStatement categoryStatement = myCon.prepareStatement(categoryTable);
            categoryStatement.executeUpdate();
            String codeTable
                    = "CREATE TABLE app.CODE \n"
                    + "(\n"
                    + "CODEID INTEGER NOT NULL \n"
                    + "GENERATED ALWAYS AS IDENTITY \n"
                    + "(START WITH 1, INCREMENT BY 1), \n"
                    + "\n"
                    + "CODENAME VARCHAR (50) NOT NULL,\n"
                    + "CODEDESCRIPTION VARCHAR (500) NOT NULL, \n"
                    + "CODEENTRY VARCHAR (5000) NOT NULL, \n"
                    + "PRIMARY KEY (CODEID),"
                    + "category int not null,"
                    + "FOREIGN KEY (category) REFERENCES app.category(categoryid))";
            PreparedStatement codeStatement = myCon.prepareStatement(codeTable);
            codeStatement.executeUpdate();
            return "Success";
        } catch (Exception e) {
            System.out.print(e);
            return "fuck";
        }
    }

}
