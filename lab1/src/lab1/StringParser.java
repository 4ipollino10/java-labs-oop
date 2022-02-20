package lab1;

import lab1.Context;

public class StringParser {
    Context _context;
    private static final String EMPTY_STR = "";
    public StringParser(Context context){
        _context = context;
    }

    public void parseString(String str){
        String word = EMPTY_STR;
        for(int i = 0; i < str.length(); ++i){
            if(Character.isLetterOrDigit(str.charAt(i))){
                word += str.charAt(i);
            }
            else{
                if(word.length() > 0){
                    _context.addWord(word);
                    word = EMPTY_STR;
                }
                else{
                    continue;
                }
            }
        }
    }

}
