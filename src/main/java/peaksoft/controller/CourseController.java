package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CompanyService companyService;

    private final GroupService groupService;

    private final CourseService courseService;

    @Autowired
    public CourseController(CompanyService companyService, GroupService groupService, CourseService courseService) {
        this.companyService = companyService;
        this.groupService = groupService;
        this.courseService = courseService;
    }


    @GetMapping("/getAll/{companyId}")
    public String getAll(Model model,
                         @PathVariable Long companyId,
                         @ModelAttribute("group") Group group){
        model.addAttribute("courses",courseService.getAllCourse(companyId));
        model.addAttribute("companyId",companyId);
        model.addAttribute("groups",groupService.getAllGroup(companyId));
        return "/course/allCourses";
    }

    @GetMapping("/add/{companyId}")
    public String addCourse(Model model,@PathVariable Long companyId){
        model.addAttribute("newCourse",new Course());
        model.addAttribute("companyId",companyId);
        return "/course/newCourse";
    }

    @PostMapping("/save/{companyId}")
    public String saveCourse(@ModelAttribute("course") Course course,@PathVariable Long companyId){
        courseService.saveCourse(companyId, course);
        return "redirect:/courses/getAll/{companyId}";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,Model model){
        model.addAttribute("course",courseService.getById(id));
        model.addAttribute("companyId",id);
        return "/course/updateCourse";
    }

    @PostMapping("/updateSave/{id}/{companyId}")
    public String updateSave(@PathVariable Long id,@ModelAttribute("course") Course course,@PathVariable Long companyId){
        courseService.updateCourse(id, course);
        return "redirect:/courses/getAll/{companyId}";
    }

    @PostMapping("/delete/{id}/{companyId}")
    public String delete(@PathVariable Long id,@PathVariable Long companyId){
        courseService.deleteCourseById(id);
        return "redirect:/courses/getAll/{companyId}";
    }

    @PostMapping("/{companyId}/{courseId}/saveAssign")
    private String saveAssign(@PathVariable("courseId") Long courseId,
                              @ModelAttribute("group") Group group,
                              @PathVariable("companyId") Long comId) {
        System.out.println(group);
        groupService.assignGroupToCourse(group.getId(), courseId);
        return "redirect:/courses/getAll/{companyId}";
    }
}
