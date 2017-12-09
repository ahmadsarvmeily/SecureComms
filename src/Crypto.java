public class Crypto {

    // Applies two rounds of (transposition->substitution)
    // in the right direction
    public static String encrypt (String plainText, int key) {

        String round1 = substitute(transpose(plainText,key,Direction.RIGHT),key,Direction.RIGHT);
        return substitute(transpose(round1,key,Direction.RIGHT),key,Direction.RIGHT);
    }

    // Applies two rounds of (substitution->transposition)
    // in the left direction
    public static String decrypt (String cipherText, int key) {

        String round1 = transpose(substitute(cipherText,key,Direction.LEFT),key,Direction.LEFT);
        return transpose(substitute(round1,key,Direction.LEFT),key,Direction.LEFT);
    }

    public enum Direction {
        LEFT,
        RIGHT
    }

    private static String transpose (String text, int key, Direction direction) {

        StringBuilder transposedString = new StringBuilder();
        int nChunks = text.length() / 8;
        int shift = 0;

        switch (direction) {
            case LEFT:
                shift = key % 8;
                break;
            case RIGHT:
                shift = -(key % 8);
                break;
        }

        // Process each chunk of 8 letters
        for(int i = 0; i < nChunks; i++) {
            String chunk = text.substring(8*i,8*i + 8);
            StringBuilder transposedChunk = new StringBuilder();

            // Append shifted letter to new string
            for(int j = 0; j < 8; j++) {
                transposedChunk.append(chunk.charAt((j+shift) % 8));
            }

            // Append new string to transposed string
            transposedString.append(transposedChunk.toString());
        }

        return transposedString.toString();
    }

    private static String substitute (String text, int key, Direction direction) {

        StringBuilder substitutedString = new StringBuilder();

        // Rotate each character based on key and direction
        for(int i = 0; i < text.length(); i++) {
            char rotatedChar = rotateChar(text.charAt(i),key % 26,direction);
            substitutedString.append(rotatedChar);
        }

        return substitutedString.toString();
    }

    private static char rotateChar(char c, int rotations, Direction direction) {

        char result = c;

        for(int i = 0; i < rotations; i++) {

            // Letters cycle through the alphabet and then loop around
            // rotateChar('Z',1,right) -> 'A'
            // rotateChar('A',1,left) -> 'Z'

            switch (direction) {
                case LEFT:
                    if (result == 'A')
                        result = 'Z';
                    else result = (char) (result - 1);
                    break;

                case RIGHT:
                    if (result == 'Z')
                        result = 'A';
                    else result = (char) (result + 1);
            }
        }

        return result;
    }
}
