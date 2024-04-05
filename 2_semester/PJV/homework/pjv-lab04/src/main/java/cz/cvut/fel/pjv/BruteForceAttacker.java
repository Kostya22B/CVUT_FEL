package cz.cvut.fel.pjv;
public class BruteForceAttacker extends Thief {
    int tempor = 0;
    @Override
    public void breakPassword(int LengthOfPassword) {
        char[] cur_var = new char[LengthOfPassword];
        get_all_Variants(LengthOfPassword, cur_var, 0, getCharacters());
    }
    public void get_all_Variants(int LengthOfPassword, char[] cur_var, int cur_len, char[] characters){
        if (tempor != 1){
            boolean open_case = tryOpen(cur_var);
            if (cur_len < LengthOfPassword) {
                for (char character : characters) {
                    cur_var[cur_len] = character;
                    get_all_Variants(LengthOfPassword, cur_var, cur_len + 1, characters);
                }
            }
            if (open_case){
                tempor = 1;
            }
        }
    }
}