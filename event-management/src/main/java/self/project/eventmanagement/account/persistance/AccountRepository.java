package self.project.eventmanagement.account.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import self.project.eventmanagement.account.persistance.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
