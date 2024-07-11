package de.micromata.jira.rest.core.domain;

import com.google.gson.annotations.Expose;

public class IssuelinkBean {

    @Expose
    private String id;
    @Expose
    private String self;
    @Expose
    private IssueLinkTypeBean type;
    @Expose
    private LinkedIssueBean inwardIssue;
    @Expose
    private LinkedIssueBean outwardIssue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public IssueLinkTypeBean getType() {
        return type;
    }

    public void setType(IssueLinkTypeBean type) {
        this.type = type;
    }

    public LinkedIssueBean getInwardIssue() {
        return inwardIssue;
    }

    public void setInwardIssue(LinkedIssueBean inwardIssue) {
        this.inwardIssue = inwardIssue;
    }

    public LinkedIssueBean getOutwardIssue() {
        return outwardIssue;
    }

    public void setOutwardIssue(LinkedIssueBean outwardIssue) {
        this.outwardIssue = outwardIssue;
    }
}
