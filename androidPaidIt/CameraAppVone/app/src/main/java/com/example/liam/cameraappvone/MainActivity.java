package com.example.liam.cameraappvone;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //bCamera = (Button) findViewById(R.id.button1);
    static int TAKE_PIC =1;
    Uri outPutfileUri;
    ImageView imageView;
    String formattedDate="";
    Drawable img;
    Bitmap bitmap;
    Drawable d;
    String timeStamp;
    TextView myText = null;
    Button bEmail;
    EditText etEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView = (ImageView) findViewById(R.id.image_view);
        setSupportActionBar(toolbar);



        Calendar c = Calendar.getInstance();
        //System.out.println("Current time =&gt; " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File file = getFile();

                outPutfileUri = Uri.fromFile(file);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

                startActivityForResult(intent, TAKE_PIC);

            }
        });








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private File getFile() {
        Calendar c = Calendar.getInstance();

        File folder = new File("sdcard/CameraAppVone");
        if(!folder.exists()){
            folder.mkdir();
        }
        //  String formattedDate=;
        // File image_file = new File(folder, c.getTime() +"cam_image.jpg" );
        timeStamp= c.getTime().toString();

        File image_file = new File(folder, "cam_image.jpg" );
        return image_file;
    }












    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        if (requestCode == TAKE_PIC && resultCode==RESULT_OK){
            //Toast.makeText(this, outPutfileUri.toString(),Toast.LENGTH_LONG).show();
        }



        //ImageView iv  = (ImageView)waypointListView.findViewById(R.id.waypoint_picker_photo);
        //  Bitmap d = new BitmapDrawable(ctx.getResources(), w.photo.getAbsolutePath()).getBitmap();
        //  int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
        //Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
        //iv.setImageBitmap(scaled);
        //new File("image_file").getName();
        //System.out.print(timeStamp);
        String path = "storage/emulated/0/CameraAppVone/"+ getFile().getName();
        //img = Drawable.createFromPath(path);
        //  bitmap = ((BitmapDrawable) img).getBitmap();
        //d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 50, true));

        imageView.setImageDrawable(Drawable.createFromPath(path));


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.setType("image/jpg");
        intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(getFile()));//pngFile
        intent.putExtra(Intent.EXTRA_SUBJECT, "PaidIt receipt");
        intent.putExtra(Intent.EXTRA_TEXT, timeStamp);

        startActivity(Intent.createChooser(intent, "Send Email"));


        Toast.makeText(getApplicationContext(), timeStamp, Toast.LENGTH_LONG).show();
    }
}

/*   BUTTTON EMAIL CODE
     <EditText
        android:id="@+id/etEmail"
        android:layout_width="170dp"
        android:layout_height="50dp"
        android:layout_above="@+id/bEmail"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />
    <Button
        android:id="@+id/bEmail"
        android:text="Email"
        android:layout_width="170dp"
        android:layout_height="50dp"
        android:layout_alignParentBottom="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:layout_marginBottom="38dp" />*/