package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
public class GroupRepositoryImpl implements GroupRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveGroup(Long compId, Group group) {
        Company company = entityManager.find(Company.class, compId);
        company.addGroup(group);
        group.setCompany(company);
        entityManager.persist(group);
    }

    @Override
    public Group updateGroup(Long id, Group group) {
        Group group1 = entityManager.find(Group.class, id);
        group1.setGroupName(group.getGroupName());
        group1.setImage(group.getImage());
        group1.setDateOfStart(group.getDateOfStart());
        entityManager.merge(group1);
        return group1;
    }

    @Override
    public Group getById(Long id) {
       return entityManager.find(Group.class, id);
    }

    @Override
    public List<Group> getAllGroup(Long compId) {
        return entityManager.createQuery("select g from Group  g where g.company.id=:id", Group.class).setParameter("id",compId).getResultList();
    }

    @Override
    public void deleteGroupById(Long id) {
        Group group = entityManager.find(Group.class, id);
        entityManager.remove(group);
    }

    @Override
    public void assignGroupToCourse(Long groupId,Long courseId){
        Group group = entityManager.find(Group.class, groupId);
        Course course = entityManager.find(Course.class, courseId);

        List<Group> groups = new ArrayList<>();
        groups.add(group);

        List<Course> courses = new ArrayList<>();
        courses.add(course);

        group.setCourses(courses);
        course.setGroups(groups);

        entityManager.merge(group);
    }
}
