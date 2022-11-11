import kweb.template.paginate
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ServerKtTest {

    @Test
    fun paginate() {
        assertEquals(listOf<String>(), listOf<String>().paginate(page = 1, limit = 3))
        assertEquals(listOf<String>(), listOf<String>().paginate(page = 2, limit = 3))

        assertEquals(listOf(1, 2, 3), (1..10).toList().paginate(page = 1, limit = 3))
        assertEquals(listOf(4, 5, 6), (1..10).toList().paginate(page = 2, limit = 3))
        assertEquals(listOf(7, 8, 9), (1..10).toList().paginate(page = 3, limit = 3))
        assertEquals(listOf(10), (1..10).toList().paginate(page = 4, limit = 3))
        assertEquals(listOf<Int>(), (1..10).toList().paginate(page = 5, limit = 3))

        assertEquals(listOf(1, 2), (1..4).toList().paginate(page = 1, limit = 2))
        assertEquals(listOf(3, 4), (1..4).toList().paginate(page = 2, limit = 2))
        assertEquals(listOf<Int>(), (1..4).toList().paginate(page = 3, limit = 2))
    }
}
