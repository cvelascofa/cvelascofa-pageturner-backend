package uoc.tfg.cvelascofa.pageturner_backend.book.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AuthorDTO {

    private Long id;
    private String name;
    private String bio;
    private String website;
    private Integer followersCount;

}