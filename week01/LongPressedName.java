public class LongPressedName {
    public boolean solution(String name, String typed) {
        if (name == null || typed == null) {
            return false;
        }

        int nameLength = name.length();
        int typedLength = typed.length();

        int nameIndex = 0, typedIndex = 0;

        while (typedIndex < typedLength) {
            if (nameIndex < nameLength && name.charAt(nameIndex) == typed.charAt(typedIndex)) {
                nameIndex++;
                typedIndex++;
            } else if (typedIndex > 0 && typed.charAt(typedIndex - 1) == typed.charAt(typedIndex)) {
                typedIndex++;
            } else {
                return false;
            }
        }

        return nameIndex == nameLength;
    }
}