import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterTest {

    private Register register;

    @Before
    public void setUp() throws Exception {
        register = new Register();
    }

    @Test
    public void absentObjectReturnsNull() {
        Object something = register.get("absent");
        Assert.assertNull(something);
    }

    @Test
    public void canRegisterSomethingAndRetrieve() {
        Object one = new Object();
        register.add("one", one);
        Object two = new Object();
        register.add("two", two);
        Object retrievedOne = register.get("one");
        Object retrievedTwo = register.get("two");
        Assert.assertNotNull(retrievedOne);
        Assert.assertSame(one, retrievedOne);
        Assert.assertSame(two, retrievedTwo);
        Assert.assertNotSame(retrievedOne, retrievedTwo);

    }
}
