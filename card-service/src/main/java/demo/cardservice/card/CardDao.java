package demo.cardservice.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardDao extends JpaRepository<Card, Integer> {
    Optional<Card> findByIdAndDeletedAtIsNull(Integer id);


    @Query("""
            select c from Card as c where c.userId=:userId
            """)
    List<Card> findByUserId(Integer userId);
}
