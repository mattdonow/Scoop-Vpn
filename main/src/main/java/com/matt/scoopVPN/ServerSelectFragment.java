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
 * {@link OnServerSelectListener} interface
 * to handle interaction events.
 * Use the {@link ServerSelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServerSelectFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER





    private OnServerSelectListener mListener;
    private SharedPreferences ui_prefs;
    private String selectedCountry;

    public ServerSelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ServerSelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServerSelectFragment newInstance() {
        ServerSelectFragment fragment = new ServerSelectFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui_prefs = getContext().getSharedPreferences("UI_PREF", Context.MODE_PRIVATE);
        selectedCountry = ui_prefs.getString("selectedCountry", "US");
        String TAG = "MSDMSDMSDMSD";






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_server_select, container, false);
        xmlHandler myHandle = new xmlHandler(getContext());
        final String[] serverNames = myHandle.getServerNamesByCountry(selectedCountry);
        ListView listView = (ListView) view.findViewById(R.id.lview_server_select);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1 , serverNames);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("MSDMSD",serverNames[i] );
                SharedPreferences.Editor editor= ui_prefs.edit();
                editor.putString("selectedSerber", serverNames[i]);
                editor.commit();
                mListener.onServerSelect();

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onServerSelect();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnServerSelectListener) {
            mListener = (OnServerSelectListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnServerSelectListener");
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
    public interface OnServerSelectListener {
        // TODO: Update argument type and name
        void onServerSelect();
    }
}
