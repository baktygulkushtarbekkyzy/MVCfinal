package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Group;
import peaksoft.service.CompanyService;
import peaksoft.service.GroupService;

@Controller
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final CompanyService companyService;

    @Autowired
    public GroupController(GroupService groupService, CompanyService companyService) {
        this.groupService = groupService;
        this.companyService = companyService;
    }

    @GetMapping("/getAll/{companyId}")
    public String getAll(Model model, @PathVariable Long companyId){
        model.addAttribute("groups",groupService.getAllGroup(companyId));
        model.addAttribute("companyId",companyId);
        return "/group/allGroup";
    }

    @GetMapping("/add/{companyId}")
    public String addGroup(Model model,@PathVariable Long companyId){
        model.addAttribute("newGroup",new Group());
        model.addAttribute("companyId",companyId);
        return "/group/newGroup";
    }

    @PostMapping("/save/{companyId}")
    public String saveGroup(@ModelAttribute("group") Group group,
                            @PathVariable Long companyId){
        groupService.saveGroup(companyId,group);
        return "redirect:/groups/getAll/{companyId}";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,Model model){
        model.addAttribute("group",groupService.getById(id));
        return "/group/updateGroup";
    }

    @PostMapping("/updateSave/{id}/{companyId}")
    public String updateSave(@PathVariable Long id,
                             @ModelAttribute("group") Group group,
                             @PathVariable Long companyId){
        groupService.updateGroup(id,group);
        return "redirect:/groups/getAll/{companyId}";
    }

    @PostMapping("/delete/{id}/{companyId}")
    public String delete(@PathVariable Long id,
                         @PathVariable Long companyId){
        groupService.deleteGroupById(id);
        return "redirect:/groups/getAll/{companyId}";
    }
}
