package kz.meirambekuly.websocketjava.services;

import kz.meirambekuly.websocketjava.model.Log;

import java.util.List;

public interface LogService {
    List<Log> findAll();
    Log addLog(Log log);
    Log deleteLog(Long id);
    Log findLogById(Long id);
}
