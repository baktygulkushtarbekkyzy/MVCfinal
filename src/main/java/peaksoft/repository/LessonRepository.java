package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;

import java.util.List;
@Repository
public interface LessonRepository {

    void saveLesson(Long courseId,Lesson lesson);

    Lesson updateLesson(Long id,Lesson lesson);

    Lesson getById(Long id);

    List<Lesson> getAllLesson(Long courseId);

    void deleteLessonById(Long id);

}
