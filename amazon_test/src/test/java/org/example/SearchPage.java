package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    /**
     * определение локатора кнопки фильтра
     */
    //@FindBy(xpath = "//*[@id=\"searchDropdownBox\"]")
    @FindBy(xpath = "//*[@id=\"nav-search-dropdown-card\"]/div")
    private WebElement filterBtn;

    /**
     * определение локатора нужного фильтра
     */
    @FindBy(xpath = "//*[@id=\"searchDropdownBox\"]/option[6]")
    private WebElement bookBtn;
    //*[@id="nav-search-submit-button"]

    /**
     * определение локатора поля поиска
     */
    @FindBy(xpath = "//*[@id=\"twotabsearchtextbox\"]")
    private WebElement searchFld;

    /**
     * определение локатора кнопки поиска
     */
    @FindBy(xpath = "//*[@id=\"nav-search-submit-button\"]")
    private WebElement searchBtn;

    /**
     * метод для нажатия кнопки фильтра
     */
    public void clickFilterDropdown() {
        filterBtn.click(); }

    /**
     * метод для нажатия кнопки нужного фильтра
     */
    public void clickBookFilter() {
        bookBtn.click(); }

    /**
     * метод для ввода тега
     */
    public void inputFilterTag(String login) {
        searchFld.sendKeys(login); }

    /**
     * метод для нажатия кнопки поиска
     */
    public void clickSearchButton() {
        searchBtn.click(); }


}
