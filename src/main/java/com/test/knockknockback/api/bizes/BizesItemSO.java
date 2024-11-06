package com.test.knockknockback.api.bizes;

import com.test.knockknockback.api.item.ItemSO;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Data
public class BizesItemSO {
    private String placeNumber;
    private String bizesNumber;
    private String bizesName;
    private String address;
    private List<ItemSO> itemList;

    public BizesItemSO(String placeNumber, WebDriver driver){
        this.placeNumber = placeNumber;
        List<WebElement> elementList = driver.findElements(By.cssSelector(".yxkiA"));
        WebElement element = elementList.get(2).findElement(By.cssSelector("a"));
        setBizesName(element.getAttribute("data-line-title"));
        setAddress(element.getAttribute("data-line-description"));
    }
}
