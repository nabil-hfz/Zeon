package com.example.volley.zeon.MenuActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.volley.zeon.R;

public class ContactActivity extends AppCompatActivity {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = ContactActivity.class.getSimpleName();

    /**
     * TEAM EMAIL for send the  messages to it .
     */
    private static final String TEAM_EMAIL = "mahmoudtrro@gmail.com";

    /**
     * Name sender of  the  messages .
     */
    private String mNameSender = "";

    /**
     * Email sender of  the  messages .
     */
    private String nEmailSender = "";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us_activity);

        // Listener To Button when pressed the button .
        ListenerToButton();
    }

    private void ListenerToButton() {
        mButtonSendMessage = findViewById(R.id.ButtonSendFeedback);
        mButtonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllInfoAboutSender();

                if (getEveryThingIsOk() == false)
                    return;

                Intent mail = handlingInfo();

                if (mail.resolveActivity(getPackageManager()) != null) {
                    startActivity(mail);
                }
            }
        });
    }

    private void getAllInfoAboutSender() {
        EditText editText = findViewById(R.id.EditTextName);
        mNameSender = editText.getText().toString();

        editText = findViewById(R.id.EditTextEmail);
        nEmailSender = editText.getText().toString();

        editText = findViewById(R.id.EditTextSubject);
        mSubjectOfMessage = editText.getText().toString();

        Spinner feedbackSpinner = findViewById(R.id.SpinnerFeedbackType);
        mFeedbackType = feedbackSpinner.getSelectedItem().toString();

        editText = findViewById(R.id.EditTextFeedbackDetail);
        mBodyOfSentMessage = editText.getText().toString();

        CheckBox responseCheckbox = findViewById(R.id.CheckBoxResponse);
        mRequireResponse = responseCheckbox.isChecked();
    }

    private boolean getEveryThingIsOk() {
        if (mNameSender == null || mNameSender.isEmpty()) {
            Toast.makeText(ContactActivity.this, "Sorry Field Name is Empty", Toast.LENGTH_LONG).show();
            return false;
        }
        if (nEmailSender == null || nEmailSender.isEmpty()) {
            Toast.makeText(ContactActivity.this, "Sorry Field Email is Empty", Toast.LENGTH_LONG).show();
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
        Intent mail = new Intent(Intent.ACTION_SENDTO);

        mail.putExtra(Intent.EXTRA_EMAIL, new String[]{TEAM_EMAIL});

        mail.putExtra(Intent.EXTRA_SUBJECT, mSubjectOfMessage);

        mail.putExtra(Intent.EXTRA_TEXT, transformToCompleteString());

        mail.setData(Uri.parse("mailto: "));

        return mail;
    }

    private String transformToCompleteString() {
        return "Sender Name : " + mNameSender + "\n" +
                "Sender Email : " + nEmailSender + "\n" +
                "Type Of Feedback : " + mFeedbackType + "\n" +
                RequiresResponseToSender() + "\n" +
                "The Message : " + mBodyOfSentMessage;
    }

    private String RequiresResponseToSender() {
        if (mRequireResponse)
            return "Sender want response from team";
        return "No need to response from team";
    }
}
