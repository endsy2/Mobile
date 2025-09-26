package util

object IdGenerator {
    private var current_id_stu=1;
    private var current_id_course=1;
    fun nextIdStu(): String ="ST00"+current_id_stu++;
    fun nextIdCourse(): Int =current_id_course++;
}