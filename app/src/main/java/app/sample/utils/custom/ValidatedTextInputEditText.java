package app.sample.utils.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextWatcher;
import android.util.AttributeSet;

import app.sample.R;
import app.sample.utils.SimpleTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ValidatedTextInputEditText extends TextInputEditText {

    private String regex = null, textError;
    private TextInputLayout parent = null;
    private boolean isValid = false;
    private ValidatedTextInputEditText dependency = null;
    private OnValidateListener onValidateListener;

    private String dependencyText;
    private boolean isEmptyError = true, isWatching = true, isDependent = false;

    private TextWatcher textWatcher = (SimpleTextWatcher) this::check;
    private TextWatcher dependencyTextWatcher = (SimpleTextWatcher) this::setDependencyText;

    public ValidatedTextInputEditText(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ValidatedTextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ValidatedTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ValidatedTextInputEditText,
                defStyleAttr, 0);

        try {
            regex = a.getString(R.styleable.ValidatedTextInputEditText_regex);
            textError = a.getString(R.styleable.ValidatedTextInputEditText_textError);
            if (textError == null)
                textError = context.getString(R.string.error_required);
        } finally {
            a.recycle();
        }

        addTextChangedListener(textWatcher);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getParent().getParent() instanceof TextInputLayout) {
            parent = (TextInputLayout) getParent().getParent();
        }
    }

    public void setOnValidateListener(OnValidateListener onValidateListener) {
        this.onValidateListener = onValidateListener;
    }

    public void setWatching(boolean watching) {
        if (watching != isWatching) {

            if (isWatching) {
                removeTextChangedListener(textWatcher);
                if (parent != null)
                    parent.setError(null);
            } else
                addTextChangedListener(textWatcher);

            isWatching = watching;
        }
    }

    public void setEmptyError(boolean emptyError) {
        isEmptyError = emptyError;
    }

    private boolean validate(String text) {
        boolean error = false;
        if (isEmptyError && text.isEmpty()) {
            error = true;
//            setParentError(getContext().getString(R.string.error_required));
        } else if (isDependent && !text.equals(dependencyText)) {
            error = true;
            setParentError(textError);
        } else if (regex != null && !regex.isEmpty() && !text.matches(regex)) {
            error = true;
            setParentError(textError);
        } else {
            setParentError(null);
        }
        return !error;
    }

    private void setParentError(String error) {
        if (parent != null)
            parent.setError(error);
    }

    private void check(String text) {
        isValid = validate(text);
        if (onValidateListener != null)
            onValidateListener.onValidate(isValid);
    }

    private void setDependencyText(String dependencyText) {
        this.dependencyText = dependencyText;
        isValid();
    }

    public boolean isValid() {
        if (!isWatching)
            return true;

        check(getText().toString().trim());
        return isValid;
    }

    public void setDependency(ValidatedTextInputEditText dependency) {
        if (dependency == null)
            return;

        this.dependency = dependency;
        this.dependency.addTextChangedListener(dependencyTextWatcher);
        this.dependencyText = dependency.getText().toString();
        isDependent = true;
    }

    public void removeDependency() {
        if (this.dependency == null)
            return;

        dependency.removeTextChangedListener(dependencyTextWatcher);
        isDependent = false;
        this.dependency = null;
    }

    public interface OnValidateListener {

        void onValidate(boolean isValid);

    }

}
