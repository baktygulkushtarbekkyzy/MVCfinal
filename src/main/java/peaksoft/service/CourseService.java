package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Course;

import java.util.List;
@Service
public interface CourseService {

    void saveCourse(Long companyId, Course course);

    Course updateCourse(Long id,Course course);

    Course getById(Long id);

    List<Course> getAllCourse(Long companyId);

    void deleteCourseById(Long id);
}
