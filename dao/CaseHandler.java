/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.PanelInterface;
import java.sql.PreparedStatement;
import model.Case;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Article;
import model.Costumer;
import model.Employee;
import model.Log;

/**
 *
 * @author pdyst
 */
public class CaseHandler {

    private static CaseHandler instance;
    Calendar cal;
    DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");

    private CaseHandler() {
        cal = Calendar.getInstance();
    }

//    public ArrayList<Case> getCases() throws SQLException {
//        ArrayList<Case> cases = new ArrayList<>();
//        String statement;
//        statement = "SELECT * FROM cases;";
//        ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
//        while (rs.next()) {
//            Costumer costumer = CostumerHandler.getInstance().getCostumer(rs.getInt("costumer_id"));
//            ArrayList<PanelInterface> articles = ArticleHandler.getInstance().getArticles(rs.getInt("konsNr"));
//            ArrayList<Log> logs = LogHandler.getInstance().getCaseLogs(rs.getInt("log_id"));
//            Case c = new Case(rs.getInt("konsNr"), rs.getInt("offerNmb"), rs.getString("caseName"), rs.getString("description"),
//                    articles, rs.getBoolean("finished"), rs.getDate("lastUpdated"), rs.getDate("createdAt"), costumer, logs);
//            cases.add(c);;
//        }
//        rs.close();
//        return cases;
//    }
    public ArrayList<PanelInterface> getCasesNewest() throws SQLException {
        ArrayList<PanelInterface> cases = new ArrayList<>();
        String statement = "SELECT * FROM cases ORDER BY lastUpdated DESC LIMIT 10;";
        ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
        while (rs.next()) {
            Case c = new Case(rs.getInt("case_id"), rs.getInt("konsNr"), rs.getInt("offerNmb"), rs.getString("caseName"), rs.getString("description"),
                    null, rs.getBoolean("finished"), rs.getDate("lastUpdated"), rs.getDate("createdAt"), null, null, null);
            cases.add(c);
        }
        rs.close();
        for (int i = 0; i < cases.size(); i++) {
            Case aCase = (Case) cases.get(i);
            Costumer costumer = CostumerHandler.getInstance().getCostumer(getCustomerId(aCase));
            aCase.setCustomer(costumer);
            ArrayList<PanelInterface> articles = ArticleHandler.getInstance().getArticles(aCase);
            aCase.setArticles(articles);
            ArrayList<Log> logs = LogHandler.getInstance().getCaseLogs(getLogId(aCase));
            aCase.setLogs(logs);
            ArrayList<PanelInterface> caseResponsible = EmployeeHandler.getInstance().getCaseResponsibles(aCase);
            aCase.setCaseResponsible(caseResponsible);
        }
        return cases;
    }

