package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry created = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry == null){
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }

    }
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id,  @RequestBody TimeEntry expected) {
        TimeEntry updated = timeEntryRepository.update(id, expected);
        if(updated != null){
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
