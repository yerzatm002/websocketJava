package kz.meirambekuly.websocketjava.services;

import kz.meirambekuly.websocketjava.model.Log;
import kz.meirambekuly.websocketjava.repositories.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService{

    private final LogRepository logRepository;

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }

    @Override
    public Log addLog(Log log) {
        return logRepository.save(log);
    }

    @Override
    public Log deleteLog(Long id) {
        Optional<Log> log = logRepository.findById(id);
        logRepository.deleteById(id);
        return log.get();
    }

    @Override
    public Log findLogById(Long id) {
        Optional<Log> log = logRepository.findById(id);
        return log.orElse(null);
    }
}
