package app.dto;

public class BookSummaryDTO {
    private final String title;
    private final String authorName;

    public BookSummaryDTO(String title, String authorName) {
        this.title = title;
        this.authorName = authorName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    @Override
    public String toString() {
        return title + " - " + authorName;
    }
}
