package ai.ready.ready.book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookCardDtoMapper {
    BookCardDto toBookCardDto(Book book);
    Book toBook(BookCardDto bookCardDto);
    List<BookCardDto> toBookCardDtoList(List<Book> books);
    List<Book> toBookList(List<BookCardDto> bookCardDtos);
}
