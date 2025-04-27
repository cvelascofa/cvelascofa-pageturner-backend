package uoc.tfg.cvelascofa.pageturner_backend.book.dto;

import lombok.Data;

@Data
public class PublisherDTO {

    private Long id;
    private String name;
    private String website;
    private String country;

}