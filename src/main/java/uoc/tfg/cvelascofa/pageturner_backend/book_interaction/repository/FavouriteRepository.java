package uoc.tfg.cvelascofa.pageturner_backend.book_interaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.book_interaction.entity.Favourite;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {
}