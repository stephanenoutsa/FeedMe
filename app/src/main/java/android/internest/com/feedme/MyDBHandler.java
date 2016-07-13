package android.internest.com.feedme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "resto.db";

    public static final String TABLE_RESTO = "resto";
    public static final String RESTO_COLUMN_ID = "_rid";
    public static final String RESTO_COLUMN_NAME = "rname";
    public static final String RESTO_COLUMN_LOGO = "rlogo";
    public static final String RESTO_COLUMN_LOCATION = "rlocation";
    public static final String RESTO_COLUMN_DAY = "day";

    public static final String TABLE_FOOD = "food";
    public static final String FOOD_COLUMN_ID = "_fid";
    public static final String FOOD_COLUMN_NAME = "fname";
    public static final String FOOD_COLUMN_ICON = "ficon";

    public static final String TABLE_MENU = "menu";
    public static final String MENU_COLUMN_ID = "_mid";
    public static final String MENU_COLUMN_DAY = "day";
    public static final String MENU_COLUMN_NAME = "mname";

    public static final String TABLE_RESTO_MENU = "resto_menu";
    public static final String TABLE_MENU_FOOD = "menu_food";
    public static final String KEY_RESTO_MENU_ID = "_rmid";
    public static final String KEY_MENU_FOOD_ID = "_mfid";
    public static final String KEY_RESTO_ID = "resto_id";
    public static final String KEY_MENU_ID = "menu_id";
    public static final String KEY_FOOD_ID = "food_id";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String resto = "CREATE TABLE " + TABLE_RESTO + "(" +
                RESTO_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + ", " +
                RESTO_COLUMN_NAME + " TEXT " + ", " +
                RESTO_COLUMN_LOGO + " TEXT " + ", " +
                RESTO_COLUMN_LOCATION + " TEXT " + ", " +
                RESTO_COLUMN_DAY + " TEXT " +
                ")";
        db.execSQL(resto);

        String food = "CREATE TABLE " + TABLE_FOOD + "(" +
                FOOD_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + ", " +
                FOOD_COLUMN_NAME + " TEXT " + ", " +
                FOOD_COLUMN_ICON + " TEXT " + ", " +
                MENU_COLUMN_NAME + " TEXT " +
                ")";
        db.execSQL(food);

        String menu = "CREATE TABLE " + TABLE_MENU + "(" +
                MENU_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + ", " +
                MENU_COLUMN_DAY + " TEXT " + ", " +
                MENU_COLUMN_NAME + " TEXT " +
                ")";
        db.execSQL(menu);

        String resto_menu = "CREATE TABLE " + TABLE_RESTO_MENU + "(" +
                KEY_RESTO_MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + ", " +
                KEY_RESTO_ID + " INTEGER " + ", " +
                KEY_MENU_ID + " INTEGER " + ", " +
                RESTO_COLUMN_DAY + " TEXT " +
                ")";
        db.execSQL(resto_menu);

        String menu_food = "CREATE TABLE " + TABLE_MENU_FOOD + "(" +
                KEY_MENU_FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + ", " +
                KEY_MENU_ID + " INTEGER " + ", " +
                KEY_FOOD_ID + " INTEGER " + ", " +
                MENU_COLUMN_NAME + " TEXT " +
                ")";
        db.execSQL(menu_food);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTO + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTO_MENU + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU_FOOD + ";");
        onCreate(db);
    }

    // Add a new restaurant to the Resto table
    public void addResto(Resto resto, long[] menu_ids) {
        ContentValues values = new ContentValues();
        values.put(RESTO_COLUMN_NAME, String.valueOf(resto.getRname()));
        values.put(RESTO_COLUMN_LOGO, String.valueOf(resto.getRlogo()));
        values.put(RESTO_COLUMN_LOCATION, String.valueOf(resto.getRlocation()));
        values.put(RESTO_COLUMN_DAY, String.valueOf(resto.getDay()));
        SQLiteDatabase db = getWritableDatabase();

        // Insert row
        long resto_id = db.insert(TABLE_RESTO, null, values);

        // Assign menu to restaurant
        for (long menu_id : menu_ids) {
            addRestoMenu(resto_id, menu_id, RESTO_COLUMN_DAY);
        }
        db.close();
    }

    // Add a menu to a restaurant
    public void addRestoMenu(long resto_id, long menu_id, String day) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RESTO_ID, resto_id);
        values.put(KEY_MENU_ID, menu_id);
        values.put(RESTO_COLUMN_DAY, day);

        db.insert(TABLE_RESTO_MENU, null, values);
    }

    // Update a food in the Food table
    public void updateResto(Resto resto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESTO_COLUMN_NAME, String.valueOf(resto.getRname()));
        values.put(RESTO_COLUMN_LOGO, String.valueOf(resto.getRlogo()));
        values.put(RESTO_COLUMN_LOCATION, String.valueOf(resto.getRlocation()));
        values.put(RESTO_COLUMN_DAY, String.valueOf(resto.getDay()));

        db.update(TABLE_FOOD, values, RESTO_COLUMN_ID + " = ?", new String[]{String.valueOf(resto.get_rid())});
    }

    // Get single restaurant from the Resto table
    public Resto getResto(long resto_id) {
        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_RESTO + " WHERE " + RESTO_COLUMN_ID + " = " + resto_id + ";";

        Cursor c = db.rawQuery(query, null);

        if (c != null)
            c.moveToFirst();

        Resto resto = new Resto();
        resto.set_rid(Integer.parseInt(c.getString(0)));
        resto.setRname(c.getString(1));
        resto.setRlogo(c.getString(2));
        resto.setRlocation(c.getString(3));
        resto.setDay(c.getString(4));

        return resto;
    }

    // Get all restaurants from the Resto table
    public List<Resto> getAllRestos() {
        List<Resto> restoList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_RESTO + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null)
            c.moveToFirst();

        // Loop through all rows and add each to list
        while(!c.isAfterLast()) {
            Resto resto = new Resto();
            resto.set_rid(Integer.parseInt(c.getString(0)));
            resto.setRname(c.getString(1));
            resto.setRlogo(c.getString(2));
            resto.setRlocation(c.getString(3));
            resto.setDay(c.getString(4));

            restoList.add(resto);

            c.moveToNext();
        }

        return restoList;
    }

    // Get number of restaurants in Resto table
    public int getRestoCount() {
        String query = "SELECT * FROM " + TABLE_RESTO + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        try {
            return c.getCount();
        } finally {
            c.close();
            db.close();
        }
    }

    // Get all foods under a restaurant
    public List<Food> getAllFoodsByResto(String rName, String day) {
        List<Food> foodList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_FOOD + " fd, " + TABLE_MENU + " mn, " + TABLE_RESTO_MENU
                + " rm, " + TABLE_RESTO + " rs, " + TABLE_MENU_FOOD + " mf WHERE rs." + RESTO_COLUMN_NAME
                + " = '" + rName + "'" + " AND rs." + RESTO_COLUMN_ID  + " = rm." + KEY_RESTO_ID + " AND mn." + MENU_COLUMN_ID
                + " = rm." + KEY_MENU_ID + " AND mn." + MENU_COLUMN_ID + " = mf." + KEY_MENU_ID + " AND fd."
                + FOOD_COLUMN_ID + " = mf." + KEY_FOOD_ID + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null)
            c.moveToFirst();

        // Loop through all rows and add each to list
        while(!c.isAfterLast()) {
            Food food = new Food();
            food.set_fid(Integer.parseInt(c.getString(0)));
            food.setFname(c.getString(1));
            food.setFicon(c.getString(2));
            food.setmName(c.getString(3));

            foodList.add(food);

            c.moveToNext();
        }

        return foodList;
    }

    // Add a new food to the Food table
    public void addFood(Food food) {
        ContentValues values = new ContentValues();
        values.put(FOOD_COLUMN_NAME, String.valueOf(food.getFname()));
        values.put(FOOD_COLUMN_ICON, String.valueOf(food.getFicon()));
        values.put(MENU_COLUMN_NAME, String.valueOf(food.getmName()));
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_FOOD, null, values);
        db.close();
    }

    // Update a food in the Food table
    public void updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FOOD_COLUMN_NAME, String.valueOf(food.getFname()));
        values.put(FOOD_COLUMN_ICON, String.valueOf(food.getFicon()));
        values.put(MENU_COLUMN_NAME, String.valueOf(food.getmName()));

        db.update(TABLE_FOOD, values, FOOD_COLUMN_ID + " = ?", new String[]{String.valueOf(food.get_fid())});
    }

    // Get single food from the Food table
    public Food getFood(long food_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FOOD + " WHERE " + FOOD_COLUMN_ID + " = " + food_id + ";";

        Cursor c = db.rawQuery(query, null);

        if (c != null)
            c.moveToFirst();

        Food food = new Food();
        food.set_fid(Integer.parseInt(c.getString(0)));
        food.setFname(c.getString(1));
        food.setFicon(c.getString(2));
        food.setmName(c.getString(3));

        return food;
    }

    // Get all foods from the Food table
    public List<Food> getAllFoods() {
        List<Food> foodList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_FOOD + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null)
            c.moveToFirst();

        // Loop through all rows and add each to list
        while(!c.isAfterLast()) {
            Food food = new Food();
            food.set_fid(Integer.parseInt(c.getString(0)));
            food.setFname(c.getString(1));
            food.setFicon(c.getString(2));
            food.setmName(c.getString(3));

            foodList.add(food);

            c.moveToNext();
        }

        return foodList;
    }

    // Get number of foods in Food table
    public int getFoodCount() {
        String query = "SELECT * FROM " + TABLE_FOOD + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        try {
            return c.getCount();
        } finally {
            c.close();
            db.close();
        }
    }

    // Get all foods under a menu
    public List<Food> getAllFoodsByMenu(String mName) {
        List<Food> foodList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_FOOD + " fd, " + TABLE_MENU + " mn, " + TABLE_MENU_FOOD
                + " mf WHERE mn." + MENU_COLUMN_NAME + " = '" + mName + "'" + " AND mn." + MENU_COLUMN_ID
                + " = mf." + KEY_MENU_ID + " AND fd." + FOOD_COLUMN_ID + " = mf." + KEY_FOOD_ID + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null)
            c.moveToFirst();

        // Loop through all rows and add each to list
        while(!c.isAfterLast()) {
            Food food = new Food();
            food.set_fid(Integer.parseInt(c.getString(0)));
            food.setFname(c.getString(1));
            food.setFicon(c.getString(2));
            food.setmName(c.getString(3));

            foodList.add(food);

            c.moveToNext();
        }

        return foodList;
    }

    // Add a new menu to the Menu table
    public void addMenu(Menu menu, long[] food_ids) {
        ContentValues values = new ContentValues();
        values.put(MENU_COLUMN_DAY, String.valueOf(menu.getDay()));
        values.put(MENU_COLUMN_NAME, String.valueOf(menu.getmName()));
        SQLiteDatabase db = getWritableDatabase();

        // Insert row
        long menu_id = db.insert(TABLE_MENU, null, values);

        // Assign foods to menu
        for (long food_id : food_ids) {
            addMenuFood(menu_id, food_id, MENU_COLUMN_NAME);
        }

        db.close();
    }

    //
    public void addMenuFood(long menu_id, long food_id, String mName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MENU_ID, menu_id);
        values.put(KEY_FOOD_ID, food_id);
        values.put(MENU_COLUMN_NAME, mName);

        db.insert(TABLE_MENU_FOOD, null, values);
    }

    // Update a menu in the Menu table
    public void updateMenu(Menu menu) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MENU_COLUMN_DAY, String.valueOf(menu.getDay()));
        values.put(MENU_COLUMN_NAME, String.valueOf(menu.getmName()));

        db.update(TABLE_FOOD, values, RESTO_COLUMN_ID + " = ?", new String[]{String.valueOf(menu.get_mid())});
    }

    // Get single menu from the Menu table
    public Menu getMenu(long menu_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_MENU + " WHERE " + FOOD_COLUMN_ID + " = " + menu_id + ";";

        Cursor c = db.rawQuery(query, null);

        if (c != null)
            c.moveToFirst();

        Menu menu = new Menu();
        menu.set_mid(Integer.parseInt(c.getString(0)));
        menu.setDay(c.getString(1));
        menu.setmName(c.getString(2));

        return menu;
    }

    // Get all menus from the Menu table
    public List<Menu> getAllMenus() {
        List<Menu> menuList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_MENU + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c != null)
            c.moveToFirst();

        // Loop through all rows and add each to list
        while(!c.isAfterLast()) {
            Menu menu = new Menu();
            menu.set_mid(Integer.parseInt(c.getString(0)));
            menu.setDay(c.getString(1));
            menu.setmName(c.getString(2));

            menuList.add(menu);

            c.moveToNext();
        }

        return menuList;
    }

    // Get number of menus in Menu table
    public int getMenuCount() {
        String query = "SELECT * FROM " + TABLE_MENU + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        try {
            return c.getCount();
        } finally {
            c.close();
            db.close();
        }
    }

}
