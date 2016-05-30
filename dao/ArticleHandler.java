/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.PanelInterface;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Article;
import model.Task;

/**
 *
 * @author pdyst
 */
public class ArticleHandler {
    private static ArticleHandler instance;
    
    private ArticleHandler() {
        
    }
    
      public ArrayList<PanelInterface> getArticles(int caseKonsNmb) throws SQLException {
        ArrayList<PanelInterface> articles = new ArrayList<>();
        String statement;
        statement = "SELECT * FROM objects WHERE caseKonsNmb = '" + caseKonsNmb + "';";
        ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
        while (rs.next()) {
            ArrayList<Task> tasks = TaskHandler.getInstance().getTasks(rs.getInt("object_id"));
            Article article = new Article(rs.getString("objectName"), caseKonsNmb, rs.getString("objectType"), rs.getInt("konsNr"), tasks);
            articles.add(article);
        }
        return articles;
    }
    
    public static ArticleHandler getInstance() {
        if (instance == null) {
            instance = new ArticleHandler();
        }
        return instance;
    }
}
