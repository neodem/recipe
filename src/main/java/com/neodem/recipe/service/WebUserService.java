package com.neodem.recipe.service;

import java.util.Locale;

import com.neodem.recipe.web.beans.WebUser;

public interface WebUserService {
    public WebUser getValidWebUser(String username, Locale locale) ;
}

