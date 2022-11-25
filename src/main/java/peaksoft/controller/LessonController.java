package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Lesson;
import peaksoft.service.CourseService;
import peaksoft.service.LessonService;

@Controller
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;
    private final CourseService courseService;

    @Autowired
    public LessonController(LessonService lessonService, CourseService courseService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @GetMapping("/getAll/{courseId}")
    public String getAll(Model model,@PathVariable Long courseId){
        model.addAttribute("lessons",lessonService.getAllLesson(courseId));
        model.addAttribute("courseId",courseId);
        return "/lesson/allLesson";
    }

    @GetMapping("/add/{courseId}")
    public String addLesson(Model model,@PathVariable Long courseId){
        model.addAttribute("newLesson",new Lesson());
        model.addAttribute("courseId",courseId);
        return "/lesson/newLesson";
    }

    @PostMapping("/save/{courseId}")
    public String save(@ModelAttribute("lesson") Lesson lesson, @PathVariable Long courseId){
        lessonService.saveLesson(courseId, lesson);
        return "redirect:/lessons/getAll/{courseId}";
    }

    @GetMapping("/update/{id}")
    public String update(Model model,@PathVariable Long id){
        model.addAttribute("lesson",lessonService.getById(id));
        model.addAttribute("lessonId",id);
        return "/lesson/updateLesson";
    }

    @PostMapping("/updateSave/{id}/{courseId}")
    public String updateSave(@PathVariable Long id,@ModelAttribute("lesson") Lesson lesson,@PathVariable Long courseId){
        lessonService.updateLesson(id, lesson);
        return "redirect:/lessons/getAll/{courseId}";
    }

    @PostMapping("/delete/{id}/{courseId}")
    public String delete(@PathVariable Long id,@PathVariable Long courseId){
        lessonService.deleteLessonById(id);
        return "redirect:/lessons/getAll/{courseId}";
    }
}
