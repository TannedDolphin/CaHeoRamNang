package cayxanh.GreencareTest.repo;

import cayxanh.GreencareTest.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
}
