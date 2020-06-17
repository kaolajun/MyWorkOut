package com.nwpu.yanjin.myworkout;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nwpu.yanjin.myworkout.Database.Action;
import com.nwpu.yanjin.myworkout.ViewModel.ActionViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class AddActionFragment extends Fragment {

    private ActionViewModel actionViewModel;
    private EditText et_actionName,et_muscle;
    private Button btn_addAction;
    private String actionName;
    private String muscle;
    private String partOfBody;
    private ArrayAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addaction_fragment,container,false);
        actionViewModel = ViewModelProviders.of(getActivity()).get(ActionViewModel.class);
        et_actionName = view.findViewById(R.id.et_actionname);
        et_muscle = view.findViewById(R.id.et_muscle);
        btn_addAction = view.findViewById(R.id.btn_addaction);
        partOfBody = getArguments().getString("partOfBody");
//        et_actionName.onche
//        if (TextUtils.isEmpty(actionName) || TextUtils.isEmpty(muscle)){
//            btn_addAction.setEnabled(false);
//        }else{
//            btn_addAction.setEnabled(true);
//        }
        btn_addAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Action> allActionsLive = actionViewModel.getAllActionsLive().getValue();
                actionName = et_actionName.getText().toString();
                muscle = et_muscle.getText().toString();
                if (!TextUtils.isEmpty(actionName)) {
                    if (allActionsLive.size() == 0) {
                        actionViewModel.insertActions(new Action(actionName, muscle, partOfBody));
                    }
                    for (int i = 0; i < allActionsLive.size(); i++) {
                        if (actionName.equals(allActionsLive.get(i).getActionName())) {
                            actionViewModel.updateActions(new Action(actionName, muscle, partOfBody));
                            break;
                        }
                        if (i == allActionsLive.size() - 1) {
                            actionViewModel.insertActions(new Action(actionName, muscle, partOfBody));
                        }
                    }
                    NavController controller = Navigation.findNavController(v);
                    controller.navigate(R.id.action_addActionFragment_to_actionFragment);
                }else {
                    Toast.makeText(getContext(),"请输入动作名称",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
