package com.learnAdvancedHybernate.advancedHybernate;

import com.learnAdvancedHybernate.advancedHybernate.dao.AppDAO;
import com.learnAdvancedHybernate.advancedHybernate.entity.Course;
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			createInstructorWithCourses(appDAO);
		};
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor(
				"Susan",
				"Public",
				"susani@com");

		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"youtube channel",
				"ultimate gamer");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		Course course1 = new Course("Guitar - The ultimate Guide");
		Course course2 = new Course("Pinball - The ultimate Guide");
		Course course3 = new Course("Golf - The ultimate Guide");

		tempInstructor.add(course1);
		tempInstructor.add(course2);
		tempInstructor.add(course3);

		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The courses: " + tempInstructor.getCourseList());
		appDAO.save(tempInstructor);

		System.out.println("Done");


	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 3;

		System.out.println("deleting instructor detail id: " + id);

		appDAO.deleteInstructorDetailById(id);

		System.out.println("Done");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		System.out.println("instructor details: " + tempInstructorDetail);

		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting the instructor id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Already deleted the instructor.");
	}

	private void findInstructor(AppDAO appDAO) {
		System.out.println("Finding instructor id: " + 2);
		Instructor instructor = appDAO.findInstructorById(2);
		System.out.println(instructor);
		System.out.println("Instructor details only: " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor tempInstructor = new Instructor(
				"Chad",
				"Darby",
				"hi@com");

		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"youtube channel",
				"Luv 2 code");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Saving instructor: " + tempInstructor);

		appDAO.save(tempInstructor);

		System.out.println("Done");
	}
}
