import org.junit.Assert
import org.junit.Test

class MyTestCase {
    @Test
    fun `ensure everything works`() {
    }

    @Test fun ensureEverythingWorks_onAndroid() {
        Assert.assertEquals(true, true)
    }


}