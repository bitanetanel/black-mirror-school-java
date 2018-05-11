package school;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * A student in the Black Mirror School.
 *
 * @author Netanel Bitan
 */
@Getter
@RequiredArgsConstructor
public class Student {


    /* --- Data Members --- */

    /** The full name of the student. */
    @NonNull private String name;

    /** The phone number of the student. */
    @NonNull private String phone;

    /** The age of the student. */
    @NonNull private int age;

    /**
     * The schedule of the student.
     * In the Black Mirror School, a student learn the same schedule each day.
     * The schedule is represented as a list, so the index is the hour in the day and the value is the class number.
     */
    @Setter private List<Integer> schedule;
}
