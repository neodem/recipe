package com.neodem.recipe.web;

public interface RecipeWebConstants {   
    //--session object keys
    public static final String SESSION_LOGIN_TOKEN = "sessionLoginToken";
    public static final String SESSION_USER = "sessionUser";  
    public static final String SESSION_SEARCHRESULTSET = "resultSet";
       
    //number of times the user has tried to logon 
    public static final String SESSION_ATTEMPTS = "sessionAttempts"; 
   
    //--struts forwards
    public static final String FORWARD_BAD_LOGIN = "badLogin";  
    public static final String FORWARD_CANCEL = "cancel";  
    public static final String FORWARD_EDIT = "edit";  
    public static final String FORWARD_ERROR = "error";
    public static final String FORWARD_FAILURE = "failure";    
    public static final String FORWARD_HOME = "home";
    public static final String FORWARD_INTENDED = "intended"; 
    public static final String FORWARD_LOGIN = "login";    
    public static final String FORWARD_MODIFY = "modify";
    public static final String FORWARD_ORIGINAL = "original";
    public static final String FORWARD_PREVIOUS_REQUEST  = "previousRequest";
    public static final String FORWARD_SKIP  = "skip";
    public static final String FORWARD_START = "start";
    public static final String FORWARD_STATUS_CONFIRM = "statusConfirm";
    public static final String FORWARD_STATUS_CONFIRMMED = "statusConfirmmed";
    public static final String FORWARD_STATUS_DEFERRAL = "statusDeferral";    
    public static final String FORWARD_STATUS_ERROR = "statusError";
    public static final String FORWARD_STATUS_EXCUSED = "statusExcused";
    public static final String FORWARD_STATUS_OTHER = "statusOther";    
    public static final String FORWARD_STATUS_RESPONDED = "statusResponded";   
    public static final String FORWARD_SUBMIT = "submit";
    public static final String FORWARD_SUBMITTED  = "submitted";
    public static final String FORWARD_SUCCESS = "success";
    public static final String FORWARD_TIMEOUT = "timeOut"; 
    public static final String FORWARD_TRANSACTION_FAILURE = "txFailure";       
    public static final String FORWARD_VIEW = "view";      
    public static final String FORWARD_NOTFOUND = "notFound";   
    public static final String FORWARD_ING_SUCCESS = "sIngredient";
    public static final String FORWARD_SAME = "same";
}   
