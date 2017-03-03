package com.example.quanla.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {

    private ImageButton imbImage;
    private EditText edtTitle;
    private EditText edtPost;
    private Button btnSubmit;
    private Uri imageUri;

    private static final int GALLERY_REQUEST = 1;

    private StorageReference storage;
    private DatabaseReference data;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        storage = FirebaseStorage.getInstance().getReference();
        data = FirebaseDatabase.getInstance().getReference().child("Blog");

        imbImage = (ImageButton) findViewById(R.id.imb);
        edtTitle = (EditText) findViewById(R.id.edt_title);
        edtPost = (EditText) findViewById(R.id.edt_post);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        progress = new ProgressDialog(this);

        imbImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Request mobile to get content
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });

    }

    private void startPosting() {
        progress.setMessage("Posting to Blog...");
        progress.show();

        final String title = edtTitle.getText().toString().trim();
        final String desciption = edtPost.getText().toString().trim();
        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(desciption) && imageUri != null){
            StorageReference filePath = storage.child("Blog_Image").child(imageUri.getLastPathSegment());
            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    DatabaseReference newPost = data.push();
                    newPost.child("title").setValue(title);
                    newPost.child("desc").setValue(desciption);
                    newPost.child("image").setValue(downloadUrl.toString());

                    progress.dismiss();
                    startActivity(new Intent(PostActivity.this, MainActivity.class));
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Get content
        if(requestCode==GALLERY_REQUEST && resultCode == RESULT_OK){
            imageUri = data.getData();
            imbImage.setImageURI(imageUri);
        }
    }
}
