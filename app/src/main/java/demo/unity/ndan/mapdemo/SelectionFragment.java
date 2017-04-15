package demo.unity.ndan.mapdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SelectionFragment extends Fragment {
    Button mHybrid, mNormal;
    private MapContact mListener;
    String mButton1text, mButton2Text;

    public SelectionFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIArgument arg = (UIArgument) getArguments().getSerializable("text");
        mButton1text = arg.mbutton1Text;
        mButton2Text = arg.mButton2Text;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_selection, container, false);
        mHybrid = (Button) v.findViewById(R.id.hybrid);
        mNormal = (Button) v.findViewById(R.id.normal);

        mHybrid.setText(mButton1text);
        mNormal.setText(mButton2Text);

        mHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.setMyMapType("hybrid");
            }
        });
        mNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.setMyMapType("normal");
            }
        });

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MapContact) {
            mListener = (MapContact) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface MapContact {
        void setMyMapType(String type);
    }
}
