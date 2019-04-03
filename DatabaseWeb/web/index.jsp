<%-- 
    Document   : index
    Created on : 1-Apr-2019, 7:03:39 PM
    Author     : User
--%>
<%@page import="javax.swing.text.Document"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URL"%>
<%@page import="models.Category"%>
<%@page import="java.util.List"%>
<%@page import="models.Code"%>
<%@page import="database.databaseConnect"%>
<%@page import="Controller.*"%>
<%@page import="Utilities.HtmlAndSQL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    CreateTables create = new CreateTables();
    ReturnCategories RetCat = new ReturnCategories();
    ReturnCodeWithCategory RetCodeWithCat = new ReturnCodeWithCategory();
    ReturnCodes RetCode = new ReturnCodes();
    Code chosenCode = new Code();
    create.createTables();
    List<Code> codesWithCategoriesList = RetCodeWithCat.codesWithCategories();
    List<Code> codeList = RetCode.codes();
    List<Category> categoriesList = RetCat.categories();
    String passThis = request.getParameter("subject");
    String passSearch = request.getParameter("updateSearch");

%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Components</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" media="screen" href="http://localhost:8084/DatabaseWeb/main.css">
        <script src="main.js"></script>
        <style>
        </style>
    </head>

    <body>
        <header>
            <h1 id="header-title">Header Title</h1>
            <h1 id="header-phrase">Header Phrase</h1>
        </header>
        <nav id="nav-menu">
            <a class="nav-button" href="" target="">HTML</a>
            <a class="nav-button" href="" target="">CSS</a>
            <a class="nav-button" href="" target="">Java</a>
            <a class="nav-button" href="" target="">JavaScript</a>
        </nav>
        <main>
            <section id="left-side">
                <form id="search" method='post'>

                    <input name='updateSearch' type="text" placeholder="Search.." method='post' onchange='if (this.value != 0) {
                                this.form.submit();
                            }'>

                    <img src='http://localhost:8084/DatabaseWeb/images/search.png'>
                </form>
                <%                    for (Category category : categoriesList) {
                        if (passSearch == null) {
                            out.print("<h2 class='categories'>");
                            out.print("<img src='http://localhost:8084/DatabaseWeb/images/category.png' alt='category'>");
                            out.print(category.getCategoryname());
                            out.print("</h2>");
                        }
                        for (Code code : codesWithCategoriesList) {
                            if (passSearch == null) {
                                if (code.getCategory().getCategoryname().equals(category.getCategoryname().toString())) {
                                    out.print("<hr>");
                                    out.print("<form class='option-container' method='post'>");
                                    out.print("<button name='subject' class='option' ");
                                    out.print("type='submit' value=" + code.getCodeid() + ">" + code.getCodename());
                                    out.print("</button></form>");
                                    try {
                                        if (code.getCodeid() == Integer.parseInt(passThis)) {
                                            chosenCode = code;
                                        }

                                    } catch (Exception e) {
                                    }
                                }
                            } else {
                                if (code.getCodename().toLowerCase().startsWith(passSearch) && code.getCategory().getCategoryname().equals(category.getCategoryname().toString())) {
                                    out.print("<hr>");
                                    out.print("<form class='option-container' method='post'>");
                                    out.print("<button name='subject' class='option' ");
                                    out.print("type='submit' value=" + code.getCodeid() + ">" + code.getCodename());
                                    out.print("</button></form>");
                                    try {
                                        if (code.getCodeid() == Integer.parseInt(passThis)) {
                                            chosenCode = code;
                                        }

                                    } catch (Exception e) {
                                    }
                                }
                            }
                        }
                    }
                %>
            </section>

            <section id="right-side">
                <h1 class="title"><%out.print(chosenCode.getCodename());%></h1>
                <h2 class="description">
                    <%out.print(chosenCode.getCodedescription());%>
                </h2>
                <textarea rows="4" cols="50" class="codeArea">
                    <%out.print(chosenCode.getCodeentry());%>
                </textarea>
                <br>
                <button class='button' onclick="">Run Code</button>
            </section>
        </main>
    </body>

</html>
