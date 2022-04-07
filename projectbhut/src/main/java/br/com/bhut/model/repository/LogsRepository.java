package br.com.bhut.model.repository;

import br.com.bhut.model.entity.LogsBhut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogsRepository extends JpaRepository<LogsBhut, Long> {
}
