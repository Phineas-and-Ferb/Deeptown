package org.example.pages;

import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import net.serenitybdd.core.pages.WebElementFacade;
import java.util.stream.Collectors;

import net.serenitybdd.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("https://www.saucedemo.com/")
public class LoginPage extends PageObject {

    @FindBy(id="user-name")
    private WebElementFacade userName;

    @FindBy(id="password")
    private WebElementFacade password;

    @FindBy(id="login-button")
    private WebElementFacade loginButton;

    @FindBy(tagName = "h3")
    private WebElementFacade loginError;

    public void enter_username(String username) {
        this.userName.type(username);
    }

    public void enter_password(String password) {
        this.password.type(password);
    }

    public void try_login(){
        loginButton.click();
    }

    public boolean loginSucceded()
    {
        return !loginError.isVisible();
    }
}