package btl.Controller;

import btl.Entities.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import btl.Service.*;

@RequestMapping("/")
@Controller
public class Homepage {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PersonService personService;
	
	@GetMapping
	public String home(Model model) {
		personService.activeOff(0);
		List<Book> books = bookService.getAll();
		model.addAttribute("books", books);
		return "Homepage/homepage";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "Homepage/signup";
	}
	
	@PostMapping("/signed")
	public String signed(@Valid Person person, @RequestParam("role") String role, Model model) {
		List<Person> persons = personService.getAll();
		for(Person ps : persons) {
			if(ps.getUsername().equals(person.getUsername()) || ps.getEmail().equals(person.getEmail())) {
				model.addAttribute("mess", "error");
				return "Homepage/signup";
			}
		}
		model.addAttribute("mess", "success");
		person.setRole(role);
		personService.save(person);
		List<Book> books = bookService.getAll();
		model.addAttribute("books", books);
		return "Homepage/homepage";
	}
	
	@GetMapping("/login")
	public String login() {
		return "Homepage/login";
	}
	
	@PostMapping("/checkLogin")
	public String checklogin(Model model,@RequestParam("username") String username, @RequestParam("pass") String pass) {
		personService.activeOff(0);		
		List<Person> persons = personService.getAll();
		for(Person person : persons) {
			if(person.getUsername().equals(username)&&person.getPassword().equals(pass)) {
				personService.activeOn(person.getPersonId());
				if(person.getRole().equals("admin")) {
					return "redirect:/admin";
				}
				else {
					return "redirect:/user";
				}
			}
		}
		model.addAttribute("mess", "error");
		return "Homepage/login";
	}
}
