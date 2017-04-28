package com.example.yujin_000.a09_arrayadapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button; //선택삭제 버튼
    ListView listView;
    FoodAdapter adapter;

    //Footer에 추가한 더보기 버튼
    //xml이 다르기 때문에 Button이라고 해주어도 상관없음.
    Button footerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //전체를 탐색할 때는 뒤에서부터 탐색해야 한다. 만약 앞에서부터 탐색할 경우
                //배열에서 하나씩 앞으로 당겨져서 삭제하려고 체크한게 삭제되지 않는 경우가 발생하기 때문이다.
                for (int i = adapter.getCount()-1; i>-1; i--){
                    Food item = adapter.getItem(i);


                    if(item.check){
                        adapter.remove(item);
                    }
                }
            }
        }); //end OnClickListener

        /**FooterView 만들기*/
        //추가적인 디자인 파일 로드하기(xml파일 여러개를 독자적으로)
        View footerview = getLayoutInflater().inflate(R.layout.food_footer,null);

        footerButton = (Button) footerview.findViewById(R.id.button);
        footerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //adapter에 데이터를 추가하기 위한 함수 호출
                addData();
            }
        }); //end OnClickListener

        listView = (ListView) findViewById(R.id.listView);

        //ListView에 FooterView 추가하기 (adapter작업 전에 해야 한다.)
        listView.addFooterView(footerview);

        adapter = new FoodAdapter(MainActivity.this,
                R.layout.food_item, new ArrayList<Food>());

        // listView에 adapter연결
        listView.setAdapter(adapter);

        // 데이터 추가하기
        addData();
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

    /** 데이터와 한 줄의 레이아웃을 병합하여 ListView에게 전달하는 역할의 클래스 */
    class FoodAdapter extends ArrayAdapter<Food> {

        int resource;

        public FoodAdapter(Context context, int resource, List<Food> objects) {
            super(context, resource, objects);
            // 전달받은 XML의 id를 멤버변수에 복사 << R.layout.food_item
            this.resource = resource;
        }

        /**
         * ListView에 의해서 호출되는 메서드.
         * @param position      ListView 내에서의 위치 (index)
         * @param convertView   최초에 리턴한 한 줄의 레이아웃을 돌려받는다.
         * @param parent        ListView 자체
         * @return              데이터와 레이아웃의 병합 결과를 리턴한다
         */
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // ListView에게서 받은 View객체를 저장한다. -> 최초 1회 호출시 무조건 null
            View view = convertView;    // food_item.xml에 대한 객체

            // (1) Holder객체를 선언
            FoodHolder holder;

            if (view == null) { // <-- 이 메서드가 ListView에 의해서 최초로 호출된 시점

                // 레이아웃 XML파일을 열어서 객체로 저장한다.
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(this.resource, null);

                /*
                 (2) Holder안에 객체 참조를 저장
                 이렇게 객체참조에 저장을 해주어야 객체를 수백개, 수천개 만들어도 1:1로 삭제한 부분처럼 만들어줄 필요가 없음.
                 그냥 이 참조 코드 하나면 객체 갯수에 상관없이 참조 가능! 때문에 속도가 굉장히 빨라짐.

                  */
                holder = new FoodHolder();
                holder.imageView = (ImageView) view.findViewById(R.id.imageView);
                holder.textView1 = (TextView) view.findViewById(R.id.textView1);
                holder.textView2 = (TextView) view.findViewById(R.id.textView2);
                holder.button = (Button) view.findViewById(R.id.button);
                holder.checkBox = (CheckBox)view.findViewById(R.id.checkBox);
                // (3) ListView와 지속적으로 주고 받는 view객체 안에 holder를 저장한다.
                view.setTag(holder);
            }

            /*
            위의 (2) Holder안에 객체 참조를 저장 부분을 코딩하였기 때문에 이 부분은 삭제해준다!

            // view객체(food_item.xml) 안에서 컴포넌트를 꺼낸다

            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textView1 = (TextView) view.findViewById(R.id.textView1);
            TextView textView2 = (TextView) view.findViewById(R.id.textView2);

            */

            // 요청된 위치에 대한 데이터를 꺼낸다.
            final Food item = getItem(position);

            if (item != null) {
                //View안에 저장되어 있는 holder를 추출
                holder = (FoodHolder) view.getTag();

                // 추출한 컴포넌트에 데이터를 출력한다.
                holder.imageView.setImageResource(item.image);
                holder.textView1.setText(item.name);
                holder.textView2.setText(item.description);

                holder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*
                        이 안에서 자유롭게 사용할 수 있는 것
                        -->외부 클래스의 메서드와 멤버변수, 외부의 상수
                        이 안에서 사용할 수 없는 것
                        -->외부의 지역변수
                        */

                        //ArrayAdaptor의 remove()메서드로 개체 삭제가 가능
                        //얘는 특이하게 item이라는 위치를 직접!가져온다. (원래는 배열을 가져와서 삭제하는게 일반적.)
                        //위에서 final을 붙인 이유 : final을 붙이게되면 그 값은 더 이상 변경할 수 없는 상수가 되므로 그 값을 분명히 전달 할 수있다.

                        remove(item);


                    }
                }); //end OnClickListener

                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        item.check = isChecked;
                    }
                }); //end OnCheckedChangeListener

                //체크박스의 상태를 데이터객체의 값으로 강제 설정
                holder.checkBox.setChecked(item.check);

            }

            return view;
        }
    }

}






