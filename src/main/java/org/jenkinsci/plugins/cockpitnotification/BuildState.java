package org.jenkinsci.plugins.cockpitnotification;

import hudson.model.AbstractBuild;
import hudson.model.Cause;
import hudson.model.Job;
import hudson.model.Run;
import hudson.util.DescribableList;
import jenkins.model.Jenkins;

import java.io.File;
import java.lang.StringBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.jenkinsci.plugins.cockpitnotification.Utils.*;

/**
 * Give the information about the builds
 */
public class BuildState {

    private Phase phase;
    private StringBuilder log;

    private String fullUrl;
    private int number;
    private String status;
    private String displayName;
    private String jobname;
    private String description;
    private long duration;
    private Date requestedon;
    private String userid;
    private String shortdescription;
    private String username;

    public String getShortDescription() {
        return this.shortdescription;
    }

    public void setShorttDescription(String shortdescription) {
        this.shortdescription = this.shortdescription;
    }

    public String getUserId() {
        return this.userid;
    }

    public void setUserId(String userId) {
        this.userid = userId;
    }

    public String getUserName() {
        return this.username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getJobName() {
        return this.jobname;
    }

    public void setJobName(String jobname) {
        this.jobname = jobname;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Date getRequestedOn() {
        return this.requestedon;
    }

    public void setRequestedOn(Date requestedon) {
        this.requestedon = requestedon;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public StringBuilder getLog() {
        return this.log;
    }

    public void setLog(StringBuilder log) {
        this.log = log;
    }

    public void setBuildCauses(Cause.UserIdCause cause) {
        this.shortdescription = cause.getShortDescription();
        this.userid = cause.getUserId();
        this.username = cause.getUserName();
    }
    
    public void setBuildCausedAutomatically(String shortDescription)
    {
        this.userid = "";
        this.username = shortDescription;
        this.shortdescription = shortDescription + ". This build was automatically triggered.";
    }
}
