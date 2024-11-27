package com.dov.calculator.album;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dov.calculator.R;
import com.dov.calculator.webservice.AlbumApi;
import com.dov.calculator.webservice.AlbumService;
import com.dov.calculator.webservice.Photo;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import retrofit2.Call;

public class AlbumActivity extends AppCompatActivity {

    TextInputEditText albumIdEditText;
    MaterialTextView photoTitleTextView;
    ShapeableImageView photoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewItems();
    }

    private void setViewItems() {
        albumIdEditText = findViewById(R.id.id_et);
        photoTitleTextView = findViewById(R.id.photo_title);
        photoImageView = findViewById(R.id.photo_image);
        findViewById(R.id.search_btn).setOnClickListener(v -> search());
    }

    private void search() {
        if (albumIdEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Veuillez entrer un numéro d'album", Toast.LENGTH_SHORT).show();
            return;
        }
        callService();
    }

    private void callService() {
        AlbumService albumService = AlbumApi.getInstance().getRetrofitClient().create(AlbumService.class);
        Call<List<Photo>> call = albumService.getPhotos(Integer.parseInt(albumIdEditText.getText().toString()));
        call.enqueue(new retrofit2.Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, retrofit2.Response<List<Photo>> response) {
                updateView(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable throwable) {
                Toast.makeText(AlbumActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateView(List<Photo> body) {
        if (body != null && !body.isEmpty()) {
            Photo photo = body.get(0);
            photoTitleTextView.setText(photo.getTitle());
            Glide.with(this)
                    .load(photo.getUrl())
                    .into(photoImageView);
        } else {
            Toast.makeText(this, "Aucun résultat", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}