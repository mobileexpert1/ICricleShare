package com.icirklshare.com.icirklshare_Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.icirklshare.R;
import com.icrklshare_Helper.custom.Button_Roboto_Regular;
import com.icrklshare_Helper.custom.CannonballTwitterLoginButton;
import com.icrklshare_Helper.custom.EditText_Roboto_Regular;
import com.icrklshare_Helper.custom.TextView_Roboto_Regular;
import com.icrklshare_Helper.util.FinishAnimation;
import com.icrklshare_Helper.util.Static;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;

public class LoginActivity extends Activity {

    private LoginButton btn_facebook;
    private CallbackManager callbackManager;
    private String facebookAccessToken;
    private String birthday;
    private LoginActivity activity;
    private static final String TWITTER_KEY = "B7ZNVg3Aygu4IrDMf8WcIeyBL";
    private static final String TWITTER_SECRET = "GqFn6y0eJR7faHVSfc8ucVIQ8ujI3a34CC5VufMOTSM4mp5Qm6";
    private CannonballTwitterLoginButton loginButton;
    private TwitterSession session;
    private TwitterAuthToken authToken;
    private String name;
    private String profileurl="";
    private String id;
    private String email;
    private EditText_Roboto_Regular et_email;
    private EditText_Roboto_Regular et_password;
    private Button_Roboto_Regular bt_login;
    private TextView_Roboto_Regular tv_forgot,tv_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        FacebookSdk.sdkInitialize(getApplicationContext());
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activity = this;






        getUI();
        onClick();

        printKeyHash(activity);

    }


    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);


            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    private void getUI()
    {

        et_email=(EditText_Roboto_Regular)findViewById(R.id.et_emal);
        et_password=(EditText_Roboto_Regular)findViewById(R.id.et_password);
        bt_login=(Button_Roboto_Regular)findViewById(R.id.bt_login);
        tv_signup=(TextView_Roboto_Regular)findViewById(R.id.tv_signup);
        tv_forgot=(TextView_Roboto_Regular)findViewById(R.id.tv_forgot);
        btn_facebook = (LoginButton) findViewById(R.id.fb_loginbtn);
        btn_facebook.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        initializeFb();
        loginButton = (CannonballTwitterLoginButton) findViewById(R.id.bt_twitter);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                /*result.response.getUrl();*/

                session = Twitter.getSessionManager().getActiveSession();
                authToken = session.getAuthToken();
                final String token = authToken.token;
                final String secret = authToken.secret;


                Log.d("Token", token);
                Log.d("secret", secret);

                TwitterAuthClient authClient = new TwitterAuthClient();
                authClient.requestEmail(session, new Callback<String>() {
                    @Override
                    public void success(Result<String> result) {

                        Log.d("Data: ", result.data);

                        // Do something with the result, which provides the email address

                        //   new TwitterService(activity).execute(name, Static_Data.reg_id, id, token, email, "twitter", birthday, secret);

                    }

                    @Override
                    public void failure(TwitterException exception) {

                        Log.d("errorrrrr", exception.getMessage() + "");
                        exception.printStackTrace();

                    }
                });

                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
                twitterApiClient.getAccountService().verifyCredentials(true, true, new Callback<User>() {
                    @Override
                    public void success(Result<User> userResult) {
                        name = userResult.data.name;
                        email = userResult.data.email;
                        profileurl = userResult.data.profileImageUrl.replace("_normal", "");
                        id = userResult.data.idStr;
                        Log.d("name", name);
                        Log.e("profileurl1111", profileurl);
                        Log.d("id", id);
                        //   new TwitterService(activity).execute(name, Static_Data.reg_id, id, token, email, "twitter", birthday);
                     /*   editor.putString("twitter_image",profileurl);
                        editor.commit();*/
                    }

                    @Override
                    public void failure(TwitterException e) {

                    }
                });


                // Do something with result, which provides a TwitterSession for making API calls
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure


                Log.d("error", exception.getMessage() + "");
                exception.printStackTrace();

            }
        });


    }

    private void initializeFb() {
        callbackManager = CallbackManager.Factory.create();

        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                //     Toast.makeText(activity, "Success" + loginResult.getAccessToken().getToken(), Toast.LENGTH_LONG).show();

                //     Dialog_Manager.showToast(Login.this, loginResult.getAccessToken().getToken() + "");
                Log.d("FB Access Token>>>", loginResult.getAccessToken().getToken() + "");

                facebookAccessToken = loginResult.getAccessToken().getToken();

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                try {
                                    //          Dialog_Manager.startProgressDialog(Login_activity.this);
                                    System.out.println(object.toString());
                                    String Fb_id = object.getString("id");
                                    String email = object.getString("email");
                                    String image_url = object.getJSONObject("picture").getJSONObject("data").getString("url").toString();
                                    String name = object.getString("name");

                                    if (object.has("birthday")) {
                                        birthday = object.getString("birthday");
                                    }

                                    System.out.println("Name " + name);
                                    System.out.println("fb_id " + Fb_id);
                                    System.out.println("email " + email);
                                    System.out.println("image_value " + image_url);
                                    System.out.println("birthday " + birthday);
                                   /* editor.putString("fb_image", image_url);
                                    editor.commit();
                                    new FbService(activity).execute(name, Static_Data.reg_id, Fb_id, facebookAccessToken, email, "facebook", birthday);
*/
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "email,name,birthday,picture.width(720).height(720)");
                request.setParameters(parameters);
                request.executeAsync();


            }

            @Override
            public void onCancel() {
                //       Dialog_Manager.showToast(Login.this, "Logged Cancelled");

                Toast.makeText(activity, "Cancel", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException e) {
                //        Dialog_Manager.showToast(Login.this, "Logged Error  " + e);
                Log.d("FB Error>>>", e.getMessage());

                Toast.makeText(activity, "Error" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        btn_facebook.setReadPermissions("public_profile", "email", "user_birthday");

        btn_facebook.registerCallback(callbackManager, callback);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

private void onClick()
{
    bt_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {



                // call webservice


                Intent intent = new Intent(activity, HomeFragmentActivity.class);
                startActivity(intent);
                FinishAnimation.overidePendingTransition(activity);


            }


    });
    tv_signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {



            // call webservice


            Intent intent = new Intent(activity, RegistrationActivity.class);
            startActivity(intent);
            FinishAnimation.overidePendingTransition(activity);


        }


    });

    tv_forgot.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {



            // call webservice


            Intent intent = new Intent(activity, ForgotActivity.class);
            startActivity(intent);
            FinishAnimation.overidePendingTransition(activity);


        }


    });
}



    public static boolean isValidEmail(String email) {

        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



    private void requestFocus(View view) {
        if (view.requestFocus()) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        }


        }
