package btl.Repository;

import java.util.List;
import java.util.ArrayList;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import btl.Entities.*;

@Repository
public class BookRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	public Book save(Book book) {
        String sql = "INSERT INTO Book (id_book, title, author, type, date, sold, page, description, skin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getBookId(), book.getTitle(), book.getAuthor(),
        		book.getType(), book.getDate(), book.getSold(), book.getPage(), book.getDescription(), book.getSkinpath());
        return book;
    }

    public List<Book> findAll() {
        String sql = "SELECT * FROM book";
        List<Book> books = jdbcTemplate.query(sql, (rs, rowNum) -> new Book(
                rs.getLong("id_book"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("type"),
                rs.getString("date"),
                rs.getLong("sold"),
                rs.getLong("page"),
                rs.getString("description"),
                rs.getString("skin")
        ));
        return books;
    }

    @SuppressWarnings("deprecation")
	public Book findByBookId(long id) {
        String sql = "SELECT * FROM book WHERE id_book = ?";
        
		Book book = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Book(
        		rs.getLong("id_book"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("type"),
                rs.getString("date"),
                rs.getLong("sold"),
                rs.getLong("page"),
                rs.getString("description"),
                rs.getString("skin")
        ));
        return book;
    }

    public void delete(Book book) {
        String sql = "DELETE FROM sold WHERE id_book = ?";
        jdbcTemplate.update(sql, book.getBookId());
        
        sql = "DELETE FROM book WHERE id_book = ?";
        jdbcTemplate.update(sql, book.getBookId());
        sql = "DELETE FROM rate WHERE id_book = ?";
        jdbcTemplate.update(sql, book.getBookId());
    }
    
    public void buy(Sold sold, Book book) {
    	String sql = "insert into sold (id_person, id_book, num) values (?, ?, ?)";
    	jdbcTemplate.update(sql, sold.getPersonId(), sold.getBookId(), sold.getNum());
    	sql = "UPDATE book SET sold = ? WHERE id_book = ?";
    	jdbcTemplate.update(sql, sold.getNum()+book.getSold(), sold.getBookId());
    }
    
    @SuppressWarnings("deprecation")
	public List<Long> findBoughtByPersonId(long id) {
        String sql = "SELECT id_book FROM sold WHERE id_person = ?";
        List<Long> books_id = jdbcTemplate.queryForList(sql, new Object[]{id}, Long.class);
        return books_id; // danh sach id book co trong bang sold
    }
    
    public void cancelBuy(long id_person, Book book) {
    	String sql = "SELECT num FROM sold WHERE id_person = ? AND id_book = ?";
    	@SuppressWarnings("deprecation")
		Long sold = jdbcTemplate.queryForObject(sql, new Object[]{id_person, book.getBookId()}, Long.class);
    	sql = "UPDATE book SET sold = ? WHERE id_book = ?";
    	jdbcTemplate.update(sql, book.getSold()-sold, book.getBookId());
    	sql = "DELETE FROM sold WHERE id_person = ? AND id_book = ?";
    	jdbcTemplate.update(sql,  id_person, book.getBookId());
    }
    
    public void addRating(Rate rate) {
    	String sql = "INSERT INTO rate (id_rate, id_person, id_book, comment, rating, username) VALUES (?, ?, ?, ?, ?, ?)";
    	jdbcTemplate.update(sql, rate.getRateId(), rate.getPersonId(), rate.getBookId(),
    			rate.getCmt(), rate.getRating(), rate.getUsername());
    }
    
    public void update(Book book) {
    	String sql = "UPDATE book SET title = ? WHERE id_book = ?";
    	jdbcTemplate.update(sql, book.getTitle(), book.getBookId());
    	sql = "UPDATE book SET author = ? WHERE id_book = ?";
    	jdbcTemplate.update(sql, book.getAuthor(), book.getBookId());
    	sql = "UPDATE book SET type = ? WHERE id_book = ?";
    	jdbcTemplate.update(sql, book.getType(), book.getBookId());
    	sql = "UPDATE book SET date = ? WHERE id_book = ?";
    	jdbcTemplate.update(sql, book.getDate(), book.getBookId());
    	sql = "UPDATE book SET page = ? WHERE id_book = ?";
    	jdbcTemplate.update(sql, book.getPage(), book.getBookId());
    	sql = "UPDATE book SET description = ? WHERE id_book = ?";
    	jdbcTemplate.update(sql, book.getDescription(), book.getBookId());
    	sql = "UPDATE book SET skin = ? WHERE id_book = ?";
    	jdbcTemplate.update(sql, book.getSkinpath(), book.getBookId());
    }
    
    @SuppressWarnings("deprecation")
	public Sold findSoldByBookId(long id_book, long id_person) {
    	String sql = "SELECT * FROM sold WHERE id_book = ? and id_person = ?";
        Sold sold = jdbcTemplate.queryForObject(sql, new Object[]{id_book, id_person}, (rs, rowNum) -> new Sold(
        		rs.getLong("id_book"),
        		rs.getLong("id_person"),
        		rs.getLong("num")
        ));
        return sold;
    }
    
    public void updateSold(Sold sold, Book book) {
    	String sql = "UPDATE sold SET num = ? WHERE id_book = ?";
    	jdbcTemplate.update(sql, sold.getNum(), sold.getBookId());
    	sql = "UPDATE book SET sold = ? WHERE id_book = ?";
    	jdbcTemplate.update(sql, sold.getNum()+book.getSold(), sold.getBookId());
    }
    
    @SuppressWarnings("deprecation")
	public List<Rate> findRateByBookId(long id){
    	String sql = "SELECT * FROM rate WHERE id_book = ?";
        List<Rate> rates = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> new Rate(
        		rs.getLong("id_rate"),
        		rs.getLong("id_person"),
        		rs.getLong("id_book"),
        		rs.getLong("rating"),
        		rs.getString("comment"),
        		rs.getString("username")
        ));
        return rates;
    }
}
