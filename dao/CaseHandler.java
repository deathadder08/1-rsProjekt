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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

    public ArrayList<PanelInterface> getCasesNewest() throws SQLException {
        ArrayList<PanelInterface> cases = new ArrayList<>();
        String statement = "SELECT * FROM cases WHERE finished = 0 ORDER BY lastUpdated DESC LIMIT 10;";
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
        String selectSQL = "SELECT * FROM myCases LEFT JOIN cases ON myCases.cases_id = cases.case_id WHERE employee_id = ? AND finished = 0";
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

    /*Fejl i JUnit test:
    *-boolean failed, der afgør om statementet eksekveres?
    *-JOptionPane, der ved fejl rydder søgeresultaterne?
     */
    public ArrayList<PanelInterface> searchCases(Employee e, JPanel displayPanel, String caseIDParam, String caseNameParam, String konsNmbParam, String offerNmbParam) throws SQLException {
        ArrayList<PanelInterface> cases = new ArrayList<>();
        boolean failed = false;
        String errorMessage = "";
        displayPanel.removeAll();
        String selectedTab = displayPanel.getName();
        String statement;
        statement = "SELECT * FROM cases WHERE ";
        if (selectedTab.equals("finishedCasesP")) {
            statement += "finished = 1 AND ";
        } else {
            statement += "finished = 0 AND ";
        }

        if (!(caseIDParam.isEmpty()) && caseIDParam.matches("[0-9]")) {
            if (Integer.parseInt(caseIDParam) > 0) {
                statement += "case_id = " + caseIDParam + " AND ";
                System.out.println("works?");
            } else {
                failed = true;
                errorMessage += "Sagsnr. kan ikke være nul";
            }
        } else {
            failed = true;
            errorMessage += "Sagsnr. må ikke indeholde negative værdier";
        }

        if (caseNameParam.matches("[a-zA-Z]+") && !(caseNameParam.isEmpty())) {
            statement += "caseName LIKE '" + caseNameParam + "%' AND ";
        }

        if (!(konsNmbParam.isEmpty()) && konsNmbParam.matches("[0-9]")) {
            if (Integer.parseInt(konsNmbParam) > 0) {
                statement += "konsNr = " + konsNmbParam + " AND ";
            } else {
                failed = true;
                errorMessage += "Konsnr. kan ikke være nul";
            }
        } else {
            failed = true;
            errorMessage += "Konsnr. må ikke indeholde negative værdier";
        }
        if (!(offerNmbParam.isEmpty()) && offerNmbParam.matches("[0-9]")) {
            if (Integer.parseInt(offerNmbParam) > 0) {
                statement += "offerNmb = " + offerNmbParam + " AND ";
            }
        }

        statement = statement.substring(0, (statement.length() - 5));
        if (selectedTab.equals("newestCasesP")) {
            statement += " ORDER BY lastUpdated DESC;";
        } else {
            statement += ";";
        }

        System.out.println(statement);
        if (!failed) {
            System.out.println("not failed");
            ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                Case c = new Case(rs.getInt("case_id"), rs.getInt("konsNr"), rs.getInt("offerNmb"), rs.getString("caseName"), rs.getString("description"),
                        null, rs.getBoolean("finished"), rs.getDate("lastUpdated"), rs.getDate("createdAt"), null, null, null);
                if (!(selectedTab.equals("myCasesP"))) {
                    cases.add(c);
                } else if (e.checkAddedMyCases(c)) {
                    cases.add(c);
                }
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
        } else {
            JOptionPane.showMessageDialog(displayPanel, errorMessage);
            System.out.println("failed");
        }
        return cases;

    }

    //Skal benyttes, når man trykker sig ind på et nyt panel via rediger knappen
    //Virker i teorien men skal tilpasses rollback
    public void editCase(Employee e, Case c) throws SQLException {
        String stmt = "SET autocommit = 0;";
        String stmt1 = "begin ";
        String stmt2 = "update cases set caseName = '" + c.getCaseName() + "', descripiton = '" + c.getDescription() + "', lastUpdated = '"
                + dateFormat.format(cal.getTime()) + "', updateBy = '" + e.getFullName() + "' WHERE case_id = " + c.getCaseID() + "; ";
        String stmt3 = "end;";
        System.out.println(stmt1 + "\n" + stmt2 + "\n");
        DBHandler.getInstance().conn.createStatement().executeUpdate(stmt);
        DBHandler.getInstance().conn.createStatement().executeUpdate(stmt1);
        DBHandler.getInstance().conn.createStatement().executeUpdate(stmt2);
//        DBHandler.getInstance().conn.createStatement().executeUpdate(stmt3);
    }

    public void saveCase(Case c, Employee e, boolean existingCostumer) throws SQLException {
        String errorMessage = "";
        boolean succeeded = true;
        int caseID = getCaseID();

        String offerNmb = String.valueOf(c.getOfferNmb());
        if (offerNmb.isEmpty() /*!(offerNmb.matches("[0-9]")) Integer.parseInt(offerNmb) < 0*/) {
            succeeded = false;
            errorMessage = errorMessage + "Tilbuds nr. skal indeholde et gyldigt tal.\n";
        }

        String caseName = c.getCaseName();
        if (!(caseName.matches("[a-zA-Z]+")) || caseName.isEmpty()) {
            succeeded = false;
            errorMessage = errorMessage + "Sagsnavn må kun indeholde bogstaver.\n";
        }

        java.util.Date utilDateConvert = c.getLastUpdated();
        java.sql.Date sqlLastUpdate = new java.sql.Date(utilDateConvert.getTime());
        utilDateConvert = c.getCreatedAt();
        java.sql.Date sqlCreatedAt = new java.sql.Date(utilDateConvert.getTime());
        if (succeeded) {
            CostumerHandler.getInstance().saveCostumer(c.getCustomer(), existingCostumer);
            System.out.println("costumer suceeded");
            if (c.getArticles().size() > 0) {
                for (PanelInterface article : c.getArticles()) {
                    Article a = (Article) article;
                    ArticleHandler.getInstance().saveArticle(a, c);
                }
            }
            LogHandler.getInstance().saveLog(c.getLogs().get(0));
            String saveCase = "INSERT INTO cases (konsNr, offerNmb, caseName, description, objects_id, finished,"
                    + "lastUpdated, updateBy, createdAt, costumer_id, log_id)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBHandler.getInstance().conn.prepareStatement(saveCase);
            ps.setInt(1, c.getKonsNmb());
            ps.setInt(2, Integer.parseInt(offerNmb));
            ps.setString(3, caseName);
            ps.setString(4, c.getDescription());
            ps.setInt(5, caseID);
            ps.setBoolean(6, c.isFinished());
            ps.setDate(7, sqlLastUpdate);
            ps.setString(8, e.getFullName());
            ps.setDate(9, sqlCreatedAt);
            ps.setInt(10, c.getCustomer().getCostumerID());
            ps.setInt(11, caseID);
            ps.execute();

        } else {
            //JOptionPane.showConfirmDialog(parentComponent, errorMessage);
            //Returner int -1, og kald optionpane i gui?   
            System.out.println(errorMessage);
        }
    }

    public int getCaseID() throws SQLException {
        int caseID = 0;
        String statement = "SELECT case_id FROM cases ORDER BY case_id DESC LIMIT 1;";
        //Eller det her statement SELECT MAX(konsNr) FROM cases;
        ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
        if (rs.next()) {
            caseID = rs.getInt("case_id") + 1;
        }
        rs.close();
        return caseID;
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
        ps.setInt(1, c.getCaseID());
        ps.execute();

    }

    /*Metoden må kun benyttes på de sager, som er under fanen mine sager,
     *da det kun bruges til referencer
     *
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

    public int getLogId(Case aCase) {
        int result = -1;
        try {
            String statement = "SELECT log_id FROM cases WHERE case_id = " + aCase.getCaseID() + ";";
            ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                result = rs.getInt("log_id");
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return result;
    }

    public int getCustomerId(Case aCase) {
        int result = -1;
        try {
            String statement = "SELECT costumer_id FROM cases WHERE case_id = " + aCase.getCaseID() + ";";
            ResultSet rs = DBHandler.getInstance().conn.createStatement().executeQuery(statement);
            while (rs.next()) {
                result = rs.getInt("costumer_id");
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return result;
    }

    public static CaseHandler getInstance() {
        if (instance == null) {
            instance = new CaseHandler();
        }
        return instance;
    }
}
