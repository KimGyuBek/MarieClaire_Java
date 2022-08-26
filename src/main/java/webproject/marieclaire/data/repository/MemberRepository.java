package webproject.marieclaire.data.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import webproject.marieclaire.data.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByUserId(String userId);


}
