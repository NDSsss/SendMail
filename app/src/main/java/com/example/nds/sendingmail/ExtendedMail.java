package com.example.nds.sendingmail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExtendedMail extends Activity {
    int SELECTION = 3;
    Context mainContext;
    String title;
    String text;
    String from;
    String where;

    public ExtendedMail() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.extended_mail);
        this.mainContext = this;

        ((Button)this.findViewById(R.id.screen_sendnews_btn_send)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                sender_mail_async async_sending = new sender_mail_async();
                async_sending.execute(new Object[0]);
            }
        });
    }

    private class sender_mail_async extends AsyncTask<Object, String, Boolean> {
        ProgressDialog WaitingDialog;

        private sender_mail_async() {
        }

        protected void onPreExecute() {
            this.WaitingDialog = ProgressDialog.show(ExtendedMail.this, "Отправка данных", "Отправляем сообщение...", true);
        }

        protected void onPostExecute(Boolean result) {
            this.WaitingDialog.dismiss();
            Toast.makeText(ExtendedMail.this.mainContext, "Отправка завершена!!!", Toast.LENGTH_LONG).show();
        }

        protected Boolean doInBackground(Object... params) {

            try {
                MailSenderClass sender = new MailSenderClass("dddddd1997@gmail.com", "zsefbvcx132");
                sender.sendMail("This is Subject",
                        "This is Body",
                        "dimakuhta@yandex.ru",
                        "dimakuhta@yandex.ru");
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
//            try {
//                ExtendedMail.this.title = ((EditText)ExtendedMail.this.findViewById(R.id.screen_sendnews_et_title)).getText().toString();
//                ExtendedMail.this.text = ((EditText)ExtendedMail.this.findViewById(R.id.screen_sendnews_et_text)).getText().toString();
//                ExtendedMail.this.from = "from_post_msg@gmail.com";
//                ExtendedMail.this.where = "dimakuhta@yandex.ru";
//                MailSenderClass sender = new MailSenderClass("dddddd1997@gmail.com", "zsefbvcx132");
//                sender.sendMail(title,text,from,where);
//            } catch (Exception var3) {
//                Toast.makeText(ExtendedMail.this.mainContext, "Ошибка отправки сообщения!", Toast.LENGTH_LONG).show();
//            }

            return false;
        }
    }
}