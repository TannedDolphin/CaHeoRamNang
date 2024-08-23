package cayxanh.GreencareTest.repo;

import cayxanh.GreencareTest.entity.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DichVuRepo extends JpaRepository<DichVu, Integer> {
    @Query("select p from DichVu p where p.dichvuname=:input")
    Optional<DichVu> findByName(String input);
}
