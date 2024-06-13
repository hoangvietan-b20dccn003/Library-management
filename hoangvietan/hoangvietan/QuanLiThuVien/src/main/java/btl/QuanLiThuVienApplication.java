package btl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("Entities")
public class QuanLiThuVienApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanLiThuVienApplication.class, args);
	}

}
