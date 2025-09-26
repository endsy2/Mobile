package service

import model.Course
import model.Enrollment
import model.Student
import storage.EnrollmentStorage
import storage.StudentStorage

class EnrollmentService {
    fun addEnrollment(courseId: String,studentId: String){
        EnrollmentStorage.enrollments.add(Enrollment(studentId,courseId));
    }
    fun getStudentByCourseId(courseId:String): List<Student>{
        val studentIds=EnrollmentStorage.enrollments.filter{it.courseId==courseId}.map { it.studentId };
        return StudentStorage.students.filter { it.id in studentIds }
    }

}