/*
 * Copyright (c) 2012-2018 Arne Schwabe
 * Distributed under the GNU GPL v2 with additional terms. For full terms see the file doc/LICENSE.txt
 */

package com.matt.scoopVPN;
import de.blinkt.openvpn.R;
//import android.app.ListFragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//import android.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCountrySelectListener} interface
 * to handle interaction events.
 * Use the {@link CountrySelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountrySelectFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnCountrySelectListener mListener;

    public CountrySelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CountrySelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CountrySelectFragment newInstance(String param1, String param2) {
        CountrySelectFragment fragment = new CountrySelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country_select, container, false);

        final SharedPreferences ui_prefs = getContext().getSharedPreferences("UI_PREF", Context.MODE_PRIVATE);
        String[] values={"A","B","C","D", "E", "F", "G", "H", "I",};
        final String countries[] = getCountries();
        ListView listView = (ListView) view.findViewById(R.id.lview_country_select);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1 , countries);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("MSDMSD",countries[i] );
                SharedPreferences.Editor editor= ui_prefs.edit();
                editor.putString("selectedCountry", countries[i]);
                editor.commit();
                mListener.onCountrySelect();

            }
        });

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCountrySelect();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCountrySelectListener) {
            mListener = (OnCountrySelectListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCountrySelectListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCountrySelectListener {
        // TODO: Update argument type and name
        void onCountrySelect();
    }
    private String[] getCountries() {
        xmlHandler myHandle= new xmlHandler(getContext());
        String countries[] =myHandle.getCountriesList();
        return countries;
    }

}
