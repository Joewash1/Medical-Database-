package model;

import java.io.Serializable;

/**
 *
 */
public class Grant implements Serializable{
    
    /**
     * account id
     */
    private String accountID;
    
    /**
     * type
     */
    private String type;
    
    /**
     * title
     */
    private String title;
    
    /**
     * source
     */
    private String source;
    
    /**
     * start date
     */
    private String startDate;
    
    /**
     * end date
     */
    private String endDate;
    
    /**
     * end date
     */
    private String startAmount;
    
    /**
     * end date
     */
    private String currentBalance;

    /**
     * constructor
     * 
     * @param accountID
     * @param type
     * @param title
     * @param source
     * @param startDate
     * @param endDate
     * @param startAmount
     * @param currentBalance 
     */    
    public Grant(String accountID, String type, String title, String source, String startDate, String endDate, String startAmount, String currentBalance) {
        this.accountID = accountID;
        this.type = type;
        this.title = title;
        this.source = source;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startAmount = startAmount;
        this.currentBalance = currentBalance;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartAmount() {
        return startAmount;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

                                    
}
