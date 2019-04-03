/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Utilities.HtmlAndSQL.toHtml;
import static database.databaseConnect.Connect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Code;

/**
 *
 * @author dylan
 */
public class ReturnCodes {
    
    public List<Code> codes() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection myCon = Connect();
        Statement myStatement = myCon.createStatement();
        ResultSet myResSet = myStatement.executeQuery
        ("SELECT * FROM app.CODE");
        
        
        List<Code> codes = new ArrayList<>();
        while (myResSet.next()) {
            Code code = new Code();
            code.setCodedescription(myResSet.getString("codedescription"));
            code.setCodeid(myResSet.getInt("codeid"));
            code.setCodename(myResSet.getString("codename"));
            code.setCodeentry(toHtml(myResSet.getString("codeentry")));
            codes.add(code);
        }
        return codes;

    }
}
