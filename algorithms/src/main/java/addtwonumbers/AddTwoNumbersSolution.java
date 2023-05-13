package addtwonumbers;

public class AddTwoNumbersSolution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        putSum(l1, l2, head);
        ListNode current = fixValueAndGetNext(head, l1.next != null || l2.next != null);

        while (l1.next != null) {
            l1 = l1.next;
            if (l2 != null) {
                l2 = l2.next;
            }

            putSum(l1, l2, current);
            current = fixValueAndGetNext(current, l1.next != null || (l2 != null && l2.next != null));
        }

        if (l2 == null) {
            return head;
        } else {
            l1 = l1.next;
        }

        while (l2.next != null) {
            l2 = l2.next;
            putSum(l2, l1, current);
            current = fixValueAndGetNext(current, l2.next != null);
        }

        return head;
    }

    private ListNode fixValueAndGetNext(ListNode current, boolean needEmptyNext) {
        ListNode next = new ListNode();

        int currentValue = current.val;
        if (currentValue > 9) {
            current.val = currentValue % 10;
            next.val = 1;
            current.next = next;
            return next;
        } else {
            if (needEmptyNext) {
                current.next = next;
                return next;
            }

            return current;
        }
    }

    private void putSum(ListNode l1, ListNode l2, ListNode current) {
        int sum = 0;
        sum += l1.val;
        if (l2 != null) {
            sum += l2.val;
        }

        current.val += sum;
    }
}
