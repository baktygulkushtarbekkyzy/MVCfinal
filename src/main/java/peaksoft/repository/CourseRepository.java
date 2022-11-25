package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;

import java.util.List;

@Repository
public interface CourseRepository {
    void saveCourse(Long companyId,Course course);

    Course updateCourse(Long id,Course course);

    Course getById(Long id);

    List<Course> getAllCourse(Long companyId);

    void deleteCourseById(Long id);
}
