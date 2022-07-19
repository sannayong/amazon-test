package org.example;

    /**
     * класс для книг
    */
    public class Book {

    // определяем атрибуты книги
    public String name = "Default";
    public String author = "Default";
    public String price = "Default";
    public String isBestSeller = "Default";

    // определяем конструкторы книги
    public Book () {}

    public Book (String n, String a, String p, String is) {
        name = n;
        author = a;
        price = p;
        isBestSeller = is;
    }

        public Book (String n, String a, String is) {
            name = n;
            author = a;
            isBestSeller = is;
        }

    // определяем метод вывода данных книги
    public void getInfo (int i){
        System.out.println("The book №" + (i+1) + ": '" + name + "' " + author + " " + isBestSeller + "\n" + price);
    }

}
