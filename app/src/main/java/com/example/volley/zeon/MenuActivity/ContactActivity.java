package com.example.volley.zeon.MenuActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.volley.zeon.MainActivity;
import com.example.volley.zeon.R;
import com.example.volley.zeon.Util.Constants;
import com.example.volley.zeon.Util.UtilTools;

/**
 * Author of this class is Louay .
 * <p>
 * Update this class Nabil in in 2018/03/11
 */

public class ContactActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = ContactActivity.class.getSimpleName();
    /**
     * TextView for display an message say invalid email when the sender Types their email in wrong manner .
     */
    TextView textViewInvalidEmail;
    /**
     * Name sender of  the  messages .
     */
    private String mNameSender = "";
    /**
     * Email sender of  the  messages .
     */
    private String mEmailSender = "";
    /**
     * subject  of  the  messages .
     */
    private String mSubjectOfMessage = "";
    /**
     * Feedback Type in spinner  of the Sent message .
     */
    private String mFeedbackType = "";
    /**
     * Body of the Sent message .
     */
    private String mBodyOfSentMessage = "";
    /**
     * boolean email response from ZEON team email to sender the message .
     */
    private boolean mRequireResponse = false;
    /**
     * Button to send the message when the the sender make it .
     */
    private View mButtonSendMessage;
    /**
     * EditText to post the Email sender when the the sender make it .
     */
    private AppCompatEditText mEditTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us_activity);

        // Listener To Button when pressed the button .
        ListenerToButton();
    }

    private void ListenerToButton() {

        checkEmailValidPermanently();

        mButtonSendMessage = findViewById(R.id.ButtonSendFeedback);

        mButtonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllInfoAboutSender();

                if (getEveryThingIsOk() == false)
                    return;

                Intent mail = handlingInfo();

                try {
                    if (mail.resolveActivity(getPackageManager()) != null) {
                        startActivity(Intent.createChooser(mail, "Send mail..."));
                        Toast.makeText(ContactActivity.this, "The letter has been sent.", Toast.LENGTH_LONG);
                        finish();
                    }

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ContactActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkEmailValidPermanently() {
        textViewInvalidEmail = findViewById(R.id.invalid_email);
        textViewInvalidEmail.setVisibility(View.GONE);

        mEditTextEmail = findViewById(R.id.EditTextEmail);
        mEditTextEmail.addTextChangedListener(new TextWatcherForEmail());

    }

    private void getAllInfoAboutSender() {
        EditText editText = findViewById(R.id.EditTextName);
        mNameSender = editText.getText().toString().trim();

        mEmailSender = mEditTextEmail.getText().toString().trim();

        editText = findViewById(R.id.EditTextSubject);
        mSubjectOfMessage = editText.getText().toString().trim();


        Spinner feedbackSpinner = findViewById(R.id.SpinnerFeedbackType);
        mFeedbackType = feedbackSpinner.getSelectedItem().toString().trim();

        editText = findViewById(R.id.EditTextFeedbackDetail);
        mBodyOfSentMessage = editText.getText().toString().trim();

        CheckBox responseCheckbox = findViewById(R.id.CheckBoxResponse);
        mRequireResponse = responseCheckbox.isChecked();
    }

    private boolean getEveryThingIsOk() {
        if (mNameSender == null || mNameSender.isEmpty()) {
            Toast.makeText(ContactActivity.this, "Sorry Field Name is Empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (mEmailSender == null || mEmailSender.isEmpty()) {
            Toast.makeText(ContactActivity.this, "Sorry Field Email is Empty  ", Toast.LENGTH_LONG).show();
            //Log.v(LOG_TAG, "\n Email Sender : "+ mEmailSender +"\n");
            return false;
        }

        if (mSubjectOfMessage == null || mSubjectOfMessage.isEmpty()) {
            Toast.makeText(ContactActivity.this, "Sorry Field Subject is Empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (mFeedbackType == null || mFeedbackType.isEmpty()) {
            Toast.makeText(ContactActivity.this, "Sorry Field FeedbackType is Empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (mBodyOfSentMessage == null || mBodyOfSentMessage.isEmpty()) {
            Toast.makeText(ContactActivity.this, "Sorry Field Body Of Message is Empty", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private Intent handlingInfo() {
        Intent mail = new Intent(Intent.ACTION_SEND);

        mail.setType("message/rfc822");

        mail.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.TEAM_EMAIL[1]});

        mail.putExtra(Intent.EXTRA_SUBJECT, mSubjectOfMessage);

        mail.putExtra(Intent.EXTRA_TEXT, transformToCompleteString());

        // mail.setData(Uri.parse("mailto: "));

        return mail;

    }

    private String transformToCompleteString() {
        return "Sender Name : " + mNameSender + "\n" +
                "Sender Email : " + mEmailSender + "\n" +
                "Type Of Feedback : " + mFeedbackType + "\n" +
                RequiresResponseToSender() + "\n" +
                "The Message : " + mBodyOfSentMessage;
    }

    private String RequiresResponseToSender() {
        if (mRequireResponse)
            return "Sender want response from team";
        return "No need to response from team";
    }

    @Override
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onPrepareNavigateUpTaskStack(builder);
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        NavUtils.navigateUpTo(this, intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Press back to exit", Toast.LENGTH_SHORT).show();
    }
    //TODO : Advanced ...
    // This method for : MainActivity needs to know which album we want to display.
    // On TrackActivity, we need to override onPrepareSupportNavigateUpTaskStack to edit the intent
    // that will start the parent activity when pressing Up: specially to use when get Notification
    // and get back to mainActivity instead of go back to phone .

    private class TextWatcherForEmail implements TextWatcher {
        public TextWatcherForEmail() {

        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //Log.v(LOG_TAG, "\n beforeTextChanged \n");


        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable EmailAfterChange) {

            if (EmailAfterChange.length() == 0 || EmailAfterChange.equals("")) {
                mEditTextEmail.getBackground().mutate().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
                textViewInvalidEmail.setVisibility(View.GONE);

            } else if (UtilTools.isValidEmail(EmailAfterChange)) {
                textViewInvalidEmail.setText("Valid email address.");
                textViewInvalidEmail.setTextColor(getResources().getColor(R.color.valid_Email_Text));
                textViewInvalidEmail.setVisibility(View.VISIBLE);

                mEditTextEmail.getBackground().mutate().setColorFilter(getResources().getColor(R.color.valid_Email_EditText), PorterDuff.Mode.SRC_ATOP);

            } else {
                textViewInvalidEmail.setText("Please enter a valid email address");
                textViewInvalidEmail.setTextColor(getResources().getColor(R.color.invalid_Email_Text));
                textViewInvalidEmail.setVisibility(View.VISIBLE);
                mEditTextEmail.getBackground().mutate().setColorFilter(getResources().getColor(R.color.invalid_Email_EditText), PorterDuff.Mode.SRC_ATOP);
                //TODO : to make all context in the editTet with colors we need to do following :
                //TODO : Setting the android:textCursorDrawable attribute to @null should result in the use of android:textColor as the cursor color.
                //TODO : Attribute "textCursorDrawable" is available in API level 12 and higher
                //TODO : and make shape in drawable folder .  // mEditTextEmail.setTextColor(getResources().getColor(R.color.invalid_Email_EditText));
                // TO CHECK : https://stackoverflow.com/questions/7238450/set-edittext-cursor-color
            }
        }
    }

}