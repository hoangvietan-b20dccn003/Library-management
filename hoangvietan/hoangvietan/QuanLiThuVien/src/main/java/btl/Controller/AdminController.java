package btl.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import btl.Entities.*;
import btl.Service.*;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
    private PersonService personService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String home(Model model) {
		List<Book> books = bookService.getAll();
		model.addAttribute("books", books);
		return "Admin/booklists";
	}
	
	@GetMapping("/new")
	public String newbook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("action", "new");
		return "viewbook";
	}
	
	@GetMapping("/view")
	public String view(Model model, @RequestParam("id") long id) {
		Book book = bookService.findByBookId(id);
		model.addAttribute("book", book);
		model.addAttribute("action", "view");
		return "viewbook";
	}
	
	
	@PostMapping("/save")
	public String savebook(@Valid Book book, Model model, @RequestParam("action") String action, 
			@RequestParam("id") long id, @RequestParam("imageUpload") MultipartFile image){
		
		if(action.equals("new")) {
			List<Book> books = bookService.getAll();
			boolean exist = false;
			for(Book b : books) {
				if(b.getTitle().toLowerCase().equals(book.getTitle().toLowerCase()) && b.getAuthor().toLowerCase().equals(book.getAuthor().toLowerCase())) {
					exist = true;
				}
			}
			if(exist) {
				model.addAttribute("mess", "error");
				model.addAttribute("action", action);
				return "viewbook";
			}
			try {
	            bookService.saveImage(image);
	            book.setSkinpath("/images/"+image.getOriginalFilename());
	        } catch (IOException e) {
	            book.setSkinpath("/images/cam.jpg");
	        }
			bookService.save(book);
			model.addAttribute("mess", "save");
		}
		else {
			if(action.equals("view")) {
				model.addAttribute("action", "update");
				Book b = bookService.findByBookId(id);
				model.addAttribute("book", b);
				return "viewbook";
			}
			else {
				List<Book> books = bookService.getAll();
				boolean exist = false;
				for(Book b : books) {
					if(b.getBookId()!=id) {
						if(b.getTitle().equals(book.getTitle()) && b.getAuthor().equals(book.getAuthor())) {
							exist = true;
						}
					}
				}
				if(exist) {
					model.addAttribute("mess", "error");
					model.addAttribute("action", action);
					Book b = bookService.findByBookId(id);
					model.addAttribute("book", b);
					return "viewbook";
				}
				try {
		            bookService.saveImage(image);
		            book.setSkinpath("/images/"+image.getOriginalFilename());
		        } catch (IOException e) {
		            book.setSkinpath(bookService.findByBookId(id).getSkinpath());
		        }
				book.setBookId(id);
				bookService.update(book);
			}
		}
		List<Book> books = bookService.getAll();
		model.addAttribute("books", books);
		return "Admin/booklists";

	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") long theId, Model model) throws IOException {
		Book book = bookService.findByBookId(theId);
		bookService.delete(book);
		bookService.deleteImage(book);
		model.addAttribute("mess", "delete");
		List<Book> books = bookService.getAll();
		model.addAttribute("books", books);
		return "Admin/booklists";
	}
	

}