package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveCourse(Long companyId,Course course){
        Company company = entityManager.find(Company.class, companyId);
        company.addCourse(course);
        course.setCompany(company);
        entityManager.persist(course);
    }


    @Override
    public Course updateCourse(Long id, Course course) {
        Course newCourse = getById(id);
        newCourse.setCourseName(course.getCourseName());
        newCourse.setDuration(course.getDuration());
        newCourse.setDescription(course.getDescription());
        entityManager.merge(newCourse);
        return newCourse;
    }

    @Override
    public Course getById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public List<Course> getAllCourse(Long companyId) {
        return entityManager.createQuery("SELECT c FROM Course c where c.company.id=:companyId", Course.class).setParameter("companyId", companyId).getResultList();
    }

    @Override
    public void deleteCourseById(Long id) {
        entityManager.remove(entityManager.find(Course.class,id));

    }
}
