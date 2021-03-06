/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import interfaces.PanelInterface;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Philip
 */
public class Case implements PanelInterface, Comparable{
    private int caseID;
    private int konsNmb;
    private int offerNmb;
    private String caseName;
    private String description;
    private ArrayList<PanelInterface> articles;
    private boolean finished;
    private Date lastUpdated;
    private Date createdAt;
    private Costumer customer;
    private ArrayList<Log> logs;
    private ArrayList<PanelInterface> caseResponsible;

    
    public Case(int caseID, int konsNmb, int offerNmb, String caseName, String description, ArrayList<PanelInterface> articles, boolean finished, Date lastUpdated, Date createdAt, Costumer customer, ArrayList<Log> logs, ArrayList<PanelInterface> caseResponsibles) {
        this.caseID = caseID;
        this.konsNmb = konsNmb;
        this.offerNmb = offerNmb;
        this.caseName = caseName;
        this.description = description;
        this.articles = articles;
        this.finished = finished;
        this.lastUpdated = lastUpdated;
        this.createdAt = createdAt;
        this.customer = customer;
        this.logs = logs;
        this.caseResponsible = caseResponsibles;
    }
    
    @Override
    public int compareTo(Object o) {
        int result;
        if (this.caseID == ((Case) o).caseID) {
            result = 0;
        } else if ((this.caseID) > ((Case) o).caseID) {
            result = 1;
        } else {
            result = -1;
        }
        return result;
    }

    public int getCaseID() {
        return caseID;
    }
    
    public int getKonsNmb() {
        return konsNmb;
    }

    public String getCaseName() {
        return caseName;
    }

    public String getDescription() {
        return description;
    }
    
    public boolean isFinished() {
        return finished;
    }

    public ArrayList<PanelInterface> getArticles() {
        return articles;
    }

    public ArrayList<PanelInterface> getCaseResponsible() {
        return caseResponsible;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Costumer getCustomer() {
        return customer;
    }

    public ArrayList<Log> getLogs() {
        return logs;
    }
    
    public int getOfferNmb() {
        return offerNmb;
    }

    public void setArticles(ArrayList<PanelInterface> articles) {
        this.articles = articles;
    }

    public void setCustomer(Costumer customer) {
        this.customer = customer;
    }

    public void setLogs(ArrayList<Log> logs) {
        this.logs = logs;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setCaseResponsible(ArrayList<PanelInterface> caseResponsible) {
        this.caseResponsible = caseResponsible;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    
    
    
    

    
    
    
    
}
