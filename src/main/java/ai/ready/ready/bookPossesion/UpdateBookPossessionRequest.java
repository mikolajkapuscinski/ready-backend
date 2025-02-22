package ai.ready.ready.bookPossesion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookPossessionRequest {
    private BookState bookState;
    private Integer currentPage;
}
