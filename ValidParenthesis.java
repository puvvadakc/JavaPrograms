public class ValidParenthesis {
    public boolean isValid(String s) {
        boolean valid = true;
        if(s.length()%2 == 0){
            for(int i = 0; i < s.length(); i+=2){
                if(s.charAt(i) == '('){
                    if(s.charAt(i+1) != ')'){
                        valid = false;
                        return valid;
                    }
                }else if(s.charAt(i) == '{'){
                    if(s.charAt(i+1) != '}'){
                        valid = false;
                        return valid;
                    }
                }else if(s.charAt(i) == '['){
                    if(s.charAt(i+1) != ']'){
                        valid = false;
                        return valid;
                    }
                }else{
                    valid = false;
                    return valid;
                } 
            }
        }else{
            valid = false;
            return valid;
        }
        return valid;
    }
}