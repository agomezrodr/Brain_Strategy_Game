/*
Adrian Gomez Rodriguez and Jesus Ramos
CS4330 Mobile apps
Dr. Cheon
 */

package cs4330.cs.utep.edu.bs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    Context context;
    private static final int DATABASE_VERSION = 13;
    private static final String DATABASE_NAME = "BrainStrategy";
    private static final String TABLE_QUEST = "quest";
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "opta";
    private static final String KEY_OPTB = "optb";
    private static final String KEY_OPTC = "optc";
    private SQLiteDatabase dbase;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion();
    }

    private void addQuestion() {
        Question q1 = new Question("6 x (5+3) = ?", "48", "33", "35", "48");
        this.addQuestion(q1);
        Question q2 = new Question("Which planet is the closest to Earth?", "Mars", "Venus", "Moon", "Venus");
        this.addQuestion(q2);
        Question q3 = new Question("What is the chemical symbol for iron?", "In", "Fe", "Ir", "Fe");
        this.addQuestion(q3);
        Question q4 = new Question("How many strings does a violin have?", "Six", "Three", "Four", "Four");
        this.addQuestion(q4);
        Question q5 = new Question("3 + 6 x 2 = ?", "15", "9", "12", "15");
        this.addQuestion(q5);
        Question q6 = new Question("What color is the circle on the Japanese national flag?", "White", "Red", "Blue", "Red");
        this.addQuestion(q6);
        Question q7 = new Question("In Fahrenheit, at what temperature does water freeze?", "31", "32", "33", "32");
        this.addQuestion(q7);
        Question q8 = new Question("How many players are there in a baseball team?", "Eleven", "Eight", "Nine", "Nine");
        this.addQuestion(q8);
        Question q9 = new Question("15 -14 / 7= ?", "13", "1", "15", "13");
        this.addQuestion(q9);
        Question q10 = new Question("Which US state is nearest to the old Soviet Union?", "Florida", "Alaska", "Maine", "Alaska");
        this.addQuestion(q10);
        Question q11 = new Question(" 78 + 12 / 6 = ?", "70", "80", "90", "80");
        this.addQuestion(q11);
        Question q12 = new Question("Which is the only Mexican team that Lionel Messi has make a goal? ", "Cruz Azul", "America", "Atlante", "Atlante");
        this.addQuestion(q12);
        Question q13 = new Question("Who is the father in Computer Science?", "Alan Turing", "Ken Thompson", "Vint Cerf", "Alan Turing");
        this.addQuestion(q13);
        Question q14 = new Question("Which word in the dictionary is spelled 'incorrectly'?", "Innoculus", "Incorrectly", "Celphone", "Incorrectly");
        this.addQuestion(q14);
        Question q15 = new Question("What is the name of the villain on Avengers 2", "Thanos", "Ultron", "Luky", "Ultron");
        this.addQuestion(q15);
        Question q16 = new Question("On the word 'UTEP', What do the 'E' means?", "East", "Earth", "El", "El");
        this.addQuestion(q16);
        Question q17 = new Question("what is the square root of 81 + 1 - 9", "1", "9", "0", "1");
        this.addQuestion(q17);
        Question q18 = new Question("How many champions league does the Real Madrid has won util 2019?", "5", "13", "10", "13");
        this.addQuestion(q18);
        Question q19 = new Question("how many times did leonardo dicaprio get nominated for an oscar", "1", "6", "3", "6");
        this.addQuestion(q19);
        Question q20 = new Question("in what year did the titanic sink?", "May 1, 2002", "June 4,1915", "April 14, 1912", "April 14, 1912");
        this.addQuestion(q20);
        Question q21 = new Question("In what month do the Russians celebrate the 'October Revolution'?", "November", "October", "June", "November");
        this.addQuestion(q21);
        Question q22 = new Question("What color are the 'Black Boxes' of the planes?", "Black", "Orange", "Blue", "Orange");
        this.addQuestion(q22);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        onCreate(db);
    }

    public void addQuestion(Question quest) {
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
        dbase.insert(TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();

        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            Question quest = new Question();
            quest.setID(cursor.getInt(0));
            quest.setQUESTION(cursor.getString(1));
            quest.setANSWER(cursor.getString(2));
            quest.setOPTA(cursor.getString(3));
            quest.setOPTB(cursor.getString(4));
            quest.setOPTC(cursor.getString(5));
            quesList.add(quest);
        }
        return quesList;
    }
}
