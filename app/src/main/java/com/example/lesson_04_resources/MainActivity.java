package com.example.lesson_04_resources;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        inList();

    }

    private void inList() {
        LinearLayout layoutList = findViewById(R.id.layoutList);
        String [] versions = getResources().getStringArray(R.array.version_names);
        // При помощи этого объекта будем надувать элементы, спрятанные в android_item.xml
        // Сами элементы вставляются при помощи inflater. При помощи метода getLayoutInflater()
        // получаем inflater, где вызываем метод inflate(). Этот метод создаёт пользовательский элемент на
        // основе переданного макета, а также передаёт, куда мы будем добавлять новый пользовательский
        // элемент. Булевым параметром будем определять, добавлять прямо сейчас или оставить добавление
        // на потом.
        LayoutInflater layoutInflater = getLayoutInflater();

        for (int i = 0; i < versions.length; i++) {
            String version = versions[i];
            // Достаём элемент из android_item.xml
           View item = layoutInflater.inflate(R.layout.android_item, layoutList, false);
            // Находим в этом элементе TextView
            TextView tv = item.findViewById(R.id.textAndroid);
            tv.setText(version);
            // Получить из ресурсов массив указателей на изображения
            // при помощи метода obtainTypedArray() получаем идентификаторы изображений в массив TypedArray. Чтобы
            // получить идентификатор самого изображения, используем getResourceId() на массиве
            // TypedArray. TypedArray следует использовать, когда необходимо получить данные, связанные с
            // пользовательским интерфейсом, в данном случае — с идентификаторами изображений. Если просто
            // получить массив целых значений, то идентификаторы ресурсов ещё не будут готовы.
            TypedArray imgs = getResources().obtainTypedArray(R.array.version_logos);
            // Выбрать по индексу подходящее изображение
            AppCompatImageView imgLogo = item.findViewById(R.id.imageAndroid);
            imgLogo.setImageResource(imgs.getResourceId(i, -1));

            layoutList.addView(item);

        }



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