package btl.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import btl.Repository.*;
import btl.Entities.*;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepo;
	
	public Book save(Book book) {
        return bookRepo.save(book);
    }

    public List<Book> getAll() {
        return bookRepo.findAll();
    }
    
    public void delete(Book book) {
    	bookRepo.delete(book);
    }
    
    public Book findByBookId(long id) {
    	return bookRepo.findByBookId(id);
    }
    
    public void buy(Sold sold, Book book) {
    	bookRepo.buy(sold, book);
    }
    
    public List<Long> findBookByPersonId(long id){
    	return bookRepo.findBoughtByPersonId(id);
    }
    
    public void cancelBuy(long id_person, Book book) {
    	bookRepo.cancelBuy(id_person, book);
    }
    
    public void addRating(Rate rate) {
    	bookRepo.addRating(rate);
    }
    
    public void update(Book book) {
    	bookRepo.update(book);
    }
    
    public void updateSold(Sold sold, Book book) {
    	bookRepo.updateSold(sold, book);
    }
    
    public Sold findSoldByBookId(long id_book, long id_person) {
    	return bookRepo.findSoldByBookId(id_book, id_person);
    }
//    D:\chuyen tu c\qltv\QuanLiThuVien\src\main\resources\static\images
    public void saveImage(MultipartFile image) throws IOException {
    	String folder = "D:/chuyen tu c/qltv/QuanLiThuVien/src/main/resources/static/images/"; 
    	byte[] bytes = image.getBytes();
        String address = folder + image.getOriginalFilename();
        Path path = Paths.get(address);
        Files.write(path, bytes);
    }
    
    public void deleteImage(Book book) throws IOException {
    	String folder = "D:/chuyen tu c/qltv/QuanLiThuVien/src/main/resources/static";
        String address = folder + book.getSkinpath();
        Path path = Paths.get(address);
        Files.delete(path); 
    }
    
    public List<Rate> findRateByBookId(long id){
    	return bookRepo.findRateByBookId(id);
    }
}
