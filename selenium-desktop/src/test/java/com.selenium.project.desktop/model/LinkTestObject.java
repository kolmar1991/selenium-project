package com.selenium.project.desktop.model;


public class LinkTestObject {
    private String destination;
    private String expected;
    private ActionType action;
    private int statusCode;

    public LinkTestObject(String destination, String expected, ActionType action) {
        this.destination = destination;
        this.expected = expected;
        this.action = action;
    }

    public LinkTestObject(String destination, int statusCode, ActionType action) {
        this.destination = destination;
        this.action = action;
        this.statusCode = statusCode;
    }

    public LinkTestObject(String destination, ActionType action) {
        this.destination = destination;
        this.action = action;
    }

    public String getDestination() {
        return destination;
    }

    public String getExpected() {
        return expected;
    }

    public ActionType getAction() {
        return action;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
