package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReadingProgressId implements Serializable {

    private Long userId;
    private Long bookId;

}