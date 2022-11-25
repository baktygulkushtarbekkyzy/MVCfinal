package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        Course course = entityManager.find(Course.class, courseId);
        course.addLesson(lesson);
        lesson.setCourse(course);
        entityManager.persist(lesson);
    }

    @Override
    public Lesson updateLesson(Long id, Lesson lesson) {
        Lesson lesson1 = entityManager.find(Lesson.class, id);
        lesson1.setLessonName(lesson.getLessonName());
        entityManager.persist(lesson1);
        return lesson1;
    }

    @Override
    public Lesson getById(Long id) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        return lesson;
    }

    @Override
    public List<Lesson> getAllLesson(Long courseId) {
       return entityManager.createQuery("select l from  Lesson  l where l.course.id=:id", Lesson.class).setParameter("id",courseId).getResultList();
    }

    @Override
    public void deleteLessonById(Long id) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        entityManager.remove(lesson);
    }
}
