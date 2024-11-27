package com.dov.calculator.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dov.calculator.database.AppRoomDatabase;

public class AppContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.dov.calculator.provider";
    private static final String TABLE_NAME = "calculator";
    private AppRoomDatabase database;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int CODE_CALCULATORS = 1;

    static {
        uriMatcher.addURI(AUTHORITY, TABLE_NAME, CODE_CALCULATORS);
    }

    @Override
    public boolean onCreate() {
        database = AppRoomDatabase.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        Cursor cursor;
        if (uriMatcher.match(uri) == CODE_CALCULATORS) {
            return database.calculatorDao().getAllAsCursor();
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.android.cursor.dir/" + AUTHORITY + "." + TABLE_NAME;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        throw new IllegalArgumentException("Invalid URI for delete: " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new IllegalArgumentException("Invalid URI for delete: " + uri);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Update is not supported yet.");
    }
}
