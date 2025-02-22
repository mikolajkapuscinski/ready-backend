package ai.ready.ready.bookPossesion;

import org.mapstruct.Mapper;

@Mapper
public interface BookPossessionDTOMapper {
    BookPossessionDTO toDTO(BookPossession bookPossession);
}
