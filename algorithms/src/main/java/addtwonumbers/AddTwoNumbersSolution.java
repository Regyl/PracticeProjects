package addtwonumbers;

public class AddTwoNumbersSolution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        putSum(l1, l2, head);
        ListNode current = fixValueAndGetNext(head);

        while (l1.next != null) {
            l1 = l1.next;
            if (l2 != null) {
                l2 = l2.next;
            }

            putSum(l1, l2, current);
            current = fixValueAndGetNext(current);
        }

        if (l2 == null || l2.next == null) {
            return head;
        }

        do {
            l2 = l2.next;
            putSum(l2, l1, current);
            current = fixValueAndGetNext(current);
        } while (l2.next != null);

        return head;
    }

    private ListNode fixValueAndGetNext(ListNode current) {
        ListNode next = new ListNode();

        int currentValue = current.val;
        if (currentValue > 9) {
            current.val = currentValue % 10;
            next.val = 1;
            current.next = next;
        } else {
            current.next = next;
        }

        return next;
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
