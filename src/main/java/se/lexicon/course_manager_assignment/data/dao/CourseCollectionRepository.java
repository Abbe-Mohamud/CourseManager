package se.lexicon.course_manager_assignment.data.dao;



import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;


public class CourseCollectionRepository implements CourseDao{

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        Course course = new Course(CourseSequencer.nextCourseId(),courseName,startDate,weekDuration);
        courses.add(course);
        return course;

    }

    @Override
    public Course findById(int id) {
        for(Course course : courses){
            if(id == course.getId()){
                return course;
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {
        Collection<Course> courseList = new HashSet<>();
        for (Course course : courses) {
            if (course.getCourseName().contains(name)) {
                courseList.add(course);
            }
            return courseList;
        }
        return null;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        Collection<Course> courseList = new HashSet<>();
        for (Course course : courses) {
            if (course.getStartDate().isBefore(end)) {
                courseList.add(course);
            }
            return courseList;
        }
        return null;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        Collection<Course> courseList = new HashSet<>();
        for (Course course : courses) {
            if (course.getStartDate().isAfter(start)) {
                courseList.add(course);
            }
            return courseList;
        }
        return null;
    }

    @Override
    public Collection<Course> findAll() {
        return courses;
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {
        Collection<Course> courseList = new HashSet<>();
        for (Course course : courses) {
            //Collection<Student>  students = new HashSet<>(); //studList
            for (Student student : course.getStudents()) {
                if (studentId == student.getId()) {
                    courseList.add(course);
                }

            }
        }
        return courseList;
    }

    @Override
    public boolean removeCourse(Course course) {
        return courses.remove(course);
        //return false;
    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }
}
