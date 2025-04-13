package com.edstry.Tasklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // может откатить транщакции к бд если те некорректно выполнились
@EnableCaching
public class TasklistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasklistApplication.class, args);
		
	}

}
