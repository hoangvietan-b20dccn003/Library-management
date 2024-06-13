package btl.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import btl.Entities.*;
import btl.Service.*;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
    private PersonService personService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String home(Model model) {
		List<Book> books = bookService.getAll();
		model.addAttribute("books", books);
		return "User/booklists";
	}
	
	@PostMapping("/buy")
	public String buy(@RequestParam("id") long id, @RequestParam("num") long num, Model model) {
		Person person = personService.findActiveUser(1);
		List<Long> ids = bookService.findBookByPersonId(person.getPersonId());
		boolean bought = false;
		for(long i : ids) {
			if(id == i) {
				bought = true;
				Sold sold = bookService.findSoldByBookId(id, person.getPersonId());
				sold.setNum(sold.getNum()+num);
				Book book = bookService.findByBookId(id);
				bookService.updateSold(sold, book);
			}
		}
		if(!bought) {
			Sold sold = new Sold();
			sold.setBookId(id);
			sold.setNum(num);
			sold.setPersonId(person.getPersonId());
			Book book = bookService.findByBookId(id);
			bookService.buy(sold, book);
		}
		model.addAttribute("mess", "buy success");
		Book book = bookService.findByBookId(id);
		List<Book> books = bookService.getAll();
		model.addAttribute("books", books);
		return "User/booklists";
	}
	
	@GetMapping("/bought")
	public String bought(Model model) {
		Person person = personService.findActiveUser(1);
		List<Long> id = bookService.findBookByPersonId(person.getPersonId());
		List<Book> books = new ArrayList<>();
		List<Sold> solds = new ArrayList<>();
		for(long i : id) {
			Book book = bookService.findByBookId(i);
			Sold sold = bookService.findSoldByBookId(i, person.getPersonId());
			books.add(book);
			solds.add(sold);
		}
		model.addAttribute("solds", solds);
		model.addAttribute("books", books);
		return "User/bought";
	}
	
	@GetMapping("/cancelbuy")
	public String cancelbuy(@RequestParam("id") long idbook) {
		long idperson = personService.findActiveUser(1).getPersonId();
		Book book = bookService.findByBookId(idbook);
		bookService.cancelBuy(idperson, book);
		return "redirect:/user/bought";
	}
	
	@GetMapping("/rating")
	public String rating(Model model,@RequestParam("id") long id) {
		Rate rate = new Rate();
		model.addAttribute("idbook", id);
		model.addAttribute("rate", rate);
		return "User/rating";
	}
	
	@PostMapping("/rated")
	public String rated(Model model,@RequestParam("cmt") String cmt, @RequestParam("id") long id, @Valid Rate rate) {
		rate.setCmt(cmt);
		rate.setBookId(id);
		rate.setPersonId(personService.findActiveUser(1).getPersonId());
		rate.setUsername(personService.findActiveUser(1).getUsername());
		bookService.addRating(rate);
		model.addAttribute("mess", "rate success");
		List<Book> books = bookService.getAll();
		model.addAttribute("books", books);
		return "User/booklists";
	}
	
	@GetMapping("/viewing")
	public String view(Model model, @RequestParam("id") long id) {
		Book book = bookService.findByBookId(id);
		model.addAttribute("book", book);
		List<Rate> rates = bookService.findRateByBookId(id);
		model.addAttribute("rates", rates);
		return "User/viewbookinfo";
	}
}
