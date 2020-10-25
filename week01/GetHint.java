public class GetHint {
    public String getHint(String secret, String guess) {
        int[] cache = new int[10];
        int A = 0;
        int B = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                A++;
            } else {
                int s = secret.charAt(i) - '0';
                int g = guess.charAt(i) - '0';
                if (cache[s] < 0) B++;
                if (cache[g] > 0) B++;
                cache[s]++;
                cache[g]--;
            }
        }
        return A + "A" + B + "B";
    }
}