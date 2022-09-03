package webproject.marieclaire.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webproject.marieclaire.data.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Board findByMember_UserId(String userId);


}
