package uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto;

import lombok.Data;

@Data
public class AuthorDTO {

    private Long id;
    private String name;
    private String bio;
    private String website;
    private Integer followersCount;

}