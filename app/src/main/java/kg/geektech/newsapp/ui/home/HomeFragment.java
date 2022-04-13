package kg.geektech.newsapp.ui.home;

import static kg.geektech.newsapp.ui.NewsFragment.KEY_FRAGMENT_NEWS_MODEL;
import static kg.geektech.newsapp.ui.NewsFragment.RK_FRAGMENT_NEWS_BUNDLE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import kg.geektech.newsapp.R;
import kg.geektech.newsapp.databinding.FragmentHomeBinding;
import kg.geektech.newsapp.models.NewsViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fragmentHomeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(R.id.newsFragment);
            }
        });
        getParentFragmentManager().setFragmentResultListener(RK_FRAGMENT_NEWS_BUNDLE,
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        NewsViewModel newsViewModel = (NewsViewModel) result.getSerializable(KEY_FRAGMENT_NEWS_MODEL);
                        Log.d("HomeFragment", "Result - " + newsViewModel.getTitle());
                    }
                });
    }

    private void openFragment(int id) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}