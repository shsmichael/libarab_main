package com.libarab.michaelg.libarab.favorites.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.libarab.michaelg.libarab.Item.ViewPagerActivity;
import com.libarab.michaelg.libarab.R;
import com.libarab.michaelg.libarab.User;
import com.libarab.michaelg.libarab.favorites.bean.Book;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import jp.wasabeef.glide.transformations.BlurTransformation;


public class BookCoverFragment extends Fragment {

    private static final String ARG_BOOK_ID = "book_id";
    private static final String ARG_BOOK = "book";

    private Book book;
    private User user;
    // Michael gonic: Should be using Book !!!

    public static BookCoverFragment newInstance(int bookId) {
        BookCoverFragment fragment = new BookCoverFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_BOOK_ID, bookId);
        fragment.setArguments(args);
        return fragment;
    }

    public static BookCoverFragment newInstance(Book book) {
        BookCoverFragment fragment = new BookCoverFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_BOOK, book);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().containsKey(ARG_BOOK_ID)) {
                //book = DataSupport.find(Book.class, getArguments().getInt(ARG_BOOK_ID));
            } else if (getArguments().containsKey(ARG_BOOK)) {
                book = (Book) getArguments().getSerializable(ARG_BOOK);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fav_fragment_book_cover, container, false);

        ImageView ivBookCover = (ImageView) view.findViewById(R.id.book_cover);
        ImageView ivBookCoverBg = (ImageView) view.findViewById(R.id.book_cover_bg);



        ivBookCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 user  = (User) getActivity().getIntent().getSerializableExtra("user");
                Intent intent1=new Intent(getActivity(),ViewPagerActivity.class);
                //this record id used by the ViewPagerActivity
                intent1.putExtra("recordId",book.getBookid());
                intent1.putExtra("userId",user.getUsername());
                intent1.putExtra("usertype", user.getUserType());
                intent1.putExtra("type","book");
                intent1.putExtra("user",user);

                //this weblink used by the ViewPagerActivity
                //intent1.putExtra("webLink",bookList.get(position).getWeblink());
                // Remember that variable (user) is the private variable above that is sent by the search

                startActivity(intent1);

            }
        });

        //TextView tvRate = (TextView) view.findViewById(R.id.tv_cover_rate);
       // RatingBar rbRate = (RatingBar) view.findViewById(R.id.rb_cover_rate);

        //View viewRate = view.findViewById(R.id.book_rate);

        // Book cover
        Glide.with(ivBookCover.getContext())
                .load(book.getImage())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(new IconicsDrawable(getContext()).icon(GoogleMaterial.Icon.gmd_book).colorRes(R.color.boo_cover_icon))
                .error(new IconicsDrawable(getContext()).icon(GoogleMaterial.Icon.gmd_book).colorRes(R.color.boo_cover_icon))
                .into(ivBookCover);

        // background
        Glide.with(ivBookCoverBg.getContext())
                .load(book.getImage())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(new BlurTransformation(ivBookCoverBg.getContext(), 25, 3))
                .into(ivBookCoverBg);

        // Book Review
       // tvRate.setText(book.getAverage());
        //rbRate.setRating((Float.parseFloat(book.getAverage())/2));

        // Admission animated book cover
        Animation cover_an = AnimationUtils.loadAnimation(getContext(), R.anim.book_cover_anim);
        ivBookCover.startAnimation(cover_an);

        // Admission Anime Books Review
       // Animation rate_an = AnimationUtils.loadAnimation(getContext(), R.anim.book_cover_rate_anim);
        //viewRate.startAnimation(rate_an);

        return view;
    }
}
