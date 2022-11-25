package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Group;

import java.util.List;
@Repository
public interface GroupRepository {

    void saveGroup(Long courseId,Group group);

    Group updateGroup(Long id,Group group);

    Group getById(Long id);

    List<Group> getAllGroup(Long courseId);

    void deleteGroupById(Long id);

    void assignGroupToCourse(Long groupId,Long courseId);

}
