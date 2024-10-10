package com.example.artistmarket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class UserProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView profileImageView;
    private TextView descriptionTextView;
    private EditText descriptionEditText;
    private ImageView editDescriptionButton;
    private ImageView saveDescriptionButton;
    private ImageView logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profileImageView = findViewById(R.id.profile_image);
        descriptionTextView = findViewById(R.id.description);
        descriptionEditText = findViewById(R.id.edit_description);
        editDescriptionButton = findViewById(R.id.edit_description_button);
        saveDescriptionButton = findViewById(R.id.save_description_button);
        logoutButton = findViewById(R.id.logout_button);

        editDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEditingDescription();
            }
        });

        saveDescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDescription();
            }
        });

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                Bitmap roundedBitmap = getRoundedCroppedBitmap(bitmap);
                profileImageView.setImageBitmap(roundedBitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getRoundedCroppedBitmap(Bitmap bitmap) {
        int widthLight = bitmap.getWidth();
        int heightLight = bitmap.getHeight();
        int radius = Math.min(widthLight, heightLight);

        Bitmap finalBitmap = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);

        final Canvas canvas = new Canvas(finalBitmap);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(radius / 2, radius / 2, radius / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return finalBitmap;
    }

    private void startEditingDescription() {
        String currentDescription = descriptionTextView.getText().toString();
        descriptionEditText.setText(currentDescription);

        descriptionTextView.setVisibility(View.GONE);
        descriptionEditText.setVisibility(View.VISIBLE);
        saveDescriptionButton.setVisibility(View.VISIBLE);
        editDescriptionButton.setVisibility(View.GONE);

        descriptionEditText.requestFocus();
    }

    private void saveDescription() {
        String newDescription = descriptionEditText.getText().toString();
        descriptionTextView.setText(newDescription);

        descriptionTextView.setVisibility(View.VISIBLE);
        descriptionEditText.setVisibility(View.GONE);
        saveDescriptionButton.setVisibility(View.GONE);
        editDescriptionButton.setVisibility(View.VISIBLE);
    }
}
