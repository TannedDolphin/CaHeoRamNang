package cayxanh.GreencareTest.repo;

import cayxanh.GreencareTest.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepo extends JpaRepository<InvalidatedToken, String> {
}
