package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.example.pages.LoginPage;
import org.example.pages.ShopPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

public class EndUserSteps {

    LoginPage loginPage;
    ShopPage shopPage;

    @Step
    public void enters_username(String username) {
        loginPage.enter_username(username);
    }

    @Step
    public void enters_password(String password) {
        loginPage.enter_password(password);
    }

    @Step
    public void try_login() {
        loginPage.try_login();
    }

    @Step
    public void is_the_login_page() {
        loginPage.open();
    }

    @Step
    public void login_account(String username, String password){
        enters_username(username);
        enters_password(password);
        try_login();
    }

    @Step
    public void can_add_backpack(){
        assert shopPage.canAddBackpack();
    }

    @Step
    public void can_add_light(){
        assert shopPage.canAddLight();
    }

    @Step
    public void can_remove_backpack(){
        assert shopPage.canRemoveBackpack();
    }

    @Step
    public void can_remove_light(){
        assert shopPage.canRemoveLight();
    }

    @Step
    public void logout(){
        shopPage.logout();
    }
    @Step
    public void addBackpack(){
        can_add_backpack();
        int count = 0;
        if(shopPage.cartHasItems()){
            count = Integer.parseInt(shopPage.getCartItems());
        }
        shopPage.addBackpack();
        can_remove_backpack();
        count++;
        assert (count == Integer.parseInt(shopPage.getCartItems()));
    }

    @Step
    public void addLight(){
        can_add_light();
        int count = 0;
        if(shopPage.cartHasItems()){
            count = Integer.parseInt(shopPage.getCartItems());
        }
        shopPage.addLight();
        can_remove_light();
        count++;
        assert (count == Integer.parseInt(shopPage.getCartItems()));
    }

    @Step
    public void removeBackpack(){
        can_remove_backpack();
        int count = Integer.parseInt(shopPage.getCartItems());
        shopPage.removeBackpack();
        can_add_backpack();
        count--;
        if(count == 0){
            assert !shopPage.cartHasItems();
        }
        else{
            assert (count == Integer.parseInt(shopPage.getCartItems()));
        }
    }

    @Step
    public void removeLight(){
        can_remove_light();
        int count = Integer.parseInt(shopPage.getCartItems());
        shopPage.removeLight();
        can_add_light();
        count--;
        if(count == 0){
            assert !shopPage.cartHasItems();
        }
        else{
            assert (count == Integer.parseInt(shopPage.getCartItems()));
        }
    }

}