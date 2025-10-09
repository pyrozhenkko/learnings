//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public static class Main {
    static void main() {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        IO.println(String.format("Hello and welcome!"));

        for (int i = 1; i <= 5; i++) {
             //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            IO.println("i = " + i);
        }
    }
}
public boolean isAnagram(String s, String t) {
        if (s.length() !=t.length()) {
            return false;
        }
        int count[] = new int[26];
        for (int i=0;i<s.length();i++) {
            char ch = s.charAt(i);
            count[ch - 'a']++;
        }
        for (int i=0;i<t.length();i++) {
            char ch = t.charAt(i);
            count[ch - 'a']--;
        }
        for (int i=0; i <count.length; i++) {
            if (count[i] > 0) { return false; }
        }
        return true;
}

void main() {
}
