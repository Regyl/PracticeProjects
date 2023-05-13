import addtwonumbers.AddTwoNumbersSolution;
import addtwonumbers.ListNode;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class AddTwoNumbersSolutionTest {

    private static final AddTwoNumbersSolution SOLUTION = new AddTwoNumbersSolution();

    @Test
    public void summaryWhereFirstDigitIsLonger() {
        int[] first = new int[] {1, 2, 3};
        int[] second = new int[] {1, 2};
        ListNode l1 = fillList(first);
        ListNode l2 = fillList(second);

        ListNode result = SOLUTION.addTwoNumbers(l1, l2);
        assertListValues(result, new int[] {2, 4, 3});
    }

    @Test
    public void summaryWhereSecondDigitIsLonger() {
        int[] first = new int[] {1, 2};
        int[] second = new int[] {1, 2, 3};
        ListNode l1 = fillList(first);
        ListNode l2 = fillList(second);

        ListNode resultTail = SOLUTION.addTwoNumbers(l1, l2);
        assertListValues(resultTail, new int[] {2, 4, 3});
    }

    @Test
    public void sumDigitsWithTheSameLength() {
        int[] first = new int[] {2, 4, 3};
        int[] second = new int[] {5, 6, 4};
        ListNode l1 = fillList(first);
        ListNode l2 = fillList(second);

        ListNode result = SOLUTION.addTwoNumbers(l1, l2);
        assertListValues(result, new int[] {7, 0, 8});
    }

    @Test
    public void sumDigitsWithOverflow() {
        int l1Val = 7;
        int l2Val = 8;
        ListNode l1 = new ListNode(l1Val);
        ListNode l2 = new ListNode(l2Val);

        ListNode result = SOLUTION.addTwoNumbers(l1, l2);
        assertNodeValue(result, 5);
        assertNodeValue(result.next, 1);
    }

    private ListNode fillList(int[] input) {
        ListNode current = new ListNode();
        ListNode head = current;

        current.val = input[0];
        for (int i = 1; i < input.length; i++) {
            current.next = new ListNode(input[i]);
            current = current.next;
        }

        return head;
    }

    private void assertListValues(ListNode resultTail, int[] payload) {
        ListNode current = resultTail;
        for (int i = 0; current != null; i++) {
            assertThat(i).isLessThan(payload.length);
            assertThat(current.val).isEqualTo(payload[i]);
            current = current.next;
        }
    }

    private void assertNodeValue(ListNode current, int expectedValue) {
        assertThat(current).isNotNull();
        assertThat(current.val).isEqualTo(expectedValue);
    }
}
