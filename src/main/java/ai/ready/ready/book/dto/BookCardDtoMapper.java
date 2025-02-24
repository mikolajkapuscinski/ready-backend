package ai.ready.ready.book.dto;
import ai.ready.ready.book.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookCardDtoMapper {
    BookCardDto toBookCardDto(Book book);
    Book toBook(BookCardDto bookCardDto);
    List<BookCardDto> toBookCardDtos(List<Book> books);
    List<Book> toBooks(List<BookCardDto> bookCardDtos);
}
