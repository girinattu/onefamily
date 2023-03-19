package uk.onefamily.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResults  extends BasePage{

    private String prodNameToUseInLinks = null;

    private String productNameStr = null;

    private final String productsList = "div.productlist_grid";

    private final String productPreview = "div.product_preview";

    private final String productName_Text = "span.product-name";

    private String productLink = "div.product_preview a[title*='product_short_name_replace']";

    private String moreInfo_link = "a[title='product_short_name_replace'] button.button-main";

    private final String next_button = "div.nbf_tpl_productsearchpagectrl_next";

    private String journeyDays_Text = "a[title='product_short_name_replace'] div.days-ctr";

    private String travelAmt_Text = "a[title='product_short_name_replace'] span span.ibecurr";


    private void setProductName(String prodName) {
        productNameStr = prodName;
        setProdNameAsInLinks(productNameStr);
    }

    private void setProdNameAsInLinks(String productName) {
        switch (productName) {
            case "11 Days - Classic Escorted Tours":
                prodNameToUseInLinks = "Tour - India";
                break;
            default:
                prodNameToUseInLinks = "Tour - " + productName;
        }
    }


    private void updatePageIDs(String prodName) {
        setProductName(prodName);
        productLink = productLink.replace("product_short_name_replace", prodNameToUseInLinks);

        moreInfo_link = moreInfo_link.replace("product_short_name_replace", prodNameToUseInLinks);

        journeyDays_Text = journeyDays_Text.replace("product_short_name_replace", prodNameToUseInLinks);

        travelAmt_Text = travelAmt_Text.replace("product_short_name_replace", prodNameToUseInLinks);

    }

    public void clickMoreInfoOnAProduct(String prodName){
        updatePageIDs(prodName);
        getWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector(moreInfo_link)));
        getWebelementBy(By.cssSelector(moreInfo_link)).click();
    }



}
