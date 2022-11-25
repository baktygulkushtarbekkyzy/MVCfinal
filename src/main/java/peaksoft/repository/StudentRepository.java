package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Student;

import java.util.List;
@Repository
public interface StudentRepository {

    void saveStudent(Long groupId,Student student);

    Student updateStudent(Long id,Student student);

    Student getById(Long id);

    List<Student> getAllStudent(Long groupId);

    void deleteStudentById(Long id);

}
