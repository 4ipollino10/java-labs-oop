package ru.gulyaev;

public class StringParser {
    Context _context;
    private static final String EMPTY_STR = "";
    public StringParser(Context context){
        _context = context;
    }

    public void parseString(String str){
        StringBuilder word = new StringBuilder(EMPTY_STR);
        for(int i = 0; i < str.length(); ++i){
            if(Character.isLetterOrDigit(str.charAt(i))){
                word.append(str.charAt(i));
            }
            else{
                if(word.length() > 0){
                    _context.addWord(word.toString());
                    word = new StringBuilder(EMPTY_STR);
                }
            }
        }
    }

}
