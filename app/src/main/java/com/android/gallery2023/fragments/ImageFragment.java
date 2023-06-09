package com.android.gallery2023.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.gallery2023.R;
import com.android.gallery2023.data.Media;
import com.android.gallery2023.util.BitmapUtils;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A Media Fragment for showing an Image (static)
 */
public class ImageFragment extends BaseMediaFragment {

    @BindView(R.id.subsampling_view)
    SubsamplingScaleImageView imageView;

    @NonNull
    public static ImageFragment newInstance(@NonNull Media media) {
        return BaseMediaFragment.newInstance(new ImageFragment(), media);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Uri mediaUri = media.getUri();

        imageView.setOrientation(BitmapUtils.getOrientation(mediaUri, getContext()));
        imageView.setImage(ImageSource.uri(mediaUri));
        setTapListener(imageView);

    }

    @Override
    public void onDestroyView() {
        imageView.recycle();
        super.onDestroyView();
    }

    /**
     * Rotate the currently displaying media image.
     *
     * @param rotationInDegrees The rotation in degrees
     */
    public void rotatePicture(int rotationInDegrees) {
        if (rotationInDegrees == -90 && imageView.getOrientation() == 0) {
            imageView.setOrientation(SubsamplingScaleImageView.ORIENTATION_270);
        } else {
            imageView.setOrientation(Math.abs(imageView.getOrientation() + rotationInDegrees) % 360);
        }
    }
}
