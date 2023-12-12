package ex04;

import lombok.*;

// @Builder 메서드 체이닝
@Getter
@Setter
public class Book {
    private String title;

    private String author;

    private String publisher;

    private Book() {}

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
    // @Builder
    public static Builder builder(){
        return new Builder();
    }

    protected static class Builder{
        private Book instance = new Book();

        public Builder title(String title){
            instance.title = title;
            return this;
        }
        public Builder author(String author){
            instance.author = author;
            return this;
        }
        public Builder publisher(String publisher){
            instance.publisher = publisher;
            return this;
        }
        public Book build(){
            return instance;
        }
    }
}
