package peaksoft.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void saveGroup(Long companyId, Group group) {
        groupRepository.saveGroup(companyId, group);
    }

    @Override
    public Group updateGroup(Long id, Group group) {
        return groupRepository.updateGroup(id, group);
    }

    @Override
    public Group getById(Long id) {
        return groupRepository.getById(id);
    }

    @Override
    public List<Group> getAllGroup(Long courseId) {
        return groupRepository.getAllGroup(courseId);
    }

    @Override
    public void deleteGroupById(Long id) {
        groupRepository.deleteGroupById(id);
    }

    @Override
    public void assignGroupToCourse(Long groupId,Long courseId){
        groupRepository.assignGroupToCourse(groupId, courseId);
    }
}
