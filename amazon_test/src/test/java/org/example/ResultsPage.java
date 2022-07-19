package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class ResultsPage {

    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    /**
     * Получаем список названий книг
     */
    @FindBy(xpath = "//span[@class='a-size-medium a-color-base a-text-normal']")
    private List<WebElement> titleList;

    /**
     * Получаем список цен книг
     */
    @FindBy(xpath = "//div[@class='sg-col sg-col-4-of-12 sg-col-4-of-16 sg-col-4-of-20']")

    //@FindBy(xpath = "//*[@class='a-price']")
    private List<WebElement> priceList2;



    /**
     * Получаем список авторов книг
     */
    @FindBy(xpath = "//*[@class='a-row a-size-base a-color-secondary']")
    private List<WebElement> authorList;

    /**
     * метод для создания/заполнения массива названий книг
     */
    List<String> titleArr = new ArrayList<String>();
    public void toArrayTitles() {


        for (int i=0; i<titleList.size(); i++){
            List<WebElement> elements = titleList;
            for (WebElement element: elements){
                titleArr.add(element.getText());
            }
        }

        System.out.println("");

        // проверяем что записалось
        /*for (WebElement element: titleList) {
            System.out.println(element.getText());  }; */

    }

    /**
     * метод для создания/заполнения массива авторов книг
     */
    List<String> authorArr2 = new ArrayList<String>();
    public void toArrayAuthors() {
        List<String> authorArr = new ArrayList<String>();
        String a = "";

        for (int i=0; i<authorList.size(); i++){

            authorArr.add(authorList.get(i).getText().replaceAll("\\n", ""));

        }

        for (int i=0; i<authorArr.size(); i++){

            if (authorArr.get(i).contains("by")==false){
                authorArr.remove(i);
                i--;
            }
        }

        for (int i=0; i<authorArr.size(); i++){

            if (authorArr.get(i).contains("by")==true && authorArr.get(i).startsWith("by")==false){

                a = authorArr.get(i);
                a = a.substring(a.indexOf('|')+2);
                a = a.substring(0, a.indexOf('|'));
                authorArr2.add(a);

            } else if (authorArr.get(i).startsWith("by")==true && authorArr.get(i).contains("|")==true){

                a = authorArr.get(i);
                a = a.substring(0, a.indexOf('|'));
                authorArr2.add(a);

            } else authorArr2.add(authorArr.get(i));

        }

        // проверяем что записалось
        /* for (int i=0; i<authorArr2.size(); i++){
            System.out.println(i + ") " + authorArr2.get(i));
        } */

    }

    /**
     * метод для создания/заполнения массива цен книг
     */
    List<String> priceArr3 = new ArrayList<String>();
    public void toArrayPrices() {
        List<String> priceArr = new ArrayList<String>();

        /*for (int i=0; i<priceList2.size(); i++){
            List<WebElement> price_el = priceList2;
            for (WebElement element: price_el){
                priceArr.add(element.getText().replaceAll("\\n", "."));
            }
        }

        // проверяем что записалось
        for (int i=0; i<priceList2.size(); i++){
            System.out.println(i + ") " + priceArr.get(i));
        }
        System.out.println("");*/

        String a = "";
        List<String> priceArr2 = new ArrayList<String>();

        for (int i=0; i<priceList2.size(); i++){
            List<WebElement> price_el = priceList2;
            for (WebElement element: price_el){
                priceArr2.add(element.getText().replaceAll("\\n", "."));
            }
        }

        for (int i=0; i<priceArr2.size(); i++){
            if (priceArr2.get(i).contains("$")==true){
                a = priceArr2.get(i);
                a = a.substring(0, a.indexOf('$')+6);
                priceArr3.add(a);

            } else if (priceArr2.get(i).contains("$")==true && priceArr2.get(i).contains("0.00")==true) {
                a = priceArr2.get(i);
                a = a.substring(0, a.indexOf('$')+5);
                priceArr3.add(a);
            } else {
                a = priceArr2.get(i);
                priceArr3.add(a);
            }
        }

        // проверяем что записалось
        /*for (int i=0; i<16; i++){
            System.out.println(i + ") " + priceArr3.get(i));
        }*/

    }

    /**
     * метод для создания/заполнения массива тега "бестселлер" для книг
     */
    List<String> bestArr = new ArrayList<String>();
    public void toArrayBestseller() {

        for (int i=2; i<titleList.size()+2; i++){
            if (driver.findElements(By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[3]/div[2]/div[" + i + "]/div/div/div/div/div/div[1]/div/div[1]/div/span/div/span/span[1]/span/span")).size()!=0) {
                bestArr.add("- BESTSELLER");
            } else bestArr.add("");
        }

        System.out.println("");

        // проверяем что записалось
        /* for (int i=0; i<titleList.size(); i++){
            System.out.println(bestArr.get(i));
        } */

    }

    /**
     * метод для вывода данных о найденных книгах
     */
    public void printInfo() {

        Book[] books = new Book[16];

        for (int i=0; i<16; i++){
            books[i] = new Book(titleArr.get(i), authorArr2.get(i), priceArr3.get(i), bestArr.get(i));
        }

        for (int i=0; i<16; i++){
            books[i].getInfo(i);
        }

    }

    /**
     * метод для проверки есть ли определенная книга в списке
     */
        public void CheckTitle() {

        System.out.println("");

        String result="";

        for (WebElement element: titleList) {
            if(element.getText().equals(ConfProperties.getProperty("bookname"))) {
                result = element.getText();
                break;
            }
        };

        if (result==""){
            System.out.println("The list does not has your book :(");
        } else System.out.println("The list has your book :) - " + result);

    }

}