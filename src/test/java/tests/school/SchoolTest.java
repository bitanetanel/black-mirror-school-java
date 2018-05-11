package tests.school;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.School;
import school.Student;
import school.Teacher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchoolTest {


    /* --- Constants --- */

    final private List<Integer> VALID_SCHEDULE = Lists.newArrayList(1, 2, 3, 4, 5);

    /* --- Data Members --- */

    private School school;
    private Student someStudent;

    @BeforeEach
    public void setup() {
        school = new School();
        someStudent = new Student("name", "phone", 1);
    }

    @Test
    public void studentWithAValidScheduleShouldBeAdded() {
        assertAll(
                () -> assertTrue(school.addStudent(someStudent, VALID_SCHEDULE)),
                () -> assertEquals(Lists.newArrayList(someStudent), school.getStudents())
        );

        for (int hour = 0; hour < VALID_SCHEDULE.size(); hour++) {
            int classNumber = VALID_SCHEDULE.get(hour);
            assertTrue(school.getClass(classNumber).getStudents().get(hour).contains(someStudent));
        }
    }

    @Test
    public void studentWithTooLowHoursInScheduleShouldNotBeAdded() {
        List<Integer> invalidSchedule = Lists.newArrayList(1, 2);
        assertAll(
                () -> assertFalse(school.addStudent(someStudent, invalidSchedule)),
                () -> assertTrue(school.getStudents().isEmpty())
        );
    }

    @Test
    public void moreThanMaxStudentsCanNotRegisterToClassInSameHour() {
        // Add maximum number of students with same schedule.
        for (int i = 0; i < school.HOURS_PER_DAY; i++) {
            school.addStudent(
                    new Student(String.valueOf(i), String.valueOf(i), i),
                    Lists.newArrayList(VALID_SCHEDULE));
        }
        // The next student with the same schedule should not be added.
        assertAll(
                () -> assertFalse(school.addStudent(someStudent, VALID_SCHEDULE)),
                () -> assertFalse(school.getStudents().contains(someStudent))
        );
        // If we add the student in the same classes but reversed order, it should be added.
        final List<Integer> DIFFERENT_VALID_SCHEDULE = Lists.newArrayList(5, 4, 2, 3, 1);
        assertAll(
                () -> assertTrue(school.addStudent(someStudent, DIFFERENT_VALID_SCHEDULE)),
                () -> assertTrue(school.getStudents().contains(someStudent))
        );
    }

    @Test
    public void addOneTeacher() {
        int classNumber = 0;
        Teacher teacher = new Teacher("name", "phone", 0);
        school.addTeacher(teacher);
        assertAll(
                () -> assertEquals(Lists.newArrayList(teacher), school.getTeachers()),
                () -> assertEquals(teacher, school.getClasses().get(classNumber).getTeacher())
        );
    }
}