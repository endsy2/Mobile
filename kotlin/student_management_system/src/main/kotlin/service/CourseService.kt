package service

import model.Course
import storage.CourseStorage
import util.IdGenerator

class CourseService {
    fun createCourse(courseName:String,credits:Int): Course{
        val course=Course(courseName+IdGenerator.nextIdCourse(),courseName,credits);
        CourseStorage.courses.add(course)
        return course;
    }
    fun getAllCourse():List <Course>{
        return CourseStorage.courses;
    }
}