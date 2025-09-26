package model

import java.sql.RowId

data class Enrollment(
    val studentId: String,
    val courseId: String,
)