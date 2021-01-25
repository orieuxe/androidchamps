package android.eservices.webrequests.presentation.bookdisplay.groupstage.fragment;

import android.eservices.webrequests.R;
import android.eservices.webrequests.presentation.bookdisplay.groupstage.adapter.ParticipantAdapter;
import android.eservices.webrequests.presentation.bookdisplay.groupstage.adapter.ParticipantInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GroupstageFragment extends Fragment implements ParticipantInterface {

    public static final String TAB_NAME = "Groupstage";
    private View rootView;
    private RecyclerView recyclerView;
    private ParticipantAdapter groupAdapter;

    private GroupstageFragment() {

    }

    public static GroupstageFragment newInstance() {
        return new GroupstageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_groupstage, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Character[] groups = {'A', 'B', 'C', 'D'};
        for (Character group: groups){
            setupGroupRecyclerView(group);
        }
        registerViewModels();
    }

    private void registerViewModels() {

    }

    private void setupGroupRecyclerView(Character group) {
        int id = getResources().getIdentifier("group_"+group.toString(), "id", getContext().getPackageName());
        recyclerView = rootView.findViewById(id);
        groupAdapter = new ParticipantAdapter(this);
        recyclerView.setAdapter(groupAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
