package appone.bmp.com.cmysql;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.*;


public class MainActivity extends AppCompatActivity {
    private static final String url="jdbc:mysql://192.168.86.2:3306/dblog";
    private static final String user="root";
    private static final String pass="bounmy1234";
    private TextView fname,lname;
    private Button btnload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname = (TextView) findViewById(R.id.txtName);
        lname = (TextView) findViewById(R.id.txtSurname);
        btnload =(Button)findViewById(R.id.btnLoad);
        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute();
            }
        });
    }
    private class MyTask extends AsyncTask<Void, Void, Void>{
        private String fName="",lName="";
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection c =DriverManager.getConnection(url,user,pass);
                String sql="select * from tblog";
                ResultSet rs = c.createStatement().executeQuery(sql);
                rs.next();
                fName = rs.getString(2);
                lName = rs.getString(3);
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void Result) {
            fname.setText(fName);
            lname .setText(lName);
            super.onPostExecute(Result);
        }
    }
}
