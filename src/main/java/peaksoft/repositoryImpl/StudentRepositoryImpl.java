package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Group;
import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveStudent(Long groupId, Student student) {
        Group group = entityManager.find(Group.class, groupId);
        group.addStudent(student);
        student.setGroup(group);
        entityManager.merge(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student student1 = entityManager.find(Student.class, id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        student1.setPhoneNumber(student.getPhoneNumber());
        entityManager.merge(student1);
        return student1;
    }

    @Override
    public Student getById(Long id) {
        Student student = entityManager.find(Student.class, id);
        return student;
    }

    @Override
    public List<Student> getAllStudent(Long groupId) {
        List<Student> resultList = entityManager.createQuery("select  s from Student s", Student.class).getResultList();
        Group group = entityManager.find(Group.class, groupId);
        group.setStudents(resultList);
        return resultList;
    }

    @Override
    public void deleteStudentById(Long id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
}