    public ArrayList<PanelInterface> getMyCases(Employee e) throws SQLException {
        ArrayList<PanelInterface> cases = new ArrayList<>();
        int employeeID = e.getEmployeeID();
        PreparedStatement ps = null;
        String selectSQL = "SELECT * FROM myCases LEFT JOIN cases ON myCases.cases_id = cases.case_id WHERE employee_id = ?";
        ps = DBHandler.getInstance().conn.prepareStatement(selectSQL);
        ps.setInt(1, employeeID);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Case c = new Case(rs.getInt("case_id"), rs.getInt("konsNr"), rs.getInt("offerNmb"), rs.getString("caseName"), rs.getString("description"),
                    null, rs.getBoolean("finished"), rs.getDate("lastUpdated"), rs.getDate("createdAt"), null, null, null);
            cases.add(c);
        }
        rs.close();
        for (int i = 0; i < cases.size(); i++) {
            Case aCase = (Case) cases.get(i);
            Costumer costumer = CostumerHandler.getInstance().getCostumer(getCustomerId(aCase));
            aCase.setCustomer(costumer);
            ArrayList<PanelInterface> articles = ArticleHandler.getInstance().getArticles(aCase);
            aCase.setArticles(articles);
            ArrayList<Log> logs = LogHandler.getInstance().getCaseLogs(getLogId(aCase));
            aCase.setLogs(logs);
            ArrayList<PanelInterface> caseResponsible = EmployeeHandler.getInstance().getCaseResponsibles(aCase);
            aCase.setCaseResponsible(caseResponsible);
        }
        return cases;
    }

    public int getLogId(Case aCase) {
        int result = -1;
        try {
            String statement = "SELECT log_id FROM cases WHERE konsNr = " + aCase.getKonsNmb() + ";";
            ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                result = rs.getInt("log_id");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int getCustomerId(Case aCase) {
        int result = -1;
        try {
            String statement = "SELECT costumer_id FROM cases WHERE konsNr = " + aCase.getKonsNmb() + ";";
            ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                result = rs.getInt("costumer_id");
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<PanelInterface> getFinishedCases() throws SQLException {
        ArrayList<PanelInterface> cases = new ArrayList<>();
        String statement = "SELECT * FROM cases WHERE finished = 1;";
        ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
        while (rs.next()) {
            Case c = new Case(rs.getInt("case_id"), rs.getInt("konsNr"), rs.getInt("offerNmb"), rs.getString("caseName"), rs.getString("description"),
                    null, rs.getBoolean("finished"), rs.getDate("lastUpdated"), rs.getDate("createdAt"), null, null, null);
            cases.add(c);
        }
        rs.close();
        for (int i = 0; i < cases.size(); i++) {
            Case aCase = (Case) cases.get(i);
            Costumer costumer = CostumerHandler.getInstance().getCostumer(getCustomerId(aCase));
            aCase.setCustomer(costumer);
            ArrayList<PanelInterface> articles = ArticleHandler.getInstance().getArticles(aCase);
            aCase.setArticles(articles);
            ArrayList<Log> logs = LogHandler.getInstance().getCaseLogs(getLogId(aCase));
            aCase.setLogs(logs);
            ArrayList<PanelInterface> caseResponsible = EmployeeHandler.getInstance().getCaseResponsibles(aCase);
            aCase.setCaseResponsible(caseResponsible);
        }
        return cases;
    }

    public ArrayList<PanelInterface> searchCases(int konsNmb, String caseName) throws SQLException {
        ArrayList<PanelInterface> cases = new ArrayList<>();
//        PreparedStatement ps = null;
//	String selectSQL = "SELECT * FROM cases WHERE konsNr = ? AND caseName LIKE '?%'";
//        ps = DBHandler.getInstance().conn.prepareStatement(selectSQL);
//        ps.setInt(1, konsNmb);
//        ps.setString(2, caseName);
//        ResultSet rs = ps.executeQuery();
        String statement = "SELECT * FROM cases WHERE konsNr = "
                + konsNmb + " OR caseName LIKE '" + caseName + "%';";
        ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
        while (rs.next()) {
            Case c = new Case(rs.getInt("case_id"), rs.getInt("konsNr"), rs.getInt("offerNmb"), rs.getString("caseName"), rs.getString("description"),
                    null, rs.getBoolean("finished"), rs.getDate("lastUpdated"), rs.getDate("createdAt"), null, null, null);
            cases.add(c);
        }
        rs.close();
        for (int i = 0; i < cases.size(); i++) {
            Case aCase = (Case) cases.get(i);
            Costumer costumer = CostumerHandler.getInstance().getCostumer(getCustomerId(aCase));
            aCase.setCustomer(costumer);
            ArrayList<PanelInterface> articles = ArticleHandler.getInstance().getArticles(aCase);
            aCase.setArticles(articles);
            ArrayList<Log> logs = LogHandler.getInstance().getCaseLogs(getLogId(aCase));
            aCase.setLogs(logs);
            ArrayList<PanelInterface> caseResponsible = EmployeeHandler.getInstance().getCaseResponsibles(aCase);
            aCase.setCaseResponsible(caseResponsible);
        }
        return cases;

    }

    //Skal benyttes, når man trykker sig ind på et nyt panel via rediger knappen
    public void editCase(Case c) throws SQLException {
        String stmt = "SET autocommit = 0;";
        String stmt1 = "begin ";
        String stmt2 = "update cases set caseName = '" + c.getCaseName() + "', lastUpdated = '"
                + dateFormat.format(cal.getTime()) + "' where konsNr = " + c.getKonsNmb() + "; ";
        String stmt3 = "end;";
        System.out.println(stmt1 + "\n" + stmt2 + "\n" );
        DBHandler.getInstance().conn.createStatement().executeUpdate(stmt);
        DBHandler.getInstance().conn.createStatement().executeUpdate(stmt1);
        DBHandler.getInstance().conn.createStatement().executeUpdate(stmt2);
//        DBHandler.getInstance().conn.createStatement().executeUpdate(stmt3);
    }

    /*CustomerHandler og ArticleHandler der kalder en saveCustomer(Costumer customer) 
    og saveArticles(ArrayList<Article> articles) inde i saveCase(Case c). En getter henter customerid fra
    det Costumer objekt, som Case objektet indeholder, da kunden sagtens kan optræde 2 gange og ikke
    må have 2 forskellige customer id'er, hvorimod articles skal gemmes tilsvarende Case objektets primary key
     */
    public void saveCase(Case c, Employee e, boolean existingCostumer) throws SQLException {
        //Husk ArrayListen af articles
        //ArticleHandler måske?
        //Customer id samt customer objekt?
        /*
        Insert statement skal justeres til også at gemme foreign key (log_id)
        til cases table i databasen
         */
        java.util.Date utilDateConvert = c.getLastUpdated();
        java.sql.Date sqlLastUpdate = new java.sql.Date(utilDateConvert.getTime());
        utilDateConvert = c.getCreatedAt();
        java.sql.Date sqlCreatedAt = new java.sql.Date(utilDateConvert.getTime());
        String saveCase = "INSERT INTO cases (konsNr, offerNmb, caseName, description, finished,"
                + "lastUpdated, updateBy, createdAt, costumer_id)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = DBHandler.getInstance().conn.prepareStatement(saveCase);
        ps.setInt(1, c.getKonsNmb());
        ps.setInt(2, c.getOfferNmb());
        ps.setString(3, c.getCaseName());
        ps.setString(4, c.getDescription());
        ps.setBoolean(5, c.isFinished());
        ps.setDate(6, sqlLastUpdate);
        ps.setString(7, e.getFullName());
        ps.setDate(8, sqlCreatedAt);
        ps.setInt(9, c.getCustomer().getCostumerID());
        ps.execute();
        CostumerHandler.getInstance().saveCostumer(c.getCustomer(), existingCostumer);
        if (c.getArticles().size() > 0) {
            for (PanelInterface article : c.getArticles()) {
                Article a = (Article) article;
                ArticleHandler.getInstance().saveArticle(a, c);
            }
        }
    }

    public int generateKonsNmb() throws SQLException {
        int konsNmb = 0;
        String statement = "SELECT konsNr FROM cases ORDER BY konsNr DESC LIMIT 1;";
        //Eller det her statement SELECT MAX(konsNr) FROM cases;
        ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
        if (rs.next()) {
            konsNmb = rs.getInt("konsNr") + 1;
        }
        rs.close();
        return konsNmb;
    }

    public void addToMyCases(Employee e, Case c) throws SQLException {
        String addCase = "INSERT INTO myCases (employee_id, cases_id)"
                + " values (?, ?)";
        PreparedStatement ps = DBHandler.getInstance().conn.prepareStatement(addCase);
        ps.setInt(1, e.getEmployeeID());
        ps.setInt(1, 2/*CASE ID - Sags nr*/);
        ps.execute();

    }

    /*Metoden må kun benyttes på de sager, som er under fanen mine sager,
    * da det kun bruges til referencer
     */
    public void deleteMyCase(int employeeID, int caseID) throws SQLException {
        String stmt1 = "DELETE FROM mycases WHERE employee_id = " + employeeID + " AND cases_id = " + caseID + ";";
        DBHandler.getInstance().conn.createStatement().executeUpdate(stmt1);
        //Sikre at den ikke prøver at slette noget, som ikke er der
    }

    public void cancelLastAction() throws SQLException {
        String stmt = "rollback;";
        System.out.println(stmt);
        DBHandler.getInstance().conn.createStatement().executeUpdate(stmt);
    }
    
    public void completeTransaction() throws SQLException {
        String stmt = "commit;";
        System.out.println(stmt);
        DBHandler.getInstance().conn.createStatement().executeUpdate(stmt);
    }

    public static CaseHandler getInstance() {
        if (instance == null) {
            instance = new CaseHandler();
        }
        return instance;
    }
}
