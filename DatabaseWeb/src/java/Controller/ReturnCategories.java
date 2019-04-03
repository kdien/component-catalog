/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static database.databaseConnect.Connect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Category;

/**
 *
 * @author dylan
 */
public class ReturnCategories {

    public List<Category> categories() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection myCon = Connect();
        Statement myStatement = myCon.createStatement();
        ResultSet myResSet = myStatement.executeQuery("SELECT * FROM app.category");
        List<Category> categories = new ArrayList<>();
        while (myResSet.next()) {
            Category category = new Category();
            category.setCategoryname(myResSet.getString("categoryname"));
            categories.add(category);
        }
        return categories;

    }
}
