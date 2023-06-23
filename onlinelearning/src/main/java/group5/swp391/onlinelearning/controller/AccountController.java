package group5.swp391.onlinelearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import group5.swp391.onlinelearning.Service.IUserService;
import group5.swp391.onlinelearning.Service.Impl.UserService;
import group5.swp391.onlinelearning.entity.User;

@Controller
public class AccountController {

    @Autowired
    private IUserService userService = new UserService();

    @GetMapping("/account")
    public String getAccountManage(Model model) {
        model.addAttribute("listAccount", userService.getAllUserDTOAccountRequest());
        return "ManageAccount";
    }

    @PostMapping("/account")
    public String postAccountManage(@RequestParam("id") String sId) {
        // TODO: process POST request
        int id = Integer.parseInt(sId);
        userService.changeStatus(id);

        return "redirect:account";
    }

}
