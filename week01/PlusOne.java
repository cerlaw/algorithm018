public class PlusOne {
    public int[] solution(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }

        for (int i = digits.length - 1; i >= 0; i--) {
            int result = digits[i] + 1;
            if (result >= 10) {
                digits[i] = result - 10;
            } else {
                digits[i] = result;
                return digits;
            }
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }
}