package id.sch.smktelkom_mlg.learn.bookku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.sch.smktelkom_mlg.learn.bookku.R;
import id.sch.smktelkom_mlg.learn.bookku.model.Book;

import static android.R.attr.data;

public class BookFormActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.editBookTitle)
    EditText editBooktittle;
    @BindView(R.id.editBookAuthor)
    EditText editBookAuthor;
    @BindView(R.id.editGenre)
    EditText editGenre;
    @BindView(R.id.editIsbn)
    EditText editIsbn;
    @BindView(R.id.editPublishedYear)
    EditText editPubLishedYear;
    @BindView(R.id.editSynopsis)
    EditText editSynopsis;
    @BindView(R.id.btnSave)
    Button btnSave;
    Book book;

    @Override
    protected void Oncreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            book = (Book) bundle.getSerializable("bookEdit");
            editIsbn.setText(book.getISBN());
            editPubLishedYear.setText(book.getPublished_year() + "");
            editBookAuthor.setText(book.getBook_author());
            editBooktittle.setText(book.getBook_title());
            editGenre.setText(book.getBook_genre());
            editSynopsis.setText(book.getBook_synopsis());
            btnSave.setEnabled(false);
            getSupportActionBar().setTitle(book.getBook_title());

        } else {
            book = new Book();

        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    book.setBook_title(editBooktittle.getText().toString());
                    book.setBook_author(editBookAuthor.getText().toString());
                    book.setPublished_year(Integer.parseInt(editPubLishedYear.getText().toString()));
                    book.setBook_genre(editGenre.getText().toString());
                    book.setBook_synopsis(editSynopsis.getText().toString());

                    Intent i = new Intent();
                    i.putExtra("book", book);
                    setResult(RESULT_OK);
                    finish();

                }
            }
        });
    }

    private boolean validate() {
        boolean valid = true;

        String isbn = editIsbn.getText().toString();
        String booktitle = editBooktittle.getText().toString();
        String bookauthor = editBookAuthor.getText().toString();
        String publishedYear = editPubLishedYear.getText().toString();

        if (isbn.isEmpty()) {
            editIsbn.setError("Enter ISBN");
            valid = false;
        } else {
            editIsbn.setError(null);
        }


        if (booktitle.isEmpty()) {
            editBooktittle.setError("Enter Book Title");
            valid = false;
        } else {
            editBooktittle.setError(null);
        }


        if (bookauthor.isEmpty()) {
            editBookAuthor.setError("Enter Book Author");
            valid = false;
        } else {
            editBookAuthor.setError(null);
        }


        if (publishedYear.isEmpty()) {
            editPubLishedYear.setError("published Year empty or must in yyy format");
            valid = false;
        } else {
            editPubLishedYear.setError(null);
        }

        return valid;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent Data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && resultCode == TO_FORM)
            Book bookform ==(Book) data.getExtras().getSerializable("book");

        List bookList;
        int bookform;
        bookList.add(bookform);
        Book bookForm;
        Toast.makeText(this, "Book" + bookForm.getBook_title() + "successfully added", Toast.LENGTH_SHORT).show();
    }
}




