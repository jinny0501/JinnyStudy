package com.example.yujin_000.a09_arrayadapter;

/**
 * Created by yujin_000 on 2017-04-28.
 */
public class Food {
    public String name;        // 이름
    public String description; // 설명
    public int image;          // 이미지 리소스 아이디
    public boolean check;

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }
}
