import service.StudentService
import service.CourseService
import service.EnrollmentService
import java.util.Scanner
import java.util.InputMismatchException

fun main() {
    val studentService = StudentService()
    val courseService = CourseService()
    val enrollmentService = EnrollmentService()
    val scanner = Scanner(System.`in`)

    while (true) {
        try {
            println(
                """
                ========== Student Management System ==========
                1 → Register Student
                2 → Create Course
                3 → Enroll Student
                4 → View All Students
                5 → View All Courses
                6 → View Students in a Course
                0 → Exit
                ==============================================
            """.trimIndent()
            )
            print("Choose an option: ")

            when (scanner.nextInt()) {
                1 -> {
                    scanner.nextLine()
                    print("Enter student name: ")
                    val name = scanner.nextLine()
                    print("Enter student email: ")
                    val email = scanner.nextLine()
                    print("Enter student major: ")
                    val major = scanner.nextLine()
                    val student = studentService.addstudent(name,email,major )
                    println("Registered: $student")
                }

                2 -> {
                    scanner.nextLine()
                    print("Enter course title: ")
                    val title = scanner.nextLine()
                    print("Enter course credited: ")
                    val credits = scanner.nextInt()
                    val course = courseService.createCourse(title,credits )
                    println("Created course: $course")
                }

                3 -> {
                    // Students Table
                    println("Students:")
                    println(String.format("%-10s %-10s %-5s", "ID", "Name", "Major")) // headers
                    studentService.getstudents().forEach { student ->
                        println(String.format("%-10s %-10s %-5s", student.id, student.name, student.major))
                    }

                    // Courses Table
                    println("\nCourses:")
                    println(String.format("%-10s %-10s %-5s", "ID", "Course", "Credits")) // headers
                    courseService.getAllCourse().forEach { course ->
                        println(String.format("%-10s %-10s %-5s", course.id, course.courseName, course.credits))
                    }

                    scanner.nextLine()
                    print("\nEnter student ID to enroll: ")
                    val studentId = scanner.nextLine()
                    print("Enter course ID to enroll in: ")
                    val courseId = scanner.nextLine()

                    val student = studentService.getstudents().find { it.id == studentId }
                    val course = courseService.getAllCourse().find { it.id == courseId }

                    if (student != null && course != null) {
                        enrollmentService.addEnrollment(course.id, student.id)
                        println("\nEnrolled ${student.name} in ${course.courseName}")
                    } else {
                        println("\nInvalid student or course ID")
                    }
                }

                4 -> {
                    println("All Students:")
                    println(String.format("%-10s %-10s %-10s", "ID", "Name","Major"))
                    val students = studentService.getstudents()
                    students.forEach { student ->
                        println(String.format("%-10s %-10s %-10s", student.id, student.name,student.major))
                    }
                }


                5 -> {
                    println("All Courses:")
                    println(String.format("%-10s %-10s %-10s", "ID", "Course Name","Credit"))
                    val courses = courseService.getAllCourse()
                    courses.forEach { course ->
                        println(String.format("%-10s %-10s %-10s", course.id, course.courseName,course.credits))
                    }
                }


                6 -> {
                    println("Courses:")
                    println(String.format("%-10s %-20s", "ID", "Course Name"))
                    courseService.getAllCourse().forEach { course ->
                        println(String.format("%-10s %-20s", course.id, course.courseName))
                    }
                    scanner.nextLine()
                    print("Enter course ID to view students: ")
                    val courseId = scanner.nextLine()
                    val course = courseService.getAllCourse().find { it.id == courseId }

                    if (course != null) {
                        val students = enrollmentService.getStudentByCourseId(course.id)
                        println("Students in ${course.courseName}:")
                        println(String.format("%-10s %-10s %-10s", "ID", "Name","Major"))
                        students.forEach { student ->
                            println(String.format("%-10s %-10s %-10s", student.id, student.name,student.major))
                        }
                    } else {
                        println("Course not found")
                    }
                }
                0 -> {
                    println("Exiting...")
                    break
                }

                else -> println("Invalid option")
            }
        } catch (e: InputMismatchException) {
            println("Invalid input. Please enter a number.")
            scanner.nextLine() // clear the invalid input
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            scanner.nextLine() // clear scanner buffer
        }

        println()
    }
}
