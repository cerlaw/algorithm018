public class PlusOne {
    public int[] solution(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }

        int[] res = digits;
        for (int i = digits.length - 1; i >= 0; i--) {
            int result = digits[i] + 1;
            if (result >= 10) {
                res[i] = result - 10;
            } else {
                res[i] = result;
                return res;
            }
        }
        res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }
}