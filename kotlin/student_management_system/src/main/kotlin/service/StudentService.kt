package service

import model.Student
import storage.StudentStorage
import util.IdGenerator

class StudentService {
    fun addstudent(name:String,email:String,major:String): String{
        StudentStorage.students.add(Student(IdGenerator.nextIdStu(),name, email, major))
        return "student added"
    }
    fun getstudents():List<Student>{
        return StudentStorage.students;
    }
}