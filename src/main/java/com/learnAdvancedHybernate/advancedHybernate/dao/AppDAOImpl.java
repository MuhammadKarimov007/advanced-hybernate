package com.learnAdvancedHybernate.advancedHybernate.dao;

import com.learnAdvancedHybernate.advancedHybernate.entity.Course;
import com.learnAdvancedHybernate.advancedHybernate.entity.Instructor;
import com.learnAdvancedHybernate.advancedHybernate.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    private final EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor tempInstructor = findInstructorById(id);

        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail tempInstructor = findInstructorDetailById(id);

        tempInstructor.getInstructor().setInstructorDetail(null);

        // As we have set the operations to cascade, once we remove
        // the particular InstructorDetail object, we also implicitly
        // delete the associated Instructor object as well
        entityManager.remove(tempInstructor);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );

        query.setParameter("data", id);

        return query.getResultList();
    }

    @Override
    public Instructor findCoursesByInstructorIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
          "select i from Instructor i Join fetch  i.courseList " +
                  "where i.id = :data", Instructor.class
        );
        query.setParameter("data", id);

        return query.getSingleResult();
    }
}
