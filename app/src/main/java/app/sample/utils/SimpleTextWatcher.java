package app.sample.utils;

import android.text.Editable;
import android.text.TextWatcher;

public interface SimpleTextWatcher extends TextWatcher {

    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count) {
        onTextChanged(s.toString().trim());
    }

    @Override
    default void afterTextChanged(Editable s) {

    }

    void onTextChanged(String newText);

}
