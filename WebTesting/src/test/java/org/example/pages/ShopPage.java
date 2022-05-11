package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://www.saucedemo.com/inventory.html")
public class ShopPage extends PageObject {

    @FindBy(id="add-to-cart-sauce-labs-backpack")
    private WebElementFacade addBackpackButton;

    @FindBy(id="remove-sauce-labs-backpack")
    private WebElementFacade removeBackpackButton;

    @FindBy(id="add-to-cart-sauce-labs-bike-light")
    private WebElementFacade addLightButton;

    @FindBy(id="remove-sauce-labs-bike-light")
    private WebElementFacade removeLightButton;

    @FindBy(className="shopping_cart_badge")
    private WebElementFacade shoppingCartBadge;
    @FindBy(id="react-burger-menu-btn")
    private WebElementFacade menuBtn;
    @FindBy(id="logout_sidebar_link")
    private WebElementFacade logoutBtn;


    public void addBackpack(){
        addBackpackButton.click();
    }

    public void addLight(){
        addLightButton.click();
    }

    public void removeBackpack(){
        removeBackpackButton.click();
    }

    public void removeLight(){
        removeLightButton.click();
    }

    public boolean canAddBackpack() {
        return addBackpackButton.isVisible();
    }

    public boolean canAddLight() {
        return  addLightButton.isVisible();
    }

    public boolean canRemoveBackpack(){
        return removeBackpackButton.isVisible();
    }

    public boolean canRemoveLight(){
        return removeLightButton.isVisible();
    }

    public boolean cartHasItems(){
        return shoppingCartBadge.isVisible();
    }

    public String getCartItems(){
        return shoppingCartBadge.getText();
    }

    public void logout(){
        menuBtn.click();
        logoutBtn.click();
    }
}
