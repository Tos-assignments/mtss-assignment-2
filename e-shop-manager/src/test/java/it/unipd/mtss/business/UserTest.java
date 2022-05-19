////////////////////////////////////////////////////////////////////
// [IVAN ANTONINO] [ARENA] [2000546]
// [SEBASTIEN] [BIOLLO] [1223855]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserTest {
    @Test
    public void test() {
        User user = new User(1, "Lola", "Carpa", 10);

        user.setId(2);
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setAge(11);

        assertEquals(2, user.getId());
        assertEquals("Mario", user.getName());
        assertEquals("Rossi", user.getSurname());
        assertEquals(11, user.getAge());
    }
}