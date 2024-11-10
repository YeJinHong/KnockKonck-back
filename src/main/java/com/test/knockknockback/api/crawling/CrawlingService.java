package com.test.knockknockback.api.crawling;

import com.test.knockknockback.api.bizes.BizesItemSO;
import com.test.knockknockback.api.booking.BookingDTO;
import com.test.knockknockback.api.item.ItemRepository;
import com.test.knockknockback.api.item.ItemSO;
import com.test.knockknockback.util.UrlModifier;
import jakarta.transaction.Transactional;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingService {
    private final TimeDataGenerator timeDataGenerator;
    private final ItemRepository itemRepository;

    final String TIME_QUERY = ".calendar_time_selector .time_item";
    final String IFRAME_QUERY = "iframe#entryIframe";
    final String BOOKING_TAB_QUERY = ".place_section .UoIF_ .yxkiA > a";
    final String PLACE_CONTENT_LIST_QUERY = ".place_section_content > ul > li";
    final String ITEM_NAME_QUERY = ".tkK1g .QTWaA .lsthu";
    final String PRICE_LIST_QUERY = ".tkK1g .CTesk em";
    final String ITEM_BOOKING_URL_QUERY = ".tkK1g .QTWaA a";
    final String ITEM_IMAGE_URL_QUERY = ".nqKzs a img";
    @Autowired
    public CrawlingService( TimeDataGenerator timeDataGenerator, ItemRepository itemRepository) {
        this.timeDataGenerator = timeDataGenerator;
        this.itemRepository = itemRepository;
    }

    public BookingDTO getBookingTimeData(CrawlingTimeRequestDTO crawlingTimeRequestDTO){
        WebDriver driver = WebDriverUtil.getChromeDriver();

        String bookingItemUrl = itemRepository.findBookingUrlByBizesNumberAndItemNumber(
                crawlingTimeRequestDTO.getBizesNumber(), crawlingTimeRequestDTO.getItemNumber()
        );

        driver.get(bookingItemUrl + "?startDate="+crawlingTimeRequestDTO.getStartDate());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        List<WebElement> timeListElement = driver.findElements(By.cssSelector(TIME_QUERY));

        BookingDTO bookingTimeData = timeDataGenerator.generateBookingTimeData(crawlingTimeRequestDTO, timeListElement);

        WebDriverUtil.quit(driver);

        return bookingTimeData;
    }

    @Transactional
    public BizesItemSO crawlingBizesItemList(String mapUrl){
        WebDriver driver = WebDriverUtil.getChromeDriver();

        driver.get(mapUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.switchTo().frame(driver.findElement(By.cssSelector(IFRAME_QUERY)));

        // 1. 사업체 정보 추출 - bizesNumber는 아이템 정보에서 추가
        BizesItemSO bizesItemSO = new BizesItemSO(mapUrl, UrlParamExtractor.getPlaceNumberFromUrl(mapUrl), driver);

        // 예약 버튼을 클릭한다.
        driver.findElement(By.cssSelector(BOOKING_TAB_QUERY)).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        // 현재 프레임에서 상위 프레임으로 이동한다
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        // 상세정보 프레임에서 예약 정보가 들어있는 곳으로 이동한다.
        driver.switchTo().frame(driver.findElement(By.cssSelector(IFRAME_QUERY)));

        // 2. 예약 아이템 정보 추출
        List<WebElement> placeSectionContentList = driver.findElements(By.cssSelector(PLACE_CONTENT_LIST_QUERY));


        List<ItemSO> list = new ArrayList<>();
        for(WebElement we : placeSectionContentList){
            // 주의 : className은 암호화되어 있어 추후 변경의 여지가 있음
            String itemName = we.findElement(By.cssSelector(ITEM_NAME_QUERY)).getText();
            List<WebElement> priceList = we.findElements(By.cssSelector(PRICE_LIST_QUERY));
            String lowPrice = priceList.get(0).getText();
            String highPrice = priceList.get(1).getText();

            String itemBookingUrl = (we.findElement(By.cssSelector(ITEM_BOOKING_URL_QUERY))).getAttribute("href");
            String itemImageUrl = (we.findElement(By.cssSelector(ITEM_IMAGE_URL_QUERY))).getAttribute("src");

            itemBookingUrl = UrlModifier.removeQueryString(itemBookingUrl);
            String bizesNumber = UrlParamExtractor.getBizesNumberFromUrl(itemBookingUrl);
            String itemNumber = UrlParamExtractor.getItemNumberFromUrl(itemBookingUrl);
            bizesItemSO.setBizesNumber(bizesNumber); // 주의 : 반복 할당

            ItemSO itemSO = ItemSO.builder()
                    .bizesNumber(bizesNumber)
                    .itemNumber(itemNumber)
                    .bookingUrl(itemBookingUrl)
                    .name(itemName)
                    .itemImageUrl(itemImageUrl)
                    .lowPrice(lowPrice)
                    .highPrice(highPrice)
                    .build();

            list.add(itemSO);
        }
        bizesItemSO.setItemList(list);

        WebDriverUtil.quit(driver);

        return bizesItemSO;
    }

}
