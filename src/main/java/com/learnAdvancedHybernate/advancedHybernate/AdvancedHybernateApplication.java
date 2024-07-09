package com.learnAdvancedHybernate.advancedHybernate;

import com.learnAdvancedHybernate.advancedHybernate.dao.AppDAO;
import com.learnAdvancedHybernate.advancedHybernate.entity.Instructor;
import com.learnAdvancedHybernate.advancedHybernate.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvancedHybernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedHybernateApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			createInstructor(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor("Chad", "Darby", "hi@com");

		InstructorDetail tempInstructorDetail = new InstructorDetail("youtube channel", "Luv 2 code");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Saving instructor: " + tempInstructor);

		appDAO.save(tempInstructor);

		System.out.println("Done");
	}
}
