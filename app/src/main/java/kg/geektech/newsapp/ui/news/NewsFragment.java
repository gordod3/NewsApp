package kg.geektech.newsapp.ui.news;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kg.geektech.newsapp.R;
import kg.geektech.newsapp.databinding.FragmentNewsBinding;

public class NewsFragment extends Fragment {
    // Решил добавить статичный стринг чтобы точно не напартачить с названием ключа в будущем.
    public static final String KEY_FRAGMENT_NEWS_MODEL = "key_fragment_news_model", RK_FRAGMENT_NEWS_BUNDLE = "rk_fragment_news_bundle";
    private FragmentNewsBinding binding;
    // Думаю я пожалею что решил создать один bundle в onCreatedView но кажется
    // так приложение будет лучше оптимизировано... Лишь думаю.
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bundle = new Bundle();

        binding.fragmentNewsSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                close();
            }
        });
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

    private void save() {
        String title = binding.fragmentNewsEditTextTitle.getText().toString();
        NewsViewModel newsViewModel = new NewsViewModel(title, System.currentTimeMillis());
        bundle.putSerializable(KEY_FRAGMENT_NEWS_MODEL, newsViewModel);
        getParentFragmentManager().setFragmentResult(RK_FRAGMENT_NEWS_BUNDLE, bundle);
    }
}