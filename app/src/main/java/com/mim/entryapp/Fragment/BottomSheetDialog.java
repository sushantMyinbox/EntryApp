package com.mim.entryapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mim.entryapp.R;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)

    {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);

        v.setBackgroundResource(R.drawable.rounded_corner);
        return v;



//        Button algo_button = v.findViewById(R.id.algo_button);
//        Button course_button = v.findViewById(R.id.course_button);

//        algo_button.setOnClickListener(v12 -> {
//            Toast.makeText(getActivity(), "Algorithm Shared", Toast.LENGTH_SHORT)
//                    .show();
//            dismiss();
//        });
//
//
//        course_button.setOnClickListener(v1 -> {
//            Toast.makeText(getActivity(), "Course Shared", Toast.LENGTH_SHORT)
//                    .show();
//            dismiss();
//        });

    }
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        return new BottomSheetDialog(requireContext(),
//                android.R.style.Theme_Translucent_NoTitleBar); // To have transparent dialog window background.
//    }
}
