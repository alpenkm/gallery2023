package com.android.gallery2023.settings;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.android.gallery2023.R;
import com.android.gallery2023.util.preferences.Prefs;

import org.horaapps.liz.ThemedActivity;

/**
 * Created by dnld on 12/9/16.
 */

public class GeneralSetting extends ThemedSetting {

    public GeneralSetting(ThemedActivity activity) {
        super(activity);
    }

    public void editNumberOfColumns() {
        AlertDialog.Builder multiColumnDialogBuilder = new AlertDialog.Builder(getActivity(), getActivity().getDialogStyle());
        View dialogLayout = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_multi_column, null);

        ((TextView) dialogLayout.findViewById(R.id.folders_title)).setTextColor(getActivity().getTextColor());
        ((TextView) dialogLayout.findViewById(R.id.media_title)).setTextColor(getActivity().getTextColor());
        ((TextView) dialogLayout.findViewById(R.id.folders_title_landscape)).setTextColor(getActivity().getTextColor());
        ((TextView) dialogLayout.findViewById(R.id.media_title_landscape)).setTextColor(getActivity().getTextColor());
        ((CardView) dialogLayout.findViewById(R.id.multi_column_card)).setCardBackgroundColor(getActivity().getCardBackgroundColor());

        dialogLayout.findViewById(R.id.multi_column_title).setBackgroundColor(getActivity().getPrimaryColor());
        final TextView nColFolders = dialogLayout.findViewById(R.id.n_columns_folders);
        final TextView nColMedia = dialogLayout.findViewById(R.id.n_columns_media);
        final TextView nColFoldersL = dialogLayout.findViewById(R.id.n_columns_folders_landscape);
        final TextView nColMediaL = dialogLayout.findViewById(R.id.n_columns_media_landscape);

        nColFolders.setTextColor(getActivity().getSubTextColor());
        nColMedia.setTextColor(getActivity().getSubTextColor());
        nColFoldersL.setTextColor(getActivity().getSubTextColor());
        nColMediaL.setTextColor(getActivity().getSubTextColor());

        SeekBar barFolders = dialogLayout.findViewById(R.id.seek_bar_n_columns_folders);
        SeekBar barMedia = dialogLayout.findViewById(R.id.seek_bar_n_columns_media);
        SeekBar barFoldersL = dialogLayout.findViewById(R.id.seek_bar_n_columns_folders_landscape);
        SeekBar barMediaL = dialogLayout.findViewById(R.id.seek_bar_n_columns_media_landscape);

        getActivity().themeSeekBar(barFolders);
        getActivity().themeSeekBar(barMedia);
        getActivity().themeSeekBar(barFoldersL);
        getActivity().themeSeekBar(barMediaL);

        /** Portrait Orientation */
        int folderColumnsPortrait = Prefs.getFolderColumnsPortrait();
        int mediaColumnsPortrait = Prefs.getMediaColumnsPortrait();
        nColFolders.setText(String.valueOf(folderColumnsPortrait));
        nColMedia.setText(String.valueOf(mediaColumnsPortrait));
        barFolders.setProgress(folderColumnsPortrait - 1);
        barMedia.setProgress(mediaColumnsPortrait - 2);
        barFolders.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nColFolders.setText(String.valueOf(i + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        barMedia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nColMedia.setText(String.valueOf(i + 2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        /** Landscape Orientation */
        int folderColumnsLandscape = Prefs.getFolderColumnsLandscape();
        int mediaColumnsLandscape = Prefs.getMediaColumnsLandscape();
        nColFoldersL.setText(String.valueOf(folderColumnsLandscape));
        nColMediaL.setText(String.valueOf(mediaColumnsLandscape));
        barFoldersL.setProgress(folderColumnsLandscape - 2);
        barMediaL.setProgress(mediaColumnsLandscape - 3);
        barFoldersL.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nColFoldersL.setText(String.valueOf(i + 2));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        barMediaL.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nColMediaL.setText(String.valueOf(i + 3));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        multiColumnDialogBuilder.setPositiveButton(getActivity().getString(R.string.ok_action).toUpperCase(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int nFolders = Integer.parseInt(nColFolders.getText().toString());
                int nMedia = Integer.parseInt(nColMedia.getText().toString());
                int nFoldersL = Integer.parseInt(nColFoldersL.getText().toString());
                int nMediaL = Integer.parseInt(nColMediaL.getText().toString());
                Prefs.setFolderColumnsPortrait(nFolders);
                Prefs.setMediaColumnsPortrait(nMedia);
                Prefs.setFolderColumnsLandscape(nFoldersL);
                Prefs.setMediaColumnsLandscape(nMediaL);
            }
        });
        multiColumnDialogBuilder.setNegativeButton(getActivity().getString(R.string.cancel).toUpperCase(), null);
        multiColumnDialogBuilder.setView(dialogLayout);
        multiColumnDialogBuilder.show();
    }
}
