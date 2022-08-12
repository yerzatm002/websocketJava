package kz.meirambekuly.websocketjava.web;

import kz.meirambekuly.websocketjava.model.Log;
import kz.meirambekuly.websocketjava.services.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @PostMapping("/add")
    public ResponseEntity<?> addLog(@RequestBody Log log){
        return ResponseEntity.ok().body(logService.addLog(log));
    }

    @GetMapping("/getLogs")
    public ResponseEntity<?> findAllLogs(){
        return ResponseEntity.ok().body(logService.findAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> findById(@RequestParam Long id){
        return ResponseEntity.ok().body(logService.findLogById(id));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> delete(@RequestParam Long id){
        return ResponseEntity.ok().body(logService.deleteLog(id));
    }

}
