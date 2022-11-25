package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Group;

import java.util.List;
@Service

public interface GroupService {

    void saveGroup(Long companyId, Group group);

    Group updateGroup(Long id,Group group);

    Group getById(Long id);

    List<Group> getAllGroup(Long courseId);

    void deleteGroupById(Long id);

    void assignGroupToCourse(Long groupId,Long courseId);

}
