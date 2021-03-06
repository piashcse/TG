package mp.piash.tg.fragment;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mp.piash.tg.R;
import mp.piash.tg.adapter.AdapterAchievement;
import mp.piash.tg.adapter.AdapterPlay;
import mp.piash.tg.database.TechGaintHandler;

/**
 * A simple {@link} subclass.
 */
public class AchievementFragment extends DialogFragment {
    public static final String TAG = "AcheivementFragment";
    private AdapterAchievement mAdapterAchievement;
    private RecyclerView mRecyclerViewPlay;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Integer> mStringList;
    private TechGaintHandler mTechGaintHandler;

    public AchievementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_achievement, container, false);
        mRecyclerViewPlay = (RecyclerView)view.findViewById(R.id.recyclerViewAchievement);
        mTechGaintHandler = new TechGaintHandler(getActivity());



        if (!mTechGaintHandler.getAllAchievement().isEmpty()){
            mLayoutManager = new GridLayoutManager(getActivity(), 1);
            mRecyclerViewPlay.setLayoutManager(mLayoutManager);
            mAdapterAchievement = new AdapterAchievement(getActivity(), mTechGaintHandler.getAllAchievement());
            mRecyclerViewPlay.setAdapter(mAdapterAchievement);
        }else {
            Toast("There is no Achievement");
        }

        return view;
    }
    private void Toast(String string){
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout_root,
                (ViewGroup)getView().findViewById(R.id.toast_layout_root));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(string);

        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
