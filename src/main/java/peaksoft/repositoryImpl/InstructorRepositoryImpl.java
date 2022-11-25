package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveInstructor(Long courseId,Instructor instructor) {
        Course course = entityManager.find(Course.class, courseId);
        course.addInstructor(instructor);
        instructor.setCourse(course);
        entityManager.persist(instructor);
    }

    @Override
    public Instructor updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = entityManager.find(Instructor.class, id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        entityManager.merge(instructor1);

        return instructor1;
    }

    @Override
    public Instructor getById(Long id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        return instructor;
    }

    @Override
    public List<Instructor> getAllInstructor(Long courseId) {
        return entityManager.createQuery("SELECT i FROM Instructor i where i.course.id=:id", Instructor.class).setParameter("id", courseId).getResultList();
    }

    @Override
    public void deleteInstructorById(Long id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        entityManager.remove(instructor);
    }
}
