package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Instructor;

import java.util.List;
@Repository
public interface InstructorRepository {

    void saveInstructor(Long courseId,Instructor instructor);

    Instructor updateInstructor(Long id,Instructor instructor);

    Instructor getById(Long id);

    List<Instructor> getAllInstructor(Long courseId);

    void deleteInstructorById(Long id);

}
