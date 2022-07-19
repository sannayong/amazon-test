package org.example;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class SearchTest {
    public static SearchPage searchPage;
    public static ResultsPage resultsPage;
    public static WebDriver driver;
    /**
     * осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() {
        //определение пути до драйвера и его настройка
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        //создание экземпляра драйвера
        driver = new ChromeDriver();
        searchPage = new SearchPage(driver);
        resultsPage = new ResultsPage(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        //получение ссылки на страницу входа из файла настроек
        driver.get(ConfProperties.getProperty("searchpage"));}

    @Test
    public void searchTest() {
        // нажимаем на выбор категории
        searchPage.clickFilterDropdown();
        // выбираем нужный фильтр
        searchPage.clickBookFilter();
        // вводим нужный тег
        searchPage.inputFilterTag(ConfProperties.getProperty("tag"));
        // нажимаем на поиск
        searchPage.clickSearchButton();
        // записываем список названий книг в массив
        resultsPage.toArrayTitles();
        // проверяем есть ли нужная книга в результатах поиска
        resultsPage.CheckTitle();
        // записываем список тега "бестселлер" книг в массив
        resultsPage.toArrayBestseller();
        // записываем список цен книг в массив
        resultsPage.toArrayPrices();
        // записываем список авторов книг в массив
        resultsPage.toArrayAuthors();
        // выводим данные о найденных книгах
        resultsPage.printInfo();

    }
}
