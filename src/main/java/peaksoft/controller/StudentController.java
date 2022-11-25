package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Student;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @GetMapping("/getAll/{groupId}")
    public String getAll(@PathVariable Long groupId, Model model){
        model.addAttribute("students",studentService.getAllStudent(groupId));
        model.addAttribute("groupId",groupId);
        return "/student/allStudent";
    }

    @GetMapping("/add/{groupId}")
    public String add(@PathVariable Long groupId,Model model){
        model.addAttribute("newStudent",new Student());
        model.addAttribute("groupId",groupId);
        return "/student/newStudent";
    }


    @PostMapping("/saveStudent/{groupId}")
    public String save(@ModelAttribute("student") Student student, @PathVariable Long groupId){
        studentService.saveStudent(groupId, student);
        return "redirect:/students/getAll/{groupId}";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id , Model model){
        model.addAttribute("student",studentService.getById(id));
        model.addAttribute("groupId",id);
        return "/student/updateStudent";
    }

    @PostMapping("/updateSave/{id}/{groupId}")
    public String updateSave(@PathVariable Long id ,@ModelAttribute("student") Student student,@PathVariable Long groupId){
        studentService.updateStudent(id, student);
        return "redirect:/students/getAll/{groupId}";
    }

    @PostMapping("/delete/{id}/{groupId}")
    public String delete(@PathVariable Long id,@PathVariable Long groupId){
        studentService.deleteStudentById(id);
        return "redirect:/students/getAll/{groupId}";
    }
}
