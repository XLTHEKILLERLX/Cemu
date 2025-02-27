package info.cemu.Cemu;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import info.cemu.Cemu.databinding.FragmentInputSettingsBinding;

public class InputSettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentInputSettingsBinding binding = FragmentInputSettingsBinding.inflate(inflater, container, false);
        GenericRecyclerViewAdapter genericRecyclerViewAdapter = new GenericRecyclerViewAdapter();
        for (int index = 0; index < NativeLibrary.MAX_CONTROLLERS; index++) {
            int controllerIndex = index;
            int controllerType = NativeLibrary.isControllerDisabled(controllerIndex) ? NativeLibrary.EMULATED_CONTROLLER_TYPE_DISABLED : NativeLibrary.getControllerType(controllerIndex);
            String controllerTypeName = getString(NativeLibrary.controllerTypeToResourceNameId(controllerType));
            ButtonRecyclerViewItem buttonRecyclerViewItem = new ButtonRecyclerViewItem(
                    getString(R.string.controller_numbered, controllerIndex + 1),
                    getString(R.string.emulated_controller_with_type, controllerTypeName),
                    () -> {
                        Bundle bundle = new Bundle();
                        bundle.putInt(ControllerInputsFragment.CONTROLLER_INDEX, controllerIndex);
                        NavHostFragment.findNavController(InputSettingsFragment.this).navigate(R.id.action_inputSettingsFragment_to_controllerInputsFragment, bundle);
                    });
            genericRecyclerViewAdapter.addRecyclerViewItem(buttonRecyclerViewItem);
        }
        binding.inputSettingsRecyclerView.setAdapter(genericRecyclerViewAdapter);
        return binding.getRoot();
    }
}
