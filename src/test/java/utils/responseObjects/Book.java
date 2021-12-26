package utils.responseObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    public String publisher;
    public String title;

    @Override
    public String toString() {
        return "Book{" +
                "publisher='" + publisher + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
