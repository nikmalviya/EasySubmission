package com.project.entity;

import java.util.HashMap;
import java.util.Map;

public enum SubmissionStatus {
    NOT_SUBMITTED,
    SUBMITTED,
    LATE_SUBMITTED,
    GRADED;

    public String getStatus(){
        return this.toString().replace('_',' ');
    }
    public Map<SubmissionStatus,String> badgeClasses(){
        Map<SubmissionStatus,String> map = new HashMap<>();
        map.put(NOT_SUBMITTED,"badge-warning");
        map.put(SUBMITTED,"badge-info");
        map.put(LATE_SUBMITTED,"badge-danger");
        map.put(GRADED,"badge-success");
        return map;
    }
    public String getBadgeTag(){
        return String.format("<span class=\"badge %s\">%s</span>",badgeClasses().get(this),this.getStatus());
    }
}
