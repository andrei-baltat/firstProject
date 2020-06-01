package model;

import java.util.Date;

public class Expense {
    private Date date;
    private int cost;
    private String type;
    private String comment;

    public Expense() {}

    public Expense(final Date date, final int cost, final String type, final String comment) {
        this.date = date;
        this.cost = cost;
        this.type = type;
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(final int cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "date=" + date +
                ", cost=" + cost +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
