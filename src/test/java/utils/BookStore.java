package utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.responseObjects.Book;
import utils.responseObjects.Books;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.*;

public class BookStore {
    public String publisher;

    public BookStore(String publisher) {
        this.publisher = publisher;
        this.subTest1();
    }

    private static Predicate<SelenideElement> notEmptyPredicate()
    {
        return book -> book.lastChild().has(not(exactText(" ")));
    }

    private static Predicate<SelenideElement> publisherPredicate()
    {
        return book -> book.lastChild().has(exactText("O'Reilly Media"));
    }

    private void subTest1() {
        open("https://demoqa.com/books");

        var allBookUI = $$($$(".rt-tbody .rt-tr-group .rt-tr")
                .stream().filter(notEmptyPredicate()).collect(Collectors.toList())).last();

        String lastBookTitleUI = allBookUI.$(By.tagName("a")).text();

        $("#searchBox").sendKeys(this.publisher);
        var books_collection = $$($$(".rt-tbody .rt-tr-group .rt-tr")
                .stream().filter(publisherPredicate()).collect(Collectors.toList()));

        var raw_response = RestAssuredHelper.get("https://bookstore.toolsqa.com/BookStore/v1/Books");
        Books serialized_books = raw_response.as(Books.class);
        List<Book> filtered_books = serialized_books.books.stream()
                .filter(b -> Objects.equals(b.publisher, publisher)).collect(Collectors.toList());

        String lastBookTitle = serialized_books.books.get(serialized_books.books.size() - 1).title;

        Assert.assertEquals(filtered_books.size(), books_collection.size());
        Assert.assertEquals(lastBookTitle, lastBookTitleUI);
    }
}
