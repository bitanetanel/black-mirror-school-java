package school;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A teacher in the Black Mirror School.
 * Each teacher is teaching in only one class.
 *
 * @author Netanel Bitan
 */
@AllArgsConstructor
@Getter
public class Teacher {


    /* --- Data Members --- */

    /** The full name of the teacher. */
    private String name;

    /** Phone number of the teacher. */
    private String phone;

    /** The class that this teacher is teaching at. */
    private int classNumber;
}
