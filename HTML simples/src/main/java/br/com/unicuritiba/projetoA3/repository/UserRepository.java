package br.com.unicuritiba.projetoA3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.unicuritiba.projetoA3.models.User;

public interface UserRepository 
				extends JpaRepository<User, Long> {

}
