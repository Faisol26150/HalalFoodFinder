package com.aburubban.halalfoodfinder;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aburubban.halalfoodfinder.Common.Common;
import com.aburubban.halalfoodfinder.Database.Database;
import com.aburubban.halalfoodfinder.Model.Food;
import com.aburubban.halalfoodfinder.Model.Order;
import com.aburubban.halalfoodfinder.Model.Rating;
import com.andremion.counterfab.CounterFab;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.C;
import com.stepstone.apprating.listener.RatingDialogListener;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Arrays;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.R.attr.value;

public class FoodDetail extends AppCompatActivity implements RatingDialogListener{

    TextView food_name,food_price,food_description;
    ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart,btnRating;
    ElegantNumberButton numberButton;
    RatingBar ratingBar;

    String foodId="";
    FirebaseDatabase database;
    DatabaseReference foods;
    DatabaseReference ratingTbl;

    Food currentFood;



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // add this code
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Mitr-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        setContentView(R.layout.activity_food_detail);


        //firebase
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Foods");
        ratingTbl = database.getReference("Rating");

        // Init view
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);
        btnRating = (FloatingActionButton)findViewById(R.id.btn_rating);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRatingDialog();
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        currentFood.getName(),
                        numberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDiscont()
                ));

                Toast.makeText(FoodDetail.this, "ใส่ไปยังรถเข็นแล้ว", Toast.LENGTH_SHORT).show();

            }
        });

        CounterFab btnCart1 = (CounterFab) findViewById(R.id.btnCart1);
        btnCart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart1Intent = new Intent(FoodDetail.this,Cart.class);
                startActivity(cart1Intent);
            }
        });

        btnCart1.setCount(new Database(this).getCountCart());

        food_description = (TextView)findViewById(R.id.food_description);
        food_name = (TextView)findViewById(R.id.food_name);
        food_price = (TextView)findViewById(R.id.food_price);
        food_image = (ImageView)findViewById(R.id.img_food);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.CollapsedAppbar);

        //Get Food Id from Intent
        if (getIntent() != null)
            foodId = getIntent().getStringExtra("FoodId");
        if (!foodId.isEmpty())
        {
            if (Common.isConnectedToInterner(getBaseContext()))
            {
                getDetailFood(foodId);
                getRatingFood(foodId);
            }

            else
            {
                Toast.makeText(FoodDetail.this, "ตรวจสอบการเชื่อมต่ออินเทอร์เน็ต !!", Toast.LENGTH_SHORT).show();
                return;
            }
        }


    }

    private void getRatingFood(String foodId) {

        com.google.firebase.database.Query foodRating = ratingTbl.orderByChild("foodId").equalTo(foodId);

        foodRating.addValueEventListener(new ValueEventListener() {

            int count=0,sum=0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Rating item = postSnapshot.getValue(Rating.class);
                    sum+=Integer.parseInt(item.getRateValue());
                    count++;
                }
               if (count != 0)
               {
                   float average = sum/count;
                   ratingBar.setRating(average);
               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showRatingDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("ตกลง")
                .setNegativeButtonText("ยกเลิก")
                .setNoteDescriptions(Arrays.asList("แย่มาก","แย่","ดี","ดีมาก","สุดยอด"))
                .setDefaultRating(1)
                .setTitle("เรตติ้งเมนูอาหาร")
                .setDescription("กรุณาเลือกคะแนนดาวความพึงพอใจและแสดงความคิดตอบกลับ")
                .setTitleTextColor(R.color.colorPrimary)
                .setHint("กรุณาแสดงความคิดเห็นเพิ่มเติม")
                .setHintTextColor(android.R.color.white)
                .setCommentTextColor(android.R.color.white)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.RatingDialogFadeAnim)
                .create(FoodDetail.this)
                .show();
    }

    private void getDetailFood(final String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentFood = dataSnapshot.getValue(Food.class);

                //set Image
                Picasso.with(getBaseContext()).load(currentFood.getImage())
                        .into(food_image);
                collapsingToolbarLayout.setTitle(currentFood.getName());

                food_price.setText(currentFood.getPrice());

                food_name.setText(currentFood.getName());

                food_description.setText(currentFood.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onPositiveButtonClicked(int value,String comments) {
        //Get Rating and upload to firebase
        final Rating rating = new Rating(Common.currentUser.getPhone(),
                foodId,
                String.valueOf(value),
                comments);
        ratingTbl.child(Common.currentUser.getPhone()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(Common.currentUser.getPhone()).exists())
                {
                    //remove old value
                    ratingTbl.child(Common.currentUser.getPhone()).removeValue();
                    //Update new value
                    ratingTbl.child(Common.currentUser.getPhone()).setValue(rating);
                }
                else
                {
                    //Update new value
                    ratingTbl.child(Common.currentUser.getPhone()).setValue(rating);
                }
                Toast.makeText(FoodDetail.this, "ขอบคุณสำหรับการแสดงความคิดเห็น", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onNegativeButtonClicked() {

    }
}
