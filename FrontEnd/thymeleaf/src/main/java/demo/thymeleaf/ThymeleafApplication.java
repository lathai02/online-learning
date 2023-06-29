package demo.thymeleaf;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import demo.thymeleaf.model.Course;
import demo.thymeleaf.model.Student;

@SpringBootApplication
public class ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);
		List<String> fields = new ArrayList<>();
		List<String> fieldsType = new ArrayList<>();

		for (Field field : Student.class.getDeclaredFields()) {
			fields.add(field.getName());
			fieldsType.add(field.getType().getSimpleName());
			// System.out.println(field.getType().getSimpleName());
		}
		// System.out.println("Simple Name: " + Student.class.getSimpleName());
		// fields.forEach(System.out::println);
		// fieldsType.forEach(System.out::println);
	}
}

@Controller
class HelloController {
	@GetMapping("Student/index")
	public String getIndex(Model model) {
		List<Student> students = Arrays.asList(
				new Student(1, true, 0, "Kha", "0889899292", "kha@gmail.com", "Hanoi", "JohnHA"),
				new Student(2, true, 0, "Hung", "0889899292", "kha@gmail.com", "Hanoi", "JohnHA"),
				new Student(3, true, 0, "Thai", "0889899292", "kha@gmail.com", "Hanoi", "JohnHA"),
				new Student(4, true, 0, "Dung", "0889899292", "kha@gmail.com", "Hanoi", "JohnHA"),
				new Student(5, true, 0, "Nguyen", "0889899292", "kha@gmail.com", "Hanoi", "JohnHA"),
				new Student(6, true, 0, "Nguyen", "0889899292", "kha@gmail.com", "Hanoi", "JohnHA"),
				new Student(7, true, 0, "Nguyen", "0889899292", "kha@gmail.com", "Hanoi", "JohnHA"),
				new Student(8, true, 0, "Nguyen", "0889899292", "kha@gmail.com", "Hanoi", "JohnHA"));
		String title = "List student";
		setBaseForList(model, students, title);
		return "view-sample/index";
	}

	@GetMapping("Student/create")
	public String getCreate(Model model) {
		Student student = new Student();
		setBaseForEntity(model, "Create Student", student);
		return "view-sample/create";
	}

	@GetMapping("Student/edit/{id}")
	public String getEdit(Model model, @PathVariable int id) {
		Student student = new Student(1, true, 0, "Kha", "0889899292", "kha@gmail.com", "Hanoi", "JohnHA");
		setBaseForEntity(model, "Edit student", student);
		return "view-sample/edit";
	}

	@GetMapping("Student/delete/{id}")
	public String getDelete(Model model, @PathVariable int id) {
		Student student = new Student(1, true, 0, "Kha", "0889899292", "kha@gmail.com", "Hanoi", "JohnHA");
		setBaseForEntity(model, "Confirm delete student", student);
		return "view-sample/delete";
	}

	@GetMapping("Student/details/{id}")
	public String getDetail(Model model, @PathVariable int id) {
		Student student = new Student(1, true, 889, "Kha", "0868636668", "kha@fpt.edu.vn", "kha@fp", "kha@fpt.edu.vn");
		setBaseForEntity(model, "Detail student", student);
		return "view-sample/detail";
	}

	// demo list products
	@GetMapping("home")
	public String getListProduct(Model model) {
		model.addAttribute("courses", new Course().toList());
		return "course/list-products";
	}

	private void setBaseForEntity(Model model, String title, Object o) {
		model.addAttribute("entity", o);
		model.addAttribute("title", title);
		List<Field> fields = Arrays.asList(o.getClass().getDeclaredFields());
		model.addAttribute("fields", fields);
	}

	private void setBaseForList(Model model, List<? extends Object> list, String title) {
		model.addAttribute("title", title);
		model.addAttribute("entities", list);
		List<Field> fields = Arrays.asList(list.get(0).getClass().getDeclaredFields());
		model.addAttribute("fields", fields);
	}
}
