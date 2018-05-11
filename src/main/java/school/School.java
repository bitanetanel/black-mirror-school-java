package school;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

/**
 * The Black Mirror School.
 *
 * @author Netanel Bitan
 */
public class School {


    /* --- Constants --- */

    /** Number of hours each students learns each day. */
    public final int HOURS_PER_DAY = 5;

    /** Number of students in a standard class. */
    public final int STUDENTS_PER_CLASS = 5;

    /** Number of classes in tests.school. */
    public final int NUMBER_OF_CLASSES = 8;


    /* --- Data Members --- */

    @Getter
    final private List<Student> students = Lists.newArrayList();

    @Getter
    final private List<Teacher> teachers = Lists.newArrayList();

    @Getter
    final private List<Klass> classes = Lists.newArrayList();


    /* --- Constructor --- */

    /**
     * Initializes a new tests.school with {@link #NUMBER_OF_CLASSES} new classes.
     */
    public School() {
        for (int i = 0; i < NUMBER_OF_CLASSES; i++) {
            classes.add(new Klass(i, this));
        }
    }


    /* --- Public Methods --- */

    /**
     * Adds a new student to tests.school.
     * Each new student should be added with it's schedule.
     * The student will be added only if the schedule is valid.
     *
     * @param student The student to add.
     * @param schedule The desired schedule for the student.
     *
     * @return True if the student was successfully added, false if not.
     *
     * @see Student#schedule for the schedule structure.
     */
    public boolean addStudent(Student student, List<Integer> schedule) {
        if (scheduleIsValid(schedule)) {
            student.setSchedule(schedule);
            for (int hour = 0; hour < schedule.size(); hour++) {
                getClass(schedule.get(hour)).addStudent(hour, student);
            }
            students.add(student);
            return true;
        }
        return false;
    }

    /**
     * Add a new teacher to tests.school.
     * Set the match class's teacher as the new teacher.
     *
     * @param teacher The teacher to add.
     */
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        classes.get(teacher.getClassNumber()).setTeacher(teacher);
    }

    /**
     * Get a class by a class number.
     *
     * @param classNumber The number of the desired class.
     *
     * @return The matched class.
     */
    public Klass getClass(int classNumber) {
        return classes.get(classNumber);
    }


    /* --- Private Methods --- */

    /**
     * Checks that the given schedule is valid.
     * Schedule will considered valid if:
     * <li>It has {@link #HOURS_PER_DAY} hours in it.</li>
     * <li>If all desired classes has a free space for the new student in the wanted hour.</li>
     *
     * @param schedule The schedule to check. The indexes are the hours and the values are the class number.
     *
     * @return True if the schedule is valid, false otherwise.
     */
    private boolean scheduleIsValid(List<Integer> schedule) {
        if (schedule.size() != HOURS_PER_DAY) {
            return false;
        }

        for (int hour = 0; hour < schedule.size(); hour++) {
            int classNumber = schedule.get(hour);
            if (!getClass(classNumber).canHandleMoreStudents(hour)) {
                return false;
            }
        }

        return true;
    }
}
