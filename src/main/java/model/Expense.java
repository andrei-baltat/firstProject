package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;

public class Expense {
    private int id;
    private Date date;
    private int cost;
    @JsonIgnore
    private int userId;
    private String type;
    private String comment;

    public Expense() {}

    public Expense(final Date date, final int cost, final String type, final String comment) {
        this.date = date;
        this.cost = cost;
        this.type = type;
        this.comment = comment;
    }

    public Expense(final int id, final Date date, final int cost, final String type, final String comment, int userId) {
        this.id = id;
        this.date = date;
        this.cost = cost;
        this.type = type;
        this.comment = comment;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
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
