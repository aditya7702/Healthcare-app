package com.example.healthcare_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    TextView tv;

    Button btn;

    private static String[][] doctor_details1 = {
            {"Doctor Name: Dr. Rajesh Sharma", "Hospital Address: Mumbai", "Exp: 12 years", "Mobile No: +91 98765 43210", "800"},
            {"Doctor Name: Dr. Priya Patel", "Hospital Address: Delhi", "Exp: 8 years", "Mobile No: +91 87654 32109", "700"},
            {"Doctor Name: Dr. Sanjay Kumar", "Hospital Address: Bengaluru", "Exp: 15 years", "Mobile No: +91 76543 21098", "900"},
            {"Doctor Name: Dr. Nisha Gupta", "Hospital Address: Kolkata", "Exp: 10 years", "Mobile No: +91 65432 10987", "750"},
            {"Doctor Name: Dr. Rohit Singh", "Hospital Address: Chennai", "Exp: 5 years", "Mobile No: +91 54321 09876", "600"}
    };

    private static String[][] doctor_details2 = {
            {"Doctor Name: Dr. Anjali Sharma", "Hospital Address: Jaipur", "Exp: 10 years", "Mobile No: +91 87654 32101", "900"},
            {"Doctor Name: Dr. Sameer Gupta", "Hospital Address: Hyderabad", "Exp: 6 years", "Mobile No: +91 76543 21092", "750"},
            {"Doctor Name: Dr. Deepika Patel", "Hospital Address: Ahmedabad", "Exp: 13 years", "Mobile No: +91 65432 10983", "850"},
            {"Doctor Name: Dr. Arjun Kumar", "Hospital Address: Pune", "Exp: 9 years", "Mobile No: +91 54321 09874", "700"},
            {"Doctor Name: Dr. Kavita Singh", "Hospital Address: Gurugram", "Exp: 4 years", "Mobile No: +91 43210 98765", "550"}
    };

    private static String[][] doctor_details3 = {
            {"Doctor Name: Dr. Rahul Verma", "Hospital Address: Chandigarh", "Exp: 7 years", "Mobile No: +91 76543 21097", "700"},
            {"Doctor Name: Dr. Sunita Gupta", "Hospital Address: Lucknow", "Exp: 11 years", "Mobile No: +91 65432 10986", "850"},
            {"Doctor Name: Dr. Manish Patel", "Hospital Address: Indore", "Exp: 8 years", "Mobile No: +91 54321 09875", "750"},
            {"Doctor Name: Dr. Pooja Sharma", "Hospital Address: Bhopal", "Exp: 3 years", "Mobile No: +91 43210 98764", "500"},
            {"Doctor Name: Dr. Ravi Kumar", "Hospital Address: Noida", "Exp: 14 years", "Mobile No: +91 87654 32103", "950"}
    };

    private static String[][] doctor_details4 = {
            {"Doctor Name: Dr. Naveen Gupta", "Hospital Address: Coimbatore", "Exp: 9 years", "Mobile No: +91 65432 10989", "800"},
            {"Doctor Name: Dr. Aarti Singh", "Hospital Address: Kochi", "Exp: 5 years", "Mobile No: +91 54321 09878", "600"},
            {"Doctor Name: Dr. Anuj Sharma", "Hospital Address: Shimla", "Exp: 12 years", "Mobile No: +91 43210 98767", "850"},
            {"Doctor Name: Dr. Sonali Patel", "Hospital Address: Patna", "Exp: 6 years", "Mobile No: +91 87654 32105", "700"},
            {"Doctor Name: Dr. Rohan Kumar", "Hospital Address: Jaipur", "Exp: 11 years", "Mobile No: +91 76543 21094", "900"}
    };

    private static String[][] doctor_details5 = {
            {"Doctor Name: Dr. Ananya Gupta", "Hospital Address: Surat", "Exp: 8 years", "Mobile No: +91 54321 09879", "750"},
            {"Doctor Name: Dr. Vivek Singh", "Hospital Address: Nagpur", "Exp: 13 years", "Mobile No: +91 43210 98768", "900"},
            {"Doctor Name: Dr. Sushma Patel", "Hospital Address: Amritsar", "Exp: 6 years", "Mobile No: +91 87654 32107", "700"},
            {"Doctor Name: Dr. Ajay Sharma", "Hospital Address: Ranchi", "Exp: 10 years", "Mobile No: +91 76543 21096", "850"},
            {"Doctor Name: Dr. Divya Gupta", "Hospital Address: Varanasi", "Exp: 4 years", "Mobile No: +91 65432 10985", "600"}
    };

    String[][] doctor_details = {};
    HashMap<String,String> item;

    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewODTitle);
        btn = findViewById(R.id.buttonLTBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_details = doctor_details1;
        else if(title.compareTo("Dietitian")==0)
            doctor_details = doctor_details2;
        else if(title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else if(title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list = new ArrayList();

        for(int i=0;i<doctor_details.length;i++){

            item = new HashMap<String,String>();

            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5", "Cons Fees : " + doctor_details[i][4] + "/-");

            list.add(item);
        }

        sa = new SimpleAdapter(this,
                list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        ListView lst = findViewById(R.id.listViewLT);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);

                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });

    }
}