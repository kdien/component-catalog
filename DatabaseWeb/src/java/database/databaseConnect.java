/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static Utilities.HtmlAndSQL.addApostrophe;
import static Utilities.HtmlAndSQL.toHtml;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import models.Code;

/**
 *
 * @author User
 */
public class databaseConnect {

    public static void main(String[] args) {

    }

    public static Connection Connect() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Connection myCon = null;
        //Including Database
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        //Connecting to database
        myCon = DriverManager.getConnection("jdbc:derby://localhost:1527/myDatabase");
        //Creating a statement object from the connection
        return myCon;
    }

}
