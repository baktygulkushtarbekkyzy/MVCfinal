package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveTask(Long lessonId, Task task) {
        Lesson lesson = entityManager.find(Lesson.class, lessonId);
        lesson.addTask(task);
        task.setLesson(lesson);
        entityManager.merge(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task task1 = entityManager.find(Task.class, id);
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadline(task.getDeadline());
        entityManager.merge(task1);
        return task1;
    }

    @Override
    public Task getById(Long id) {
        Task task = entityManager.find(Task.class, id);
        return task;
    }

    @Override
    public List<Task> getAllTask(Long lessonId) {
        List<Task> tasks = entityManager.createQuery("select t from Task t", Task.class).getResultList();
        Lesson lesson = entityManager.find(Lesson.class, lessonId);
        lesson.setTasks(tasks);
        return tasks;
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = entityManager.find(Task.class, id);
        entityManager.remove(task);
    }
}
