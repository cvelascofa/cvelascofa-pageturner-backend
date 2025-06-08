package uoc.tfg.cvelascofa.pageturner_backend.bookinteraction.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.dto.AuthorDTO;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.entity.Author;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.mapper.AuthorMapper;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.repository.AuthorRepository;
import uoc.tfg.cvelascofa.pageturner_backend.bookmanagement.service.AuthorServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;
    private AuthorDTO authorDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        author = new Author();
        author.setId(1L);
        author.setName("John Doe");
        author.setBio("Bio example");
        author.setWebsite("https://johndoe.com");
        author.setFollowersCount(100);

        authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("John Doe");
        authorDTO.setBio("Bio example");
        authorDTO.setWebsite("https://johndoe.com");
        authorDTO.setFollowersCount(100);
    }

    @Test
    void create_ShouldSaveAndReturnAuthorDTO() {
        when(authorMapper.toEntity(authorDTO)).thenReturn(author);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapper.toDTO(author)).thenReturn(authorDTO);

        AuthorDTO created = authorService.create(authorDTO);

        assertNotNull(created);
        assertEquals(authorDTO.getName(), created.getName());
        verify(authorRepository, times(1)).save(author);
    }

    @Test
    void getAll_ShouldReturnListOfAuthorDTO() {
        List<Author> authors = List.of(author);
        List<AuthorDTO> dtos = List.of(authorDTO);

        when(authorRepository.findAll()).thenReturn(authors);
        when(authorMapper.toDTOList(authors)).thenReturn(dtos);

        List<AuthorDTO> result = authorService.getAll();

        assertEquals(1, result.size());
        assertEquals(authorDTO.getName(), result.get(0).getName());
    }

    @Test
    void getById_WhenAuthorExists_ShouldReturnAuthorDTO() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorMapper.toDTO(author)).thenReturn(authorDTO);

        Optional<AuthorDTO> result = authorService.getById(1L);

        assertTrue(result.isPresent());
        assertEquals(authorDTO.getName(), result.get().getName());
    }

    @Test
    void getById_WhenAuthorDoesNotExist_ShouldReturnEmptyOptional() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<AuthorDTO> result = authorService.getById(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void update_WhenAuthorExists_ShouldUpdateAndReturnDTO() {
        AuthorDTO updateDTO = new AuthorDTO();
        updateDTO.setName("Updated Name");
        updateDTO.setBio("Updated Bio");
        updateDTO.setWebsite("https://updated.com");
        updateDTO.setFollowersCount(200);

        Author updatedAuthor = new Author();
        updatedAuthor.setId(1L);
        updatedAuthor.setName("Updated Name");
        updatedAuthor.setBio("Updated Bio");
        updatedAuthor.setWebsite("https://updated.com");
        updatedAuthor.setFollowersCount(200);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenReturn(updatedAuthor);
        when(authorMapper.toDTO(updatedAuthor)).thenReturn(updateDTO);

        Optional<AuthorDTO> result = authorService.update(1L, updateDTO);

        assertTrue(result.isPresent());
        assertEquals("Updated Name", result.get().getName());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void update_WhenAuthorDoesNotExist_ShouldReturnEmptyOptional() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<AuthorDTO> result = authorService.update(1L, authorDTO);

        assertTrue(result.isEmpty());
        verify(authorRepository, never()).save(any());
    }

    @Test
    void delete_ShouldCallRepositoryDelete() {
        doNothing().when(authorRepository).deleteById(1L);

        authorService.delete(1L);

        verify(authorRepository, times(1)).deleteById(1L);
    }

    @Test
    void searchAuthorsPageable_ShouldReturnPagedAuthorDTO() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Author> pageAuthors = new PageImpl<>(List.of(author));
        Page<AuthorDTO> pageDTOs = new PageImpl<>(List.of(authorDTO));

        when(authorRepository.findByNameContainingIgnoreCase("john", pageable)).thenReturn(pageAuthors);
        when(authorMapper.toDTO(any(Author.class))).thenReturn(authorDTO);

        Page<AuthorDTO> result = authorService.searchAuthorsPageable("john", pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("John Doe", result.getContent().get(0).getName());
    }
}
