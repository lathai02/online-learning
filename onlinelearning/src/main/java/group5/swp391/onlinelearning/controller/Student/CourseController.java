package group5.swp391.onlinelearning.controller.student;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import group5.swp391.onlinelearning.entity.Course;
import group5.swp391.onlinelearning.entity.User;
import group5.swp391.onlinelearning.model.dto.CourseDtoDetail;
import group5.swp391.onlinelearning.model.dto.CourseDtoDetailStudent;

import group5.swp391.onlinelearning.service.IViewService;
import group5.swp391.onlinelearning.service.Impl.CourseService;

@Controller(value = "CourseStudentController")
@RequestMapping("/student")
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    IViewService viewService;

    @GetMapping("/course/detail/{courseId}")
    public String detail(Model model, @PathVariable Integer courseId, HttpSession session) {
        CourseDtoDetailStudent course = courseService.getCourseDetailForStudentById(courseId);
        model.addAttribute("course", course);
        long view = viewService.addView(courseId);
        model.addAttribute("view", view);
        List<Course> courses = (List<Course>) session.getAttribute("cartStudentSession");
        boolean checkCart = false;
        for (Course c : courses) {
            if (c.getId() == courseId) {
                checkCart = true;
                break;
            }
        }

        User student = (User) session.getAttribute("studentSession");
        List<Course> myCourses = courseService.getMyCourse(student.getId());
        boolean checkBought = false;
        for (Course c : myCourses) {
            if (c.getId() == courseId) {
                checkBought = true;
                break;
            }
        }
        if (checkCart) {
            model.addAttribute("textCart", "already in cart");
        } else if (checkBought) {
            model.addAttribute("textCart", "you already bought");
        } else {
            model.addAttribute("textCart", "add to cart");
        }

        return "/student/course/detail";
    }

    @PostMapping("/my-course")
    public String getMyCourse(HttpSession session, Model model) {
        User student = (User) session.getAttribute("studentSession");
        List<Course> courses = courseService.getMyCourse(student.getId());
        model.addAttribute("courses", courses);
        return "student/course/my-course";
    }

}
