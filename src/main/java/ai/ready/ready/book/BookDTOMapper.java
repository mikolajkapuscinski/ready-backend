package ai.ready.ready.book;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookDTOMapper {
    BookDTO toBookDTO(Book book);
    Book toBook(BookDTO bookDTO);
    List<BookDTO> toBookDTOs(List<Book> books);
    List<Book> toBooks(List<BookDTO> bookDTOs);}
