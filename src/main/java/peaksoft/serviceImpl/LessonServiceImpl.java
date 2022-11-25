package peaksoft.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        lessonRepository.saveLesson(courseId, lesson);
    }

    @Override
    public Lesson updateLesson(Long id, Lesson lesson) {
        return lessonRepository.updateLesson(id, lesson);
    }

    @Override
    public Lesson getById(Long id) {
        return lessonRepository.getById(id);
    }

    @Override
    public List<Lesson> getAllLesson(Long courseId) {
        return lessonRepository.getAllLesson(courseId);
    }

    @Override
    public void deleteLessonById(Long id) {
        lessonRepository.deleteLessonById(id);
    }
}
