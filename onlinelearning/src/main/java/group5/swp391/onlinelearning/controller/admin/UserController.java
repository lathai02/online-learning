package group5.swp391.onlinelearning.controller.admin;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import group5.swp391.onlinelearning.entity.User;
import group5.swp391.onlinelearning.model.dto.UserDTORegisterRequest;
import group5.swp391.onlinelearning.service.impl.UserService;
import group5.swp391.onlinelearning.utils.ThymeleafBaseCRUD;

@RequestMapping("/admin/users")
@Controller
public class UserController {
    @Autowired
    private ThymeleafBaseCRUD thymeleafBaseCRUD;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/index")
    public String getIndex(Model model) {
        List<User> users = userService.getAllUsers();
        String title = "List users - Admin";
        thymeleafBaseCRUD.setBaseForList(model, users, title);
        return "sample/index";
    }

    @GetMapping("/create")
    public String getCreate(Model model) {
        UserDTORegisterRequest user = new UserDTORegisterRequest();
        thymeleafBaseCRUD.setBaseForEntity(model, user, "Create Staff - Admin");
        return "sample/create";
    }

    @PostMapping("/create")
    public String postCreate(
            @Valid @ModelAttribute("entity") UserDTORegisterRequest userDTORegisterRequest,
            BindingResult bindingResult,
            Model model) {
        String title = "Create Staff - Admin";
        if (bindingResult.hasErrors()) {
            thymeleafBaseCRUD.setBaseForEntity(model, userDTORegisterRequest, title);
            return "/sample/create";
        }
        User user = modelMapper.map(userDTORegisterRequest, User.class);
        try {
            userService.addStaff(user);
        } catch (Exception e) {
            thymeleafBaseCRUD.setBaseForEntity(model, user, title);
            return "/sample/create";
        }
        return "redirect:/admin/users/index";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(Model model, @PathVariable @NotNull int id) {
        User user = userService.getUserById(id);
        thymeleafBaseCRUD.setBaseForEntity(model, user, "Edit user - Admin");
        return "sample/edit";
    }

    @GetMapping("/delete/{id}")
    public String getDelete(Model model, @PathVariable @NotNull int id) {
        User user = userService.getUserById(id);
        thymeleafBaseCRUD.setBaseForEntity(model, user, "Confirm delete user - Admin");
        return "sample/delete";
    }

    @GetMapping("/details/{id}")
    public String getDetail(Model model, @PathVariable @NotNull int id) {
        User user = userService.getUserById(id);
        thymeleafBaseCRUD.setBaseForEntity(model, user, "Detail user - Admin");
        return "sample/detail";
    }
}