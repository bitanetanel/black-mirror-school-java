package school;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

/**
 * A class in the Black Mirror School.
 *
 * @author Netanel Bitan
 */
@Getter
public class Klass {


    /* ---  Data Members --- */

    /** The class number. This is a unique identifier for the class. */
    final private int classNumber;

    /** The students in the class. Maps hour to a set of students in this hour. */
    final private Map<Integer, Set<Student>> students = Maps.newHashMap();

    /** The tests.school that the class is belong to. */
    final private School school;

    /** Each class in the Black Mirror School has one teacher in it. */
    @Setter private Teacher teacher;


    /* --- Constructor --- */

    /**
     * Initializes {@link #classNumber} and {@link #school}.
     * Adds {@link School#HOURS_PER_DAY} hours to the {@link #students} map.
     *
     * @param classNumber The class number of this class.
     * @param school The tests.school that the class is belong to.
     */
    public Klass(int classNumber, School school) {
        this.classNumber = classNumber;
        this.school = school;
        for (int i = 0 ; i < school.HOURS_PER_DAY ; i++) {
            students.put(i, Sets.newHashSet());
        }
    }


    /* --- Public Methods --- */

    /**
     * Adds a student to the class in the wanted hour.
     *
     * @param hour The hour to add the student to.
     * @param student The student to add.
     */
    public void addStudent(int hour, Student student) {
        students.get(hour).add(student);
    }

    /**
     * Checks whether or not the class can handle another student in the given hour.
     *
     * @param hour The hour to check.
     *
     * @return True if the class can handle more students in the given hour, false if not.
     */
    public boolean canHandleMoreStudents(int hour) {
        return students.get(hour).size() < getSchool().STUDENTS_PER_CLASS;
    }
}
