package com.fedor.pavel.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;

import com.fedor.pavel.library.adapters.BooksRVAdapter;
import com.fedor.pavel.library.models.BookModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BooksTableActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.activity_books_table_rv_books_table)
    RecyclerView rvBooksTable;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.activity_books_table_rgSort)
    RadioGroup rgSort;

    private BooksRVAdapter booksRVAdapter;

    public static final String BOOK_KEY = "book";


    public static final String LOG_TAG = "BooksTableActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_table);

        ButterKnife.bind(this);


        if (savedInstanceState != null) {

            booksRVAdapter = new BooksRVAdapter(this, (ArrayList<BookModel>) savedInstanceState.getSerializable("books"));

            rgSort.check(savedInstanceState.getInt("sortId"));


        } else {

            rgSort.check(R.id.activity_books_table_rbRating);

            booksRVAdapter = new BooksRVAdapter(this, addBooks());

        }

        booksRVAdapter.sort(rgSort.getCheckedRadioButtonId());

        rgSort.setOnCheckedChangeListener(this);

        prepareRecyclerView();

        prepareToolbar();


    }


    private void prepareRecyclerView() {


        rvBooksTable.setAdapter(booksRVAdapter);

        rvBooksTable.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.numOffColumns)));


    }

    private void prepareToolbar() {


        setSupportActionBar(toolbar);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putSerializable("books", booksRVAdapter.getAllBooks());

        outState.putInt("sortId", rgSort.getCheckedRadioButtonId());

        super.onSaveInstanceState(outState);
    }

    private ArrayList<BookModel> addBooks() {

        ArrayList<BookModel> models = new ArrayList<>();

        models.add(new BookModel("http://files.parsetfss.com/73008297-8936-4fb1-99e4-feedd84aa07d/tfss-6648cf2b-eb84-41ca-994f-d43d42619341-Enchantment-Book-Cover-Best-Seller1.jpg"
                , "Enchantment: The Art of Changing Hearts", "Enchantment, as defined by bestselling business guru Guy Kawasaki, is not about manipulating people. It transforms situations and relationships. It converts hostility into civility and civility into affinity. " +
                "It changes skeptics and cynics into believers and the undecided into the loyal." +
                "Enchantment can happen during a retail transaction, a high-level corporate negotiation, " +
                "or a Facebook update. And when done right, it’s more powerful than traditional persuasion, influence, or marketing techniques.", 1));

        models.add(new BookModel("http://www.pattlindkyle.com/wp-content/uploads/2009/06/BookCover_new2-694x1024.jpg"
                , "Heal your mind rewire your Brain", "Yes, you can teach your old brain new tricks! " +
                "Breakthroughs in the scientific understanding of how the brain works have shown us that our brains " +
                "are constantly rewiring themselves in response to events in our lives. " +
                "This handbook applies this new science in practical ways, by giving us a training program to re-pattern our behavior " +
                "and thereby change the ways our brain is wired. It interrupts our suffering, sharpens our mental " +
                "abilities and corrects our cognitive imbalances. As we learn these mental skills, " +
                "the neural patterns of our brains begin to change and we literally reprogram the neural networks through which information and energy flows. " +
                "If you've heard about neuroplasticity, epigenetics, ", 4));

        models.add(new BookModel("https://www.smashingmagazine.com/images/book-covers/book-covers-18.jpg"
                , "A clockwork orange", "Fifteen-year-old Alex and his three friends start an evening’s mayhem by hitting an old man, " +
                "tearing up his books and stripping him of money and clothes.Or rather Alex and his three droogs tolchock an old veck," +
                " razrez his books, pull off his outer platties and take a malenky bit of cutter.For Alex’s confessions are written in " +
                "‘nadsat’—the teenage argot of a not-too-distant future.Because of his delinquent excesses, Alex is jailed and made " +
                "subject to “Ludovico’s Technique,” a chilling experiment in Reclamation Treatment…Horror farce? Social prophecy?" +
                " Penetrating study of human choice between good and evil? A “Clockwork Orange” is all three, dazzling proof of Anthony" +
                " Burgess’s vast talents.", 3));


        models.add(new BookModel("https://www.foe.co.uk/sites/default/files/atoms/the_last_wild_book_cover.jpg"
                , "The Last Wild", "follows a 12 year-old boy named Kester and his friend Polly, in a Dr Dolittle style adventure, " +
                "on a journey that keeps going wrong - with wonderful illustrations from Thomas Flintham. " +
                "Expect lots of talking animals – ranging from the majestic stag on the book’s cover to some very cheeky mini-beasts.", 2));


        models.add(new BookModel("https://i.guim.co.uk/img/media/9a19fedf27882429f0287ecf5ea24b0e5c582c3f/0_0_2359_3543/master/2359.jpg?w=700&q=85&auto=format&sharp=10&s=33948aa10e58c2d72724e389a5305e6e"
                , "The Last Wild", "The most evil and powerful dark wizard in history, Lord Voldemort, murdered married couple James and Lily Potter but mysteriously disappeared after failing to kill their infant son, Harry. " +
                "While the wizarding world celebrates Voldemort's apparent downfall, Professor Dumbledore, " +
                "Professor McGonagall and half-giant Rubeus Hagrid place the one-year-old orphan in the care of his surly and cold " +
                "Muggle uncle and aunt, Vernon and Petunia Dursley, with their spoiled and bullying son, Dudley.", 5));


        models.add(new BookModel("https://upload.wikimedia.org/wikipedia/en/e/e9/First_Single_Volume_Edition_of_The_Lord_of_the_Rings.gif"
                , "Lord of the rings ", "The Lord of the Rings is an epic high-fantasy novel written by English author J. R. R. Tolkien. " +
                "The story began as a sequel to Tolkien's 1937 fantasy novel The Hobbit, but eventually developed into a much larger work." +
                " Written in stages between 1937 and 1949," +
                " The Lord of the Rings is the best-selling novel ever written, with over 150 million copies sold", 5));


        return models;

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        booksRVAdapter.sort(checkedId);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){

            booksRVAdapter.getBook(requestCode).setRating(data.getFloatExtra("rating", booksRVAdapter.getBook(requestCode).getRating()));

            booksRVAdapter.notifyItemChanged(requestCode);

            if (rgSort.getCheckedRadioButtonId()==R.id.activity_books_table_rbRating){

                booksRVAdapter.sort(rgSort.getCheckedRadioButtonId());

            }
        }
    }
}
