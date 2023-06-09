package com.android.gallery2023.settings;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

import com.android.gallery2023.R;
import com.android.gallery2023.util.StaticMapProvider;
import com.orhanobut.hawk.Hawk;

import org.horaapps.liz.ThemedActivity;

/**
 * Created by dnld on 12/9/16.
 */

public class MapProviderSetting extends ThemedSetting {

    public MapProviderSetting(ThemedActivity activity) {
        super(activity);
    }

    public void choseProvider() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity(), getActivity().getDialogStyle());
        View dialogLayout = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_map_provider, null);
        TextView dialogTitle = dialogLayout.findViewById(R.id.title);
        ((CardView) dialogLayout.findViewById(R.id.dialog_chose_provider_title)).setCardBackgroundColor(getActivity().getCardBackgroundColor());
        dialogTitle.setBackgroundColor(getActivity().getPrimaryColor());

        final RadioGroup mapProvider = dialogLayout.findViewById(R.id.radio_group_maps_provider);
        RadioButton radioGoogleMaps = dialogLayout.findViewById(R.id.radio_google_maps);
        RadioButton radioMapBoxStreets = dialogLayout.findViewById(R.id.radio_mapb_streets);
        RadioButton radioMapBoxDark = dialogLayout.findViewById(R.id.radio_mapb_dark);
        RadioButton radioMapBoxLight = dialogLayout.findViewById(R.id.radio_mapb_light);
        RadioButton radioTyler = dialogLayout.findViewById(R.id.radio_osm_tyler);

        getActivity().themeRadioButton(radioGoogleMaps);
        getActivity().themeRadioButton(radioMapBoxStreets);
        getActivity().themeRadioButton(radioMapBoxDark);
        getActivity().themeRadioButton(radioMapBoxLight);
        getActivity().themeRadioButton(radioTyler);

        ((TextView) dialogLayout.findViewById(R.id.header_proprietary_maps)).setTextColor(getActivity().getTextColor());
        ((TextView) dialogLayout.findViewById(R.id.header_free_maps)).setTextColor(getActivity().getTextColor());
        switch (StaticMapProvider.fromValue(Hawk.get(getActivity().getString(R.string.preference_map_provider),
                StaticMapProvider.GOOGLE_MAPS.getValue()))) {
            case GOOGLE_MAPS:
            default:
                radioGoogleMaps.setChecked(true);
                break;
            case MAP_BOX:
                radioMapBoxStreets.setChecked(true);
                break;
            case MAP_BOX_DARK:
                radioMapBoxDark.setChecked(true);
                break;
            case MAP_BOX_LIGHT:
                radioMapBoxLight.setChecked(true);
                break;
            case TYLER:
                radioTyler.setChecked(true);
                break;
        }

        dialogBuilder.setNegativeButton(getActivity().getString(R.string.cancel).toUpperCase(), null);
        dialogBuilder.setPositiveButton(getActivity().getString(R.string.ok_action).toUpperCase(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (mapProvider.getCheckedRadioButtonId()) {
                    case R.id.radio_google_maps:
                    default:
                        Hawk.put(getActivity().getString(R.string.preference_map_provider), StaticMapProvider.GOOGLE_MAPS.getValue());
                        break;
                    case R.id.radio_mapb_streets:
                        Hawk.put(getActivity().getString(R.string.preference_map_provider), StaticMapProvider.MAP_BOX.getValue());
                        break;
                    case R.id.radio_osm_tyler:
                        Hawk.put(getActivity().getString(R.string.preference_map_provider), StaticMapProvider.TYLER.getValue());
                        break;
                    case R.id.radio_mapb_dark:
                        Hawk.put(getActivity().getString(R.string.preference_map_provider), StaticMapProvider.MAP_BOX_DARK.getValue());
                        break;
                    case R.id.radio_mapb_light:
                        Hawk.put(getActivity().getString(R.string.preference_map_provider), StaticMapProvider.MAP_BOX_LIGHT.getValue());
                        break;
                }
            }
        });
        dialogBuilder.setView(dialogLayout);
        dialogBuilder.show();
    }
}
