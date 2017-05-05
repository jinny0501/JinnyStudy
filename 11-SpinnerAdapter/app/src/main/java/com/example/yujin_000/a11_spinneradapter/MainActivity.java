package com.example.yujin_000.a11_spinneradapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button button;
    FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner);
        button = (Button)findViewById(R.id.button);
        adapter = new FoodAdapter(MainActivity.this, R.layout.food_item, new ArrayList<Food>());

        spinner.setAdapter(adapter);
        addData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = spinner.getSelectedItemPosition();
                Food item = adapter.getItem(position);
                Toast.makeText(MainActivity.this, item.description, Toast.LENGTH_SHORT).show();

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Food item = adapter.getItem(position);
                Toast.makeText(MainActivity.this, item.name, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    class FoodAdapter extends ArrayAdapter<Food>{

        int resource;

        public FoodAdapter(Context context, int resource, List<Food> objects){
            super(context, resource, objects);
            this.resource = resource;

        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            FoodHolder holder;

            if (view == null) {
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(this.resource, null);

                holder = new FoodHolder();
                holder.imageView = (ImageView) view.findViewById(R.id.imageView);
                holder.textView1 = (TextView) view.findViewById(R.id.textView1);
                holder.textView2 = (TextView) view.findViewById(R.id.textView2);

                view.setTag(holder);
            }

            final Food item = getItem(position);

            if (item != null) {
                holder = (FoodHolder) view.getTag();

                holder.imageView.setImageResource(item.image);
                holder.textView1.setText(item.name);
                holder.textView2.setText(item.description);
            }

            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return this.getView(position, convertView, parent);
        }
    }

    void addData() {
        Food f1 = new Food();
        f1.name = "딸기";
        f1.description = "꼭지가 마르지 않고 진한 푸른색을 띠는 것이 좋다.";
        f1.image = R.drawable.food1;
        adapter.add(f1);

        Food f2 = new Food();
        f2.name = "달래";
        f2.description = "알뿌리가 굵은 것일수록 향이 강하다.";
        f2.image = R.drawable.food2;
        adapter.add(f2);

        Food f3 = new Food();
        f3.name = "두릅";
        f3.description = "두릅순이 연하고 굵은 것, 잎이 피지 않는 것이 좋다.";
        f3.image = R.drawable.food3;
        adapter.add(f3);

        Food f4 = new Food();
        f4.name = "감자";
        f4.description = "감자의 표면에 흠집이 적으며 매끄러운 것이 좋다.";
        f4.image = R.drawable.food4;
        adapter.add(f4);

        Food f5 = new Food();
        f5.name = "참외";
        f5.description = "색깔이 선명하고 꼭지가 싱싱한지 확인하여 구입한다.";
        f5.image = R.drawable.food5;
        adapter.add(f5);

        Food f6 = new Food();
        f6.name = "복숭아";
        f6.description = "색깔이 선명하고 꼭지가 싱싱한지 확인하여 구입한다.";
        f6.image = R.drawable.food6;
        adapter.add(f6);

        Food f7 = new Food();
        f7.name = "배";
        f7.description = "껍질이 팽팽하며 묵직한 것을 고르고 상처가 없는 것이 좋다.";
        f7.image = R.drawable.food7;
        adapter.add(f7);

        Food f8 = new Food();
        f8.name = "고구마";
        f8.description = "모양이 고르고 병충해의 흠집이 없을 것이 좋다.";
        f8.image = R.drawable.food8;
        adapter.add(f8);

        Food f9 = new Food();
        f9.name = "사과";
        f9.description = "껍질에 탄력이 있고 꽉찬 느낌이 드는 것이 좋다.";
        f9.image = R.drawable.food9;
        adapter.add(f9);

        Food f10 = new Food();
        f10.name = "배추";
        f10.description = "배추 잎을 씹어 보면 고소한 맛이 나고 결구의 상태가 둥근 것이 좋다.";
        f10.image = R.drawable.food10;
        adapter.add(f10);

        Food f11 = new Food();
        f11.name = "유자";
        f11.description = "껍질이 단단하고 울퉁불퉁 한 것, 상처가 없는 것이 좋다.";
        f11.image = R.drawable.food11;
        adapter.add(f11);

        Food f12 = new Food();
        f12.name = "과매기";
        f12.description = "통통하고 살이 단단한 것을 고른다.";
        f12.image = R.drawable.food12;
        adapter.add(f12);
    }
}

