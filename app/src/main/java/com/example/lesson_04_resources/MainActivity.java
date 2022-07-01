package com.example.lesson_04_resources;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Подключили новый шрифт
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Splash-Regular.ttf");

        // ВЫВОД РЕСУРСОВ В СТРОКИ ПРОГРАММНО
        TextView descriptionLanguage = findViewById(R.id.textVLang);
        descriptionLanguage.setText(getString((R.string.descriptionLanguage)));

        // применили нов шрифт
        descriptionLanguage.setTypeface(tf);

        TextView textLanguage = findViewById(R.id.textLang);
        textLanguage.setText(getResources().getString(R.string.language));

        AppCompatImageView image = findViewById(R.id.imageView);
        loadImageFromAsset(image, "logo.png");

    }

    // Картинки так не грузим. Тут просто для примера.
    public void loadImageFromAsset (ImageView imageV, String filename) {

        InputStream inStr = null;
        try {
            inStr = getAssets().open(filename);
            // загружаем как Drawable
            Drawable d = Drawable.createFromStream(inStr, null);
            // выводим картинку в ImageView
            imageV.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}