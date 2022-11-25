package peaksoft.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Student;
import peaksoft.repository.StudentRepository;
import peaksoft.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveStudent(Long groupId, Student student) {
     studentRepository.saveStudent(groupId, student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        return studentRepository.updateStudent(id, student);
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.getById(id);
    }

    @Override
    public List<Student> getAllStudent(Long groupId) {
        return studentRepository.getAllStudent(groupId);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteStudentById(id);
    }
}
