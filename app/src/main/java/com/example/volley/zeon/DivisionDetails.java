package com.example.volley.zeon;



import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.zeon.Util.Constants;
import com.example.volley.zeon.widget.ProgressWheelFolder.ProgressWheel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;


public class DivisionDetails extends AppCompatActivity {

    private NestedScrollView view;
    private Animation animationa;

    private ProgressWheel mProgressWheel;

    private CircleImageView member_photo;

    private TextView member_name;

    private TextView member_email;

    private TextView member_face_account;

    private TextView member_phone_num;

    private TextView member_department;

    private TextView member_skills;

    private TextView member_brief;

    private TextView member_projects;

    private RequestQueue requestQueue;

    private Integer id;
    private String imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_division);

        initUI();

        animationa= AnimationUtils.loadAnimation(this,R.anim.from_right);
        view.startAnimation(animationa);

        getJsonDetail();

    }

   public void initUI(){

        view=findViewById(R.id.view);

       mProgressWheel = findViewById(R.id.progress_wheel);

       member_photo=findViewById(R.id.member_image);

       member_name=findViewById(R.id.member_name_detail);

       member_email=findViewById(R.id.member_email_detail);

       member_face_account=findViewById(R.id.facebook_account_detail);

       member_phone_num=findViewById(R.id.phone_number_detail);

       member_department=findViewById(R.id.departments_detail);

       member_skills=findViewById(R.id.skills_detail);

       member_brief=findViewById(R.id.brief_detail);

       member_projects=findViewById(R.id.projects_detail);

       id=getIntent().getExtras().getInt("ID");

       imageURI=getIntent().getExtras().getString("IMAGE");

       Picasso.get().load(imageURI).fit().centerInside().into(member_photo);

       requestQueue= Volley.newRequestQueue(this);


   }


   public void getJsonDetail()
   {
       JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Constants.DIVISION_DETAIL_URL + id,
               (JSONObject) null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {

               try {
                   JSONArray jsonArray=response.getJSONArray("result");

                   JSONObject jsonObject=jsonArray.getJSONObject(0);

                   if(jsonObject.has("Name"))
                       member_name.setText(jsonObject.getString("Name"));

                   if(jsonObject.has("Email"))
                       member_email.setText(jsonObject.getString("Email"));

                   if(jsonObject.has("Facebook_account"))
                       member_face_account.setText(jsonObject.getString("Facebook_account"));

                   if(jsonObject.has("Phone_number"))
                       member_phone_num.setText(jsonObject.getString("Phone_number"));

                   if(jsonObject.has("Department"))
                       member_department.setText(jsonObject.getString("Department"));

                   if(jsonObject.has("Skills"))
                       member_skills.setText(jsonObject.getString("Skills"));

                   if(jsonObject.has("Brief"))
                       member_brief.setText(jsonObject.getString("Brief"));

                   if(jsonObject.has("Projects"))
                       member_projects.setText(jsonObject.getString("Projects"));


               } catch (JSONException e) {
                   e.printStackTrace();
               }
               mProgressWheel.setVisibility(View.INVISIBLE);
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(getApplicationContext(),"fgh",Toast.LENGTH_SHORT).show();

           }
       });
       requestQueue.add(jsonObjectRequest);
   }
}
