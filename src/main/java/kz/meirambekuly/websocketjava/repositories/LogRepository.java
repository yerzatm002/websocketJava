package kz.meirambekuly.websocketjava.repositories;

import kz.meirambekuly.websocketjava.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}