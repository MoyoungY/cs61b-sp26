import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/** Unit tests for the completed HW03 IntList code. */
public class IntListTest {

    private static IntList listOf(int... values) {
        if (values.length == 0) {
            return null;
        }

        IntList head = new IntList(values[0], null);
        IntList tail = head;
        for (int i = 1; i < values.length; i += 1) {
            tail.rest = new IntList(values[i], null);
            tail = tail.rest;
        }
        return head;
    }

    private static int[] toArray(IntList list) {
        int[] result = new int[list == null ? 0 : list.iterativeSize()];
        int index = 0;
        for (IntList node = list; node != null; node = node.rest) {
            result[index] = node.first;
            index += 1;
        }
        return result;
    }

    @Test
    public void sizeAndGetWorkForSingleAndMultipleElements() {
        IntList single = listOf(42);
        assertEquals(1, single.size());
        assertEquals(1, single.iterativeSize());
        assertEquals(42, single.get(0));

        IntList multiple = listOf(5, -2, 9, 0);
        assertEquals(4, multiple.size());
        assertEquals(4, multiple.iterativeSize());
        assertEquals(5, multiple.get(0));
        assertEquals(9, multiple.get(2));
        assertEquals(0, multiple.get(3));
    }

    @Test
    public void incrRecursiveDestructiveChangesAndReusesOriginalNodes() {
        IntList list = listOf(1, -2, 3);
        IntList originalHead = list;
        IntList originalSecond = list.rest;
        IntList originalThird = list.rest.rest;

        IntList result = IntList.incrRecursiveDestructive(list, 4);

        assertSame(originalHead, result);
        assertSame(originalSecond, result.rest);
        assertSame(originalThird, result.rest.rest);
        assertArrayEquals(new int[]{5, 2, 7}, toArray(result));
    }

    @Test
    public void incrRecursiveDestructiveHandlesNull() {
        assertNull(IntList.incrRecursiveDestructive(null, 10));
    }

    @Test
    public void sumHandlesPositiveNegativeAndZeroValues() {
        assertEquals(6, listOf(1, 2, 3).sum());
        assertEquals(0, listOf(-5, 0, 5).sum());
        assertEquals(-7, listOf(-7).sum());
    }

    @Test
    public void addLastAppendsWithoutReplacingExistingNodes() {
        IntList list = listOf(1, 2);
        IntList originalHead = list;
        IntList originalSecond = list.rest;

        list.addLast(3);

        assertSame(originalHead, list);
        assertSame(originalSecond, list.rest);
        assertArrayEquals(new int[]{1, 2, 3}, toArray(list));
    }

    @Test
    public void addFirstMovesOldContentsBackOneNode() {
        IntList list = listOf(2, 3);
        IntList originalHead = list;
        IntList originalSecond = list.rest;

        list.addFirst(1);

        assertSame(originalHead, list);
        assertSame(originalSecond, list.rest.rest);
        assertArrayEquals(new int[]{1, 2, 3}, toArray(list));
    }

    @Test
    public void mysteryAnswersAreExact() {
        assertArrayEquals(new int[]{21, 140, 30, 326, 241},
                IntListMystery.firstFiveNumbers());
        assertEquals(491, IntListMystery.middleNumber());
    }
}
