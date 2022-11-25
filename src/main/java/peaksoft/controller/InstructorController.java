package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Instructor;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;
    private final CourseService courseService;

    @Autowired
    public InstructorController(InstructorService instructorService, CourseService courseService) {
        this.instructorService = instructorService;
        this.courseService = courseService;
    }

    @GetMapping("/getAll/{courseId}")
    public String getAll(Model model,@PathVariable Long courseId){
        model.addAttribute("instructors",instructorService.getAllInstructor(courseId));
        model.addAttribute("courseId",courseId);
        return "/instructor/allInstructors";
    }

    @GetMapping("/add/{courseId}")
    public String addInstructor(Model model,@PathVariable Long courseId){
        model.addAttribute("newInstructor",new Instructor());
        model.addAttribute("courseId",courseId);
        return "/instructor/newInstructor";
    }

    @PostMapping("/{courseId}/save")
    public String saveInstructor(@PathVariable Long courseId,
                                 Instructor instructor) {
        instructorService.saveInstructor(courseId, instructor);
        return "redirect:/instructors/getAll/{courseId}";
    }

    @GetMapping("/update/{id}")
    public String update(Model model,@PathVariable Long id){
        model.addAttribute("instructor",instructorService.getById(id));
        model.addAttribute("courseId",id);
        return "/instructor/update";
    }

    @PostMapping("/editInstructor/{id}/{courseId}")
    public String saveUpdateInstructor(@PathVariable Long id,
                                       @PathVariable  Long courseId,
                                       @ModelAttribute("instructor") Instructor instructor) {
        instructorService.updateInstructor(id, instructor);
        return "redirect:/instructors/getAll/{courseId}";
    }

    @GetMapping("/delete/{id}/{courseId}")
    public String delete(@PathVariable Long id,@PathVariable Long courseId){
        instructorService.deleteInstructorById(id);
        return "redirect:/instructors/getAll/{courseId}";
    }
}
