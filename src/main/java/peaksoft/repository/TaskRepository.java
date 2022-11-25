package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Task;

import java.util.List;
@Repository
public interface TaskRepository {

    void saveTask(Long lessonId,Task task );

    Task updateTask(Long id,Task task );

    Task getById(Long id);

    List<Task> getAllTask(Long lessonId);

    void deleteTaskById(Long id);

}
